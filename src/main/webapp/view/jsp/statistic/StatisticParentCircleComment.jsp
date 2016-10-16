<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/rms" prefix="rms" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>家长圈评论详细列表</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>

    </head>
	    <style type="text/css">
        .datagrid-cell, .datagrid-cell-group, .datagrid-header-rownumber, .datagrid-cell-rownumber{  
    		o-text-overflow: ellipsis;  
    		text-overflow: ellipsis;  
		} 
        </style>
    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td>
                            	<a id="goback" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
                            </td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>创建时间</td>
                            <td>
                                <input name="createdStartSch" class="easyui-datebox" style="width: 100px" /> -
                                <input name="createdEndSch" class="easyui-datebox" style="width: 100px" />
                            </td>
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="searchForm()">检索</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">清除检索条件</a></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div data-options="region:'center',border:false">
                <table id="grid">
                </table>
            </div>
        </div>
        <script type="text/javascript">
        	var gird;
        	var articleBaseId = <%=request.getParameter("articleBaseId")%>;
            $(function() {
                grid = $('#grid').datagrid({
                    fit: true,
                    border: false,
                    collapsible: false,
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    rownumbers: true,
                    url: ctx + 'listArticleComment.do?articleBaseIdSch='+articleBaseId,
                    columns: [
                             [
							 {
							     field: 'id',
							     title: 'ID',
							     sortable:true
							 }, {
                                 field: 'sendName',
                                 title: '真实姓名',
                                 sortable:true
                             },  {
                                 field: 'content',
                                 title: '评论内容',
                                 sortable:true
                             }, {
                           	     field: 'sortId',
                           	     title: '排序',
                           	     sortable:true
                           	 }, {
                           	     field: 'createdTime',
                           	     title: '添加时间',
	                           	 formatter: function(value, row, index){
			            			return value.substring(0,10);
			            		 },
                           	     sortable:true
                           	 }]
                         ],
                    onLoadSuccess: function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    },
                    onClickRow: function(index, row) {
                    }
                });
                
            });
            
            $("#goback").click(function() {
				window.location.href="StatisticParentCircle.jsp"
			});
			function searchForm(){
				grid.datagrid('load',fw.serializeObject($('#searchForm')));
			}

        </script>
    </body>

    </html>