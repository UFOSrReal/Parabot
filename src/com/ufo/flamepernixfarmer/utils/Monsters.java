package com.ufo.flamepernixfarmer.utils;

import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

/**
 * Created by UFO.
 */
public class Monsters {
    public static Npc flameNpc() {
        for (Npc monster : Npcs.getNearest(10001)) {
            if (monster != null) {
                return monster;
            }
        }
        return null;
    }
}
