package com.nuanshui.frms.test.utils.http;

import com.nuanshui.frms.test.command.example.Expschema;
import com.nuanshui.frms.test.command.example.FrmsTest;
import com.nuanshui.frms.test.command.example.ReportInfo;
import com.nuanshui.frms.test.entity.cs.FrmsCase;
import com.nuanshui.frms.test.entity.cs.FrmsVparam;
import com.nuanshui.frms.test.utils.ReportUtil;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestRunUtils {

    private static final Logger log = LoggerFactory.getLogger(TestRunUtils.class);

    public static ReportInfo scrawtestcase(String surl, String sbody,String Method,String ReqType, List<FrmsVparam> fvs) {
        String msg = null;
        ReportInfo reportInfo = new ReportInfo();
        try {
            Response response=null;
            if(ReqType.equals("http")&& Method.equals("get")){
                response = (Response) RequestUtil.sendgetWithHttp(surl,sbody);
                response.getBody().prettyPrint();
            }
            else if(ReqType.equals("http")&& Method.equals("post")){
                response = (Response) RequestUtil.sendpostWithHttp(surl,sbody);
                response.getBody().prettyPrint();
            }
            else if(ReqType.equals("https")&& Method.equals("get")){
                response = (Response) RequestUtil.sendgetWithHttps(surl,sbody);
                response.getBody().prettyPrint();
            }
            else if(ReqType.equals("https")&& Method.equals("post")){
                response = (Response) RequestUtil.sendpostWithHttps(surl,sbody);
                response.getBody().prettyPrint();
            }

            if (String.valueOf(response.getStatusCode()).equals("200")) {
                reportInfo.setSpendTime(String.valueOf(response.getTime()+"ms"));
                for (FrmsVparam frmsVparam : fvs) {
                    response.then().body(frmsVparam.getParamKey(), equalTo(frmsVparam.getPramValue()));
                }
            }
            reportInfo.setStatus("成功");
            List<String> logs=new ArrayList<>();
            logs.add(response.getBody().prettyPrint());
            reportInfo.setLog(logs);
        } catch (AssertionError error) {

            error.printStackTrace();
            log.info(ReportUtil.toString_02(error));
            List<String> logs=new ArrayList<>();
            logs.add(ReportUtil.toString_02(error));
            reportInfo.setStatus("失败");
            reportInfo.setLog(logs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reportInfo;

    }

    public static FrmsCase testcase(String surl, FrmsCase frmsCase, java.util.List<FrmsVparam> fv) {
        String msg = null;
        try {
            URL url = new URL(surl);
            Response response = given().log().all().
                    header("accept", "application/json").
                    contentType("application/json").
                    body(frmsCase.getRequestparam()).
                    then().
                    when().
                    post(url);
            response.getBody().prettyPrint();
            frmsCase.setRelstatus(String.valueOf(response.getStatusCode()));
            if (frmsCase.getRelstatus().equals(frmsCase.getExpstatus())) {
                frmsCase.setSpendtime(String.valueOf(response.getTime()));
                frmsCase.setRelresponse(response.getBody().prettyPrint());
                for (FrmsVparam frmsVparam : fv) {
                    if (frmsVparam.getParamType().equals("string"))
                        response.then().body(frmsVparam.getParamKey(), equalTo(frmsVparam.getPramValue()));
                    else if (frmsVparam.getParamType().equals("int"))
                        response.then().body(frmsVparam.getParamKey(), equalTo(Integer.parseInt(frmsVparam.getPramValue())));
                }
                frmsCase.setVerifyresult("成功");
            }

        } catch (AssertionError error) {

            error.printStackTrace();
            log.info(ReportUtil.toString_02(error));
            frmsCase.setVerifyresult("失败");
            frmsCase.setLog(ReportUtil.toString_02(error));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return frmsCase;

    }

    public static FrmsTest testcases(FrmsTest frmsTest) {
        Response response=null;
        try {
            if (frmsTest.getReqType().equals("http")) {
                if (frmsTest.getMethod().equals("get")) {
                    response= (Response) RequestUtil.sendgetWithHttp(frmsTest.getPath(), frmsTest.getBody());
                } else if (frmsTest.getMethod().equals("post")) {
                    response= RequestUtil.sendpostWithHttp(frmsTest.getPath(), frmsTest.getBody());
                }
            } else if (frmsTest.getReqType().equals("https")) {
                if (frmsTest.getMethod().equals("get")) {
                    response= (Response) RequestUtil.sendgetWithHttps(frmsTest.getPath(), frmsTest.getBody());
                } else if (frmsTest.getMethod().equals("post")) {
                    response= RequestUtil.sendpostWithHttps(frmsTest.getPath(), frmsTest.getBody());
                }
            }


        } catch (AssertionError error) {

            error.printStackTrace();
            log.info(ReportUtil.toString_02(error));
        } catch (Exception e) {
            e.printStackTrace();
            log.info(ReportUtil.toString_02(e));
        }
        frmsTest.setResponse((org.springframework.web.bind.annotation.ResponseBody) response.getBody());
        return frmsTest;

    }
}
