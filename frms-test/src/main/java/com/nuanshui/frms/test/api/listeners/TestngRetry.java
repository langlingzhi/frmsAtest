package com.nuanshui.frms.test.api.listeners;

import com.nuanshui.frms.test.api.exception.ErrorRespStatusException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

//IRetryAnalyzer失败重试需继承接口
public class TestngRetry implements IRetryAnalyzer {
	private static int retryCount = 1;
	private static int maxRetryCount = 2;

	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (result.getThrowable() instanceof ErrorRespStatusException && retryCount % maxRetryCount != 0) {
//			String message = "running retry for  '" + result.getName()
//					+ "' on class " + this.getClass().getName() + " Retrying "
//					+ retryCount + " times";
			Reporter.setCurrentTestResult(result);//当前的测试结果
			
			Reporter.log("RunCount=" + (retryCount + 1));
			retryCount++;
			return true;
		} else {
			resetRetryCount();
			return false;
		}
	}

	public static void resetRetryCount() {
		retryCount = 1;
	}
}

