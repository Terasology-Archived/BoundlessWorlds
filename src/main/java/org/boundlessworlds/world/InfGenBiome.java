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
package org.boundlessworlds.world;


import org.terasology.biomesAPI.Biome;

public enum InfGenBiome implements Biome {
	//defaults
    DEFAULT("Default", true, true, true, 1001),
    HOME("Home", true, false, true, 1002),
    WATER("Water", true, false, true, 1003),
    
    //SKY 
    SKY("Sky", true, true, true, 1004),

    //UNDERGROUNG
    UNDERGROUND("Underground", true, true, true, 1005),
    UNDERGROUNDWATER("UndergroundWater", true, true, true, 1006),
    CAVE("Cave", true, true, true, 1007),
    FROZENCAVE("FrozenCave", true, true, true, 1008),
    
    //LAND http://en.wikipedia.org/wiki/Ecozone
    POLARDESERT("PolarDesert", true, true, true, 1009),
    POLARTUNDRA("PolarTundra", true, true, true, 1009),
    ;

    private final String id;
    private final String name;
    private final boolean vegetationFriendly;
    private final boolean hostilleMobFriendly;
    private final boolean neutrallMobFriendly;
    private short hash;

    InfGenBiome(String name, boolean vegetationFriendly, boolean hostilleMobFriendly, boolean neutrallMobFriendly, int hash) {
        this.hash = (short) hash;
        this.id = "IG:" + name().toLowerCase();
        this.name = name;
        this.vegetationFriendly = vegetationFriendly;
        this.hostilleMobFriendly=hostilleMobFriendly;
        this.neutrallMobFriendly=neutrallMobFriendly;
    }

    /**
     * 
     * @return
     */
    public boolean isVegetationFriendly() {
        return vegetationFriendly;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public short biomeHash() {
        return hash;
    }

    /**
	 * @return the hostilleMobFriendly
	 */
	public boolean isHostilleMobFriendly() {
		return hostilleMobFriendly;
	}

	/**
	 * @return the neutrallMobFriendly
	 */
	public boolean isNeutrallMobFriendly() {
		return neutrallMobFriendly;
	}

}
