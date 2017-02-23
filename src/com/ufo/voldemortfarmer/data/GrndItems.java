package com.ufo.voldemortfarmer.data;

import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.wrappers.GroundItem;

/**
 * Created by UFO.
 */
public class GrndItems {

    public static GroundItem lootingItems() {
        for (GroundItem itemID : GroundItems.getNearest(3883, 3881, 20137, 20159, 20596, 20597, 20598, 18394, 20237, 20593, 20594, 20595, 20083, 20590, 20591, 20592)) {
            if (itemID != null) {
                return itemID;
            }
        }
        return null;
    }

}
