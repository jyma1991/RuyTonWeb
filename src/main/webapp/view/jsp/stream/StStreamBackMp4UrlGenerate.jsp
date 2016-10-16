<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mp4下载地址生成</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<form id="form" name="form" method="post" class="form">                               
                    <fieldset>
                        <legend> Mp4下载地址生成 </legend>
                        <table class="table" >
                            <tbody >
                                <tr >
                                    <th style="width:100px;">ID</th>
                                    <td><input id="id" readonly="readonly" name="id" class="textbox" value=<%=request.getParameter("id") %>  style="height: 22px; background:#eee;width:100px;" readonly="readonly" /></td>
                                </tr>
                                <tr>  
                                    <th>文件名</th>
                                    <td><input id="videoName" name="videoName" class="textbox easyui-validatebox"  style="height: 22px; " missingMessage="必填"  validtype="length[3,25]" data-options="required:true"  /></td>
                                </tr>
                                <tr>
                                    <th>开始时间</th>
                                    <td><input id="start" name="start" class="easyui-datetimebox"  missingMessage="必填"  data-options="required:true"   style="height: 22px;"/></td>
                                </tr>
								<tr>    
                                    <th>结束时间</th>
                                    <td><input id="end" name="end" class="easyui-datetimebox"  style="height: 22px;" missingMessage="必填" data-options="required:true"/></td>
                                </tr>
                                 <tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                                	<th>操作</th>
                                	<td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">生成</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td>
                                </tr>
                                <tr>    
                                    <th>Mp4下载地址</th>
                                    <td>
                                    	<input id="url" name="url" class="textbox"  style="float:left;height: 22px;" disabled="disabled"/>
                                    	<a href="javascript:void(0);" class="btn-style" style="float:left; margin-left:10px;" title="" >复制</a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
	</div>
	<script type="text/javascript">
        var closeDialog = function($dialog) {
			$dialog.dialog('destroy');
		}
        
        //确认提交操作
        $("#operateMode_submit").click(function() {
            var idValue = $("#form input[name='id']").val();
            if ($('#form').form('validate')) {
                    //提交
                    $.post(ctx+"/getDownloadMp4Rrl.do", fw.serializeObject($('form')), function(result) {
                        if (result.success) {
                        	$("#url").val(result.downloadUrl);
                            $.messager.show({
                                title : message.title.normal,
                                msg : '生成成功！',
                                timeout : message.timeout,
                                showType : message.showType
                            });
                        }else{
                        	$.messager.show({
                                title : message.title.normal,
                                msg : '生成失败！请查询该视频是否正常连接',
                                timeout : message.timeout,
                                showType : message.showType
                            });
                        }
                        //启用复制功能 
                    	$(".btn-style").zclip({
                        	path:ctx+'assets/swf/ZeroClipboard.swf',
                        	copy:function(){
                        		return $("#url").val();
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
                    	//复制div格式化
                        $(".zclip").css({
                        	"position":"relative",
                        	"top":"-25px",
                        	"left":"160px",
                        	
                        });
                    }, 'json');
            } else {
               	var videoName = $("#videoName").val();
               	var start = $("#start").val();
               	var end = $("#end").val();
              	if(videoName.trim().length == 0){
               		$.messager.show({
                        title : message.title.normal,
                        msg : '请输入文件名！',
                        timeout : message.timeout,
                        showType : message.showType
                    });
           			return;
              	}
              	if(start.trim().length == 0){
              		$.messager.show({
                        title : message.title.normal,
                        msg : '请输入开始时间！',
                        timeout : message.timeout,
                        showType : message.showType
                    });
          			return;
          		}
              	if(end.trim().length == 0){
              		$.messager.show({
                        title : message.title.normal,
                        msg : '请输入结束时间！',
                        timeout : message.timeout,
                        showType : message.showType
                    });
          			return;
          		}
            }
        })
        //重置操作
        $("#operateMode_reset").click(function() {
            var idValue = $("#form input[name='id']").val();
            if (idValue > 0) {
            	$("#videoName").val("");
            	$("#start").val("");
            	$("#end").val("");
            }

        })
    </script>
</body>
</html>