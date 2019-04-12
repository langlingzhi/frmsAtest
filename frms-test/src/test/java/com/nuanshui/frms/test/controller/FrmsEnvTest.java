package com.nuanshui.frms.test.controller;
import com.nuanshui.frms.test.TestApplication;
import com.nuanshui.frms.test.api.listeners.AutoTestListener;
import com.nuanshui.frms.test.api.listeners.RetryListener;
import com.nuanshui.frms.test.command.example.FrmsEnvCmd;
import com.nuanshui.frms.test.controller.frmsapi.FrmsEnvController;
import com.nuanshui.frms.test.csservice.FrmsEnvService;
import com.nuanshui.frms.test.entity.cs.FrmsEnv;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;



@Slf4j
@Listeners({AutoTestListener.class, RetryListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(TestApplication.class)
public class FrmsEnvTest extends AbstractTestNGSpringContextTests {

    String id;
    @Autowired
    private FrmsEnvController frmsEnvController;

    @Autowired
    private FrmsEnvService frmsEnvService;


    @Test
    public void toaddFrmsEnvTest() {
        FrmsEnv frmsEnv = new FrmsEnv();
        frmsEnv.setProductname("催收");
        frmsEnv.setEnvpro("http");
        frmsEnv.setEnvtest("http://frmsuat.nuanshui.net/fqh/");
        frmsEnv.setProtocol("http");
        frmsEnv.setStatus("1");
        frmsEnvController.addFrmsEnv(frmsEnv);
    }

    @Test()
    public void insertfrmsenvTest() {
        FrmsEnvCmd frmsEnvCmd = new FrmsEnvCmd();
        frmsEnvCmd.setProductname("催收");
        frmsEnvController.qryFrmsEnvList(frmsEnvCmd);


    }

    @Test()
    public void frmsEnvModifyTest() {
        FrmsEnv frmsEnv = new FrmsEnv();
        frmsEnv.setProductname("催收");
        frmsEnv.setEnvpro("http1");
        frmsEnv.setEnvtest("http://frmsuat.nuanshui.net/fqh/");
        frmsEnv.setProtocol("https");
        frmsEnv.setStatus("0");
        frmsEnvController.frmsEnvModify(frmsEnv);
    }

//    @Test()
//    public void delFrmsEnvTest() {
//        frmsEnvController.delFrmsEnv(id);
//        id="";
//    }
}

