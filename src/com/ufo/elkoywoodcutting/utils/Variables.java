package com.ufo.elkoywoodcutting.utils;

import org.parabot.environment.api.utils.Timer;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Players;

import java.text.NumberFormat;

/**
 * Created by UFO on 1/22/17.
 */
public class Variables {
    private static int[] tree;
    private static int city;
    private int logs;
    private Timer time = new Timer();
    private static String state;
    private static boolean powerActive;//powerlevel
    private static boolean dialogueOpen;

    public static boolean bankOpened() {//temp
        return Interfaces.isOpen(5063) || Interfaces.isOpen(5292);
    }

    public static boolean isTeleporting() {//8941
        return Players.getMyPlayer().getAnimation() == 8941;
    }

    public static boolean noAxeInterfaceOpened() {
        return Interfaces.getBackDialogId() == 356;
    }

    public String getPerHour(long arg) {
        return NumberFormat.getIntegerInstance().format(//credits to creator
                arg * 3600000D / time.getElapsedTime());
    }

    public void setLogs(int logs) {
        this.logs = logs;
    }

    public int getLogs() {
        return logs;
    }

    public void setTree(int[] tree) {
        this.tree = tree;
    }

    public int[] getTree() {
        return tree;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setPowerLevel(boolean powerActive) {
        this.powerActive = powerActive;
    }

    public boolean isPowerActive() {
        return powerActive;
    }

    public void setDialogueOpen(boolean dialogueOpen) {
        this.dialogueOpen = dialogueOpen;
    }

    public boolean isDialogueOpen() {
        return dialogueOpen;
    }
}
