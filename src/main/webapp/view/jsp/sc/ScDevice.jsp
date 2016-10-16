<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>设备管理</title>
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
                                    <th>Id</th>
                                    <td><input id="id" readonly="readonly" name="id" class="textbox" value=0   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                <tr>
                                    <th>deviceName</th>
                                    <td><input id="deviceName" name="deviceName" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>deviceTypeId</th>
                                    <td><input id="deviceTypeId" value="0" name="deviceTypeId" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>agentId</th>
                                    <td><input id="agentId" value="0" name="agentId" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>remark</th>
                                    <td><input id="remark" name="remark" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>isValid</th>
                                    <td><input id="isValid" value="0" name="isValid" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>streamId</th>
                                    <td><input id="streamId" name="streamId" class="textbox"      style="height: 22px;" /></td>
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
                                    <th>sortId</th>
                                    <td><input id="sortId" value="0" name="sortId" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>isDeleted</th>
                                    <td><input id="isDeleted" value="0" name="isDeleted" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>operaterId</th>
                                    <td><input id="operaterId" value="0" name="operaterId" class="textbox"   style="height: 22px;" /></td>
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
                    url:ctx+'listScDevice.do',
                    columns:[[				{field:'id',title:'ID'}
            		            			,{field:'deviceName',title:'设备名'}
            		            			,{field:'deviceTypeId',title:'设备类型'}
            		            			,{field:'agentId',title:'代理商'}
            		            			,{field:'remark',title:'描述'}
            		            			,{field:'isValid',title:'有效性'}
            		            			,{field:'streamId',title:'流'}
            		            			,{field:'sortId',title:'序号'}
            		            			,{field:'createdTime',title:'创建时间'}
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
                                $.post(ctx+"delScDevice.do", {
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
                            $.post(ctx+"updateScDevice.do", fw.serializeObject($('form')), function(result) {
                                if (result.success) {
                                    $.messager.show({
                                        title : message.title.normal,
                                        msg : message.update_success,
                                        timeout : message.timeout,
                                        showType : message.showType
                                    });
            
                                    grid.datagrid("reload");
            
                                    //重新获取form信息内容
                                    $("#form").form("load", ctx+"getScDeviceById.do?id=" + idValue);
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
                                $.post(ctx+"addScDevice.do", fw.serializeObject($('form')), function(result) {
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
                        $("#form").form("load",ctx+"getScDeviceById.do?id=" + idValue);
                    } else {
                        $("#form").form("reset");
                    }
                })
            });
        </script>
    </body>
</html>