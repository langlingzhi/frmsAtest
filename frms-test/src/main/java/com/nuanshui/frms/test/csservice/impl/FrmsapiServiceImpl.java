package com.nuanshui.frms.test.csservice.impl;

import com.nuanshui.frms.test.command.example.FrmsapiCmd;
import com.nuanshui.frms.test.csmapper.FrmsapiMapper;
import com.nuanshui.frms.test.csservice.FrmsEnvService;
import com.nuanshui.frms.test.csservice.FrmsapiService;
import com.nuanshui.frms.test.entity.cs.FrmsEnv;
import com.nuanshui.frms.test.entity.cs.Frmsapi;
import com.nuanshui.frms.test.utils.http.RequestUtil;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
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
    public Frmsapi frmsapitest(Frmsapi frmsapi) {



        try {
            if (frmsapi.getReqType().equals("http")) {

                if (frmsapi.getMethod().equals("get")) {
                    Response response = (Response) RequestUtil.sendgetWithHttp( frmsapi.getPath(), frmsapi.getBody());
                        frmsapi.setSusResponse(response.getBody().prettyPrint());
                        frmsapi.setFalResponse(String.valueOf(response.getStatusCode()));

                } else {
                    Response response = (Response) RequestUtil.sendpostWithHttp(frmsapi.getPath(), frmsapi.getBody());
                    frmsapi.setSusResponse(response.getBody().prettyPrint());
                    frmsapi.setFalResponse(String.valueOf(response.getStatusCode()));
                }
            }
            if (frmsapi.getReqType().equals("https") ) {

                if (frmsapi.getMethod().equals("get")) {
                    Response response = (Response) RequestUtil.sendgetWithHttps( frmsapi.getPath(), frmsapi.getBody());
                    frmsapi.setSusResponse(response.getBody().prettyPrint());
                    frmsapi.setFalResponse(String.valueOf(response.getStatusCode()));

                } else {
                    Response response = (Response) RequestUtil.sendpostWithHttps(frmsapi.getPath(), frmsapi.getBody());
                    frmsapi.setSusResponse(response.getBody().prettyPrint());
                    frmsapi.setFalResponse(String.valueOf(response.getStatusCode()));
                }
            }
        }catch (Exception e){
            log.error("FrmsapiServiceImpl.frmsapitest", e);
        }
        return frmsapi;
    }

    @Override
    public List<Frmsapi> selectByStatus( ) {
        return frmsapiMapper.selectByStatus();
    }

}

