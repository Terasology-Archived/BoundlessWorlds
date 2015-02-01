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

import java.util.ArrayList;
import java.util.List;

import org.terasology.utilities.procedural.Noise3D;
import org.terasology.world.generation.Produces;

/**
 * 
 * @author esereja
 */
public class LandFormDefinition implements Noise3D {
	
	protected List<Noise3D> noiseList;
	
    protected float formValue;
 	
    protected float minDensity;
    protected float maxDensity;   

    protected float minAltitude;
    protected float maxAltitude;	
    
    protected float minTemperature;
    protected float maxTemperature;
	
    protected float minHumidity;
    protected float maxHumidity;
    
    protected float scoreOffset;

    
    /**
     * 
     * @param formValue
     */
    protected LandFormDefinition(float formValue){
    	this.formValue=formValue;
    	this.maxDensity=0;
    	this.minDensity=0;
    	this.maxAltitude=0;
    	this.minAltitude=0;
    	this.maxTemperature=0;
    	this.minTemperature=0;	
    	this.maxHumidity=0;
    	this.minHumidity=0;
    	this.noiseList = new ArrayList<Noise3D>();
    	this.scoreOffset=0;
    }
    
    /**
     * 
     * @param formValue
     * @param minDensity
     * @param maxDensity
     * @param minAltitude
     * @param maxAltitude
     * @param minTemperature
     * @param maxTemperature
     * @param minHumidity
     * @param maxHumidity
     */
    public LandFormDefinition(float formValue,
    		float minDensity,float maxDensity,float minAltitude,float maxAltitude,float minTemperature,float maxTemperature
    		,float minHumidity,float maxHumidity){
    	this.formValue=formValue;
    	this.maxDensity=maxDensity;
    	this.minDensity=minDensity;
    	this.maxAltitude=maxAltitude;
    	this.minAltitude=minAltitude;
    	this.maxTemperature=maxTemperature;
    	this.minTemperature=minTemperature;	
    	this.maxHumidity=maxHumidity;
    	this.minHumidity=minHumidity;
    	this.noiseList = new ArrayList<Noise3D>();
    	this.scoreOffset=0;
    }
    
    /**
     * 
     * @param noiseList
     * @param formValue
     * @param minDensity
     * @param maxDensity
     * @param minAltitude
     * @param maxAltitude
     * @param minTemperature
     * @param maxTemperature
     * @param minHumidity
     * @param maxHumidity
     */
    public LandFormDefinition( ArrayList<Noise3D> noiseList,float formValue,
    		float minDensity,float maxDensity,float minAltitude,float maxAltitude,float minTemperature,float maxTemperature
    		,float minHumidity,float maxHumidity){
    	this.formValue=formValue;
    	this.maxDensity=maxDensity;
    	this.minDensity=minDensity;
    	this.maxAltitude=maxAltitude;
    	this.minAltitude=minAltitude;
    	this.maxTemperature=maxTemperature;
    	this.minTemperature=minTemperature;	
    	this.maxHumidity=maxHumidity;
    	this.minHumidity=minHumidity;
    	this.noiseList = noiseList;
    	this.scoreOffset=0;
    }
    
    public float noise(float x, float y,float z) {
		float n= 0;
		int i=0;
		
		while(i<this.noiseList.size()){
			n+=this.noiseList.get(i).noise(x, y, z);
			i++;
		}
		return n;
    }

