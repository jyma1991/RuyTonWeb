<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>产品管理</title>
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
		                    <td>产品名称</td>
		                    <td>
		                        <input name="productNameSch" class="textbox" style="width: 120px; height: 22px;" />
		                    </td>
		                    <td>创建时间</td>
		                    <td>
		                        <input name="createdStartSch" class="easyui-datebox" style="width: 100px" /> -
		                        <input name="createdEndSch" class="easyui-datebox" style="width: 100px" />
		                    </td>
		                    <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',fw.serializeObject($('#searchForm')));">过滤</a>
		                    	<a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a>
		                    </td>
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
		                            <th>ID</th>
		                            <td>
		                                <input id="id" readonly="readonly" name="id" class="textbox" style="height: 22px;width:40px" />
		                            </td>
		                        </tr>
		                        <tr>
		                            <th>产品名称</th>
		                            <td>
		                                <input id="productName" name="productName" class="textbox" required="true" style="height: 22px;" />
		                            </td>
		                        </tr>
		                        <tr>
		                            <th>图片</th>
		                            <td>
		                                <select class="selectFiles">
		                                    <option>选择文件</option>
		                                </select> <a href="#" class="easyui-linkbutton uploader" data-options="iconCls:'icon-add'">选择文件</a>
		                                <input name="picIds" class="selectFilesValue" type="hidden" />
		                            </td>
		                            <!--     <td><input id="picIds" name="picIds" class="textbox"      style="height: 22px;" /></td> -->
		                        </tr>
		                        <tr>
		                            <th>产品类型</th>
		                            <td>
		                                <input id="productType" name="productType" class="textbox" style="height: 22px;" />
		                            </td>
		                        </tr>
		                        <tr>
		                            <th>产品价格</th>
		                            <td>
		                                <input id="originalPrice" name="originalPrice" class="textbox easyui-validatebox" data-options="validType:'floatOrInt'"  required="true" placeholder="" style="height: 22px;" />
		                            </td>
		                        </tr>
		                        <tr>
		                            <th>折后价</th>
		                            <td>
		                                <input id="presentPrice" name="presentPrice" class="textbox easyui-validatebox" data-options="validType:'floatOrInt'"  required="true" style="height: 22px;" />
		                            </td>
		                        </tr>
		
		
		                        <tr>
		                            <th>产品描述</th>
		                            <td>
		                                <textarea rows="3" id="remark" name="remark" class="textbox" style=" width:90%;white-space: pre-wrap;" wrap="hard" /></textarea>
		                            </td>
		                        </tr>
		                        <tr>
		                            <th>序号</th>
		                            <td>
		                                <input id="sortId" name="sortId" class="textbox" style="height: 22px;" />
		                            </td>
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
            grid = $('#grid').datagrid({
                fit: true,
                border: false,
                collapsible: false,
                rownumbers: true,
                pagination: true,
                singleSelect: true,
                rownumbers: true,
                url: ctx + 'listFinanceChargeProduct.do',
                columns: [
                    [{
                        sortable: true,
                        field: 'productName',
                        title: '产品名称'
                    }, {
                        sortable: true,
                        field: 'productType',
                        title: '产品类型'
                    }, {
                        sortable: true,
                        field: 'originalPrice',
                        title: '价格'
                    }, {
                        sortable: true,
                        field: 'presentPrice',
                        title: '折后价'
                    }, {
                        sortable: true,
                        field: 'sortId',
                        title: '序号'
                    }, {
                        sortable: true,
                        field: 'editedTime',
                        title: '编辑时间'
                    }, {
                        sortable: true,
                        field: 'createdTime',
                        title: '创建时间'
                    }]
                ],
                onLoadSuccess: function(data) {
                    $('.iconImg').attr('src', fw.pixel_0);
                },
                onClickRow: function(index, row) {
                    $("#form").form("load", row);
                    $("#operateMode_title").html("修改模式");
                    EditRecord(row);
                }
            });
            //产品类型
            $('#productType').combobox({
                valueField: 'id',
                textField: 'name',
                panelHeight: 'auto',
                data: [{
                    id: "0",
                    name: "虚拟"
                }, {
                    id: "1",
                    name: "实物"
                }]
            });

            //添加操作
            $("#btn_add").click(function() {
                $("#operateMode_title").html("添加模式");
                $('#grid').datagrid("unselectAll");
                $("#id").attr("value", 0);
                $(".selectFiles").html("<option>选择文件</option>");
                $("#form").form("reset");
            })

            //删除操作
            $("#btn_dele").click(function() {
                var rows = $('#grid').datagrid('getSelections');
                if (rows <= 0) {
                    $.messager.show({
                        title: message.title.normal,
                        msg: message.grid_select,
                        timeout: message.timeout,
                        showType: message.showType
                    });
                } else {
                    $.messager.confirm(message.title.askTitle, message.dele_comfirm, function(r) {
                        if (r) {
                            var idValue = $("#form input[name='id']").val();
                            $.post(ctx + "delFinanceChargeProduct.do", {
                                id: idValue
                            }, function(result) {
                                if (result.success) {
                                    $.messager.show({
                                        title: message.title.normal,
                                        msg: message.dele_success,
                                        timeout: message.timeout,
                                        showType: message.showType
                                    });
                                    grid.datagrid('reload');
                                } else {
                                    $.messager.show({
                                        title: message.title.normal,
                                        msg: message.dele_fail,
                                        timeout: message.timeout,
                                        showType: message.showType
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
                            $.post(ctx + "updateFinanceChargeProduct.do", fw.serializeObject($('form')), function(result) {
                                if (result.success) {
                                    $.messager.show({
                                        title: message.title.normal,
                                        msg: message.update_success,
                                        timeout: message.timeout,
                                        showType: message.showType
                                    });
                                    grid.datagrid("reload");
                                    //重新获取form信息内容
                                    $("#form").form("load", ctx + "getFinanceChargeProductById.do?id=" + idValue);
                                }
                            }, 'json');
                        } else {
                            if (idValue.trim().length == 0) {
                                $.messager.show({
                                    title: message.title.normal,
                                    msg: message.add_button_click,
                                    timeout: message.timeout,
                                    showType: message.showType
                                });
                            } else {
                                //添加
                                $.post(ctx + "addFinanceChargeProduct.do", fw.serializeObject($('form')), function(result) {
                                    if (result.success) {
                                        $.messager.show({
                                            title: message.title.normal,
                                            msg: message.add_success,
                                            timeout: message.timeout,
                                            showType: message.showType
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
                    $("#form").form("load", ctx + "getFinanceChargeProductById.do?id=" + idValue);
                } else {
                    $("#form").form("reset");
                }
            })
            $(".uploader").on('click', uploaderFun);
            function EditRecord(row) {
                //先对所有uploader的class对象进行遍历
                $(".uploader")
                    .each(function() {
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
                                .done(function(result) {
                                    result = JSON.parse(result);
                                    if (result.length > 0) {
                                        //对返回结果进行遍历，重组option选项，并将结果放置到相应的selectOption对象中去
                                        $(result)
                                            .each(function() {
                                                options += "<option>" + this.clientName + "</option>";
                                            })
                                        selectObj.html(options);
                                        selectObj.prepend("<option>选择文件(选中了 " + result.length + " 个)</option>");
                                    }
                                })
                        } else {
                            selectObj.html("<option>选择文件</option>");
                        }
                    });
            }

            function uploaderFun() {
                var btn = $(this);
                var ids = "";
                if ($(this).next().val().trim().length != 0) {
                    ids = "?ids=" + $(this).next().val();

                }
                console.log(ids);
                if (ids.length > 0) {
                    url = '../../uploader.jsp' + ids + '&moduleId=finance&funcId=product';
                } else {
                    url = '../../uploader.jsp?moduleId=finance&funcId=product';
                }
                console.log(url);
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
                        text: '清除文件',
                        handler: function() {
                            dialog.find('iframe').get(0).contentWindow.clearSelectedFiles(dialog, btn);
                        }
                    }, {
                        text: '关闭窗口',
                        handler: function() {
                            dialog.find('iframe').get(0).contentWindow.closeDialog(dialog);
                        }
                    }]
                });
            }
        });
        </script>
    </body>
</html>