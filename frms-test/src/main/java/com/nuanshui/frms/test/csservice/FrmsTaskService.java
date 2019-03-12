package com.nuanshui.frms.test.csservice;

import com.nuanshui.frms.test.command.example.FrmsTaskCmd;
import com.nuanshui.frms.test.command.example.ReportData;
import com.nuanshui.frms.test.command.example.ReportInfo;
import com.nuanshui.frms.test.entity.cs.FrmsTask;
import com.nuanshui.frms.test.entity.cs.FrmsTaskList;
import org.springframework.stereotype.Service;

import java.util.List;
public interface FrmsTaskService {
    int insertfrmstask(FrmsTask frmsTask);
    List<FrmsTaskList> selectfrmstask(FrmsTaskCmd frmsTaskCmd);
    FrmsTask selectByPrimaryKey(Integer id);
    int deletefrmstask(Integer id);
    int updatefrmstask(FrmsTask frmsTask);
    List<FrmsTask> selectfrmstaskname();
    List<FrmsTask> selectRunTask();
    int updatefrmstaskrun(FrmsTask frmsTask);

}
