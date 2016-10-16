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
                                    <th>requestURL</th>
                                    <td><input id="requestURL" name="requestURL" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>header</th>
                                    <td><input id="header" name="header" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>ip</th>
                                    <td><input id="ip" name="ip" class="textbox"      style="height: 22px;" /></td>
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
                    url:ctx+'listSysLog.do',
                    columns:[[
            		                       
            			{ sortable:true,field:'requestURL',title:'requestURL'}
            		                       
            			,{ sortable:true,field:'queryString',title:'queryString'}
            		                       
            			,{ sortable:true,field:'header',title:'header'}
            		                       
            			,{ sortable:true,field:'ip',title:'ip'}
            		                       
            			,{ sortable:true,field:'id',title:'id'}
            		                       
            			,{ sortable:true,field:'uuid',title:'uuid'}
            		                       
            			,{ sortable:true,field:'userId',title:'userId'}
            		                       
            			,{ sortable:true,field:'userName',title:'userName'}
            		                       
            			,{ sortable:true,field:'sortId',title:'sortId'}
            		                       
            			,{ sortable:true,field:'isDeleted',title:'isDeleted'}
            		                       
            			,{ sortable:true,field:'operaterId',title:'operaterId'}
            		                       
            			,{ sortable:true,field:'editedTime',title:'editedTime'}
            		                       
            			,{ sortable:true,field:'createdTime',title:'createdTime'}
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
                                $.post(ctx+"delSysLog.do", {
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
                            $.post(ctx+"updateSysLog.do", fw.serializeObject($('form')), function(result) {
                                if (result.success) {
                                    $.messager.show({
                                        title : message.title.normal,
                                        msg : message.update_success,
                                        timeout : message.timeout,
                                        showType : message.showType
                                    });
            
                                    grid.datagrid("reload");
            
                                    //重新获取form信息内容
                                    $("#form").form("load", ctx+"getSysLogById.do?id=" + idValue);
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
                                $.post(ctx+"addSysLog.do", fw.serializeObject($('form')), function(result) {
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
                        $("#form").form("load",ctx+"getSysLogById.do?id=" + idValue);
                    } else {
                        $("#form").form("reset");
                    }
                })
            });
        </script>
    </body>
</html>