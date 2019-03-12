package com.nuanshui.frms.test.utils.http;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public  class RequestUtil {
    public static Response sendpostWithHttp(String surl, String str) throws Exception{
        String msg=null;
        URL url = new URL(surl);
        Response response = given().log().all().
                header("accept", "application/json").
                contentType("application/json").
                body(str).
                then().
                when().
                post(url);
        response.getBody().prettyPrint();

        return response;
    }
    public static ValidatableResponse sendgetWithHttp(String surl, String str) throws Exception{
        URL url = new URL(surl);
        ValidatableResponse response = given()
                .log().all()
                .queryParam(str)
                .when()
                .get(surl)
                .then()
                .log().all();
        return response;
    }
    public static Response sendpostWithHttps(String surl, String str) throws Exception{
        URL url = new URL(surl);
        useRelaxedHTTPSValidation();
        Response response = given().log().all().
                header("accept", "application/json").
                contentType("application/json").
                body(str).
                then().
                statusCode(200).
                when().
                post(url);
        response.getBody().prettyPrint();
        return response;
    }
    public static ValidatableResponse sendgetWithHttps(String surl, String str) throws Exception{
        URL url = new URL(surl);
        useRelaxedHTTPSValidation();
        ValidatableResponse response = given()
                .log().all()
                .queryParam(str)
                .when()
                .get(surl)
                .then()
                .log().all()
                .statusCode(200);
        return response;
    }
}
