<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/rms" prefix="rms" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>代理商管理</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>

    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:100px;">
                <form id="searchForm">
                    <table>
                        <tr>
                        	<rms:role operateCode="add">
                            <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
                            </rms:role>
                            <rms:role operateCode="delete">
                            <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
                            </rms:role>
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>用户名</td>
                            <td>
                                <input name="userNameSch" id="userNameSch" class="textbox" style="width: 120px; height: 22px;" />
                                <input type="hidden" name="commandUserIdSch" id="commandUserIdSch"/>
                            </td>
                               <td>等级</td>
                                  <td><select name="levelIdSch" id="levelIdSch" class="textbox"   style="width: 120px;height: 22px;">
                                        <option value =""></option>
                                        <option value =1>粉丝合伙人</option>
                                        <option value=2>小区合伙人</option>
                                        <option value=3>大区合伙人</option>
                                        <option value=4>金牌合伙人</option>
                                        </select>
                                    </td>
                            <td>添加时间</td>
                            <td>
                                <input name="createdStartSch" id="createdStartSch" class="easyui-datebox" style="width: 100px" /> -
                                <input name="createdEndSch" id="createdEndSch" class="easyui-datebox" style="width: 100px" />
                            </td>
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="console.log(fw.serializeObject($('#searchForm')));grid.datagrid('load',fw.serializeObject($('#searchForm')));">检索</a> <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="nextAgent();console.log(fw.serializeObject($('#searchForm')));grid.datagrid('load',fw.serializeObject($('#searchForm')));">下级代理商</a><a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');$('#searchForm select').val('');grid.datagrid('load',{});">清除检索条件</a></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div data-options="region:'center',border:false">
                <table id="grid">
                </table>
            </div>
            <div data-options="region:'east',split:true,border:false" style="width: 50%; padding: 5px;">
                <form id="form" name="form" method="post" class="form">
                	<input type="hidden" id="user.id" name="user.id" value="0" class="textbox" style="height: 22px;" />
                    <input type="hidden" id="userId" name="userId" class="textbox" style="height: 22px;" />
                    <input type="hidden" id="userName" name="userName" class="textbox" style="height: 22px;" />
                    <input type="hidden" id="uuid" name="uuid" class="textbox" value="0" style="height: 22px;" />
                    <input type="hidden" id="isDeleted" value="0" name="isDeleted" class="textbox" style="height: 22px;" />
                    <input type="hidden" id="operaterId" value="0" name="operaterId" class="textbox" style="height: 22px;" />
                    <fieldset>
                        <legend> 基本信息--<span id="operateMode_title">添加模式</span> </legend>
                        <table class="table">
                            <tbody>
                               <tr>
                               	<th>ID</th>
					            <td><input id="id" readonly="readonly" name="id"  value="0"class="textbox" style="height: 22px;width:40px;" /><input type="hidden" id="agentId" name="agentId"></td>
					            <th>手机</th>
					            <td><input id="mobilePhone" name="user.mobilePhone" class="textbox easyui-numberbox" required="true" data-options="validType:'mobile'"  style="height: 22px;" /></td>
					           </tr>
					           <tr>
					            <th>用户名</th>
					            <td><input id="userName" name="user.userName" class="textbox easyui-validatebox" required="true"   style="height: 22px;" /></td>
					            <th>固定电话</th>
					            <td><input id="fixedPhone" name="user.fixedPhone" class="textbox " data-options="validType:'phone'" style="height: 22px;" /></td>
					           	
					          </tr>
					          <tr>
					          	<th>密码</th>
					            <td class="pw"><input type="password" id="userPwd" class="textbox easyui-validatebox" required="true" validType="length[5,20]"    style="height: 22px;" /><input type="hidden" id="hidden-userPwd" name="user.userPwd"/></td>
					            <th>邮箱</th>
					            <td><input id="email" name="user.email" class="textbox easyui-validatebox" data-options="validType:'email'" style="height: 22px;" /></td>
					          
							  </tr>
					          <tr>
					          	<th>昵称</th>
					            <td><input id="nickName" name="user.nickName" class="textbox easyui-validatebox" required="true" style="height: 22px;" /></td> 
					            <th>微信</th>
					            <td><input id="weiXin" name="user.weiXin" class="textbox" style="height: 22px;" /></td>
					          	
					 		   </tr>
					 		   <tr>
								<th>真实姓名</th>
					            <td><input id="trueName" name="user.trueName" class="textbox easyui-validatebox" required="true" style="height: 22px;" /></td> 	
					            <th>QQ</th>
					            <td><input id="qq"  name="user.qq" class="textbox easyui-numberbox" data-options="validType:'qq'"style="height: 22px; "/></td>	   
					 		   </tr>
					 		   <tr>
								<th>性别</th>
					            <td><input id="sex" name="user.sex" class="textbox easyui-validatebox" value=1 editable="false" style="height: 22px;width:40px" /></td> 	
					    		<th>序号</th>
					            <td><input id="sortId" name="sortId"  class="textbox easyui-numberspinner"  style="height: 22px;width:40px" /></td>
					          </tr>
					          <tr>
					          	<th>地址</th>
					            <td colspan="3"><span style="display: inline-block;width: 30px; text-align:center;">省</span><input id="provinceId"  name="user.provinceId" class="textbox " editable="false"  style="height: 22px;width:25%;margin-left:5px;" />
					            				<span style="display: inline-block;width: 30px; text-align:center;">市</span><input id="cityId"  name="user.cityId" class="textbox" editable="false" style="height: 22px;width:25%;margin-left:5px" />
					            				<span style="display: inline-block;width: 30px; text-align:center;">区</span><input id="districtId" name="user.districtId" class="textbox" editable="false" style="height: 22px;width:25%;margin-left:5px" />
					            </td>
					          </tr>
					           <tr>
					            <th>详细地址</th>
					            <td colspan="3"><textarea cols="100" id="addressDetail" name="user.addressDetail" class="textbox" style="padding-top:3px;height: 22px; width:90%" ></textarea></td>
					           </tr>
					           <tr>
					            <th>备注</th>
					            <td colspan="3" ><textarea rows="3"  id="remark" name="user.remark" class="textbox"style=" width:90%;white-space: pre-wrap;"  wrap="hard" /></textarea></td>
					           </tr>
					          <tr>
                                <tr>
                                     <th>代理商卡号</th>
                                    <td><input id="cardNo" name="cardNo" class="textbox easyui-validatebox" required="true"  style="height: 22px;" /></td>
                                    <th>身份证号</th>
                                    <td>
                                        <input id="identityCardNo" name="identityCardNo" class="textbox easyui-validatebox" data-options="validType:'idcard'" required="true"  style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>等级编号</th>
                                    <td>
                                        <select name="levelId" class="textbox"   style="width: 120px;height: 22px;">
                                        <option value ="1">粉丝合伙人</option>
                                        <option value="2">小区合伙人</option>
                                        <option value="3">大区合伙人</option>
                                        <option value="4">金牌合伙人</option>
                                        </select>
                                    </td>
                                     <th>传真</th>
                                    <td>
                                        <input id="fax" name="fax" class="textbox" style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>操作</th>
                                    <td colspan="3"><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <script type="text/javascript">
        function nextAgent(){
        		var agentId=$("#agentId").val();
            	if(agentId==null ||agentId==""){
            		alert("请选择一位代理商");
            	}else{
            	$("#userNameSch").val("");
            	$("#userNameSch").val("");
            	$("#levelIdSch").val("");
            	$("#createdStartSch").val("");
            	$("#createdEndSch").val("");
            	$("#commandUserIdSch").val(agentId);
            	}
        	}
            $(function() {
                var gird;
                var PS;
                var DS;
                var CS;
                var cityFlag = 0;
                var districtFlag = 0;
                grid = $('#grid').datagrid({
                    fit: true,
                    border: false,
                    collapsible: false,
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    rownumbers: true,
                    url: ctx + 'listScAgent.do',
                    columns: [
                             [ {
                                 field: 'trueName',
                                 title: '真实姓名',
                                 formatter:function(value,row,index){
                            		 if(row.user){
                            			 return row.user.trueName;
                            		 }
                                 },
                                 sortable:true
                             }, {
                                 field: 'userName',
                                 title: '用户名',
                                 formatter:function(value,row,index){
                            		 if(row.user){
                            			 return row.user.userName;
                            		 }
                                 },
                                 sortable:true
                             }, {
                                 field: 'agentId',
                                 title: '代理商ID',
                                 sortable:true
                             }, {
                                 field: 'levelId',
                                 title: '等级',
                                 sortable:true,
                                 formatter:function(value){
                                	 if(value==1){
                                		 return "粉丝合伙人";
                                	 }
                                	 else if(value==2){
                                		 return "小区合伙人";
                                	 }
                                	 else if(value==3){
                                		 return "大区合伙人";
                                	 }
                                	 else{
                                		 return "金牌合伙人";
                                	 }
                                 }
                             }, {
                                 field: 'identityCardNo',
                                 title: '身份证号',
                                 sortable:true
                             }, {
                                 field: 'fax',
                                 title: '传真',
                                 sortable:true
                             }, {
                            	 field: 'mobilePhone',
                            	 title: '移动电话',
                            	 formatter:function(value,row,index){
                            		 if(row.user){
                            			 return row.user.mobilePhone;
                            		 }
                                 },
                           	     sortable:true
                           	 }, {
                           	     field: 'createdTime',
                           	     title: '添加时间',
	                           	 formatter: function(value, row, index){
			            			return value.substring(0,10);
			            		 },
                           	     sortable:true
                           	 }]
                         ],
                    onLoadSuccess: function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    },
                    onClickRow: function(index, row) {
                        $("#form").form("myLoad", row);
                        loadItem(row);
                        $('#sex').combobox('loadData',[{"id":"1","name":"男"},{"id":"0","name":"女"}]);
                        $("#operateMode_title").html("修改模式");
                    }
                });
                //添加操作
                $("#btn_add").click(function() {
                    $("#operateMode_title").html("添加模式");
                    $('#grid').datagrid("unselectAll");
                    $("#id").attr("value", 0);
                    $("#form").form("reset");
                    $('#sex').combobox('select', '1');
                })

                //删除操作
                $("#btn_dele").click(function() {
                    var rows = $('#grid').datagrid('getSelections');
                    if (rows <= 0) {
                        $.messager.show({
                            title: message.title.normal,
                            msg: message.grid_select,
                            timeout: message.timeout,
                            showType: message.showType
                        });
                    } else {
                        $.messager.confirm(message.title.askTitle, message.dele_comfirm, function(r) {
                            if (r) {
                                var idValue = $("#form input[name='id']").val();
                                $.post(ctx + "delScAgent.do", {
                                    id: idValue
                                }, function(result) {
                                    if (result.success) {
                                        $.messager.show({
                                            title: message.title.normal,
                                            msg: message.dele_success,
                                            timeout: message.timeout,
                                            showType: message.showType
                                        });
                                        grid.datagrid('reload');
                                    } else {
                                        $.messager.show({
                                            title: message.title.normal,
                                            msg: message.dele_fail,
                                            timeout: message.timeout,
                                            showType: message.showType
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
                            	var userPwd = $("#hidden-userPwd").val();
               				    if(userPwd.indexOf($.trim($('#userPwd').val())) == 0){
               				    	//说明没有改动密码 
               				    }else{
               				    	$("#hidden-userPwd").val(faultylabs.MD5($.trim($('#userPwd').val())));
               				    	
               				    }
                                //修改
                                $.post(ctx + "updateScAgent.do", fw.serializeObject($('form')), function(result) {
                                	if (result.success) {
                                    	grid.datagrid("reload");
                                        $.messager.show({
                                            title: message.title.normal,
                                            msg: message.update_success,
                                            timeout: message.timeout,
                                            showType: message.showType
                                        });
                                        //重新获取form信息内容
                                        $("#form").form("load", ctx + "getScAgentById.do?id=" + idValue);
                                        setTimeout("loadItem(null)",100);
                                    }else{
                                    	$.messager.show({
                                            title: message.title.normal,
                                            msg: result.message,
                                            timeout: message.timeout,
                                            showType: message.showType
                                        });
                                    }
                                }, 'json');
                            } else {
                                if (idValue.trim().length == 0) {
                                    $.messager.show({
                                        title: message.title.normal,
                                        msg: message.add_button_click,
                                        timeout: message.timeout,
                                        showType: message.showType
                                    });
                                } else {
                                	$("#hidden-userPwd").val(faultylabs.MD5($.trim($('#userPwd').val())));
                                    //添加
                                    $.post(ctx + "addScAgent.do", fw.serializeObject($('form')), function(result) {
                                    	if (result.success) {
                                            $.messager.show({
                                                title: message.title.normal,
                                                msg: message.add_success,
                                                timeout: message.timeout,
                                                showType: message.showType
                                            });
                                            grid.datagrid("reload");
                                            $("#form").form("reset");
                                        }else{
                                        	$.messager.show({
                                                title: message.title.normal,
                                                msg: result.message,
                                                timeout: message.timeout,
                                                showType: message.showType
                                            });
                                        }
                                    }, 'json');
                                }
                            }
                        }
                    })
                    //重置操作
                $("#operateMode_reset").click(function() {
                    var idValue = $("#form input[name='id']").val();
                    if (idValue > 0) {
                        $("#form").form("load", ctx + "getScAgentById.do?id=" + idValue);
                    } else {
                        $("#form").form("reset");
                    }
                })
                
            	//性别
                var CSsex = $('#sex').combobox({
                    valueField: 'id',
                    textField: 'name',
            		panelHeight:'auto',  
            		data:[{id:"0",name:"女"},{id:"1",name:"男"}]
                });
            });
            /*选择省以后再显示市的下拉，在选择市的信息以后再显示区的下拉******************************/
        	/*@author:wyp***********************************************************************/
        	/*2015.11.5 createtime***************************************************************/
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
        	        	if(paramsPro){
	        	        	DS.combobox("clear");
	        	        	DS.combobox('loadData',{id:1,districtName:" "});
	        	        	$.getJSON(ctx+'listAllAreaCity.do',{provinceIdSch:paramsPro.id},function(cityJson) {
	        	        		CS=$('#cityId').combobox({
	        	        			data : cityJson.rows,
	        	        	        valueField:'id',
	        	        	        textField:'cityName',
	        	        	        onSelect:function(paramsCity){
	        	        	        	if(paramsCity){
		        	        	        	$.getJSON(ctx+'listAllAreaDistrict.do',{cityIdSch:paramsCity.id},function(districJson) {
		        	        	        	DS= $('#districtId').combobox({
		        	        	        			data : districJson.rows,
		        	        	        			valueField:'id',
		        	        	         	        textField:'districtName'
		        	        	        		})
		        	        	        	})
	        	        	        	}
	        	        	        }
	        	        		})
	        	        	})
        	        	}
        	        }
                });
            });
            function loadItem(row){
            	cityFlag = 1;
            	districtFlag = 1;
            	 var userPwd = $("#hidden-userPwd").val();
				 $("#userPwd").val(userPwd.substring(0,6));
            	 if(row){
	                 //清除省下拉框的值
	   	            $.getJSON(ctx+'listAreaProvince.do',{idSch:row.user.provinceId},function(provinceJson) {
	   	            	if(provinceJson.rows.length==0){
	   	            		PS.combobox("clear");
	   	            	}else{
	   	            		
	   	            		
	   	            	}
	   	             });
	   	        	//市初始化
	   	        	$.getJSON(ctx+'listAllAreaCity.do',{provinceIdSch:row.user.provinceId},function(cityJson) {
    	        		if(cityJson.rows.length == 0){
    	        			CS.combobox("clear");
    	        		}else{
    	   	        		CS=$('#cityId').combobox({
        	        			data : cityJson.rows,
        	        	        valueField:'id',
        	        	        textField:'cityName',
        	        	        onSelect:function(paramsCity){
	        	        	        if(paramsCity){
	        	        	        	$.getJSON(ctx+'listAllAreaDistrict.do',{cityIdSch:paramsCity.id},function(districJson) {
	        	        	        	DS= $('#districtId').combobox({
	        	        	        			data : districJson.rows,
	        	        	        			valueField:'id',
	        	        	         	        textField:'districtName',
	        	        	         	       	onLoadSuccess:function(){
													if(districtFlag == 1 && row.user.districtId >0){
				        	        	        		$('#districtId').combobox("select",row.user.districtId);
				        	        	        	}else if(districtFlag == 1){
				        	        	        	}
				            	        			districtFlag = 2;
	        	        	        	        }
	        	        	        		})
	        	        	        	})
	        	        	        }
        	        	        	
        	        	        },
        	        	        onLoadSuccess:function(){
        	        	        	if(cityFlag == 1){
        	        	        		if(row.user.cityId > 0){
        	        	        			$('#cityId').combobox("select",row.user.cityId);
        	        	        		}
        	        	        		
        	        	        	}else{
        	        	        		
        	        	        	}
            	        			cityFlag = 2;
        	        	        }
        	        		})
    	        		}

    	        	})
	   				//根据cityID获取当前用户的district
	   				$.getJSON(ctx+'listAllAreaDistrict.do',{idSch:row.user.cityId},function(districJson) {
	   	        		if(districJson.rows.length>0){	
	   	        		}else{
	   	        			DS.combobox("clear");
	   	        		}
	   	        	});
            	 }
            }
        </script>
    </body>

    </html>