package com.nuanshui.frms.test.csservice.impl;

import com.nuanshui.frms.test.command.example.FrmsapiCmd;
import com.nuanshui.frms.test.csmapper.FrmsapiMapper;
import com.nuanshui.frms.test.csservice.FrmsEnvService;
import com.nuanshui.frms.test.csservice.FrmsapiService;
import com.nuanshui.frms.test.entity.cs.FrmsEnv;
import com.nuanshui.frms.test.entity.cs.Frmsapi;
import com.nuanshui.frms.test.utils.http.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.restassured.response.Response;
import java.util.List;

import static io.restassured.RestAssured.given;

@Service
@Slf4j
public class FrmsapiServiceImpl implements FrmsapiService {
    @Autowired
    FrmsapiMapper frmsapiMapper;
    @Autowired
    FrmsEnvService frmsEnvService;


    @Override
    public int insertfrmsapi(Frmsapi frmsapi) {

        return frmsapiMapper.insertfrmsapi(frmsapi);
    }

    @Override
    public List<Frmsapi> selectfrmsapi(FrmsapiCmd frmsapiCmd) {


        return frmsapiMapper.selectfrmsapi(frmsapiCmd);


    }

    @Override
    public Frmsapi selectByPrimaryKey(Integer id) {
        return frmsapiMapper.selectByPrimaryKey(id);
    }
    @Override
    public int deletefrmsapi(Integer id){
        return frmsapiMapper.deletefrmsapi(id);
    }


    @Override
    public int updatefrmsapi(Frmsapi frmsapi){
        return frmsapiMapper.updatefrmsapi(frmsapi);
    }

    @Override
    public String frmsapitest(Frmsapi frmsapi) {

        String msg ="";
        Response response = null;
        try {
            if (frmsapi.getMethod().equals("get")) {
                FrmsEnv frmsEnv = frmsEnvService.selectByPrimaryKey(frmsapi.getProductId());
                response = (Response) RequestUtil.sendgetWithHttp(frmsEnv.getEnvtest() + frmsapi.getPath(), frmsapi.getBody());
                if (response.getStatusCode() == 200) {
                    try {
                        frmsapi.setSusResponse(response.getBody().prettyPrint());
                        frmsapiMapper.updatefrmsapi(frmsapi);
                    }catch (Exception e){
                        log.info(MarkerFactory.getMarker("frmsapi存库"), "异常【{}】", e);
                    }
                    msg="success";
                } else {
                    try{
                        frmsapi.setFalResponse(String.valueOf(response.getStatusCode()));
                        frmsapiMapper.updatefrmsapi(frmsapi);
                    }catch (Exception e){
                        log.info(MarkerFactory.getMarker("frmsapi存库"), "异常【{}】", e);
                    }
                    msg="failed";
                }
            }
            if (frmsapi.getMethod().equals("post") ) {
                FrmsEnv frmsEnv = frmsEnvService.selectByPrimaryKey(frmsapi.getProductId());
                response = (Response) RequestUtil.sendpostWithHttp(frmsEnv.getEnvtest() + frmsapi.getPath(), frmsapi.getBody());
                if (response.getStatusCode() == 200) {
                    try{
                        frmsapi.setSusResponse(response.getBody().prettyPrint());
                        frmsapiMapper.updatefrmsapi(frmsapi);
                    }catch (Exception e){
                        log.info(MarkerFactory.getMarker("frmsapi存库"), "异常【{}】", e);
                    }
                    msg="success";

                } else {
                    try{
                        frmsapi.setFalResponse(String.valueOf(response.getStatusCode()));
                        frmsapiMapper.updatefrmsapi(frmsapi);
                    }catch (Exception e){
                        log.info(MarkerFactory.getMarker("frmsapi存库"), "异常【{}】", e);
                    }
                    msg="failed";
                }
            }
        }catch (Exception e){
            log.error("", e);
        }
        return msg;
    }

    @Override
    public List<Frmsapi> selectByStatus( ) {
        return frmsapiMapper.selectByStatus();
    }

}

