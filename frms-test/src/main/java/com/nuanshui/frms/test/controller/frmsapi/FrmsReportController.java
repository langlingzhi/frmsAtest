package com.nuanshui.frms.test.controller.frmsapi;

import com.nuanshui.frms.test.command.example.FrmsReportCmd;
import com.nuanshui.frms.test.command.example.ReportData;
import com.nuanshui.frms.test.csservice.FrmsReportService;
import com.nuanshui.frms.test.csservice.FrmsTaskService;
import com.nuanshui.frms.test.entity.cs.FrmsReport;
import com.nuanshui.frms.test.utils.JacksonUtils;
import com.nuanshui.frms.test.utils.http.ReportUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping({"/frmsReport"})
public class FrmsReportController {
    private static final Logger log = LoggerFactory.getLogger(FrmsTaskController.class);
    @Autowired
    FrmsReportService frmsReportService;
    @Autowired
    FrmsTaskService frmsTaskService;
    @RequestMapping({"/getQryFrmsReport"})
    public String getQryFrmsTask(Model model) {
        model.addAttribute("TaskNames", frmsTaskService.selectfrmstaskname());
        return "/frmsreport/indexfrmsreport";
    }

    @RequestMapping(value = {"/qryFrmsReportList"}, method = RequestMethod.POST)
    @ResponseBody
    public List<FrmsReport> qryFrmsTaskList(@RequestBody FrmsReportCmd frmsReportCmd, Model model) {
        List<FrmsReport> frmsReports = null;
        try {
            model.addAttribute("TaskNames",frmsTaskService.selectfrmstaskname() );
            frmsReports = this.frmsReportService.selectfrmsreport(frmsReportCmd);
        } catch (Exception e) {
            log.error("FrmsTaskController qryFrmsTaskList ERROR", e);
        }
        return frmsReports;
    }
    @RequestMapping({"/toDetailReport"})
    public String toDetailReport( Model model,Integer id)
    {
        try {
            ReportData reportData = new ReportData();
            reportData = JacksonUtils.parseJsonFromString(frmsReportService.selectByPrimaryKey(id).getTestResult(), ReportData.class);
            ReportUtils.makereports(reportData);
        }catch (Exception e){
            log.error("FrmsReportController toDetailReport ERROR", e);
        }
        return "/frmsreport/report";
    }
    @RequestMapping({"/toTable"})
    public String toTable( Model model)
    {
//        model.addAttribute("resultData",frmsReportService.selectByPrimaryKey(7).getTestResult() );
        return "/frmsreport/report";
    }
}
