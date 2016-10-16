<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/rms" prefix="rms" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>代理商详细列表</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>

    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:50px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td>用户名称</td>
                            <td>
                                <input name="userNameSch" class="textbox" style="width: 120px; height: 22px;" />
                            </td>
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
            $(function() {
                var PS;
                var DS;
                var CS;
                var cityFlag = 0;
                var districtFlag = 0;
                grid = $('#grid').datagrid({
                    fit: true,
                    border: false,
                    collapsible: false,
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    rownumbers: true,
                    url: ctx + 'listScAgentAndSchool.do',
                    columns: [
                             [
							 {
							     field: 'id',
							     title: 'ID',
							     sortable:true
							 }, {
                                 field: 'trueName',
                                 title: '真实姓名',
                                 formatter:function(value,row,index){
                            		 if(row.user){
                            			 return row.user.trueName;
                            		 }
                                 },
                                 sortable:true
                             }, {
                                 field: 'userName',
                                 title: '用户名',
                                 formatter:function(value,row,index){
                            		 if(row.user){
                            			 return row.user.userName;
                            		 }
                                 },
                                 sortable:true
                             }, {
                                 field: 'agentId',
                                 title: '代理商ID',
                                 sortable:true
                             }, {
                                 field: 'schoolNum',
                                 title: '学校数量',
                                 sortable:true
                             }, {
                                 field: 'levelId',
                                 title: '等级',
                                 sortable:true
                             }, {
                                 field: 'identityCardNo',
                                 title: '身份证号',
                                 sortable:true
                             }, {
                                 field: 'fax',
                                 title: '传真',
                                 sortable:true
                             }, {
                            	 field: 'mobilePhone',
                            	 title: '移动电话',
                            	 formatter:function(value,row,index){
                            		 if(row.user){
                            			 return row.user.mobilePhone;
                            		 }
                                 },
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
			function searchForm(){
				grid.datagrid('load',fw.serializeObject($('#searchForm')));
			}

        </script>
    </body>

    </html>