    //TODO In need of heavy optimization
    /**
     * get Score of suitability of this terrain generator for these conditions.
     *  zero is best suitability, smaller values mean worse suitability
     * @param altitude
     * @param density
     * @param form
     * @param humidity
     * @param temperature
     * @return
     */
    public float getScore(final float altitude,final float form,final float density,final float humidity,final float temperature) {
    	float result=this.scoreOffset;
    	
    	if(this.formValue!=0){
	    	if(form>this.formValue){
	    		result-=(form-this.formValue)*10;
	    	}else
		    	if(form<this.formValue){
		    		result+=(form+this.formValue)*10;
	    	}
    	}
    	
    	//if(this.maxAltitude!=Float.MAX_VALUE && this.minAltitude!=Float.MIN_VALUE){
    		if( altitude>this.maxAltitude){
    			result-=altitude-this.maxAltitude;
    		}else
	    		if(altitude<this.minAltitude){
	    			result+=altitude+this.minAltitude;
    		}
    	//}
    	
    	//if(this.maxDensity!=Float.MAX_VALUE && this.minDensity!=Float.MIN_VALUE){
    		if(density>this.maxDensity){
    			result-=(density-this.maxDensity)*50;
    		}else
    			if(density<this.minDensity){
    				result+=(density+this.minDensity)*50;
    		}
    	//}
    	
    	//if(this.maxHumidity!=Float.MAX_VALUE && this.minHumidity!=Float.MIN_VALUE){
	    	if(humidity>this.maxHumidity){
	    		result-=(humidity-this.maxHumidity)*100;
	    	}else
		    	if(humidity<this.minHumidity){
		    		result+=(humidity+this.minHumidity)*100;
	    	}
    	//}
    	
    	//if(this.maxTemperature!=Float.MAX_VALUE && this.minTemperature!=Float.MIN_VALUE){
	    	if(temperature>this.maxTemperature){
	    		result-=(temperature-this.maxTemperature)*10;
	    	}else
		    	if(temperature<this.minTemperature){
		    		result+=(temperature+this.minTemperature)*10;
	    	}
    	//}
    	
    	return result;
    }
    
    public void addNoise(Noise3D noise){
    	this.noiseList.add(noise);
    } 

	/**
	 * @return the formValue
	 */
	public float getFormValue() {
		return formValue;
	}

	/**
	 * @param formValue the formValue to set
	 */
	public void setFormValue(float formValue) {
		this.formValue = formValue;
	}

	/**
	 * @return the minTemperature
	 */
	public float getMinTemperature() {
		return minTemperature;
	}

	/**
	 * @param minTemperature the minTemperature to set
	 */
	public void setMinTemperature(float minTemperature) {
		this.minTemperature = minTemperature;
	}

	/**
	 * @return the maxTemperature
	 */
	public float getMaxTemperature() {
		return maxTemperature;
	}

	/**
	 * @param maxTemperature the maxTemperature to set
	 */
	public void setMaxTemperature(float maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	/**
	 * @return the minHumidity
	 */
	public float getMinHumidity() {
		return minHumidity;
	}

	/**
	 * @param minHumidity the minHumidity to set
	 */
	public void setMinHumidity(float minHumidity) {
		this.minHumidity = minHumidity;
	}

	/**
	 * @return the maxHumidity
	 */
	public float getMaxHumidity() {
		return maxHumidity;
	}

	/**
	 * @param maxHumidity the maxHumidity to set
	 */
	public void setMaxHumidity(float maxHumidity) {
		this.maxHumidity = maxHumidity;
	}

	/**
	 * @return the minDensity
	 */
	public float getMinDensity() {
		return minDensity;
	}

	/**
	 * @param minDensity the minDensity to set
	 */
	public void setMinDensity(float minDensity) {
		this.minDensity = minDensity;
	}

	/**
	 * @return the maxDensity
	 */
	public float getMaxDensity() {
		return maxDensity;
	}

	/**
	 * @param maxDensity the maxDensity to set
	 */
	public void setMaxDensity(float maxDensity) {
		this.maxDensity = maxDensity;
	}

	/**
	 * @return the minAltitude
	 */
	public float getMinAltitude() {
		return minAltitude;
	}

	/**
	 * @param minAltitude the minAltitude to set
	 */
	public void setMinAltitude(float minAltitude) {
		this.minAltitude = minAltitude;
	}

	/**
	 * @return the maxAltitude
	 */
	public float getMaxAltitude() {
		return maxAltitude;
	}

	/**
	 * @param maxAltitude the maxAltitude to set
	 */
	public void setMaxAltitude(float maxAltitude) {
		this.maxAltitude = maxAltitude;
	}

	/**
	 * @return the scoreOffset
	 */
	public float getScoreOffset() {
		return scoreOffset;
	}

	/**
	 * @param scoreOffset the scoreOffset to set
	 */
	public void setScoreOffset(float scoreOffset) {
		this.scoreOffset = scoreOffset;
	}
}