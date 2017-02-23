package com.ufo.elkoywoodcutting.utils;

public enum Trees {

    NORMAL(new int[]{1276, 1277}),
    OAK(new int[]{1281}),
    WILLOW(new int[]{5551}),
    MAPLE(new int[]{1307}),
    YEW(new int[]{1309}),
    MAGIC(new int[]{1306});

    private final int[] treeID;

    public int[] getTreeID() {
        return treeID;
    }

    Trees(int[] treeIDs) {
        this.treeID = treeIDs;
    }
}