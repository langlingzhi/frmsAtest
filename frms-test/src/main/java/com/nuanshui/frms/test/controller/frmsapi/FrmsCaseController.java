package com.nuanshui.frms.test.controller.frmsapi;

import com.nuanshui.frms.test.command.example.FrmsCaseCmd;
import com.nuanshui.frms.test.csservice.FrmsCaseService;
import com.nuanshui.frms.test.csservice.FrmsVparamService;
import com.nuanshui.frms.test.csservice.FrmsapiService;
import com.nuanshui.frms.test.entity.cs.FrmsCase;
import com.nuanshui.frms.test.entity.cs.FrmsCaseList;
import com.nuanshui.frms.test.utils.IOUtil;
import com.nuanshui.frms.test.utils.PathUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping({"/frmsCase"})
public class FrmsCaseController {
    private static final Logger log = LoggerFactory.getLogger(FrmsapiController.class);
    @Autowired
    private FrmsCaseService frmsCaseService;
    @Autowired
    private FrmsapiService frmsapiService;
    @Autowired
    FrmsVparamService frmsVparamService;
    @RequestMapping({"/getQryFrmsCase"})
    public String getQryFrmsCase(Model model)
    {
        try{
            model.addAttribute("apiIds",frmsapiService.selectByStatus());
        }catch (Exception e){
            log.error("FrmsCaseController getQryFrmsCase ERROR", e);
        }
        return "/frmscase/indexfrmscase";
    }
    @RequestMapping({"/getQryFrmsCases"})
    public String getQryFrmsCases(int apiId,Model model)
    {
        try{
            model.addAttribute("apiIds",frmsapiService.selectByStatus());
            model.addAttribute("apiId",apiId);


        }catch (Exception e){
            log.error("FrmsCaseController getQryFrmsCase ERROR", e);
        }
        return "/frmscases/frmscaseindex";

    }

    @RequestMapping(value={"/qryFrmsCaseList"}, method= RequestMethod.POST)
    @ResponseBody
    public List<FrmsCaseList> qryFrmsCaseList(@RequestBody FrmsCaseCmd frmsCaseCmd)
    {
        List<FrmsCaseList> frmscases = null;
        try
        {
            frmscases = this.frmsCaseService.selectfrmscase(frmsCaseCmd);
        }
        catch (Exception e)
        {
            log.error("FrmsCaseController qryFrmsCaseList ERROR", e);
        }
        return frmscases;
    }

    @RequestMapping({"/toaddFrmsCase"})
    public String toaddFrmsCase(Model model)
    {
        try{
            model.addAttribute("apiIds",frmsapiService.selectByStatus());
        }catch (Exception e){
            log.error("FrmsCaseController toaddFrmsCase ERROR", e);
        }
        return "/frmscase/addfrmscase";
    }
    @RequestMapping({"/toaddFrmsCases"})
    public String toaddFrmsCases(int apiId,Model model)
    {
        try{
            model.addAttribute("apiIds",frmsapiService.selectByStatus());
            model.addAttribute("apiId",apiId);
            model.addAttribute("caseId",frmsCaseService.selectmaxid());
        }catch (Exception e){
            log.error("FrmsCaseController toaddFrmsCase ERROR", e);
        }
        return "/frmscases/frmscaseadd";
    }

    @RequestMapping({"/toFrmsCaseLeft"})
    public String toFrmsCaseLeft(Model model)
    {
        try{
            model.addAttribute("apiIds",frmsapiService.selectByStatus());
        }catch (Exception e){
            log.error("FrmsCaseController toaddFrmsCase ERROR", e);
        }
        return "/frmscases/frmscaseleft";
    }

    @RequestMapping(value={"/addFrmsCase"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String addFrmsCase(@RequestBody FrmsCase frmsCase)
    {
        String msg;
        try
        {

            msg = this.frmsCaseService.insertfrmscase(frmsCase) == 0 ? "exists" : "success";
        }
        catch (Exception e)
        {
//            String msg;
            log.error("FrmsCaseController addFrmsCase ERROR", e);
            msg = "false";
        }
        return msg;
    }

    @RequestMapping(value={"/delFrmsCase"})
    @ResponseBody
    public String delFrmsCase(String caseid)
    {

        String msg1;
        String msg2;
        try
        {
            msg1 = this.frmsCaseService.deletefrmscase(Integer.parseInt(caseid)) == 0 ? "exists" : "success";
            msg2 = frmsVparamService.deletefrmsvparambycaseid(Integer.parseInt(caseid))==0? "exists" : "success";

        }
        catch (Exception e)
        {
//            String msg;
            log.error("FrmsCaseController delFrmsCase ERROR", e);
            msg1 = "false";
        }

        return msg1;
    }
    @RequestMapping(value={"/testFrmsCase"})
    @ResponseBody
    public String testFrmsCase(Integer id)
    {

        String msg;
        try
        {
            msg = this.frmsCaseService.frmscasetest(frmsCaseService.selectByPrimaryKey(id)) == 0 ? "exists" : "success";
        }
        catch (Exception e)
        {
//            String msg;
            log.error("FrmsCaseController delFrmsCase ERROR", e);
            msg = "false";
        }
        return msg;
    }

    @RequestMapping({"/toFrmsCaseModify"})
    public String toFrmsCaseModify(Integer id, Model model)
    {
        model.addAttribute("frmscase", frmsCaseService.selectByPrimaryKey(id));
        model.addAttribute("apiIds",frmsapiService.selectByStatus());
        return "/frmscase/frmscaseModify";
    }
    @RequestMapping({"/toFrmsCasesModify"})
    public String toFrmsCasesModify(Integer id, Model model)
    {
        model.addAttribute("frmscase", frmsCaseService.selectByPrimaryKey(id));
        model.addAttribute("apiIds",frmsapiService.selectByStatus());
        return "/frmscases/frmscasemodify";
    }

    @RequestMapping(value={"/frmsCaseModify"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String frmsCaseModify(@RequestBody FrmsCase frmsCase)
    {
        String msg;
        try
        {
            msg = this.frmsCaseService.updatefrmscase(frmsCase) == 0 ? "exists" : "success";
        }
        catch (Exception e)
        {
//            String msg;
            log.error("FrmsCaseController frmsCaseModify ERROR", e);
            msg = "false";
        }
        return msg;
    }

    @RequestMapping({"/toFrmsCaseLog"})
    public String toFrmsCaseLog(Integer id, Model model)
    {
        model.addAttribute("frmscase", frmsCaseService.selectfrmscaselog(id));
        return "/frmscase/frmscaseLog";
    }
    @RequestMapping(value={"/toTreejson" },method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String toTreejson()
    {
        String msg;
        try
        {
            String data;
            data = this.frmsCaseService.makeTreeResponse();
            JSONObject status=new JSONObject();
            status.put("code",200);
            status.put("message","操作成功");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", status.toString());  //必填
            jsonObject.put("data",data);        //必填
            msg=jsonObject.toString();
        }
        catch (Exception e)
        {
//            String msg;
            log.error("FrmsCaseController toTreejson ERROR", e);
            JSONObject status=new JSONObject();
            status.put("code",400);
            status.put("message","操作失败");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", status.toString());  //必填
            msg=jsonObject.toString();
        }

//        String fileName ="C:\\workspacegit\\frms-test\\frms-test\\src\\main\\resources\\static\\json\\iframetree.json";
//        System.out.println(fileName);
//        File file =new File(fileName);
//        String msg = IOUtil.readStringFromFile(file, "UTF-8");
//        System.out.println(msg);
        return msg;
    }


}
