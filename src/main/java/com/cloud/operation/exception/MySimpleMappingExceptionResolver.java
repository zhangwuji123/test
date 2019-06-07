package com.cloud.operation.exception;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.LockedAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.cloud.operation.core.utils.ConstantUtil;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.google.gson.Gson;

public class MySimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {
	static final Logger logger = LoggerFactory.getLogger(MySimpleMappingExceptionResolver.class);
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception error) {
		// Expose ModelAndView for chosen error view.
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
				.getHeader("X-Requested-With") != null && request.getHeader(
				"X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			String viewName = determineViewName(error, request);
			if (viewName != null) {// JSP格式返回
				// 如果不是异步请求
				// Apply HTTP status code for error views, if specified.
				// Only apply it if we're processing a top-level request.
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
				return getModelAndView(viewName, error, request);
			} else {// JSON格式返回
				return null;

			}
		} else {
			try {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				Gson gson = new Gson();
				DataVO<Object> vo = new DataVO<Object>();
				vo.setState(ConstantUtil.ResponseState.TIMEOUT);
				String msg = MessageUtil.MSG_OTHER_ERROR;
				if (error != null) {
					if (error instanceof org.apache.shiro.authc.UnknownAccountException) {
						msg = MessageUtil.MSG_UNKNOWN_ACCOUNT;
					} else if (error instanceof org.apache.shiro.authc.IncorrectCredentialsException) {
						msg = MessageUtil.MSG_INCORRECT_CREDENTIALS;
					} else if (error instanceof com.cloud.operation.service.shiro.IncorrectCaptchaException) {
						msg = MessageUtil.MSG_INCORRECT_CAPTCHA;
					} else if (error instanceof org.apache.shiro.authc.DisabledAccountException) {
						msg = MessageUtil.MSG_DISABLED_ACCOUNT;
					} else if (error instanceof org.apache.shiro.authc.AuthenticationException) {
						msg = MessageUtil.MSG_AUTHENTICATION;
					} else if (error instanceof org.apache.shiro.authz.UnauthorizedException) {
						msg = MessageUtil.MSG_UNAUTHORIZED;
					} else if (error instanceof LockedAccountException) {
						msg = MessageUtil.MSG_LOCKED_ACCOUNT;
					} else if (error instanceof com.cloud.operation.service.shiro.IncorrectUserTypeCaptchaException){
						msg = MessageUtil.MSG_USER_TYPE;
					}

				}

				vo.setMessage(msg);
				String ret = gson.toJson(vo);
				out.println(ret);
				out.flush();
				out.close();
			} catch (Exception e1) {
				logger.error("exception resolve error:", e1);
			}
			return null;
		}
	}
}