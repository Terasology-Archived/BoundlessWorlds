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

import org.boundlessworlds.world.generation.facets.InfiniteGenFacet;
import org.terasology.math.geom.Vector3f;
import org.terasology.utilities.procedural.Noise3D;
import org.terasology.utilities.procedural.SubSampledNoise3D;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetProvider;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Updates;
/**
 * 
 * @author esereja
 */
@Updates(@Facet(InfiniteGenFacet.class))
public class Noise3DTerainProvider implements FacetProvider {

	protected SubSampledNoise3D sampledNoise;

	protected Vector3f zoom;

	protected double modulus;
	protected double multifier;
	protected double increase;

	public Noise3DTerainProvider(Noise3D noise, Vector3f zoom,
			double frequency, double multificator, double increase) {
		this.zoom = zoom;
		this.modulus = frequency;
		this.multifier = multificator;
		this.increase = increase;
		this.sampledNoise = new SubSampledNoise3D(noise, zoom, 4);
	}

	/**
	 * this constructor doesn't initialize noise, so do it by hand!
	 * 
	 * @param zoom
	 * @param frequency
	 * @param multificator
	 * @param increase
	 */
	public Noise3DTerainProvider(Vector3f zoom, double frequency,
			double multificator, double increase) {
		this.zoom = zoom;
		this.modulus = frequency;
		this.multifier = multificator;
		this.increase = increase;
	}

	public void setSeed(long seed) {
	}

	public void process(GeneratingRegion region) {
		InfiniteGenFacet facet = region.getRegionFacet(InfiniteGenFacet.class);
		float[] noise = sampledNoise.noise(facet.getWorldRegion());

		float[] orginalData = facet.getInternal();
		for (int i = 0; orginalData.length > i; i++) {
			noise[i] *= multifier;
			if (modulus != 0) {
				noise[i] = (float) (noise[i] % modulus);
			}
			noise[i] += increase;
			orginalData[i] += noise[i];
			if (facet.getMax() < orginalData[i]) {
				facet.setMax(orginalData[i]);
			} else if (facet.getMin() > orginalData[i]) {
				facet.setMin(orginalData[i]);
			}
		}
	}

	/**
	 * @return the zoom
	 */
	public Vector3f getZoom() {
		return zoom;
	}

	/**
	 * @param zoom
	 *            the zoom to set
	 */
	public void setZoom(Vector3f zoom) {
		this.zoom = zoom;
	}

	/**
	 * 
	 * @return
	 */
	public double getIncrease() {
		return increase;
	}

	/**
	 * 
	 * @param increase
	 */
	public void setIncrease(double increase) {
		this.increase = increase;
	}

	/**
	 * @return the frequency
	 */
	public double getFrequency() {
		return modulus;
	}

	/**
	 * @param frequency
	 *            the frequency to set
	 */
	public void setFrequency(double frequency) {
		this.modulus = frequency;
	}

	/**
	 * @return the multificator
	 */
	public double getMultificator() {
		return multifier;
	}

	/**
	 * @param multificator
	 *            the multificator to set
	 */
	public void setMultificator(double multificator) {
		this.multifier = multificator;
	}

	/**
	 * 
	 * @return
	 */
	public SubSampledNoise3D getSurfaceNoise() {
		return sampledNoise;
	}

	/**
	 * 
	 * @param noise
	 */
	public void setSurfaceNoise(Noise3D noise) {
		this.sampledNoise = new SubSampledNoise3D(noise, this.zoom, 4);
	}

}
