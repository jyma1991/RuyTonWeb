<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@page import="org.durcframework.core.UserContext" %>
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
                                <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a>
                                </td>
                                <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a>
                                </td>
                                <td><a id="btn_export"  href="#" class="easyui-linkbutton btn_scStudent" data-options="iconCls:'ext-icon-page_white_excel'">下载模板</a></td>
                                <td><a id="btn_import"  href="#" class="easyui-linkbutton btn_scStudent" data-options="iconCls:'ext-icon-page_excel'">导入(仅*.xlsx)</a></td>
                                <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a>
                                </td>
                            </tr>
                        </table>
                        <table>
                            <tr>
	                       <td>所属代理商</td>
	                        <td>
	                            <input id="agentIdSch" name="agentIdSch" class="textbox" style="width: 120px; height: 22px;" />
	                        </td>
                            <td>学校名称</td>
							<td>
								<input id="schoolId"  name="schoolId"  type="hidden"/> 
								<input id="schoolIdSch"  name="schoolIdSch"/>
							</td>
                                <td>用户名称</td>
                                <td>
                                    <input name="userNameSch" class="textbox" style="width: 120px; height: 22px;" />
                                </td>
                                <td>创建时间</td>
                                <td>
                                    <input name="createdStartSch" class="easyui-datebox" style="width: 100px" />-
                                    <input name="createdEndSch" class="easyui-datebox" style="width: 100px" />
                                </td>
                                <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',fw.serializeObject($('#searchForm')));">过滤</a>  <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a>
                                </td>
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
                            <legend>基本信息--<span id="operateMode_title">添加模式</span> 
                            </legend>
                            <table class="table">
                                <tbody>
                                    <tr id = "idTr">
                                        <th>id</th>
                                        <td>
                                            <input id="id" readonly="readonly" name="id" value = "0" class="textbox" style="height: 22px;" />
                                            <input id="teacherId" readonly="readonly" name="user.id" class="textbox" style="height: 22px;" />
                                            <input id="roleProperty"  name="roleProperty" value="2" class="textbox" style="height: 22px;" />                                      
                                        </td>
                                    </tr>
                                 <tr>
									<th>选择班级</th>
	                                <td>
	                                  <input id="classes" value="" name="classes" class="textbox"  style="height: 22px;" />
	                                </td>
                                </tr>
                                 <tr>
                                        <th>真实姓名</th>
                                        <td>
                                            <input id="trueName" name="user.trueName" required="true"   class="textbox easyui-validatebox" style="height: 22px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>用户名</th>
                                        <td>
                                            <input id="mobilePhone" name="user.mobilePhone" required="true" data-options="validType:'mobile'"  class="textbox easyui-validatebox" style="height: 22px;" />
                                         （长度为11位的电话号码）
                                        </td>
                                    </tr>
                                       <tr>
	                                    <th>性别</th>
	                                    <td>
	                                        <input id="sex"  name="user.sex" class="textbox" required = "required" style="height: 22px; width: 40px"  />
	                                    </td>
                                	</tr>
                                    <tr id = "idpwd">
                                      <th>密码</th>
                                      <td>
                                           <input id="userPwd" name="user.userPwd"   readonly="true" type="password" hidden="hidden"  style="height: 22px;" />
                                           <input id="userPwdShow" name="userPwdShow"   readonly="true" type="password"  class="textbox easyui-validatebox" style="height: 22px;" />
                                      </td>
                                    </tr>
                                	<tr>
                                        <th>昵称</th>
                                        <td>
                                            <input id="nickName" name="user.nickName"     class="textbox easyui-validatebox" style="height: 22px;" />
                                       （默认名称是真实姓名）
                                        </td>
                                    </tr>
                                   
                                    <tr id="userNameTr">
                                        <th>电话</th>
                                        <td>
                                            <input id="userName" name="user.userName"  data-options="validType:'mobile'"  class="textbox easyui-validatebox" style="height: 22px;" />
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
            	var schoolId = <%=UserContext.getInstance().getUser().getId()%>;
                var globalClassId;
                var globalTeacherId;
                var comboxTexts;
                var nowrow;
               
                document.getElementById("idTr").style.display ='none';  
                document.getElementById("idpwd").style.display ='none'; 
                document.getElementById("userNameTr").style.display ='none'; 

                $("#mobilePhone").blur(function(){
                	 $("#userName").val($("#mobilePhone").val());
                	});
                //getdefaultPassword,field,target
                 var defaulPwd;
                classTypeName =	$.getJSON(ctx+"getdefaultPassword.do",function(dataJson){
              	  defaulPwd = dataJson;
                 });
                
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
                    $("#operateMode_title").html("修改模式");
                    document.getElementById('mobilePhone').setAttribute("readonly",""); 
                }

                $.getJSON(ctx + 'listAllUser.do', {rolePropertySch: 8}, function(agJson) {
                    $('#agentIdSch').combobox({
                        data: agJson.rows,
                        valueField: 'id',
                        textField: 'trueName',
                        onSelect: function(row) {
                            count = false;
                            agId = row.id;
                           // $("#agentId").val(agId);
                            console.log(fw.serializeObject($('#searchForm')));
                            grid.datagrid('load', fw.serializeObject($('#searchForm')));
                            
                            $.getJSON(ctx+'listAllScSchool.do',{  agentIdSch: agId},function(parentsJson) 
                                    {
                            	        CSsex = $("#schoolIdSch").combobox({
                                            valueField: 'schoolId',
                                            textField: 'schoolName',
                            				panelHeight:'auto',  
                            				data:parentsJson.rows,
                            				onSelect:function(params){
                            	  	         	schoolId=params.schoolId;
                            	  	         	$("#schoolIdSch").val(schoolId);
                            	  	         	$("#schoolId").val(schoolId);
                            	  	          	grid.datagrid('load', fw.serializeObject($('#searchForm')));
            									//根据学校列出班级
                            	                $.getJSON(ctx + 'listScClass.do?placeTypeIdSch=69&schoolIdSch='+schoolId, function (classJson) {
                            	                	CS = $('#classes')
                            	                        .combobox({
                            	                            data: classJson.rows,
                            	                            valueField: 'id',
                            	                            textField: 'className',
                            	                            multiple:true,
                            	        					panelHeight:'auto',
                            	                            onSelect: function (row) {
                            	                            	//comboxTexts= $('#classId').combobox('clear');
                            	                            	comboxTexts= $('#classes').combobox('getValues');
                            	                            	console.log(comboxTexts);
                            	                            },
                            	                        
                            		                        onUnselect: function (){
                            	                            	//comboxTexts= $('#classId').combobox('clear');
                            		                        	comboxTexts= $('#classes').combobox('getValues');
                            		                        	console.log(comboxTexts);
                            		                        }
                            	                            
                            								
                            	                        });
                            	                    grid.datagrid('reload');
                            	                });
                            	  	        }
                            	            });     
                                    }); 
                            
                        }
                    
                    })
                }); 
                
              
                
                
                $(function () {
                    var gird;
					
                    grid = $('#grid').datagrid({
                            fit: true,
                            border: false,
                            collapsible: false,
                            rownumbers: true,
                            pagination: true,
                            singleSelect: true,
                            rownumbers: true,
                            url: ctx + 'getScTeacherByTrueName.do',
                            columns: [[{
                                    field: 'id',
                                    title: 'id',
                                    hidden: 'true',
                                }
                             , {
                                    field: 'user',
                                    title: '老师姓名',
                                    formatter: function (value, row, index) {
                                    	if(row.user)
                                        {return row.user.trueName;}
                                    	else
                                    	{
                                    		return '';
                                    	}
                                    }
                                }
                             , {
                                    field: 'mobilePhone',
                                    title: '电话',
                                    formatter: function (value, row, index) {
                                        if(row.user)
                                        {return row.user.mobilePhone;}
                                    	else
                                    	{
                                    		return '';
                                    	}
                                    }
                                }
                             , {
                                    field: 'editedTime',
                                    title: '编辑时间',
                                    formatter: getDate
                                }
                             , {
                                    field: 'createdTime',
                                    title: '创建时间',
                                    formatter: getDate,
                                    sortable: true
                                }
                                  ]],
                               onLoadSuccess: function (data) {
                                    $('.iconImg').attr('src', fw.pixel_0);
                                },
                                onClickRow: function (index, row) {
                    	        	$("#form").form("reset");
                                	$('#classes').combobox("clear");
                                    $("#form").form("myLoad", row);
                                    nowrow = row;
                                    $('#sex').combobox('loadData',[{"id":"1","name":"男"},{"id":"0","name":"女"}]);
                                    $('#classes').combobox('setValues', row.classes);
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
                    $("#btn_add")
                        .click(function () {
                            $("#operateMode_title")
                                .html("添加模式");
                            $('#grid')
                                .datagrid("unselectAll");
                            $("#classIdSelect")
                                .attr("value", globalClassId);
                            $("#id")
                                .attr("value", 0);
                            $("#form")
                                .form("reset");
                        	$('#classes').combobox("clear");
                        	document.getElementById('mobilePhone').removeAttribute("readonly"); 
                        })

                    //删除操作
                    $("#btn_dele").click(function () {
                            var rows = $('#grid')
                                .datagrid('getSelections');
                            if (rows <= 0) {
                                $.messager.show({
                                    title: message.title.normal,
                                    msg: message.grid_select,
                                    timeout: message.timeout,
                                    showType: message.showType
                                });
                            } else {
                                $.messager.confirm(message.title.askTitle, message.dele_comfirm, function (r) {
                                    if (r) {
                                        var idValue = $("#form input[name='id']")
                                            .val();
                                        var teacherIdValue = $("#form input[name='user.id']")
                                            .val();
                                        $.post(ctx + "delScTeacher.do", {
                                            id: idValue,
                                            teacherId: teacherIdValue
                                        }, function (result) {
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
                                        $("#btn_add")
                                            .click();
                                    }
                                });
                            }
                        })

                        
                    //确认提交操作
                    $("#operateMode_submit")
                        .click(function () {
                            var idValue = $("#form input[name='id']").val();
                            copyDefaulValue();
                            if ($('#form').form('validate')) {
                                if (idValue > 0) {
                                    //修改
                                    $.post(ctx + "updateScTeacher.do", fw.serializeObject($('form')), function (result) {
                                        if (result.success) {
                                            $.messager.show({
                                                title: message.title.normal,
                                                msg: message.update_success,
                                                timeout: message.timeout,
                                                showType: message.showType
                                            });

                                            grid.datagrid("reload");

                                            //重新获取form信息内容
                                            $("#form").form("load", ctx + "getScTeacherById.do?id=" + idValue);
                                        }
                                        else
                                        {
                                        	$.messager.show({
                                                title: message.title.normal,
                                                msg: message.userName_has,
                                                timeout: message.timeout,
                                                showType: message.showType
                                            });
                                        }
                                    }, 'json');
                                } else {
                                    if (idValue.trim()
                                        .length == 0) {
                                        $.messager.show({
                                            title: message.title.normal,
                                            msg: message.add_button_click,
                                            timeout: message.timeout,
                                            showType: message.showType
                                        });
                                    } else {
                                        //添加
                                        $.post(ctx + "addScTeacher.do", fw.serializeObject($('form')), function (result) {
                                        	console.log(fw.serializeObject($('form')));
                                        console.log(result);
                                            if (result.success) {
                                                $.messager.show({
                                                    title: message.title.normal,
                                                    msg: message.add_success,
                                                    timeout: message.timeout,
                                                    showType: message.showType
                                                });
                                                grid.datagrid("reload");
                                                $("#form")
                                                    .form("reset");
                                            }
                                            else
                                            {
                                            	$.messager.show({
                                                    title: message.title.normal,
                                                    msg: message.userName_has,
                                                    timeout: message.timeout,
                                                    showType: message.showType
                                                });
                                            }
                                        }, 'json');
                                    }
                                }
                            }
                        })
                        
                     $("#btn_import").click(function() {
                        var btn = $(this);
                        if(schoolId==1) 
                        alert("请选择学校");
                        else
                        url = '../../pluploader.jsp?moduleId=sc&funcId=importTeacher'+'&userId=' + schoolId;
                        
                        var dialog = fw.modalDialog({
                            title: '选择文件',
                            url: url,
                            width: 700,
                            height: 500,
                            buttons: [{
                                text: '确认选择',
                                handler: function() {
                                    var rows = dialog.find('iframe').get(0).contentWindow.returnFormValue(dialog);
                                    $.post(ctx + "importTeacher.do",rows[0], function(result) {
                                        if (result.success) {
                                            $.messager.show({
                                                title: message.title.normal,
                                                msg: message.add_success,
                                                timeout: message.timeout,
                                                showType: message.showType
                                            });
                                            grid.datagrid("reload");
                                            $("#form").form("reset");
                                        }else
                                        	{
                                        	$.messager.show({
                                                title: message.title.normal,
                                                msg: message.add_fail+result.message,
                                                timeout: message.timeout,
                                                showType: message.showType
                                            });
                                        	}
                                    }, 'json');
                                    
                                }
                            }, {
                                text: '清除文件',
                                handler: function() {
                                    dialog.find('iframe').get(0).contentWindow.clearSelectedFiles(dialog, btn);
                                }
                            }, {
                                text: '关闭窗口',
                                handler: function() {
                                    dialog.find('iframe').get(0).contentWindow.closeDialog(dialog);
                                }
                            }]
                        });
                });
                    
                    $("#btn_export").click(function() {
                    		window.open('../../../template/importTeacherTemplate.xlsx');
                    });
                    
                        //重置操作
                    $("#operateMode_reset")
                        .click(function () {
                        	
                            var idValue = $("#form input[name='id']").val();
                            if (idValue > 0) {
                                $("#form")
                                    .form("load", ctx + "getScTeacherById.do?id=" + idValue);
                            } else {
                                $("#form").form("reset");
                            }
                        })
                });
			//	$.ajaxSettings.async = false;
                $.getJSON(ctx + 'listScClass.do?placeTypeIdSch=69&schoolIdSch='+schoolId, function (classJson) {
                	CS = $('#classes')
                        .combobox({
                            data: classJson.rows,
                            valueField: 'id',
                            textField: 'className',
                            multiple:true,
        					panelHeight:'auto',
                            onSelect: function (row) {
                            	//comboxTexts= $('#classId').combobox('clear');
                            	comboxTexts= $('#classes').combobox('getValues');
                            	console.log(comboxTexts);
                            },
                        
	                        onUnselect: function (){
                            	//comboxTexts= $('#classId').combobox('clear');
	                        	comboxTexts= $('#classes').combobox('getValues');
	                        	console.log(comboxTexts);
	                        }
                            
							
                        });
                    grid.datagrid('reload');
                });
                

             function resetPassword(row) {
      			MsgUtil.confirm("确定给" + row.user.username + "重置密码吗?", function() {
      				Action.jsonAsyncActByData(ctx + 'resetUserPassword.do', row.user, function(e) {
      					if (e.success) {
      						MsgUtil.alert('密码重置成功,新密码为:<strong style="color:red;">' + e.message + '</strong>');
      					}
      				});
      			});
             }	


             function searchForm(){
             	var className = $("input[name='userTrueName']").val();
             	var beginDate = $("input[name='beginTime']").val();
             	var endDate = $("input[name='endTime']").val();
             	$.ajax({
             		url:ctx+"getScTeacherByTrueName.do",//findClassScheduleByClassName.do",
             		type:"post",
             		data:{className:className,beginDate:beginDate,endDate:endDate},
             		success:function(data){
             			$('#searchForm input').val('');
             			if(data.rows.length == 0){
             				$('#grid').datagrid('loadData', { total: 0, rows: [] });
             			}else{
             				$('#grid').datagrid('loadData', { total: 0, rows: [] }).datagrid('loadData',data.rows);	
             			}
             		},
             		dataType:'json'
             	});
             } 
             
             
                
            </script>
        </body>


        </html>