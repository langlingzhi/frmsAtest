package com.nuanshui.frms.test.aop;

import com.nuanshui.frms.test.config.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * data source aop
 */
@Aspect
@Component
@Slf4j
public class DataSourceAop {

    public DataSourceAop() {
        log.debug("aspect init");
    }

    @Before("    execution(* com.nuanshui.frms.test.service..*.find*(..))" +
            " or execution(* com.nuanshui.frms.test.service..*.get*(..))" +
            " or execution(* com.nuanshui.frms.test.service..*.search*(..))" +
            " or execution(* com.nuanshui.frms.test.service..*.select*(..))" +
            " or execution(* com.nuanshui.frms.test.service..*.count*(..))" +
            " or execution(* com.nuanshui.frms.test.service..*.query*(..))" +
            " or execution(* com.nuanshui.frms.test.service..*.load*(..))" +
            "")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        log.debug("dataSource切换到：Read");
    }

    @Before("    execution(* com.nuanshui.frms.test.service..*.insert*(..))" +
            " or execution(* com.nuanshui.frms.test.service..*.update*(..)) " +
            " or execution(* com.nuanshui.frms.test.service..*.save*(..)) " +
            " or execution(* com.nuanshui.frms.test.service..*.delete*(..)) " +
            " or execution(* com.nuanshui.frms.test.service..*.create*(..)) ")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        log.debug("dataSource切换到：write");
    }

    /**
     * app前端数据源
     */
    @Before("    execution(* com.nuanshui.frms.test.appservice..*.find*(..))" +
            " or execution(* com.nuanshui.frms.test.appservice..*.get*(..))" +
            " or execution(* com.nuanshui.frms.test.appservice..*.search*(..))" +
            " or execution(* com.nuanshui.frms.test.appservice..*.select*(..))" +
            " or execution(* com.nuanshui.frms.test.appservice..*.query*(..))" +
            " or execution(* com.nuanshui.frms.test.appservice..*.load*(..))" +
            "" )
    public void setAppDataSourceType() {
        DataSourceContextHolder.app();
        log.debug("dataSource切换到：App");
    }

    /**
     * cs前端数据源
     */
    @Before("    execution(* com.nuanshui.frms.test.csservice..*.insert*(..))" +
            " or execution(* com.nuanshui.frms.test.csservice..*.update*(..))" +
            " or execution(* com.nuanshui.frms.test.csservice..*.delete*(..))" +
            " or execution(* com.nuanshui.frms.test.csservice..*.select*(..))" +
            " or execution(* com.nuanshui.frms.test.csservice..*.query*(..))" +
            " or execution(* com.nuanshui.frms.test.csservice..*.load*(..))" +
            "" )
    public void setCSDataSourceType() {
        DataSourceContextHolder.cs();
        log.debug("dataSource切换到：cs");
    }

}
