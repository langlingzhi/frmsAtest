package com.nuanshui.frms.test.csservice;

import com.nuanshui.frms.test.command.example.FrmsapiCmd;
import com.nuanshui.frms.test.entity.cs.FrmsEnv;
import com.nuanshui.frms.test.entity.cs.Frmsapi;

import io.restassured.response.Response;
import java.util.List;

public interface FrmsapiService {
    int insertfrmsapi(Frmsapi frmsapi);
    List<Frmsapi> selectfrmsapi(FrmsapiCmd frmsapiCmd);
    Frmsapi selectByPrimaryKey(Integer id);
    List<Frmsapi> selectByStatus( );
    int deletefrmsapi(Integer id);
    int updatefrmsapi(Frmsapi frmsapi);
    Frmsapi frmsapitest(Frmsapi frmsapi);
}
