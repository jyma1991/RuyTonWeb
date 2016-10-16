<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/rms" prefix="rms" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>政教通后台管理系统</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>

    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:100px;">
                <form id="searchForm">
                    <table>
                        <tr>
                        	<rms:role operateCode="add">
                            <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
                            </rms:role>
                            <rms:role operateCode="delete">
                            <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
                            </rms:role>
                            <td><a onclick="refreshStreamStatus()" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
                            <td><a onclick="getDownloadMp4Url()" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'">mp4下载地址</a></td>
                       		 <td><a onclick="realDeleteStream()" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">真删除</a></td>
                            <td><a onclick="synchronizedStream()" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">同步</a></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                       	 	
                            <td>主题</td>
                            <td><input name="titleSch" class="textbox" style="width: 120px; height: 22px;" /></td>
                            <td>设备名称</td>
                            <td><input name="deviceNameSch" class="textbox" style="width: 120px; height: 22px;" /></td>
                            <td>创建时间</td>
                            <td><input name="createdStartSch" class="easyui-datebox" style="width: 100px" />
                                -
                                <input name="createdEndSch" class="easyui-datebox" style="width: 100px" />
                            </td>
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="console.log(fw.serializeObject($('#searchForm')));grid.datagrid('load',fw.serializeObject($('#searchForm')));">检索</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">清空检索条件</a></td>
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
                	<input type="hidden" id="userId" name="userId" class="textbox"   style="height: 22px;" />                                
                  	<input type="hidden" id="userName" name="userName" class="textbox"   style="height: 22px;" />
                  	<input type="hidden" id="uuid" name="uuid" class="textbox" value="0"   style="height: 22px;" />          
                  	<input type="hidden" id="isDeleted" value="0" name="isDeleted" class="textbox"   style="height: 22px;" />
                  	<input type="hidden" id="operaterId" value="0" name="operaterId" class="textbox"   style="height: 22px;" />
                  	<input type="hidden" id="hidden-agentId" name="agentId" class="textbox"   style="height: 22px;" />
                  	<input type="hidden" id="hidden-schoolId" name="schoolId" class="textbox"   style="height: 22px;" />
                  	<input type="hidden" id="hidden-classId" name="classId" class="textbox"   style="height: 22px;" />
                    <fieldset>
                        <legend> 基本信息--<span id="operateMode_title">添加模式</span> </legend>
                        <table id="copyTable" class="table">
                        	<tr>
                            		<th class="blockStyle" style="width:80px;"> 推流地址</th>
                            		<td>	
                            			<a id="publishCopy" href="javascript:void(0);" class="btn-style btn-copy">复制<input class="hidden-val" type="hidden" value="" /></a>   		
                            		</td>
                            		<th class="blockStyle" style="width:80px;"> 播放地址</th>
                            		<td>
                                        <a id="playCopy" href="javascript:void(0);" class="btn-style btn-copy">复制<input class="hidden-val" type="hidden" value="" /></a>
                            		</td>
                            		<th class="blockStyle" style="width:80px;"> 测试播放</th>
                            		<td>
                                    	<a id="playVideo" href="javascript:void(0);" class="btn-style">播放<input class="hidden-val" type="hidden" value="" /></a>
									</td>
									<th class="blockStyle" style="width:80px;"> 阻止推流</th>
                            		<td>
                                    	<a id="forbidpublish" href="javascript:void(0);" class="btn-style">阻止<input class="hidden-val" type="hidden" value="" /></a>
                            		</td>
                            		
							</tr>
                        </table>
                        <table class="table" >
                            <tbody >
                                <tr >
                                    <th style="width:80px;">ID</th>
                                    <td><input id="id" readonly="readonly" name="id" class="textbox" value=0  style="height: 22px; background:#eee;width:80px;" readonly="readonly" /></td>
                                </tr>
                                <tr>  
                                    <th>流ID</th>
                                    <td><input id="streamId" name="streamId" class="textbox"  style="height: 22px; background:#eee;width:300px;" readonly="readonly"  /></td>
                                </tr>
                                <tr>
                                    <th>主题</th>
                                    <td><input id="title" name="title" class="textbox easyui-validatebox"  missingMessage="必填"  validtype="length[5,30]" data-options="required:true"   style="height: 22px;"/></td>
                                </tr>
								<tr>    
                                    <th>中心名称</th>
                                    <td><input id="hub" name="hub" class="textbox"   style="height: 22px; background:#eee;" disabled="disabled"  /></td>
                                </tr>
                                <tr>   
                                    <th>推流键</th>
                                    <td><input id="publishKey" name="publishKey" class="textbox easyui-validatebox"  missingMessage="必填" data-options="required:true"  style="height: 22px;" /></td>
                                </tr>
                                <tr>   
                                    <th>推流安全类别</th>
                                    <td>
                                    	<input  name="publishSecurity"  type="radio" value="static"    style="height: 22px;float:left;" checked/><span style="line-height:28px;display:block; float:left;" >静态</span>
                                    	<input  name="publishSecurity"  type="radio" value="dynamic"   style="height: 22px;float:left;" /><span style="line-height:28px;display:block; float:left;" >动态</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th>设备名称</th>
                                    <td><input id="deviceName" name="deviceName" class="textbox easyui-validatebox" missingMessage="必填" data-options="required:true"  style="height: 22px;width:200px;"/></td>
                                </tr>
								<tr>    
                                    <th>设备编号</th>
                                    <td><input id="deviceId" name="deviceId" class="textbox"   style="height: 22px;width:200px;"   /></td>
                                </tr>
                                 <tr> 
                                    <th>描述</th>
                                    <td>
                                    	<textarea cols="100" id="description" name="description" class="textbox"   style="width:300px;height: 44px;line-height:22px;" ></textarea>                        	
                                    </td>
                                </tr>
                                <tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                                	<th>序号</th>
                                	<td><input id="sortId" name="sortId"
                                            class="easyui-validatebox easyui-numberspinner"
                                            data-options="min:0,max:100,required:true" value="0"   style="width: 50px;" />
                                    </td>
                                </tr>  
                                <tr>   
                                    <th>代理商</th>
                                    <td>
                                    	<input id="agentId" name="scAgent.agentId" class="textbox" editable="false"  style="height: 22px;"/>
                                    	<a href="#" onclick="clearSet()">清除</a>
                                    </td>
                                </tr>
                                <tr>    
                                    <th>学校</th>
                                    <td><input id="schoolId" name="scSchool.schoolId" class="textbox"  editable="false"  style="height: 22px;" /></td>
                                </tr>
                                <tr>   
                                    <th>场地</th>
                                    <td><input id="classId" name="scClass.id" class="textbox"  editable="false"  style="height: 22px;" /></td>
                                </tr>
                                <tr>     
                                    <th>rtmp<br/>推流地址:</th>
                                    <td><input id="publishRtmpUrl" name="publishRtmpUrl" class="textbox"    style="width:300px;height: 22px; background:#eee;" disabled="disabled"  /></td>
                                </tr>
								<tr>
                                    <th>rtmp<br/>播放地址:</th>
                                    <td><input id="playRtmpUrl" name="playRtmpUrl" class="textbox"  style="width:300px; height: 22px; background:#eee;" disabled="disabled"  /></td>
                                </tr>
                                <tr>
                                    <th>Hls<br/>播放地址:</th>
                                    <td><input id="playHlsUrl" name="playHlsUrl" class="textbox"  style="width:300px; height: 22px; background:#eee;" disabled="disabled"  /></td>
                                </tr>
                                <tr>
                                    <th>流状态</th>
                                    <td><input id="streamStatus" name="streamStatus" class="textbox"   style="width:100px;height: 22px; background:#eee;" disabled="disabled"  /></td>
                                </tr>
                                <tr>  
                                    <th>地址</th>
                                    <td><input id="addr" name="addr" class="textbox"      style="width:200px;height: 22px;background:#eee;" disabled="disabled"/></td>
                                </tr>
                                <tr>   
                                    <th>阻止推流</th>
                                    <td><input id="disabled"  name="disabled" class="textbox"   style="width:30px;height: 22px;background:#eee;" disabled="disabled"/></td>
                                </tr>      
                                <tr>   
                                    <th>批量操作</th>
                                    <td><input id="batch" name="batch" class="easyui-validatebox easyui-numberspinner"
                                            data-options="min:1,max:100,required:true" value="1"   style="width: 50px;" /></td>
                                </tr>                
                                <tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                                	<th>操作</th>
                                	<td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <script type="text/javascript">
            $(function() {
                var gird;
                grid=$('#grid').datagrid({
                    fit:true,
                    border : false,
                    collapsible : false,
                    pagination : true,
                    singleSelect : true,
                    rownumbers : true,
                    url:ctx+'listStStream.do',
                    columns:[[
  		            			{field:'id',title:'ID',sortable:true}
  		            			,{field:'title',title:'主题',sortable:true}
  		            			,{field:'deviceName',title:'设备名称',sortable:true}
  		            			,{field:'agentId',title:'代理商',formatter: function(value, row, index){
  		            				if(row.scAgent){
  		            					return row.scAgent.trueName;
  		            				}else{
  		            					return ''; 
  		            				}
  		            			},sortable:true}
  		            			,{field:'schoolId',title:'学校',formatter: function(value, row, index){
  		            				if(row.scSchool){
  		            					return row.scSchool.schoolName;
  		            				}else{
  		            					return ''; 
  		            				}
  		            			},sortable:true}
  		            			,{field:'classId',title:'场地',formatter: function(value, row, index){
  		            				if(row.scClass){
  		            					return row.scClass.className;
  		            				}else{
  		            					return ''; 
  		            				}
  		            			},sortable:true}
  		            			/**
  		            			,{field:'publishRtmpUrl',title:'推流地址',width:80,formatter: function(value, row, index){
  		            				return '<a href="javascript:void(0);" class="btn-style btn-copy'+index+3+'" title="'+value+'" >复制<input class="hidden-val" type="hidden" value="'+value+'" /></a>';
  		            			},sortable:true}
  		            			,{field:'playRtmpUrl',title:'播放地址',width:80,height:25,formatter: function(value, row, index){//onclick="setCopy(\''+value+'\')"
		            			        return '<a href="javascript:void(0);" class="btn-style btn-copy'+index+4+'" title="'+value+'">复制<input class="hidden-val" type="hidden" value="'+value+'" /></a>';
  		            			},sortable:true}
  		            			,{field:'testPlay',title:'测试播放',width:80,height:25,formatter: function(value, row, index){//onclick="setCopy(\''+value+'\')"
  		            				return '<a href="javascript:void(0);" class="btn-style" onclick="playStream(\''+row.playRtmpUrl+'\',\''+row.deviceName+'\')">播放</a>';
  		            			},sortable:true}**/
  		            			,{field:'streamStatus',title:'连接状态',formatter: function(value, row, index){
  		            				if(value =='disconnected'){
  		            					return '未连接';
  		            				}else{
  		            					return '已连接'; 
  		            				}
  		            			},sortable:true}
  		            			,{field:'startTime',title:'开始时间',formatter: function(value, row, index){
  		            				return value.substring(0,10);
  		            			},width:70,sortable:true}
  		            			,{field:'bytesPerSecond',title:'每秒字节数',align:"right",sortable:true,width:80}
  		            			,{field:'disabled',title:'阻止推流',formatter: function(value, row, index){
  		            			    /**if (value == "0") {
  		            			        return '<a href="javascript:void(0);" class="btn-style" onclick="changeDisabled(1,'+row.id+')"><span class="cancel-btn">阻止</span></a>';
  		            			    }
  		            			    else {
  		            			        return '<a href="javascript:void(0);" class="btn-style" onclick="changeDisabled(0,'+row.id+')"><span class="cancel-btn">允许</span></a>';
  		            			    }**/
  		            			    if(value == '0'){
  		            			    	return '否';
  		            			    }else{
  		            			    	return '是';
  		            			    }
  		            			},sortable:true}
  		            			,{field:"sortId",title:'序号',sortable:true,width:45}
  		            			,{field:"createdTime",title:'创建时间',formatter: function(value, row, index){
  		            				return value.substring(0,10);
  		            			},width:70,sortable:true}
  		                    ]],
                    onLoadSuccess : function(data) {
                    	$(".datagrid-btable tr").css({
                    		"padding":"5px auto",
            				"height":"30px"
                    	});
                        $('.iconImg').attr('src', fw.pixel_0);
                        $(".datagrid-row div").css({
                        	"line-height":"25px"
                        });
                        //调整排序图标 
                        $(".datagrid-sort-icon").css({
                        	"padding":"0px"
                        })
                        //格式化允许推流值
                        var disabled = ($("#disabled").val() == 0 || $("#disabled").val() == '否')? '否':'是';
                        $("#disabled").val(disabled);
                        //格式化流状态
                        var streamStatus = ($("#streamStatus").val() == 'disconnected' || $("#streamStatus").val() == '未连接' )? '未连接':'已连接';
                        if($("#streamStatus").val()!=""){
                        	$("#streamStatus").val(streamStatus);
                        }
                        //启用title属性
                        $("#title").attr("disabled",false);
                        //$("#title").css("background","#fff");
                        //启用复制功能 
                    	$("a[class*=btn-copy]").zclip({
                        	path:ctx+'assets/swf/ZeroClipboard.swf',
                        	copy:function(){
                        		return $($(this).find(".hidden-val")).val();
                        	},
                        	afterCopy:function(){
                        		$.messager.show({
                                    title : message.title.normal,
                                    msg : '复制成功',
                                    timeout : message.timeout,
                                    showType : message.showType
                                });
                            }
                        });
                      	var marginLeft = ($("#copyTable td").width()-50)/2+1;
                        //复制div格式化
                        $(".zclip").css({
                        	"position":"fixed",
                        	"margin":"0px auto",
                        	"left":"",
                        	"top":"",
                        	"margin-left":marginLeft+"px",
                        	"margin-top":"-25px"
                        	/*"top":"60px",
                        	"left":"153px"*/
                        });
                    },
                    onClickRow : function(index, row) {
                    	$("#description").val("");

                        setTimeout(function(){
                        	loadItem(row);
                        },100);
                        $("#form").form("myLoad", row);
                    	//清空学校和场地下拉框
   	            		DS.combobox("clear");
   	            		ES.combobox("clear");
                        $("#operateMode_title").html("修改模式");
                        
                        //清除批量操作数量
                        $("#batch").numberspinner('setValue', 1);
	                    //格式化按钮
	                    $($("#publishCopy").find(".hidden-val")).val($("#publishRtmpUrl").val());
	                    $($("#playCopy").find(".hidden-val")).val($("#playRtmpUrl").val());
	                    //先取消绑定
	                    $('#playVideo').unbind("click");
	                    $("#playVideo").click(function(){
	                    	playStream(row.playRtmpUrl,row.deviceName);
	                    });
	                    //先取消绑定
	                    $('#forbidpublish').unbind("click");
	                    $("#forbidpublish").click(function(){
	                    	changeDisabled(1-row.disabled,row.id);
	                    });
                        //格式化允许推流值
                        var disabled = ($("#disabled").val() == 0 || $("#disabled").val() == '否')? '否':'是';
                        $("#disabled").val(disabled);
                        $("#forbidpublish").html(disabled == '是'?'允许':'阻止');
                        //格式化流状态
                        var streamStatus = ($("#streamStatus").val() == 'disconnected' || $("#streamStatus").val() == '未连接' )? '未连接':'已连接';
                        $("#streamStatus").val(streamStatus);
                        //禁用title属性
                        $("#title").attr("disabled",true);
                        $("#title").css("background","#eee");
                        $("#title").removeClass("validatebox-invalid").addClass("disabled-color");

                    }
                });
                //添加操作
                $("#btn_add").click(function() {
                    $("#operateMode_title").html("添加模式");
                    $('#grid').datagrid("unselectAll");
                    $("#id").attr("value", 0);
                    $("#form").form("reset");
                    //格式化阻止推流属性 
                    $("#disabled").val('否');
                  	//清空流状态
                    $("#streamStatus").val("");
                    //启用title属性
                    $("#title").attr("disabled",false);
                    $("#title").css("background","");
                    $("#title").addClass("validatebox-invalid").removeClass("disabled-color");
                    $("#title").focus();
                    //格式化按钮
                    $($("#publishCopy").find(".hidden-val")).val("");
                    $($("#playCopy").find(".hidden-val")).val("");
                  	//先取消绑定
                    $('#playVideo').unbind("click");
                    $('#forbidpublish').unbind("click");
                    $("#forbidpublish").html('阻止');
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
                                $.post(ctx+"delStStream.do", {
                                    id : idValue
                                }, function(result) {
                                    if (result.success) {
                                    	//清空流状态
                                        $("#streamStatus").val("");
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
                            $.post(ctx+"updateStStream.do", fw.serializeObject($('form')), function(result) {
                                if (result.success) {
                                    $.messager.show({
                                        title : message.title.normal,
                                        msg : message.update_success,
                                        timeout : message.timeout,
                                        showType : message.showType
                                    });
            
                                    grid.datagrid("reload");
                                  	//格式化允许推流值
                                    var disabled = ($("#disabled").val() == 0 || $("#disabled").val() == '否')? '否':'是';
                                    $("#disabled").val(disabled);
                                    //格式化流状态
                                    var streamStatus = ($("#streamStatus").val() == 'disconnected' || $("#streamStatus").val() == '未连接' )? '未连接':'已连接';
                                    $("#streamStatus").val(streamStatus);
                                    //重新获取form信息内容
                                    $("#form").form("load", ctx+"getStStreamById.do?id=" + idValue);
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
                            	var title = $("#title").val();
                            	var status = $("INPUT:checked").val();
                            	var publishKey = $("#publishKey").val();
                            	if(title.trim().length == 0){
                            		$.messager.show({
                                        title : message.title.normal,
                                        msg : '请输入流主题！',
                                        timeout : message.timeout,
                                        showType : message.showType
                                    });
                        			return;
                            	}
                            	if(publishKey.trim().length == 0){
                        			$.messager.show({
                                        title : message.title.normal,
                                        msg : '请输入推流键！',
                                        timeout : message.timeout,
                                        showType : message.showType
                                    });
                        			return;
                        		}
                            	if(status == null || status.length==0){
                            		$.messager.show({
                                        title : message.title.normal,
                                        msg : '请选择状态！',
                                        timeout : message.timeout,
                                        showType : message.showType
                                    });
                            		return;
                            	}
                                //添加
                                $.post(ctx+"addStStream.do", fw.serializeObject($('form')), function(result) {
                                    if (result.success) {
                                        $.messager.show({
                                            title : message.title.normal,
                                            msg : message.add_success,
                                            timeout : message.timeout,
                                            showType : message.showType
                                        });
                                        grid.datagrid("reload");
                                        $("#form").form("reset");
                                    }else{
                                    	$.messager.show({
                                            title : message.title.normal,
                                            msg : result.message,
                                            timeout : message.timeout,
                                            showType : message.showType
                                        });
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
                    	$("#disabled").val("");
                        $("#form").form("load",ctx+"getStStreamById.do?id=" + idValue);
                        setTimeout(function(){
                            var disabledVal = ($("#disabled").val() == 0 || $("#disabled").val() == '否')? '否':'是';
                            $("#disabled").val(disabledVal);
                            //格式化流状态
                            var streamStatus = ($("#streamStatus").val() == 'disconnected' || $("#streamStatus").val() == '未连接' )? '未连接':'已连接';
                            $("#streamStatus").val(streamStatus);
                        },20);
                    } else {
                        $("#form").form("reset");
                        $("#disabled").val('否');
                    }

                })
            });
            //刷新获取服务器的流状态并同步到服务器中 
            function refreshStreamStatus(){
            	$.messager.show({
                    title : message.title.normal,
                    msg : '正在刷新流状态！请稍等...',
                    timeout : message.timeout,
                    showType : message.showType
                });
            	$.post(ctx+'/refreshAllStream.do',{},function(data){
            		var json = JSON.parse(data);
            		if(json.success){
            			grid.datagrid('reload');
            		}else{
            			$.messager.show({
                            title : message.title.normal,
                            msg : '刷新失败！',
                            timeout : message.timeout,
                            showType : message.showType
                        });
            		}
            	},'json');
            	
            }
            //更改流是否往服务器推 
            function changeDisabled(val,id){
           		$.post(ctx+'/changeStreamDisabled.do',{id:id,disabled:val},function(data){
               		if(data.success){
               			grid.datagrid('reload');
               			var disabledVal = (val == 0 || val == '否')? '否':'是';
                        $("#disabled").val(disabledVal);
                        $("#forbidpublish").html(disabledVal == '是'?'允许':'阻止');
               		}else{
               			$.messager.show({
                            title : message.title.normal,
                            msg : '刷新失败！',
                            timeout : message.timeout,
                            showType : message.showType
                        });
               		}
               	},'json');
            }
            function playStream(url,title){
            	if(title == 'undefined'){
            		title="";
            	}
            	var dialog = fw.modalDialog({
                    title: '流播放',
                    url: ctx+'/view/jsp/stream/StStreamPlay.jsp?url='+url+'&title='+title,
                    width: 800,
                    height: 620,
                    buttons: [
                        {
                        text: '取消',
                        handler: function() {
                            dialog.find('iframe').get(0).contentWindow.closeDialog(dialog);
                        }
                    }]
                });
            	
            	/*
            	if(title == 'undefined'){
            		title="";
            	}
            	window.open(ctx+'/view/jsp/stream/StStreamPlay.jsp?url='+url+'&title='+title);
            	*/
            }
            function getDownloadMp4Url(){
            	var rows = $('#grid').datagrid('getSelections');
                if (rows <= 0) {
                    $.messager.show({
                        title : message.title.normal,
                        msg : message.grid_select,
                        timeout : message.timeout,
                        showType : message.showType
                    });
                } else {
                	 var idValue = $("#form input[name='id']").val();
                	 var dialog = fw.modalDialog({
                         title: 'Mp4下载地址生成',
                         url: ctx+'/view/jsp/stream/StStreamBackMp4UrlGenerate.jsp?id='+idValue,
                         width: 500,
                         height: 360,
                         buttons: [
                             {
                             text: '取消',
                             handler: function() {
                                 dialog.find('iframe').get(0).contentWindow.closeDialog(dialog);
                             }
                         }]
                     });
                }
            }
            function realDeleteStream(){
            	$.post(ctx+'realDelAllStStream.do',{},function(data){
            		var data = JSON.parse(data);
               		if(data.success == "true"){
               			grid.datagrid('reload');
               			$.messager.show({
                            title : message.title.normal,
                            msg : '删除成功！',
                            timeout : message.timeout,
                            showType : message.showType
                        });
               		}else{
               			$.messager.show({
                            title : message.title.normal,
                            msg : '真删除之前，确保数据库中存在假删除数据！',
                            timeout : message.timeout,
                            showType : message.showType
                        });
               		}
               	},'json');
            }
            function synchronizedStream(){
            	$.messager.show({
                    title : message.title.normal,
                    msg : '正在同步流！请稍等...',
                    timeout : message.timeout,
                    showType : message.showType
                });
            	$.post(ctx+'synchronizedStream.do',{},function(data){
            		var data = JSON.parse(data);
               		if(data.success == 'true'){
               			grid.datagrid('reload');
               			$.messager.show({
                            title : message.title.normal,
                            msg : '同步成功！',
                            timeout : message.timeout,
                            showType : message.showType
                        });
               		}else{
               			if(data.error){
               				$.messager.show({
                                title : message.title.normal,
                                msg : data.error,
                                timeout : message.timeout,
                                showType : message.showType
                            });
               				return;
               			}
               			$.messager.show({
                            title : message.title.normal,
                            msg : '同步失败！',
                            timeout : message.timeout,
                            showType : message.showType
                        });
               		}
               	},'json');
            }
            var CS = null;
            var DS = null;
            var ES = null;
            var CSloadFlag = false;
            var DSloadFlag = false;
            var ESloadFlag = false;
            //代理商初始化
        	$.getJSON(ctx+'listAllScAgent.do',function(agentJson) {
        	 	CS = $('#agentId').combobox({
            		    data:agentJson.rows,
            	        valueField:'agentId',
            	        textField:'trueName',
            	        DropDownStyle:'DropDownList',
            	        onSelect:function(row){
            	        	DSloadFlag = false;
            	        	if(row){
            	        		$("#hidden-agentId").val(row.agentId);
            	        		//取消保存班级id
            	        		$("#hidden-classId").val(0);
            	        		$.getJSON(ctx+'listAllScSchool.do',{agentIdSch:row.agentId},function(schoolJson) {
	            	        		DS= $('#schoolId').combobox({
		        	        			data : schoolJson.rows,
		        	        			valueField:'schoolId',
		        	         	        textField:'schoolName',
		        	         	        DropDownStyle:'DropDownList',
		        	         	        onSelect:function(row){
		        	         	        	ESloadFlag = false;
		        	         	       		if(row){
		        	         	       			$("#hidden-schoolId").val(row.schoolId);
			        	         	       		$.getJSON(ctx+'listAllScClass.do',{schoolIdSch:row.schoolId},function(classJson) {
			        	         	       		ES= $('#classId').combobox({
			    		        	        			data : classJson.rows,
			    		        	        			valueField:'id',
			    		        	         	        textField:'className',
			    		        	         	        DropDownStyle:'DropDownList',
			    		        	         	        onSelect:function(row){
			    		        	         	       		if(row){
			    		        	         	       			$("#hidden-classId").val(row.id);
			    		        	         	       		}
			    											
			    		        	        	        },
			    		        	        	        onLoadSuccess:function(){
			    		        	        	        	ESloadFlag = true;
			    		                    	        }
			    		        	        		})
			                	        		});
		        	         	       		}
		        	        	        },
		        	        	        onLoadSuccess:function(){
		        	        	        	DSloadFlag = true;
		                    	        }
		        	        		})
            	        		});
            	        	}
            	        },
            	        onLoadSuccess:function(){
            	        	CSloadFlag = true;
            	        }
            	}) 
        	});
            //学校初始化
            DS = $('#schoolId').combobox();
            //班级初始化
            ES = $('#classId').combobox();
            
            //读取代理商学校以及班级下拉框的数据
            function loadItem(row){
            	 if(row){
            		 var agentId = 0;
            		 if(row.scAgent){
            			 agentId = row.scAgent.id;
            		 }
	                 //代理商初始化
	                 $.getJSON(ctx+'listAllScAgent.do',{agentIdSch:agentId},function(agentJson) {
	   	            	if(agentJson.rows.length==0){
	   	            		CS.combobox("clear");
	   	            		DS.combobox('loadData',{schoolId:0,schoolName:" "});
	   	                 	ES.combobox('loadData',{id:0,className:" "});
	   	            	}else{
	   	            		CS.combobox("clear").combobox("select",agentId);
	   	            		var Did=setInterval(function(){
	   	            			if(DSloadFlag){
	   	            				clearInterval(Did);
	   	            				DS.combobox("clear").combobox("select",row.schoolId);
	   	            				var Eid = setInterval(function(){
	   		   	            			if(ESloadFlag){
	   		   	            				clearInterval(Eid);
	   		   	            				ES.combobox("clear").combobox("select",row.classId);
	   		   	            			}
	   		   	            		}, 5);
	   	            			}
	   	            		}, 5);
	   	            		
	   	            		/**setTimeout(function(){
	   	            			if(row.schoolId){
		   	            			DS.combobox("select",row.schoolId);
		   	            			if(row.classId){
			   	            			setTimeout(function(){
				   	            			ES.combobox("select",row.classId);
				   	            		},100);
	   	            				}
	   	            			}
	   	            		},50);**/
	   	            		
	   	            	}
	   	             });
            	 }
            }
            //清除数据
            function clearSet(){
            	$('#agentId').combobox('clear');
            	$("#hidden-agentId").val(0);
            	$('#schoolId').combobox('clear');
            	DS.combobox('loadData',{schoolId:0,schoolName:" "});
            	$("#hidden-schoolId").val(0);
            	$('#classId').combobox('clear');
                ES.combobox('loadData',{id:0,className:" "});
            	
            }
        </script>
    </body>
</html>