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
package org.boundlessworlds.world.generator.landFormDefinitions;


import org.boundlessworlds.utilities.procedural.adapter.AdditionAdapter;
import org.boundlessworlds.world.generation.facets.InfiniteGenFacet;
import org.terasology.math.geom.Vector3f;
import org.terasology.utilities.procedural.BrownianNoise3D;
import org.terasology.utilities.procedural.Noise3D;
import org.terasology.utilities.procedural.SimplexNoise;
import org.terasology.utilities.procedural.SubSampledNoise3D;
import org.terasology.world.generation.Produces;

/**
 * 
 * @author esereja
 */
@Produces(InfiniteGenFacet.class)
public class HillsFormDefinition extends LandFormDefinition implements Noise3D {
    
    /**
     * 
     * @param seed
     */
	public HillsFormDefinition(long seed){
    	super(1);
    	this.maxDensity=400f;
    	this.minDensity=-100f;
    	this.maxAltitude=500f;
    	this.minAltitude=-1500f;
    	this.maxTemperature=Float.MAX_VALUE;
    	this.minTemperature=Float.MIN_VALUE;	
    	this.maxHumidity=Float.MAX_VALUE;
    	this.minHumidity=Float.MIN_VALUE;
    	
    	this.noiseList.add(new AdditionAdapter(
    			new SubSampledNoise3D(
    			new BrownianNoise3D(new SimplexNoise(seed),4),
    			new Vector3f(0.005f, 0.005f, 0.005f), 4),
    			0.1f)
    	);
    }
    
}