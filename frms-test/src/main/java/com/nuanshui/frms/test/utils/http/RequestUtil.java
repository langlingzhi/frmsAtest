package com.nuanshui.frms.test.utils.http;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;


import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
@Slf4j
public  class RequestUtil {
    private static URL url;
    static Response response;
    public static Response sendpostWithHttp(String surl, String str) {
        String msg=null;
        try {
             url = new URL(surl);
        } catch (MalformedURLException e) {
            log.info("sendpostWithHttp error" +"MalformedURLException");
        }

            response = given().log().all().
                    header("accept", "application/json").
                    contentType("application/json").
                    body(str).
                    then().
                    when().
                    post(url);

        return response;
    }
    public static ValidatableResponse sendgetWithHttp(String surl, String str) {
        try {
            url = new URL(surl);
        } catch (MalformedURLException e) {
            log.info("sendpostWithHttp error" +"MalformedURLException");
        }
        ValidatableResponse response = given()
                .log().all()
                .queryParam(str)
                .when()
                .get(surl)
                .then()
                .log().all();
        return response;
    }
    public static Response sendpostWithHttps(String surl, String str) {
        try {
            url = new URL(surl);
        } catch (MalformedURLException e) {
            log.info("sendpostWithHttp error" +"MalformedURLException");
        }
        useRelaxedHTTPSValidation();
        Response response = given().log().all().
                header("accept", "application/json").
                contentType("application/json").
                body(str).
                then().
                when().
                post(url);
        return response;
    }
    public static ValidatableResponse sendgetWithHttps(String surl, String str) {
        try {
            url = new URL(surl);
        } catch (MalformedURLException e) {
            log.info("sendpostWithHttp error" +"MalformedURLException");
        }
        useRelaxedHTTPSValidation();
        ValidatableResponse response = given()
                .log().all()
                .queryParam(str)
                .when()
                .get(surl)
                .then()
                .log().all();
        return response;
    }
}
