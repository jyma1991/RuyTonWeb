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
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
                            <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>用户名称</td>
                            <td><input name="user_nameSch" class="textbox" style="width: 120px; height: 22px;" /></td>
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
                        <table class="table">
                            <tbody>
                                                                                                                                                                                                                                                                <tr>
                                    <th>clientName</th>
                                    <td><input id="clientName" name="clientName" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>serverName</th>
                                    <td><input id="serverName" name="serverName" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>fileExt</th>
                                    <td><input id="fileExt" name="fileExt" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>fileSize</th>
                                    <td><input id="fileSize" value="0" name="fileSize" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>fileWidth</th>
                                    <td><input id="fileWidth" value="0" name="fileWidth" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>fileHeight</th>
                                    <td><input id="fileHeight" value="0" name="fileHeight" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>moduleId</th>
                                    <td><input id="moduleId" name="moduleId" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>funcId</th>
                                    <td><input id="funcId" name="funcId" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>dataId</th>
                                    <td><input id="dataId" name="dataId" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>fileFullPath</th>
                                    <td><input id="fileFullPath" name="fileFullPath" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>zipId</th>
                                    <td><input id="zipId" value="0" name="zipId" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>proCode</th>
                                    <td><input id="proCode" name="proCode" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>proFileType</th>
                                    <td><input id="proFileType" name="proFileType" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>createdBy</th>
                                    <td><input id="createdBy" name="createdBy" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>fileTypes</th>
                                    <td><input id="fileTypes" name="fileTypes" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>customerId</th>
                                    <td><input id="customerId" value="0" name="customerId" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                <tr>
                                    <th>id</th>
                                    <td><input id="id" readonly="readonly" name="id" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>uuid</th>
                                    <td><input id="uuid" name="uuid" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>userId</th>
                                    <td><input id="userId" value="0" name="userId" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>userName</th>
                                    <td><input id="userName" name="userName" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>isDeleted</th>
                                    <td><input id="isDeleted" value="0" name="isDeleted" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>sortId</th>
                                    <td><input id="sortId" value="0" name="sortId" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>operaterIid</th>
                                    <td><input id="operaterIid" value="0" name="operaterIid" class="textbox"   style="height: 22px;" /></td>
                                </tr>
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
                    rownumbers : true,
                    pagination : true,
                    singleSelect : true,
                    rownumbers : true,
                    url:ctx+'listSysUpload.do',
                    columns:[[
            		            			{field:'clientName',title:'clientName'}
            		            			,{field:'serverName',title:'serverName'}
            		            			,{field:'fileExt',title:'fileExt'}
            		            			,{field:'fileSize',title:'fileSize'}
            		            			,{field:'fileWidth',title:'fileWidth'}
            		            			,{field:'fileHeight',title:'fileHeight'}
            		            			,{field:'moduleId',title:'moduleId'}
            		            			,{field:'funcId',title:'funcId'}
            		            			,{field:'dataId',title:'dataId'}
            		            			,{field:'fileFullPath',title:'fileFullPath'}
            		            			,{field:'zipId',title:'zipId'}
            		            			,{field:'proCode',title:'proCode'}
            		            			,{field:'proFileType',title:'proFileType'}
            		            			,{field:'createdBy',title:'createdBy'}
            		            			,{field:'fileTypes',title:'fileTypes'}
            		            			,{field:'customerId',title:'customerId'}
            		            			,{field:'id',title:'id'}
            		            			,{field:'uuid',title:'uuid'}
            		            			,{field:'userId',title:'userId'}
            		            			,{field:'userName',title:'userName'}
            		            			,{field:'isDeleted',title:'isDeleted'}
            		            			,{field:'sortId',title:'sortId'}
            		            			,{field:'operaterIid',title:'operaterIid'}
            		            			,{field:'editedTime',title:'editedTime'}
            		            			,{field:'createdTime',title:'createdTime'}
            		                    ]],
                    onLoadSuccess : function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    },
                    onClickRow : function(index, row) {
                        $("#form").form("load", row);
                        $("#operateMode_title").html("修改模式");
                    }
                });
                //添加操作
                $("#btn_add").click(function() {
                    $("#operateMode_title").html("添加模式");
                    $('#grid').datagrid("unselectAll");
                    $("#id").attr("value", 0);
                    $("#uuid").attr("value",Math.uuid(32,62));
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
                                $.post(ctx+"delSysUpload.do", {
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
                            $.post(ctx+"updateSysUpload.do", fw.serializeObject($('form')), function(result) {
                                if (result.success) {
                                    $.messager.show({
                                        title : message.title.normal,
                                        msg : message.update_success,
                                        timeout : message.timeout,
                                        showType : message.showType
                                    });
            
                                    grid.datagrid("reload");
            
                                    //重新获取form信息内容
                                    $("#form").form("load", ctx+"getSysUploadById.do?id=" + idValue);
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
                                $.post(ctx+"addSysUpload.do", fw.serializeObject($('form')), function(result) {
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
                        $("#form").form("load",ctx+"getSysUploadById.do?id=" + idValue);
                    } else {
                        $("#form").form("reset");
                    }
                })
            });
        </script>
    </body>
</html>