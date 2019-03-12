package com.nuanshui.frms.test.csservice.impl;

import com.nuanshui.frms.test.command.example.*;
import com.nuanshui.frms.test.csmapper.FrmsCaseMapper;
import com.nuanshui.frms.test.csservice.FrmsCaseService;
import com.nuanshui.frms.test.csservice.FrmsEnvService;
import com.nuanshui.frms.test.csservice.FrmsVparamService;
import com.nuanshui.frms.test.csservice.FrmsapiService;
import com.nuanshui.frms.test.entity.cs.*;
import com.nuanshui.frms.test.utils.http.TestRunUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class FrmsCaseServiceImpl implements FrmsCaseService{
    @Autowired
    private FrmsCaseMapper frmsCaseMapper;
    @Autowired
    FrmsCaseService frmsCaseService;
    @Autowired
    FrmsapiService frmsapiService;
    @Autowired
    FrmsEnvService frmsEnvService;
    @Autowired
    FrmsVparamService frmsVparamService;
    @Override
    public int insertfrmscase(FrmsCase frmsCase) {
        return frmsCaseMapper.insertfrmscase(frmsCase);
    }

    @Override
    public List<FrmsCaseList> selectfrmscase(FrmsCaseCmd frmsCaseCmd) {
        return frmsCaseMapper.selectfrmscase(frmsCaseCmd);
    }

    @Override
    public List<FrmsCaseList> selectRunfrmscase(FrmsCaseCmd frmsCaseCmd) {
        return frmsCaseMapper.selectRunfrmscase(frmsCaseCmd);
    }

    @Override
    public FrmsCase selectfrmscaselog(Integer id) {
        return frmsCaseMapper.selectfrmscaselog(id);
    }

    @Override
    public FrmsCase selectByPrimaryKey(Integer id) {
        return frmsCaseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FrmsCase> selectAllCase() {
        return frmsCaseMapper.selectAllCase();
    }

    @Override
    public List<FrmsCase> selectCasesbyproductid(Integer productid) {
        return frmsCaseMapper.selectCasesbyproductid(productid);
    }


    @Override
    public int deletefrmscase(Integer caseid) {
        return frmsCaseMapper.deletefrmscase(caseid);
    }

    @Override
    public int updatefrmscase(FrmsCase frmscase) {
        Date date=new Date();
        frmscase.setCreate_time((java.sql.Date) date);
        frmscase.setUpdate_time((java.sql.Date) date);
        return frmsCaseMapper.updatefrmscase(frmscase);
    }

    @Override
    public int frmscasetest(FrmsCase frmsCase) {
        Frmsapi frmsapi=frmsapiService.selectByPrimaryKey(frmsCase.getApiid());
        FrmsEnv frmsEnv= frmsEnvService.selectByPrimaryKey(frmsapi.getProductId());
        List<FrmsVparam> fv=new ArrayList<>();
        fv=frmsVparamService.selectfrmsvparamBycaseid(frmsCase.getId());
        String url=null;
        url=frmsEnv.getEnvtest()+frmsapi.getPath();
        frmsCase= TestRunUtils.testcase(url,frmsCase,fv);
        Date date=new Date();
        frmsCase.setUpdate_time((java.sql.Date) date);
        return frmsCaseMapper.updatefrmscase(frmsCase);
    }

    @Override
    public Integer selectmaxid() {
        return frmsCaseMapper.selectmaxcaseid();
    }

    @Override
    public String makeTreeResponse() {
        List<FrmsEnv> frmsEnvs= frmsEnvService.selectByStatus();
        JSONArray data=new JSONArray();
        for(FrmsEnv frmsEnv:frmsEnvs){
            TreeResponse treeResponse=new TreeResponse();
            treeResponse.setId(String.valueOf(frmsEnv.getId()));
            treeResponse.setTitle(frmsEnv.getProductname());
            treeResponse.setIsLast(false);

            treeResponse.setParentId("0");
            treeResponse.setLevel("1");
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("type","0");
            jsonObject1.put("isChecked","0");
            treeResponse.setCheckArr(jsonObject1.toString());
            FrmsapiCmd frmsapiCmd=new FrmsapiCmd();
            frmsapiCmd.setProductId(frmsEnv.getId());

            List<Frmsapi> frmsapis=frmsapiService.selectfrmsapi(frmsapiCmd);
            if(frmsapis.isEmpty()){
                continue;
            }
            JSONArray children=new JSONArray();
            for(Frmsapi frmsapi:frmsapis){
                TreeChildren treeChildren=new TreeChildren();
                treeChildren.setId(String.valueOf(frmsapi.getId()));
                treeChildren.setTitle(frmsapi.getApiName());
                treeChildren.setIsLast(true);
                treeChildren.setParentId(treeResponse.getId());
                treeChildren.setLevel("2");
                treeChildren.setCheckArr(jsonObject1.toString());
                children.add(treeChildren);
            }
            treeResponse.setChildren(children.toString());
            data.add(treeResponse);

        }

        return data.toString();
    }
}
