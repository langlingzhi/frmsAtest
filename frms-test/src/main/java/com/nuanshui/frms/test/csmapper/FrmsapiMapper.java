package com.nuanshui.frms.test.csmapper;

import com.nuanshui.frms.test.command.example.FrmsCaseCmd;
import com.nuanshui.frms.test.command.example.FrmsapiCmd;
import com.nuanshui.frms.test.entity.cs.Frmsapi;

import java.util.List;

public  interface FrmsapiMapper {
     int insertfrmsapi(Frmsapi frmsapi);
     List<Frmsapi> selectfrmsapi(FrmsapiCmd frmsapiCmd);
     Frmsapi selectByPrimaryKey(Integer id);
     List<Frmsapi> selectByStatus( );
     int deletefrmsapi(Integer id);
     int updatefrmsapi(Frmsapi frmsapi);
}
