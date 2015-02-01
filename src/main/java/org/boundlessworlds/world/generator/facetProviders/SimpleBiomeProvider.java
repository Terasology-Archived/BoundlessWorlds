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

import org.boundlessworlds.world.InfGenBiome;
import org.boundlessworlds.world.generation.facets.BiomeFacet;
import org.boundlessworlds.world.generation.facets.HumidityFacet;
import org.boundlessworlds.world.generation.facets.TemperatureFacet;
import org.terasology.world.biomes.Biome;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetProvider;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generation.Requires;

/**
 * Determines the biome based on temperature and humidity
 * @author Isäntä
 */
@Produces(BiomeFacet.class)
@Requires({@Facet(TemperatureFacet.class), @Facet(HumidityFacet.class)})
public class SimpleBiomeProvider implements FacetProvider {

	protected InfGenBiome biome;
	
	/**
	 * 
	 * @param in
	 */
	public SimpleBiomeProvider(InfGenBiome in){
		this.biome=in;
	}

    public void process(GeneratingRegion region) {
        TemperatureFacet temperature = region.getRegionFacet(TemperatureFacet.class);
        HumidityFacet surfaceHumidityFacet = region.getRegionFacet(HumidityFacet.class);

        Border3D border = region.getBorderForFacet(BiomeFacet.class);
        BiomeFacet biomeFacet = new BiomeFacet(region.getRegion(), border);

        for(int x=biomeFacet.getRelativeRegion().minX();x<biomeFacet.getRelativeRegion().maxX()+1;x++){
        	for(int y=biomeFacet.getRelativeRegion().minY();y<biomeFacet.getRelativeRegion().maxY()+1;y++){
        		for(int z=biomeFacet.getRelativeRegion().minZ();z<biomeFacet.getRelativeRegion().maxZ()+1;z++){
        			biomeFacet.set(x, y, z, this.biome);
        		}
        	}
        }
        region.setRegionFacet(BiomeFacet.class, biomeFacet);
    }

	/**
	 * @return the biome
	 */
	public InfGenBiome getBiome() {
		return biome;
	}

	/**
	 * @param biome the biome to use
	 */
	public void setBiome(InfGenBiome biome) {
		this.biome = biome;
	}

	/**
	 * does nothing
	 */
	public void setSeed(long seed) {
	}
}
