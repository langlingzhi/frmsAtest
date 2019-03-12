package com.nuanshui.frms.test.testcase.apitest;

import com.nuanshui.frms.test.command.example.Expschema;
import com.nuanshui.frms.test.command.example.ReportInfo;
import com.nuanshui.frms.test.utils.http.TestRunUtils;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class runtest {
    @Test
    public void runtest(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phoneNum", "13321131070");
        jsonObject.put("appName", "installmentloan");
        jsonObject.put("version", "1.0.0");
        Expschema expschema1=new Expschema();
        expschema1.setKey("resultCode");
        expschema1.setValue("1");
        List<Expschema> es=new ArrayList<>();
        es.add(expschema1);
        ReportInfo reportInfo=null;
//        reportInfo=TestRunUtils.scrawtestcase("http://frmsuat.nuanshui.net/fqh/validatePhone/whiteList",jsonObject.toString(),es);
        System.out.println(reportInfo.toString());
    }
}
