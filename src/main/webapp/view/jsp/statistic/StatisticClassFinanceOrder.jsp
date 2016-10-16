<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="org.durcframework.core.UserContext"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>学校订单详细列表</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>

    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:50px;">
                <form id="searchForm">
                    <table>

                        <tr>
                        	<td>
                        		<a id="goback" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
                        	</td>
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
         
            $(function() {

                grid = $('#grid').datagrid({
                    fit: true,
                    border: false,
                    collapsible: false,
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    rownumbers: true,
                    url: ctx + 'listClassFinanceOrder.do?schoolIdSch='+<%=request.getParameter("schoolId")%>,
                    columns: [
                        [{
                            field: 'classId',
                            title: 'ID',
                            sortable:true
                        }, {
                            field: 'className',
                            title: '班级名称',
                            sortable:true
                        }, {
                            field: 'orderNum',
                            title: '订单数量',
                            sortable:true
                        }, {
                            field: 'totalFee',
                            title: '总收费',
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
				window.location.href="StatisticSchoolFinanceOrder.jsp?agentId="+<%=request.getParameter("agentId")%>;
			});
        </script>
    </body>

    </html>