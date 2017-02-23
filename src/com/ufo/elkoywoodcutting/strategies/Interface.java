package com.ufo.elkoywoodcutting.strategies;

import com.ufo.elkoywoodcutting.utils.Variables;
import org.parabot.core.Context;
import org.parabot.core.ui.Logger;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;

/**
 * Created by CameronCC on 1/29/17.
 */
public class Interface implements Strategy {
    @Override
    public boolean activate() {
        return Variables.noAxeInterfaceOpened() && Game.isLoggedIn();
    }

    @Override
    public void execute() {
        Context.getInstance().getRunningScript().setState(Script.STATE_STOPPED);
        Logger.addMessage("No axe equipped nor axe in inventory, script ending.");
    }
}
