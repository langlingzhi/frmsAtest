package com.nuanshui.frms.test.csservice;

import com.nuanshui.frms.test.entity.cs.system.FrmsUser;
import com.nuanshui.frms.test.entity.cs.system.FrmsUserCmd;
import org.springframework.stereotype.Service;

@Service
public interface FrmsUserService {
    FrmsUser selectByFrmsUser(FrmsUserCmd frmsUserCmd);
    int updateByNoSelective(FrmsUserCmd frmsUserCmd);
}
