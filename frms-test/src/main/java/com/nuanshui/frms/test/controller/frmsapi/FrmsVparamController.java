package com.nuanshui.frms.test.controller.frmsapi;

import com.nuanshui.frms.test.command.example.FrmsVparamCmd;
import com.nuanshui.frms.test.csservice.FrmsVparamService;
import com.nuanshui.frms.test.entity.cs.FrmsVparam;
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
@RequestMapping({"/frmsVparam"})
public class FrmsVparamController{
    private static final Logger log = LoggerFactory.getLogger(FrmsapiController.class);
    @Autowired
    private FrmsVparamService frmsVparamService;

    @RequestMapping(value={"/qryFrmsVparamList"}, method= RequestMethod.POST)
    @ResponseBody
    public List<FrmsVparam> qryFrmsVparamList(@RequestBody FrmsVparamCmd frmsVparamCmd)
    {
        List<FrmsVparam> frmsVparams = null;
        try
        {
            frmsVparams = this.frmsVparamService.selectfrmsvparamBycaseid(frmsVparamCmd.getCaseid());
        }
        catch (Exception e)
        {
            log.error("FrmsVparamController qryFrmsVparamList ERROR", e);
        }
        return frmsVparams;
    }

    @RequestMapping(value={"/addFrmsVparam"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String addFrmsVparam(@RequestBody FrmsVparam frmsVparam)
    {
        String msg;
        try
        {

            msg = this.frmsVparamService.insertfrmsvparam(frmsVparam) == 0 ? "exists" : "success";
        }
        catch (Exception e)
        {
//            String msg;
            log.error("FrmsapiController addFrmsApi ERROR", e);
            msg = "false";
        }
        return msg;
    }

    @RequestMapping(value={"/delFrmsVparam"})
    @ResponseBody
    public String delFrmsVparam(String apiId)
    {

        String msg;
        try
        {
            msg = this.frmsVparamService.deletefrmsvparam(Integer.parseInt(apiId)) == 0 ? "exists" : "success";
        }
        catch (Exception e)
        {
//            String msg;
            log.error("FrmsapiController delFrmsApi ERROR", e);
            msg = "false";
        }
        return msg;
    }

}
