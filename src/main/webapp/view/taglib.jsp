<%@page import="com.ryt.web.common.PropertiesManager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value='<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/" %>'/>
<link rel="icon" href="${ctx}assets/jquery-easyui-1.4.3/themes/default/images/icon-small.png" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="${ctx}assets/jquery-easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}assets/jquery-easyui-1.4.3/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${ctx}assets/css/easyui.icon.css" />
<link rel="stylesheet" type="text/css" href="${ctx}assets/css/style.css" />
<script type="text/javascript">var ctx = '${ctx}';</script>
<script type="text/javascript" src="${ctx}assets/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}assets/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}assets/js/myLoad.js"></script>
<script type="text/javascript" src="${ctx}assets/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}assets/jquery.js/jquery.json.js"></script>
<script type="text/javascript" src="${ctx}assets/jquery.js/jquery.fw.ext.js"></script>
<script type="text/javascript" src="${ctx}assets/easyui.js/easyui.ext.js"></script>
<script type="text/javascript" src="${ctx}assets/easyui.js/easyui.validate.js"></script>
<script type="text/javascript" src="${ctx}assets/js/menu.js"></script>
<script type="text/javascript" src="${ctx}assets/js/message.js"></script>
<script type="text/javascript" src="${ctx}assets/js/sysClassTypeId.js"></script>
<script type="text/javascript" src="${ctx}assets/js/common.min.js"></script>
<script type="text/javascript" src="${ctx}assets/js/MD5.js"></script>
<script type="text/javascript" src="${ctx}assets/js/plugin/RoleSelect.js"></script>
<script type="text/javascript" src="${ctx}assets/js/jquery.zclip.min.js"></script>
<script type="text/javascript" src="${ctx}assets/js/moment.js"></script>
<script type="text/javascript" src="${ctx}assets/js/zh-cn.js"></script>
<script type="text/javascript" src="${ctx}videoplayer/flowplayer-3.2.8.min.js"></script>
<script src="${ctx}assets/js/fusioncharts.js" type="text/javascript"></script>
<script src="${ctx}assets/js/themes/fusioncharts.theme.fint.js" type="text/javascript"></script>


<script type="text/javascript" src="${ctx}assets/qnupload/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="${ctx}assets/qnupload/js/ui.js"></script>
<script type="text/javascript" src="${ctx}assets/qnupload/js/qiniu.js"></script>
