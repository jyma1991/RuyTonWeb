<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="org.durcframework.core.UserContext"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>信息管理</title>
    </head>
    <body>
        <jsp:include page="/view/ueditorlib.jsp"></jsp:include>
        <div id="layout" class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
                            <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a>
                                <a id="btn_back" href="javascript:history.go(-1);" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>信息标题</td>
                            <td>
                                <input name="subjectSch" class="textbox" style="width: 120px; height: 22px;" />
                            </td>
                            <td>关键字</td>
                            <td>
                                <input name="keywordsSch" class="textbox" style="width: 120px; height: 22px;" />
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
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',fw.serializeObject($('#searchForm')));">过滤</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div data-options="region:'center',border:false">
                <table id="grid">
                </table>
            </div>
            <div data-options="region:'east',split:true" title="信息详情" style="width: 70%; padding: 5px;">
                <input type="hidden" id="extFieldsTmpValue" name="extFieldsTmpValue">
                <input type="hidden" id="classNameTmpValue" name="classNameTmpValue">
                <form id="form" name="form" method="post" class="form">
                    <input type="hidden" name="articleProperty" id="articleProperty">
                    <input type="hidden" id="schoolId" name="schoolId">
                        <div title="基本信息">
                            <fieldset>
                                <legend> 基本信息--<span id="operateMode_title">添加模式</span> </legend>

                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <th>编号</th>
                                            <td>
                                                <input id="id" name="id" readonly="readonly" class="textbox" value="0" style="height: 22px;" />
                                            </td>
                                            <th>所属栏目</th>
                                            <td>
                                                <input id="className" name="className" readonly="readonly" class="textbox" style="height: 22px;">
                                                <input id="sysClassId" name="sysClassId" type="hidden" value="<%=request.getParameter("scId")%>">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>信息标题</th>
                                            <td>
                                                <input id="subject" name="subject" class="textbox easyui-validatebox" data-options="required:true" style="height: 22px;" />
                                            </td>
                                            <th>作者</th>
                                            <td>
                                                <input name="author" class="textbox" style="height: 22px;" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>来源</th>
                                            <td>
                                                <input id= "source" name="source" class="textbox" style="height: 22px;" />
                                                <a href="#" class="easyui-linkbutton"  id= "btnSource"  data-options="iconCls:'icon-add'">选择来源</a>
                                            </td>
                                            <th>图片</th>
                                            <td>
                                                <select class="selectFiles">
                                                    <option>选择文件</option>
                                                </select> <a href="#" class="easyui-linkbutton uploader" data-options="iconCls:'icon-add'">选择文件</a>
                                                <input name="picId" class="selectFilesValue" type="hidden" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>关键词</th>
                                            <td>
                                                <input name="keyWords" class="textbox" style="height: 22px; width: 300px;">
                                            </td>
                                            <th>信息排序</th>
                                            <td>
                                                <input name="pos" class="easyui-numberspinner" value="0" style="width: 50px;" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>简介</th>
                                            <td colspan="3">
                                                <textarea id="brief" name="brief" style="width: 98%; margin-bottom: 3px;"></textarea>
                                                <input type="button" mode="0" class="editor" onclick="EditorRender('brief',$(this))" value="切换至编辑器">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>信息内容</th>
                                            <td colspan="3">
                                                <textarea id="content" name="content" style="width: 98%; margin-bottom: 3px;" class="easyui-validatebox" data-options="required:true"></textarea>
                                                <input type="button" mode="0" class="editor" onclick="EditorRender('content',$(this))" value="切换至编辑器">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>添加时间</th>
                                            <td>
                                                <input name="createdTime" data-options="disabled:true" class="easyui-datetimebox" style="width: 180px;" />
                                            </td>
                                            <th>修改时间</th>
                                            <td>
                                                <input name="editedTime" data-options="disabled:true" class="easyui-datetimebox" style="width: 180px;" />
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>

                            </fieldset>
                        </div>

                    <div style="text-align: center; padding: 10px;">
                        <a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
                    </div>
                </form>
            </div>
        </div>
        <script type="text/javascript">
            var scId = <%=request.getParameter("scId")%>;
            var hideBack = <%=request.getParameter("hideBack")%>;
            var userId = <% out.print(UserContext.getInstance().getUser().getId());%>;
            if(hideBack){
            	$("#btn_back").hide();
            }
			$("#schoolId").val(userId);
			//文章管理编辑操作
            function EditRecord(row) {
                //遍历所有编辑器，将编辑器模式切换到textarea模式，否则将会出问题
                $('.editor').each(function() {
                    if ($(this).attr('mode') == 1) {
                        $(this).click();
                    }
                });
                //加载基础字段
                $("#form").form("load", row);
                //加载扩展字段
                $("#form").form("load", ctx + 'findArticleExtendByAbId.do?abId=' + row.id);
                $("#operateMode_title").html("修改模式");

                //更新操作时，处理所有文件选择
                //先对所有uploader的class对象进行遍历
                $(".uploader").each(function() {
                    var selectObj = $(this).prev('.selectFiles');
                    var options = "";
                    //如果该文件选择器有值，则进行异步数据获取
                    if ($(this).next('.selectFilesValue').val().trim().length != 0) {
                        var ids = $(this).next('.selectFilesValue').val();
                        $.ajax({
                            type: "POST",
                            url: ctx + "findSysUploadByIds.do",
                            data: {
                                ids: ids
                            }
                        }).done(function(result) {
                        	result = JSON.parse(result);
                            if (result.length > 0) {
                                //对返回结果进行遍历，重组option选项，并将结果放置到相应的selectOption对象中去
                                $(result).each(function() {
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
                if ($("#layout").layout("panel", "east").panel('panel')[0]["offsetLeft"] <= 0) {
                    $("#layout").layout("expand", "east");
                }
            }

            $(function() {
                $("#layout").layout("collapse", "east");

                var gird;
                grid = $('#grid').datagrid({
                    fit: true,
                    border: false,
                    collapsible: false,
                    singleSelect : true,
                    rownumbers: true,
                    pagination: true,
                    url: ctx + 'listArticleBase.do?sysClassIdSch=' + scId,
                    frozenColumns: [
                      	[{
                            field: 'subject',
                            title: '信息标题',
                            width: 300
                        }]
                    ],
                    columns: [
                        [{
                            field: 'author',
                            title: '作者',
                            width: 80
                        }, {
                            field: 'source',
                            title: '来源',
                            width: 80
                        }, {
                            field: 'keyWords',
                            title: '关键字',
                            width: 120
                        }, {
                            field: 'hits',
                            title: '点击数',
                            width: 50,
                            sortable: true,
                            align: 'center'
                        }, {
                            field: 'createdTime',
                            title: '添加时间',
                            width: 120,
                            sortable: true,
                            formatter:getDate
                        }]
                    ],
                    onLoadSuccess: function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    },
                    onClickRow: function(index, row) {
                        $("#operateMode_title").html("修改模式");
                        EditRecord(row);
                    }
                });
			
                /*
                // 获取当前的信息分类
                */
                $.ajax({
                    type: "POST",
                    url: ctx + 'getSysClassById.do',
                    data: {
                        id: scId
                    }
                }).done(function(result) {
                    var result = JSON.parse(result);
                    $("#className").val(result.className);
                    //className的临时变量，用以复制className的值，否则form在reset的时候className会被清除
                    $("#classNameTmpValue").val(result.className);
                    //绑定上传按钮功能
                    $(".uploader").on('click', uploaderFun);
                }, 'json');

                //选择文件部分的函数操作，需要将自身对象及ids列表作为参数进行传递
                function uploaderFun() {
                    var btn = $(this);
                    var ids = "";
                    if ($(this).next().val().trim().length != 0) {
                        ids = "?ids=" + $(this).next().val();
                    }

                    if (ids.length > 0) {
                        url = '../../uploader.jsp' + ids + '&moduleId=sc&funcId=ScArticle&userId='+userId;
                    } else {
                        url = '../../uploader.jsp?moduleId=sc&funcId=ScArticle&userId='+userId;
                    }

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
				
                $("#btnSource").click(function() {
                    var btn = $(this);
                    url = '../../uploader.jsp?source=1&moduleId=sc&funcId=ScArticle'+'&userId=' + userId;

                    var dialog = fw.modalDialog({
                        title: '选择文件',
                        url: url,
                        width: 700,
                        height: 500,
                        buttons: [{
                            text: '确认选择',
                            handler: function() {
                                var rows = dialog.find('iframe').get(0).contentWindow.returnFormValue(dialog);
                                console.log(rows);
                                $("#source").val(rows[0].fileFullPath);
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
            });
                
                //添加操作
                $("#btn_add").click(function() {
                    $("#operateMode_title").html("添加模式");
                    $('#grid').datagrid("unselectAll");
                    $("#id").attr("value", 0);
                    $(".selectFiles").html("<option>选择文件</option>");
                    $(".selectFilesValue").val('');
                    $("#form").form("reset");
                    $("#extFields").val($("#extFieldsTmpValue").val());
                    $("#className").val($("#classNameTmpValue").val());
                    var tab = $("#tabs").tabs("select", 0);
                    if ($("#layout").layout("panel", "east").panel('panel')[0]["offsetLeft"] <= 0) {
                        $("#layout").layout("expand", "east");
                    }
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
                                $.post(ctx + "delArticleBase.do", {
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
                	
                	//遍历所有编辑器，将编辑器模式切换到textarea模式，目的是为了必填的编辑器字段验证数据是否有输入
    				$('.editor').each(function() {
    					if ($(this).attr('mode') == 1) {
    						$(this).click();
    					}
    				});

    				//首先判断subject及content必填字段是否为空，如果不为空，则去判断扩展字段中的数据字段是否有验证字段，并且不为空
    				if ($("#subject").val().trim().length != 0 && $("#content").val().trim().length != 0) {
    					$('.easyui-validatebox').each(function() {

    						if ($(this).attr('id') != 'subject' && $(this).attr('id') != 'content') {
    							if ($(this).attr('data-options').toLowerCase().indexOf("required:true") >= 0 && $(this).val().trim().length == 0) {
    								var tab = $("#tabs").tabs("select", 1);
    								//如果扩展字段中有一个需要验证的字段，且为空，则切到tabs的扩展选项卡中，并中断
    								return false;
    							}
    						}

    					});
    				} else {
    					var tab = $("#tabs").tabs("select", 0);
    				}
                	
                        var idValue = $("#form input[name='id']").val();
                        if ($('#form').form('validate')) {
                            if (idValue > 0) {
                                //修改
                                $.post(ctx + "updateArticleBase.do", fw.serializeObject($('form')), function(result) {
                                    if (result.success) {
                                        $.messager.show({
                                            title: message.title.normal,
                                            msg: message.update_success,
                                            timeout: message.timeout,
                                            showType: message.showType
                                        });

                                        grid.datagrid("reload");

                                        //重新获取form信息内容
                                        $("#form").form("load", ctx + "getArticleBaseById.do?id=" + idValue);
                                    }
                                }, 'json');
                            } else {
                                //添加
                                $.post(ctx + "addArticleBase.do",fw.serializeObject($('form')), function(result) {
                                    if (result.success) {
                                        $.messager.show({
                                            title: message.title.normal,
                                            msg: message.add_success,
                                            timeout: message.timeout,
                                            showType: message.showType
                                        });
                                        grid.datagrid("reload");
                                        $(".selectFiles").html("<option>选择文件</option>");
                                        $(".selectFilesValue").val('');
                                        $("#form").form("reset");
                                    }
                                }, 'json');

                            }
                        }
                    })
                    //重置操作
                $("#operateMode_reset").click(function() {
                    var idValue = $("#form input[name='id']").val();
                    if (idValue > 0) {
                        $("#form").form("load", ctx + "getArticleBaseById.do?id=" + idValue);
                    } else {
                        $("#form").form("reset");
                        $(".selectFiles").html("<option>选择文件</option>");
                        $(".selectFilesValue").val('');
                    }
                })
            });
        </script>
    </body>

    </html>