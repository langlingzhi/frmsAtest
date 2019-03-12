package com.nuanshui.frms.test.csservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nuanshui.frms.test.command.example.FrmsReportCmd;
import com.nuanshui.frms.test.command.example.FrmsTaskCmd;
import com.nuanshui.frms.test.command.example.ReportData;
import com.nuanshui.frms.test.entity.cs.FrmsReport;
import com.nuanshui.frms.test.entity.cs.FrmsTask;

import java.util.List;

public interface FrmsReportService {
    int insertfrmsreport(ReportData reportData) throws JsonProcessingException;
    List<FrmsReport> selectfrmsreport(FrmsReportCmd frmsReportCmd);
    FrmsReport selectByPrimaryKey(Integer id);
    int selectByReportId(String reportid);
}
