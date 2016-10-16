<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Insert title here</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>

    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
                            <rms:role operateCode="delete">
                            <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
                            </rms:role>
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
 							<td> <a id="back" class="easyui-linkbutton"  data-options="iconCls:'icon-back'">返回</a></td>
 					</tr>
                    </table>
                    <table>
                        <tr>
                         	<td>用户姓名</td>
                            <td>
                                <input id="parentIdSch" name="parentIdSch" class="textbox" style="width: 100px" /> -
                            </td>
                            <td>创建时间</td>
                            <td>
                                <input name="createdStartSch" class="easyui-datebox" style="width: 100px" /> -
                                <input name="createdEndSch" class="easyui-datebox" style="width: 100px" />
                            </td>
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',fw.serializeObject($('#searchForm')));">过滤</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
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
                 <fieldset>

                        <legend> 基本信息--<span id="operateMode_title">添加模式</span> </legend>
                        <table class="table">
                            <tbody>
                               <input id="studentId" value="<%=request.getParameter("studentId")%>" name="studentId" class="textbox" style="height: 22px; display:none;"  />

                                <tr id = "parentIdClass">
                                    <th>家长ID</th>
                                    <td>
                                        <input id="id" name="id" value="0" class="textbox" style="height: 22px;" />
                                        <input id="user.id" name="user.id" class="textbox" style="height: 22px;" />
                                        <input id="roleProperty"  name="roleProperty" value="1" class="textbox" style="height: 22px;" />
                                        <input id="avatar"  name="avatar" value="image/1.png" class="textbox" style="height: 22px;" />
                                    </td>
                                </tr>
                                 <tr>
                                    <th>家长姓名</th>
                                    <td>
                                        <input id="trueName"  name="user.trueName" required="true" class="textbox easyui-validatebox" style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr >
                                    <th>用户名</th>
                                    <td>
                                        <input id="userName" name="user.userName" required="true" data-options="validType:'mobile'"  class="textbox easyui-validatebox" style="height: 22px;" />
                                   （长度为11位的电话号码） </td>
                                </tr> 
                                 <tr>
								       <th>性别</th>
								      <td>
								      <input id="sex"  name="user.sex" class="textbox" required = "required" style="height: 22px; width: 40px"  />
								       </td>
								 </tr>
								 
								 <tr>
                                    <th>亲子关系</th>
                                    <td>
                                    	<input id="relation" value="请选择关系" name="relatedTypeId" class="textbox" style="height: 22px;width: 100px" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>卡号</th>
                                    <td>
                                        <input id="cardNo"  name="cardNo" class="textbox easyui-validatebox" style="height: 22px;" />
                                    </td>
                                </tr>
                                 <tr>
                                    <th>昵称</th>
                                    <td>
                                        <input id="nickName"  name="user.nickName"  class="textbox easyui-validatebox" style="height: 22px;" />
                                      （默认名称是真实姓名）
                                    </td>
                                </tr>
                                  <tr>
                                    <th>详细地址</th>
                                    <td colspan="3">
                                        <textarea cols="100" id="addressDetail" name="user.addressDetail" class="textbox" style="padding-top:3px;height: 22px; width:90%;"></textarea>
                                    </td>
                                </tr>
                               
                                <tr id="userNameTr">
                                    <th>电话</th>
                                    <td>
                                        <input id="userName" name="user.userName"  data-options="validType:'mobile'"  class="textbox easyui-validatebox" style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr id = "idpwd">
                                      <th>密码</th>
                                      <td>
                                           <input id="userPwd" name="user.userPwd"   readonly="true" type="password" hidden="hidden"  style="height: 22px;" />
                                           <input id="userPwdShow" name="userPwdShow"   readonly="true" type="password"  class="textbox easyui-validatebox" style="height: 22px;" />
                                      </td>
                                 </tr>
                                <th>操作</th>
                                <td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> 
                                    <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
                                    <a id="resetPwd" class="easyui-linkbutton"   data-options="iconCls:'icon-reload'">重置密码</a>
                                </td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <script type="text/javascript">
        
        document.getElementById("parentIdClass").style.display ='none';
        document.getElementById("idpwd").style.display ='none';
        document.getElementById("userNameTr").style.display ='none';
        $("#mobilePhone").blur(function(){
       	 $("#userName").val($("#mobilePhone").val());
       	});
        var nowrow;
        var defaulPwd;
        classTypeName =	$.getJSON(ctx+"getdefaultPassword.do",function(dataJson){
      	  defaulPwd = dataJson;
         });        
        var classId = <%=request.getParameter("classId")%>;
        var studentId = <%=request.getParameter("studentId")%>;
        var schoolId = <%=request.getParameter("schoolId")%>;
        
        $.getJSON(ctx+'listAllUser.do',{roleProperty:1},function(parentsJson) {
            $("#parentIdSch").combobox({
    			data:parentsJson.rows,
    			valueField:'id',
    	        textField:'trueName',
    	        selected:parentsJson.rows[0],
    	        filter: function(q, row){
            		var opts = $(this).combobox('options');
            		return row[opts.textField].indexOf(q) == 0;
            	},
    	        onSelect:function(params){
    	         	schoolId=params.schoolId;
    	        }
    			})
       		})
        
        function copyDefaulValue()
        {
        	if($("#nickName").val()=="") $("#nickName").val($("#trueName").val());
            if($("#userName").val()=="") $("#userName").val($("#mobilePhone").val());
            if($("#userPwd").val()=="") 
            {
            	$("#userPwd").val(defaulPwd);//if userPwd is not null, set defaulPwd
            	$("#userPwdShow").val(defaulPwd.substring(0,6));
            }
            else
            {
            	$("#userPwdShow").val($("#userPwd").val().substring(0,6));
            }
            document.getElementById('userName').setAttribute("readonly",""); 
            $("#operateMode_title").html("修改模式");
        }
        $(function() {
                var gird;
                grid = $('#grid').datagrid({
                    fit: true,
                    border: false,
                    collapsible: false,
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    rownumbers: true,
                    url: ctx + "listScStudentParents.do?studentIdSch="+<%=request.getParameter("studentId")%>,
                    columns: [
                        [
                         {
                         field: 'id',
                         title: '家长ID',
                         sortable: true
                         
                     	}
                          ,{
                            field: 'user',
                            title: '家长名称',
                            formatter:function(value,row,index){
                            	if(row.user!=null)
	            				return row.user.trueName;
                            	else
                            	return "";
	            			},
	            			sortable: true
                        } 
                        ,{
                            field: 'userName',
                            title: '用户名',
                            formatter:function(value,row,index){
	            				if(row.user!=null)
		            				return row.user.userName;
	                            	else
	                            	return "";
	            			}
                        } 
                        ,{
                            field: 'createdTime',
                            title: '创建时间',
                            formatter: getDate
                        },{
                            field: 'editedTime',
                            title: '编辑时间',
                            formatter: getDate
                        }
                        ]
                    ],
                    onLoadSuccess: function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    },
                    onClickRow: function(index, row) {
                        $("#form").form("myLoad", row);
                        nowrow = row;
                        copyDefaulValue();
                    }
                });
                $("#resetPwd").click(function(){
                	resetPassword(nowrow);
                });
                CSsex = $('#sex').combobox({
                    valueField: 'id',
                    textField: 'name',
					panelHeight:'auto',  
					data:[{id:"1",name:"男"},{id:"0",name:"女"}]
                });
                //添加操作
                $("#btn_add").click(function() {
                    $("#operateMode_title").html("添加模式");
                    $('#grid').datagrid("unselectAll");
                    $("#id").attr("value", 0);
                    $("#form").form("reset");
                    document.getElementById('userName').removeAttribute("readonly"); 
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
                                var trueName = $("#form input[name='trueName']").val();
                                $.post(ctx + "delScStudentParents.do", {
                                    id: idValue,
                                    trueName :trueName
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
                        copyDefaulValue();
                        if ($('#form').form('validate')) {
                            if (idValue > 0) {
                                //修改
                                console.log(fw.serializeObject($('form')));
                                $.post(ctx + "updateScStudentParents.do", fw.serializeObject($('form')), function(result) {
                                    if (result.success) {
                          				$.messager.show({
                                            title: message.title.normal,
                                            msg: message.update_success,
                                            timeout: message.timeout,
                                            showType: message.showType
                                        });
                          				grid.datagrid("reload");
                                        //重新获取form信息内容
                                        $("#form").form("load", ctx + "getScStudentParentsById.do?id=" + idValue);
                                    }
                                    else
                                    {
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
                                    //添加
                                    $.post(ctx + "addScStudentParents.do", fw.serializeObject($('form')), function(result) {
                                    	
                                        if (result.success) {
                                            $.messager.show({
                                                title: message.title.normal,
                                                msg: message.add_success,
                                                timeout: message.timeout,
                                                showType: message.showType
                                            });
                                            grid.datagrid("reload");
                                            $("#form").form("reset");
                                        }
                                        else
                                        {
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
                    	 $("#form").form("reset");
                    } else {
                        $("#form").form("reset");
                    }
                })

               
			var sysClassTypeId = ClassTypeId.ScStudentParent;
			$.getJSON(ctx + 'listSysClass.do?sysClassTypeIdSch='+sysClassTypeId, function (classJson) {

                    CS = $('#relation').combobox({
                            data: classJson,
                            valueField: 'id',
                            textField: 'className',
                            panelHeight: 'auto',
                            onSelect: function (row) 
						    {
								$('#relatedTypeId').val(row.id);
								console.log(classJson);
						    }
                                      
                           	});

			});
                 
            });
        
        function resetPassword(row) {
        	console.log(row.user);
  			MsgUtil.confirm("确定给" + row.user.username + "重置密码吗?", function() {
  				Action.jsonAsyncActByData(ctx + 'resetUserPassword.do', row.user, function(e) {
  					if (e.success) {
  						MsgUtil.alert('密码重置成功,新密码为:<strong style="color:red;">' + e.message + '</strong>');
  					}
  				});
  			});
         }	
        
        $("#back").click(function() {

        	if( $("#id").val().trim().length>0 ){
				window.location.href="AdminStudent.jsp?studentId="+studentId+"&classId="+classId+"&schoolId="+schoolId+"&agentIdSch="+<%=request.getParameter("agentIdSch")%>;
			}else{
				$.messager.show({
					title : message.title.normal,
					msg : message.no_record,
					timeout : message.timeout,
					showType : message.showType
				});
			}
		});
        
        </script>
    </body>

    </html>