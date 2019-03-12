package com.nuanshui.frms.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig extends AsyncConfigurerSupport {


    public static int TEST_ASYNC_COUNT = 0;

    @Value("${asyncthread.corePoolSize}")
    private int corePoolSize;

    @Value("${asyncthread.maxPoolSize}")
    private int maxPoolSize;

    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(corePoolSize);
        threadPoolExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolExecutor.initialize();
        return threadPoolExecutor;
    }

}