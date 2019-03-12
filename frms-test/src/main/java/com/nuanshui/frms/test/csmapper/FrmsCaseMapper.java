package com.nuanshui.frms.test.csmapper;




import com.nuanshui.frms.test.command.example.FrmsCaseCmd;
import com.nuanshui.frms.test.entity.cs.FrmsCase;
import com.nuanshui.frms.test.entity.cs.FrmsCaseList;

import java.util.List;

public  interface FrmsCaseMapper {
     int insertfrmscase(FrmsCase frmsCase);
     List<FrmsCaseList> selectfrmscase(FrmsCaseCmd frmsCaseCmd);
     List<FrmsCaseList> selectRunfrmscase(FrmsCaseCmd frmsCaseCmd);
     FrmsCase selectfrmscaselog(Integer id);
     FrmsCase selectByPrimaryKey(Integer id);
     List<FrmsCase> selectCasesbyproductid(Integer productid);
     List<FrmsCase> selectAllCase();
     int deletefrmscase(Integer caseid);
     int updatefrmscase(FrmsCase frmscase);
     Integer selectmaxcaseid();
}
