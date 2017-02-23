package com.ufo.voldemortfarmer.data;

import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

/**
 * Created by UFO.
 */
public class Npcss {
    public static Npc voldemort() {
        for (Npc monster : Npcs.getNearest(1)) {
            if (monster != null) {
                return monster;
            }
        }
        return null;
    }
}
