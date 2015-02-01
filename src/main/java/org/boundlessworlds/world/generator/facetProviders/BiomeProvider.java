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
public class BiomeProvider implements FacetProvider {

    public void setSeed(long seed) {
    }

    public void process(GeneratingRegion region) {
        TemperatureFacet temperature = region.getRegionFacet(TemperatureFacet.class);
        HumidityFacet surfaceHumidityFacet = region.getRegionFacet(HumidityFacet.class);

        Border3D border = region.getBorderForFacet(BiomeFacet.class);
        BiomeFacet biomeFacet = new BiomeFacet(region.getRegion(), border);

		int x1 = biomeFacet.getWorldRegion().minX();
		int y1 = biomeFacet.getWorldRegion().minY();
		int z1 = biomeFacet.getWorldRegion().minZ();
        
		//TODO finnish this
        for(int x=biomeFacet.getRelativeRegion().minX();x<biomeFacet.getRelativeRegion().maxX()+1;x++){
        	for(int y=biomeFacet.getRelativeRegion().minY();y<biomeFacet.getRelativeRegion().maxY()+1;y++){
        		for(int z=biomeFacet.getRelativeRegion().minZ();z<biomeFacet.getRelativeRegion().maxZ()+1;z++){
        			biomeFacet.set(x, y, z, InfGenBiome.DEFAULT);
                	z1++;
        		}
        		z1 = biomeFacet.getWorldRegion().minZ();
        		y1++;
        	}
        	y1 = biomeFacet.getWorldRegion().minY();
        	x1++;
        }
        region.setRegionFacet(BiomeFacet.class, biomeFacet);
    }
}
