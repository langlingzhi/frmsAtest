package com.nuanshui.frms.test.csmapper;


import com.nuanshui.frms.test.command.example.FrmsEnvCmd;
import com.nuanshui.frms.test.entity.cs.FrmsEnv;

import java.util.List;

public  interface FrmsEnvMapper {
     int insertfrmsenv(FrmsEnv frmsEnv);
     List<FrmsEnv> selectfrmsenv(FrmsEnvCmd frmsEnvCmd);
     FrmsEnv selectByPrimaryKey(Integer id);
     List<FrmsEnv> selectByStatus( );
     int deletefrmsenv(Integer id);
     List<FrmsEnv> selectAllProduct();
     int updatefrmsenv(FrmsEnv frmsEnv);
}
