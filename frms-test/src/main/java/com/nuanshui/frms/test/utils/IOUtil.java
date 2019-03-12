package com.nuanshui.frms.test.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class IOUtil {
	private final static Logger logger = LoggerFactory.getLogger(IOUtil.class);
	
	private static final String DEFAULT_CHARSET = "utf-8";

	public static void writeString2Disk(String content, String filePath,
			String fileName) {
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		BufferedWriter writer = null;
		try {
			File xml = new File(filePath + fileName);
			writer = new BufferedWriter(new FileWriter(xml));
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			logger.error(MarkerFactory.getMarker("sendMail"),"路径【{}】的文件【{}】写入失败", filePath, fileName);
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * <b>从文本文件读取内容</b>
	 * 
	 * @param file
	 * @return
	 */
	public static String readStringFromFile(File file, String Charset) {
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(is, Charset));
			String line = "";
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (Exception e) {
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public static String readStringFromResourceOnIdea(String fileName, String Charset) {
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = IOUtil.class.getClassLoader().getResourceAsStream(fileName);
			br = new BufferedReader(new InputStreamReader(is, Charset));
			String line = "";
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (Exception e) {
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public static String readStringFromFile(File file) {
		return readStringFromFile(file, DEFAULT_CHARSET);
	}

	/**
	 * <b>输入流由调用者关闭</b>
	 * 
	 * @param is
	 * @return
	 */
	public static String readStringFromIs(InputStream is, String Charset) {
		try {
			InputStreamReader ir = new InputStreamReader(is, Charset);
			BufferedReader br = new BufferedReader(ir);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			ir.close();
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readStringFromIs(InputStream is) {
		return readStringFromIs(is, DEFAULT_CHARSET);
	}

	public static void writeString2Client(OutputStream os, String message,
			String Charset) {
		try {
			byte[] b = message.getBytes(Charset);
			os.write(b);
			os.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void writeString2Client(OutputStream os, String message) {
		writeString2Client(os, message, DEFAULT_CHARSET);
	}

	public static void saveFileFromIs(InputStream is, String filePath) {
		try {
			OutputStream os = new FileOutputStream(new File(filePath));
			byte[] b = new byte[1024];
			int len;
			while ((len = is.read(b)) != -1) {
				os.write(b, 0, len);
			}
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static byte[] readByteFromIs(InputStream is) {
		ByteArrayOutputStream os = null;
		try {
			os = new ByteArrayOutputStream();
			int ch;
			while ((ch = is.read()) != -1) {
				os.write(ch);
			}
			byte b[] = os.toByteArray();
			return b;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static Map<String, String> readProperties(String filePath) {
		InputStream in = null;
		Map<String,String> map = new HashMap<String,String>();
		logger.info("读取配置文件【{}】",filePath);
		try {
			Properties pps = new Properties();
			
			in = new BufferedInputStream(new FileInputStream(PathUtil.getClassesPath() + filePath));
			pps.load(in);
			Enumeration en = pps.propertyNames(); // 得到配置文件的名字
			while (en.hasMoreElements()) {
				String strKey = (String) en.nextElement();
				String strValue = pps.getProperty(strKey);
				logger.info("读取配置文件【{}】的内容【{}】：【{}】",filePath,strKey,strValue);
				map.put(strKey, strValue);
			}
		} catch (Exception ex) {
			logger.error(MarkerFactory.getMarker("sendMail"),"读取配置文件【{}】的内容异常",filePath,ex);
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}

	public static StringBuffer getRequestString(ServletRequest request){
		if(request == null){
			logger.info("reqeust请求为null");
		}
		BufferedReader in = null;
		InputStreamReader is = null;
		String line = null;  
		StringBuffer sb = new StringBuffer();
		try{
			is = new  InputStreamReader(request.getInputStream());
			in = new BufferedReader(is);
			 while ((line = in.readLine()) != null) {   
		         sb.append(line);   
		     }
		}catch(Exception ex){
			logger.error(MarkerFactory.getMarker("sendMail"),"读取输入流异常", ex);
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException ex) {
					logger.error(MarkerFactory.getMarker("sendMail"),"关闭InputStreamReader异常", ex);
				}
			}
			if(in != null){
				try {
					in.close();
				} catch (IOException ex) {
					logger.error(MarkerFactory.getMarker("sendMail"),"关闭BufferedReader异常", ex);
				}
			}
		}
		return sb;
	}
	
	public static StringBuffer getStringByInputStream(ServletInputStream servletInputStream){
		if(servletInputStream == null){
			logger.info("servletInputStream请求为null");
		}
		BufferedReader in = null;
		InputStreamReader is = null;
		String line = null;  
		StringBuffer sb = new StringBuffer();
		try{
			is = new  InputStreamReader(servletInputStream);
			in = new BufferedReader(is);
			 while ((line = in.readLine()) != null) {   
		         sb.append(line);   
		     }
		}catch(Exception ex){
			logger.error(MarkerFactory.getMarker("sendMail"),"读取输入流异常", ex);
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException ex) {
					logger.error(MarkerFactory.getMarker("sendMail"),"关闭InputStreamReader异常", ex);
				}
			}
			if(in != null){
				try {
					in.close();
				} catch (IOException ex) {
					logger.error(MarkerFactory.getMarker("sendMail"),"关闭BufferedReader异常", ex);
				}
			}
		}
		return sb;
	}
	public static String read(String path) {
		File file = null;
		try {
			file = ResourceUtils.getFile(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStream is = null;
		StringBuffer sb = new StringBuffer();
		try {
			is = new FileInputStream(file);
			int index = 0;
			byte[] b = new byte[1024];
			while ((index = is.read(b)) != -1) {
				sb.append(new String(b, 0, index,"UTF-8"));
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
