<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/rms" prefix="rms" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>家长圈详细列表</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>

    </head>
	    <style type="text/css">
        .datagrid-cell, .datagrid-cell-group, .datagrid-header-rownumber, .datagrid-cell-rownumber{  
    		o-text-overflow: ellipsis;  
    		text-overflow: ellipsis;  
		} 
        </style>
    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td><a id="operateComment" class="easyui-linkbutton" data-options="iconCls:'ext-icon-accept',disabled:true">查看评论</a></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                        	<td>学校</td>
                           	<td>
                           		<input id="schoolIdSch" name="schoolIdSch" class="textbox" editable="false"  style="width: 150px; height: 22px;" />                          		
                           	</td>
                            <td>用户类型</td>
                            <td>
                                <input id="articlePropertySch" name="articlePropertySch" class="textbox" style="width: 120px; height: 22px;" />
                            </td>
                            <td>创建时间</td>
                            <td>
                                <input name="createdStartSch" class="easyui-datebox" style="width: 100px" /> -
                                <input name="createdEndSch" class="easyui-datebox" style="width: 100px" />
                            </td>
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="searchForm()">检索</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('clearSelections');$('#operateComment').linkbutton('disable');grid.datagrid('load',{});">清除检索条件</a></td>
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
        	var gird;
            $(function() {
                var PS;
                var SS;
                var CS;
                grid = $('#grid').datagrid({
                    fit: true,
                    border: false,
                    collapsible: false,
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    rownumbers: true,
                    url: ctx + 'listArticleParentCircle.do',
                    columns: [
                             [
							 {
							     field: 'id',
							     title: 'ID',
							     sortable:true
							 }, {
                                 field: 'author',
                                 title: '真实姓名',
                                 sortable:true
                             },{
							     field: 'picId',
							     title: '图片Id',
							     sortable:true
							 },  {
                                 field: 'content',
                                 title: '内容',
                                 sortable:true
                             }, {
                                 field: 'hits',
                                 title: '访问量',
                                 sortable:true
                             }, {
                                 field: 'agentId',
                                 title: '代理商',
                                 formatter:function(value,row,index){
                            		 if(row.agent){
                            			 return row.agent.trueName;
                            		 }
                                 },
                                 sortable:true
                             }, {
                                 field: 'studentId',
                                 title: '学校',
                                 formatter:function(value,row,index){
                            		 if(row.school){
                            			 return row.school.schoolName;
                            		 }
                                 },
                                 sortable:true
                             }, {
                                 field: 'classId',
                                 title: '班级',
								 formatter:function(value,row,index){
                            		 if(row.scClass){
                            			 return row.scClass.className;
                            		 }
                                 },
                                 sortable:true
                             }, {
                           	     field: 'sortId',
                           	     title: '排序',
                           	     sortable:true
                           	 }, {
                           	     field: 'createdTime',
                           	     title: '添加时间',
	                           	 formatter: function(value, row, index){
			            			return value.substring(0,10);
			            		 },
                           	     sortable:true
                           	 }]
                         ],
                    onLoadSuccess: function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    },
                    onClickRow: function(index, row) {
                    	$("#operateComment").linkbutton('enable');
                    }
                });
                
              //初始化学校
               $.getJSON(ctx+'listAllScSchool.do',function(schooleJson) {
  	            	if(schooleJson.rows.length==0){
  	            		SS = $('#schoolIdSch').combobox();
  	            		SS.combobox("clear");
  	            		//DS.combobox('loadData',{schoolId:0,schoolName:" "});
  	                 	//ES.combobox('loadData',{id:0,className:" "});
  	            	}else{
  	            		SS = $('#schoolIdSch').combobox({
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
              	CS = $("#articlePropertySch").combobox({
              		data:[{'id':1,'roleName':'家长'},{'id':2,'roleName':'教师'},{'id':4,'roleName':'园长'}],
              		valueField:'id',
              		textField:'roleName',
              		DropDownStyle:'DropDownList',
           	        onSelect:function(row){
           	        	searchForm();
           	        }
              	});
                //重置操作
                $("#operateMode_reset").click(function() {
                    var idValue = $("#form input[name='id']").val();
                    if (idValue > 0) {
                        $("#form").form("load", ctx + "getScAgentById.do?id=" + idValue);
                    } else {
                        $("#form").form("reset");
                    }
                })
                //初始化查看评论按钮
                $("#operateComment").click(function() {
                	var rows = $('#grid').datagrid('getSelections');
    				if( rows.length>0 ){
    					window.location.href="StatisticParentCircleComment.jsp?articleBaseId="+rows[0].id;
    				}else{
    					$.messager.show({
    						title : message.title.normal,
    						msg : message.no_record,
    						timeout : message.timeout,
    						showType : message.showType
    					});
    				}
    			});
                
            });
			function searchForm(){
				grid.datagrid('clearSelections');
				$("#operateComment").linkbutton('disable');
				grid.datagrid('load',fw.serializeObject($('#searchForm')));
			}

        </script>
    </body>

    </html>