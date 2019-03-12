package com.nuanshui.frms.test.controller.frmsapi;

import com.nuanshui.frms.test.command.example.FrmsEnvCmd;
import com.nuanshui.frms.test.csservice.FrmsEnvService;
import com.nuanshui.frms.test.entity.cs.FrmsEnv;
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
@RequestMapping({"/frmsEnv"})
public class FrmsEnvController {
    private static final Logger log = LoggerFactory.getLogger(FrmsapiController.class);
    @Autowired
    private FrmsEnvService frmsEnvService;
    @RequestMapping({"/getQryFrmsEnv"})
    public String getQryFrmsApiList()
    {
        return "/frmsenv/indexfrmsenv";
    }

    @RequestMapping(value={"/qryFrmsEnvList"}, method= RequestMethod.POST)
    @ResponseBody
    public List<FrmsEnv> qryFrmsEnvList(@RequestBody FrmsEnvCmd frmsEnvCmd)
    {
        List<FrmsEnv> frmsenvs = null;
        try
        {
            frmsenvs = this.frmsEnvService.selectfrmsenv(frmsEnvCmd);
        }
        catch (Exception e)
        {
            log.error("FrmsEnvController qryFrmsEnvList ERROR", e);
        }
        return frmsenvs;
    }

    @RequestMapping({"/toaddFrmsEnv"})
    public String toaddFrmsEnv()
    {
        return "/frmsenv/addfrmsenv";
    }

    @RequestMapping(value={"/addFrmsEnv"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String addFrmsEnv(@RequestBody FrmsEnv frmsEnv)
    {
        String msg;
        try
        {
            msg = this.frmsEnvService.insertfrmsenv(frmsEnv) == 0 ? "exists" : "success";
        }
        catch (Exception e)
        {
//            String msg;
            log.error("FrmsEnvController addFrmsEnv ERROR", e);
            msg = "false";
        }
        return msg;
    }

    @RequestMapping(value={"/delFrmsEnv"})
    @ResponseBody
    public String delFrmsEnv(String id)
    {

        String msg;
        try
        {
            msg = this.frmsEnvService.deletefrmsenv(Integer.parseInt(id)) == 0 ? "exists" : "success";
        }
        catch (Exception e)
        {
//            String msg;
            log.error("FrmsEnvController delFrmsEnv ERROR", e);
            msg = "false";
        }
        return msg;
    }

    @RequestMapping({"/toFrmsEnvModify"})
    public String todelFrmsEnvModify(Integer id, Model model)
    {
        model.addAttribute("frmsenv", frmsEnvService.selectByPrimaryKey(id));
        return "/frmsenv/frmsenvModify";
    }

    @RequestMapping(value={"/frmsEnvModify"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String frmsEnvModify(@RequestBody FrmsEnv frmsEnv)
    {
        String msg;
        try
        {
            msg = this.frmsEnvService.updatefrmsenv(frmsEnv) == 0 ? "exists" : "success";
        }
        catch (Exception e)
        {
//            String msg;
            log.error("FrmsEnvController frmsEnvModify ERROR", e);
            msg = "false";
        }
        return msg;
    }
}
