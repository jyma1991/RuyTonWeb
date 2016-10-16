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
            <div data-options="region:'north',split:true,border:false" style="height:50px;">
                <form id="searchForm">
                    <table>
                        <tr>
                        	<td>学校</td>
                            <td><input id="schoolName" name="schoolIdSch" class="textbox" editable="false"  style="width: 120px; height: 22px;" /></td>
                            <td>班级</td>
                            <td><input id="className" name="classIdSch" class="textbox" editable="false"  style="width: 120px; height: 22px;" /></td>                            
                        	<td>学号</td>
                            <td>
                              <input name="studentCodeSch" class="textbox" style="width: 120px; height: 22px;" />
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
        </div>
        <script type="text/javascript">
        var grid;
		var SS;
		var CS;
		$(function() {
			//只有当用户为代理商的时候，roleName以及userId才会用到
        	var roleName = <%=request.getParameter("roleName")%>;
        	var userId = <%=request.getParameter("userId")%>;
        	var param ="";
        	var url =ctx + 'listScStudent.do';
			if(roleName == "agent"){
				param = "?agentIdSch="+userId;
				url =ctx+'listScStudentStatistic.do'+param;
			}
		    grid = $('#grid').datagrid({
		        fit: true,
		        border: false,
		        collapsible: false,
		        rownumbers: true,
		        pagination: true,
		        singleSelect: true,
		        rownumbers: true,
		        url: url,
		        columns: [
		            [{
		                    field: 'studentCode',
		                    title: '学号',
		                    sortable: true
		                }, {
		                    field: 'schoolId',
		                    title: '学校ID',
		                    hidden: 'true'
		                }, {
		                    field: 'user',
		                    title: '姓名',
		                    formatter: function(value, row, index) {
		                    	if(row.user)
		                        return row.user.trueName;
		                    }
		                }, {
		                    field: 'sex',
		                    title: '性别',
		                    formatter: function(value, row, index) {
		                    	if(row.user)
		                        if (row.user.sex) {
		                            return "男"
		                        } else {
		                            return "女"
		                        }
		                    }
		                }, {
		                    field: 'adressDetail',
		                    title: ' 详细地址',
		                    formatter: function(value, row, index) {
		                    	if(row.user)
		                        return row.user.addressDetail;
		                    }
		                }, {
		                    field: 'birthday',
		                    title: '出生日期',
		                    formatter: function(value, row, index) {
		                    	if(row.user)
		                        var birth = row.user.birthday;
		                        if (birth != null)
		                            return birth.substring(0, 10);
		                        else
		                            return null;
		                    }
		                }, {
		                    field: 'createdTime',
		                    title: '创建时间',
		                    formatter: getDate,
		                    sortable: true
		                }, {
		                    field: 'editedTime',
		                    title: '编辑时间',
		                    formatter: getDate,
		                    sortable: true
		                }

		            ]
		        ],
		        onLoadSuccess: function(data) {
		            $('.iconImg').attr('src', fw.pixel_0);
		        },
		    });
		    
        	//初始化学校
	        $.getJSON(ctx+'listAllScSchool.do'+param,function(schooleJson) {
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
	           	        	grid.datagrid('load',fw.serializeObject($('#searchForm')));
	           	        	if(row){
		                        	//班级初始化  教室为69  公共场地为70
		                         	$.getJSON(ctx+'listAllScClass.do',{schoolIdSch:row.schoolId,placeTypeIdSch:69},function(classJson) {
		            	            	if(classJson.rows.length==0){
		            	            		$("input[name='className']").val(null);
		            	            		CS = $('#className').combobox();
		            	            		CS.combobox("clear");
		            	            		CS.combobox('loadData',{id:0,className:" "});
		            	            		//DS.combobox('loadData',{schoolId:0,schoolName:" "});
		            	                 	//ES.combobox('loadData',{id:0,className:" "});
		            	            	}else{
		            	            		CS = $('#className').combobox({
		            	            		    data:classJson.rows,
		            	            	        valueField:'id',
		            	            	        textField:'className',
		            	            	        DropDownStyle:'DropDownList',
		            	            	        onSelect:function(row){
		            	            	        	grid.datagrid('load',fw.serializeObject($('#searchForm')));
		            	            	        }
		            	            		});
		            	            	}
		            	             });
	           	        	}
	           	        }
	           		});
	           	}
	        });
		})
        </script>
    </body>

    </html>