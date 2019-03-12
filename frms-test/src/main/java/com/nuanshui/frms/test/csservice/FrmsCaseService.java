package com.nuanshui.frms.test.csservice;

import com.nuanshui.frms.test.command.example.FrmsCaseCmd;
import com.nuanshui.frms.test.command.example.TreeResponse;
import com.nuanshui.frms.test.entity.cs.FrmsCase;
import com.nuanshui.frms.test.entity.cs.FrmsCaseList;

import java.util.List;

public interface FrmsCaseService {
    int insertfrmscase(FrmsCase frmsCase);
    List<FrmsCaseList> selectfrmscase(FrmsCaseCmd frmsCaseCmd);
    List<FrmsCaseList> selectRunfrmscase(FrmsCaseCmd frmsCaseCmd);
    FrmsCase selectfrmscaselog(Integer id);
    FrmsCase selectByPrimaryKey(Integer id);
    List<FrmsCase> selectAllCase();
    List<FrmsCase> selectCasesbyproductid(Integer productid);
    int deletefrmscase(Integer caseid);
    int updatefrmscase(FrmsCase frmscase);
    int frmscasetest(FrmsCase frmsCase);
    Integer selectmaxid();
    String makeTreeResponse();
}
