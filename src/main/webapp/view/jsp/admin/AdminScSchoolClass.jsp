<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@page import="com.ryt.web.entity.user.User"%>
<%@page import="org.durcframework.core.UserContext"%>
<%@ taglib prefix="rms" uri="/rms" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>班级管理</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>
    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a>
                            <!-- <a href="javascript:history.go(-1);" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a> -->
                            <a id="goback" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
                            </td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>场所名称</td>
                            <td><input name="classNameSch" class="textbox" style="width: 120px; height: 22px;" /></td>
                            <td>创建时间</td>
                            <td><input name="createdStartSch" class="easyui-datebox" style="width: 100px" />
                                -
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
                        <div style="display:none">
			                <input name="schoolId" id="schoolId" value="<%=request.getParameter("schoolId")%>"/>
			                <input name="agentId" id="agentId" value="<%=UserContext.getInstance().getUser().getId()%>"/>
		                </div>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th>ID</th>
                                    <td><input id="id" readonly="readonly" value="0"  name="id" class="textbox"   style="height: 22px;width:40px" /></td>
                                    <th>场所名称</th>
                                    <td><input id="className" name="className" class="textbox easyui-validatebox"    required="true"  style="height: 22px;" /></td>
                                </tr>
                                 <tr>
                                                                     <th>场地类型</th>
                                    <td>
                                        <input id="placeTypeId" name="placeTypeId" class="easyui-combobox" style="width:120px"/>
										<a href="#" onclick="$('#placeTypeId').combobox('clear')">清除</a>
                                    </td>
                                    <th>年级</th>
                                    <td><input id="grade"  name="grade" class="textbox easyui-validatebox" data-options="validType:'integer'"  style="height: 22px;" /></td>
                                    
                                <!--     <th>最大人数</th>
                                    <td><input id="maxAmount"  name="maxAmount" class="textbox easyui-validatebox" data-options="validType:'integer'"  style="height: 22px;" /></td> -->
                                </tr>
                                 <tr>
                                    <th>当前人数</th>

                                    <td><input id="amount"  name="amount" class="textbox easyui-numberbox"   style="height: 22px;" /></td>

                                    <th>开班日期</th>
                                    <td><input id="startDate" name="startDate" data-options="disabled:false" editable="false" class="easyui-datebox"  required="true" /></td>
                                </tr>
                                <tr>

                                     <th>流选择</th>
                                    <td><input id="streamId"  name="streamId" class="textbox easyui-validatebox" editable="false"  style="height: 22px;" /></td>
                                      <th>序号</th>
                                    <td colspan="3"><input id="sortId"  name="sortId" class="textbox easyui-numberspinner" min="0"   style="height: 22px; width:50px" /></td>
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
        var sysClassTypeId = ClassTypeId.ScClass;//场地类型的字段类型
    	var placeTypedic = new Array(); 
    	//ctx + 'listSysClass.do?sysClassTypeIdSch='+sysClassTypeId
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
        var cbox;
        var scIdSch=<%=request.getParameter("schoolId")%>;
        $.getJSON(ctx+'listSchoolStream.do',{schoolId:scIdSch},function(json) {  	
        	cbox=$('#streamId').combobox({
    	        data : json.rows,
    	        valueField:'id',
    	        textField:'title',
    	        formatter: function(row){
    	        	if(row.deviceName){
    	        		var title_deviceName =row.title+" "+row.deviceName;
    	        	}else{
    	        		var title_deviceName =row.title;
    	        	}
    	    		return title_deviceName;
    	    	}
        	});
        })
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
                    url:ctx+'listScClass.do?schoolIdSch='+<%=request.getParameter("schoolId")%>,
                    columns:[[				
											{field:'id',title:'ID',sortable:true}
            		            			,{field:'className',title:'场所名称',sortable:true}
            		            			,{field:'grade',title:'年级',sortable:true}
            		            			,{field:'amount',title:'当前人数',sortable:true}
            		            			,{field:'startDate',title:'开班日期',formatter:getDate,sortable:true}
            		            			,{  field: 'placeTypeId',
            		                            title: '场地类型',
            		                            formatter: function(value, row) {
            		                            	var str = placeTypedic[value];
            		                            	return str;
            		                            },sortable:true}
            		            			,{field:'streamId',title:'摄像头编号',sortable:true}
            		            			,{field:'sortId',title:'序号',sortable:true}
            		            			,{field:'createdTime',title:'创建时间',formatter:getDate,sortable:true}
            		                    ]],
                    onLoadSuccess : function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                        
                        
                    },
                    onClickRow : function(index, row) {
                        $("#form").form("load", row);
                        $("#operateMode_title").html("修改模式");
                        $.getJSON(ctx+'listSchoolStream.do',{schoolId:scIdSch,classId:row.id},function(json) {
                        	cbox=$('#streamId').combobox({
                    	        data : json.rows,
                    	        valueField:'id',
                    	        textField:'title',
                    	        editable: false, //不允许手动输入
                    	        formatter: function(row){
                    	        	if(row.deviceName){
                    	        		var title_deviceName =row.title+" "+row.deviceName;
                    	        	}else{
                    	        		var title_deviceName =row.title;
                    	        	}
                    	    		return title_deviceName;
                    	    	},
                                onLoadSuccess: function () { //数据加载完毕事件
                                    var data = $('#streamId').combobox('getData');
	                                for (var i = 0, len = data.length; i < len; i++) {
	                                    if (data[i].id == row.streamId) {
	                                        $("#streamId").combobox('select', data[i].id);
	                                    }
									}
                                }
                        	})
                        })
                    }
                    
                });
                //添加操作
                $("#btn_add").click(function() {
                    $("#operateMode_title").html("添加模式");
                    $('#grid').datagrid("unselectAll");
                    $("#id").attr("value", 0);
                    $("#form").form("reset");
                    $.getJSON(ctx+'listSchoolStream.do',{schoolId:scIdSch},function(json) {  	
                    	$('#streamId').combobox({
                	        data : json.rows,
                	        valueField:'id',
                	        textField:'title',
                	        formatter: function(row){
                	        	if(row.deviceName){
                	        		var title_deviceName =row.title+" "+row.deviceName;
                	        	}else{
                	        		var title_deviceName =row.title;
                	        	}
                	    		return title_deviceName;
                	    	},
                    	});
                    	cbox.combobox("clear");
                    })
                    
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
                                $.post(ctx+"delScClass.do", {
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
                            //修改
                            
                            $.post(ctx+"updateScClass.do", fw.serializeObject($('form')), function(result) {
                                if (result.success) {
                                    $.messager.show({
                                        title : message.title.normal,
                                        msg : message.update_success,
                                        timeout : message.timeout,
                                        showType : message.showType
                                    });
 							
                                    grid.datagrid("reload");
            
                                    //重新获取form信息内容
                                    $("#form").form("load", ctx+"getScClassById.do?id=" + idValue);
                                }
                            }, 'json');
                        } else {
                            if(idValue.trim().length == 0){
                                $.messager.show({
                                    title : message.title.normal,
                                    msg : message.add_button_click,
                                    timeout : message.timeout,
                                    showType : message.showType
                                });
                            }else{
                                //添加
                                if($("#streamId").combobox('getText')==""){
                                	 $.messager.show({
                                         title : message.title.normal,
                                         msg : message.chStream,
                                         timeout : message.timeout,
                                         showType : message.showType
                                     });
                                }else{
                                	 $.post(ctx+"addScClass.do", fw.serializeObject($('form')), function(result) {
                                     	console.log(fw.serializeObject($('form')));
                                     	console.log(result);
                                         if (result.success) {
                                             $.messager.show({
                                                 title : message.title.normal,
                                                 msg : message.add_success,
                                                 timeout : message.timeout,
                                                 showType : message.showType
                                             });
                                             grid.datagrid("reload");
                                             $("#form").form("reset");
                                             $.getJSON(ctx+'listSchoolStream.do',{schoolId:scIdSch},function(json) {  	
                                             	$('#streamId').combobox({
                                         	        data : json.rows,
                                         	        valueField:'id',
                                         	        textField:'title',
                                         	        formatter: function(row){
                                         	        	if(row.deviceName){
                                         	        		var title_deviceName =row.title+" "+row.deviceName;
                                         	        	}else{
                                         	        		var title_deviceName =row.title;
                                         	        	}
                                         	    		return title_deviceName;
                                         	    	}
                                             	});
                                             	//cbox.combobox("clear");
                                             })
                                         }
                                     }, 'json');
                                }
/*                                 */
                            }
                        }
                    }
                })
                //重置操作
                $("#operateMode_reset").click(function() {
                    var idValue = $("#form input[name='id']").val();
                    if (idValue > 0) {
                        $("#form").form("load",ctx+"getScClassById.do?id=" + idValue);
                    } else {
                        $("#form").form("reset");
                    }
                })

            });
	        $("#goback").click(function() {
					window.location.href="AdminScSchool.jsp"
			});
        
        </script>
    </body>
</html>