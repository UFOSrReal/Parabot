package com.ufo.voldemortfarmer.tasks;


import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Items;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Item;
import com.ufo.voldemortfarmer.data.GUI;
import com.ufo.voldemortfarmer.data.Npcss;
import com.ufo.voldemortfarmer.data.Variables;

/**
 * Created by UFO on 1/16/17.
 */
public class Drink implements Strategy {
    @Override
    public boolean activate() {
        /*
        * If prayer level is less than or equal to x input from GUI & restore is in inventory, return true
        */
        return Variables.getPrayerLevel() <= GUI.restoreDrinkPerc && Inventory.contains(Variables.RESTORE_POTS);
    }

    @Override
    public void execute() {
        Variables vars = new Variables();
        vars.setState("Drinking...");
        Item[] pots = Inventory.getItems(Variables.RESTORE_POTS);

        /*
        * Extra null checks, and prayer level is less than or equal to x
        * consume restore potion
        */
        if (pots != null && Variables.getPrayerLevel() <= GUI.restoreDrinkPerc) {
            pots[0].interact(Items.Option.CONSUME);
            Time.sleep(1200);
        }

        /*
        * If npc is near, and prayer is about x
        * go back to combat
        */
        if (Npcss.voldemort() != null && Variables.getPrayerLevel() > GUI.restoreDrinkPerc) {
            Npcss.voldemort().interact(Npcs.Option.ATTACK);
            Time.sleep(1200);
        }
    }
}
