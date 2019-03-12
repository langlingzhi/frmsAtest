package com.nuanshui.frms.test.command.example;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public  class ReportInfo {

    private String name;

    private String className;

    private String methodName;

    private String description;

    private String spendTime;

    private String status;

    private List<String> log;

}