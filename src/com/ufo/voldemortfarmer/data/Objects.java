package com.ufo.voldemortfarmer.data;

import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

/**
 * Created by UFO.
 */
public class Objects {

    public static SceneObject altarID() {
        for (SceneObject bank : SceneObjects.getNearest(409)) {
            if (bank != null) {
                return bank;
            }
        }
        return null;
    }

    public static SceneObject bankID() {
        for (SceneObject bank : SceneObjects.getNearest(11758)) {
            if (bank != null) {
                return bank;
            }
        }
        return null;
    }
}
