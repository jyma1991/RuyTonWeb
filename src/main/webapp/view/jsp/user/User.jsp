<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
<jsp:include page="/view/taglib.jsp"></jsp:include>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
  <div data-options="region:'north',split:true,border:false" style="height:80px;">
    <form id="searchForm">
          <table >
        <tr>
          <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
          <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
          <td><a id="btn_addrole" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">设置角色</a></td>
          <td><a id="btn_delpassword" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">重置密码</a></td> 
          <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
        </tr>
      </table>
    <table>
        <tr>
          <td>真实姓名</td>
          <td><input name="trueNameSch" class="textbox" style="width: 100px; height: 22px;" /></td>
          <td>手机</td>
          <td><input name="mobilePhoneSch" class="textbox" style="width: 100px; height: 22px;" /></td>
          <td>性别</td>
          <td><input id="sexSch" name="sexSch" class="easyui-combobox" style="width: 40px; height: 22px;" /></td>
          <td>角色</td>
          <td><input id="rolePropertySch" name="rolePropertySch" class="easyui-combobox" style="width: 100px; height: 22px;" /></td>     
          <td>创建时间</td>
          <td><input name="createdStartSch"  class="easyui-datebox" style="width: 100px" />
            -
            <input name="createdEndSch" class="easyui-datebox" style="width: 100px" /></td>
          <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',fw.serializeObject($('#searchForm')));">检索</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">清空检索条件</a></td>
        </tr>
      </table>

    </form>
  </div>
  <div id="addRoleDlg" class="easyui-dialog" style="width: 500px; height: 440px;" title="设置角色" data-options="onClose:Globle.clearPanel()" closed="true" modal="true">
	<div id="roleFrame"></div>
	</div>
  <div data-options="region:'center',border:false">
    <table id="grid">
    </table>
  </div>
  <div data-options="region:'east',split:true,border:false" style="width: 60%; padding: 5px;">
  <div id="tt" class="easyui-tabs" data-options="plain:true" style="width: 100%; height: auto">
  	<div title="基本信息" id="frmView">
    <form id="form" name="form" method="post" class="form">
      <fieldset>
        <legend> 基本信息--<span id="operateMode_title">添加模式</span> </legend>
        <table class="table">
          <tbody>
           <tr>
           <th>ID</th>
            <td><input id="id" readonly="readonly" name="id"  value="0"class="textbox" style="height: 22px;width:40px" /></td>
            <th>手机</th>
            <td><input id="mobilePhone" name="mobilePhone" class="textbox easyui-numberbox" required="true" data-options="validType:'mobile'"  style="height: 22px;" /></td>

           </tr>
           <tr>
            <th>用户名</th>
            <td><input id="userName" name="userName" class="textbox easyui-validatebox" required="true"   style="height: 22px;" /></td>
            <th>固定电话</th>
            <td><input id="fixedPhone" name="fixedPhone" class="textbox " data-options="validType:'phone'" style="height: 22px;" /></td>
           	
          </tr>
          <tr>
          	<th>密码</th>
            <td class="pw"><input type="password" id="userPwd" name="userPwd" class="textbox easyui-validatebox" required="true" validType="length[5,1000]"    style="height: 22px;" /></td>
            <th>邮箱</th>
            <td><input id="email" name="email" class="textbox easyui-validatebox" data-options="validType:'email'" style="height: 22px;" /></td>
          
		  </tr>
          <tr>
          	<th>昵称</th>
            <td><input id="nickName" name="nickName" class="textbox easyui-validatebox" required="true" style="height: 22px;" /></td> 
            <th>微信</th>
            <td><input id="weiXin" name="weiXin" class="textbox" style="height: 22px;" /></td>
          	
 		   </tr>
 		   <tr>
			<th>真实姓名</th>
            <td><input id="trueName" name="trueName" class="textbox easyui-validatebox" required="true" style="height: 22px;" /></td> 	
            <th>QQ</th>
            <td><input id="qq"  name="qq" class="textbox easyui-numberbox" data-options="validType:'qq'"style="height: 22px; "/></td>	   
 		   </tr>
 		   <tr>
			<th>性别</th>
            <td><input id="sex" name="sex" value='1' class="textbox easyui-validatebox"  editable="false" style="height: 22px;width:40px" /></td> 	
    		<th>序号</th>
            <td><input id="sortId" name="sortId"  class="textbox easyui-numberspinner"  style="height: 22px;width:40px" /></td>
          </tr>
          <tr>
          	<th>地址</th>
            <td colspan="3"><span style="display: inline-block;width: 30px; text-align:center;">省</span><input id="provinceId"  name="provinceId" class="textbox " editable="false"  style="height: 22px;width:25%;margin-left:5px;" />
            				<span style="display: inline-block;width: 30px; text-align:center;">市</span><input id="cityId"  name="cityId" class="textbox" editable="false" style="height: 22px;width:25%;margin-left:5px" />
            				<span style="display: inline-block;width: 30px; text-align:center;">区</span><input id="districtId" name="districtId" class="textbox" editable="false" style="height: 22px;width:25%;margin-left:5px" />
            </td>
          </tr>
           <tr>
            <th>详细地址</th>
            <td colspan="3"><textarea cols="100" id="addressDetail" name="addressDetail" class="textbox" style="padding-top:3px;height: 22px; width:90%" ></textarea></td>
           </tr>
           <tr>
            <th>备注</th>
            <td colspan="3" ><textarea rows="3"  id="remark" name="remark" class="textbox"style=" width:90%;white-space: pre-wrap;"  wrap="hard" /></textarea></td>
           </tr>
          <tr>
           <th>操作</th>
           <td colspan="3"><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a>&nbsp;<a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td>
          </tr>
            </tbody>
        </table>
      </fieldset>
    </form>
  	</div>
  </div>
  </div>
