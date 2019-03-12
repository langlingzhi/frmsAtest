package com.nuanshui.frms.test.utils.http;

import com.aventstack.extentreports.utils.FileUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nuanshui.frms.test.command.example.ReportData;
import com.nuanshui.frms.test.command.example.ReportInfo;
import com.nuanshui.frms.test.entity.cs.FrmsReport;
import com.nuanshui.frms.test.utils.DateUtils;
import com.nuanshui.frms.test.utils.IOUtil;
import io.restassured.response.Response;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

//import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

public class ReportUtils {
    public static void makereport(List<ReportInfo> listInfo) {

        try {
            //”/”代表Web应用的根目录，”./” 代表当前目录,“../”代表上级目录。
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String name = formatter.format(System.currentTimeMillis());

            Map<String, Object> result = new HashMap<String, Object>();
            result.put("testName", "风控");
            result.put("testPass", "11");
            result.put("testFail", "22");
            result.put("testSkip", "0");
            result.put("testAll", "33");

            result.put("beginTime", formatter.format(System.currentTimeMillis()));
            result.put("totalTime", formatter.format(System.currentTimeMillis()) + "ms");
            result.put("testResult", listInfo);

            String templatePath = "classpath:templates/frmsreport/template";
            String path = "classpath:templates/frmsreport/report.ftl";
            File file =  ResourceUtils.getFile(path);
            File templatefile = ResourceUtils.getFile(templatePath);
            String template = IOUtil.readStringFromFile(templatefile, "UTF-8");

            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            System.out.println(gson.toJson(result));
//            String template = ReportUtils.read(templatePath);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            template = template.replaceFirst("\\$\\{resultData\\}", Matcher.quoteReplacement(gson.toJson(result)));
            System.out.println(template);

//            IOUtil.writeString2Disk(template,path,);
            output.write(template);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makereports(ReportData reportData) {

        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//            String name = formatter.format(System.currentTimeMillis());
//
//            Map<String, Object> result = new HashMap<String, Object>();
//            result.put("reportid", formatter.format(System.currentTimeMillis()));

            String templatePath = "classpath:templates/frmsreport/template";
            String path = "classpath:templates/frmsreport/report.ftl";
            File file =  ResourceUtils.getFile(path);
            String template = IOUtil.read(templatePath);
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            System.out.println(gson.toJson(reportData));
//            String template = ReportUtils.read(templatePath);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            template = template.replaceFirst("\\$\\{resultData\\}", Matcher.quoteReplacement(gson.toJson(reportData)));
            template = template.replaceFirst("��","展" );
            System.out.println(template);

//            IOUtil.writeString2Disk(template,path,);
            output.write(template);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
