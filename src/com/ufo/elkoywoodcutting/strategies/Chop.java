package com.ufo.elkoywoodcutting.strategies;

import com.ufo.elkoywoodcutting.utils.Constants;
import com.ufo.elkoywoodcutting.utils.Objects;
import com.ufo.elkoywoodcutting.utils.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;

/**
 * Created by UFO on 1/22/17.
 */
public class Chop implements Strategy {
    Variables vars = new Variables();

    @Override
    public boolean activate() {
        return Objects.tree() != null && !Inventory.isFull()
                && !Constants.HOME_AREA.contains(Players.getMyPlayer().getLocation()) && !Variables.noAxeInterfaceOpened() && Game.isLoggedIn()
                && vars.getTree().length > 0;
    }

    @Override
    public void execute() {
        vars.setState("Chopping...");
        if (Objects.tree() != null && Objects.tree().distanceTo() < 20) {
            if (vars.getTree().length > 0) {
                Objects.tree().interact(SceneObjects.Option.CHOP_DOWN);
                Time.sleep(2000);//extra sleep for anti-spam
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Players.getMyPlayer().getAnimation() == -1 || !Game.isLoggedIn();
                    }
                }, 15000);
            }
        }
    }
}
