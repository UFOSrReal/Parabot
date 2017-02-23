package com.ufo.elkoywoodcutting.strategies;

import com.ufo.elkoywoodcutting.utils.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;

/**
 * Created by UFO on 1/29/17.
 */
public class Drop implements Strategy {
    Variables vars = new Variables();

    @Override
    public boolean activate() {
        return Inventory.isFull() && vars.isPowerActive() && Game.isLoggedIn();
    }

    @Override
    public void execute() {
        vars.setState("Clearing inventory...");
        Keyboard.getInstance().sendKeys("::empty");
        Time.sleep(400);//stops spam
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Inventory.isEmpty();
            }
        }, 3500);
    }
}
