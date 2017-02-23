package com.ufo.voldemortfarmer.data;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.rev317.min.api.methods.Menu;

/**
 * Created by UFO.
 */
public class Methods {
    public static void teleToVoldemort() {
        Keyboard.getInstance().sendKeys("::joinraid1");
        Time.sleep(4500);

    }

    public static void turnOnSS() {
        Variables vars = new Variables();
        Menu.sendAction(1016, 5021, 3, 3214);
        Time.sleep(700);
        Menu.sendAction(169, 5021, 3, 22539);
        Time.sleep(700);
        Menu.sendAction(1014, 5021, 3, 22507);
        Time.sleep(1000);
        vars.setPrayerEnabled(true);

    }
}
