<%@page import="com.ryt.web.entity.user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>政教通·数据支撑管理平台</title>
<jsp:include page="/view/taglib.jsp"></jsp:include>
<jsp:include page="/view/ueditorlib.jsp"></jsp:include>
<script type="text/javascript">
		
		$(function(){
			$('.login_uname :input').keyup(function(event) {
				if (event.keyCode == 13) {
					loginFun();
				}
			});
			
		});
		function loginFun() {
			$('#loginBtn').linkbutton('disable');
			var password = $.trim($('#pwd').val());
			var username = $.trim($('#loginname').val());
			var data = {userPwd:faultylabs.MD5(password),userName:username};

			$.ajax({
			  type: 'POST',
			  url: 'login.do?userName='+ username+'&userPwd='+faultylabs.MD5(password),
			  success: function(result){
				  if (result.success) {
						location.replace('index.jsp');
					} else {
						
						 $.messager.show({
			                 title : message.title.normal,
			                 msg : result.message,
			                 timeout : message.timeout,
			                 showType : message.showType
			             });

					}
			  },
			  dataType: 'json'
			});
		}
</script>
<style>
body{
background-color:#269746;
background-image: radial-gradient(40em circle at 50% 25%, #4EDE67, #269746);
background-image: -ms-radial-gradient(40em circle at 50% 25%, #4EDE67, #269746);
background-image: -moz-radial-gradient(40em circle at 50% 25%, #4EDE67, #269746); 
background-image: -webkit-radial-gradient(40em circle at 50% 25%, #4EDE67, #269746);
/* filter:alpha(opacity=100 finishopacity=0 style=1 startx=0,starty=5,finishx=90,finishy=60);
progid:DXImageTransform.Microsoft.gradient(startcolorstr=red,endcolorstr=blue,gradientType=0); */
}
img{
cursor: auto;
}
.bg_dow{
background-image: -ms-radial-gradient(10em 1em ellipse at 50% 50%, rgba(88, 88, 88, 0.38), rgba(25, 128, 35, 0));
background-image: -moz-radial-gradient(10em 11em ellipse at 50% 50%, rgba(88, 88, 88, 0.38), rgba(25, 128, 35, 0)); 
background-image: -webkit-radial-gradient(10sem 1em ellipse at 50% 50%, #52cd20, rgba(88, 88, 88, 0.38), rgba(25, 128, 35, 0)); 
}
.login_tab{
	height:90px;
	width:660px;
	margin:0 auto;
	box-shadow: 2px 2px 2px #676767;
	margin-top:6%;
	border-radius: 7px;
}
input.loginname{
	width:210px;
	height:30px;
	margin-top:27px;
	margin-left:100px;
	padding-left:5px;
	border-radius:5px;
	border:1px solid #21A71D;
}
input.pwd{
	width:210px;
	height:30px;
	margin-top:27px;
	margin-left:5px;
	padding-left:5px;
	border-radius:5px;
	border:1px solid #21A71D;
}
.login_uname img.longinIcon{
	position: absolute;
    margin-top: -15px;
    margin-left:30px;
}
button.login_btn{
    position: absolute;
	padding:0;
	margin-top:28px;
	margin-left:10px;
	border-radius:2px;
	border:1px solid #DCE2DC;
	cursor: pointer;
}

</style>
</head>

<body class="easyui-layout" data-options="fit:true,border:false" >
	<img src="${ctx}assets/jquery-easyui-1.4.3/themes/default/images/loginBg.png"style="position: absolute;z-index:-100" />
	<div class="longin_title" style="margin-top:13%;text-align:center" >
		<img src="${ctx}assets/jquery-easyui-1.4.3/themes/default/images/loginTitle.png"/>
	</div>
 	<div class="login_from">
			<div class="login_tab" >		
				<div class="login_uname">
					<img src="${ctx}assets/jquery-easyui-1.4.3/themes/default/images/loginFrom.png" style="position: absolute;width:660px;height:90px;z-index:-100"/>
					<img class="longinIcon" src="${ctx}assets/jquery-easyui-1.4.3/themes/default/images/longinIcon.png" />
					<input class="loginname" id="loginname" name="loginname" placeholder="用户名"/>
					<input class="pwd" id="pwd" name="pwd" type="password"  placeholder="密码"/>
					<button onclick="loginFun()" class="login_btn"><img class="loginBtn" src="${ctx}assets/jquery-easyui-1.4.3/themes/default/images/loginBtn.png"/>
					</button>
				</div>
			</div>
		
	</div> 


	
</body>
</html>