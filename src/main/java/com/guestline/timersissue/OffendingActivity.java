package com.guestline.timersissue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.ApplicationScoped;

import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class OffendingActivity {
    @Resource
    private ManagedScheduledExecutorService executorService;
    
    @PostConstruct
    public void init(){
        log.info("init");
        executorService.execute(this::infiniteTaskLoop);
    }
    
    public void infiniteTaskLoop() {
        executorService.execute(this::infiniteTaskLoop);
    }
}
