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

import org.terasology.world.biomes.Biome;

public enum InfGenBiome implements Biome {
	//defaults
    DEFAULT("Default", true, true, true, 0.0f, 0.5f, 20f),
    HOME("Home", true, false, true, 0.0f, 0.5f, 20f),
    WATER("Water", true, false, true, 0.0f, 1.0f, 20f),
    
    //SKY 
    SKY("Sky", true, true, true, 0.9f, 0.5f, 0.5f),
    
    //Fantasy sky
    
    //UNDERGROUNG
    UNDERGROUND("Underground", true, true, true, 0.8f, 0.8f, 20f),// just hard stone
    UNDERGROUNDWATER("UndergroundWater", true, true, true, 0.8f, 0.8f, 20f),// under ground water
    CAVE("Cave", true, true, true, 0.8f, 0.8f, 20f),
    FROZENCAVE("FrozenCave", true, true, true, 0.8f, 0.8f, 20f),
    
    //fantasy under ground
    
    //LAND http://en.wikipedia.org/wiki/Ecozone
    POLARDESERT("PolarDesert", true, true, true, 0.0f, 1.0f, 42f),
    POLARTUNDRA("PolarTundra", true, true, true, 0.0f, 1.0f, 42f),
    
    //http://en.wikipedia.org/wiki/Palearctic_ecozone
    /*PALEARTICMIXEDFOREST("PalearcticMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    PALEARTICMONOFOREST("PalearctiMonoForest", true, true, true, 0.0f, 0.5f, 0.6f),
    PALEARTICSWAMP("PalearcticSwamp", true, true, true, 0.0f, 0.5f, 0.6f),
    PALEARTICSAVANNA("PalearcticSavanna", true, true, true, 0.0f, 0.5f, 0.6f),
    PALEARTICGRASSLAND("PalearcticGrassland", true, true, true, 0.0f, 0.5f, 0.6f),
    PALEARTICDESERT("PalearcticDeseret", true, true, true, 0.0f, 0.5f, 0.6f),
    
    //http://en.wikipedia.org/wiki/Nearctic_ecozone
    NEARCTICMIXEDFOREST("NearcticMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    NEARCTICMONOFOREST("NearcticMonoForest", true, true, true, 0.0f, 0.5f, 0.6f),
    NEARCTICSWAMP("NearcticSwamp", true, true, true, 0.0f, 0.5f, 0.6f),
    NEARCTICSAVANNA("NearcticSavanna", true, true, true, 0.0f, 0.5f, 0.6f),
    NEARCTICGRASSLAND("NearcticGrassland", true, true, true, 0.0f, 0.5f, 0.6f),
    NEARCTICDESERT("NearcticDeseret", true, true, true, 0.0f, 0.5f, 0.6f),
    
    //http://en.wikipedia.org/wiki/Afrotropic_ecozone
    AFROTROPICMIXEDFOREST("AfrotropicMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    AFROTROPICMONOFOREST("AfrotropicMonoForest", true, true, true, 0.0f, 0.5f, 0.6f),
    AFROTROPICSWAMP("AfrotropicSwamp", true, true, true, 0.0f, 0.5f, 0.6f),
    AFROTROPICSAVANNA("AfrotropicSavanna", true, true, true, 0.0f, 0.5f, 0.6f),
    AFROTROPICGRASSLAND("AfrotropicGrassland", true, true, true, 0.0f, 0.5f, 0.6f),
    AFROTROPICDESERT("AfrotropicDeseret", true, true, true, 0.0f, 0.5f, 0.6f),
    
    //http://en.wikipedia.org/wiki/Neotropic_ecozone
    NEOTROPICMIXEDFOREST("NeotropicMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    NEOTROPICMONOFOREST("NeotropicMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    NEOTROPICSWAMP("NeotropicMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    NEOTROPICSAVANNA("NeotropicMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    NEOTROPICGRASSLAND("NeotropicMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    NEOTROPICDESERT("NeotropicMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    
    //http://en.wikipedia.org/wiki/Australasian_ecozone
    AUSTRALASIAMIXEDFOREST("AustralasiaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    AUSTRALASIAMONOFOREST("AustralasiaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    AUSTRALASIASWAMP("AustralasiaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    AUSTRALASIASAVANNA("AustralasiaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    AUSTRALASIAGRASSLAND("AustralasiaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    AUSTRALASIADESERT("AustralasiaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    
    //http://en.wikipedia.org/wiki/Indomalaya_ecozone
    INDOMALAYAMIXEDFOREST("IndoMalayaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    INDOMALAYAMONOFOREST("IndoMalayaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    INDOMALAYASWAMP("IndoMalayaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    INDOMALAYASAVANNA("IndoMalayaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    INDOMALAYADESERT("IndoMalayaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    
    //http://en.wikipedia.org/wiki/Oceania_ecozone
    OCEANIAMIXEDFOREST("OceaniaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    OCEANIAMONOFOREST("OceaniaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    OCEANIASWAMP("OceaniaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    OCEANIASAVANNA("OceaniaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    OCEANIAGRASSLAND("OceaniaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    OCEANIADESERT("OceaniaMixedForest", true, true, true, 0.0f, 0.5f, 0.6f),
    
    //http://en.wikipedia.org/wiki/Antarctic_ecozone
    ANTARCTICSCOTIA("AntarcticScotia", true, true, true, 0.0f, 0.5f, 0.6f),
    ANTARCTICDESERT("Antarctic", true, true, true, 0.0f, 0.5f, 0.6f),
    ANTARCTICTUNDRA("Antarctic", true, true, true, 0.0f, 0.5f, 0.6f),
    ANTARCTICINDIA("Antarctic", true, true, true, 0.0f, 0.5f, 0.6f),
    
    /*out of use till needed
    
    //FRESH WATER SYSTEMS
    NEARCTICLAKE("NearcticLake", true, true, true, 0.0f, 1.0f, 42f),
    NEARCTICRIVER("NearcticRiver", true, true, true, 0.0f, 1.0f, 42f),
    NEARCTICWETLAND("NearcticWetland", true, true, true, 0.0f, 1.0f, 42f),
    PALEARCTICLAKE("PalearcticLake", true, true, true, 0.0f, 1.0f, 42f),
    PALEARCTICRIVER("PalearcticRiver", true, true, true, 0.0f, 1.0f, 42f),
    PALEARCTICWETLAND("PalearcticWetland", true, true, true, 0.0f, 1.0f, 42f),
    AFROTROPICLAKE("AfrotropicLake", true, true, true, 0.0f, 1.0f, 42f),
    AFROTROPICRIVER("AfrotropicRiver", true, true, true, 0.0f, 1.0f, 42f),
    AFROTROPICWETLAND("AfrotropicWetland", true, true, true, 0.0f, 1.0f, 42f),
    INDOMALAYALAKE("IndomalayaLake", true, true, true, 0.0f, 1.0f, 42f),
    INDOMALAYARIVER("IndomalayaRiver", true, true, true, 0.0f, 1.0f, 42f),
    INDOMALAYAWETLAND("IndomalayaWetland", true, true, true, 0.0f, 1.0f, 42f),
    //Fantasy fresh water systems
    
    //SEA
    POLARSEA("PolarSea", true, true, true, 0.0f, 1.0f, 42f),
    TEMPERATESEA("TemperateSea", true, true, true, 0.0f, 1.0f, 42f),
    TROPICALSEA("TropicalSea", true, true, true, 0.0f, 1.0f, 42f),
    CORALSEA("CoralSea", true, true, true, 0.0f, 1.0f, 42f),
    HYDROTHERMALVENTSEA("HydrothermalVentSea", true, true, true, 0.0f, 1.0f, 42f),
    ABYSALSEA("AbysalSea", true, true, true, 0.0f, 1.0f, 42f),
    HADALSEA("HadalSea", true, true, true, 0.0f, 1.0f, 42f)
    
    */
    
    //FANTASY SEA
    ;

    private final String id;
    private final String name;
    private final boolean vegetationFriendly;
    private final boolean hostilleMobFriendly;
    private final boolean neutrallMobFriendly;
    private final float fog;
    private final float humidity;
    private final float temperature;

    private InfGenBiome(String name, boolean vegetationFriendly, boolean hostilleMobFriendly, boolean neutrallMobFriendly, float fog, float humidity, float temperature) {
        this.id = "IG:" + name().toLowerCase();
        this.name = name;
        this.vegetationFriendly = vegetationFriendly;
        this.hostilleMobFriendly=hostilleMobFriendly;
        this.neutrallMobFriendly=neutrallMobFriendly;
        this.fog = fog;
        this.humidity = humidity;
        this.temperature = temperature;
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
    public float getFog() {
        return fog;
    }

    @Override
    public float getHumidity() {
        return humidity;
    }

    @Override
    public float getTemperature() {
        return temperature;
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
