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
                <div data-options="region:'north',split:true,border:false" style="height:50px;">
                    <form id="searchForm">
                        <table>
                            <tr>
                            	<td>学校</td>
                            	<td>
                            		<input id="schoolName" name="schoolName" class="textbox" editable="false"  style="width: 120px; height: 22px;" />
                            		<div id="agentId" style="display:none;" ></div>                            		
                            	</td>
                                <td>用户名称</td>
                                <td>
                                    <input name="userTrueNameSch" class="textbox" style="width: 120px; height: 22px;" />
                                </td>
                                <td>创建时间</td>
                                <td>
                                    <input name="beginTime" class="easyui-datebox" style="width: 100px" />-
                                    <input name="endTime" class="easyui-datebox" style="width: 100px" />
                                </td>
                                <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="searchForm()">过滤</a>  <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="resetData()">重置过滤</a>
                                </td>
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
                var SS;
                var gird;
                var param ="";
                $(function () {
                	//只有当用户为代理商的时候，roleName以及userId才会用到
                	var roleName = <%=request.getParameter("roleName")%>;
                	var userId = <%=request.getParameter("userId")%>;
                	
					if(roleName == "agent"){
						param = "?agentIdSch="+userId;
						$("#agentId").html(userId);
					}
                    grid = $('#grid').datagrid({
                            fit: true,
                            border: false,
                            collapsible: false,
                            rownumbers: true,
                            pagination: true,
                            singleSelect: true,
                            rownumbers: true,
                            url: ctx + 'listScTeacherStatistics.do'+param,
                            columns: [[{
                                    field: 'id',
                                    title: 'id',
                                    hidden: 'true',
                                }
                             , {
                                    field: 'user',
                                    title: '老师姓名',
                                    formatter: function (value, row, index) {
                                    	if(row.user){
                                    		return row.user.trueName;
                                    	}
                                        
                                    }
                                }
                             , {
                                    field: 'mobilePhone',
                                    title: '电话',
                                    formatter: function (value, row, index) {
                                    	if(row.user){
                                        	return row.user.mobilePhone;
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
                                }
                                
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
	       	            	        	searchForm();
	       	            	        }
	       	            		});
	       	            	}
	       	             }); 
                });
                
                
   

             function searchForm(){
             	var userTrueName = $("input[name='userTrueNameSch']").val();
             	var beginTime = $("input[name='beginTime']").val();
             	var endTime = $("input[name='endTime']").val();
             	var schoolId = $("input[name='schoolName']").val();
             	var agentId = $("#agentId").html();
             	if(agentId ==""){
             		agentId = 0;
             	}
             	if(schoolId ==""){
             		schoolId = 0;
             	}
             	var searchParam = "?agentId="+agentId+"&schoolId="+schoolId+"&userTrueName="+userTrueName+"&beginTime="+beginTime+"&endTime="+endTime;
             	$('#grid').datagrid({
                    url: ctx+"findTeacherListByCondition.do"+searchParam
                });
             	$('#grid').datagrid('load',{});
             	/**$.ajax({
             		url:ctx+"findTeacherListByCondition.do",//findClassScheduleByClassName.do",
             		type:"post",
             		data:{agentId:agentId,schoolId:schoolId,userTrueName:userTrueName,beginTime:beginTime,endTime:endTime},
             		success:function(data){
             			if(data.rows.length == 0){
             				$('#grid').datagrid('loadData', { total: 0, rows: [] });
             			}else{
             				$('#grid').datagrid('loadData', { total: 0, rows: [] }).datagrid('loadData',data.rows);	
             			}
             		},
             		dataType:'json'
             	});**/
             }
             function resetData(){
            	 $('#searchForm input').val('');
            	 $('#grid').datagrid({
                     url: ctx + 'listScTeacherStatistics.do'+param
                 });
            	 grid.datagrid('load',{});
             }
            </script>
        </body>


        </html>