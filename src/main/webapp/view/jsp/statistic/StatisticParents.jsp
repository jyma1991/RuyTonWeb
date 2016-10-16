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
            <div data-options="region:'north',split:true,border:false" style="height:50px;">
                <form id="searchForm">
                    <table>
                        <tr>
                           	<td>学校</td>
                            <td>
                            	<input id="schoolName" name="schoolName" class="textbox" editable="false"  style="width: 120px; height: 22px;" />
                            	<div id="agentIdSch" style="display:none;"></div>
                            </td>
                            <td>班级</td>
                            <td><input id="className" name="className" class="textbox" editable="false"  style="width: 120px; height: 22px;" /></td>
                            <td>创建时间</td>
                            <td>
                                <input name="createdStartSch" class="easyui-datebox" style="width: 100px" /> -
                                <input name="createdEndSch" class="easyui-datebox" style="width: 100px" />
                            </td>
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="searchForm()">过滤</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="resetData()">重置过滤</a></td>
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
        var CS;
        var param;
      	//只有当用户为代理商的时候，roleName以及userId才会用到
    	var roleName = <%=request.getParameter("roleName")%>;
    	var userId = <%=request.getParameter("userId")%>;
        $(function() {
	        	
	        	param ="";
	        	var url =ctx + 'listScStudentParents.do';
				if(roleName == "agent"){
					param = "?agentIdSch="+userId;
					url =ctx+'listScStudentParentsStatistic.do'+param;
					$("#agentIdSch").val(userId);
				}
                var gird;
                grid = $('#grid').datagrid({
                    fit: true,
                    border: false,
                    collapsible: false,
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    rownumbers: true,
                    url:url,
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
                            	if(row.user)
	            				return row.user.trueName;            				
	            			},
	            			sortable: true
                        }
                        ,{
                            field: 'mobilePhone',
                            title: '电话',
                            formatter:function(value,row,index){
                            	if(row.user)
	            				return row.user.mobilePhone;            				
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
        	            	            	        	searchForm();
        	            	            	        }
        	            	            		});
        	            	            	}
        	            	             });
                   	        	}
                   	        }
                   		});
                   	}
                });
        });

      
      	//搜索
        function searchForm(){
        	var createdStartSch = $("input[name='createdStartSch']").val();
        	var createdEndSch = $("input[name='createdEndSch']").val();
        	
        	var schoolId = SS == undefined ? 0:SS.combobox('getValue');
        	var classId = (CS == undefined || CS.combobox('getValue')=="")? 0:CS.combobox('getValue');
        	var agentId = $("#agentIdSch").val() =="" ? 0:$("#agentIdSch").val();
        	/**
        	$.ajax({
        		url:ctx+"getParentsByCondition.do",
        		type:"post",
        		data:{userIdSch:agentId,studentIdSch:schoolId,parentIdSch:classId,createdStartSch:createdStartSch,createdEndSch:createdEndSch},
        		success:function(data){
        			if(data.rows.length == 0){
        				$('#grid').datagrid('loadData', { total: 0, rows: [] });
        			}else{
        				$('#grid').datagrid('loadData', { total: 0, rows: [] }).datagrid('loadData',data.rows);
        				
        			}
        		},
        		dataType:'json'
        	});**/
        	var searchParam = "?userIdSch="+agentId+"&studentIdSch="+schoolId+"&parentIdSch="+classId+"&createdStartSch="+createdStartSch+"&createdEndSch="+createdEndSch;
        	$('#grid').datagrid({
                url: ctx+"getParentsByCondition.do"+searchParam
            });
         	$('#grid').datagrid('load',{});
        }
        function resetData(){
	       	 $('#searchForm input').val('');
	       	 var url =ctx + 'listScStudentParents.do';
			 if(roleName == "agent"){
				param = "?agentIdSch="+userId;
				url =ctx+'listScStudentParentsStatistic.do'+param;
			 }
	       	 $('#grid').datagrid({
	                url: url
	         });
	       	 grid.datagrid('load',{});
        }
        </script>
    </body>

    </html>