package com.nuanshui.frms.test.controller.frmsapi;

import com.nuanshui.frms.test.command.example.FrmsEnvCmd;
import com.nuanshui.frms.test.command.example.FrmsTaskCmd;
import com.nuanshui.frms.test.csservice.FrmsEnvService;
import com.nuanshui.frms.test.csservice.FrmsTaskService;
import com.nuanshui.frms.test.csservice.QuartzService;
import com.nuanshui.frms.test.entity.cs.FrmsEnv;
import com.nuanshui.frms.test.entity.cs.FrmsTask;
import com.nuanshui.frms.test.entity.cs.FrmsTaskList;
import com.nuanshui.frms.test.entity.cs.system.FrmsUser;
import com.nuanshui.frms.test.utils.JobUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping({"/frmsTask"})
public class FrmsTaskController {
    private static final Logger log = LoggerFactory.getLogger(FrmsTaskController.class);
    @Autowired
    private FrmsTaskService frmsTaskService;
    @Autowired
    private FrmsEnvService frmsEnvService;
    @Autowired
    QuartzService quartzService;

    @RequestMapping({"/getQryFrmsTask"})
    public String getQryFrmsTask(Model model) {
        model.addAttribute("productIds", frmsEnvService.selectAllProduct());
        return "/frmstask/indexfrmstask";
    }

    @RequestMapping(value = {"/qryFrmsTaskList"}, method = RequestMethod.POST)
    @ResponseBody
    public List<FrmsTaskList> qryFrmsTaskList(@RequestBody FrmsTaskCmd frmsTaskCmd,Model model) {
        List<FrmsTaskList> frmsTasks = null;
        try {
            model.addAttribute("productIds", frmsEnvService.selectAllProduct());
            frmsTasks = this.frmsTaskService.selectfrmstask(frmsTaskCmd);
        } catch (Exception e) {
            log.error("FrmsTaskController qryFrmsTaskList ERROR", e);
        }
        return frmsTasks;
    }

    @RequestMapping({"/toaddFrmsTask"})
    public String toaddFrmsEnv(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("userInfo") != null) {
            try {
                FrmsUser frmsUser = (FrmsUser) request.getSession().getAttribute("userInfo");
                model.addAttribute("frmsUser", frmsUser);
                model.addAttribute("productIds", frmsEnvService.selectAllProduct());
            } catch (Exception e) {
                log.error("FrmsTaskController toaddFrmsEnv ERROR", e);
            }
        }
        return "/frmstask/addfrmstask";
    }

    @RequestMapping(value = {"/addFrmsTask"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String addFrmsTask(@RequestBody FrmsTask frmsTask) {
        String msg;
        try {
            msg = this.frmsTaskService.insertfrmstask(frmsTask) == 0 ? "exists" : "success";
        } catch (Exception e) {
//            String msg;
            log.error("FrmsTaskController addFrmsTask ERROR", e);
            msg = "false";
        }
        return msg;
    }

    @RequestMapping(value = {"/delFrmsTask"})
    @ResponseBody
    public String delFrmsTask(String id) {

        String msg;
        try {
            msg = this.frmsTaskService.deletefrmstask(Integer.parseInt(id)) == 0 ? "exists" : "success";
        } catch (Exception e) {
//            String msg;
            log.error("FrmsTaskController delFrmsTask ERROR", e);
            msg = "false";
        }
        return msg;
    }

    @RequestMapping({"/toFrmsTaskModify"})
    public String toFrmsTaskModify(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("userInfo") != null) {
            try {
                FrmsUser frmsUser = (FrmsUser) request.getSession().getAttribute("userInfo");
                model.addAttribute("frmsUser", frmsUser);
                model.addAttribute("productIds", frmsEnvService.selectAllProduct());
                model.addAttribute("frmstask", frmsTaskService.selectByPrimaryKey(id));
            } catch (Exception e) {
                log.error("FrmsTaskController toFrmsTaskModify ERROR", e);
            }
        }
        return "/frmstask/frmstaskModify";
    }

    @RequestMapping(value = {"/frmsTaskModify"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String frmsTaskModify(@RequestBody FrmsTask frmsTask) {
        String msg;
        try {
            msg = this.frmsTaskService.updatefrmstask(frmsTask) == 0 ? "exists" : "success";
        } catch (Exception e) {
//            String msg;
            log.error("FrmsTaskController frmsTaskModify ERROR", e);
            msg = "false";
        }
        return msg;
    }
    @RequestMapping({"/toFrmsTaskJobrun"})
    public String toFrmsTaskJobrun(Integer id, Model model) {
            try {
                model.addAttribute("frmstask", frmsTaskService.selectByPrimaryKey(id));
            } catch (Exception e) {
                log.error("FrmsTaskController toFrmsTaskJobrun ERROR", e);
            }

        return "/frmstask/frmstaskjobrun";
    }
    @RequestMapping(value = {"/frmsTaskTaskJobrun"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String frmsTaskTaskJobrun(@RequestBody FrmsTask frmsTask) {
        String msg;
        try {
            msg = this.frmsTaskService.updatefrmstaskrun(frmsTask) == 0 ? "exists" : "success";
            quartzService.initVopVos();
            System.out.println("定时任务更新成功...");
        } catch (Exception e) {
//            String msg;
            log.error("FrmsTaskController frmsTaskModify ERROR", e);
            msg = "false";
        }
        return msg;
    }

}
