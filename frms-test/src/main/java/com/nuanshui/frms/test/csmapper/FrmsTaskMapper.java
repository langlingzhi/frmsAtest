package com.nuanshui.frms.test.csmapper;

import com.nuanshui.frms.test.command.example.FrmsTaskCmd;
import com.nuanshui.frms.test.entity.cs.FrmsTask;
import com.nuanshui.frms.test.entity.cs.FrmsTaskList;

import java.util.List;

public interface FrmsTaskMapper {
    int insertfrmstask(FrmsTask frmsTask);
    List<FrmsTaskList> selectfrmstask(FrmsTaskCmd frmsTaskCmd);
    FrmsTask selectByPrimaryKey(Integer id);
    int deletefrmstask(Integer id);
    int updatefrmstask(FrmsTask frmsTask);
    List<FrmsTask> selectfrmstaskname();
    List<FrmsTask> selectRunTask();
    int updatefrmstaskrun(FrmsTask frmsTask);
}
