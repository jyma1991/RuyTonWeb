<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="com.ryt.web.entity.user.User"%>
<%@page import="org.durcframework.core.UserContext"%>
<%@ taglib prefix="rms" uri="/rms" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>食谱管理</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>
    <body>
        <div class="easyui-layout" data-options="fit:true">
        	<div data-options="region:'north',split:true,border:false" style="height:90px;">
 				<table style="margin:10px 0 10px 5px">
				        <tr>
				          <td><a onclick="addrow();"id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
				          <td><a onclick="uploaderFun();"id="btn_delpassword" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">图片上传</a></td> 
				          <td><a onclick="gridxReload();" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
				        </tr>
				</table>
            	<div >
             		<span>选择日期</span>&nbsp<input id="datech" class="datech" editable="false"/>
             		
	             	<div class="cook_breakfast" style="position: absolute;margin-top:-75px;margin-left:300px">
	                	<div ><img src="/assets/jquery-easyui-1.4.3/themes/default/images/breakfast.png" width="60";height="60"/><div style="text-align:center">早餐</div></div>	
	                </div>
	               	<div class="cook_lunch" style="position: absolute;margin-top:-75px;margin-left:380px">
	                	<div ><img src="/assets/jquery-easyui-1.4.3/themes/default/images/lunch.png" width="60";height="60"/><div style="text-align:center">中餐</div></div>	
	                </div> 
	                <div class="cook_dinner" style="position: absolute;margin-top:-75px;margin-left:460px">
	                	<div ><img src="/assets/jquery-easyui-1.4.3/themes/default/images/dinner.png" width="60";height="60"/><div style="text-align:center">晚餐</div></div>	
	                </div> 
	                <div class="cook_snack" style="position: absolute;margin-top:-75px;margin-left:540px">
	                	<div ><img src="/assets/jquery-easyui-1.4.3/themes/default/images/snack.png" width="60";height="60"/><div style="text-align:center">点心</div></div>	
	                </div>
	                <div style="position: absolute;margin-top:-65px;margin-left:620px" ><p>如果您不选择食谱的图片</p><p>系统会默认选择相应图片</p></div>  
             	</div> 
            </div>
            <div data-options="region:'center',border:false">
                     
                <table id="grid" style="">
                </table>
            </div>
        </div>   
    <script type="text/javascript">
    /*食谱管理*****************************************************************************/
    /*@author:lihui***********************************************************************/
    /*2015.11.17 createtime***************************************************************/
    var foodType = new Array(); //食谱类型
	var users = new Array(); //所有数据
	var pics = new Array(); //图片
	var datasearchstart = moment().weekday(0).format('YYYY-MM-DD');
	var datasearchend = moment().weekday(6).format('YYYY-MM-DD');
	var datanow = moment().format('YYYY-MM-DD');
	var agentId ;
	
	function getfoodtype(response) {
		
	    for (var i = 0, len = response.length; i < len; i++) {
	        foodType[i] = response[i];
	    }
	};
	
	function getall(response) {
	    for (var i = 0, len = response.rows.length; i < len; i++) {
	        users[i] = response.rows[i];
	    }
	};
	
	function getpic(response) {
	    for (var i = 0, len = response.rows.length; i < len; i++) {
	        pics[i] = response.rows[i];
	    }
	};
	function getAgentId(response){
		agentId= response.agentId;
	}
	$.ajaxSettings.async = false;
	$.getJSON(ctx + 'listAllSysClass.do?sysClassTypeIdSch=5', getfoodtype);
	$.getJSON(ctx + 'listAllScCookBook.do?publishDateStartLSch=' + datasearchstart + '&publishDateEndLSch=' + datasearchend + '&schoolIdSch=' + <%=UserContext.getInstance().getUser().getId()%>, getall);
	$.getJSON(ctx + 'listAllSysUpload.do?moduleIdSch=cook&funcIdSch=' + <%=UserContext.getInstance().getUser().getId()%>, getpic);
	$.getJSON(ctx + 'getScSchoolBySchoolIdId.do?schoolIdSch' + <%=UserContext.getInstance().getUser().getId()%>,getAgentId)
	//默认加载当天的一个星期的食谱
	console.log(users);
	gridx();
	//选择一天，列出这天所在周的所有食谱
	$('#datech').datebox({
	    panelHeight: 'auto',
	    onSelect: function(pre) {
	        users.splice(0, users.length)
	        datanow = pre.getFullYear() + "-" + (pre.getMonth() + 1) + "-" + pre.getDate()
	        datasearchstart = moment(pre).weekday(0).format('YYYY-MM-DD');
	        datasearchend = moment(pre).weekday(6).format('YYYY-MM-DD');
	        $.ajaxSettings.async = false;
	        $.getJSON(ctx + 'listAllScCookBook.do?publishDateStartLSch=' + datasearchstart + '&publishDateEndLSch=' + datasearchend + '&schoolIdSch=' + <%=UserContext.getInstance().getUser().getId()%>, getall);
	        gridx();
	    }
	});
	function gridxReload(){
		if (editcount > 0) {
		    $.messager.alert('警告', '当前还有' + editcount + '记录正在编辑，不能刷新。');
		    return;
		};
		$.ajaxSettings.async = false;
		$.getJSON(ctx + 'listAllSysUpload.do?moduleIdSch=cook&funcIdSch=' + <%=UserContext.getInstance().getUser().getId()%>, getpic);
		gridx();
	}
	function gridx() {
	    $('#grid').datagrid({
	        iconCls: 'icon-edit',
            fit:true,
            border : false,
            rownumbers : true,
	        singleSelect: true,
	        nowrap:true,
	        data: users,
	        columns: [
	            [{
	                field: 'publishDate',
	                title: '日期',
	                width: 140,
	                formatter:getDate,
	                editor: {
	                    type: 'datebox',
	                },
	                sortable:true,
	            }, {
	                field: 'foodTypeId',
	                title: '食谱类型',
	                width: 100,
	                formatter: foodtype,
	                editor: {
	                    type: 'combobox',
	                    options: {
	                        valueField: 'id',
	                        textField: 'className',
	                        data: foodType,
	                        required: true,             
	                        editable:false,
	                        panelHeight: 'auto'
	                    }
	                },sortable:true,
	            }, {
	                field: 'detail',
	                title: '食谱内容',
	                width: 200,
	            	editor: {  
	            		type:'validatebox',
	            		 options: {
		                        required: true,
		                    }
                    },sortable:true,
	               
	            }, {
	                field: 'foodPicId',
	                title: '图片选择',
	                width: 150,
	                formatter: foodpicId,
	                editor: {
	                    type: 'combobox',
	                    options: {
	                        valueField: 'id',
	                        textField: 'clientName',
	                        data: pics,
	                        //editable:false,
	                        filter: function(q, row){
	                    		var opts = $(this).combobox('options');
	                    		return row[opts.textField].indexOf(q) == 0;
	                    	}
	                    }
	                }
	            }, {
	                field: 'action',
	                title: '操作',
	                width: 100,
	                align: 'center',
	                formatter: function(value, row, index) {
	                    if (row.editing) {
	                        var s = '<a href="#"  onclick="saverow(' + index + ')">保存</a> ';
	                        var c = '<a href="#"  onclick="cancelrow(' + index + ')">取消</a>';
	                        return s + c;
	                    } else {
	                        var e = '<a href="#"  onclick="editrow(' + index + ')">编辑</a> ';
	                        var d = '<a href="#"  onclick="deleterow(' + index + ')">删除</a>';
	                        return e + d;
	                    }
	                }
	            }]
	        ],
/*  	        toolbar: [{
	            text: '增加',
	            iconCls: 'icon-add',
	            handler: addrow
	        }, {
	            text: '取消',
	            iconCls: 'icon-cancel',
	            handler: cancelall
	        }, {
	            text: '图片上传',
	            iconCls: 'icon-add',
	            handler: uploaderFun
	        }],  */
	        onBeforeEdit: function(index, row) {
	            row.editing = true;
	            $('#grid').datagrid('refreshRow', index);
	            editcount++;
	        },
	        onAfterEdit: function(index, row) {
	            row.editing = false;
	            $('#grid').datagrid('refreshRow', index);
	            editcount--;
	        },
	        onCancelEdit: function(index, row) {
	            row.editing = false;
	            $('#grid').datagrid('refreshRow', index);
	            editcount--;
	        }
	    })
	};
	var editcount = 0;
	
	function editrow(index) {
		 $('#grid').datagrid('beginEdit', index);
		 
	     //console.log($('#grid').datagrid('getData').rows[index].foodPicId);
	     //$('[field="foodPicId"] input.textbox-value').val('123')
	     
	}

	
	function deleterow(index) {
	    $.messager.confirm('确认', '是否真的删除?', function(r) {
	        if (r) {
	            var data = $('#grid').datagrid('getData');
	            $.post(ctx + "/delScCookBook.do", {
	                id: data.rows[index].id,
	                schoolId: <%=UserContext.getInstance().getUser().getId()%>
	            }, function(result) {
	                $.messager.show({
	                    title: message.title.normal,
	                    msg: message.dele_success,
	                    timeout: message.timeout,
	                    showType: message.showType
	                });
	                //$('#grid').datagrid('reload'); 
	            });
	            $('#grid').datagrid('deleteRow', index);
	            gridx();
	        }
	    });
	}
	
	function saverow(index) {
	    $('#grid').datagrid('endEdit', index);
	    var data = $('#grid').datagrid('getData');
	    console.log(data);
		if(!data.rows[index].editing){
			var foodPicId
			if(data.rows[index].foodPicId!=""){
				foodPicId=data.rows[index].foodPicId
			}else{
				foodPicId=0;				
			}
			console.log(foodPicId);
			alert(<%=UserContext.getInstance().getUser().getId()%>);
		    $.post(ctx + "updateScCookBook.do", {
		        id: data.rows[index].id,
		        publishDate: data.rows[index].publishDate,
		        foodTypeId: data.rows[index].foodTypeId,
		        foodPicId: foodPicId,
		        schoolId: <%=UserContext.getInstance().getUser().getId()%>,
		        detail: data.rows[index].detail,
		        agentId:agentId
		    }, function(result) {
		        if (result.indexOf("success")) {
		            $.messager.show({
		                title: message.title.normal,
		                msg: message.update_success,
		                timeout: message.timeout,
		                showType: message.showType
		            });
		
		        }
		    })
		}else{
			 $.messager.show({
	                title: message.title.normal,
	                msg: message.update_fail,
	                timeout: message.timeout,
	                showType: message.showType
	            });
		} 
	}
	
	function cancelrow(index) {
	    $('#grid').datagrid('cancelEdit', index);
	}
	
	function addrow() {
	    if (editcount > 0) {
	        $.messager.alert('警告', '当前还有' + editcount + '记录正在编辑，不能增加记录。');
	        return;
	    };
	    $('#grid').datagrid('appendRow', {
	        publishDate: datanow,
	        foodTypeId: '',
	        detail: '',
	        foodPicId: ''
	    });
	    $.post(ctx + "addScCookBook.do", {
	        publishDate: datanow,
	        foodTypeId: 78,
	        //foodPicId: 0,
	        detail: '',
	        agentId:agentId,
	        schoolId: <%=UserContext.getInstance().getUser().getId()%>
	    }, function(result) {
	        if (result.indexOf("success")) {
	            $.messager.show({
	                title: message.title.normal,
	                msg: message.update_success,
	                timeout: message.timeout,
	                showType: message.showType
	            });
	        };
	        users.splice(0, users.length);
	        $.getJSON(ctx + 'listAllScCookBook.do?publishDateStartLSch=' + datasearchstart + '&publishDateEndLSch=' + datasearchend + '&schoolIdSch=' + <%=UserContext.getInstance().getUser().getId()%>, getall);
	        gridx();
	    }) 
	}
	
	function saveall() {
	    $('#grid').datagrid('acceptChanges');
	
	}
	
	function cancelall() {
	    $('#grid').datagrid('rejectChanges');
	}
	
	function foodtype(value) {
		
	    for (var i = 0; i < foodType.length; i++) {
	        if (foodType[i].id == value){
	        	return foodType[i].className;
	        }
	    }
	    return null;
	};
	
	function publishdate(value) {
	    for (var i = 0; i < publishDate.length; i++) {
	        if (publishDate[i].id == value) return publishDate[i].name;
	    }
	    return value;
	};
	
	function foodpicId(value, row) {
		
	    for (var i = 0; i < pics.length; i++) {
	    	if(pics[i].id == 0) return "";
	        if (pics[i].id == value) return pics[i].clientName;
	        
	    }
	    return null;
	};
	//图片上传
	//$(".uploader").on('click', uploaderFun);
	function uploaderFun() {
	    var btn = $(this);
	    var url = '../../uploader.jsp?moduleId=cook&funcId='+<%=UserContext.getInstance().getUser().getId()%>
	    var dialog = fw.modalDialog({
	        title: '选择文件',
	        url: url,
	        width: 700,
	        height: 500,
	        buttons: [{
	            text: '确认选择',
	            handler: function() {
	                dialog.find('iframe').get(0).contentWindow.submitForm(dialog, btn);
	            }
	        }, {
	            text: '关闭窗口',
	            handler: function() {
	                dialog.find('iframe').get(0).contentWindow.closeDialog(dialog);
	                
	            }
	        }]
	    });
	}
        </script>
    </body>
</html>