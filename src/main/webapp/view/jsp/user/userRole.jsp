<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/view/taglib.jsp"></jsp:include>
<title>角色管理</title>

<body>

	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false">
			<div class="title" style="padding:10px;">
				设置<strong style="color:red">${param.userName}</strong>的角色
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dgRole"></table>
		</div>

		<div data-options="region:'south',border:false">
			<div id="dgRole-buttons" style="text-align: right; padding: 10px;">
				<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="setUserRole(); return false;">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin(); return false;">取消</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
 	var $dgRole;
	var userName = '${param.userName}';
	var uId = '${param.uId}';
	
 	$(function(){
		//获取所有角色
		$.getJSON(ctx+'listAuthRole.do',function(json) {	
			$dgRole = $('#dgRole').datagrid({
				  data : json.rows,
				  columns:[[
				        {field:'roleName',title:'角色',width:100},
				        {field:'id',title:'角色ID',width:100},
				        {field:'ck',width:100,checkbox : true,},			              
				   ]], 
					//获取该用户已有角色
				   onLoadSuccess:function(data){
						//$.getJSON(ctx+'listAllAuthRoleUsers.do',{userNameSch:userName},function(aRjson) {
						$.getJSON(ctx+'listAllAuthRoleUsers.do',{uIdSch:uId},function(aRjson) {
							var rows = aRjson.rows;
							var ind =new Array();
							for (var j = 0, len = rows.length; j < len; j++) {
								for (var i = 0, len = data.rows.length; i < len; i++) {
									if(data.rows[i].id==aRjson.rows[j].roleId){
										$dgRole.datagrid('checkRow', i);
									}
								} 
							}
						});
				   }
			  });
		 });
	});  
	//关闭
	function closeWin() {
		parent.closeDlg()
	} 
	//保存
	//设置角色
	function setUserRole() {
		var rows = $dgRole.datagrid('getSelections');
		var roleIds = [];
		if (rows && rows.length > 0) {
			for (var i = 0, len = rows.length; i < len; i++) {
				roleIds.push(rows[i].id);
			}
			Action.post(ctx + 'setUserRole.do', {
				userName:userName,
				roleIds : roleIds,
				uId : uId
			}, function(e) {
				if (e.success) {
					$.messager.show({
                        title : message.title.normal,
                        msg : message.update_success,
                        timeout : message.timeout,
                        showType : message.showType
                    });
				} else {
					$.messager.show({
                        title : message.title.normal,
                        msg : message.update_fail,
                        timeout : message.timeout,
                        showType : message.showType
                    });
				}
			});
		} else {
			MsgUtil.topMsg('请选择角色');
		}
	}
	</script>
</body>