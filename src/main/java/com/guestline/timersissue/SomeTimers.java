package com.guestline.timersissue;

import java.time.LocalTime;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import lombok.extern.slf4j.Slf4j;

@Startup
@Singleton
@Slf4j
public class SomeTimers {
    HashMap<String, LocalTime> lastFire = new HashMap<String, LocalTime>();
    @Resource
    private TimerService timerService;

    @PostConstruct
    public void init() {
        log.info("Scheduling timeout...");

        TimerConfig config = new TimerConfig();
        config.setPersistent(false);
        timerService.createIntervalTimer(10, 10000, config);
    }

    @Schedule(hour = "*", minute = "*", second = "*", persistent = false)
    public void runTimer0() {
        lastFire.put("timer#0", LocalTime.now());
    }

    @Schedule(hour = "*", minute = "*", second = "*", persistent = false)
    public void runTimer() {
        lastFire.put("timer#1", LocalTime.now());
    }

    @Schedule(hour = "*", minute = "*", second = "*", persistent = true)
    public void runTimer2() {
        lastFire.put("timer#2", LocalTime.now());
    }

    @Schedule(hour = "*", minute = "*", second = "*", persistent = false)
    public void runTimer3() {
        lastFire.put("timer#3", LocalTime.now());
    }

    @Schedule(hour = "*", minute = "*", second = "*", persistent = false)
    public void runTimer4() {
        lastFire.put("timer#4", LocalTime.now());
    }

    @Schedule(hour = "*", minute = "*", second = "*", persistent = false)
    public void runTimer5() {
        lastFire.put("timer#5", LocalTime.now());
    }
    
    @Timeout
    public void runTimeout() {
        lastFire.put("timeout", LocalTime.now());
        log.info("timers {} lastFire: {}", (allFired()?"OK":"FAIL"), lastFire);
    }
    
    public boolean allFired() {
        return lastFire.size() == 7;
    }
}
