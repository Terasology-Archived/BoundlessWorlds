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
package org.boundlessworlds.world.generator.rasterizers;

import org.boundlessworlds.world.generation.facets.InfiniteGenFacet;
import org.terasology.math.TeraMath;
import org.terasology.math.Vector3i;
import org.terasology.world.chunks.ChunkConstants;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.Updates;
import org.terasology.world.generation.WorldRasterizer;
import org.terasology.world.generation.facets.DensityFacet;

/**
 * @author Esereja
 */
@Updates(@Facet(DensityFacet.class))
public class BW2DefaultRasterizer implements WorldRasterizer {
	
    private int mode;
    private float rangeStart;
    private float rangeEnd;
    private boolean blend;
    private boolean replace;
    private boolean multibly;
    
    /**
     *  this will apply values to default density and have only little effect near surface.
     */
    public BW2DefaultRasterizer(){
    	this.mode=0;
    	this.rangeStart=0;
    	this.rangeEnd=0;
    	this.blend=true;
    	this.multibly=false;
    	this.replace=false;
    }

    /**
     * this will apply values to default density and have only little effect near surface.
     * @param mode this set how current world will be modified whit bw density data. 0 means all densities will be affected.
     * values<1 mean only negatives densities affect world and value>1 means only positive values affect world.
     */
    public BW2DefaultRasterizer(int mode){
    	this.mode=mode;
    	this.rangeStart=0;
    	this.rangeEnd=0;
    	this.blend=true;
    	this.multibly=false;
    	this.replace=false;
    }
    
    /**
     * @param mode this set how current world will be modified whit bw density data. 0 means all densities will be affected.
     * values<1 mean only negatives densities affect world and value>1 means only positive values affect world.
     * @param start of density range of default world
     * @param end of density range of default world
     * @param blend values(add)
     * @param multibly values
     * @param replace values by BW densities
     */
    public BW2DefaultRasterizer(int mode,float start,float end,boolean blend,boolean multibly,boolean replace){
    	this.mode=mode;
    	this.rangeStart=start;
    	this.rangeEnd=end;
    	this.blend=blend;
    	this.multibly=multibly;
    	this.replace=replace;
    }

    public void initialize() {
    }

    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        DensityFacet solidityFacet = chunkRegion.getFacet(DensityFacet.class);        
        InfiniteGenFacet densityFacet = chunkRegion.getFacet(InfiniteGenFacet.class);

        for (Vector3i pos : ChunkConstants.CHUNK_REGION) {

            float density = solidityFacet.get(pos);
            float bwdensity = densityFacet.get(pos);
            
            float midpoint=(rangeStart-rangeEnd)/2;
            
            
            if(density>rangeStart || rangeStart==0){
            	if(density<rangeEnd || rangeEnd==0){
            		float mult=0;
            		if(rangeEnd==0 && rangeStart==0){
            			mult=1;
            		}else if(rangeEnd==0){
                    	mult=1;//rangeStart;
                    }else if(rangeStart==0){
                    	mult=1;//rangeEnd;
                    }else{
                    	mult=(1-TeraMath.fastAbs((density-rangeStart-midpoint)/midpoint));//smoothing that calculates percentage distance from mid point of range to one end of range
                    }
            		if(mode==0){
			        	if(this.blend){
			        		density+=bwdensity*mult;
			        	}
			        	
			        	if(this.multibly){
			        		density*=bwdensity*mult;
			        	}
			        	
			        	if(this.replace){
			        		density=bwdensity*mult;//+density*(1-mult);
			        	}
			        	
			        }else if(this.mode<0 && bwdensity<0){
			        	if(this.blend){
			        		density+=bwdensity*mult;
			        	}
			        	
			        	if(this.multibly){
			        		density*=bwdensity*mult;
			        	}
			        	
			        	if(this.replace){
			        		density=bwdensity*mult;//+density*(1-mult);
			        	}
			        	
			        }else if(bwdensity>0){
			        	if(this.blend){
			        		density+=bwdensity*mult;
			        	}
			        	
			        	if(this.multibly){
			        		density*=bwdensity*mult;
			        	}
			        	
			        	if(this.replace){
			        		density=bwdensity*mult;//+density*(1-mult);
			        	}
			        	
			        }
            	}
            }
            solidityFacet.set(pos, density);
        }
        
    }

}