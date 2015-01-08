/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.boundlessworlds.world.generator.facetProviders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.boundlessworlds.utilities.math.Statistics;
import org.boundlessworlds.world.generation.facets.FormFacet;
import org.boundlessworlds.world.generation.facets.HumidityFacet;
import org.boundlessworlds.world.generation.facets.InfiniteGenFacet;
import org.boundlessworlds.world.generation.facets.TemperatureFacet;
import org.boundlessworlds.world.generator.landFormDefinitions.LandFormDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.math.TeraMath;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetProvider;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Requires;
import org.terasology.world.generation.Updates;

@Updates(@Facet(InfiniteGenFacet.class))
@Requires({@Facet(FormFacet.class), @Facet(TemperatureFacet.class), @Facet(HumidityFacet.class)})
public class LandFormProvider implements FacetProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(LandFormProvider.class);

	protected List<LandFormDefinition> noiseList; 
	
    protected float modulus;
    protected float multifier;
    protected float increase;
    
    protected float orginalRatio;

    protected boolean debug;
    
    /**
     * Note DensityValues should be between [-1000-1000] for generaton to work.
     * @param frequency
     * @param multificator
     * @param increase
     */
    public LandFormProvider(float frequency, float multificator,float increase){
    	this.noiseList = new ArrayList<LandFormDefinition>();
    	
    	this.modulus=frequency;
    	this.multifier=multificator;
    	this.increase=increase;
    	this.orginalRatio=0;
    	this.debug=false;
    }
    
    /**
     * Note DensityValues should be between [-1000-1000] for generaton to work.
     * @param frequency
     * @param multificator
     * @param increase
     */
    public LandFormProvider(float orginalRatio,float frequency, float multificator,float increase){
    	this.noiseList = new ArrayList<LandFormDefinition>();
    	
    	this.modulus=frequency;
    	this.multifier=multificator;
    	this.increase=increase;
    	this.orginalRatio=orginalRatio;
    	this.debug=false;
    }
    
    /**
     * Note DensityValues should be between [-1000-1000] for generaton to work.
     * @param noiseList
     * @param frequency
     * @param multificator
     * @param increase
     */
    public LandFormProvider( ArrayList<LandFormDefinition> noiseList,float frequency, float multificator,float increase){
    	this.noiseList = noiseList;
    	
    	this.modulus=frequency;
    	this.multifier=multificator;
    	this.increase=increase;
    	this.orginalRatio=0;
    	this.debug=false;
    }
    
    
    @Override
    public void setSeed(long seed) {
    }
    
    @Override
    public void process(GeneratingRegion region) {
    	if(this.noiseList.size()<1){	
        	return;
        }
    	long time=0;
    	if(debug){
    		time=System.currentTimeMillis();
    	}
        InfiniteGenFacet denFacet =  region.getRegionFacet(InfiniteGenFacet.class);
        TemperatureFacet tempFacet =  region.getRegionFacet(TemperatureFacet.class);
        HumidityFacet humFacet =  region.getRegionFacet(HumidityFacet.class);
        FormFacet formFacet =  region.getRegionFacet(FormFacet.class);

		int x1 = denFacet.getWorldRegion().minX();
		int y1 = denFacet.getWorldRegion().minY();
		int z1 = denFacet.getWorldRegion().minZ();
        	        
        for(int x=denFacet.getRelativeRegion().minX();x<denFacet.getRelativeRegion().maxX()+1;x++){
        	for(int y=denFacet.getRelativeRegion().minY();y<denFacet.getRelativeRegion().maxY()+1;y++){
        		for(int z=denFacet.getRelativeRegion().minZ();z<denFacet.getRelativeRegion().maxZ()+1;z++){
        			
        			float orginal=denFacet.get(x, y, z);
        			
        			float humidity=humFacet.get(x, y, z);
        			float form=formFacet.get(x, y, z);
        			float temperature=tempFacet.get(x, y, z);
        			float n = 0;
        			int i=0;
        			
        			//TODO some really clever optimization here could do miracles for performance
        			int depth=this.noiseList.size();
        			float[] scores=  new float[depth];
        			float[] t= new float[depth];
        			while(i<this.noiseList.size()){
        				scores[i]=this.noiseList.get(i).getScore(y1, form, orginal, humidity, temperature);
        				t=Statistics.sortAdd(t, i, scores[i]);
        				//i could save position of score in orginal array and save some
        				//cpu which is used in searching later.
        				i++;
        			}
        			
        			//find best
        			float best=t[this.noiseList.size()-1];
        			i=Statistics.find(scores,best);
        			n=(this.noiseList.get(i).noise(x1, y1, z1));
        			
        			//find second best
        			if(this.noiseList.size()>1){
        				float second=t[this.noiseList.size()-2];
        				i=Statistics.find(scores,second);
        				//percentage of two noises, note all scores are negative. and thus sing disappear. after that smaller score has bigger value.
        				float p=1;
        				if(best<-1 && second<-1){
        					p=TeraMath.fastAbs(best/second);
        				}
        				n= n*(1-p);
        				n+=(this.noiseList.get(i).noise(x1, y1, z1)*p );
        			}
        			
        			
        			
        			n*=multifier;
                	if(modulus!=0){
                		n=(float) (n %modulus);
                	}
                	n+=increase;
                	if(this.orginalRatio!=0){
                		denFacet.set(x, y, z, n+orginal*this.orginalRatio);
                	}else{
                		denFacet.set(x, y, z, n);
                	}
                	
                	if(denFacet.getMax()<n){
                		denFacet.setMax(n);
                	}else if(denFacet.getMin()>n){
                		denFacet.setMin(n);
                	}
					z1++;
				}
				z1 = denFacet.getWorldRegion().minZ();
				y1++;
			}
			y1 = denFacet.getWorldRegion().minY();
			x1++;
		}
        if(debug){
        	logger.debug("chunk generated in " +((double)System.currentTimeMillis()-time)/1000 +" s");
        	}
    }

    
    public void addNoise(LandFormDefinition noise){
    	this.noiseList.add(noise);
    } 

	/**
	 * @return the modulus
	 */
	public float getModulus() {
		return modulus;
	}

	/**
	 * @param modulus the modulus to set
	 */
	public void setModulus(float modulus) {
		this.modulus = modulus;
	}

	/**
	 * @return the multifier
	 */
	public float getMultifier() {
		return multifier;
	}

	/**
	 * @param multifier the multifier to set
	 */
	public void setMultifier(float multifier) {
		this.multifier = multifier;
	}

	/**
	 * @return the increase
	 */
	public float getIncrease() {
		return increase;
	}

	/**
	 * @param increase the increase to set
	 */
	public void setIncrease(float increase) {
		this.increase = increase;
	}

	/**
	 * @return the orginalRatio
	 */
	public float getOrginalRatio() {
		return orginalRatio;
	}

	/**
	 * note that orginal noise should be between -1000-1000
	 * and values of these noises are -1 -1 per layer.
	 * so this value should be 0.001*wanted value 
	 * @param orginalRatio the orginalRatio to set
	 */
	public void setOrginalRatio(float orginalRatio) {
		this.orginalRatio = orginalRatio;
	}

	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * @param debug the debug to set
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}