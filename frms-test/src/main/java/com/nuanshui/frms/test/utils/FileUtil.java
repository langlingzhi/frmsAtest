/**
 * autor：张广海
 * 版权所有，抄袭必究
 * 修改日期：2012-8-16
 * 项目：securityManager
 * 文件名：FileUtil.java
 * 通联支付网络服务股份有限公司
 * 功能：
 */
package com.nuanshui.frms.test.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileUtil {

	// 删除文件夹
	// param folderPath 文件夹完整绝对路径
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 如果不存在该目录，则创建
	 * 
	 * @param filepath
	 */
	public static void creatIfNotExsit(String filepath) {
		File file = new File(filepath);
		if (file.isAbsolute() && !file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 判断该目录或者文件是否存在
	 * 
	 * @param filepath
	 * @return
	 */
	public static boolean isExsit(String filepath) {
		File file = new File(filepath);
		if (file.exists())
			return true;
		return false;
	}

	/**
	 * 列出指定目录下的所有文件
	 * 
	 * @param fliepath
	 * @return 日期：Sep 5, 2012
	 */
	public String[] fileList(String fliepath) {
		String[] myList = null;
		try {
			File path = new File(fliepath);// 定义一个File对象
			// 定义一个字符串数组
			myList = path.list();
			for (int i = 0; i < myList.length; i++)// 输出文件列表
			{
				System.out.println(myList[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myList;
	}

	public static boolean saveToFile(String str, String filepath, String encoding) {
		InputStream bis = null;
		try {
			if (encoding == null || "".equals(encoding)) {
				bis = new ByteArrayInputStream(str.getBytes("GBK"));
			} else {
				bis = new ByteArrayInputStream(str.getBytes(encoding));
			}
			OutputStream os = new FileOutputStream(filepath);
			IOUtils.copy(bis, os);
			bis.close();
			os.close();
			System.out.println("保存文件:" + filepath);
			return true;
		} catch (IOException e) {
			System.out.println("保存文件失败:" + filepath);
			e.printStackTrace();
			return false;
		}
	}
	
	public static String readFile(String filePath){
		try {
			String line = null;
			StringBuffer sb = new StringBuffer();
			File file = new File(filePath);
			if(file.isFile() && file.exists()){
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				while((line = br.readLine()) != null){
					sb.append(line);
				}
				br.close();
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String file = System.getProperty("user.dir") + "/src.main.resource/zhang.txt";
		System.out.println("文件:" + file);
		saveToFile("长个国辉", file, null);
	}
	
}
