package com.nuanshui.frms.test.asyncService;

import org.springframework.scheduling.annotation.Async;

public interface AsyncExampleService {


    @Async
    void testAsync();

}
