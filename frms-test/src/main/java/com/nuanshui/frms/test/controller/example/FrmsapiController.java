//package com.nuanshui.frms.test.controller.example;
//
//import com.nuanshui.frms.test.csservice.FrmsapiService;
//import com.nuanshui.frms.test.csservice.MessageListService;
//import com.nuanshui.frms.test.entity.cs.Frmsapi;
//import com.nuanshui.frms.test.entity.cs.Message;
//import com.nuanshui.frms.test.utils.JacksonUtils;
//import lombok.extern.slf4j.Slf4j;
//import net.sf.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//@Controller
//@RestController
//@RequestMapping("/test")
//@Slf4j
//public  class FrmsapiController {
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
//
//
//
//    @RequestMapping(value = "/selectfrmsapi", method = RequestMethod.POST, produces = {
//            "application/json; charset=utf-8" })
//    @ResponseBody
//    public String selectfrmsapi() throws IOException {
//        List<Frmsapi> frmsapis= frmsapiService.selectfrmsapi();
//        StringBuffer stb=new StringBuffer();
//        for (Frmsapi f:frmsapis){
//            stb.append(f.toString());
//        }
//        return stb.toString();
//
//    }
//
//
//
//
//    @RequestMapping(value = "/deletefrmsapi", method = RequestMethod.POST, produces = {
//            "application/json; charset=utf-8" })
//    @ResponseBody
//    private Long deletefrmsapi(@RequestBody String json) throws IOException {
//        Long id;
//        id = Long.parseLong(json);
//        Long res;
//        res = frmsapiService.loadfrmsapi(id);
//
//        System.out.println(res);
//
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("resultCode", "00");
//        jsonObject1.put("resultMsg", "成功");
//        return res;
//
//    }
//
//
//
//
//}
