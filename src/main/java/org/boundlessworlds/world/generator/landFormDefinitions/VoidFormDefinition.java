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

import org.boundlessworlds.utilities.procedural.noise.NullNoise;
import org.terasology.utilities.procedural.Noise3D;

public class VoidFormDefinition extends LandFormDefinition implements Noise3D {
    
    /**
     * 
     * @param formValue
     */
	public VoidFormDefinition(Long seed){
    	super(-100);
    	this.maxDensity=Float.MAX_VALUE;
    	this.minDensity=100f;
    	this.maxAltitude=Float.MAX_VALUE;
    	this.minAltitude=-2000f;
    	this.maxTemperature=Float.MAX_VALUE;
    	this.minTemperature=Float.MIN_VALUE;	
    	this.maxHumidity=Float.MAX_VALUE;
    	this.minHumidity=Float.MIN_VALUE;
    	
    	this.noiseList.add(new NullNoise(0f));
    }
    
}