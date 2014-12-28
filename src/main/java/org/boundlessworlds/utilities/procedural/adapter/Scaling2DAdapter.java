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
package org.boundlessworlds.utilities.procedural.adapter;

import javax.vecmath.Vector2f;

import org.terasology.utilities.procedural.Noise2D;

/**
 * @author Esereja
 */
public class Scaling2DAdapter implements Noise2D {

    private Noise2D noise;
    
    private Vector2f scale;
    
    /**
     * 
     * @param noise
     * @param Addition
     */
    public Scaling2DAdapter(Noise2D noise,Vector2f scale) {
        this.noise = noise;
        this.scale=scale;
    }


    @Override
    public float noise(float x, float y) {
    	return this.noise.noise(x*scale.x , y*scale.y);
    }
  
}
