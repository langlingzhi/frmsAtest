package com.nuanshui.frms.test.csservice.impl;

import com.nuanshui.frms.test.command.example.Expschema;
import com.nuanshui.frms.test.command.example.FrmsTaskCmd;
import com.nuanshui.frms.test.command.example.ReportData;
import com.nuanshui.frms.test.command.example.ReportInfo;
import com.nuanshui.frms.test.csmapper.FrmsTaskMapper;
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
public class FrmsTaskServiceImpl implements FrmsTaskService {
    @Autowired
    FrmsTaskMapper frmsTaskMapper;
    @Autowired
    FrmsCaseService frmsCaseService;
    @Autowired
    FrmsEnvService frmsEnvService;
    @Autowired
    FrmsapiService frmsapiService;
    @Autowired
    FrmsVparamService frmsVparamService;

    @Override
    public int insertfrmstask(FrmsTask frmsTask) {
        Date date = new Date();
        frmsTask.setCreate_time(date);
        frmsTask.setUpdate_time(date);
        return frmsTaskMapper.insertfrmstask(frmsTask);
    }

    @Override
    public List<FrmsTaskList> selectfrmstask(FrmsTaskCmd frmsTaskCmd) {
        return frmsTaskMapper.selectfrmstask(frmsTaskCmd);
    }

    @Override
    public FrmsTask selectByPrimaryKey(Integer id) {
        return frmsTaskMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deletefrmstask(Integer id) {
        return frmsTaskMapper.deletefrmstask(id);
    }

    @Override
    public int updatefrmstask(FrmsTask frmsTask) {
        Date date = new Date();
        frmsTask.setUpdate_time(date);
        return frmsTaskMapper.updatefrmstask(frmsTask);
    }

    @Override
    public List<FrmsTask> selectfrmstaskname() {
        return frmsTaskMapper.selectfrmstaskname();
    }

    @Override
    public List<FrmsTask> selectRunTask() {
        return frmsTaskMapper.selectRunTask();
    }

    @Override
    public int updatefrmstaskrun(FrmsTask frmsTask) {
        return frmsTaskMapper.updatefrmstaskrun(frmsTask);
    }

}
