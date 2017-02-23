package com.ufo.altarrun.strategies;

import com.ufo.altarrun.data.Objects;
import com.ufo.altarrun.data.Variables;
import com.ufo.altarrun.main.Ui;
import org.parabot.core.Context;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Walking;

/**
 * Created by UFO
 */
public class Banking implements Strategy {

    private Variables vars = new Variables();

    @Override
    public boolean activate() {
        /*
        * If inventory doesn't contain bone id and the bank is close
        * execute
        */
        return !Inventory.contains(Ui.boneID) && Objects.bankID() != null;
    }

    @Override
    public void execute() {
        /*
        * gets state for paint
        */
        vars.setStatus("Banking...");

        /*
        * If Bank isn't open, click to open the bank.
        */
        if (!Bank.isOpen()) {
            Walking.walkTo(Objects.bankID().getLocation());
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Objects.bankID().getLocation().distanceTo() < 5;
                }
            }, 10000);
            Objects.bankID().interact(SceneObjects.Option.OPEN);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Bank.isOpen();
                }
            }, 5000);
        }

        /*
        * If the bank is already open and doesn't contain any bones in inventory then
        * withdraw bones.
        */
        if (Bank.isOpen() && !Inventory.contains(Ui.boneID) && Bank.getItem(Ui.boneID) != null) {
            Bank.withdraw(Ui.boneID, 50, 800);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.isFull();
                }
            }, 7000);
         /*
         *If bank has no more bones, end script
         */
        } else {
            Logger.addMessage("the bone cant be found in bank");
            Time.sleep(3000);
            Context.getInstance().getRunningScript().setState(Script.STATE_STOPPED);
        }
    }
}
