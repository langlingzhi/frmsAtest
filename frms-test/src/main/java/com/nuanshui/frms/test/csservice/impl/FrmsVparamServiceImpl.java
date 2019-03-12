package com.nuanshui.frms.test.csservice.impl;

import com.nuanshui.frms.test.csmapper.FrmsVparamMapper;
import com.nuanshui.frms.test.csservice.FrmsVparamService;
import com.nuanshui.frms.test.entity.cs.FrmsVparam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrmsVparamServiceImpl implements FrmsVparamService {
    @Autowired
    FrmsVparamMapper frmsVparamMapper;
    @Override
    public int insertfrmsvparam(FrmsVparam frmsVparam) {
        return frmsVparamMapper.insertfrmsvparam(frmsVparam);
    }

    @Override
    public List<FrmsVparam> selectfrmsvparamBycaseid(Integer caseid) {
        return frmsVparamMapper.selectfrmsvparamBycaseid(caseid);
    }

    @Override
    public FrmsVparam selectByPrimaryKey(Integer id) {
        return frmsVparamMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deletefrmsvparam(Integer id) {
        return frmsVparamMapper.deletefrmsvparam(id);
    }

    @Override
    public int deletefrmsvparambycaseid(Integer caseid) {
        return frmsVparamMapper.deletefrmsvparambycaseid(caseid);
    }

    @Override
    public int updatefrmvparam(FrmsVparam frmsVparam) {
        return frmsVparamMapper.updatefrmvparam(frmsVparam);
    }
}
