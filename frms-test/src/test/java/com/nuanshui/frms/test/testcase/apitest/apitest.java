package com.nuanshui.frms.test.testcase.apitest;

import com.nuanshui.frms.test.TestApplication;
import com.nuanshui.frms.test.api.listeners.AutoTestListener;
import com.nuanshui.frms.test.api.listeners.RetryListener;
import com.nuanshui.frms.test.api.utils.ReportUtil;
import com.nuanshui.frms.test.csservice.FrmsapiService;
import com.nuanshui.frms.test.entity.cs.Frmsapi;
import com.nuanshui.frms.test.utils.http.RequestUtil;
import org.dom4j.DocumentException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Listeners({ AutoTestListener.class, RetryListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(TestApplication.class)
public class apitest extends AbstractTestNGSpringContextTests {

    @Autowired
    FrmsapiService frmsapiService;

    @DataProvider(name = "apiDatas")
    public Iterator<Object[]> getApiData(ITestContext context)
            throws DocumentException {
        List<Frmsapi> frmsapis= frmsapiService.selectByStatus();

        StringBuffer stb=new StringBuffer();
        for (Frmsapi f:frmsapis){
            stb.append(f.toString());
        }
        System.out.println(stb.toString());
        List<Object[]> dataProvider = new ArrayList<Object[]>();
        for (Frmsapi data : frmsapis) {

                dataProvider.add(new Object[] { data });

        }
        return dataProvider.iterator();
    }

    @Test(dataProvider = "apiDatas")
    public void apiTest(Frmsapi frmsapi) throws Exception {
        ReportUtil.log("--- test start ---");
        String responseData;

            // 执行
            if(frmsapi.getMethod().equals("get")){
                RequestUtil.sendgetWithHttp(frmsapi.getPath(),frmsapi.getBody());
            }
            if(frmsapi.getMethod().equals("post")){
                RequestUtil.sendpostWithHttp(frmsapi.getPath(),frmsapi.getBody());
            }
            else{
                ReportUtil.log("method is not support:" + frmsapi.getMethod());
            }

        // 输出返回数据log
        ReportUtil.log("--- test finish ---" );

    }

}
