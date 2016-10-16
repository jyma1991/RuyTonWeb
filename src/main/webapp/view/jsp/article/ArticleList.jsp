<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>信息管理</title>
    </head>

    <body>
        <jsp:include page="../../ueditorlib.jsp"></jsp:include>
        <div id="layout" class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a>
                            </td>
                            <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a>
                            </td>
                            <td><a href="#" class="easyui-menubutton" data-options="menu:'#mm1',iconCls:'ext-icon-key',plain:false">批量发布</a>
                                <div id="mm1">
                                    <div data-options="iconCls:'icon-redo'" onclick="BatFun(1);">批量发布</div>
                                    <div data-options="iconCls:'icon-undo'" onclick="BatFun(-1);">取消发布</div>
                                </div> <a href="#" class="easyui-menubutton" data-options="menu:'#mm2',iconCls:'ext-icon-ruby_get',plain:false">批量置顶</a>
                                <div id="mm2">
                                    <div data-options="iconCls:'icon-redo'" onclick="BatFun(2);">批量置顶</div>
                                    <div data-options="iconCls:'icon-undo'" onclick="BatFun(-2);">取消置顶</div>
                                </div> <a href="#" class="easyui-menubutton" data-options="menu:'#mm3',iconCls:'ext-icon-thumb_up',plain:false">批量推荐</a>
                                <div id="mm3">
                                    <div data-options="iconCls:'icon-redo'" onclick="BatFun(4);">批量推荐</div>
                                    <div data-options="iconCls:'icon-undo'" onclick="BatFun(-4);">取消推荐</div>
                                </div> <a href="#" class="easyui-menubutton" data-options="menu:'#mm4',iconCls:'ext-icon-sport_8ball',plain:false">批量滚动</a>
                                <div id="mm4">
                                    <div data-options="iconCls:'icon-redo'" onclick="BatFun(8);">批量滚动</div>
                                    <div data-options="iconCls:'icon-undo'" onclick="BatFun(-8);">取消滚动</div>
                                </div><a href="#" class="easyui-menubutton" data-options="menu:'#mm5',iconCls:'ext-icon-rosette',plain:false">批量热点</a>
                                <div id="mm5">
                                    <div data-options="iconCls:'icon-redo'" onclick="BatFun(16);">批量热点</div>
                                    <div data-options="iconCls:'icon-undo'" onclick="BatFun(-16);">取消热点</div>
                                </div><a href="#" class="easyui-menubutton" data-options="menu:'#mm6',iconCls:'ext-icon-comment',plain:false">批量评论</a>
                                <div id="mm6">
                                    <div data-options="iconCls:'icon-redo'" onclick="BatFun(32);">批量评论</div>
                                    <div data-options="iconCls:'icon-undo'" onclick="BatFun(-32);">取消评论</div>
                                </div>
                            </td>
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a>
                                <a id="ret" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
                            </td>
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
                                <input name="createdTimeStartSch" class="easyui-datebox" style="width: 100px" />-
                                <input name="createdTimeEndSch" class="easyui-datebox" style="width: 100px" />
                            </td>
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',fw.serializeObject($('#searchForm')));">过滤</a>  <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a>
                            </td>
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
                    <input type="hidden" name="extFields" id="extFields">
                    <input type="hidden" name="articleProperty" id="articleProperty">
                    <div id="tabs" class="easyui-tabs" style="width: 100%">
                        <div title="基本信息">
                            <fieldset>
                                <legend>基本信息--<span id="operateMode_title">添加模式</span> 
                                </legend>

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
                                                <input id="source"  name="source" class="textbox" style="height: 22px;" />
                                                <a href="#" class="easyui-linkbutton"  id= "btnSource"  data-options="iconCls:'icon-add'">选择来源</a>
                                            </td>
                                            <th>资源文件</th>
                                            <td>
                                                <select class="selectFiles">
                                                    <option>选择文件</option>
                                                </select> <a href="#" class="easyui-linkbutton uploader" data-options="iconCls:'icon-add'">选择文件</a>
                                                <input name="picId" class="selectFilesValue" type="hidden" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>关键词</th>
                                            <td colspan="3">
                                                <input name="keyWords" class="textbox" style="height: 22px; width: 300px;">
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
                                            <th>信息排序</th>
                                            <td>
                                                <input name="pos" class="easyui-numberspinner" value="0" style="width: 50px;" />
                                            </td>
                                            <th>点击次数</th>
                                            <td>
                                                <input name="hits" class="easyui-numberspinner" value="0" style="width: 50px;" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>信息属性</th>
                                            <td colspan="3">
                                                <input name="isPublic" type="checkbox" value="1" />是否发布
                                                <input name="isTop" type="checkbox" value="1" />是否置顶
                                                <input name="isCommend" type="checkbox" value="1" />是否推荐
                                                <input name="isScroll" type="checkbox" value="1" />是否滚动
                                                <input name="isHot" type="checkbox" value="1" />是否热点
                                                <input name="isComment" type="checkbox" value="1" />是否评论</td>
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
                        <div title="扩展字段表单" id="extTab">
                            <fieldset>
                                <legend>扩展字段信息</legend>
                                <table class="table" width="100%">
                                    <tbody id="extForm"></tbody>
                                </table>
                            </fieldset>
                        </div>

                    </div>

                    <div style="text-align: center; padding: 10px;">
                        <a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a>  <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
                    </div>
                </form>
            </div>
        </div>
        <script type="text/javascript">
        var sysClassTypeId = <%=request.getParameter("sysClassTypeId")%>;
          var scId = <%= request.getParameter("scId") %> ;

            /******************************************************************************
             *  批量操作：1.发布  2.置顶 4.推荐 8.滚动 16.热点 32.评论
             *  1.发布  2.置顶 4.推荐 8.滚动 16.热点 32.评论
             *  负值代表取消
             *******************************************************************************/
            /*--------------------------------Begin---------------------------------------*/
            var BatFun = function (v) {
                    var url = ctx + "updateArticleBaseByBatchIds.do";
                    var rows = $('#grid')
                        .datagrid('getChecked');

                    //判断是否选择行
                    if (rows.length == 0) {
                        $.messager.show({
                            title: '提示',
                            msg: '请选择要更新记录',
                            timeout: 2000,
                            showType: 'slide'
                        });
                        return;
                    }
                    /* 组织数据 */
                    var ids = [];
                    var aps = [];
                    if (v > 0) {
                        for (var i = 0; i < rows.length; i++) {
                            rows[i].articleProperty = rows[i].articleProperty | v;
                            ids.push(rows[i].id);
                            aps.push(rows[i].articleProperty);
                        }
                    } else {
                        for (var i = 0; i < rows.length; i++) {
                            rows[i].articleProperty = rows[i].articleProperty & (~Math.abs(v));
                            ids.push(rows[i].id);
                            aps.push(rows[i].articleProperty);
                        }
                    }
                    /*更新数据*/
                    $.post(url, {
                            ids: ids.join(','),
                            aps: aps.join(',')
                        },
                        function () {
                            grid.datagrid('reload');
                        }, 'json');
                }
                /*--------------------------------End---------------------------------------*/

            /******************************************************************************
             * 扩展字段数据编辑
             ******************************************************************************/
            /*--------------------------------Begin---------------------------------------*/
             //获取扩展字段的字段名清单，为了给fw.serializeObject进行参数传递
            function getExtFiledsList(arrFields) {
                var extFields = new Array();
                for (var i = 0; i < arrFields.length; i++) {
                    arrSingleField = arrFields[i].split(':');
                    eName = arrSingleField[0];
                    extFields.push(eName);
                }
                return extFields.toString();
            }

             //动态表单的html内容生成器
            function getHtml(arrFields) {
                    html = "";
                    for (var i = 0; i < arrFields.length; i++) {
                        //单个字段
                        arrSingleField = arrFields[i].split(':');
                        //英文字段名称
                        eName = arrSingleField[0];
                        //中文字段说明
                        cName = arrSingleField[1];
                        //必选验证
                        fRequire = arrSingleField[2];
                        //验证类型
                        fCheckType = arrSingleField[3];
                        arrCheckType = new Array('', 'integer', 'email', 'url', 'phone', 'mobile', 'phoneOrMobile', 'zip', 'currency', 'english', 'chinese', 'qq');
                        fCheckString = arrCheckType[fCheckType];
                        //字段类型，数字型
                        fType = parseInt(arrSingleField[4].split('+')[0]);
                        //字段的值
                        fValue = arrSingleField[4].split('+')[1];

                        //验证字符串组成部分
                        if (fRequire == 1) {
                            fRequireStrings = "required:true";
                        } else {
                            fRequireStrings = "required:false";
                        }

                        fCheckTypeStrings = "";
                        if (fCheckType != 0) {
                            fCheckTypeStrings = "validType:'" + fCheckString + "'";
                        }

                        if (fCheckTypeStrings.trim()
                            .length == 0) {
                            checkString = 'class="easyui-validatebox" data-options="' + fRequireStrings + '"';
                        } else {
                            checkString = 'class="easyui-validatebox" data-options="' + fRequireStrings + "," + fCheckTypeStrings + '"';
                        }

                        //字段窗体对象字符串组成部分
                        html += "<tr>";
                        switch (fType) {
                        case 1: //文本框
                            html += fw.formatString("<th>{1}</th><td><input class='textbox' style='height:22px;' type='text' name='{0}' {2}></td>", eName, cName, checkString);
                            break;
                        case 2: //编辑框
                            html += fw.formatString("<th>{1}</th><td><textarea class='textareabox' style='width:98%;margin-bottom:3px;' name='{0}' {2}></textarea></td>", eName, cName, checkString);
                            break;
                        case 3: //下拉框
                            optionHtml = "<option value=''>请选择</option>";
                            $.each(fValue.split('|'), function () {
                                optionHtml += fw.formatString("<option>{0}</option>", this.trim());
                            });
                            html += fw.formatString("<th>{1}</th><td><select class='selectValue' name='{0}' {3}>{2}</select></td>", eName, cName, optionHtml, checkString);
                            break;
                        case 4: //复选框
                            checkboxHtml = "";
                            $.each(fValue.split('|'), function () {
                                checkboxHtml += fw.formatString("<input class='extCheckbox' type='checkbox' name='{0}' value='" + this.trim() + "'> {1}", eName, this.trim());
                            });
                            html += fw.formatString("<th>{0}</th><td>{1}</td>", cName, checkboxHtml);
                            break;
                        case 5: //选择文件
                            html += fw.formatString("<th>{1}</th><td><select class='selectFiles'><option>选择文件</option></select> <a href='#' class='easyui-linkbutton uploader' data-options=\"iconCls:'icon-add'\">选择文件</a> <input name='{0}' class='selectFilesValue' type='hidden' {2}/></td>", eName, cName, checkString);
                            break;
                        case 6: //选择日期
                            html += fw.formatString("<th>{1}</th><td><input class='easyui-datebox' name='{0}' {2}></td>", eName, cName, checkString);
                            break;
                        case 7: //富文本框
                            html += fw.formatString("<th>{3}</th><td><textarea id='{0}' class='textareabox' style='width:98%;margin-bottom: 3px;' name='{1}' {4}></textarea><br/><input type='button' mode='0' class='editor' onclick=\"EditorRender('{2}',$(this))\" value='切换至编辑器'></td>", eName, eName, eName, cName, checkString);
                            break;
                        default:

                        }
                        html += "</tr>";

                    }
                    return html;
                }
                /*--------------------------------End---------------------------------------*/

            /******************************************************************************
             * 二进制属性字段拆分组合
             ******************************************************************************/
            /*--------------------------------Begin---------------------------------------*/
             //二进制字段编辑前拆分的拆分
            function setRowBeforeEdit(row) {
                row.isPublic = 0;
                row.isTop = 0;
                row.isCommend = 0;
                row.isScroll = 0;
                row.isHot = 0;
                row.isComment = 0;

                if (1 == (row.articleProperty & 1)) //发布
                    row.isPublic = 1;
                if (2 == (row.articleProperty & 2)) //置顶
                    row.isTop = 1;
                if (4 == (row.articleProperty & 4)) //推荐
                    row.isCommend = 1;
                if (8 == (row.articleProperty & 8)) //滚动
                    row.isScroll = 1;
                if (16 == (row.articleProperty & 16)) //热点
                    row.isHot = 1;
                if (32 == (row.articleProperty & 32)) //评论
                    row.isComment = 1;
            }

             //编辑后组合
            function setRowAfterEdit(frmdata) {
                frmdata.articleProperty = 0;
                if (1 == frmdata.isPublic) //发布
                    frmdata.articleProperty += 1;
                if (1 == frmdata.isTop) //置顶
                    frmdata.articleProperty += 2;
                if (1 == frmdata.isCommend) //推荐
                    frmdata.articleProperty += 4;
                if (1 == frmdata.isScroll) //滚动
                    frmdata.articleProperty += 8;
                if (1 == frmdata.isHot) //热点
                    frmdata.articleProperty += 16;
                if (1 == frmdata.isComment) //评论
                    frmdata.articleProperty += 32;
            }

             //文章管理编辑操作
            function EditRecord(value) {
                //清除extForm中的form表单值
                $("#extForm .textbox,.textareabox,.selectValue,.selectFiles,.selectFilesValue").val('');
                //日期选择器清零
                $('#extForm .easyui-datebox').datebox('setValue', '');
                //复选框清零
                $("#extForm :checkbox").prop("checked", false);

                //遍历所有编辑器，将编辑器模式切换到textarea模式，否则将会出问题
                $('.editor').each(function () {
                        if ($(this).attr('mode') == 1) {
                            $(this).click();
                        }
                    });

                //通过value值选中记录
                $('#grid').datagrid('selectRecord', value);
                //获取记录的row对象
                var row = $('#grid').datagrid('getSelected');

                //加载基础字段
                $("#form").form("load", row);
                //加载扩展字段
                $("#form").form("load", ctx + 'findArticleExtendByAbId.do?abId=' + row.id);
                $("#operateMode_title").html("修改模式");


                //更新操作时，对扩展字段的checkbox对象进行获值操作
                var arrCheckboxObj = [];
                $(".extCheckbox").each(function () {
                        arrCheckboxObj.push($(this).attr('name'));
                    });

                var checkboxObj = $.unique(arrCheckboxObj);
                //对唯一的字段名称数组进行遍历
                $(checkboxObj).each(function (index, element) {
                        $.ajax({
                                type: "POST",
                                url: ctx + "findABExtCheckboxByabIdAndCellname.do",
                                data: {
                                    abId: row.id,
                                    cellname: element
                                }
                            }).done(function (result) {
                                //如果扩展字段中能够找到相应的元素，则进行checkbox打钩选择操作
                                if (typeof (result.cellvalue) != 'undefined') {

                                    $('input[class="extCheckbox"][name="' + element + '"]').each(function () {
                                            if ($.inArray($(this).val(), result.cellvalue.split(',')) != -1) {
                                                $(this).prop("checked", true);
                                            }
                                        });

                                }
                            })

                    });

                //更新操作时，处理所有文件选择
                //先对所有uploader的class对象进行遍历
                $(".uploader").each(function () {
                        var selectObj = $(this).prev('.selectFiles');
                        var options = "";
                        //如果该文件选择器有值，则进行异步数据获取
                        if ($(this).next('.selectFilesValue').val().trim().length != 0) {
                            var ids = $(this).next('.selectFilesValue').val();
                            $.ajax({
                                    type: "POST",
                                    url: ctx + "findSysUploadByIds.do",
                                    data: {ids: ids}
                                }).done(function (result) {
                                    result = JSON.parse(result);
                                    if (result.length > 0) {
                                        //对返回结果进行遍历，重组option选项，并将结果放置到相应的selectOption对象中去
                                        $(result).each(function () {
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

                $("#extFields").val($("#extFieldsTmpValue").val());
                $("#className").val($("#classNameTmpValue").val());

            }

            $(function () {
                $("#layout").layout("collapse", "east");

                var gird;
                grid = $('#grid').datagrid({
                        fit: true,
                        border: false,
                        collapsible: false,
                        rownumbers: true,
                        pagination: true,
                        singleSelect : true,
        				checkOnSelect:false,
        				selectOnCheck:false,
                        url: ctx + 'listArticleBase.do?sysClassIdSch=' + scId,
                        frozenColumns: [
                        [{
                                field: 'id',
                                title: '操作',
                                width: 80,
                                formatter: EditRecords
                        }, {
                                field: 'ck',
                                checkbox: true
                        }, {
                                field: 'subject',
                                title: '信息标题',
                                width: 300
                        }]
                    ],
                        columns: [
                        [{
                                field: 'author',
                                title: '作者',
                                width: 80,
                                rowspan: 2
                        }, {
                                field: 'source',
                                title: '来源',
                                width: 80,
                                rowspan: 2
                        }, {
                                field: 'keyWords',
                                title: '关键字',
                                width: 120,
                                rowspan: 2
                        }, {
                                field: 'hits',
                                title: '点击数',
                                width: 50,
                                sortable: true,
                                align: 'center',
                                rowspan: 2
                        }, {
                                field: 'createdTime',
                                title: '添加时间',
                                width: 120,
                                sortable:'true',
                                formatter:getDate,
                                rowspan: 2
                        }, {
                                title: '属性', //1 是否发布 2 置顶 4 是否被推荐 8 是否滚动 16 热点 32 是否为评论
                                align: 'center',
                                colspan: 6
                        }],
                        [{
                                field: 'isPublic',
                                title: '发布',
                                width: 40,
                                align: 'center',
                                sortable: true,
                                formatter: function (value, row) {
                                    var str = '';
                                    if (1 == (row.articleProperty & 1)) {
                                        str = '是';
                                    } else {
                                        str = '否';
                                    }
                                    return str;
                                }
                        }, {
                                field: 'isTop',
                                title: '置顶',
                                width: 40,
                                align: 'center',
                                sortable: true,
                                formatter: function (value, row) {
                                    var str = '';
                                    if (2 == (row.articleProperty & 2)) {
                                        str = '是';
                                    } else {
                                        str = '否';
                                    }
                                    return str;
                                }
                        }, {
                                field: 'isCommend',
                                title: '推荐',
                                width: 40,
                                align: 'center',
                                sortable: true,
                                formatter: function (value, row) {
                                    var str = '';
                                    if (4 == (row.articleProperty & 4)) {
                                        str = '是';
                                    } else {
                                        str = '否';
                                    }
                                    return str;
                                }
                        }, {
                                field: 'isScroll',
                                title: '滚动',
                                width: 40,
                                align: 'center',
                                sortable: true,
                                formatter: function (value, row) {
                                    var str = '';
                                    if (8 == (row.articleProperty & 8)) {
                                        str = '是';
                                    } else {
                                        str = '否';
                                    }
                                    return str;
                                }
                        }, {
                                field: 'isHot',
                                title: '热点',
                                width: 40,
                                align: 'center',
                                sortable: true,
                                formatter: function (value, row) {
                                    var str = '';
                                    if (16 == (row.articleProperty & 16)) {
                                        str = '是';
                                    } else {
                                        str = '否';
                                    }
                                    return str;
                                }
                        }, {
                                field: 'isComment',
                                title: '是否为评论',
                                width: 70,
                                align: 'center',
                                sortable: true,
                                formatter: function (value, row) {
                                    var str = '';
                                    if (32 == (row.articleProperty & 32)) {
                                        str = '是';
                                    } else {
                                        str = '否';
                                    }
                                    return str;
                                }
                        }]
                    ],
                        onLoadSuccess: function (data) {
                                $('.iconImg').attr('src', fw.pixel_0);
                            },
                            onClickRow: function (index, row) {
                                setRowBeforeEdit(row); //设置属性
                                $("#form").form("load", row);
                                $("#operateMode_title").html("修改模式");
                        		EditRecord(row);
                            }
                    });

                //文章管理操作按钮
                function EditRecords(value, row, index) {
                    var str = "";
                    str += "<a href='javascript:EditRecord(" + value + ")'>修改</a> ";
                    str += "<a href='ArticleComment.jsp?sysClassId=" + scId + "&articleBaseId=" + value + "'>评论</a>";
                    return str;
                }

                /*
                // 获取当前的信息分类
                */
                $.ajax({
                        type: "POST",
                        url: ctx + 'getSysClassById.do',
                        data: {
                            id: scId
                        }
                    }).done(function (result) {
                        var result = JSON.parse(result);
                        $("#className").val(result.className);
                        //className的临时变量，用以复制className的值，否则form在reset的时候className会被清除
                        $("#classNameTmpValue").val(result.className);
                        //扩展字段操作部分
                        //如果formobjectlist未定义或为空，则扩展字段选项卡禁用
                        if (typeof (result.formObjectList) == 'undefined' || result.formObjectList.trim().length == 0) {
                            $('#tabs').tabs('disableTab', 1);
                        } else {
                            $('#extForm').html(getHtml(result.formObjectList.split(',')));
                            //重新渲染对象，以便验证操作生效
                            $.parser.parse($('#extForm'));

                            //扩展字段清单
                            $('#extFields').val(getExtFiledsList(result.formObjectList.split(',')));

                            //extFields的临时变量，用以复制extFields的值，否则form在reset的时候extFields会被清除
                            $('#extFieldsTmpValue').val(getExtFiledsList(result.formObjectList.split(',')));
                        }

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
                        url = '../../uploader.jsp' + ids + '&moduleId=article&funcId=article';
                    } else {
                        url = '../../uploader.jsp?moduleId=article&funcId=article';
                    }

                    var dialog = fw.modalDialog({
                        title: '选择文件',
                        url: url,
                        width: 700,
                        height: 500,
                        buttons: [{
                            text: '确认选择',
                            handler: function () {
                                dialog.find('iframe').get(0).contentWindow.submitForm(dialog, btn);
                            }
                        }, {
                            text: '清除文件',
                            handler: function () {
                                dialog.find('iframe').get(0).contentWindow.clearSelectedFiles(dialog, btn);
                            }
                        }, {
                            text: '关闭窗口',
                            handler: function () {
                                dialog.find('iframe').get(0).contentWindow.closeDialog(dialog);
                            }
                        }]
                    });
                }

                $("#btnSource").click(function() {
                    var btn = $(this);
                    url = '../../uploader.jsp?source=1&moduleId=article&funcId=article';

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
                $("#btn_add").click(function () {
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
                $("#btn_dele").click(function () {
                        //var rows = $('#grid').datagrid('getSelections');
                        var rows_ck= $('#grid').datagrid('getChecked'); 
                        if (rows_ck.length <= 0) {
                            $.messager.show({
                                title: message.title.normal,
                                msg: message.grid_select,
                                timeout: message.timeout,
                                showType: message.showType
                            });
                        } else {
                            $.messager.confirm(message.title.askTitle, message.dele_comfirm, function (r) {
                                if (r) {
                                    //var idValue = $("#form input[name='id']").val();
                                    for (var i = 0, len = rows_ck.length; i < len; i++) {
                                    	var idvalue=rows_ck[i].id;
                                    	$.post(ctx + "delArticleBase.do", {
                                            id: idvalue
                                        }, function (result) {
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
                                    }
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
    			/* 	if ($("#subject").val().trim().length != 0 && $("#content").val().trim().length != 0) {
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
    				} */
                	
                        var idValue = $("#form input[name='id']").val();
                        if ($('#form').form('validate')) {
                            frmdata = fw.serializeObject($('form'));
                            setRowAfterEdit(frmdata);
                            if (idValue > 0) {
                                //修改
                                $.post(ctx + "updateArticleBase.do", frmdata, function(result) {
                                    if (result.success) {
                                        $.messager.show({
                                            title: message.title.normal,
                                            msg: message.update_success,
                                            timeout: message.timeout,
                                            showType: message.showType
                                        });

                                        grid.datagrid("reload");

                                        //重新获取form信息内容
                                        $("#form").form("load", ctx + "getArticleBaseById.do?id=" + idValue, function (result) {
                                                $("#form").form("load", result);

                                                grid.datagrid('selectRecord', idValue);
                                                var rowIndex = grid.datagrid('getRowIndex', grid.datagrid('getSelected'))
                                                grid.datagrid('updateRow', {
                                                    index: rowIndex,
                                                    row: result
                                                });

                                            }, 'json');
                                    }
                                }, 'json');
                            } else {
                                //添加
                                $.post(ctx + "addArticleBase.do", frmdata, function (result) {
                                    if (result.success) {
                                        $.messager.show({
                                            title: message.title.normal,
                                            msg: message.add_success,
                                            timeout: message.timeout,
                                            showType: message.showType
                                        });
                                        grid.datagrid("reload");
                                        $("#form").form("reset");
                                        $(".selectFiles").html("<option>选择文件</option>");
                                        $(".selectFilesValue").val('');
                                        $("#extFields").val($("#extFieldsTmpValue").val());
                                        $("#className").val($("#classNameTmpValue").val());
                                    }
                                }, 'json');

                            }
                        }
                    })
                    //重置操作
                $("#operateMode_reset")
                    .click(function () {
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
            
            $("#ret").click(function() {
                  window.location.href = "ArticleClass.jsp?sysClassTypeId="+sysClassTypeId;
            });
        </script>
    </body>

    </html>