package com.nuanshui.frms.test.utils;

import com.github.pagehelper.util.StringUtil;
import org.testng.Reporter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

public class ReportUtil {
    public static String getExceptionAllinformation(Error ex) {
        String sOut = "";
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
    }

    public static String toString_02(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

}

