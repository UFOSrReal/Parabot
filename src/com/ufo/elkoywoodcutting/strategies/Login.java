package com.ufo.elkoywoodcutting.strategies;

import com.ufo.elkoywoodcutting.utils.Objects;
import com.ufo.elkoywoodcutting.utils.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;

/**
 * Created by UFO.
 */
public class Login implements Strategy {
    Variables vars = new Variables();

    @Override
    public boolean activate() {
        return !Game.isLoggedIn();
    }

    @Override
    public void execute() {
        vars.setState("Logging in...");
        System.out.print("Mouse at " + Mouse.getInstance().getPoint());
        Time.sleep(500);
        Mouse.getInstance().click(400, 450, true);
        Time.sleep(1000);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Game.isLoggedIn() && Objects.tree() != null;
            }
        }, 5700);
    }
}
