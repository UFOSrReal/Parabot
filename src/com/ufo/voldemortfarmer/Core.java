package com.ufo.voldemortfarmer;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import com.ufo.voldemortfarmer.data.GUI;
import com.ufo.voldemortfarmer.data.Variables;
import com.ufo.voldemortfarmer.tasks.*;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * User: UFO credit to Fryslan
 */
@ScriptManifest(
        author = "UFO",
        name = "UFOS Voldemort Farmer",
        category = Category.COMBAT,
        version = 1.2,
        description = "Safespots voldemort, and loots.",
        servers = {"DreamScape"})

public class Core extends Script implements MessageListener, Paintable {

    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    private Timer time;//credits to Cyanic for helping
    private final Variables vars = new Variables();

    /*
    * If player died, reset prayer boolean
    */
    public void messageReceived(MessageEvent messageEvent) {
        if (messageEvent.getMessage().equals("Oh dear, you are dead!")) {
            vars.setPrayerEnabled(false);
        }

        /*
        * If message contains specific text
        * add +1 to kills for paint
        */

        if (messageEvent.getMessage().contains("Voldemort has been defeated!")) {
            vars.setKills(vars.getKills() + 1);
        }
    }

    @Override
    public boolean onExecute() {
        time = new Timer();
        /*
        * Displays GUI and title
        */
        GUI window = new GUI();
        window.setTitle("UFOS VoldemortFarmer");
        window.setVisible(true);
        while (window.isVisible()) {
            sleep(20);
        }

        strategies.add(new Attack());
        strategies.add(new Banking());
        strategies.add(new Loot());
        strategies.add(new Drink());
        strategies.add(new HandleTile());
        strategies.add(new HandleTeleport());
        provide(strategies);
        return true;
    }

    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setColor(Color.RED);
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.1F);
        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
        g.setFont(newFont);
        g.drawRect(7, 7, 190, 46);
        g.drawString("Run time: " + time, 10, 20);
        g.drawString("State: " + vars.getState(), 10, 35);
        g.drawString("Kills: " + nf.format(vars.getKills()) + "/" + vars.getPerHour(vars.getKills()), 10, 50);//interface 12140
    }

    @Override
    public void onFinish() {
        Logger.addMessage("Script Stopped");
    }
}