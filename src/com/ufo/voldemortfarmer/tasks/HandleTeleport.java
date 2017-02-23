package com.ufo.voldemortfarmer.tasks;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Inventory;
import com.ufo.voldemortfarmer.data.*;

/**
 * Created by UFO on 1/10/17.
 */
public class HandleTeleport implements Strategy {
    @Override
    public boolean activate() {
        /*
        * If interface id 12140 is open, teleport back to voldemort
        * or if player is farther than 25 f
        */
        if (Interfaces.isOpen(12140) && Variables.PLAYER_SS.getLocation().distanceTo() > 25 || Variables.PLAYER_SS.getLocation().distanceTo() > 25) {
            if (Objects.bankID() == null) {
                Methods.teleToVoldemort();
            }
        }

        /*
        * If npc is null, bank is near, inventory is not full and inventory contains retore
        * teleport to voldemort
        */
        if (Npcss.voldemort() == null && Objects.bankID() != null && Inventory.contains(Variables.RESTORE_POTS) && !Inventory.isFull()) {
            Methods.teleToVoldemort();
        }

        /*
        * If voldemort is near, and bank is null
        * If inventory is full or inventory does not have restore and prayer is low, return true
        */
        if (Npcss.voldemort() != null && Objects.bankID() == null) {
            if (Inventory.isFull() || !Inventory.contains(Variables.RESTORE_POTS) && Variables.getPrayerLevel() <= GUI.restoreDrinkPerc) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        Variables vars = new Variables();
        vars.setState("Teleporting...");
        /*
        * Have keyboard enter teleportation to home
        */
        Keyboard.getInstance().sendKeys("::home");
        Time.sleep(4500);
    }
}
