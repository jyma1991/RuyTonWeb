<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="org.durcframework.core.UserContext"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>场所管理</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>

    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                	<table>
                        <tr>
                        	<td><a onclick="setAuthTeacher();" id="btn_teacher_edit" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-edit'">设置老师</a></td>
                            <td><a onclick="setParents();"id="btn_parent_edit" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-edit'">设置家长</a></td>
                            <td><a id="StudentManager" class="easyui-linkbutton" data-options="group:'g1',iconCls:'ext-icon-application_form_edit'">管理班级学生</a></td>
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
                        </tr>
                    </table>
                    <table>

                        <tr>
                            <td>班级名称</td>
                            <td>
                                <input name="classNameSch" class="textbox" style="width: 120px; height: 22px;" />
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
            <div data-options="region:'east',split:true,border:false" style="width: 40%; padding: 5px;">
                <form id="form" name="form" method="post" class="form">
                    <fieldset>
                        <legend> 基本信息<span id="operateMode_title"></span> </legend>
                                    <input type="hidden" id="schoolId" value="0" name="schoolId" class="textbox" style="height: 22px;" />
                                    <input type="hidden" id="agentId" value="0" name="agentId" class="textbox" style="height: 22px;" />
                                    <input type="hidden" id="deviceId" value="0" name="deviceId" class="textbox" style="height: 22px;" />
                        			<input type="hidden" id="streamId" value="0" name="streamId" class="textbox" style="height: 22px;" />
                        <table class="table">
                            <tbody>
								  <tr>
                                    <th style="width:100px;">ID</th>
                                    <td>
                                        <input id="id" readonly="readonly" value="0" name="id" class="textbox" style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>班主任</th>
                                    <td><input id="managerId" name="managerId" class="easyui-combobox" style="width:120px"/>
									<a href="#" onclick="$('#managerId').combobox('clear')">清除</a></td>
                                </tr>
                                <tr>
                                    <th>场所名称</th>
                                    <td>
                                        <input id="className" name="className" class="textbox easyui-validatebox" required="true"  style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>备注</th>
                    				<td colspan="3">
                      					<textarea id="remark" wrap="off" name="remark" validType="length[0,150]"  class="textbox" style="height: 22px;width:100%;white-space: pre-wrap;" wrap="hard"></textarea>
                    				</td>
                                </tr>
                                <tr>
                                    <th>年级</th>
                                    <td>
                                        <input id="grade" value="0" name="grade" class="textbox" style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>现有人数</th>
                                    <td>
                                        <input id="amount" value="0" name="amount" class="textbox" style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>开班日期</th>
                                    <td>
                                        <input id="startDate" name="startDate" class="easyui-datebox"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>视频开放时间</th>
                                    <td>
                                        <input id="videoStart" name="videoStart" class="easyui-timespinner" data-options="max:'11:59'" style="width:100px;"/>
                                        <a href="javascript:void(0)" onclick="setAllDay()" class="easyui-linkbutton">全日制</a>
                                    </td>
                                </tr>
                                <tr>
                                    <th>视频结束时间</th>
                                    <td>
                                        <input id="videoStop" name="videoStop" class="easyui-timespinner" data-options="min:'12:00',max:'23:59'" style="width:100px;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>场所类型</th>
                                    <td>
                                        <input id="placeTypeId" name="placeTypeId" class="easyui-combobox" style="width:120px"/>
										<a href="#" onclick="$('#placeTypeId').combobox('clear')">清除</a>
                                    </td>
                                </tr>
                               	<tr>
	                                <th>操作</th>
	                                <td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <script type="text/javascript">
        var btnStudentManagerEnable = true;
        var sysClassTypeId = ClassTypeId.ScClass;
        var userId = <% out.print(UserContext.getInstance().getUser().getId());%>;
        
        function InitManagerId(row){
	    	//设置班主任 初始化下拉框数据
	    	$.getJSON(ctx + 'listAllScClassTeacher.do?classIdSch='+row.id, function (classJson) {
			var managerId =	 $('#managerId').combobox({
					editable : false,
					panelHeight : 'auto',
					valueField : 'teacherId',
					textField : 'teacherName',
					data : classJson.rows,
    				onLoadSuccess:function(data) {
    					for(var i =0; i<data.length; i++){
    						if(data[i].teacherId==row.managerId){
    							 $('#managerId').combobox('select', data[i].teacherId);
    						}
    					}
                    }
				});
        	})
        };
        
       
        //当前登录人的ID 该页面应该是园长 即schoolId
            $(function() {
            	var placeTypedic = new Array(); 
            	var placeTypeId = $('#placeTypeId').combobox({
    				editable : false,
    				panelHeight : 'auto',
    				valueField : 'id',
    				textField : 'className',
    				url : ctx + 'listSysClass.do?sysClassTypeIdSch='+sysClassTypeId,
    				onLoadSuccess:function(data) {
    					for(var i =0;i<data.length;i++){
    						placeTypedic[data[i].id]=data[i].className;
    					}
                    },
    			});
            	
                var gird;
                grid = $('#grid').datagrid({
                    fit: true,
                    border: false,
                    collapsible: false,
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    rownumbers: true,
                    url: ctx + 'listScClass.do?schoolIdSch='+userId,
                    columns: [
                        [{
                            field: 'id',
                            title: 'ID',
                            sortable:'true'
                        }, {
                            field: 'className',
                            title: '名称',
                            sortable:'true'
                        }, {
                            field: 'grade',
                            title: '年级',
                            sortable:'true'
                        }, {
                            field: 'amount',
                            title: '现有人数',
                            sortable:'true'
                        }, {
                            field: 'startDate',
                            title: '开班日期',
                            sortable:'true',
                            formatter:getDate
                        }, {
                            field: 'remark',
                            title: '说明',
                            sortable:'true'
                        }, {
                            field: 'placeTypeId',
                            title: '场所类型',
                            sortable:'true',
                            formatter: function(value, row) {
                            	var str = placeTypedic[value];
                            	return str;
                            }
                        }, {
                            field: 'streamId',
                            title: '摄像头编号',
                            sortable:'true'
                        }, {
                            field: 'videoStart',
                            title: '视频开放时间',
                            sortable:'true',
                            formatter:function(value,row){
                            	if(value){
                            		var videoEnd = getDate(row.videoStop) == null ? "24:00":getDate(row.videoStop).substring(0,5);
                            		return getDate(row.videoStart).substring(0,5)+"-"+videoEnd;
                            	}else{
                            		return "";
                            	}
                            	
                            }
                        }, {
                            field: 'createdTime',
                            title: '添加时间',
                            sortable:'true',
                            formatter:getDate
                        }]
                    ],
                    onLoadSuccess: function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    },
                    onClickRow: function(index, row) {
                    	$('#videoStop').timespinner('setValue', '');
                    	$('#videoStart').timespinner('setValue', '');
                    	$("input[name='videoStop']").val("");
                        $("#form").form("load", row);
                        InitManagerId(row);
                        if(placeTypedic[row.placeTypeId]=="教室"){
                        	$('#StudentManager').linkbutton('enable');
                        	btnStudentManagerEnable = true;
                        }else{
                        	$('#StudentManager').linkbutton('disable');
                        	btnStudentManagerEnable = false;
                        }
                        $("#operateMode_title").html("--修改模式");
                        
                    }
                });

                //确认提交操作
                $("#operateMode_submit").click(function() {
                        var idValue = $("#form input[name='id']").val();
                        if ($('#form').form('validate')) {
                        	
                            if (idValue > 0) {
                                //修改
                                $.post(ctx + "updateScClass.do", fw.serializeObject($('form')), function(result) {
                                	console.log(result);
                                    if (result.success) {
                                        $.messager.show({
                                            title: message.title.normal,
                                            msg: message.update_success,
                                            timeout: message.timeout,
                                            showType: message.showType
                                        });

                                        grid.datagrid("reload");
                                        //重新获取form信息内容
                                        $("#form").form("load", ctx + "getScClassById.do?id=" + idValue);
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
                                    //添加
                                    $.post(ctx + "addScClass.do", fw.serializeObject($('form')), function(result) {
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
                        $("#form").form("load", ctx + "getScClassById.do?id=" + idValue);
                    } else {
                        $("#form").form("reset");
                    }
                })
                
                //点击按钮进入本班级学生管理界面
                $("#StudentManager").click(function() {
                    if ($("#id").val() > 0 && btnStudentManagerEnable) {
                        window.location.href = "ScStudent.jsp?cId=" + $("#id").val();
                    } else {
                        $.messager.show({
                            title: message.title.normal,
                            msg: "请选择一个教室进行操作！",
                            timeout: message.timeout,
                            showType: message.showType
                        });
                    }
                });
            });
            function setAuthTeacher() {
                var btn = $(this);
                var schoolId = $("#schoolId").val();
                var classId = $("#id").val();
                var streamId = $("#streamId").val();
                var url = ctx+"view/jsp/sc/ScAuthUserStream.jsp?schoolId="+schoolId+"&classId="+classId+"&userRole=teacher&streamId="+streamId;
                var rows = $('#grid').datagrid('getSelections');
                if (rows <= 0) {
                    $.messager.show({
                        title: message.title.normal,
                        msg: message.grid_select,
                        timeout: message.timeout,
                        showType: message.showType
                    });
                } else {
	                var dialog = fw.modalDialog({
	                    title: '设置老师',
	                    url: url,
	                    width: 400,
	                    height: 500,
	                    buttons: [{
	                        text: '确认选择',
	                        handler: function() {
	                            dialog.find('iframe').get(0).contentWindow.submitForm(dialog, $.messager);
	                        }
	                    }, {
	                        text: '取消选择',
	                        handler: function() {
	                            dialog.find('iframe').get(0).contentWindow.closeDialog(dialog);
	                        }
	                    }]
	                });
	                $(dialog.find('iframe').get(0)).css({
	                	"height":"100%"
	                })
                }
            }
            
            function setParents(){
            	var btn = $(this);
                var schoolId = $("#schoolId").val();
                var classId = $("#id").val();
                var streamId = $("#streamId").val();
                //获取场地类型名称 
                var placeTypeName = $('#placeTypeId').combobox('getText');
                var url = ctx;
                if(placeTypeName == '公共场地'){
                	url += "view/jsp/sc/ScPublicClassAuthUserStream.jsp?schoolId="+schoolId+"&classId="+classId+"&userRole=parent&streamId="+streamId;
                }else{
                	url += "view/jsp/sc/ScAuthUserStream.jsp?schoolId="+schoolId+"&classId="+classId+"&userRole=parent&streamId="+streamId;
                }
                var rows = $('#grid').datagrid('getSelections');
                if (rows <= 0) {
                    $.messager.show({
                        title: message.title.normal,
                        msg: message.grid_select,
                        timeout: message.timeout,
                        showType: message.showType
                    });
                } else {
	                var dialog = fw.modalDialog({
	                    title: '设置家长',
	                    url: url,
	                    width: 400,
	                    height: 500,
	                    buttons: [{
	                        text: '确认选择',
	                        handler: function() {
	                            dialog.find('iframe').get(0).contentWindow.submitForm(dialog, $.messager);
	                        }
	                    }, {
	                        text: '取消选择',
	                        handler: function() {
	                            dialog.find('iframe').get(0).contentWindow.closeDialog(dialog);
	                        }
	                    }]
	                });
	                $(dialog.find('iframe').get(0)).css({
	                	"height":"100%"
	                })
                }
            	
            }
            
            function setAllDay(){
            	$("#videoStart").timespinner('setValue', '00:00');
            	$("#videoStop").timespinner('setValue', '23:59');
            }
        </script>
    </body>

    </html>