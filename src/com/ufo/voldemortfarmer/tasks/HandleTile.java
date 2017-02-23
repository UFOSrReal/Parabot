package com.ufo.voldemortfarmer.tasks;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Walking;
import com.ufo.voldemortfarmer.data.GrndItems;
import com.ufo.voldemortfarmer.data.Npcss;
import com.ufo.voldemortfarmer.data.Variables;

/**
 * Created by UFO on 1/10/17.
 */
public class HandleTile implements Strategy {
    @Override
    public boolean activate() {
        /*
        * If loot is null, npc is near, and player is not in safespot, return true
        */
        return !Players.getMyPlayer().getLocation().equals(Variables.PLAYER_SS) && GrndItems.lootingItems() == null && Npcss.voldemort() != null
                || !Players.getMyPlayer().getLocation().equals(Variables.PLAYER_SS) && Variables.PLAYER_SS.getLocation().distanceTo() < 20;
    }

    @Override
    public void execute() {
        Variables vars = new Variables();
        vars.setState("Getting on tile");

        /*
        * Walk to safespot
        */
        Walking.walkTo(Variables.PLAYER_SS);

        /*
        * Sleep until player is in safespot
        */
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Players.getMyPlayer().getLocation().equals(Variables.PLAYER_SS);
            }
        }, 2000);
    }
}

