package com.guestline.timersissue;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

@Startup
@Singleton
@Slf4j
public class BigSlowService {
    @Inject
    OffendingActivity dep;
    
    @PostConstruct
    public void init() {
        log.info("slow init..."+dep);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("init interrupted", e);
        }
        log.info("slow ready.");
    }
}
