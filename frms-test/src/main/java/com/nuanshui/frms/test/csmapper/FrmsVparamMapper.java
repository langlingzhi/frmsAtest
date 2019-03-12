package com.nuanshui.frms.test.csmapper;

import com.nuanshui.frms.test.entity.cs.FrmsVparam;

import java.util.List;

public interface FrmsVparamMapper {
    int insertfrmsvparam(FrmsVparam frmsVparam);
    List<FrmsVparam> selectfrmsvparamBycaseid( Integer caseid);
    FrmsVparam selectByPrimaryKey(Integer id);
    int deletefrmsvparam(Integer id);
    int deletefrmsvparambycaseid(Integer caseid);
    int updatefrmvparam(FrmsVparam frmsVparam);
}
