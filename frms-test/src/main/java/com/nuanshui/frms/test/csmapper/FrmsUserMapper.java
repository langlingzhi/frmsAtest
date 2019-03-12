package com.nuanshui.frms.test.csmapper;

import com.nuanshui.frms.test.entity.cs.system.FrmsUser;
import com.nuanshui.frms.test.entity.cs.system.FrmsUserCmd;

public interface FrmsUserMapper {
    FrmsUser selectByFrmsUser(FrmsUserCmd frmsUserCmd);
    int updateByNoSelective(FrmsUserCmd frmsUserCmd);
}
