package com.ufo.voldemortfarmer.tasks;

import org.parabot.core.Context;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Items;
import org.rev317.min.api.methods.Menu;
import com.ufo.voldemortfarmer.data.GUI;
import com.ufo.voldemortfarmer.data.Npcss;
import com.ufo.voldemortfarmer.data.Objects;
import com.ufo.voldemortfarmer.data.Variables;

import java.util.HashMap;

/**
 * Created by UFO on 1/10/17.
 */
public class Banking implements Strategy {
    /*
    * Withdrawing alternative
    */
    private static HashMap<String, Integer> settings = Context.getInstance().getServerProviderInfo().getSettings();

    @Override
    public boolean activate() {
        /*
        * If either my players inventory is full, bank is near and voldemort is not near, return true
        * Or if inventory contains no restore pots, and bank is near, return true
        */
        return Inventory.isFull() && Objects.bankID() != null && Npcss.voldemort() == null
                || !Inventory.contains(Variables.RESTORE_POTS) && Objects.bankID() != null && Npcss.voldemort() == null;
    }

    @Override
    public void execute() {
        Variables vars = new Variables();
        vars.setState("Banking...");

        /*
        * If bank isnt open, open
        */
        if (!Bank.isOpen()) {
            Menu.sendAction(502, 1266389427, 51, 35);

            /*
            * Sleep until bank is open
            */
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Bank.isOpen();
                }
            }, 2500);

            /*
            * If bank is open, and bank contains restores
            * withdraw x amount from input on GUI
            */
        } else {
            for (int pots : Variables.RESTORE_POTS)
                if (Bank.getItem(pots) != null) {
                    Menu.sendAction(646, 13642, 0, 22012);//bank all
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Inventory.isEmpty();
                        }
                    }, 2500);
                    //Bank.withdraw(3025, GUI.numRestores, 800);
                    Bank.getItem(3025).transform(Items.Option.TRANSFORM_X, ((Integer) settings.get("item_interface_id")).intValue());
                    Time.sleep(3500);
                    Keyboard.getInstance().sendKeys("" + GUI.numRestores);
                    Time.sleep(1000);

                    /*
                    * Sleep until Inventory has restores
                    * close bank
                    */
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Inventory.contains(Variables.RESTORE_POTS);
                        }
                    }, 3000);
                    Bank.close();
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return !Bank.isOpen();
                        }
                    }, 2100);
                }
        }
    }
}
