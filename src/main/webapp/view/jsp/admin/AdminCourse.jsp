<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/rms" prefix="rms" %>
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
                        	<rms:role operateCode="add">
                            </rms:role>
                            <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
                            <rms:role operateCode="delete">
                            </rms:role>
                            <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
                        	<!--  <td><a id="btn_export"  href="#" class="easyui-linkbutton btn_scStudent" data-options="iconCls:'ext-icon-page_white_excel'">下载模板</a></td>
                            <td><a id="btn_import"  href="#" class="easyui-linkbutton btn_scStudent" data-options="iconCls:'ext-icon-page_excel'">导入(仅*.xlsx)</a></td>-->
                        </tr>
                    </table>
                    <table>
                        <tr>
                        	<td>代理商</td>
                            <td><input id="agentIdSch" name="agentIdSch" class="textbox" editable="false"  style="width: 120px; height: 22px;" /></td>
                        	<td>学校</td>
                            <td><input id="schoolName" name="schoolName" class="textbox" editable="false"  style="width: 120px; height: 22px;" /></td>
                            <td>课程名称</td>
                            <td><input name="courseNameSch" class="textbox" style="width: 120px; height: 22px;" /></td>
                            <td>创建时间</td>
                            <td><input name="createdStartSch" class="easyui-datebox" style="width: 100px" />
                                -
                                <input name="createdEndSch" class="easyui-datebox" style="width: 100px" />
                            </td>
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="searchCondition()">过滤</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="resetSearch()">重置过滤</a></td>
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
                        <input type="hidden" id="schoolId" name="schoolId" class="textbox"  style="height: 22px;" />
                        <input type="hidden" id="userId" name="userId" class="textbox" style="height: 22px;" />
	                    <input type="hidden" id="userName" name="userName" class="textbox" style="height: 22px;" />
	                    <input type="hidden" id="uuid" name="uuid" class="textbox" value="0" style="height: 22px;" />
	                    <input type="hidden" id="isDeleted" value="0" name="isDeleted" class="textbox" style="height: 22px;" />
	                    <input type="hidden" id="operaterId" value="0" name="operaterId" class="textbox" style="height: 22px;" />
                        <table class="table">
                            <tbody>
                                                                                                                                                                                                                                                            <tr>
                                    <th>ID</th>
                                    <td><input id="id" readonly="readonly" name="id" class="textbox"  value=0    style="height: 22px; background:#eee;width:50px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                <tr>
                                    <th>课程</th>
                                    <td><input id="courseName" name="courseName" class="textbox easyui-validatebox" required="true"  style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>时长</th>
                                    <td><input id="length" value="0" name="length" class="textbox"   style="height: 22px; width:50px;" />&nbsp;分钟
                                    </td>
                                </tr>
                                <!--                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>价格</th>
                                    <td><input id="price" value="0" name="price" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                 <tr>
                                    <th>老师</th>
                                    <td><input id="teacherId" value="0" name="teacherId" class="textbox"   style="height: 22px; background:#eee;" readonly/></td>
                                </tr> -->
                                <tr>                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>备注</th>
                                    <td>
                                    	<textarea cols="100" id="remark" name="remark" class="textbox"   style="width:300px;height: 44px;line-height:22px;" ></textarea>                        	
                                    </td>
                                </tr>
                                <tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                                	<th>序号</th>
                                	<td><input id="sortId" name="sortId"
                                            class="easyui-validatebox easyui-numberspinner"
                                            data-options="min:0,max:100,required:true" value="0"   style="width: 50px;" />
                                    </td>
                                </tr>  
								<tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              <th>操作</th>
                               	 	<td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <script type="text/javascript">
        	var CS;
        	var SS;
        	var gird;
            $(function() {
                
               
                grid=$('#grid').datagrid({
                    fit:true,
                    border : false,
                    collapsible : false,
                    rownumbers : true,
                    pagination : true,
                    singleSelect : true,
                    rownumbers : true,
                    url:'',//ctx+'listScCourse.do?schoolIdSch=0',
                    columns:[[
		            						{field:'id',title:'ID',sortable:true}
            		            			,{field:'courseName',title:'课程',sortable:true}
            		            			,{field:'length',title:'时长',sortable:true}
            		            			//,{field:'price',title:'价格',align:"right",sortable:true}
            		            			//,{field:'teacherId',title:'老师',formatter: function(value, row, index){	            				
              		            			//		return ''; 
              		            			//}}
            		            			,{field:'remark',title:'备注',sortable:true}
            		            			,{field:'sortId',title:'序号',sortable:true}
            		            			,{field:'createdTime',title:'创建时间',formatter: function(value, row, index){
              		            				return value.substring(0,10);
              		            			},sortable:true}
            		                    ]],
                    onLoadSuccess : function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    },
                    onClickRow : function(index, row) {
                        $("#form").form("load", row);
                        $("#operateMode_title").html("修改模式");
                    }
                });
              //添加操作
                $("#btn_add").click(function() {
                	var schoolIdSch = $("input[name='schoolName']").val();
                	if(schoolIdSch == null || schoolIdSch == 0){
                		$.messager.show({
                            title : message.title.normal,
                            msg : '请选中学校后在进行添加操作！！',
                            timeout : message.timeout,
                            showType : message.showType
                        });
                		return;
                	}
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
                            title : message.title.normal,
                            msg : message.grid_select,
                            timeout : message.timeout,
                            showType : message.showType
                        });
                    } else {
                        $.messager.confirm(message.title.askTitle, message.dele_comfirm, function(r) {
                            if (r) {
                                var idValue = $("#form input[name='id']").val();
                                $.post(ctx+"delScCourse.do", {
                                    id : idValue
                                }, function(result) {
                                    if (result.success) {
                                        $.messager.show({
                                            title : message.title.normal,
                                            msg : message.dele_success,
                                            timeout : message.timeout,
                                            showType : message.showType
                                        });
                                        var schoolIdSch = $("input[name='schoolName']").val();
                                        grid.datagrid({url:ctx+'listScCourse.do?schoolIdSch='+schoolIdSch});
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
                            $.post(ctx+"updateScCourse.do", fw.serializeObject($('form')), function(result) {
                                if (result.success) {
                                    $.messager.show({
                                        title : message.title.normal,
                                        msg : message.update_success,
                                        timeout : message.timeout,
                                        showType : message.showType
                                    });
                                    var schoolIdSch = $("input[name='schoolName']").val();
                                    grid.datagrid({url:ctx+'listScCourse.do?schoolIdSch='+schoolIdSch});
                                    //重新获取form信息内容
                                    $("#form").form("load", ctx+"getScCourseById.do?id=" + idValue);
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
                            	var schoolIdSch = $("input[name='schoolName']").val();
                            	if(schoolIdSch == null || schoolIdSch == 0){
                            		$.messager.show({
                                        title : message.title.normal,
                                        msg : '请选中学校后在进行添加操作！！',
                                        timeout : message.timeout,
                                        showType : message.showType
                                    });
                            		return;
                            	}else{
                            		$("input[name='schoolId']").val(schoolIdSch);
                            	}
                                //添加
                                $.post(ctx+"addScCourse.do", fw.serializeObject($('form')), function(result) {
                                    if (result.success) {
                                        $.messager.show({
                                            title : message.title.normal,
                                            msg : message.add_success,
                                            timeout : message.timeout,
                                            showType : message.showType
                                        });
                                        var schoolIdSch = $("input[name='schoolName']").val();
                                        grid.datagrid({url:ctx+'listScCourse.do?schoolIdSch='+schoolIdSch});
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
                        $("#form").form("load",ctx+"getScCourseById.do?id=" + idValue);
                    } else {
                        $("#form").form("reset");
                    }
                })
                
               //初始化代理商
               $.getJSON(ctx+'listAllScAgent.do',function(agentJson){
            	   console.log(agentJson)
                   if (agentJson.rows.length == 0) {
                       CS = $('#agentIdSch').combobox();
                       CS.combobox("clear");
                       //DS.combobox('loadData',{schoolId:0,schoolName:" "});
                       //ES.combobox('loadData',{id:0,className:" "});
                   } else {
                	   CS = $('#agentIdSch').combobox({
                           data: agentJson.rows,
                           valueField: 'agentId',
                           textField: 'trueName',
                           DropDownStyle: 'DropDownList',
                           onSelect: function(row) {
                               //初始化学校
                               $.getJSON(ctx+'listAllScSchool.do',function(schooleJson) {
                    	            	if(schooleJson.rows.length==0){
                    	            		SS = $('#schoolName').combobox();
                    	            		SS.combobox("clear");
                    	            		//DS.combobox('loadData',{schoolId:0,schoolName:" "});
                    	                 	//ES.combobox('loadData',{id:0,className:" "});
                    	            	}else{
                    	            		SS = $('#schoolName').combobox({
                    	            		    data:schooleJson.rows,
                    	            	        valueField:'schoolId',
                    	            	        textField:'schoolName',
                    	            	        DropDownStyle:'DropDownList',
                    	            	        onSelect:function(row){
                    	            	        	$('#grid').datagrid("unselectAll");
                    	                         	$("#id").attr("value", 0);
                    	                        	$("#form").form("reset");
                    	            	        	searchCondition();
                    	            	        	
                    	            	        }
                    	            		});
                    	            	}
                                 });				
                           }
                       })
                   }
               });
            });
            function searchCondition(){
            	var courseNameSch = $("input[name='courseNameSch']").val();
            	var createdStartSch = $("input[name='createdStartSch']").val();
            	var createdEndSch = $("input[name='createdEndSch']").val();
            	var schoolIdSch = $("input[name='schoolName']").val();
            	$.ajax({
            		url:ctx+"listScCourse.do",
            		type:"post",
            		data:{schoolIdSch:schoolIdSch,courseNameSch:courseNameSch,createdStartSch:createdStartSch,createdEndSch:createdEndSch},
            		success:function(data){
            			if(data.rows.length == 0){
            				$('#grid').datagrid('loadData', { total: 0, rows: [] });
            			}else{
            				$('#grid').datagrid('loadData', { total: 0, rows: [] }).datagrid('loadData',data.rows);
            				
            			}
            			
            		},
            		dataType:'json'
            	});
            	
            }
            function resetSearch(){
            	$('#searchForm input').val('');
            	SS.combobox('loadData', { total: 0, rows: [] });
            	grid.datagrid({url:''});
            	grid.datagrid('loadData', { total: 0, rows: [] });
            }
        </script>
    </body>
</html>