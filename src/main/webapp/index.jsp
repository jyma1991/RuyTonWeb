<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="com.ryt.web.entity.user.User"%>
<%@page import="org.durcframework.core.UserContext"%>
<%
	if (UserContext.getInstance().getUser() == null) {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		return;
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>政教通后台管理系统</title>
 <jsp:include page="view/taglib.jsp"></jsp:include>
<script>
	//获取当前时间
	function tick(){
		$("#year").html('今天是'+moment().format('YYYY年MM月DD日'));
		$("#week").html(moment().format('dddd'));
		$("#time").html(moment().format('H:mm'));
		window.setTimeout("tick()", 1000);
	}
	tick();	
	
	logoutFun = function() {
		$.ajax({
			type : "POST",
			url : ctx + 'logout.do',
			dataType : 'json',
			success : function(result) {
				if (result.success) {
					location.href = 'login.jsp';
				}
			},
			error : function() {
				location.href = 'login.jsp';
			}
		});
	}
	
	updatePswd = function() {

		$("#dd").dialog('open');
		
	}
	
	submit = function (){
		var oldpassword = $.trim($('#oldpassword').val());
		var newpassword = $.trim($('#newpassword').val());
		var cnewpassword = $.trim($('#cnewpassword').val());
		var userName =
			"<%if(session.getAttribute("S_KEY_USER")!=null)
				{
					User value1 =(User) session.getAttribute("S_KEY_USER");
					out.print(value1.getUsername());
				}
				%>";
		var Id =
			"<%if(session.getAttribute("S_KEY_USER")!=null)
				{
					User value1 =(User) session.getAttribute("S_KEY_USER");
					out.print(value1.getId());
				}
				%>";

		if(cnewpassword!==newpassword){
			 $.messager.show({
                 title : message.title.normal,
                 msg : result.message,
                 timeout : message.timeout,
                 showType : message.showType
             });
			//alert("新密码不一致，请重新设置");
		}
		else
		{
			var pwd = {id:Id,userName: userName, userPwd: faultylabs.MD5(oldpassword), newpassword: faultylabs.MD5(newpassword)}
			$.ajax({
				type : "POST",
				data: pwd,
				url : ctx + 'updatePswd.do',
				dataType : 'json',
				success : function(result) {
					alert(result);
				},
				error : function() {
					alert("信息传递有误");
				}
			});
			$("#dd").dialog('close');
		}
		
	}
</script>
<style>
.header_self{
	height:80px;
	background:-ms-linear-gradient(left,rgb(96, 182, 48), rgb(1, 168, 58))!important;
	background:-webkit-linear-gradient(left,rgb(96, 182, 48), rgb(1, 168, 58))!important;
	background:-moz-linear-gradient(left,rgb(96, 182, 48), rgb(1, 168, 58))!important;	
}
.panel-header{ 
	background:-ms-linear-gradient(top,rgb(130, 218, 81),rgb(21, 171, 57))!important;
	background:-webkit-linear-gradient(top,rgb(130, 218, 81),rgb(21, 171, 57))!important;
	background:-moz-linear-gradient(top,rgb(130, 218, 81),rgb(21, 171, 57))!important;
	height:36px;
	padding:0;
	border:0px solid red;
}
.panel-title {
    font-size: 14px;
    font-weight: bold;
    color: hsl(0, 0%, 100%);
    height: 36px;
    line-height: 36px;
    text-align: center;
}
.tabs li a.tabs-inner {
margin-top:6px ;
border-color:#E6E6E6;
}
.tabs li.tabs-selected a.tabs-inner{
border-color:#E6E6E6;
}
</style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">

	<div class="north north_self" data-options="region:'north',border:false" style="min-width:960px" >
		<div class="header header_self" >
			<div class="logo" style="margin-left:20px">
			<div style="float:left"><img alt="" src="${ctx}assets/jquery-easyui-1.4.3/themes/default/images/icon2.png" height="60"/></div>
			<div style="float:left; margin-left:100px; margin-top:22px;font-size:12px"class="self_time"><span id="year"></span> <span id="week"></span> <span id="time"></span></div>
			</div>
			<div  style="padding-top:30px;font-size:12px;"class="logout">
			欢迎您，<% out.print(UserContext.getInstance().getUser().getUsername());%>
				| <a  style="font-size:12px;"href="javascript:void(0)" onclick="updatePswd(); return false;">修改密码</a>
				| <a style="font-size:12px;" href="javascript:void(0)" onclick="logoutFun()">退出系统&nbsp;&nbsp;</a>
			</div>
			<div style="float:right;padding-top:7px;" class="index_headicon"><img alt="" src="${ctx}assets/jquery-easyui-1.4.3/themes/default/images/loginImg.png"/></div>
		</div>
	</div>
	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false,closable:false">
			<div id="indexContent" title="首页" style="width:80%; margin:0px auto;">
				<img src="${ctx}assets/jquery-easyui-1.4.3/themes/default/images/bgIndex.png" style="width:100%;position: absolute; bottom:0px"/>
				<img src="${ctx}assets/jquery-easyui-1.4.3/themes/default/images/bgIndexicon.png" style="width:20%;position: absolute;right:15px;bottom:10px"/>
				<div id="userStatistics" style="width:30%; margin-top:50px; float:left; margin-left:3%;"></div>
				<div id="deviceStatistics" style="width:60%; margin-top:50px; float:left; margin-left:6%;"></div>
				<div id="articleStatistics" style="width:30%; margin-top:50px; float:left; margin-left:3%;"></div>
				<div id="subSysClassStatistics" style="width:30%; margin-top:50px; float:left; margin-left:3%;"></div>
				<div id="parentsCircleStatistics" style="width:30%; margin-top:50px; float:left; margin-left:3%;"></div>
				<div id="agentFinanceOrderStatistics" style="width:30%; margin-top:50px; float:left; margin-left:3%;"></div>
			</div>
		</div>
	</div>
	<div class="west" data-options="region:'west',split:true,title:'系统菜单',collapsible:false" style="background-color:rgb(249, 249, 249)">
	<ul id="tree"></ul>
	</div>
	<div class="south" data-options="region:'south'" style="background-color:rgb(249, 249, 249)">
		<div class="copyright">Copyright © 睿眼通（上海）信息科技有限公司 版权所有</div>
	</div>

	<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
		<div name="close">关闭</div>
		<div name="Other">关闭其他</div>
		<div name="All">关闭所有</div>
	</div>
	
    <div id="dd" class="easyui-dialog" closed = "true" style="padding:5px;width:400px;height:200px;"
			title="修改密码" 
			toolbar="#dlg-toolbar" buttons="#dlg-buttons">
			
		<div style="text-align:center; padding:6px 0px;" >请输入旧密码：<input id = "oldpassword" value="" type="password"></input></div>
		<div style="text-align:center;">请输入新密码：<input id = "newpassword" value="" type="password"></input></div>
		<div style="text-align:center; padding:6px 0px;">请确认新密码：<input id = "cnewpassword" value="" type="password"></input></div>	
		<div id="dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="submit()">确认</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dd').dialog('close')">取消</a>
		</div>	
	</div>
	<script>
		$(function(){
			$.ajax({
				url:ctx+'getUserStatistics.do',
				type:'post',
				data:{},
				success:function(data){
					if(data.success){
						if(data.roleName){
							var userId = data.userId;
							var jsondataBefore = '{' +'"chart":{';
							var jsondataCaption = '"caption":"用户统计",';
							var yAxisMaxValue = '"yAxisMaxValue":"'+(data.parentNum*3==0? 10:data.parentNum*3) +'",';
							//单系列图
							var jsondataAfterLess = '"xAxisName":"",' +
				                '"yAxisName":""' +',"bgcolor": ""'+
				                ',"palettecolors": "e44a00"'+
				                ',"theme": "fint"'+
				                ',"showborder":"1"'+
				                ',"yAxisMinValue":"0"'+
				                ',"numberScaleValue":"1000,1000000"'+
				                ',"numberScaleUnit":"K,W"'+
				                ',"showLegend":"1"'+
				                ',"rotateValues":"0"'+//值旋转
				                ',"rotateNames":"1"'+
				                ',"numDivLines":"5"'+
				                //',"anchorRadius":"5"'+//折线图描点直径
				                ',"useRoundEdges":"1"'+//对2d柱形图和3D饼图使用圆角和玻璃效果填充
				                ',"showPlotBorder":"0"'+//是否显示（柱形，折现，饼图...）的边框
				                //',"outCnvbaseFontColor":"ff0000"'+//画布以外部分区域字体颜色
					            //',"divLineIsDashed":"1"'+//虚线效果
					            '},"data":[]' +
					        '}';
							//获取设备分布的map
							var agentDeviceMap = data.agentDeviceMap;
							var morePictureNum = 0; 
							for(var k in agentDeviceMap) {
								for(var j = 0; j<2; j++){
									morePictureNum += agentDeviceMap[k][j];
								}
							}
							//多系列图
							var jsondataAfterMore = '"xAxisName":"",' +
				                '"yAxisName":""' +',"bgcolor": ""'+
				                ',"palettecolors": "e44a00"'+
				                ',"theme": "fint"'+
				                ',"showborder":"1"'+
				                ',"showvalues":"1"'+//显示值
				                ',"yAxisMinValue":"0"'+
				                ',"yAxisMaxValue":"'+morePictureNum +'"'+
				                ',"numberScaleValue":"1000,1000000"'+
				                ',"numberScaleUnit":"K,W"'+
				                ',"showLegend":"1"'+
				                ',"rotateNames":"1"'+
				                //',"anchorRadius":"5"'+//折线图描点直径
				                ',"useRoundEdges":"1"'+//对2d柱形图和3D饼图使用圆角和玻璃效果填充
				                ',"showPlotBorder":"0"'+//是否显示（柱形，折现，饼图...）的边框
				                //',"outCnvbaseFontColor":"ff0000"'+//画布以外部分区域字体颜色
					            //',"divLineIsDashed":"1"'+//虚线效果
					            '},"categories":"","dataset":[]' +
					        '}';
							
					        var colorArray = ["76A5DB","B53A3A","FEEA65","0D8080","F1AB7F","8DB127",,"FFA33B","D1A00D","D111B6","E20D79","FE791E","CBF47E","8612BF","005AEC","FFF48B"];
							var jsondata = jsondataBefore + jsondataCaption + yAxisMaxValue + jsondataAfterLess;
					        var jsonobj = JSON.parse(jsondata);
							//管理员登录
							if(data.roleName == 'admin'){
								var elemSet = {"label":'代理商',"value":data.angentNum,"color":colorArray[0],"link":"j-locationNumber-1"};
								jsonobj.data.push(elemSet);
								elemSet = {"label":'幼儿园',"value":data.schoolNum,"color":colorArray[1],"link":"j-locationNumber-2"};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"教师","value":data.teacherNum,"color":colorArray[2],"link":"j-locationNumber-3"};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"学生","value":data.studentNum,"color":colorArray[3],"link":"j-locationNumber-4"};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"家长","value":data.parentNum,"color":colorArray[4],"link":"j-locationNumber-5"};
								jsonobj.data.push(elemSet);
								/*elemSet = {"label":"2013","value":474461,"color":"D1A00D"};
								jsonobj.data.push(elemSet);*/
								
								var prefix = "";//./component/fusioncharts/
								// 生成图表的id用时间标识，解决刷新页面时，报图表id已存在js错误
								var chart = new FusionCharts("Column3d.swf","jsonobj","100%",300,0,1);
								// 此处若报js错误：setJSONData，很有可能是fusioncharts.js版本有问题，
								// 可先使用官网试用版进行测试
								chart.setJSONData(jsonobj);
								chart.render("userStatistics");
								//代理商设备统计
								jsondataCaption = '"caption":"代理商设备统计",';
								jsondata = jsondataBefore + jsondataCaption +jsondataAfterMore;
								jsonobj = JSON.parse(jsondata);
								var agentNum = 0;
								//category数组
								var categoryDataArray = new Array();
								var elemCategory = null;
								var agentMap = data.agentDeviceMap;
								var count = 0;
								for(var k in agentMap) {
									agentNum++;
									elemCategory = {"label":k};
									categoryDataArray.push(elemCategory);
								}
								//代理商管理的设备分为公共场地以及教室
								var className = ["教室","公共场地"];
								for(var i = 0 ; i<2; i++) {
									var dataItem = null;
									elemSet ={seriesName: className[i],set: []};
									for(var k in agentMap){
										dataItem = {"value":agentMap[k][i],"color":colorArray[i],"link":"j-locationNumber-6"};
										elemSet.set.push(dataItem);
									}
									jsonobj.dataset.push(elemSet);
								}
								jsonobj.categories = {"category":categoryDataArray};
								//格式化代理商设备统计div
								if(agentNum <= 5){
									$("#deviceStatistics").css({
										"width":"30%",
										"margin-left":"3%"
									});
								}else if(agentNum <= 10){
									$("#deviceStatistics").css({
										"width":"63%",
										"margin-left":"3%"
									});
								}else{
									$("#deviceStatistics").css({
										"width":"94%",
										"margin-left":"3%"
									});
								}
								// 生成图表的id用时间标识，解决刷新页面时，报图表id已存在js错误
								var chart = new FusionCharts("MSColumn3D.swf","jsonobj1","100%",300,0,1);
								// 此处若报js错误：setJSONData，很有可能是fusioncharts.js版本有问题，
								// 可先使用官网试用版进行测试
								chart.setJSONData(jsonobj);
								chart.render("deviceStatistics");//articleStatistics
								
								//育儿百科的分布
								jsondataCaption = '"caption":"育儿百科统计",';
								yAxisMaxValue = '"yAxisMaxValue":"'+(data.articlesNum <= data.subSysClassesNum ? data.subSysClassesNum*2 :data.articlesNum*2) +'",'
								jsondata = jsondataBefore + jsondataCaption + yAxisMaxValue + jsondataAfterLess;
								jsonobj = JSON.parse(jsondata);
								var elemSet = {"label":"主类别","value":data.mainSysClassTypeNum,"color":colorArray[0],"link":"j-locationNumber-7"};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"子类别","value":data.subSysClassesNum,"color":colorArray[1],"link":"j-locationNumber-7"};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"文章","value":data.articlesNum,"color":colorArray[2],"link":"j-locationNumber-7"};
								jsonobj.data.push(elemSet);
								var prefix = "";//./component/fusioncharts/
								// 生成图表的id用时间标识，解决刷新页面时，报图表id已存在js错误
								var chart = new FusionCharts("Column3d.swf","jsonobj2","100%",300,0,1);
								// 此处若报js错误：setJSONData，很有可能是fusioncharts.js版本有问题，
								// 可先使用官网试用版进行测试
								$("#articleStatistics").css({
									"width":"30%",
									"margin-left":"3%"
								});
								chart.setJSONData(jsonobj);
								chart.render("articleStatistics");
								
								//育儿百科文章的分布
								jsondataCaption = '"caption":"育儿百科文章数量统计",';
								yAxisMaxValue = '"yAxisMaxValue":"'+(data.articlesNum == 0 ? 10 :data.articlesNum/3) +'",'
								jsondata = jsondataBefore + jsondataCaption + yAxisMaxValue + jsondataAfterLess;
								jsonobj = JSON.parse(jsondata);
								var sybSysClassesMapIndex = 0;
								for(var k in data.sybSysClassesMap){
									var colorIndex = sybSysClassesMapIndex%16;
									elemSet = {"label":k,"value":data.sybSysClassesMap[k],"color":colorArray[colorIndex],"link":"j-locationNumber-7"};
									jsonobj.data.push(elemSet);
									sybSysClassesMapIndex ++;
								}
								var prefix = "";//./component/fusioncharts/
								// 生成图表的id用时间标识，解决刷新页面时，报图表id已存在js错误
								var chart = new FusionCharts("Column3d.swf","jsonobj3","100%",300,0,1);
								// 此处若报js错误：setJSONData，很有可能是fusioncharts.js版本有问题，
								// 可先使用官网试用版进行测试
								$("#subSysClassStatistics").css({
									"width":"30%",
									"margin-left":"3%"
								});
								chart.setJSONData(jsonobj);
								//格式化代理商设备统计div
								if(sybSysClassesMapIndex <= 8){
									$("#subSysClassStatistics").css({
										"width":"30%",
										"margin-left":"3%"
									});
								}else if(sybSysClassesMapIndex <= 18){
									$("#subSysClassStatistics").css({
										"width":"63%",
										"margin-left":"3%"
									});
								}else{
									$("#subSysClassStatistics").css({
										"width":"94%",
										"margin-left":"3%"
									});
								}
								chart.render("subSysClassStatistics");
								
								//家长圈统计
								jsondataCaption = '"caption":"家长圈统计",';
								var parentMaxVal = (data.parentArticlesNum + data.parentArticleReplyNums + data.schoolArticlesNum + data.schoolArticleReplyNums)*2;
								parentMaxVal = parentMaxVal%5 == 0 ? parentMaxVal : parentMaxVal-parentMaxVal%5;
								yAxisMaxValue = '"yAxisMaxValue":"'+parentMaxVal+'",'
								jsondata = jsondataBefore + jsondataCaption + yAxisMaxValue + jsondataAfterLess;
								jsonobj = JSON.parse(jsondata);
								elemSet = {"label":'家长',"value":data.parentArticlesNum,"color":colorArray[1],"link":"j-locationNumber-9"};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"回复家长评论","value":data.parentArticleReplyNums,"color":colorArray[2],"link":"j-locationNumber-9"};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"幼儿园","value":data.schoolArticlesNum,"color":colorArray[3],"link":"j-locationNumber-9"};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"回复幼儿园评论","value":data.schoolArticleReplyNums,"color":colorArray[4],"link":"j-locationNumber-9"};
								jsonobj.data.push(elemSet);
								var prefix = "";//./component/fusioncharts/
								// 生成图表的id用时间标识，解决刷新页面时，报图表id已存在js错误
								var chart = new FusionCharts("Column3d.swf","jsonobj4","100%",300,0,1);
								chart.setJSONData(jsonobj);
								chart.render("parentsCircleStatistics");
								
								//代理商订单数量统计
								jsondataCaption = '"caption":"代理商订单数量统计",';
								/**var parentMaxVal = (data.parentArticlesNum + data.parentArticleReplyNums + data.schoolArticlesNum + data.schoolArticleReplyNums)*2;
								parentMaxVal = parentMaxVal%5 == 0 ? parentMaxVal : parentMaxVal-parentMaxVal%5;
								yAxisMaxValue = '"yAxisMaxValue":"'+parentMaxVal+'",'*/
								jsondata = jsondataBefore + jsondataCaption  + jsondataAfterLess;
								jsonobj = JSON.parse(jsondata);
								var agentFinanceOrderIndex = 0;
								
								for(var k in data.agentFinanceOrdersMap){
									var colorIndex = agentFinanceOrderIndex % 16;
									elemSet = {"label":k,"value":data.agentFinanceOrdersMap[k],"color":colorArray[colorIndex],"link":"j-locationNumber-10"};
									jsonobj.data.push(elemSet);
									agentFinanceOrderIndex ++;
								}
								var prefix = "";//./component/fusioncharts/
								// 生成图表的id用时间标识，解决刷新页面时，报图表id已存在js错误
								var chart = new FusionCharts("Column3d.swf","jsonobj5","100%",300,0,1);
								
								$("#agentFinanceOrderStatistics").css({
									"width":"30%",
									"margin-left":"3%"
								});
								chart.setJSONData(jsonobj);
								//格式化代理商设备统计div
								if(agentFinanceOrderIndex <= 8){
									$("#agentFinanceOrderStatistics").css({
										"width":"30%",
										"margin-left":"3%"
									});
								}else if(agentFinanceOrderIndex <= 18){
									$("#agentFinanceOrderStatistics").css({
										"width":"63%",
										"margin-left":"3%"
									});
								}else{
									$("#agentFinanceOrderStatistics").css({
										"width":"94%",
										"margin-left":"3%"
									});
								}
								chart.render("agentFinanceOrderStatistics");
							//代理商登录	
							}else if(data.roleName == 'agent'){
								var elemSet = {"label":'幼儿园',"value":data.schoolNum,"color":colorArray[0],"link":"j-locationNumber-2,agent,"+userId};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"教师","value":data.teacherNum,"color":colorArray[1],"link":"j-locationNumber-3,agent,"+userId};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"学生","value":data.studentNum,"color":colorArray[2],"link":"j-locationNumber-4,agent,"+userId};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"家长","value":data.parentNum,"color":colorArray[3],"link":"j-locationNumber-5,agent,"+userId};
								jsonobj.data.push(elemSet);
								var prefix = "";//./component/fusioncharts/
								// 生成图表的id用时间标识，解决刷新页面时，报图表id已存在js错误
								var chart = new FusionCharts("Column3d.swf","jsonobj","100%",300,0,1);
								// 此处若报js错误：setJSONData，很有可能是fusioncharts.js版本有问题，
								// 可先使用官网试用版进行测试
								chart.setJSONData(jsonobj);
								chart.render("userStatistics");
								//如果代理商下面有学校
								if(data.schoolDeviceMap){
									var schoolMap = data.schoolDeviceMap;
									//学校设备统计
									jsondataCaption = '"caption":"学校设备统计",';
									jsondata = jsondataBefore + jsondataCaption +jsondataAfterMore;
									jsonobj = JSON.parse(jsondata);
									var schoolNum = 0;
									//category数组
									var categoryDataArray = new Array();
									var elemCategory = null;
									var count = 0;
									for(var k in schoolMap) {
										schoolNum++;
										elemCategory = {"label":k};
										categoryDataArray.push(elemCategory);
									}
									//代理商管理的设备分为公共场地以及教室
									var className = ["教室","公共场地"];
									for(var i = 0 ; i<2; i++) {
										var dataItem = null;
										elemSet ={seriesName: className[i],set: []};
										for(var k in schoolMap){
											dataItem = {"value":schoolMap[k][i],"color":colorArray[i],"link":"j-locationNumber-8,school,"+userId};
											elemSet.set.push(dataItem);
										}
										jsonobj.dataset.push(elemSet);
									}
									jsonobj.categories = {"category":categoryDataArray};
									//格式化代理商设备统计div
									if(schoolNum <= 5){
										$("#deviceStatistics").css({
											"width":"30%",
											"margin-left":"3%"
										});
									}else if(schoolNum <= 10){
										$("#deviceStatistics").css({
											"width":"63%",
											"margin-left":"3%"
										});
									}else{
										$("#deviceStatistics").css({
											"width":"94%",
											"margin-left":"3%"
										});
									}
									// 生成图表的id用时间标识，解决刷新页面时，报图表id已存在js错误
									var chart = new FusionCharts("MSColumn3D.swf","jsonobj1","100%",300,0,1);
									// 此处若报js错误：setJSONData，很有可能是fusioncharts.js版本有问题，
									// 可先使用官网试用版进行测试
									chart.setJSONData(jsonobj);
									chart.render("deviceStatistics");
								}
								
							}else if(data.roleName == 'school'){
								var elemSet = {"label":"教师","value":data.teacherNum,"color":colorArray[0]};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"学生","value":data.studentNum,"color":colorArray[1]};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"家长","value":data.parentNum,"color":colorArray[2]};
								jsonobj.data.push(elemSet);
								var prefix = "";//./component/fusioncharts/
								// 生成图表的id用时间标识，解决刷新页面时，报图表id已存在js错误
								var chart = new FusionCharts("Column3d.swf","jsonobj","100%",300,0,1);
								// 此处若报js错误：setJSONData，很有可能是fusioncharts.js版本有问题，
								// 可先使用官网试用版进行测试
								chart.setJSONData(jsonobj);
								chart.render("userStatistics");
								//学校的设备分布
								jsondataCaption = '"caption":"学校设备统计",';
								yAxisMaxValue = '"yAxisMaxValue":"'+(data.classDeviceNum+data.publicDeviceNum)*1.5+'",';
								jsondata = jsondataBefore + jsondataCaption + yAxisMaxValue + jsondataAfterLess;
								jsonobj = JSON.parse(jsondata);
								var elemSet = {"label":"教室","value":data.classDeviceNum,"color":colorArray[0]};
								jsonobj.data.push(elemSet);
								elemSet = {"label":"公共场地","value":data.publicDeviceNum,"color":colorArray[1]};
								jsonobj.data.push(elemSet);
								var prefix = "";//./component/fusioncharts/
								// 生成图表的id用时间标识，解决刷新页面时，报图表id已存在js错误
								var chart = new FusionCharts("Column3d.swf","jsonobj1","100%",300,0,1);
								// 此处若报js错误：setJSONData，很有可能是fusioncharts.js版本有问题，
								// 可先使用官网试用版进行测试
								//园长默认显示1/3
								$("#deviceStatistics").css({
									"width":"30%",
									"margin-left":"3%"
								});
								chart.setJSONData(jsonobj);
								chart.render("deviceStatistics");
							}
						}
						
					}
				},
				dataType:'json'
			});
		});
		//跳转详细列表页面
		function locationNumber(index){
			var roleName = index.split(",");
			var other ="";
			if(roleName.length > 1){
				other="?roleName=\""+roleName[1]+"\"&userId="+roleName[2];
			}
			var title ="";
			var content = "";
			switch (parseInt(roleName[0])){
				case 1:
					title='代理商详细列表';
					content="<iframe id='mainFrame' scrolling='no' src='"+ctx+"view/jsp/statistic/StatisticAgent.jsp' width='100%' height='99%' allowtransparency='true' frameborder='0'></iframe>";
					break;
				case 2:
					title='幼儿园详细列表';
					content="<iframe id='mainFrame' scrolling='no' src='"+ctx+"view/jsp/statistic/StatisticSchool.jsp"+other+"' width='100%' height='99%' allowtransparency='true' frameborder='0'></iframe>";
					break;
				case 3:
					title='教师详细列表';
					content="<iframe id='mainFrame' scrolling='no' src='"+ctx+"view/jsp/statistic/StatisticTeacher.jsp"+other+"' width='100%' height='99%' allowtransparency='true' frameborder='0'></iframe>";
					break;
				case 4:
					title='学生详细列表';
					content="<iframe id='mainFrame' scrolling='no' src='"+ctx+"view/jsp/statistic/StatisticStudent.jsp"+other+"' width='100%' height='99%' allowtransparency='true' frameborder='0'></iframe>";
					break;
				case 5:
					title="家长详细列表";
					content="<iframe id='mainFrame' scrolling='no' src='"+ctx+"view/jsp/statistic/StatisticParents.jsp"+other+"' width='100%' height='99%' allowtransparency='true' frameborder='0'></iframe>";
					break;
				case 6:
					title="代理商设备详细列表";
					content="<iframe id='mainFrame' scrolling='no' src='"+ctx+"view/jsp/statistic/StatisticAgentDevice.jsp' width='100%' height='99%' allowtransparency='true' frameborder='0'></iframe>";
					break;
				case 7:
					title="育儿百科详细列表";
					content="<iframe id='mainFrame' scrolling='no' src='"+ctx+"view/jsp/article/ArticleClass.jsp?sysClassTypeId=7' width='100%' height='99%' allowtransparency='true' frameborder='0'></iframe>";
					break;
				case 8:
					title="学校设备详细列表";
					content ="<iframe id='mainFrame' scrolling='no' src='"+ctx+"view/jsp/statistic/StatisticSchoolDevice.jsp"+other+"' width='100%' height='99%' allowtransparency='true' frameborder='0'></iframe>";
					break;
				case 9:
					title="家长圈详细列表";
					content ="<iframe id='mainFrame' scrolling='no' src='"+ctx+"view/jsp/statistic/StatisticParentCircle.jsp"+other+"' width='100%' height='99%' allowtransparency='true' frameborder='0'></iframe>";
					break;
				case 10:
					title="代理商订单详细列表";
					content ="<iframe id='mainFrame' scrolling='no' src='"+ctx+"view/jsp/statistic/StatisticAgentFinanceOrder.jsp"+other+"' width='100%' height='99%' allowtransparency='true' frameborder='0'></iframe>";
					break;
				default:
			}
			$("#tabs").tabs('add',{
				title:title,
				closable:true,
				content:content
			});
		}
	</script>
</body>
</html>