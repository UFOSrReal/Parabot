package com.ufo.elkoywoodcutting.strategies;

import com.ufo.elkoywoodcutting.utils.Npcss;
import com.ufo.elkoywoodcutting.utils.Objects;
import com.ufo.elkoywoodcutting.utils.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by UFO on 1/22/17.
 */
public class Banking implements Strategy {
    Variables vars = new Variables();

    @Override
    public boolean activate() {
        if (Inventory.isFull() && !vars.isPowerActive() && Game.isLoggedIn()) {
            if (Objects.bank() != null || Npcss.bankers() != null)//test
                return true;
        }
        return false;
    }

    @Override
    public void execute() {
        vars.setState("Banking...");
        Tile ghost = new Tile(2715, 3463);
        if (!Variables.bankOpened()) {
            if (Npcss.bankers() != null && Objects.bank() != null && Npcss.bankers().distanceTo() < Objects.bank().distanceTo()
                    || Npcss.bankers() != null && Objects.bank() == null) {
                if (Npcss.bankers().getLocation().equals(ghost)) {
                    Npcss.bankers().interact(Npcs.Option.BANK);
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Variables.bankOpened();
                        }
                    }, 2500);
                } else {
                    Npcss.bankers().interact(Npcs.Option.TALK_TO);
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Variables.bankOpened();
                        }
                    }, 2500);
                }
            } else if (Objects.bank() != null && Npcss.bankers() != null && Objects.bank().distanceTo() < Npcss.bankers().distanceTo()
                    || Objects.bank() != null && Npcss.bankers() == null) {
                Objects.bank().interact(SceneObjects.Option.OPEN);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Variables.bankOpened();
                    }
                }, 8000);
            }
        }

        if (Variables.bankOpened()) {
            // Menu.sendAction(646, 1725, 25, 22012);//bank all
            Bank.depositAllExcept(6740);//temp
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.isEmpty();
                }
            }, 3000);
            Bank.close();
        }
    }
}
