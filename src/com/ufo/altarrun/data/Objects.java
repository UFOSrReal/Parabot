package com.ufo.altarrun.data;

import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

/**
 * Created by UFO.
 */
public class Objects {
    public static SceneObject altarID() {//see
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
