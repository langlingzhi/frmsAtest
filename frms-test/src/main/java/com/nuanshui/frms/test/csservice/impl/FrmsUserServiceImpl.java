package com.nuanshui.frms.test.csservice.impl;


import com.nuanshui.frms.test.csmapper.FrmsUserMapper;
import com.nuanshui.frms.test.csservice.FrmsUserService;
import com.nuanshui.frms.test.entity.cs.system.FrmsUser;
import com.nuanshui.frms.test.entity.cs.system.FrmsUserCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FrmsUserServiceImpl implements FrmsUserService {
    @Autowired
    private FrmsUserMapper frmsUserMapper;

    @Override
    public FrmsUser selectByFrmsUser(FrmsUserCmd frmsUserCmd) {
        return frmsUserMapper.selectByFrmsUser(frmsUserCmd);
    }

    @Override
    public int updateByNoSelective(FrmsUserCmd frmsUserCmd) {
        return frmsUserMapper.updateByNoSelective(frmsUserCmd);
    }
}
