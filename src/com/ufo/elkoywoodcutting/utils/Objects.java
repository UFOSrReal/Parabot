package com.ufo.elkoywoodcutting.utils;

import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

/**
 * Created by UFO.
 */
public class Objects {

    public static SceneObject tree() {
        Variables vars = new Variables();
        for (SceneObject tree : SceneObjects.getNearest(vars.getTree())) {
            if (tree != null) {
                return tree;
            }
        }
        return null;
    }

    public static SceneObject bank() {
        for (SceneObject bank : SceneObjects.getNearest(2213)) {
            if (bank != null) {
                return bank;
            }
        }
        return null;
    }
}
