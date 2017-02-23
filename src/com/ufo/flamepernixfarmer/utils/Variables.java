package com.ufo.flamepernixfarmer.utils;

import com.ufo.flamepernixfarmer.main.Ui;
import org.parabot.environment.api.utils.Timer;
import org.rev317.min.api.wrappers.Tile;

import java.text.NumberFormat;

/**
 * Created by UFO.
 */
public class Variables {
    private static int count, chestDrops, keyDrops, immortalDrops;
    private Tile safeSpot = new Tile(Ui.playersXTile, Ui.playersYTile);
    private static String state = "Getting data...";
    private Timer time = new Timer();

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Tile getSafeSpot() {
        return safeSpot;
    }

    public int getChestDrops() {
        return chestDrops;
    }

    public void setChestDrops(int chestDrops) {
        this.chestDrops = chestDrops;
    }

    public int getKeyDrops() {
        return keyDrops;
    }

    public void setKeyDrops(int keyDrops) {
        this.keyDrops = keyDrops;
    }

    public int getImmortalDrops() {
        return immortalDrops;
    }

    public void setImmortalDrops(int immortalDrops) {
        this.immortalDrops = immortalDrops;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPerHour(long arg) {
        return NumberFormat.getIntegerInstance().format(//credits to creator
                arg * 3600000D / time.getElapsedTime());
    }
}
