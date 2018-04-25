<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isErrorPage="true"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="com.weilai.common.util.MapUtil"%>
<%@ page import="com.weilai.common.util.JsonUtil"%>
<%@ page import="java.util.*"%>
<%
	Logger logger = Logger.getLogger(MapUtil.class);
	logger.error(exception.getMessage(), exception);
	Map<String, Object> map = MapUtil.error500();
	response.setStatus(HttpServletResponse.SC_OK);
	response.setHeader("Content-type", "application/json;charset=UTF-8");
	response.getWriter().write(JsonUtil.toJson(map));
%>
