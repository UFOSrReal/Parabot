package com.ufo.voldemortfarmer.data;

import org.parabot.environment.api.utils.Timer;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.wrappers.Tile;

import java.text.NumberFormat;

/**
 * Created by CameronCC on 1/10/17.
 */
public class Variables {

    public static final Tile PLAYER_SS = new Tile(2364, 9911);
    public static final int[] RESTORE_POTS = {3031, 3029, 3027, 3025};

    public static int getPrayerLevel() {
        return Integer.parseInt(Interfaces.getInterface(4012).getMessage());
    }

    private static String state = "Getting data...";
    private final Timer time = new Timer();
    private static int kills;
    private static boolean prayerEnabled;

    public String getPerHour(long arg) {
        return NumberFormat.getIntegerInstance().format(//credits to creator
                arg * 3600000D / time.getElapsedTime());
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isPrayerEnabled() {
        return prayerEnabled;
    }

    public void setPrayerEnabled(boolean prayerEnabled) {
        this.prayerEnabled = prayerEnabled;
    }
}
