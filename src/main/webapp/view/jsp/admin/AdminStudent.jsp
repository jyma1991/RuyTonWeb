<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="org.durcframework.core.UserContext"%>
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
                            <td> <a id="operate_parents" class="easyui-linkbutton"  data-options="iconCls:'icon-reload', disabled:true">家长信息</a></td>
                            <td><a id="btn_export"  href="#" class="easyui-linkbutton btn_scStudent" data-options="iconCls:'ext-icon-page_white_excel'">下载模板</a></td>
                         	<td><a id="btn_import"  href="#" class="easyui-linkbutton btn_scStudent" data-options="iconCls:'ext-icon-page_excel'">导入(仅*.xlsx)</a></td>
                            <td><a id ="btn_return"  style="display:none" href="javascript:history.go(-1);" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                        	<td>所属代理商</td>
	                        <td>
	                            <input id="agentIdSch" name="agentIdSch" class="textbox" style="width: 80px; height: 22px;" />
	                        </td>
	                        
                        	<td>学校名称</td>
							<td>
								<input id="schoolId"  name="schoolId"  type="hidden"/> 
								<input id="schoolIdSch"  name="schoolIdSch" style="width: 100px; height: 22px;"/>
							</td>
							<td>班级名称</td>
							<td>
								<input id="classId"  name="classId"  />
								<input id="classIdSch"  name="classIdSch" style="width: 80px; height: 22px;" type="hidden" />
							</td>
							<!-- <td>用户名称</td>

                            <td>
                              <input id="studentIdSch" name="studentIdSch" class="textbox" style="width: 120px; height: 22px;" />
                            </td>  -->

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
                     <input id="userid"  name="user.id"   type="hidden" />
                     <input id="studentId" value="0" name="studentId"  type="hidden" /><!-- student Tr must have which will be send to parent page -->
                     <input id="id" readonly="readonly" name="id" value="0"   type="hidden"/>
                     <input id="userPwd" name="user.userPwd"  type="hidden" />
                     <input id="roleProperty" name="user.roleProperty"  type="hidden" />
                    <fieldset>

                        <legend> 基本信息--<span id="operateMode_title">添加模式</span> </legend>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th>学号</th>
                                    <td>
                                        <input id="studentCode"  name="studentCode" class="textbox easyui-validatebox" required = "required"  style="height: 22px;"  />
                                    </td>
                                </tr>
                                <tr>
                                    <th>学生姓名</th>
                                    <td>
                                        <input id="trueName"  name="user.trueName" class="textbox easyui-validatebox" style="height: 22px;" required = "required" />
                                    </td>
                                </tr>
                                
                                  <tr>
                                    <th>学生性别</th>
                                    <td>
                                        <input id="sex"  name="user.sex" class="textbox" required = "required" style="height: 22px; width: 40px"  />
                                    </td>
                                </tr>
                             
                                <tr>
                                    <th>详细地址</th>
            							<td colspan="3"><textarea cols="100" id="addressDetail" name="user.addressDetail" class="textbox" style="padding-top:3px;height: 38px; width:90%" ></textarea></td>
                                </tr>
                              
                                  <tr>
                                    <th>出生日期</th>
                                    <td>
                                        <input id="birthday"  name="user.birthday" class="easyui-datebox" style="height: 22px;"  />
                                    </td>
                                </tr>
                                <tr>
                                <th>操作</th>
                                <td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> 
                                <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <script type="text/javascript">
        var userId = <% out.print(UserContext.getInstance().getUser().getId());%>; //当前登录人ID 园长  
        var cId = <%=request.getParameter("cId")%>;//场地管理传入班级ID
        var classId= <%=request.getParameter("classId")%>;//家长回传值
        var studentBack = <%=request.getParameter("studentId")%>;
        var studentId = $("#studentId").val();
        var studentUrl = '';
        var schoolId = <%=request.getParameter("schoolId")%>;agentIdSch
        var agentIdSch = <%=request.getParameter("agentIdSch")%>;
        $.getJSON(ctx + 'listAllUser.do', {rolePropertySch: 8}, function(agJson) {
            $('#agentIdSch').combobox({
                data: agJson.rows,
                valueField: 'id',
                textField: 'trueName',
                onLoadSuccess: function(data) {
                    if(agentIdSch)
                    {
                    	$("#agentIdSch").combobox('select', agentIdSch);
                    } 
                },
                onSelect: function(row) {
                    count = false;
                    agId = row.id;
                    $("#agentIdSch").val(agId);
                	
                   
                    grid.datagrid('load', fw.serializeObject($('#searchForm')));
                    $.getJSON(ctx+'listScSchool.do',{agentIdSch: agId},function(parentsJson) 
                            {
                    	        CSsex = $("#schoolIdSch").combobox({
                                    valueField: 'schoolId',
                                    textField: 'schoolName',
                    				panelHeight:'auto',  
                    				data:parentsJson.rows,
                    				onLoadSuccess: function(data) {
                                        if(schoolId)
                                        {
                                        	$("#schoolIdSch").combobox('select', schoolId);
                                        }
                                    },
                    				onSelect:function(params){
                    	  	         	schoolId=params.schoolId;
                    	  	         	$("#schoolIdSch").val(schoolId);
                    	  	         	$("#schoolId").val(schoolId);
                    	  	         	grid.datagrid('load',fw.serializeObject($('#searchForm')));
                    	  	          	$.getJSON(ctx+'listScClass.do?placeTypeIdSch=69',{schoolIdSch:schoolId},function(parentsJson) {
                    	  	        	CSclass = $("#classId").combobox({
                                            valueField: 'id',
                                            textField: 'className',
                        					panelHeight:'auto',  
                        					data:parentsJson.rows,
                        					onLoadSuccess: function(data) {
                       	                    if(classId)
                       	                    {
                       	                    	$("#classId").combobox('select', classId);
                       	                    	grid.datagrid('load',fw.serializeObject($('#searchForm')));
                       	                    }
                        	                },
                        					onSelect:function(params){
                        		  	         	classId=params.id;
                        		  	         	$("#classIdSch").val(classId);
                        		  	         	grid.datagrid('load',fw.serializeObject($('#searchForm')));
                        		  	         }
                         				});
                    	  	          });
                    	  	        }
                    	            });     
                            }); 
                    
                    
                    
                }
            });
        });
        
        
        

       
        
        

        

        
        
        //场地管理传入班级ID
          if(cId){
        	classId = cId;
      		studentUrl = ctx + 'listScStudent.do?classIdSch='+cId;

      		$('#operate_parents').hide();
      		$('#classIdTD').hide();
      		$('#btn_return').show();
              $("#id").attr("value", 0);
      	}