</div>
<script type="text/javascript">
	//定义省市区comboboxr
	var PS;//省
	var CS;//市
	var DS;//区
	var pwd;
	$(function() {
		var gird;
	    grid=$('#grid').datagrid({
	        fit:true,
	        border : false,
	        collapsible : false,
	        rownumbers : true,
	        pagination : true,
	        singleSelect : true,
	        rownumbers : true,
	        url:ctx+'listUser.do',
	        //url:ctx+'listUser.do?rolePropertySch=0',		
	        columns:[[
						{field:'id',title:'ID',sortable:true},
						{field:'nickName',title:'昵称',sortable:true}
						,{field:'trueName',title:'真实姓名',sortable:true}
						,{field:'userName',title:'用户名',sortable:true}
						,{
                            field: 'sex',
                            title: '姓别',
                            sortable:true,
                            formatter:function(value,row,index){
		            			if(row.sex)	 
                            	{return "男" }
		            			else
		            			{return "女"}
		            		} 
						}
						,{field:'mobilePhone',title:'移动电话',sortable:true}
						//,{field:'roleProperty',title:'test'}
						,{field:'sortId',title:'序号',sortable:true}
						,{field:'createdTime',title:'添加时间',formatter:getDate,sortable:true}
			        ]],
	        onLoadSuccess : function(data) {
	            $('.iconImg').attr('src', fw.pixel_0);
	        },
	        onClickRow : function(index, row) {
	        	$("#form").form("reset");
	            $("#form").form("load", row);
	            $('#sex').combobox('loadData',[{"id":"1","name":"男"},{"id":"0","name":"女"}]);
	            //清除省下拉框的值
	            $.getJSON(ctx+'listAreaProvince.do',{idSch:row.provinceId},function(provinceJson) {
	            	if(provinceJson.rows.length==0){
	            		PS.combobox("clear");
	            	}
	             });
	           //根据cityID获取当前用户的city
	        	$.getJSON(ctx+'listAllAreaCity.do',{idSch:row.cityId},function(cityJson) {
	        		console.log(cityJson);
	        		if(cityJson.rows.length>0){		
		        	 	CS=$('#cityId').combobox({
			        		data :[{id:cityJson.rows[0].id,cityName:cityJson.rows[0].cityName,selected:true}],
			        	    valueField:'id',
			        	    textField:'cityName',  
			        	}) 
	        		}else{	
	        			CS.combobox("clear");
	        			CS.combobox('loadData',{id:1,cityName:" "});
	        		}
				});
	        	//根据districtId获取当前用户的city
	        	$.getJSON(ctx+'listAllAreaDistrict.do',{idSch:row.districtId},function(districJson) {
	        		if(districJson.rows.length>0){	
		        	 	DS=$('#districtId').combobox({
				        	data : [{id:districJson.rows[0].id,districtName:districJson.rows[0].districtName,selected:true}],
				        	valueField:'id',
				        	textField:'districtName'
				        })
	        		}else{
	        			DS.combobox("clear");
	        			DS.combobox('loadData',{id:1,districtName:""});
	        		}
	        	});
	            $("#operateMode_title").html("修改模式");
	            pwd=$("#userPwd").val();
	          	$("#userPwd").val(pwd.substring(0,6));
	            
	        },
	    });
	});
	//
	$('#rolePropertySch').combobox({
        valueField: 'id',
        textField: 'name',
		panelHeight:'auto',  
		data:[{id:"1",name:"家长"},{id:"2",name:"教师"},{id:"4",name:"园长"},{id:"8",name:"代理商"},{id:"0",name:"学生"},{id:"64",name:"系统用户"}]
    });
	//
	$('#sexSch').combobox({
        valueField: 'id',
        textField: 'name',
		panelHeight:'auto',  
		data:[{id:"0",name:"女"},{id:"1",name:"男"}]
    });
	
	//性别
    CSsex = $('#sex').combobox({
        valueField: 'id',
        textField: 'name',
		panelHeight:'auto',  
		data:[{id:"0",name:"女"},{id:"1",name:"男"}]
    });
    //添加操作
    $("#btn_add").click(function() {
        $("#operateMode_title").html("添加模式");
        $('#grid').datagrid("unselectAll");
        $("#id").attr("value", 0);
        $("#form").form("reset");
        DS.combobox('loadData',{id:1,districtName:""});
        CS.combobox('loadData',{id:1,cityName:""});
        $("#userPwd").val("");
    })
    //关闭dialog
	function closeDlg() {
		$('#addRoleDlg').dialog('close');
	}
	//设置角色
	$("#btn_addrole").click(function() {
		var rows = $('#grid').datagrid('getSelections');
		if (rows <= 0) {
	    	$.messager.show({
	        	title : message.title.normal,
	             msg : message.grid_select,
	             timeout : message.timeout,
	            showType : message.showType
	            });
	    }else{
	 		var userName = rows[0].userName;
	 		var uId = rows[0].id;
			$('#roleFrame').html('<iframe src= "userRole.jsp?userName=' +userName+'&uId='+uId+ ' " scrolling="no" frameborder="0" style="width:100%;height:400px;"></iframe>');
			$('#addRoleDlg').dialog('open');	
	    }
	})
	//重置密码
 	$("#btn_delpassword").click(function() {
		var rows = $('#grid').datagrid('getSelections');
		if(rows <= 0){
	    	$.messager.show({
	        	title : message.title.normal,
	            msg : message.grid_select,
	            timeout : message.timeout,
	            showType : message.showType
			});
		}else{
			$.messager.confirm(message.title.askTitle, message.updata_password_message, function(r) {
				if(r){
					var pwd = faultylabs.MD5("123456")
		 			$.post(ctx+"updateUser.do",{userPwd:pwd,id:rows[0].id}, function(result){
						if(JSON.stringify(result).indexOf("true")>0){
							$("#userPwd").val("");
							$.messager.show({
		                        title : message.title.normal,
		                        msg : message.password_start,
		                        timeout : message.timeout,
		                        showType : message.showType
		                    });
							grid.datagrid('reload');
						}else{
		                    $.messager.show({
		                        title : message.title.normal,
		                        msg : message.update_fail,
		                        timeout : message.timeout,
		                        showType : message.showType
		                    });
						}
					})
				}
			});
		}
	}) 
    //删除操作
    $("#btn_dele").click(function() {
        var rows = $('#grid').datagrid('getSelections');
        if (rows <= 0) {
            $.messager.show({
                title : message.title.normal,
                msg : message.grid_select,
                timeout : message.timeout,
                showType : message.showType
            });
        } else {
            $.messager.confirm(message.title.askTitle, message.dele_comfirm, function(r) {
                if (r) {
                    var idValue = $("#form input[name='id']").val();
                    $.post(ctx+"delUser.do", {
                        id : idValue
                    }, function(result) {
                        if (result.success) {
                            $.messager.show({
                                title : message.title.normal,
                                msg : message.dele_success,
                                timeout : message.timeout,
                                showType : message.showType
                            });
                            grid.datagrid('reload');
                        } else {
                            $.messager.show({
                                title : message.title.normal,
                                msg : message.dele_fail,
                                timeout : message.timeout,
                                showType : message.showType
                            });
                        }
                    }, 'json');
                    $("#btn_add").click();
                }
            });
        }
    })
    //确认提交操作
    $("#operateMode_submit").click(function() {	
        var idValue = $("#form input[name='id']").val();
        if ($('#form').form('validate')) {
            if (idValue > 0) {
            	//判断用户是否修改过密码
            	if($("#userPwd").val()!=pwd.substring(0,6)){
            		$('#userPwd').val(faultylabs.MD5($.trim($('#userPwd').val())));
            	}
            	var name=$("#userName").val();
				$.getJSON(ctx+"listAllUser.do",{userNameSch:name,},function(resultUser){
					if(resultUser.rows.length>0){
						var rowid=resultUser.rows[0].id;
					}
					var jsonLength = 0;
					for(var item in resultUser.rows){
						jsonLength++;
					}
					if(rowid==$("#id").val()||jsonLength==0){
		                $.post(ctx+"updateUser.do", fw.serializeObject($('form')), function(result) {
		                    if (result.success) {
		                        $.messager.show({
		                            title : message.title.normal,
		                            msg : message.update_success,
		                            timeout : message.timeout,
		                            showType : message.showType
		                        });
		                        grid.datagrid("reload");
		                        //重新获取form信息内容
		                        $("#form").form("load", ctx+"getUserById.do?id=" + idValue);
		                        setTimeout('$("#userPwd").val($("#userPwd").val().substring(0,6))',100);
		                    }else{
		                    	$.messager.show({
		                            title : message.title.normal,
		                            msg : message.update_fail,
		                            timeout : message.timeout,
		                            showType : message.showType
		                        });
		                    }
		                }, 'json');
						
					}else{
						$.messager.show({
                            title : message.title.normal,
                            msg : message.userName_has,
                            timeout : message.timeout,
                            showType : message.showType
                        });
					};
				});

            } else {
                //添加
                var rname=$("#userName").val();
                $.getJSON(ctx+"listAllUser.do",{userNameSch:rname,},function(resultUser){
                	if(resultUser.rows.length==0){
		                $('#userPwd').val(faultylabs.MD5($.trim($('#userPwd').val())));
		                
		                $.post(ctx+"addUser.do", fw.serializeObject($('form')), function(result) {
		                    if (result.success) {
		                        $.messager.show({
		                            title : message.title.normal,
		                            msg : message.add_success,
		                            timeout : message.timeout,
		                            showType : message.showType
		                        });
		                        grid.datagrid("reload");
		                        $("#form").form("reset");
		                    }
		                }, 'json');
	                }else{
						$.messager.show({
                            title : message.title.normal,
                            msg : message.userName_has,
                            timeout : message.timeout,
                            showType : message.showType
                        });
	                }
                });
            }
        }
    })
    //重置操作
    $("#operateMode_reset").click(function() {
        var idValue = $("#form input[name='id']").val();
        if (idValue > 0) {
            $("#form").form("load",ctx+"getUserById.do?id=" + idValue);
        } else {
            $("#form").form("reset");
        }
    })
    
	/*选择省以后再显示市的下拉，在选择市的信息以后再显示区的下拉******************************/
	/*@author:lihui***********************************************************************/
	/*2015.10.28 createtime***************************************************************/
	//市初始化
	$.getJSON(ctx+'listAllAreaCity.do',function(cityJson) {
	 	CS = $('#cityId').combobox({
    		   //data :[{id:cityJson.rows[0].id,cityName:cityJson.rows[0].cityName,selected:true}],
    	        valueField:'id',
    	        textField:'cityName',  
    	}) 
	});
    //区初始化
    $.getJSON(ctx+'listAllAreaDistrict.do',function(districJson) {
        DS = $('#districtId').combobox({
			//data : districJson.rows,
		    valueField:'id',
		    textField:'districtName'
			}) 
     });
    $.getJSON(ctx+'listAllAreaProvince.do', function(json) {
     PS= $('#provinceId').combobox({
	        data : json.rows,
	        valueField:'id',
	        textField:'provinceName',
	        DropDownStyle:'DropDownList',
	        onSelect:function(paramsPro){
	        	DS.combobox("clear");
	        	DS.combobox('loadData',{id:1,districtName:" "});
	        	$.getJSON(ctx+'listAllAreaCity.do',{provinceIdSch:paramsPro.id},function(cityJson) {
	        		$('#cityId').combobox({
	        			data : cityJson.rows,
	        	        valueField:'id',
	        	        textField:'cityName',
	        	        onSelect:function(paramsCity){
	        	        	$.getJSON(ctx+'listAllAreaDistrict.do',{cityIdSch:paramsCity.id},function(districJson) {
	        	        	DS= $('#districtId').combobox({
	        	        			data : districJson.rows,
	        	        			valueField:'id',
	        	         	        textField:'districtName'
	        	        		})
	        	        	})
	        	        }
	        		})
	        	})
	        }
        });
    });
</script>
</body>
</html>
