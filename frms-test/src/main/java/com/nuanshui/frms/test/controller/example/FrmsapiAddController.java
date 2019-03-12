//package com.nuanshui.frms.test.controller.example;
//
//import com.nuanshui.frms.test.csservice.FrmsapiService;
//import com.nuanshui.frms.test.entity.cs.Frmsapi;
//import com.nuanshui.frms.test.utils.JacksonUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//
//@Controller
//@RestController
//@RequestMapping("/test")
//public class FrmsapiAddController {
//    @Autowired
//    private FrmsapiService frmsapiService;
//
//    @RequestMapping(value = "/getfrmsapi", method = RequestMethod.POST, produces = {
//            "application/json; charset=utf-8" })
//    @ResponseBody
//    private Long getfrmsapi(@RequestBody String json) throws IOException {
//        Frmsapi frmsapi= JacksonUtils.parseJsonFromString(json,Frmsapi.class);
//
//        Long res=frmsapiService.getfrmsapi(frmsapi);
//
//        System.out.println(res);
//
//        return res;
//
//    }
//}
