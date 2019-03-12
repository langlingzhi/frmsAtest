package com.nuanshui.frms.test.controller.frmsapi;


import com.nuanshui.frms.test.command.example.FrmsTest;
import com.nuanshui.frms.test.command.example.ReportData;
import com.nuanshui.frms.test.command.example.ReportInfo;
import com.nuanshui.frms.test.csservice.FrmsReportService;
import com.nuanshui.frms.test.csservice.FrmsRunService;
import com.nuanshui.frms.test.csservice.FrmsTaskService;
import com.nuanshui.frms.test.entity.cs.FrmsReport;
import com.nuanshui.frms.test.utils.JacksonUtils;
import com.nuanshui.frms.test.utils.http.ReportUtils;
import com.nuanshui.frms.test.utils.http.TestRunUtils;
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
@RequestMapping({"/frmsRun"})
public class RunController  {
    private static final Logger log = LoggerFactory.getLogger(RunController.class);
    @Autowired
    FrmsRunService frmsRunService;
    @Autowired
    FrmsReportService frmsReportService;
    @RequestMapping({"/getFrmsRun"})
    public String FrmsTestIndex(Model model)
    {
        return "/frmsrun/indexfrmsrun";
    }
    @RequestMapping(value={"/frmsRun"}, method= RequestMethod.POST)
    @ResponseBody
    public List<ReportInfo> FrmsTestRun()
    {
        List<ReportInfo> ris=null;
        try
        {
            ris = this.frmsRunService.frmsruncase();
        }
        catch (Exception e)
        {
            log.error("RunController FrmsTestRun ERROR", e);
        }
        return ris;
    }
    @RequestMapping({"/toTable"})
    public String toTable( Model model)
    {

        return "/frmscases/table";
    }
    @RequestMapping({"/toTableAdd"})
    public String toTableAdd( Model model)
    {
        return "/frmscases/tableadd";
    }

    @RequestMapping(value={"/totestcases"},method= RequestMethod.POST)
    public FrmsTest toTableAdd(@RequestBody FrmsTest frmsTest)
    {
        FrmsTest frmsTest1=new FrmsTest();
        try
        {
            frmsTest1=TestRunUtils.testcases(frmsTest);
        }
        catch (Exception e)
        {
            log.error("FrmsCaserunController totestcases ERROR", e);
        }
        return frmsTest1;
    }
    @RequestMapping(value = {"/runFrmsTask"})
    @ResponseBody
    public String runFrmsTask(String productid) {

        String msg;
        try {

            ReportData reportData=new ReportData();
            reportData=this.frmsRunService.runfrmstask(Integer.parseInt(productid));
            frmsReportService.insertfrmsreport(reportData);
            msg="success";
        } catch (Exception e) {
//            String msg;
            log.error("FrmsTaskController runFrmsTask ERROR", e);
            msg = "false";
        }
        return msg;
    }

}
