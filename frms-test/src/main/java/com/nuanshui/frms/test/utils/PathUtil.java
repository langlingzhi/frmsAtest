package com.nuanshui.frms.test.utils;

/**
 * 获取路径方法
 * @author Administrator
 *
 */
public class PathUtil {
	
	/**
	 * 获取web项目访问路径
	 * 使用该方法是必须在web.xml中配置了监听  org.springframework.web.context.request.RequestContextListener  
	 * @return
	 */
//	public static String getWebPath(){
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";  
//		return basePath;
//	}
	
	/**
	 * 获取web项目classes路径
	 * @return
	 */
	public static String getClassesPath(){
		return Thread.currentThread().getContextClassLoader().getResource("").getPath();
	}
	
	public static void main(String[] args) {

//		System.out.println(PathUtil.getClassesPath().replaceAll("/classes/", ""));
//		System.out.println(1 * 1.0 / 40 );
	}
}
