package com.nuanshui.frms.test.csmapper;

import com.nuanshui.frms.test.command.example.FrmsReportCmd;
import com.nuanshui.frms.test.entity.cs.FrmsReport;

import java.util.List;

public interface FrmsReportMapper {
    int insertfrmsreport(FrmsReport frmsReport);
    List<FrmsReport> selectfrmsreport(FrmsReportCmd frmsReportCmd);
    FrmsReport selectByPrimaryKey(Integer id);
    int selectByReportId(String reportid);
}
