package com.nuanshui.frms.test.csservice;

import com.nuanshui.frms.test.command.example.ReportData;
import com.nuanshui.frms.test.command.example.ReportInfo;
import com.nuanshui.frms.test.entity.cs.FrmsCase;
import com.nuanshui.frms.test.entity.cs.FrmsReport;

import java.util.List;

public interface FrmsRunService {
    List<ReportInfo> frmsruncase();
    ReportData runfrmstask(Integer id);
}
