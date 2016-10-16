<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.ryt.web.entity.user.User"%>
        <%@page import="org.durcframework.core.UserContext"%>
            <%@ taglib prefix="rms" uri="/rms" %>
                <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
                <html>

                <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
                    <title>校园管理</title>
                    <jsp:include page="/view/taglib.jsp"></jsp:include>

                </head>

                <body>
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',split:true,border:false" style="height:50px;">
                            <form id="searchForm">
                                <table>
                                    <tr>
                                        <td>
                                            <div class="hidden-agent">
                                            	代理商<input id="agentIdSch" name="agentIdSch" class="textbox" editable="false" style="width: 120px; height: 22px;" />
                                            </div> 
                                        </td>
                                        <td>校园名称</td>
                                        <td>
                                            <input name="schoolNameSch" class="textbox" style="width: 120px; height: 22px;" />
                                        </td>
                                        <td>创建时间</td>
                                        <td>
                                            <input name="createdStartSch" class="easyui-datebox" style="width: 100px" /> -
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
                    </div>
                    <script type="text/javascript">
                        $(function() {
                        	//只有当用户为代理商的时候，roleName以及userId才会用到
                        	var roleName = <%=request.getParameter("roleName")%>;
                        	var userId = <%=request.getParameter("userId")%>;
                        	var param ="";
							if(roleName == "agent"){
								$('.hidden-agent').css("display","none");
								param = "?agentIdSch="+userId;
								$("#agentIdSch").val(userId);
							}
                            var gird;
                            var SS;
                            <%--//判断是否是管理员登录
            				var urldata;
	            			if(<%=UserContext.getInstance().getUser().getUserName()%> == "admin") {
	                            urldata = ctx + 'listScSchool.do';
	                        } else {
	                            urldata = ctx + 'listScSchool.do?agsentIdSch=' + <%=UserContext.getInstance().getUser().getId()%>;
	                        }--%>

                        grid = $('#grid').datagrid({
                            fit: true,
                            border: false,
                            collapsible: false,
                            rownumbers: true,
                            pagination: true,
                            singleSelect: true,
                            rownumbers: true,
                            url: ctx + 'listScSchoolAndStudentTeacher.do'+param,
                            columns: [
                                [{
                                    field: 'id',
                                    title: 'ID',
                                    sortable: true
                                }, {
                                    field: 'userName',
                                    title: '用户名',
                                    formatter: function(value, row, index) {
                                        if (row.user)
                                            return row.user.userName;
                                        return "";
                                    },
                                    sortable: true
                                }, {
                                    field: 'mobilePhone',
                                    title: '手机',
                                    formatter: function(value, row, index) {
                                        if (row.user)
                                            return row.user.mobilePhone;
                                        return "";
                                    },
                                    sortable: true
                                }, {
                                    field: 'schoolName',
                                    title: '学校名称',
                                    sortable: true
                                }, {
                                    field: 'studentNum',
                                    title: '学生数量',
                                    sortable: true
                                }, {
                                    field: 'teacherNum',
                                    title: '老师数量',
                                    sortable: true
                                }, {
                                    field: 'parentNum',
                                    title: '家长数量',
                                    sortable: true
                                }, {
                                    field: 'businessLicence',
                                    title: '营业执照',
                                    sortable: true
                                }, {
                                    field: 'sortId',
                                    title: '序号',
                                    sortable: true
                                }, {
                                    field: 'createdTime',
                                    title: '创建时间',
                                    formatter: getDate,
                                    sortable: true
                                }]
                            ],
                            onLoadSuccess: function(data) {
                                $('.iconImg').attr('src', fw.pixel_0);
                            },
                            onClickRow: function(index, row) {

                            }

                        });
                        //初始化代理商
                        $.getJSON(ctx+'listAllScAgent.do',function(agentJson){
                            if (agentJson.rows.length == 0) {
                                SS = $('#agentIdSch').combobox();
                                SS.combobox("clear");
                                //DS.combobox('loadData',{schoolId:0,schoolName:" "});
                                //ES.combobox('loadData',{id:0,className:" "});
                            } else {
                                SS = $('#agentIdSch').combobox({
                                    data: agentJson.rows,
                                    valueField: 'agentId',
                                    textField: 'trueName',
                                    DropDownStyle: 'DropDownList',
                                    onSelect: function(row) {
                                    	grid.datagrid('load',fw.serializeObject($('#searchForm')));				
                                    }
                                });
                            }
                        });


                    });
                </script>
            </body>

		</html>