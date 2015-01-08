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

import org.boundlessworlds.world.InfGenBiome;
import org.boundlessworlds.world.generation.facets.BiomeFacet;
import org.boundlessworlds.world.generation.facets.InfiniteGenFacet;
import org.terasology.math.Vector2i;
import org.terasology.math.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.ChunkConstants;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;
import org.terasology.world.generation.facets.DensityFacet;
import org.terasology.world.generation.facets.SeaLevelFacet;


/**
 * @author Esereja
 */
public class InfiniteGenSolidRasterizer implements WorldRasterizer {

	//TODO finish this
    private Block water;
    private Block ice;
    private Block stone;
    private Block sand;
    private Block grass;
    private Block snow;
    private Block dirt;

    @Override
    public void initialize() {
        BlockManager blockManager = CoreRegistry.get(BlockManager.class);
        stone = blockManager.getBlock("core:stone");
        water = blockManager.getBlock("core:water");
        ice = blockManager.getBlock("core:Ice");
        sand = blockManager.getBlock("core:Sand");
        snow = blockManager.getBlock("core:Snow");
        dirt = blockManager.getBlock("core:Dirt");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        BiomeFacet biomeFacet = chunkRegion.getFacet(BiomeFacet.class);
        InfiniteGenFacet solidityFacet = chunkRegion.getFacet(InfiniteGenFacet.class);
        SeaLevelFacet seafacet;
        
        boolean sealevel=false; 
        if((seafacet=chunkRegion.getFacet(SeaLevelFacet.class)) != null ){
        	sealevel=true;
        }

        for (Vector3i pos : ChunkConstants.CHUNK_REGION) {
            InfGenBiome biome = biomeFacet.get(pos);
            chunk.setBiome(pos.x, pos.y, pos.z, biome);
            float density =solidityFacet.get(pos);
            
            if (density < 1){
            	if(sealevel){
	            	int posY = pos.y + chunk.getChunkWorldOffsetY();
	
	                if (posY == seafacet.getSeaLevel() && InfGenBiome.POLARDESERT == biomeFacet.get(pos)) {
	                	chunk.setBlock(pos,ice);
	                } else if (posY <= seafacet.getSeaLevel()) {
	                	chunk.setBlock(pos,water);
	                }else if(density>0.8 && ( posY>4000 || InfGenBiome.POLARDESERT == biomeFacet.get(pos))){
	                	chunk.setBlock(pos,snow);
	                }
            	}
            }else if(density < 2){
            	chunk.setBlock(pos,sand);
            }else if(density < 3){
            	chunk.setBlock(pos,sand);
            }else if(density < 4){
            	chunk.setBlock(pos,sand);
            }else if(density < 5){
            	chunk.setBlock(pos,sand);
            }else if(density > 10){
            	chunk.setBlock(pos,stone);
            }



        }
    }
    

    
}