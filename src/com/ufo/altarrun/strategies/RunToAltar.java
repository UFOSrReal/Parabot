package com.ufo.altarrun.strategies;

import com.ufo.altarrun.data.Objects;
import com.ufo.altarrun.data.Variables;
import com.ufo.altarrun.main.Ui;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Walking;

/**
 * Created by UFO
 */
public class RunToAltar implements Strategy {
    private Variables vars = new Variables();

    @Override
    public boolean activate() {
        /*
        * If inventory does contain id & altar id is more than 5 tiles away
        * execute
        */
        return Inventory.contains(Ui.boneID) && Objects.altarID().getLocation().distanceTo() > 5;
    }

    @Override
    public void execute() {
        /*
        * gets state for paint
        */
        vars.setStatus("Going to altar...");

        /*
        * if altar isnt null and is more than 5 tiles away
        * walk to altar and sleep until its 4 tiles or closer.
        */
        if (Objects.altarID() != null && Objects.altarID().getLocation().distanceTo() > 5) {
            Walking.walkTo(Objects.altarID().getLocation());
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Objects.altarID().getLocation().distanceTo() < 5;
                }
            }, 9000);
        }
    }
}
