package com.ufo.altarrun.main;

import com.ufo.altarrun.data.Variables;
import com.ufo.altarrun.strategies.Banking;
import com.ufo.altarrun.strategies.BoneOnAltar;
import com.ufo.altarrun.strategies.RunToAltar;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.methods.Skill;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Script by UFO credits to Fryslan for base.
 */
@ScriptManifest(
        author = "UFO",
        name = "UFOS AltarRun",
        category = Category.PRAYER,
        version = 1.1,
        description = "Prays bones on altar, banks, repeat.",
        servers = {"DreamScape"})

public class Core extends Script implements Paintable, MessageListener {


    /*
    * Variables for paint
    */
    private Timer time;//credits to Cyanic for helping
    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    private final Variables vars = new Variables();
    private int getStartingXp;

    @Override
    public boolean onExecute() {
        Logger.addMessage("Starting UFOS AltarRun...");
        /*
        * start time and get xp
        */
        time = new Timer();
        getStartingXp = Skill.PRAYER.getExperience();
        /*
        * Gets Ui and displays
        */
        Ui window = new Ui();
        window.setTitle("UFOS AltarRun");
        window.setVisible(true);
        while (window.isVisible()) {
            sleep(20);
        }
        /*
        * Gathers tasks
        */
        strategies.add(new Banking());
        strategies.add(new BoneOnAltar());
        strategies.add(new RunToAltar());
        provide(strategies);
        return true;
    }

    @Override
    public void onFinish() {
        Logger.addMessage("Script Stopped");
    }


    /*
    * Displays paint on screen
    */
    @Override
    public void paint(Graphics g1) {
        int getXp = Skill.PRAYER.getExperience();
        int getXpGained = getXp - getStartingXp;
        Graphics2D g = (Graphics2D) g1;
        g.setColor(Color.WHITE);
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.1F);
        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
        g.setFont(newFont);
        g.drawRect(7, 7, 365, 61);
        g.drawString("Run Time: " + time, 10, 20);
        g.drawString("State: " + vars.getStatus(), 10, 35);
        g.drawString("Bones used/hr: " + nf.format(vars.getUsed()) + "/" + vars.getPerHour(vars.getUsed()), 10, 50);
        g.drawString("Exp gained/hr: " + nf.format(getXpGained) + "/" + vars.getPerHour(getXpGained), 10, 65);
    }

    /*
    * If message appears, will add +1 to bones used on paint
    */
    public void messageReceived(MessageEvent messageEvent) {
        if (messageEvent.getMessage().equals("The gods are pleased with your offering.")) {
            vars.setUsed(vars.getUsed() + 1);
        }
    }
}