<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body{font-size: 12px;}
form div{padding: 5px;}
</style>
<title>修改密码</title>
</head>
<body>

<form id="ff" method="post">   
    <div>   
        <label for="name">原密码:</label>   
        <input class="easyui-validatebox" id="oldPswd" type="password" name="oldPswd" data-options="required:true" />   
    </div>   
    <div>   
        <label for="newPswd">新密码:</label>   
        <input class="easyui-validatebox" id="newPswd" type="password" name="newPswd" data-options="required:true,validType:['length[4,16]','notEquals[\'#oldPswd\']']" />   
    </div>   
    <div>   
        <label for="newPswd2">重复新密码:</label>   
        <input class="easyui-validatebox" id="newPswd2" type="password" name="newPswd2" required="required" validType="equals['#newPswd']"  />   
    </div>
    
    <a href="javascript:void(0)" onclick="updatePswd();" class="easyui-linkbutton">修改密码</a>
</form>  

<script type="text/javascript">
var $oldPswd = $('#oldPswd');
var $newPswd = $('#newPswd');
var $newPswd2 = $('#newPswd2');
var $form = $('#ff');

$.extend($.fn.validatebox.defaults.rules, {    
	equals: {    
        validator: function(value, param){    
            return value == $(param[0]).val();    
        },    
        message: '两次密码输入不一致.'   
    }
    ,notEquals: {    
        validator: function(value, param){    
            return value != $(param[0]).val();    
        },    
        message: '新密码不能与原密码一样'
    }
});


function updatePswd(){
	$form.form('submit', {    
	    url:ctx + 'updateUserPassword.do',    
	    onSubmit: function(){
	    	var success = $(this).form('validate');
			if(success){
				var oldPswd = $.trim($oldPswd.val());
				$oldPswd.val(faultylabs.MD5(oldPswd));
				
				var newPswd = $.trim($newPswd.val());
				$newPswd.val(faultylabs.MD5(newPswd));
				
				var newPswd2 = $.trim($newPswd2.val());
				$newPswd2.val(faultylabs.MD5(newPswd2));
			}
			
			return success;
	    },    
	    success:function(resultTxt){
	    	var data = $.parseJSON(resultTxt);
	    	if(data.success){
		        alert('密码修改成功,请重新登录');
		        Globle.logout();
	    	}else{
	    		MsgUtil.topMsg(data.errorMsg);
	    	}
	    	$form.form('clear');
	    }    
	}); 
}
</script>
</body>
</html>