package com.ufo.flamepernixfarmer.utils;

import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.wrappers.GroundItem;

/**
 * Created by UFO.
 */
public class GrndItems {
    public static GroundItem flameItems() {
        for (GroundItem itemID : GroundItems.getNearest(6199, 3881, 3883, 3885, 19903, 20590, 20591, 20592)) {
            if (itemID != null) {
                return itemID;
            }
        }
        return null;
    }
}
