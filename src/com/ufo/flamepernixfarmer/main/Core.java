package com.ufo.flamepernixfarmer.main;

import com.ufo.flamepernixfarmer.strategies.Attack;
import com.ufo.flamepernixfarmer.strategies.HandleTile;
import com.ufo.flamepernixfarmer.strategies.Loot;
import com.ufo.flamepernixfarmer.strategies.Teleport;
import com.ufo.flamepernixfarmer.utils.Variables;
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
 * Created by UFO credits to Fryslan
 */
@ScriptManifest(
        author = "UFO",
        name = "UFOS Flame Pernix Farmer",
        category = Category.COMBAT,
        version = 1.2,
        description = "Kills flame pernix in safespot, loots Flame Pernix armour.",
        servers = {"DreamScape"})

public class Core extends Script implements Paintable, MessageListener {

    private ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    Timer time = new Timer();//credits to Cyanic for helping
    private Variables vars = new Variables();
    private int startDefExp, startRangeExp, startHpExp;

    @Override
    public boolean onExecute() {
        startDefExp = Skill.DEFENSE.getExperience();
        startRangeExp = Skill.RANGE.getExperience();
        startHpExp = Skill.HITPOINTS.getExperience();
        /*
        * Displays GUI and title
        */
        Ui window = new Ui();
        window.setTitle("Flame Pernix Farmer");
        window.setVisible(true);
        while (window.isVisible()) {
            sleep(20);
        }

        strategies.add(new Attack());
        strategies.add(new HandleTile());
        strategies.add(new Loot());
        strategies.add(new Teleport());
        provide(strategies);
        return true;
    }

    @Override
    public void onFinish() {
        System.out.println("Script Stopped");
    }

    @Override
    public void paint(Graphics g1) {
        int getDefExp = Skill.DEFENSE.getExperience();
        int getRangeExp = Skill.RANGE.getExperience();
        int getHpExp = Skill.HITPOINTS.getExperience();
        int defExpGained = getDefExp - startDefExp;
        int rangeExpGained = getRangeExp - startRangeExp;
        int hPExpGained = getHpExp - startHpExp;
        int allExpGained = defExpGained + rangeExpGained + hPExpGained;
        Graphics2D g = (Graphics2D) g1;
        g.setColor(Color.BLUE);
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.1F);
        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
        g.setFont(newFont);
        g.drawRect(7, 7, 190, 106);
        g.drawString("Run time: " + time, 10, 20);
        g.drawString("State: " + vars.getState(), 10, 35);
        g.drawString("Kills/hr: " + nf.format(vars.getCount()) + "/" + vars.getPerHour(vars.getCount()), 10, 50);
        g.drawString("Chest drops/hour: " + nf.format(vars.getChestDrops()) + "/" + vars.getPerHour(vars.getChestDrops()), 10, 65);
        g.drawString("Keys drops/hour: " + nf.format(vars.getKeyDrops()) + "/" + vars.getPerHour(vars.getKeyDrops()), 10, 80);
        g.drawString("Immortal(key) drops/hour: " + nf.format(vars.getImmortalDrops()) + "/" + vars.getPerHour(vars.getImmortalDrops()), 10, 95);
        g.drawString("Exp gained/hour: " + nf.format(allExpGained) + "/" + vars.getPerHour(allExpGained), 10, 110);
    }

    public void messageReceived(MessageEvent messageEvent) {
        if (messageEvent.getMessage().contains("Your Flame pernix kill count is")) {
            vars.setCount(vars.getCount() + 1);
        }

        if (messageEvent.getMessage().contains("The chest has been added directly to your bank")) {
            vars.setChestDrops(vars.getChestDrops() + 1);
        }

        if (messageEvent.getMessage().contains("The key fragment has been added directly to your bank")) {
            vars.setKeyDrops(vars.getKeyDrops() + 1);
        }

        if (messageEvent.getMessage().contains("The Immortal stone fragment has been added directly to your bank")) {
            vars.setImmortalDrops(vars.getImmortalDrops() + 1);
        }
    }

}