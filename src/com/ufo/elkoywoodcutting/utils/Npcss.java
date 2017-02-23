package com.ufo.elkoywoodcutting.utils;

import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

/**
 * Created by UFO.
 */
public class Npcss {

    public static Npc bankers() {
        for (Npc bank : Npcs.getNearest(Constants.BANKS)) {
            if (bank != null) {
                return bank;
            }
        }
        return null;
    }
}
