package com.nuanshui.frms.test.testcase;

import com.nuanshui.frms.test.TestApplication;
//import com.nuanshui.frms.test.controller.example.FrmsapiController;
import com.nuanshui.frms.test.csservice.FrmsapiService;
import com.nuanshui.frms.test.entity.cs.Frmsapi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(TestApplication.class)
public class FrmsapiTest {

//    @Autowired
//    private FrmsapiController frmsapiController;
    @Autowired
    private FrmsapiService frmsapiService;



   @Test
    public void selectByPrimaryKeyTest() throws IOException {
        Frmsapi frmsapi=new Frmsapi();
//        frmsapi.setId();
//        frmsapi.setResponse("llz");
//        frmsapi.setMethod("post");
//        frmsapi.setParam("dddddd");
//        frmsapi.setPath("11");
//        frmsapi.setProductid(11);
//        frmsapi.setStatus("1");
//        frmsapiService.getfrmsapi(frmsapi);
//       frmsapiController.selectfrmsapi();


    }

    @Test
    public void  gert(){
        System.out.println("========");
    }
   /* public static void main(String args[]){
        Frmsapi frmsapi=new Frmsapi();
        frmsapi.setId((long) 5);
        frmsapi.setDescription("llz");
        frmsapi.setMethod("post");
        frmsapi.setParam("dddddd");
        frmsapi.setUrl("11");
        frmsapi.setVerify("ssss");
        frmsapi.setStatus("1");
//        frmsapiService.getfrmsapi(frmsapi);
    }*/
}
