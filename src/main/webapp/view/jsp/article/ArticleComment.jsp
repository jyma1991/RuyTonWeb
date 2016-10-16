<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>信息管理</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>

    <body>
        <jsp:include page="/view/ueditorlib.jsp"></jsp:include>

        <!-- Layout Begin -->
        <div id="layout" class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height: 70px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td>评论标题</td>
                            <td>
                                <input name="subjectSch" class="textbox" style="width: 120px; height: 22px;" />
                            </td>
                            <td>内容</td>
                            <td>
                                <input name="contentSch" class="textbox" style="width: 120px; height: 22px;" />
                            </td>
                            <td>创建时间</td>
                            <td>
                                <input name="createdTimeStartSch" class="easyui-datebox" style="width: 100px" /> -
                                <input name="createdTimeEndSch" class="easyui-datebox" style="width: 100px" />
                            </td>
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.treegrid('load',fw.serializeObject($('#searchForm')));">过滤</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.treegrid('load',{});">重置过滤</a></td>
                        </tr>
                    </table>
                </form>

                <table>
                    <tr>
                        <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
                        <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
                        <td><a onclick="redoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-resultset_next'">展开</a></td>
                        <td><a onclick="undoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-resultset_previous'">折叠</a></td>
                        <td><a onclick="$('#btn_add').click();grid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
                        <td><a href="ArticleList.jsp?scId=<%=request.getParameter("sysClassId")%>" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>
                    </tr>
                </table>
            </div>

            <div data-options="region:'center',border:false">
                <table id="grid"></table>
            </div>

            <div data-options="region:'east',split:true" title="信息详情" style="width: 45%; padding: 5px;">
                <form id="form" name="form" method="post" class="form">
                    <input type="hidden" name="articleBaseId" value="<%=request.getParameter(" articleBaseId ")%>">
                    <input type="hidden" name="sysClassId" value="<%=request.getParameter(" sysClassId ")%>">
                    <input type="hidden" id="parentId" name="parentId" value="">
                    <input type="hidden" name="state" id="state">
                    <fieldset>
                        <legend>
                           	 基本信息--<span id="operateMode_title">添加模式</span>
                        </legend>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th>编号</th>
                                    <td>
                                        <input name="id" class="textbox" value="0" style="height: 22px;" readonly="readonly" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>评论标题</th>
                                    <td>
                                        <input name="subject" class="easyui-validatebox textbox" data-options="required:true" style="width: 360px; height: 22px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>评论内容</th>
                                    <td>
                                        <textarea id="content" name="content" style="width: 360px; height: 150px;" class="easyui-validatebox textbox" data-options="required:true"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th>是否发布</th>
                                    <td>
                                        <input name="isShow" type="checkbox" value="1" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>信息排序</th>
                                    <td>
                                        <input name="sortId" class="easyui-numberspinner" value="0" style="width: 50px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>IP地址</th>
                                    <td>
                                        <input name="createdIP" readonly="readonly" class="textbox" style="width: 180px;height:22px;" value="<%=request.getRemoteAddr()%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>添加时间</th>
                                    <td>
                                        <input name="createdTime" data-options="disabled:true" class="easyui-datetimebox" style="width: 180px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>修改时间</th>
                                    <td>
                                        <input name="editedTime" data-options="disabled:true" class="easyui-datetimebox" style="width: 180px;" />
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
            function setRowBeforeEdit(row) {
                row.isShow = 0;
                if (1 == (row.commentProperty & 1)) //发布
                    row.isShow = 1;
            }

            function setRowAfterEdit(frmdata) {
                frmdata.commentProperty = 0;
                if (1 == frmdata.isShow) //公开
                    frmdata.commentProperty += 1;
            }

            $("#btn_add").click(function() {
                $("#operateMode_title").html("添加模式");
                $('#grid').treegrid("unselectAll");
                $("#id").attr("value", 0);
                $("#parentId").attr("value", 0);
                $("#form").form("reset");
            })

            $("#btn_dele").click(function() {
                var row = $('#grid').treegrid('getSelected');
                if (row.id <= 0) {
                    $.messager.show({
                        title: message.title.normal,
                        msg: message.grid_select,
                        timeout: message.timeout,
                        showType: message.showType
                    });
                } else {
                    $.messager.confirm(message.title.askTitle, message.dele_comfirm, function(r) {
                        if (r) {
                            $.post(ctx + "delArticleComment.do", {
                                id: row.id
                            }, function(result) {
                                if (result.success) {
                                    $.messager.show({
                                        title: message.title.normal,
                                        msg: message.dele_success,
                                        timeout: message.timeout,
                                        showType: message.showType
                                    });
                                    grid.treegrid('reload');
                                } else {
                                    $.messager.show({
                                        title: message.title.normal,
                                        msg: message.dele_fail,
                                        timeout: message.timeout,
                                        showType: message.showType
                                    });
                                }
                            }, 'json');
                        }
                    });
                }
            })

            function EditRecord(value) {
                $("#form").form("reset");
                //通过value值选中记录
                $('#grid').treegrid('select', value);
                //获取记录的row对象
                var row = $('#grid').treegrid('getSelected');
                setRowBeforeEdit(row);
                $("#form").form("load", row);
                $("#state").val(row.state);
                $("#operateMode_title").html("修改模式");
            }

            function ReplyRecord(value) {
                $("#form").form("reset");
                //通过value值选中记录
                $('#grid').treegrid('select', value);
                //获取记录的row对象
                var row = $('#grid').treegrid('getSelected');
                $("#parentId").val(row.id);
                $("#operateMode_title").html("回复模式");
            }

            $(function() {
                //列表操作
                var gird;
                grid = $('#grid').treegrid({
                    fit: true,
                    border: false,
                    rownumbers: true,
                    animate: false,
                    collapsible: false,
                    fitColumns: true,
                    pagination: true,
                    url: ctx + 'listArticleCommentTree.do?articleBaseIdSch=' + <%=request.getParameter("articleBaseId")%>,
                    idField: 'id',
                    treeField: 'subject',
                    columns: [
                        [{
                            field: 'id',
                            title: '操作',
                            width: 60,
                            formatter: EditRecords
                        }, {
                            field: 'subject',
                            title: '评论标题',
                            width: '250',
                            sortable: true,
                        }, {
                            field: 'createdIP',
                            title: 'IP地址',
                            width: '80'
                        }, {
                            field: 'isShow',
                            title: '发布',
                            width: 50,
                            sortable: true,
                            align: 'center',
                            formatter: function(value, row) {
                                var str = '';
                                if (1 == (row.commentProperty & 1)) {
                                    str = "已发布";
                                } else
                                    str = "未发布";
                                return str;
                            }
                        }, {
                            field: 'createdTime',
                            title: '评论时间',
                            width: 120,
                            align: 'center',
                            sortable:'true',
                            formatter:getDate
                        }]
                    ],
                    toolbar: '#toolbar',
                    onBeforeLoad: function(row, param) {
                        if (!row) { // load top level rows
                            param.parentIdSch = 0; // set id=0, indicate to load new page rows
                        } else {
                            param.parentIdSch = row.id;
                        }
                    },
                    onLoadSuccess: function(row, data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    }
                });

                function EditRecords(value) {
                    var str = "";
                    str += "<a href='javascript:EditRecord(" + value + ")'>修改</a> ";
                    str += "<a href='javascript:ReplyRecord(" + value + ")'>回复</a> ";
                    return str;
                }

                //确认提交操作
                $("#operateMode_submit").click(function() {
                    var idValue = $("#form input[name='id']").val();

                    if ($('#form').form('validate')) {
                        var frmdata = fw.serializeObject($('form'));
                        setRowAfterEdit(frmdata);
                        if (idValue > 0) {
                            //修改
                            $.post(ctx + "updateArticleComment.do", frmdata, function(result) {


                                if (result.success) {
                                    $.messager.show({
                                        title: message.title.normal,
                                        msg: message.update_success,
                                        timeout: message.timeout,
                                        showType: message.showType
                                    });

                                    //重新获取form信息内容并且更新指定行数的表格内容
                                    $.post(ctx + "getArticleCommentById.do?id=" + idValue, function(result) {
                                        result.state = $("#state").val();
                                        $("#form").form("load", result);
                                        grid.treegrid('update', {
                                            id: idValue,
                                            row: result
                                        });
                                    }, 'json');
                                }
                            }, 'json');
                        } else {
                            //添加
                            $.post(ctx + "addArticleComment.do", frmdata, function(result) {
                                if (result.success) {
                                    $.messager.show({
                                        title: message.title.normal,
                                        msg: message.add_success,
                                        timeout: message.timeout,
                                        showType: message.showType
                                    });

                                    grid.treegrid("reload");
                                    $("#form").form("reset");

                                }
                            }, 'json');
                        }

                    }
                })

            })
        </script>
    </body>

    </html>