package com.ufo.flamepernixfarmer.tasks;

import com.ufo.flamepernixfarmer.utils.GrndItems;
import com.ufo.flamepernixfarmer.utils.Monsters;
import com.ufo.flamepernixfarmer.utils.Variables;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Walking;

/**
 * Created by UFO
 */
public class HandleTile implements Strategy {
    Variables vars = new Variables();

    @Override
    public boolean activate() {
        /*
        * If player is not in safespot, while npc is near & item isnt near, return true
        */
        return !Players.getMyPlayer().getLocation().equals(vars.getSafeSpot()) && Monsters.flameNpc() != null && GrndItems.flameItems() == null;
    }

    @Override
    public void execute() {
        vars.setState("Getting tile...");
        Logger.addMessage("Safespot tile?: " + vars.getSafeSpot().getLocation() + "players tile: " + Players.getMyPlayer().getLocation());
        /*
        * walk to safespot, sleep until tiles match safespot
        */
        Walking.walkTo(vars.getSafeSpot());
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Players.getMyPlayer().getLocation().equals(vars.getSafeSpot());
            }
        }, 5000);
    }
}

