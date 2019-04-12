package com.nuanshui.frms.test.csservice.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nuanshui.frms.test.command.example.FrmsReportCmd;
import com.nuanshui.frms.test.command.example.ReportData;
import com.nuanshui.frms.test.csmapper.FrmsReportMapper;
import com.nuanshui.frms.test.csservice.FrmsReportService;
import com.nuanshui.frms.test.entity.cs.FrmsReport;
import com.nuanshui.frms.test.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FrmsReportServiceImpl implements FrmsReportService {
    @Autowired
    FrmsReportMapper frmsReportMapper;
    @Override
    public int insertfrmsreport(ReportData reportData) throws JsonProcessingException {
        FrmsReport frmsReport=new FrmsReport();
        frmsReport.setReportId(reportData.getReportId());
        frmsReport.setTestPass(reportData.getTestPass());
        frmsReport.setTestFail(reportData.getTestFail());
        frmsReport.setBeginTime(reportData.getBeginTime());
        frmsReport.setTotalTime(reportData.getTotalTime());
        frmsReport.setTestAll(reportData.getTestAll());
        frmsReport.setTestSkip(reportData.getTestSkip());
        frmsReport.setTestName(reportData.getTestName());
        frmsReport.setTestResult(JacksonUtils.serialObject(reportData));
        Date date = new Date();
        frmsReport.setCreate_time(date);
        return frmsReportMapper.insertfrmsreport(frmsReport);
    }

    @Override
    public List<FrmsReport> selectfrmsreport(FrmsReportCmd frmsReportCmd) {
        return frmsReportMapper.selectfrmsreport(frmsReportCmd);
    }

    @Override
    public FrmsReport selectByPrimaryKey(Integer id) {
        return frmsReportMapper.selectByPrimaryKey(id);
    }

    @Override
    public int selectByReportId(String reportid) {
        return frmsReportMapper.selectByReportId(reportid);
    }

}
