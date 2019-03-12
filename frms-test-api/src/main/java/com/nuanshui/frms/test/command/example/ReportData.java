package com.nuanshui.frms.test.command.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReportData implements Serializable {
    private String reportId;
    private String testName;
    private String testPass;
    private String testFail;
    private String testSkip;
    private String testAll;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date beginTime;
    private String totalTime;
    List<ReportInfo> testResult;
}
