<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><sitemesh:write property='title' /></title>
<jsp:include page="view/taglib.jsp"></jsp:include>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
	<div id='loading' style="position: absolute; z-index: 1000; top: 0px; left: 0px; width: 100%; height: 100%; background: #ffffff; text-align: center; padding-top: 20%; font-size: 12px;">页面加载中，请稍后</div>
	<sitemesh:write property='body' />
</body>
</html>