package com.ufo.elkoywoodcutting.strategies;

import com.ufo.elkoywoodcutting.utils.*;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;

/**
 * Created by UFO on 1/22/17.
 */
public class Teleport implements Strategy {
    Variables vars = new Variables();

    @Override
    public boolean activate() {
        if (Inventory.isFull() && !vars.isPowerActive() && Game.isLoggedIn()) {
            if (Objects.bank() == null && Npcss.bankers() == null) {
                Methods.teleportHome();
            }
        }

        if (!Inventory.isFull() && Constants.HOME_AREA.contains(Players.getMyPlayer().getLocation()) && Game.isLoggedIn()) {
            if (Objects.tree() == null || Objects.tree() != null && Constants.HOME_AREA.contains(Objects.tree().getLocation())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        vars.setState("Teleporting...");
        Methods.teleportTo(vars.getCity());
    }
}
