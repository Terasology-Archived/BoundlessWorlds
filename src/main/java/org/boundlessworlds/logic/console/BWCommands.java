/*
 * Copyright 2013 MovingBlocks
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
package org.boundlessworlds.logic.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.console.commandSystem.annotations.Command;
import org.terasology.logic.console.commandSystem.annotations.CommandParam;
import org.terasology.registry.In;

/**
 * 
 * @author Esereja
 */
@RegisterSystem
public class BWCommands extends BaseComponentSystem {
	
    @In
    private EntityManager entityManager;
	
	private static final Logger logger = LoggerFactory.getLogger(BWCommands.class);
	
    @Command(shortDescription = "prints text to log, Info level", runOnServer = true)
    public String logInfo(@CommandParam("Text") String info,EntityRef client) {
    	logger.info(client.getId()+ ":" + info);
        return "wrote \'" + info +" \' to log";
    }
    
    @Command(shortDescription = "prints text to log, Warn level", runOnServer = true)
    public String logWarn(@CommandParam("Text") String info,EntityRef client) {
    	logger.warn(client.getId()+ ":" +info);
        return "wrote \'" + info +" \' to log";
    }
    
    @Command(shortDescription = "prints text to log, Error level", runOnServer = true)
    public String logError(@CommandParam("Text") String info,EntityRef client) {
    	logger.error(client.getId()+ ":" +info);
        return "wrote \'" + info +" \' to log";
    }
    
    @Command(shortDescription = "Displays debug information of you", runOnServer = true)
    public String debugSelf(EntityRef client) {
        return client.toFullDescription();
    }
    
    @Command(shortDescription = "Displays debug information of entity by entity ref number", runOnServer = true)
    public String debugEntityByID(@CommandParam("entity ref") final int targetID) {
    	EntityRef target=entityManager.getEntity(targetID);
        return target.toFullDescription();
    }
    
    /*@Command(shortDescription = "abc")
    public String test(@CommandParam("a") final float in) {
    	float[] ain={1,2,3,4,5,8,7,6,9.0000001f,9.001f,9.1f,11,10};
    	Arrays.sort(ain);
        return ""+Statistics.find(ain, in);
    }*/
	
}