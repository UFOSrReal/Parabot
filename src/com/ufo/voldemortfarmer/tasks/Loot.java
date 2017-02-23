package com.ufo.voldemortfarmer.tasks;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Players;
import com.ufo.voldemortfarmer.data.GrndItems;
import com.ufo.voldemortfarmer.data.Npcss;
import com.ufo.voldemortfarmer.data.Variables;

/**
 * Created by UFO on 1/10/17.
 */
public class Loot implements Strategy {
    @Override
    public boolean activate() {
        /*
        * If loot is near, and player is not in combat and npc is null
        */
        return GrndItems.lootingItems() != null && !Players.getMyPlayer().isInCombat() && Npcss.voldemort() == null;
    }

    @Override
    public void execute() {
        Variables vars = new Variables();
        vars.setState("Looting...");
        /*
        * Extra null check
        * Take loot
        */
        if (GrndItems.lootingItems() != null) {
            GrndItems.lootingItems().interact(GroundItems.Option.TAKE);
            Time.sleep(2000);
        }
    }
}
