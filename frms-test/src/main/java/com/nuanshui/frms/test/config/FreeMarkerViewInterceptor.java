package com.nuanshui.frms.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 */
@Slf4j
public class FreeMarkerViewInterceptor extends FreeMarkerView {

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		int port = request.getServerPort();
		String path = request.getContextPath();
//		String fullbase = scheme + "://" + serverName + ":" + port + path;
//		model.put("fullbase", fullbase);
		model.put("base", request.getContextPath());
		super.exposeHelpers(model, request);
	}
}
