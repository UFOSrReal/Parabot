package com.ufo.elkoywoodcutting.utils;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.rev317.min.api.methods.Menu;

/**
 * Created by UFO.
 */
public class Methods {
    private static Variables vars = new Variables();

    private static void openDialogue() {
        Menu.sendAction(1107, 0, 0, 0);
        Time.sleep(800);
        Menu.sendAction(315, 201, 0, 1541, 2213, 1);
        Time.sleep(800);
        Menu.sendAction(315, 201, 0, 2494, 2213, 1);
        Time.sleep(800);
        vars.setDialogueOpen(true);
    }

    public static void teleportHome() {
        Keyboard.getInstance().sendKeys("::home");
        Time.sleep(4500);
    }

    public static void teleportTo(int action3) {
        if (!Variables.isTeleporting() && !vars.isDialogueOpen()) {
            openDialogue();
        } else if (vars.isDialogueOpen() && !Variables.isTeleporting()) {
            Menu.sendAction(315, 20938752, 46, action3, 1278, 1);
            Time.sleep(800);
        }
    }
}
