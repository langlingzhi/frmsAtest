package com.nuanshui.frms.test.asyncService.impl;

import com.nuanshui.frms.test.asyncService.AsyncExampleService;
import com.nuanshui.frms.test.config.AsyncConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AsyncExampleServiceImpl implements AsyncExampleService {


    public void testAsync(){
        if(AsyncConfig.TEST_ASYNC_COUNT>0){
            log.warn("====count: {} 未执行,testAsync---",AsyncConfig.TEST_ASYNC_COUNT);
            return;
        }
        AsyncConfig.TEST_ASYNC_COUNT ++;
        Random random = new Random();
        int s = random.nextInt(10) + 1;
        log.info("====count: {} 执行,testAsync---{}",AsyncConfig.TEST_ASYNC_COUNT,s);
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException e) {
            log.error("",e);
        }finally {
            AsyncConfig.TEST_ASYNC_COUNT --;
            log.info("====count: {} 执行over,testAsync---",AsyncConfig.TEST_ASYNC_COUNT);
        }
    }
}
