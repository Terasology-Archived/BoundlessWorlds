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


import org.boundlessworlds.utilities.procedural.adapter.TrigonometricAdapter;
import org.terasology.math.geom.Vector3f;
import org.terasology.utilities.procedural.BrownianNoise3D;
import org.terasology.utilities.procedural.Noise3D;
import org.terasology.utilities.procedural.SimplexNoise;
import org.terasology.utilities.procedural.SubSampledNoise3D;

public class ChaosFormDefinition extends LandFormDefinition implements Noise3D {
    
    /**
     * 
     * @param formValue
     */
	public ChaosFormDefinition(Long seed){
    	super(-1000);
    	this.maxDensity=500f;
    	this.minDensity=-2f;
    	this.maxAltitude=1500f;
    	this.minAltitude=-2000f;
    	this.maxTemperature=Float.MAX_VALUE;
    	this.minTemperature=Float.MIN_VALUE;	
    	this.maxHumidity=Float.MAX_VALUE;
    	this.minHumidity=Float.MIN_VALUE;
    	
    	this.noiseList.add(new SubSampledNoise3D(
    			new TrigonometricAdapter(new BrownianNoise3D(new SimplexNoise(seed),2),2),
    					new Vector3f(0.008f, 0.008f, 0.008f),4
    			)
    	);
    }
    
}