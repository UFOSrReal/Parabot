package com.ufo.flamepernixfarmer.strategies;

import com.ufo.flamepernixfarmer.utils.GrndItems;
import com.ufo.flamepernixfarmer.utils.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.GroundItems;

/**
 * Created by UFO
 */
public class Loot implements Strategy {
    private Variables vars;

    @Override
    public boolean activate() {
        /*
        * If loot is near, return true
        */
        return GrndItems.flameItems() != null;
    }

    @Override
    public void execute() {
        vars = new Variables();
        vars.setState("Looting...");
        /*
        * Another null check, click item
        */
        if (GrndItems.flameItems() != null) {
            GrndItems.flameItems().interact(GroundItems.Option.TAKE);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return GrndItems.flameItems() == null;
                }
            }, 2500);
        }
    }
}
