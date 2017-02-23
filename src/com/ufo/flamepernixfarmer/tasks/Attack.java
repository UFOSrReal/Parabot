package com.ufo.flamepernixfarmer.tasks;

import com.ufo.flamepernixfarmer.utils.GrndItems;
import com.ufo.flamepernixfarmer.utils.Monsters;
import com.ufo.flamepernixfarmer.utils.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;

/**
 * Created by UFO
 */
public class Attack implements Strategy {
    Variables vars = new Variables();

    @Override
    public boolean activate() {
        /*
        * If npc is not null and both player and npc is not in combat
        * while im in safespot, dont attack npc too far than 8 tiles, return true
        */
        return Monsters.flameNpc() != null && !Monsters.flameNpc().isInCombat() && !Players.getMyPlayer().isInCombat()
                && Players.getMyPlayer().getLocation().equals(vars.getSafeSpot())
                && Monsters.flameNpc().getLocation().distanceTo() < 8 && GrndItems.flameItems() == null;
    }

    @Override
    public void execute() {
        /*
        * Another check, so doesn't return null
        * Attack npc and wait until the npc is out of combat
        */
        vars.setState("Attacking NPC...");
        if (Monsters.flameNpc() != null) {
            Monsters.flameNpc().interact(Npcs.Option.ATTACK);
            Time.sleep(1400);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Monsters.flameNpc() != null && !Monsters.flameNpc().isInCombat() || Monsters.flameNpc() == null;
                }
            }, 2500);
        }
    }
}

