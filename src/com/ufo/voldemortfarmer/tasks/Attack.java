package com.ufo.voldemortfarmer.tasks;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import com.ufo.voldemortfarmer.data.*;

/**
 * Created by UFO on 1/10/17.
 */
public class Attack implements Strategy {

    @Override
    public boolean activate() {
        /*
        * checking for restores in inventory, making sure the inventory isnt full, and my player is in the safespot
        * will check for if items are not around, and prayer level is higher than digits input from GUI, return true
        */
        return !Inventory.isFull() && Npcss.voldemort() != null
                && Players.getMyPlayer().getLocation().equals(Variables.PLAYER_SS)
                && GrndItems.lootingItems() == null && Variables.getPrayerLevel() > GUI.restoreDrinkPerc;
    }

    @Override
    public void execute() {
        Variables vars = new Variables();
        vars.setState("Attacking voldemort...");
            /*
            * Alternative to prayers
            * If prayer isn't enabled, enable
            * Dying resets prayer and will return false again
            */
        if (!vars.isPrayerEnabled()) {
            Methods.turnOnSS();
            vars.setPrayerEnabled(true);
        }

            /*
            * Another null check so it doesn't return null
            * if voldemort is around and prayer is enabled attack
            */
        if (Npcss.voldemort() != null && vars.isPrayerEnabled()) {
            Npcss.voldemort().interact(Npcs.Option.ATTACK);
        }
            /*
            * Once attack, sleep until voldemort is not in combat, or either dead
            */
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Npcss.voldemort() == null || Variables.getPrayerLevel() <= GUI.restoreDrinkPerc;
            }
        }, 60000);
    }
}
