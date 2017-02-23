package com.ufo.flamepernixfarmer.tasks;

import com.ufo.flamepernixfarmer.utils.Methods;
import com.ufo.flamepernixfarmer.utils.Monsters;
import com.ufo.flamepernixfarmer.utils.Variables;
import org.parabot.environment.scripts.framework.Strategy;

/**
 * Created by UFO
 */
public class Teleport implements Strategy {
    Variables vars = new Variables();

    @Override
    public boolean activate() {
        /*
        * If npc is not near, must not be in area, return true
        */
        return Monsters.flameNpc() == null;
    }

    @Override
    public void execute() {
        /*
        * Teleport to Flame pernix area
        */
        vars.setState("Teleporting...");
        Methods.teleToFlameKing();
    }
}
