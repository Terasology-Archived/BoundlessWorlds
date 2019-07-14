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

import javax.vecmath.Vector3f;

import org.boundlessworlds.world.generation.facets.InfiniteGenFacet;
import org.terasology.math.Region3i;
import org.terasology.math.TeraMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetProvider;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Updates;

/***
 * 
 * @author Esereja
 */
@Updates(@Facet(InfiniteGenFacet.class))
public class NoiseMapFunctionsProvider implements FacetProvider {
	//FIXME finish this
	private int a;
	
	private boolean anti;
	private int function;
	
    private Vector3f origo;
    private Vector3f deviation;
 
    private double increase;
    private double multificator;
    private double modulus;

    /***
     * just applies operators to whole noise map(means every where). 
     * @param modulus
     * @param multificator
     * @param increase
     */
    public NoiseMapFunctionsProvider(double modulus,double multificator,double increase){
    	this.origo=new Vector3f(0f,0f,0f);
    	this.deviation=new Vector3f(0f,0f,0f);
    	
    	this.anti=false;
    	this.function=0;
    	
    	this.multificator=multificator;
    	this.increase=increase;
    	this.modulus=modulus;	
    }
    
    /***
     * Applies functions to given range.
     * @param origo of calculation.(means center)
     * @param deviation has deviation values of x,y,z. set radius on given coordinate 
     * direction that is affected by function. negative values inverse noise map of given area. 
     * zero causes calculation to ignore axis
     * @param mode if true removes selected area, false removes all but selected area
     */
    public NoiseMapFunctionsProvider(Vector3f origo,Vector3f deviation, boolean mode,int function){
    	this.origo=origo;
    	this.deviation=deviation;
    	
    	this.anti=mode;
    	this.function=function;
    	
    	this.multificator=1;
    	this.increase=0;
    	this.modulus=0;    	
    }
    
    public NoiseMapFunctionsProvider(Vector3f origo,Vector3f deviation, boolean mode,int function,double modulus,double multificator,double increase){
    	this.origo=origo;
    	this.deviation=deviation;
    	
    	this.anti=mode;
    	this.function=function;
    	
    	this.multificator=multificator;
    	this.increase=increase;
    	this.modulus=modulus;    	
    }
    
    @Override
    public void setSeed(long seed) {
    }
    
    @Override
    public void process(GeneratingRegion region) {

        InfiniteGenFacet facet =  region.getRegionFacet(InfiniteGenFacet.class);
        
        Region3i area = region.getRegion();
        int X=area.minX();//real universal Y coordinate
        int Y=area.minY();//real universal Y coordinate
        int Z=area.minZ();//real universal Y coordinate
        
        for (int y = facet.getRelativeRegion().minY(); y <= facet.getRelativeRegion().maxY(); ++y) {
        	for (int x = facet.getRelativeRegion().minX(); x <= facet.getRelativeRegion().maxX(); ++x) {
        		for (int z = facet.getRelativeRegion().minZ(); z <= facet.getRelativeRegion().maxZ(); ++z) {
        			float data = facet.get(new Vector3i(x,y,z));
        			if(this.deviation.x==0&&this.deviation.y==0&&this.deviation.z==0){
        		    	data+=increase;
        				data*=multificator;
        				if(modulus !=0){
        					data=(float) (data%modulus);
        				}
	        			}else{
	        			if(this.anti){// applies an all but selected area

	        				if(this.deviation.x!=0){
								double d =TeraMath.fastAbs(X-this.origo.x);
								if(d>this.deviation.x){
									double a=0;
										if(this.function ==2){
											a=(d/this.deviation.x);
											a*=a;
										}else{
											a=(d/this.deviation.x);
										}
				        		    	data+=(increase*a);
				        				data+=data*(multificator*a);
				        				if(modulus !=0){
				        					data+=(float) (data%modulus)*a;
				        				}
								}
							}
	        				
	        				if(this.deviation.y!=0){
								double d =TeraMath.fastAbs(Y-this.origo.y);
								if(d>this.deviation.y){
									double a=0;
										if(this.function ==2){
											a=(d/this.deviation.y);
											a*=a;
										}else{
											a=(d/this.deviation.y);
										}
				        		    	data+=(increase*a);
				        				data+=data*(multificator*a);
				        				if(modulus !=0){
				        					data+=(float) (data%modulus)*a;
				        				}
								}
							}
	        				
	        				if(this.deviation.z!=0){
								double d =TeraMath.fastAbs(Z-this.origo.z);
								if(d>this.deviation.z){
									double a=0;
										if(this.function ==2){
											a=(d/this.deviation.z);
											a*=a;
										}else{
											a=(d/this.deviation.z);
										}
				        		    	data+=(increase*a);
				        				data+=data*(multificator*a);
				        				if(modulus !=0){
				        					data+=(float) (data%modulus)*a;
				        				}
								}
							}
	        				
	        				
	        				data+=(increase);
	        				data+=data*(multificator);
	        				if(modulus !=0){
	        					data+=(float) (data%modulus);
	        				}

	
	        			}else{//applies on selected area
							if(this.deviation.x!=0){
								double d =TeraMath.fastAbs(X-this.origo.x);
								if(d>this.deviation.x){
									double a=0;
										if(this.function ==2){
											a=(this.deviation.x-d/this.deviation.x);
											a*=a;
										}else{
											a=(this.deviation.x-d/this.deviation.x);
										}
				        		    	data+=(increase*a);
				        				data+=data*(multificator*a);
				        				if(modulus !=0){
				        					data+=(float) (data%modulus)*a;
				        				}
								}
							}
							
							if(this.deviation.y!=0){
								double d =TeraMath.fastAbs(Y-this.origo.y);
								if(d>this.deviation.y){
									double a=0;
										if(this.function ==2){
											a=(this.deviation.y-d/this.deviation.y);
											a*=a;
										}else{
											a=(this.deviation.y-d/this.deviation.y);
										}
				        		    	data+=(increase*a);
				        				data+=data*(multificator*a);
				        				if(modulus !=0){
				        					data+=(float) (data%modulus)*a;
				        				}
								}
							}
							
							if(this.deviation.z!=0){
								double d =TeraMath.fastAbs(Z-this.origo.z);
								if(d>this.deviation.z){
									double a=0;
										if(this.function ==2){
											a=(this.deviation.z-d/this.deviation.z);
											a*=a;
										}else{
											a=(this.deviation.z-d/this.deviation.z);
										}
				        		    	data+=(increase*a);
				        				data+=data*(multificator*a);
				        				if(modulus !=0){
				        					data+=(float) (data%modulus)*a;
				        				}
								}
							}
	
	    				}
        			}
        			facet.set(x, y, z,data);
        			Z++;
        		}
        		X++;
        	}
        	Y++;
        }
        
    }
}
