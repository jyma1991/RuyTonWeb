<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
                            <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
                            <rms:role operateCode="delete">
                            </rms:role>
                            <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
                        	<!--  <td><a id="btn_export"  href="#" class="easyui-linkbutton btn_scStudent" data-options="iconCls:'ext-icon-page_white_excel'">下载模板</a></td>
                            <td><a id="btn_import"  href="#" class="easyui-linkbutton btn_scStudent" data-options="iconCls:'ext-icon-page_excel'">导入(仅*.xlsx)</a></td>-->
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>课程名称</td>
                            <td><input name="courseNameSch" class="textbox" style="width: 120px; height: 22px;" /></td>
                            <td>创建时间</td>
                            <td><input name="createdStartSch" class="easyui-datebox" style="width: 100px" />
                                -
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
            <div data-options="region:'east',split:true,border:false" style="width: 50%; padding: 5px;">
                <form id="form" name="form" method="post" class="form">
                    <fieldset>
                        <legend> 基本信息--<span id="operateMode_title">添加模式</span> </legend>
                        <input type="hidden" id="schoolId" name="schoolId" class="textbox"  style="height: 22px;" />
                        <input type="hidden" id="userId" name="userId" class="textbox" style="height: 22px;" />
	                    <input type="hidden" id="userName" name="userName" class="textbox" style="height: 22px;" />
	                    <input type="hidden" id="uuid" name="uuid" class="textbox" value="0" style="height: 22px;" />
	                    <input type="hidden" id="isDeleted" value="0" name="isDeleted" class="textbox" style="height: 22px;" />
	                    <input type="hidden" id="operaterId" value="0" name="operaterId" class="textbox" style="height: 22px;" />
                        <table class="table">
                            <tbody>
                                                                                                                                                                                                                                                            <tr>
                                    <th>ID</th>
                                    <td><input id="id" readonly="readonly" name="id" class="textbox"  value=0    style="height: 22px; background:#eee;width:50px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                <tr>
                                    <th>课程</th>
                                    <td><input id="courseName" name="courseName" class="textbox easyui-validatebox" required="true"    style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>时长</th>
                                    <td><input id="length" value="0" name="length" class="textbox"   style="height: 22px; width:50px;" />&nbsp;分钟
                                    </td>
                                </tr>
                                <!--                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>价格</th>
                                    <td><input id="price" value="0" name="price" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                 <tr>
                                    <th>老师</th>
                                    <td><input id="teacherId" value="0" name="teacherId" class="textbox"   style="height: 22px; background:#eee;" readonly/></td>
                                </tr> -->
                                <tr>
				                      <th>选择老师</th>
				                     <td>
				                     <input id="teachers"  name="teachers" class="textbox" style="height: 22px;"  />
				                      </td>
                				</tr>
                                <tr>
	                                <th>
	                                	选择图片
	                                </th>
				                  	<td>
				                  	  <select class="selectFiles">
					                  <option>选择图片</option>
					                  </select> 
				                  	  <a href="#" class="easyui-linkbutton uploader" data-options="iconCls:'icon-add'">  </a> 
				                   	  <input name="img" class="selectFilesValue" type="hidden" />
				               		</td>
                                </tr>
                                <tr>                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>备注</th>
                                    <td>
                                    	<textarea cols="100" id="remark" name="remark" class="textbox"   style="width:300px;height: 44px;line-height:22px;" ></textarea>                        	
                                    </td>
                                </tr>
                                <tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                                	<th>序号</th>
                                	<td><input id="sortId" name="sortId"
                                            class="easyui-validatebox easyui-numberspinner"
                                            data-options="min:0,max:100,required:true" value="0"   style="width: 50px;" />
                                    </td>
                                </tr>  
								<tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              <th>操作</th>
                               	 	<td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <script type="text/javascript">
        	var courseId ;
            $(function() {
                var gird;
                grid=$('#grid').datagrid({
                    fit:true,
                    border : false,
                    collapsible : false,
                    rownumbers : true,
                    pagination : true,
                    singleSelect : true,
                    rownumbers : true,
                    url:ctx+'listScCourse.do',
                    columns:[[
		            						{field:'id',title:'ID',sortable:true}
            		            			,{field:'courseName',title:'课程',sortable:true}
            		            			,{field:'length',title:'时长',sortable:true}
            		            			//,{field:'price',title:'价格',align:"right",sortable:true}
            		            			//,{field:'teacherId',title:'老师',formatter: function(value, row, index){	            				
              		            			//		return ''; 
              		            			//}}
            		            			,{field:'remark',title:'备注',sortable:true}
            		            			,{field:'sortId',title:'序号',sortable:true}
            		            			,{field:'createdTime',title:'创建时间',formatter: function(value, row, index){
              		            				return value.substring(0,10);
              		            			},sortable:true}
            		                    ]],
                    onLoadSuccess : function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    },
                    onClickRow : function(index, row) {
                    	courseId = row.id ;
                    	$("#form").form("clear");
                        $("#form").form("load", row);
                    //    $('#teachers').combobox("clear");
                        $('#teachers').combobox('setValues', row.teachers);
                        loadfile();
                        $("#operateMode_title").html("修改模式");
                    }
                });
                

            

	  $.getJSON(ctx + 'findTeacherListBySchoolId.do', function (Json) {
  	  CS = $('#teachers')
          .combobox({
              data: Json.rows,
              valueField: 'id',
              textField: 'trueName',
              multiple:true,
			  panelHeight:'auto'	
          });
	  });
  
  
                
                
               //绑定上传按钮功能
              $(".uploader").on('click', uploaderFun);
               	function loadfile(){
                   //先对所有uploader的class对象进行遍历
                   $(".uploader")
                       .each(function () {
                           var selectObj = $(this)
                               .prev('.selectFiles');
                           var options = "";
                           //如果该文件选择器有值，则进行异步数据获取
                           if ($(this)
                               .next('.selectFilesValue')
                               .val()
                               .trim()
                               .length != 0) {
                               		var ids = $(this)
                                   	   .next('.selectFilesValue')
                                   	   .val();
	                               	   $.ajax({
	                                       type: "POST",
	                                       url: ctx + "findSysUploadByIds.do",
	                                       data: {
	                                           ids: ids
	                                       }
	                                   })
	                                   .done(function (result) {
	                                       result = JSON.parse(result);
	                                       if (result.length > 0) {
	                                           //对返回结果进行遍历，重组option选项，并将结果放置到相应的selectOption对象中去
	                                           $(result)
	                                               .each(function () {
	                                                   options += "<option>" + this.clientName + "</option>";
	                                               })
	                                           selectObj.html(options);
	                                           selectObj.prepend("<option>选择图片(选中了 " + result.length + " 个)</option>");
	                                       }
	                                   })
                           } else {
                               selectObj.html("<option>选择图片</option>");
                           }
                       });
               	}
               	
               	 //选择文件部分的函数操作，需要将自身对象及ids列表作为参数进行传递
                   function uploaderFun() {
                       var btn = $(this);
                       var ids = "";
                       if ($(this)
                           .next()
                           .val()
                           .trim()
                           .length != 0) {
                           ids = "?ids=" + $(this)
                               .next()
                               .val();
                       }

                       if (ids.length > 0) {
                           url = "../../uploader.jsp?moduleId=sc&funcId=scCourse&dataId=img";
                       } else {
                           url = "../../uploader.jsp?moduleId=sc&funcId=scCourse&dataId=img" 
                       }

                       var dialog = fw.modalDialog({
                           title: '选择文件',
                           url: url,
                           width: 700,
                           height: 500,
                           buttons: [{
                               text: '确认选择',
                               handler: function () {
                                   dialog.find('iframe')
                                       .get(0)
                                       .contentWindow.submitForm(dialog, btn);
                               }
                           },  {
                               text: '关闭窗口',
                               handler: function () {
                                   dialog.find('iframe')
                                       .get(0)
                                       .contentWindow.closeDialog(dialog);
                               }
                           }]
                       });
               	 }
                
                //添加操作
                $("#btn_add").click(function() {
                    $("#operateMode_title").html("添加模式");
                    $('#grid').datagrid("unselectAll");
                    $("#id").attr("value", 0);
                    $("#form").form("reset");
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
                                $.post(ctx+"delScCourse.do", {
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
            
                //确认提交操作
                $("#operateMode_submit").click(function() {
                    var idValue = $("#form input[name='id']").val();
                    if ($('#form').form('validate')) {
                        if (idValue > 0) {
                            //修改
                            $.post(ctx+"updateScCourse.do", fw.serializeObject($('form')), function(result) {
                                if (result.success) {
                                    $.messager.show({
                                        title : message.title.normal,
                                        msg : message.update_success,
                                        timeout : message.timeout,
                                        showType : message.showType
                                    });
            
                                    grid.datagrid("reload");
            
                                    //重新获取form信息内容
                                    $("#form").form("load", ctx+"getScCourseById.do?id=" + idValue);
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
                                //添加
                                $.post(ctx+"addScCourse.do", fw.serializeObject($('form')), function(result) {
                                    if (result.success) {
                                        $.messager.show({
                                            title : message.title.normal,
                                            msg : message.add_success,
                                            timeout : message.timeout,
                                            showType : message.showType
                                        });
                                        grid.datagrid("reload");
                                        $("#form").form("reset");
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
                        $("#form").form("load",ctx+"getScCourseById.do?id=" + idValue);
                    } else {
                        $("#form").form("reset");
                    }
                })
            });
            
        </script>
    </body>
</html>