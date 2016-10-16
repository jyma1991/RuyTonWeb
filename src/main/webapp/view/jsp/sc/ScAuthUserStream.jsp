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
        <div class="easyui-layout" data-options="fit:true" >
            <div id="left-content" data-options="region:'west',split:true,border:false" style="width: 50%;margin:0px;padding: 5px;">
            	<div class="user-stream-header">包含</div>
                <table id="includeGrid" style="margin:0px 20px; border:1px solid #ccc;" >
                </table>
            </div>

            <div id="right-content" data-options="region:'east',split:true,border:false" style="width: 50%; padding: 5px;">
                <div class="user-stream-header">排除</div>
				<table id="excludeGrid" style="margin:0px 20px; border:1px solid #ccc;" >
                </table>
            </div>
        </div>
        <script type="text/javascript">
        	var includeArray = new Array();
    		var endArray = new Array();
    		var schoolId = <%= request.getParameter("schoolId")  %>;
        	var classId = <%= request.getParameter("classId")  %>;
        	var userRole = '<%= request.getParameter("userRole") %>';
        	var streamId = '<%= request.getParameter("streamId") %>';
        	var title = userRole == "teacher" ? '老师姓名':'家长姓名';
	        var closeDialog = function($dialog) {
				$dialog.dialog('destroy');
			}
	        var submitForm = function($dialog, $btn){
	        	var data = "";
	        	for(var i = 0; i<includeArray.length; i++){
	        		if(includeArray[i] != endArray[i]){
	        			data += ($($("#left-content input[name='id']").get(i)).val()+",");
	        		}
	        	}
	        	if(data.length >0){
	        		data = data.substr(0,data.length-1);
		        	$.ajax({
		        		url:ctx+'batchUpdateScAuthUserStream.do',
		        		type:'post',
		        		data:{ids:data,classId:classId,streamId:streamId,schoolId:schoolId},
		        		success:function(data){
		        			$dialog.dialog('destroy');
		        			if(data.success){
		        				$btn.show({
                                    title : message.title.normal,
                                    msg : message.set_success,
                                    timeout : message.timeout,
                                    showType : message.showType
                                });
		        			}else{
		        				$btn.show({
                                    title : message.title.normal,
                                    msg : message.set_fail,
                                    timeout : message.timeout,
                                    showType : message.showType
                                });
		        			}
		        			
		        		},
		        		dataType:'json'
		        	});
	        	}else{
	        		$btn.show({
                        title : message.title.normal,
                        msg : message.set_success,
                        timeout : message.timeout,
                        showType : message.showType
                    });
	        		$dialog.dialog('destroy');
	        	}
	        	
	        };
            $(function() {
            	var includeGrid;
            	var excludeGrid;
            	includeGrid=$('#includeGrid').datagrid({
                    border : false,
                    collapsible : true,
                    rownumbers : true,
                    url:ctx+"listAuthTeacherMsg.do?schoolId="+schoolId+"&classId="+classId+"&userRole="+userRole+"&publicId=0",
                    columns:[[
											{field:'id',checkbox:true,formatter:function(value,row,index){
            		            				return true;            				
            		            			}}
											,{field:'trueName',title:title,formatter:function(value,row,index){
            		            				return row.trueName;            				
            		            			}}
            		                    ]],
                    onLoadSuccess : function(data) {
                    	 var rowData = data.rows;
                         $.each(rowData,function(idx,val){//遍历JSON
                         	if(val.streamIncluded=='true' || val.streamIncluded==null){
                            	$("#includeGrid").datagrid("selectRow", idx);//如果数据行为已选中则选中改行
                            	includeArray[idx] = 0;
                            	endArray[idx] = 0;
                          	}else{
                          		includeArray[idx] = 1;
                          		endArray[idx] = 1;
                          	}
                         });
                         var count = 0;
                         //选中包含多选框时，取消选中排除多选框
                         $("#left-content input[name='id']").each(function(index){
                        	 count ++ ;
                        	$(this).click(function(){
                        		if($(this).prop("checked")){
                        			$("#excludeGrid").datagrid("unselectRow", index);
                        			endArray[index] = 0;
                        		}else{
                        			$("#excludeGrid").datagrid("selectRow", index);
                        			endArray[index] = 1;
                        		}
                        	}) 
                         });
                         //全部包含 
                         $("#left-content .datagrid-header-check input[type='checkbox']").click(function(){
                        	 if($(this).prop("checked")){
                        		 for(var i = 0; i<count; i++){
                            		 $("#excludeGrid").datagrid("unselectRow", i);
                            		 endArray[i] = 0;
                            	 }
                        	 }else{
                        		 for(var i = 0; i<count; i++){
                            		 $("#excludeGrid").datagrid("selectRow", i);
                            		 endArray[i] = 1;
                            	 }
                        	 }
                        	 
                         })
                    },
                    onClickRow : function(index, row) {
                       if($($("#left-content input[name='id']").get(index)).prop("checked")){
                    	   $("#excludeGrid").datagrid("unselectRow", index);
                    	   endArray[index] = 0;
                       }else{
                    	   $("#excludeGrid").datagrid("selectRow", index);
                    	   endArray[index] = 1;
                       }
                    }
                });
            	excludeGrid=$('#excludeGrid').datagrid({
                    border : false,
                    collapsible : true,
                    rownumbers : true,
                    url:ctx+"listAuthTeacherMsg.do?schoolId="+schoolId+"&classId="+classId+"&userRole="+userRole+"&publicId=0",
                    columns:[[
											{field:'id',checkbox:true,formatter:function(value,row,index){
            		            				return true;            				
            		            			}}
											,{field:'trueName',title:title,formatter:function(value,row,index){
            		            				return row.trueName;            				
            		            			}}
            		                    ]],
                    onLoadSuccess : function(data) {
                    	 var rowData = data.rows;
                         $.each(rowData,function(idx,val){//遍历JSON
                         	if(val.streamIncluded=='false' ){
                            	$("#excludeGrid").datagrid("selectRow", idx);//如果数据行为已选中则选中改行
                          	}
                         })
                         var count = 0;
                       //选中排除多选框时，取消选中包含多选框
                         $("#right-content input[name='id']").each(function(index){
                        	 count ++;
                        	$(this).click(function(){
                        		if($(this).prop("checked")){
                        			$("#includeGrid").datagrid("unselectRow", index);
                        			endArray[index] = 1;
                        		}else{
                        			$("#includeGrid").datagrid("selectRow", index);
                        			endArray[index] = 0;
                        		}
                        	}) 
                         });
                         //全部排除 
                         $("#right-content .datagrid-header-check input[type='checkbox']").click(function(){
                        	 if($(this).prop("checked")){
                        		 for(var i = 0; i<count; i++){
                            		 $("#includeGrid").datagrid("unselectRow", i);
                            		 endArray[i] = 1;
                            	 }
                        	 }else{
                        		 for(var i = 0; i<count; i++){
                            		 $("#includeGrid").datagrid("selectRow", i);
                            		 endArray[i] = 0;
                            	 }
                        	 }
                        	 
                         })
                    },
                    onClickRow : function(index, row) {
                    	if($($("#right-content input[name='id']").get(index)).prop("checked")){
                     	   $("#includeGrid").datagrid("unselectRow", index);
                     	   endArray[index] = 1;
                        }else{
                     	   $("#includeGrid").datagrid("selectRow", index);
                     	   endArray[index] = 0;
                        }
                    }
                });
            });
            /*
            function checked(index){
            	if($($("input[name='teacherId']").get(index)).is(":checked")){
            		$($("input[name='teacherId']").get(index)).attr("checked",false);
            	}else{
            		$($("input[name='teacherId']").get(index)).prop("checked",true);
            	}
            }*/
        </script>
    </body>
</html>