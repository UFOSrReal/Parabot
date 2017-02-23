package com.ufo.altarrun.data;

import org.parabot.environment.api.utils.Timer;

import java.text.NumberFormat;

/**
 * Created by UFO.
 */
public class Variables {
    private static int used;
    private final Timer time = new Timer();
    private static String status = "Getting data...";

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPerHour(long arg) {
        return NumberFormat.getIntegerInstance().format(//credits to creator
                arg * 3600000D / time.getElapsedTime());
    }
}
