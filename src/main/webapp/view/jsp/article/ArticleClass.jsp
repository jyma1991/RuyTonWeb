<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>信息管理</title>

    </head>

    <body>
        <div class="easyui-layout" data-options="fit:true">
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
                                    <th>类别编号</th>
                                    <td>
                                        <input id="id" name="id" readonly="readonly" class="textbox" style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>类别名称</th>
                                    <td>
                                        <input name="className" readonly="readonly" class="textbox" style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>类别描述</th>
                                    <td>
                                        <input name="description" readonly="readonly" class="textbox" style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>操作</th>
                                    <td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'ext-icon-accept',disabled:true">进入操作</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <script type="text/javascript">
        var sysClassTypeId = <%=request.getParameter("sysClassTypeId")%>;
            $(function() {
                var gird;
                grid = $('#grid').treegrid({
                    fit: true,
                    border: false,
                    collapsible: false,
                    idField: 'id',
                    treeField: 'className',
                    parentField: 'parentId',
                    rownumbers: true,
                    singleSelect: true,
                    rownumbers: true,
                    url: ctx + 'listAllSysClass.do?sysClassTypeIdSch='+sysClassTypeId,
                    columns: [
                        [{
                            field: 'id',
                            title: 'ID',
                            align: 'center',
                            width: '40',
                            sortable:'true'
                        }, {
                            field: 'className',
                            title: '类别名',
                            width: '120'
                        }, {
                            field: 'description',
                            title: '分类描述',
                            width: '200'
                        },{
        					field : 'articleCount',
        					title : '篇目',
        					sortable:'true',
        					width : '40'
        				},{
                            field: 'createdTime',
                            title: '创建时间',
                            sortable:'true',
                            formatter:getDate
                        }]
                    ],
                    onLoadSuccess: function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    },
                    onClickRow: function(row) {
                        $("#form").form("load", row);
                        $("#operateMode_submit").linkbutton('enable');
                    }
                });

                $("#operateMode_submit").click(function() {
                    if ($("#id").val().trim().length > 0) {
                        window.location.href = "ArticleList.jsp?scId=" + $("#id").val()+"&sysClassTypeId="+sysClassTypeId;
                    } else {
                        $.messager.show({
                            title: message.title.normal,
                            msg: message.no_record,
                            timeout: message.timeout,
                            showType: message.showType
                        });
                    }
                });

            });
        </script>
    </body>

    </html>