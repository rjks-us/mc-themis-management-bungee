package us.rjks.module;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.concurrent.TimeUnit;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 04.06.2021 / 18:25
 *
 **************************************************************************/

public class BungeeTimer {

    private ScheduledTask timer;
    private TimeUnit timeUnit;
    private long numb1;
    private boolean running = false;

    private Plugin instance;

    public BungeeTimer(Plugin instance, long numb1, TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        this.numb1 = numb1;
        this.instance = instance;
    }

    public void execute() {

    }

    public void start() {
        if (!running) {
            running = true;
            timer = instance.getProxy().getScheduler().schedule(instance, () -> {
                execute();
            }, this.numb1, this.timeUnit);
        }
    }

    public void stop() {
        if (running) {
            instance.getProxy().getScheduler().cancel(timer);
            running = false;
        }
    }

    public boolean isRunning() {
        return running;
    }
}
