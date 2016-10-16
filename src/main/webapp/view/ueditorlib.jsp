<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value='<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/" %>'/>
<script type="text/javascript" charset="utf-8" src="${ctx}ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctx}ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}assets/easyui.js/easyui.validate.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}assets/plupload/jquery.plupload.queue/css/jquery.plupload.queue.css">   
<script type="text/javascript" src="${ctx}assets/plupload/plupload.full.min.js"></script>   
<script type="text/javascript" src="${ctx}assets/plupload/jquery.plupload.queue/jquery.plupload.queue.js"></script>   
<script type="text/javascript" src="${ctx}assets/plupload/i18n/zh_CN.js"></script>  