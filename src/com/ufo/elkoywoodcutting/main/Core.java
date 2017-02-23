package com.ufo.elkoywoodcutting.main;

import com.ufo.elkoywoodcutting.strategies.*;
import com.ufo.elkoywoodcutting.utils.Constants;
import com.ufo.elkoywoodcutting.utils.Variables;
import org.parabot.core.Context;
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
 * User: UFO credit to Fryslan
 */
@ScriptManifest(
        author = "UFO",
        name = "UFOS WoodCutter",
        category = Category.WOODCUTTING,
        version = Constants.VERSION,
        description = "Cuts any tree, banks, powerleveling, easy levels.",
        servers = {"Elkoy, Dreamscape"})

public class Core extends Script implements MessageListener, Paintable {

    private final Variables vars = new Variables();
    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    private Timer time;//credits to Cyanic
    private int startLevel;
    private int startExp;

    public void messageReceived(MessageEvent messageEvent) {
        if (messageEvent.getMessage().contains("You need a woodcutting level")) {
            Context.getInstance().getRunningScript().setState(Script.STATE_STOPPED);
            Logger.addMessage("Don't have correct woodcutting level, stopping script.");
        }

        if (messageEvent.getMessage().contains("You get some") || messageEvent.getMessage().contains("received some")) {
            vars.setLogs(vars.getLogs() + 1);
        }
    }

    @Override
    public boolean onExecute() {
        time = new Timer();
        startLevel = Skill.WOODCUTTING.getLevel();
        startExp = Skill.WOODCUTTING.getExperience();
        /*
        * Displays GUI and title
        */
        Ui window = new Ui();
        window.setTitle("UFOS WoodCutter");
        window.setVisible(true);
        while (window.isVisible()) {
            sleep(20);
        }
        strategies.add(new Teleport());
        strategies.add(new Banking());
        strategies.add(new Chop());
        strategies.add(new Drop());
        strategies.add(new Interface());
        strategies.add(new Login());
        provide(strategies);
        return true;
    }

    public void paint(Graphics g1) {
        int getLevel = Skill.WOODCUTTING.getLevel();
        int getExp = Skill.WOODCUTTING.getExperience();
        int levelsGained = getLevel - startLevel;
        int expGained = getExp - startExp;

        Graphics2D g = (Graphics2D) g1;

        g.setColor(Color.WHITE);
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.1F);
        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
        g.setFont(newFont);
        g.drawRect(7, 7, 285, 91);
        g.drawString("UFOS WoodCutter " + Constants.VERSION, 10, 20);
        g.drawString("Run time: " + time, 10, 35);
        g.drawString("State: " + vars.getState(), 10, 50);
        g.drawString("Levels gained: " + levelsGained, 10, 65);
        g.drawString("Logs cut/hour: " + nf.format(vars.getLogs()) + "/" + vars.getPerHour(vars.getLogs()), 10, 80);
        g.drawString("Exp gained/hour: " + nf.format(expGained) + "/" + vars.getPerHour(expGained), 10, 95);
    }

    @Override
    public void onFinish() {
        Logger.addMessage("Script Stopped");
    }
}