package com.nuanshui.frms.test.csservice;

import com.nuanshui.frms.test.command.example.FrmsEnvCmd;
import com.nuanshui.frms.test.entity.cs.FrmsEnv;

import java.util.List;

public interface FrmsEnvService {
    int insertfrmsenv(FrmsEnv frmsEnv);
    List<FrmsEnv> selectfrmsenv(FrmsEnvCmd frmsEnvCmd);
    FrmsEnv selectByPrimaryKey(Integer id);
    List<FrmsEnv> selectByStatus( );
    List<FrmsEnv> selectAllProduct();
    int deletefrmsenv(Integer id);
    int updatefrmsenv(FrmsEnv frmsEnv);
}
