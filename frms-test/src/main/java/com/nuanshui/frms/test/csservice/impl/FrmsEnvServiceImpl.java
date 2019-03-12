package com.nuanshui.frms.test.csservice.impl;

import com.nuanshui.frms.test.command.example.FrmsEnvCmd;
import com.nuanshui.frms.test.csmapper.FrmsEnvMapper;
import com.nuanshui.frms.test.csservice.FrmsEnvService;
import com.nuanshui.frms.test.entity.cs.FrmsEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class FrmsEnvServiceImpl implements FrmsEnvService {

    @Autowired
    private FrmsEnvMapper frmsEnvMapper;
    @Override
    public int insertfrmsenv(FrmsEnv frmsEnv) {
        return frmsEnvMapper.insertfrmsenv(frmsEnv);
    }

    @Override
    public List<FrmsEnv> selectfrmsenv(FrmsEnvCmd frmsEnvCmd) {
        return frmsEnvMapper.selectfrmsenv(frmsEnvCmd);
    }

    @Override
    public FrmsEnv selectByPrimaryKey(Integer id) {
        return frmsEnvMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FrmsEnv> selectByStatus() {
        return frmsEnvMapper.selectByStatus();
    }

    @Override
    public List<FrmsEnv> selectAllProduct() {
        return frmsEnvMapper.selectAllProduct();
    }


    @Override
    public int deletefrmsenv(Integer id) {
        return frmsEnvMapper.deletefrmsenv(id);
    }

    @Override
    public int updatefrmsenv(FrmsEnv frmsEnv) {
        return frmsEnvMapper.updatefrmsenv(frmsEnv);
    }
}
