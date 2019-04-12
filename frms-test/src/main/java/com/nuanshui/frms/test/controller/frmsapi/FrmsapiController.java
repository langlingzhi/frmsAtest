package com.nuanshui.frms.test.controller.frmsapi;

import com.nuanshui.frms.test.command.example.FrmsapiCmd;
import com.nuanshui.frms.test.csservice.FrmsEnvService;
import com.nuanshui.frms.test.csservice.FrmsapiService;
import com.nuanshui.frms.test.entity.cs.FrmsEnv;
import com.nuanshui.frms.test.entity.cs.Frmsapi;
import com.nuanshui.frms.test.entity.cs.system.FrmsUser;
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
@RequestMapping({"/frmsApi"})
public class FrmsapiController {
    private static final Logger log = LoggerFactory.getLogger(FrmsapiController.class);
    @Autowired
    private FrmsapiService frmsapiService;
    @Autowired
    private FrmsEnvService frmsEnvService;

    @RequestMapping({"/getQryFrmsApi"})
    public String getQryFrmsApiList(Model model) {
        try {
            model.addAttribute("productIds", frmsEnvService.selectAllProduct());
        } catch (Exception e) {
            log.error("FrmsapiController toaddFrmsApi ERROR", e);
        }
        return "/frmsapi/indexfrmsapi";
    }

    @RequestMapping(value = {"/qryFrmsApiList"}, method = RequestMethod.POST)
    @ResponseBody
    public List<Frmsapi> qryFrmsApiList(@RequestBody FrmsapiCmd frmsapiCmd) {
        List<Frmsapi> frmsapis = null;
        try {
            frmsapis = this.frmsapiService.selectfrmsapi(frmsapiCmd);
        } catch (Exception e) {
            log.error("FrmsapiController qryFrmsApiList ERROR", e);
        }
        return frmsapis;
    }

    @RequestMapping({"/toaddFrmsApi"})
    public String toaddFrmsApi(Model model, HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession().getAttribute("userInfo") != null) {
            try {
                FrmsUser frmsUser = (FrmsUser) request.getSession().getAttribute("userInfo");
                model.addAttribute("frmsUser", frmsUser);
                model.addAttribute("productIds", frmsEnvService.selectAllProduct());
            } catch (Exception e) {
                log.error("FrmsapiController toaddFrmsApi ERROR", e);
            }
        }

        return "/frmsapi/addfrmsapi";
    }

    @RequestMapping(value = {"/addFrmsApi"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String addFrmsApi(@RequestBody Frmsapi frmsapi) {
        String msg;
        try {

            msg = this.frmsapiService.insertfrmsapi(frmsapi) == 0 ? "exists" : "success";
        } catch (Exception e) {
//            String msg;
            log.error("FrmsapiController addFrmsApi ERROR", e);
            msg = "false";
        }
        return msg;
    }

    @RequestMapping(value = {"/delFrmsApi"})
    @ResponseBody
    public String delFrmsApi(String apiId) {

        String msg;
        try {
            msg = this.frmsapiService.deletefrmsapi(Integer.parseInt(apiId)) == 0 ? "exists" : "success";
        } catch (Exception e) {
//            String msg;
            log.error("FrmsapiController delFrmsApi ERROR", e);
            msg = "false";
        }
        return msg;
    }

    @RequestMapping({"/toFrmsApiModify"})
    public String toFrmsApiModify(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("userInfo") != null) {
            try {
                FrmsUser frmsUser = (FrmsUser) request.getSession().getAttribute("userInfo");
                model.addAttribute("frmsUser", frmsUser);
                model.addAttribute("frmsapi", frmsapiService.selectByPrimaryKey(id));
                model.addAttribute("productIds", frmsEnvService.selectAllProduct());
            } catch (Exception e) {
                log.error("FrmsapiController toFrmsApiModify ERROR", e);
            }
        }
        return "/frmsapi/frmsapiModify";
    }

    @RequestMapping({"/toFrmsApiTest"})
    public String toFrmsApiTest(Integer id, Model model) {
        Frmsapi frmsapi=frmsapiService.selectByPrimaryKey(id);
        FrmsEnv frmsEnv = frmsEnvService.selectByPrimaryKey(frmsapi.getProductId());
        frmsapi.setPath(frmsEnv.getEnvtest() + frmsapi.getPath());
        model.addAttribute("frmsapi",frmsapi);
        model.addAttribute("productIds", frmsEnvService.selectAllProduct());
        return "/frmsapi/frmsapitest";
    }

    @RequestMapping(value = {"/frmsApiModify"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String frmsApiModify(@RequestBody Frmsapi frmsapi) {
        String msg;
        try {
            msg = this.frmsapiService.updatefrmsapi(frmsapi) == 0 ? "exists" : "success";
        } catch (Exception e) {
//            String msg;
            log.error("FrmsapiController frmsApiModify ERROR", e);
            msg = "false";
        }
        return msg;
    }

    @RequestMapping(value = {"/frmsApiTest"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Frmsapi frmsApiTest(@RequestBody Frmsapi frmsapi,Model model) {
        Frmsapi frmsapi1 =new Frmsapi();
        try {
            frmsapi1 = this.frmsapiService.frmsapitest(frmsapi);
            model.addAttribute("frmsapi",frmsapi1);
        } catch (Exception e) {
//            String msg;
            log.error("FrmsapiController frmsApiTest ERROR", e);
        }
        return frmsapi1;
    }
}

