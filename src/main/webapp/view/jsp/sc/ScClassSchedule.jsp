<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.ryt.web.entity.user.User"%>
    <%@page import="org.durcframework.core.UserContext"%>
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
            <div data-options="region:'north',split:true,border:false" style="height:100px;">
                <form id="searchForm">
                    <table>
                        <tr>
                        	<rms:role operateCode="add">
        					</rms:role>
        					<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'" onclick="append()">添加</a>
        					<rms:role operateCode="delete">
        					</rms:role>
        					<td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>        					
        					<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-save'" onclick="accept()">提交</a></td>
        					<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-undo'" onclick="reject()">撤销</a></td>
                        	<!-- <td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-search'" onclick="getChanges()">保存</a></td> -->
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <!-- <td>班级名称</td>
                            <td><input name="className" class="textbox" style="width: 120px; height: 22px;" /></td> -->
                            <td>班级</td>
                            <td><input id="className" name="className" class="textbox" editable="false"  style="width: 120px; height: 22px;" /></td>
                            <td>时间段</td>
                            <td><input name="beginDate" class="easyui-timespinner" style="width: 100px" />
                                -
                                <input name="endDate" class="easyui-timespinner" style="width: 100px" />
                            </td><!-- grid.datagrid('load',fw.serializeObject($('#searchForm')));  -->
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="searchForm()">过滤</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div data-options="region:'center',border:false">
                <table id="grid">
                </table>
            </div>
        </div>
		<div id="addRootDldlg-buttons">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="addCourse(); return false;">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="$('#addRootDl').dialog('close'); return false;">取消</a>
		</div>
        <script type="text/javascript">
        	var schoolId = 0;
        	var CS;
        	var firstIndex = 0;
        	var courseJson = null;
        	//预先加载课程数据
        	$.ajax({
        		type:'post',
        		async: false,
        		url:ctx+'listAllScCourseJsonBySchoolId.do',
        		data:{schoolIdSch:schoolId},
        		success:function(data){
        			courseJson = data;
        		},
        		dataType:'json'
        	});
            $(function() {
                var gird;
        		var courses = null;
        		schoolId = 0;
                grid=$('#grid').datagrid({
                    fit:true,
                    border : false,
                    collapsible : false,
                    rownumbers : true,
                    pagination : true,
                    singleSelect : true,
                    iconCls:'icon-edit',
                    url:ctx+'listScClassSchedule.do?schoolIdSch=0',
                    columns:[[
		            						{field:'id',title:'ID',sortable:true}
            		            			,{field:'classId',title:'班级',width:80,formatter: function(value, row, index){
              		            				if(row.scClass){
              		            						return row.scClass.className;
              		            				}
              		            				
              		            			},
	              		  					editor:{
	              								type:'combobox',
	              								options:{
	              									url:ctx+'listSchoolClasses.do?placeTypeId=69&schoolIdSch=0',//69表示教室
	              									valueField:'id',
	              									textField:'className',
	              									required:true,
	              									panelHeight : 'auto',
	              									editable:false
	              								}
	              							},sortable:true}
            		            			,{field:'beginDate',title:'开始时间',width:60,formatter: function(value, row, index){
              		            				if(value.length>7){
              		            					return value.substring(0,5);
              		            				}
            		            				return value;
              		            			},editor:{
	              								type:'timespinner',
	              								options:{
	              									min:"07:00",
	              									max:"20:00",
	              									required:true
	              								}
	              							},sortable:true}
            		            			,{field:'endDate',title:'结束时间',width:60,formatter: function(value, row, index){
            		            				if(value.length>7){
              		            					return value.substring(0,5);
              		            				}
            		            				return value;
              		            			},editor:{
	              								type:'timespinner',
	              								options:{
	              									min:"07:00",
	              									max:"20:00",
	              									required:true
	              								}
	              							},sortable:true}
            		            			,{field:'d1',title:'星期一',width:80,formatter: function(value, row, index){
            		            				schoolId = row.schoolId;
              		            				if(row.course1 && row.course1.id > 0){
              		            					return row.course1.courseName;
              		            				}else{
              		            					return "";
              		            				}
              		            			},
	              		  					editor:{
	              								type:'combobox',
	              								options:{
	              									data:courseJson,
	              									valueField:'id',
	              									textField:'courseName',
	              									//required:true,
	              									panelHeight : 'auto',
	              									editable:false
	              								}
	              							},sortable:true}
            		            			,{field:'d2',title:'星期二',width:80,formatter: function(value, row, index){
            		            				schoolId = row.schoolId;
              		            				if(row.course2 && row.course2.id > 0){
              		            					return row.course2.courseName;
              		            				}else{
              		            					return "";
              		            				}
              		            				
              		            			},
	              		  					editor:{
	              								type:'combobox',
	              								options:{
	              									data:courseJson,
	              									valueField:'id',
	              									textField:'courseName',
	              									//required:true,
	              									panelHeight : 'auto',
	              									editable:false
	              								}
	              							},sortable:true}
            		            			,{field:'d3',title:'星期三',width:80,formatter: function(value, row, index){
              		            				if(row.course3 && row.course3.id > 0){
              		            					return row.course3.courseName;
              		            				}else{
              		            					return "";
              		            				}
              		            				
              		            			},
	              		  					editor:{
	              								type:'combobox',
	              								options:{
	              									data:courseJson,
	              									valueField:'id',
	              									textField:'courseName',
	              									//required:true,
	              									panelHeight : 'auto',
	              									editable:false
	              								}
	              							},sortable:true}
            		            			,{field:'d4',title:'星期四',width:80,formatter: function(value, row, index){
              		            				if(row.course4 && row.course4.id > 0){
              		            					return row.course4.courseName;
              		            				}else{
              		            					return "";
              		            				}
              		            				
              		            			},
	              		  					editor:{
	              								type:'combobox',
	              								options:{
	              									data:courseJson,
	              									valueField:'id',
	              									textField:'courseName',
	              									//required:true,
	              									panelHeight : 'auto',
	              									editable:false
	              								}
	              							},sortable:true}
            		            			,{field:'d5',title:'星期五',width:80,formatter: function(value, row, index){
              		            				if(row.course5 && row.course5.id > 0){
              		            					return row.course5.courseName;
              		            				}else{
              		            					return "";
              		            				}
              		            				
              		            			},
	              		  					editor:{
	              								type:'combobox',
	              								options:{
	              									data:courseJson,
	              									valueField:'id',
	              									textField:'courseName',
	              									//required:true,
		              								panelHeight : 'auto',
		              								editable:false
	              								}
	              							},sortable:true}
            		            			,{field:'d6',title:'星期六',width:80,formatter: function(value, row, index){
              		            				if(row.course6 && row.course6.id > 0){
              		            					return row.course6.courseName;
              		            				}else{
              		            					return "";
              		            				}
              		            				
              		            			},
	              		  					editor:{
	              								type:'combobox',
	              								options:{
	              									data:courseJson,
	              									valueField:'id',
	              									textField:'courseName',
		              								panelHeight : 'auto',
		              								editable:false
	              								}
	              							},sortable:true}
            		            			,{field:'d7',title:'星期天',width:80,formatter: function(value, row, index){
              		            				if(row.course7 && row.course7.id > 0){
              		            					return row.course7.courseName;
              		            				}else{
              		            					return "";
              		            				}
              		            				
              		            			},
	              		  					editor:{
	              								type:'combobox',
	              								options:{
	              									data:courseJson,
	              									valueField:'id',
	              									textField:'courseName',
		              								panelHeight : 'auto',
		              								editable:false
	              								}
	              							},sortable:true}
            		            			,{field:'remark',title:'备注',width:80,editor:{
	              								type:'textbox',
	              								
	              							},sortable:true}
            		            			,{field:'sortId',title:'排序',width:60,editor:{
            		            				type:'numberspinner',
            		            			},sortable:true}
            		            			,{field:'createdTime',title:'创建时间',formatter: function(value, row, index){
              		            				if(value && value!="" )
            		            				return value.substring(0,10);
              		            			},sortable:true}
            		                    ]],
            		onClickCell: onClickCell,
                    onLoadSuccess : function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                        $("input[type='text']").css("readonly","readonly");
                        if(firstIndex++ == 0){
                        	//班级初始化  教室为69  公共场地为70
                            $.getJSON(ctx+'listAllScClass.do',{schoolIdSch:schoolId,placeTypeIdSch:69},function(classJson) {
               	            	if(classJson.rows.length==0){
               	            		CS = $('#className').combobox();
               	            		CS.combobox("clear");
               	            		//DS.combobox('loadData',{schoolId:0,schoolName:" "});
               	                 	//ES.combobox('loadData',{id:0,className:" "});
               	            	}else{
               	            		CS = $('#className').combobox({
               	            		    data:classJson.rows,
               	            	        valueField:'id',
               	            	        textField:'className',
               	            	        DropDownStyle:'DropDownList',
               	            	        onSelect:function(row){
               	            	        	searchForm();
               	            	        }
               	            		});
               	            	}
               	             });
                        }
                    },
                    onClickRow : function(index, row) {
                    	$('#grid').datagrid('beginEdit',index);
                    },
                    onBeforeEdit:function(index,row){
        				row.editing = true;
        				updateActions(index);
        			},
        			onAfterEdit:function(index,row){
        				row.editing = false;
        				updateActions(index);
        			},
        			onCancelEdit:function(index,row){
        				row.editing = false;
        				updateActions(index);
        			}
                });
            
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
                            	//修改
                                var idValue = rows[0].id;//$("#form input[name='id']").val();
                                $.post(ctx+"delScClassSchedule.do", {
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
            });
            function updateActions(index){
        		$('#grid').datagrid('updateRow',{
        			index: index,
        			row:{}
        		});
        	}
            var editIndex = undefined;
            function endEditing(){
                if (editIndex == undefined){return true}
                if ($('#grid').datagrid('validateRow', editIndex)){
                    var ed1 = $('#grid').datagrid('getEditor', {index:editIndex,field:'d1'});
                    var ed2 = $('#grid').datagrid('getEditor', {index:editIndex,field:'d2'});
                    var ed3 = $('#grid').datagrid('getEditor', {index:editIndex,field:'d3'});
                    var ed4 = $('#grid').datagrid('getEditor', {index:editIndex,field:'d4'});
                    var ed5 = $('#grid').datagrid('getEditor', {index:editIndex,field:'d5'});
                    var ed6 = $('#grid').datagrid('getEditor', {index:editIndex,field:'d6'});
                    var ed7 = $('#grid').datagrid('getEditor', {index:editIndex,field:'d7'});
                    var remark = $('#grid').datagrid('getEditor', {index:editIndex,field:'remark'});
                    var begin = $('#grid').datagrid('getEditor', {index:editIndex,field:'beginDate'});
                    var end = $('#grid').datagrid('getEditor', {index:editIndex,field:'endDate'});
                    var classField = $('#grid').datagrid('getEditor', {index:editIndex,field:'classId'});
                    var courseName1 = null;
                    var courseName2 = null;
                    var courseName3 = null;
                    var courseName4 = null;
                    var courseName5 = null;
                    var courseName6 = null;
                    var courseName7 = null;
                    var className = null;
                    var courseId1 = null;
                    var courseId2 = null;
                    var courseId3 = null;
                    var courseId4 = null;
                    var courseId5 = null;
                    var courseId6 = null;
                    var courseId7 = null;
                    var classId = 0;
                    var beginValue = 0;
                    if(begin){
                    	beginValue = $(begin.target).timespinner('getValue');
                    }
                    var endValue = 0;
                    if(end){
                    	endValue = $(end.target).timespinner('getValue');
                    }
                    if(beginValue != 0 && endValue!= 0){
                    	if(!compare(beginValue,endValue)){
	                      	 $.messager.show({
	                               title : message.title.normal,
	                               msg : '开始时间不能大于结束时间！',
	                               timeout : message.timeout,
	                               showType : message.showType
	                         });
                       	return false;
                       }
                    }
                    //$('#grid').datagrid('getRows')[editIndex]['remark'] = $(remark.target).textbox("getValue");
                    //$('#grid').datagrid('getRows')[editIndex]['beginDate'] = $(begin.target).timespinner('getValue');
                    //$('#grid').datagrid('getRows')[editIndex]['endDate'] = $(end.target).timespinner('getValue');
                    if(classField){
                    	className = $(classField.target).combobox('getText');
                        classId = $(classField.target).combobox('getValue');
                    }
                    if(ed1){
                        courseName1 = $(ed1.target).combobox('getText');
                        courseId1 = $(ed1.target).combobox('getValue');
                    }
 					if(ed2){
 						courseName2 = $(ed2.target).combobox('getText');
 						courseId2 = $(ed2.target).combobox('getValue');
                    }
 					if(ed3){
 						courseName3 = $(ed3.target).combobox('getText');
 						courseId3 = $(ed3.target).combobox('getValue');
                    }
 					if(ed4){
 						courseName4 = $(ed4.target).combobox('getText');
 						courseId4 = $(ed4.target).combobox('getValue');
                    }
 					if(ed5){
 						courseName5 = $(ed5.target).combobox('getText');
 						courseId5 = $(ed5.target).combobox('getValue');
                    }
 					if(ed6){
 						courseName6 = $(ed6.target).combobox('getText');
 						courseId6 = $(ed6.target).combobox('getValue');
                    }
 					if(ed7){
 						courseName7 = $(ed7.target).combobox('getText');
 						courseId7 = $(ed7.target).combobox('getValue');
                    }
 					var course1 = null;
 					var course2 = null;
 					var course3 = null;
 					var course4 = null;
 					var course5 = null;
 					var course6 = null;
 					var course7 = null;
 					var scClass = null;
 					if($('#grid').datagrid('getRows')[editIndex]){
 						scClass = $('#grid').datagrid('getRows')[editIndex]['scClass'];
 						course1 = $('#grid').datagrid('getRows')[editIndex]['course1'];
 						course2 = $('#grid').datagrid('getRows')[editIndex]['course2'];
 	 					course3 = $('#grid').datagrid('getRows')[editIndex]['course3'];
 	 					course4 = $('#grid').datagrid('getRows')[editIndex]['course4'];
 	 					course5 = $('#grid').datagrid('getRows')[editIndex]['course5'];
 	 					course6 = $('#grid').datagrid('getRows')[editIndex]['course6'];
 	 					course7 = $('#grid').datagrid('getRows')[editIndex]['course7'];
 					}
 					if(course1){
                    	//$('#grid').datagrid('getRows')[editIndex]['d1'] = courseId1;
                    	if(courseId1 == null || courseId1 == ""){
                    		$('#grid').datagrid('getRows')[editIndex]['d1'] = null;
                    	}
 						course1['courseName'] = courseName1;
 						course1['id'] = courseId1;
 					}
 					if(course2){
 						//$('#grid').datagrid('getRows')[editIndex]['d2'] = courseId2;
 						if(courseId2 == null || courseId2 == ""){
                    		$('#grid').datagrid('getRows')[editIndex]['d2'] = null;
                    	}
 						course2['courseName'] = courseName2;
 						course2['id'] = courseId2;
 					}
 					if(course3){
 						//$('#grid').datagrid('getRows')[editIndex]['d3'] = courseId3;
 						if(courseId3 == null || courseId3 == ""){
                    		$('#grid').datagrid('getRows')[editIndex]['d3'] = null;
                    	}
 						course3['courseName'] = courseName3;
 						course3['id'] = courseId3;
 					}
 					if(course4){
 						//$('#grid').datagrid('getRows')[editIndex]['d4'] = courseId4;
 						if(courseId4 == null || courseId4 == ""){
                    		$('#grid').datagrid('getRows')[editIndex]['d4'] = null;
                    	}
 						course4['courseName'] = courseName4;
 						course4['id'] = courseId4;
 					}
 					if(course5){
 						//$('#grid').datagrid('getRows')[editIndex]['d5'] = courseId5;
 						if(courseId5 == null || courseId5 == ""){
                    		$('#grid').datagrid('getRows')[editIndex]['d5'] = null;
                    	}
 						course5['courseName'] = courseName5;
 						course5['id'] = courseId5;
 					}
 					if(course6){
 						//$('#grid').datagrid('getRows')[editIndex]['d6'] = courseId6;
 						if(courseId6 == null || courseId6 == ""){
                    		$('#grid').datagrid('getRows')[editIndex]['d6'] = null;
                    	}
 						course6['courseName'] = courseName6;
 						course6['id'] = courseId6;
 					}
 					if(course7){
 						//$('#grid').datagrid('getRows')[editIndex]['d7'] = courseId7;
 						if(courseId7 == null || courseId7 == ""){
                    		$('#grid').datagrid('getRows')[editIndex]['d7'] = null;
                    	}
 						course7['courseName'] = courseName7;
 						course7['id'] = courseId7;
 					}
 					if(scClass){
 						//$('#grid').datagrid('getRows')[editIndex]['classId'] = classId;
 						scClass['className'] = className; 
 	                    scClass['id'] = classId;
 					}
                    $('#grid').datagrid('endEdit', editIndex);
                    editIndex = undefined;
                    return true;
                } else {
                    return false;
                }
            }
            function onClickCell(index, field){
            	 if (editIndex != index){
                     if (endEditing()){
                         $('#grid').datagrid('selectRow', index)
                                 .datagrid('beginEdit', index);
                         var ed = $('#grid').datagrid('getEditor', {index:index,field:field});
                         if (ed){
                             ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
                         }
                         editIndex = index;
                     } else {
                         $('#grid').datagrid('selectRow', editIndex);
                     }
                 }
            }
            //新增操作 
            function append(){
                if (endEditing()){
                    $('#grid').datagrid('appendRow',{agentId:0,id:0,schoolId:0,classId:'',d1:'',d2:'',d3:'',d4:'',d5:'',d6:'',d7:'',scClass:{'id':0,'className':''},course1:{'id':0,'courseName':''},course1:{'id':0,'courseName':''},course2:{'id':0,'courseName':''},course3:{'id':0,'courseName':''},course4:{'id':0,'courseName':''},course5:{'id':0,'courseName':''},course6:{'id':0,'courseName':''},course7:{'id':0,'courseName':''},beginDate:'07:00',endDate:'08:00',sortId:0,operaterId:0,isDeleted:0,userId:0});
                    editIndex = $('#grid').datagrid('getRows').length-1;
                    $('#grid').datagrid('selectRow', editIndex)
                            .datagrid('beginEdit', editIndex);
                }
            }
            function removeit(){
                if (editIndex == undefined){return}
                $('#dg').datagrid('cancelEdit', editIndex)
                        .datagrid('deleteRow', editIndex);
                editIndex = undefined;
            }
            //提交操作
            function accept(){
                if (endEditing()){
                	var rows = $('#grid').datagrid('getRows');
                	var selectRow = $('#grid').datagrid('getSelected');
                	var changedRow = $("#grid").datagrid('getChanges');
                	var addCount = 0;
					for(var i = 0; i<rows.length; i++){
						//新增 
						if(rows[i].id == 0){
							$.ajax({
                				url:ctx+'addScClassSchedule.do',
                				type:'post',
                				async: false,
                				data:{
                					id:rows[i].id,agentId:rows[i].agentId,schoolId:rows[i].schoolId,classId:rows[i].classId,d1:rows[i].d1,d2:rows[i].d2,d3:rows[i].d3,d4:rows[i].d4,d5:rows[i].d5,d6:rows[i].d6,d7:rows[i].d7,sortId:rows[i].sortId,beginDate:rows[i].beginDate,endDate:rows[i].endDate,operaterId:rows[i].operaterId,isDeleted:rows[i].isDeleted,userId:rows[i].userId,remark:rows[i].remark
                				},
                				success:function(data){
                					addCount ++;
                					if(data.success){
                						$.messager.show({
                                            title : message.title.normal,
                                            msg : message.add_success,
                                            timeout : message.timeout,
                                            showType : message.showType
                                        });
                					}
                				},
    	        				dataType:'json'
                			});
						}	
					}
					var count = 0;
					/*
					//修改选中行内容 
					if(selectRow.length >0){
						$.ajax({
	        				url:ctx+'updateScClassSchedule.do',
	        				type:'post',
	        				async: false,
	        				data:{
	        					id:selectRow[i].id,classId:selectRow[i].classId,d1:selectRow[i].d1,d2:selectRow[i].d2,d3:selectRow[i].d3,d4:selectRow[i].d4,d5:selectRow[i].d5,d6:selectRow[i].d6,d7:selectRow[i].d7,sortId:selectRow[i].sortId,beginDate:selectRow[i].beginDate,endDate:selectRow[i].endDate,remark:selectRow[i].remark
	        				},
	        				success:function(data){
	        					count ++;
	        					var len = 0;
	        					if(changedRow.length>0){
	        						len = changedRow.length;
	        					}
	        					if(data.success && count == len + 1){
	        						$.messager.show({
	                                    title : message.title.normal,
	                                    msg : message.update_success,
	                                    timeout : message.timeout,
	                                    showType : message.showType
	                                });
	        					}
	        					
	        				},
	        				dataType:'json'
	        			});
					}*/
					if(changedRow.length != addCount){
						for(var i = 0 ; i<changedRow.length; i++){
							$.ajax({
	            				url:ctx+'updateScClassSchedule.do',
	            				type:'post',
	            				async: false,
	            				data:{
	            					id:changedRow[i].id,classId:changedRow[i].classId,d1:changedRow[i].d1,d2:changedRow[i].d2,d3:changedRow[i].d3,d4:changedRow[i].d4,d5:changedRow[i].d5,d6:changedRow[i].d6,d7:changedRow[i].d7,sortId:changedRow[i].sortId,beginDate:changedRow[i].beginDate,endDate:changedRow[i].endDate,remark:changedRow[i].remark
	            				},
	            				success:function(data){
	            					count ++;
	            					var len = 0;
		        					if(selectRow.length >0){
		        						len = selectRow.length;
		        					}
	            					if(data.success && count == changedRow.length + len){
	            						$.messager.show({
	                                        title : message.title.normal,
	                                        msg : message.update_success,
	                                        timeout : message.timeout,
	                                        showType : message.showType
	                                    });
	            					}
	            					
	            				},
	            				dataType:'json'
	            			});
						}
					}

					if(changedRow.length == 0){
						$.messager.show({
                            title : message.title.normal,
                            msg : '暂无修改内容，无需保存信息',
                            timeout : message.timeout,
                            showType : message.showType
                        });
						return;
					}
					$('#grid').datagrid("reload");                    
                }
            }
            
            //撤销操作
            function reject(){
                $('#grid').datagrid('rejectChanges');
                editIndex = undefined;
            }
            //判断开始时间与结束时间大小
            function compare(beginValue,endValue){
            	var begin= beginValue.split(":");
            	var end = endValue.split(":");
            	for(var i = 0; i<begin.length; i++){
            		if(parseInt(begin[i]) > parseInt(end[i])){
            			return false;
            		}else if(parseInt(begin[i]) < parseInt(end[i])){
            			return true;
            		}
            		if(i+1 == begin.length && parseInt(begin[i]) == parseInt(end[i])){
            			return false;
            		}
            	}
            	return true;
            }
            //搜索
            function searchForm(){
            	//var className = $("input[name='className']").val();
            	var beginDate = $("input[name='beginDate']").val();
            	var endDate = $("input[name='endDate']").val();
            	var classId = CS.combobox('getValue');
            	$.ajax({
            		url:ctx+"listScClassSchedule.do",//findClassScheduleByClassName.do",
            		type:"post",
            		data:{schoolIdSch:0,classIdSch:classId,beginDate:beginDate,endDate:endDate},
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
        </script>
    </body>
</html>