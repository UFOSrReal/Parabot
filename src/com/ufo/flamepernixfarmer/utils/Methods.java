package com.ufo.flamepernixfarmer.utils;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.rev317.min.api.methods.Menu;

/**
 * Created by UFO.
 */
public class Methods {
    public static void teleToFlameKing() {
        Keyboard.getInstance().sendKeys("::flameking");
        Time.sleep(800);
        Menu.sendAction(315, 192643072, 244, 2472);
        Time.sleep(4500);

    }
}
