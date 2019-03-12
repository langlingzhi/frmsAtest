package com.nuanshui.frms.test.apiservice.example;

import com.nuanshui.frms.test.entity.Example;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * example service
 *
 */
public interface ExampleApiService {

    @RequestMapping(method = RequestMethod.POST, value = "/job/sleep")
    String sleep();


    @RequestMapping(method = RequestMethod.POST, value = "/job/sleep5")
    String sleep5();


    @RequestMapping(method = RequestMethod.POST, value = "/job/sleepAsync")
    String sleep10();

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    long save(@RequestBody Example entity);
}
