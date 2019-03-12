package com.nuanshui.frms.test.controller.system;

import com.nuanshui.frms.test.csservice.FrmsUserService;
import com.nuanshui.frms.test.entity.cs.system.FrmsUser;
import com.nuanshui.frms.test.entity.cs.system.FrmsUserCmd;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    FrmsUserService frmsUserService;
    @RequestMapping(value={"/login"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Map<String, String> login(String no, String password, HttpServletRequest req)
    {
        Map<String, String> map = new HashMap();
        try
        {
            FrmsUserCmd frmsUserCmd = new FrmsUserCmd();
            frmsUserCmd.setNo(no);
            password = password;

            FrmsUser frmsUser = frmsUserService.selectByFrmsUser(frmsUserCmd);
            if (frmsUser != null)
            {
                if (frmsUser.getStatus().intValue() == 0)
                {
                    if (password.equals(frmsUser.getPassword()))
                    {
                        req.getSession().setAttribute("userInfo", frmsUser);
                        map.put("status", "1");
                    }
                    else
                    {
                        map.put("status", "0");
                        map.put("msg", "密码错误,请重新输入!");
                    }
                }
                else
                {
                    map.put("status", "0");
                    map.put("msg", "登录用户状态无效,请联系管理员核实!");
                }
            }
            else
            {
                map.put("status", "0");
                map.put("msg", "此用户在系统中不存在!");
            }
        }
        catch (Exception e)
        {
            log.error("LoginController login ERROR", e);
            map.put("status", "0");
            map.put("msg", "后台异常,请联系管理员!");
        }
        return map;
    }

    @RequestMapping({"/toUpPwd"})
    public String toUpPwd(Model model, HttpServletRequest req)
    {
        if (req.getSession().getAttribute("userInfo") != null)
        {
            FrmsUser frmsUser = (FrmsUser) req.getSession().getAttribute("userInfo");
            model.addAttribute("username", frmsUser.getNo());
        }
        else
        {
            model.addAttribute("username", "");
        }
        return "/updatePwd";
    }

    @RequestMapping(value={"/updatePwd"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String updatePwd(@RequestBody FrmsUserCmd frmsUserCmd, HttpServletRequest req)
    {
        String message;
//        String message;
        if (req.getSession().getAttribute("userInfo") != null)
        {
            FrmsUser frmsUser = (FrmsUser) req.getSession().getAttribute("userInfo");
            if (StringUtils.isNotBlank(frmsUserCmd.getPassword())) {
                frmsUserCmd.setNewPwd(frmsUserCmd.getNewPwd());
            }
            if (StringUtils.isNotBlank(frmsUserCmd.getOldPwd())) {
                frmsUserCmd.setOldPwd(frmsUserCmd.getOldPwd());
            }
            if (!frmsUserCmd.getOldPwd().equals(frmsUser.getPassword())) {
                return "2";
            }
            frmsUserCmd.setPassword(frmsUserCmd.getNewPwd());
            int status = frmsUserService.updateByNoSelective(frmsUserCmd);
            if (status > 0)
            {
                message = "0";
                frmsUser.setPassword(frmsUserCmd.getPassword());
                req.getSession().setAttribute("userInfo", frmsUser);
            }
            else
            {
                message = "1";
            }
        }
        else
        {
            message = "3";
        }
        return message;
    }

    @RequestMapping({"/logout"})
    public String logout()
    {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = req.getSession();
        if (session != null) {
            req.getSession().removeAttribute("userInfo");
        }
        return "/login";
    }
}
