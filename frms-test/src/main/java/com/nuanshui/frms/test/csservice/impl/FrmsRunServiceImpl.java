package com.nuanshui.frms.test.csservice.impl;

import com.nuanshui.frms.test.command.example.Expschema;
import com.nuanshui.frms.test.command.example.ReportData;
import com.nuanshui.frms.test.command.example.ReportInfo;
import com.nuanshui.frms.test.csservice.*;
import com.nuanshui.frms.test.entity.cs.*;
import com.nuanshui.frms.test.utils.DateUtils;
import com.nuanshui.frms.test.utils.http.TestRunUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FrmsRunServiceImpl implements FrmsRunService {

    @Autowired
    FrmsEnvService frmsEnvService;
    @Autowired
    FrmsapiService frmsapiService;
    @Autowired
    FrmsCaseService frmsCaseService;
    @Autowired
    FrmsVparamService frmsVparamService;
@Autowired
FrmsTaskService frmsTaskService;
    @Override
    public List<ReportInfo> frmsruncase() {

        List<FrmsCase> frmsCases = new ArrayList<>();
        frmsCases = frmsCaseService.selectAllCase();
        List<ReportInfo> ris = new ArrayList<>();
        for (FrmsCase frmsCase : frmsCases) {
            Frmsapi frmsapi = frmsapiService.selectByPrimaryKey(frmsCase.getApiid());
            FrmsEnv frmsEnv = frmsEnvService.selectByPrimaryKey(frmsapi.getProductId());

            String url = null;
            url = frmsEnv.getEnvtest() + frmsapi.getPath();
            ReportInfo reportInfo = null;
            List<FrmsVparam> fvs =frmsVparamService.selectfrmsvparamBycaseid(frmsCase.getId());
                    reportInfo = TestRunUtils.scrawtestcase(url, frmsapi.getMethod(), frmsapi.getReqType(), frmsCase.getRequestparam(), fvs);
            reportInfo.setClassName(frmsapi.getApiName());
            reportInfo.setMethodName(frmsCase.getCaseid());
            reportInfo.setDescription(frmsCase.getCasename());
            ris.add(reportInfo);
        }


        return ris;
    }
    @Override
    public ReportData runfrmstask(Integer id) {
        ReportData reportData = new ReportData();
        Date date = new Date();
        reportData.setBeginTime(date);
        FrmsTask frmsTask = new FrmsTask();
        frmsTask=frmsTaskService.selectByPrimaryKey(id);
        List<FrmsCase> frmsCases = new ArrayList<>();
        frmsCases = frmsCaseService.selectCasesbyproductid(frmsTask.getProductid());
        List<ReportInfo> ris = new ArrayList<>();
        int pass = 0, fail = 0, skip = 0;
        for (FrmsCase frmsCase : frmsCases) {
            Frmsapi frmsapi = frmsapiService.selectByPrimaryKey(frmsCase.getApiid());
            FrmsEnv frmsEnv = frmsEnvService.selectByPrimaryKey(frmsapi.getProductId());
            String url = null;
            if (frmsTask.getEnv().equals("0")) {
                url = frmsEnv.getEnvtest() + frmsapi.getPath();
            } else if (frmsTask.getEnv().equals("1")) {
                url = frmsEnv.getEnvpro() + frmsapi.getPath();
            }
            ReportInfo reportInfo = null;
            List<FrmsVparam> fvs = new ArrayList<>();
            fvs = frmsVparamService.selectfrmsvparamBycaseid(Integer.parseInt(frmsCase.getCaseid()));

            reportInfo = TestRunUtils.scrawtestcase(url,frmsCase.getRequestparam(), frmsapi.getMethod(), frmsapi.getReqType(), fvs);
            if (reportInfo.getStatus().equals("成功")) {
                pass++;
            } else if (reportInfo.getStatus().equals("失败")) {
                fail++;
            } else if (reportInfo.getStatus().equals("跳过")) {
                skip++;
            }
            reportInfo.setClassName(frmsapi.getApiName());
            reportInfo.setMethodName(frmsCase.getCaseid());
            reportInfo.setDescription(frmsCase.getCasename());
            ris.add(reportInfo);

        }
        reportData.setTestName(frmsTask.getTaskname());
        reportData.setReportId(String.valueOf(System.currentTimeMillis()));
        reportData.setTestPass(String.valueOf(pass));
        reportData.setTestFail(String.valueOf(fail));
        reportData.setTestSkip(String.valueOf(skip));
        reportData.setTestAll(String.valueOf(frmsCases.size()));
        reportData.setTotalTime(DateUtils.TimeDifference(date) + "ms");
        reportData.setTestResult(ris);
        System.out.println(reportData.toString());

        return reportData;
    }
}