/*         
     	 $.getJSON(ctx+'listAllUser.do',{roleProperty:0},function(studentsJson) {
          $("#studentIdSch").combobox({
  			data:studentsJson.rows,
  			valueField:'id',
  	        textField:'trueName',
  	        selected:studentsJson.rows[0],
  	        filter: function(q, row){
          		var opts = $(this).combobox('options');
          		return row[opts.textField].indexOf(q) == 0;
          	},
  	        onSelect:function(params){
  	         	schoolId=params.schoolId;
  	        }
  			})
     		}) */
         
				grid = $('#grid').datagrid({
                        fit: true,
                        border: false,
                        collapsible: false,
                        rownumbers: true,
                        pagination: true,
                        singleSelect: true,
                        rownumbers: true,
                        url: ctx + 'listScStudent.do',
                        columns: [
                            [{
                                field: 'studentCode',
                                title: '学号',
                                sortable: true
                            }, {
                                field: 'schoolId',
                                title: '学校ID',
                                hidden: 'true'
                            }
                            , {
                                field: 'user',
                                title: '姓名',
                                formatter:function(value,row,index){
                                		if(row.user!=null)
    		            				return row.user.trueName;
                                		else{
                                			return "";
                                		}
    		            		}
                            },
                			{
                                field: 'sex',
                                title: '性别',
                                formatter:function(value,row,index)
                                {
    		            			if(row.user.sex)	 
                                	{return "男" }
    		            			else
    		            			{return "女"}
    		            		}
                            },
                            {
                                field: 'adressDetail',
                                title: ' 详细地址',
                                formatter:function(value,row,index){
                                	if(row.user!=null)
    		            				return row.user.addressDetail;
                                	else 
                                		return "";
    		            	} },
                            {
                                field: 'birthday',
                                title: '出生日期',
                                formatter:function(value,row,index){
                                	
                                	if(row.user!=null && row.user.birthday!=null) 
                                		return row.user.birthday.substring(0,10);
                                	else 
                                		return null;
    		            	} }
                            ,{
                                field: 'createdTime',
                                title: '创建时间',
                                formatter:getDate,
    	            			sortable: true
                            },{
                                field: 'editedTime',
                                title: '编辑时间',
                                formatter:getDate,
    	            			sortable: true
                            }
                            
                            ]
                        ],
                        onLoadSuccess: function(data) {
                            $('.iconImg').attr('src', fw.pixel_0);
                        },
                        onClickRow: function(index, row) {
                        	$("#operate_parents").linkbutton('enable');
                            $("#form").form("myLoad", row);
                            $("#operateMode_title").html("修改模式");
                        }
                    });
        	    grid.datagrid('reload');
        	  

        /*****************************************************************************
		* @author         :xiaoren 
		* @version        : V1.0
		* @param          : 'listScStudent.do?classIdSch='+classId 
		* @Description    : have choose the class
		*					
		* @Date           : 2015年11月13日 上午9:24:42    
		******************************************************************************/
         $(function() {             
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
                            	//
                                var idValue = $("#form input[name='id']").val();
                                var studentIdValue = $("#form input[name='studentId']").val();
                                $.post(ctx + "delScStudent.do", {
                                    id: idValue,
                                    studentId : studentIdValue
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
                                //修改
                                $.post(ctx + "updateScStudent.do", fw.serializeObject($('form')), function(result) {
                                    if (result.success) {
                                        $.messager.show({
                                            title: message.title.normal,
                                            msg: message.update_success,
                                            timeout: message.timeout,
                                            showType: message.showType
                                        });

                                        grid.datagrid("reload");

                                        //重新获取form信息内容
                                        $("#form").form("load", ctx + "getScStudentById.do?id=" + idValue);
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
                                    $.post(ctx + "addScStudent.do", fw.serializeObject($('form')), function(result) {
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
                


                $("#operate_parents").click(function() {
					if( $("#operate_parents").linkbutton('options').disabled == true)
					{
					}else
					{
						if( $("#id").val().trim().length>0 ){
	    					window.location.href="AdminStudentParents.jsp?studentId="+$("#studentId").val()+"&classId="+classId+"&schoolId="+schoolId+"&agentIdSch="+$("#agentIdSch").val();
	    				}else{
	    					$.messager.show({
	    						title : message.title.normal,
	    						msg : message.no_record,
	    						timeout : message.timeout,
	    						showType : message.showType
	    					});
	    				}
					}
    			});
                
                $("#btn_export").click(function() {
                		window.open('../../../template/importStudentTemplate.xlsx');
                });
                
                $("#btn_import").click(function() {
                        var btn = $(this);
                        url = '../../pluploader.jsp?moduleId=sc&funcId=importStudent'+'&userId=' + userId;

                        var dialog = fw.modalDialog({
                            title: '选择文件',
                            url: url,
                            width: 700,
                            height: 500,
                            buttons: [{
                                text: '确认选择',
                                handler: function() {
                                    var rows = dialog.find('iframe').get(0).contentWindow.returnFormValue(dialog);
                                    rows[0].classId= classId;
                                    $.post(ctx + "importStudent.do",rows[0], function(result) {
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
                
            }); 
        </script>
    </body>

    </html>