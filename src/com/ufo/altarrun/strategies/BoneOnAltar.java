package com.ufo.altarrun.strategies;

import com.ufo.altarrun.data.Objects;
import com.ufo.altarrun.data.Variables;
import com.ufo.altarrun.main.Ui;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;

/**
 * Created by UFO
 */
public class BoneOnAltar implements Strategy {
    private Variables vars = new Variables();

    @Override
    public boolean activate() {
        /*
        * When the inventory has bone id input
        * & the altar is 4 tiles or closer
        * execute
        */
        return Inventory.contains(Ui.boneID) && Objects.altarID().getLocation().distanceTo() < 5;//checks if inventory has bone, and altar is close
    }

    @Override
    public void execute() {
        /*
        * gets state for paint
        */
        vars.setStatus("Using bones on altar...");

        /*
        * Use bone on altar, types 28 when interface shows.
        */
        Menu.sendAction(447, Ui.boneID - 1, 0, 3214);//use bone
        Time.sleep(600);

        /*
        * Both actions are for using the bone on altar. The actions switch
        * so it uses both action for it to work.
        */
        Menu.sendAction(62, 1080449603, 67, 52);
        Menu.sendAction(62, 1080449587, 51, 52);
        Menu.sendAction(62, 1080451651, 67, 68);//test
        Time.sleep(800);

        /*
        * Gets keyboard to send the number of bones to use on altar
        */
        Keyboard.getInstance().sendKeys("28");

        /*
        * Sleep till inventory no longer has bones, will timeout after 35 seconds and trys again.
        */
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return !Inventory.contains(Ui.boneID);
            }
        },35000);
    }
}
