package com.nuanshui.frms.test.controller.example;





import com.nuanshui.frms.test.apiservice.example.ExampleApiService;
import com.nuanshui.frms.test.appservice.PayOrderService;
import com.nuanshui.frms.test.asyncService.AsyncExampleService;
import com.nuanshui.frms.test.csservice.RepaymentOrderService;

import com.nuanshui.frms.test.entity.Example;
import com.nuanshui.frms.test.entity.app.PayOrder;
import com.nuanshui.frms.test.entity.cs.RepaymentOrder;
import com.nuanshui.frms.test.service.example.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/example")
@Slf4j
public class ExampleController implements ExampleApiService {

    @Autowired
    private ExampleService exampleService;

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private RepaymentOrderService repaymentOrderService;

    @Autowired
    private AsyncExampleService asyncExampleService;

    @RequestMapping(method = RequestMethod.POST, value = "/job/sleep")
    public String sleep(){
        log.info("--sleep-- invoke--");
        return "1";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/job/sleep5")
    public String sleep5(){
        Random random = new Random();
        int s = random.nextInt(5) + 1;
        log.info("##sleep5-- invoke--{}",s);
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException e) {
            log.error("",e);
        }
        return "1";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/job/sleepAsync")
    public String sleep10(){
        log.info("====sleep10-- invoke--");
        asyncExampleService.testAsync();
        return "1";
    }


    @Override
    public long save(@RequestBody Example entity) {
        PayOrder payOrder = new PayOrder();
        payOrder.setUserId(12345L);
        payOrder.setOrderNo("1234");
        payOrderService.selectByUserIdForUnclearedOrder(payOrder);

        RepaymentOrder repaymentOrder = new RepaymentOrder();
        repaymentOrder.setLotNum("12323");
        repaymentOrderService.queryExpRepaymentOrder(repaymentOrder);

        return exampleService.save(entity);
    }

}
