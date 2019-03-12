package com.nuanshui.frms.test.controller;

import com.nuanshui.frms.test.config.FreeMarkerViewInterceptor;
import com.nuanshui.frms.test.entity.Example;
import com.nuanshui.frms.test.entity.cs.system.FrmsUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 信息页面
 * Created by xingshi on 2017/09/27.
 */
@Controller
@Slf4j
public class MainController {
    @Value("${spring.application.name}")
    private String projectName;
    /**
     * Mapped URL prefix for Angular templates
     */
    private static final String TEMPLATES_URL_PREFIX = "/templates";

    /**
     * 返回首页的配置
     */
//    private static final String INDEX = "index";
    private static final String INDEX = "index";

    /**
     * URL pattern for Angular templates
     */
    private static final String TEMPLATES_URL_PATTERN = TEMPLATES_URL_PREFIX + "/**";

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=UTF-8");
        resolver.setExposeRequestAttributes(false);
        resolver.setExposeSessionAttributes(false);
        resolver.setExposeSpringMacroHelpers(true);
        resolver.setOrder(0);
        resolver.setRequestContextAttribute("request");
        resolver.setViewClass(FreeMarkerViewInterceptor.class);
        resolver.setCache(false);
        log.info("初始化 freeMarkerViewResolver ");
        return resolver;
    }

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String info() {
        System.out.println("llz");
        return projectName;
    }

    public List<Example> findByName(@PathVariable("name") String name) {
        return null;
    }

    @RequestMapping({"/tologin"})
    public String tologin(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        return "/login";
    }

    @RequestMapping({"/"})
    public String displayHomepage(HttpServletRequest request, HttpServletResponse response, Model model)
            throws IOException
    {
        if(request.getSession().getAttribute("userInfo")!= null){
            FrmsUser frmsUser = (FrmsUser) request.getSession().getAttribute("userInfo");
            model.addAttribute("frmsUser", frmsUser);
            return "/index_new";
        }
        else {
            return "/login";
        }
    }
    @RequestMapping("/mainPage")
    public String helloFtl(Model model) {
        model.addAttribute("hello","Hello FreeMarker");
        log.info("hello","Hello FreeMarker");
        return "/main";
    }
    public List<Example> frmsmain(@PathVariable("name") String name) {
        return null;
    }

    @RequestMapping({"/index"})
    public String index(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        log.info("------index");
        return "/index_new";
    }

    @RequestMapping({ TEMPLATES_URL_PATTERN })
    public String fetchTemplate(HttpServletRequest request, Locale locale) {
        String uri = request.getRequestURI();
        return uri.substring(uri.indexOf(TEMPLATES_URL_PREFIX));
    }
}