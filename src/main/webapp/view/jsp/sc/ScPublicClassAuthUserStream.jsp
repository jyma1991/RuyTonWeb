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
        <div id="parentTabs" class="easyui-tabs" data-options="fit:true,border:false,closable:false" style="width:386px;height:457px; border:0px;">
            
        </div>
        <script type="text/javascript">
        	//新建多维数组
        	var includeArray = new Array();
    		var endArray = new Array();
    		var schoolId = <%= request.getParameter("schoolId")  %>;
        	var classId = <%= request.getParameter("classId")  %>;
        	var userRole = '<%= request.getParameter("userRole") %>';
        	var streamId = '<%= request.getParameter("streamId") %>';
        	var publicId = classId;
        	var title = userRole == "teacher" ? '老师姓名':'家长姓名';
        	var tabIndex = 0;
	        var closeDialog = function($dialog) {
				$dialog.dialog('destroy');
			}
	        var submitForm = function($dialog, $btn){
	        	var data = "";
	        	for(var i = 0; i<includeArray[tabIndex].length; i++){
	        		if(includeArray[tabIndex][i] != endArray[tabIndex][i]){
	        			data += $($($(".tabs-panels #left-content").get(tabIndex)).find("input[name='id']").get(i)).val()+",";
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
            	$(".tabs").css({
            		"border":"0px",
            		"margin-bottom":"-5px"            	
            	});
            	var dataArray = new Array();
            	var content = '<div id="left-content" data-options="region:\'west\',split:true,border:false" style="width: 47%;margin:0px;float:left;padding:5px 3px 0px 3px;">'+
            	'<div class="user-stream-header">包含</div>'+
            	'<table id="includeGrid" style="margin:0px 20px; border:1px solid #ccc;" ></table></div>'+
            	//'<div class="borderStyle">&nbsp;</div>'+
            	'<div id="right-content" data-options="region:\'east\',split:true,border:false" style="width: 47%;float:left;padding:5px 3px 0px 3px;">'+
            	'<div class="user-stream-header">排除</div>'+
            	'<table id="excludeGrid" style="margin:0px 20px; border:1px solid #ccc;" ></table></div>';
            	$.ajax({
            		type:"post",
            		async: false,
            		url:ctx+"listAllScClass.do?schoolIdSch="+schoolId+"&placeTypeIdSch=69",
            		success:function(data){
            			if(data){
            				for(var i = 0; i<data.rows.length;i++){
            					dataArray[i] = data.rows[i].id;
            					$("#parentTabs").tabs('add',{
                    				title:data.rows[i].className,
                    				content:content
                    			});
            				}
            				
            			}
            			
            		},
            		dataType:'json'
            	});
            	$('#parentTabs').tabs({
            	    onSelect:function(title){
            	    	var tab = $('#parentTabs').tabs('getSelected');
            	    	var index = $('#parentTabs').tabs('getTabIndex',tab);
            	    	tabIndex = index;
            	    	includeArray[index] = new Array();
            	    	endArray[index] = new Array();
            	    	getTabsDataByClassId(index,dataArray[index]);
            	    }
            	});
            	$(".borderStyle").css({
            		"width":"10px",
            		"background":"#E6EEF8",
            		"height":"100%",
            		"float":"left"
            	});
            });
            	/*
            	$('#tt').tabs({
            	    border:false,
            	    onSelect:function(title){
            	        alert(title+' is selected');
            	    }
            	});*/
            function getTabsDataByClassId(tabIndex,classId){
            	var includeGrid;
            	var excludeGrid;
            	var includeGridDiv = $($('.tabs-panels #left-content').get(tabIndex)).find('#includeGrid');
            	var excludeGridDiv = $($('.tabs-panels #right-content').get(tabIndex)).find('#excludeGrid');
            	
            	includeGrid=includeGridDiv.datagrid({
                    border : false,
                    collapsible : true,
                    rownumbers : true,
                    url:ctx+"listAuthTeacherMsg.do?schoolId="+schoolId+"&classId="+classId+"&userRole="+userRole+"&publicId="+publicId,
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
                         		includeGridDiv.datagrid("selectRow", idx);//如果数据行为已选中则选中改行
                            	includeArray[tabIndex][idx] = 0;
                            	endArray[tabIndex][idx] = 0;
                          	}else{
                          		includeArray[tabIndex][idx] = 1;
                          		endArray[tabIndex][idx] = 1;
                          	}
                         });
                         var count = 0;
                         //选中包含多选框时，取消选中排除多选框
                         $($(".tabs-panels #left-content").get(tabIndex)).find("input[name='id']").each(function(index){
                        	 count ++ ;
                        	$(this).click(function(){
                        		if($(this).prop("checked")){
                        			excludeGridDiv.datagrid("unselectRow", index);
                        			endArray[tabIndex][index] = 0;
                        		}else{
                        			excludeGridDiv.datagrid("selectRow", index);
                        			endArray[tabIndex][index] = 1;
                        		}
                        	}) 
                         });
                         //全部包含 
                         $(".tabs-panels #left-content .datagrid-header-check input[type='checkbox']").click(function(){
                        	 if($(this).prop("checked")){
                        		 for(var i = 0; i<count; i++){
                        			 excludeGridDiv.datagrid("unselectRow", i);
                            		 endArray[tabIndex][i] = 0;
                            	 }
                        	 }else{
                        		 for(var i = 0; i<count; i++){
                        			 excludeGridDiv.datagrid("selectRow", i);
                            		 endArray[tabIndex][i] = 1;
                            	 }
                        	 }
                        	 
                         })
                    },
                    onClickRow : function(index, row) {
                       if($($($(".tabs-panels #left-content").get(tabIndex)).find("input[name='id']").get(index)).prop("checked")){
                    	   excludeGridDiv.datagrid("unselectRow", index);
                    	   endArray[tabIndex][index] = 0;
                       }else{
                    	   excludeGridDiv.datagrid("selectRow", index);
                    	   endArray[tabIndex][index] = 1;
                       }
                    }
                });
            	excludeGrid=excludeGridDiv.datagrid({
                    border : false,
                    collapsible : true,
                    rownumbers : true,
                    url:ctx+"listAuthTeacherMsg.do?schoolId="+schoolId+"&classId="+classId+"&userRole="+userRole+"&publicId="+publicId,
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
                         	if(val.streamIncluded=='false'){
                         		excludeGridDiv.datagrid("selectRow", idx);//如果数据行为已选中则选中改行
                          	}
                         })
                         var count = 0;
                       //选中排除多选框时，取消选中包含多选框
                       $($(".tabs-panels #right-content").get(tabIndex)).find("input[name='id']").each(function(index){
                        	 count ++;
                        	$(this).click(function(){
                        		if($(this).prop("checked")){
                        			includeGridDiv.datagrid("unselectRow", index);
                        			endArray[tabIndex][index] = 1;
                        		}else{
                        			includeGridDiv.datagrid("selectRow", index);
                        			endArray[tabIndex][index] = 0;
                        		}
                        	}) 
                         });
                         //全部排除 
                         $(".tabs-panels #right-content .datagrid-header-check input[type='checkbox']").click(function(){
                        	 if($(this).prop("checked")){
                        		 for(var i = 0; i<count; i++){
                        			 includeGridDiv.datagrid("unselectRow", i);
                            		 endArray[tabIndex][i] = 1;
                            	 }
                        	 }else{
                        		 for(var i = 0; i<count; i++){
                        			 includeGridDiv.datagrid("selectRow", i);
                            		 endArray[tabIndex][i] = 0;
                            	 }
                        	 }
                        	 
                         })
                    },
                    onClickRow : function(index, row) {
                    	if($($($(".tabs-panels #right-content").get(tabIndex)).find("input[name='id']").get(index)).prop("checked")){
                    		includeGridDiv.datagrid("unselectRow", index);
                     	   endArray[tabIndex][index] = 1;
                        }else{
                        	includeGridDiv.datagrid("selectRow", index);
                     	   endArray[tabIndex][index] = 0;
                        }
                    }
                });
            }
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