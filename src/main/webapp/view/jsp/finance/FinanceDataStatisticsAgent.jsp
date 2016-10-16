<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>数据统计</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>
    <body onload="query();">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td>创建时间</td>
                            <td><input name="createdStartSch" class="easyui-datebox" data-options="disabled:false" editable="false"  style="width: 100px" />
                                -
                                <input name="createdEndSch" class="easyui-datebox" data-options="disabled:false" editable="false"  style="width: 100px" />
                            </td>
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="filter();">过滤</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
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
         //查询操作
         function query(){
         	var grid=$("#grid").datagrid({
         		   fit:true,
                    border : false,
                    collapsible : false,
                    pagination : false,
                    singleSelect : true,
         		url:ctx+'agentManage.do',
                 columns:[[
                   {field:'agentOneLevel',title:'粉丝合伙人人数',width:"20%",align:'center',sortable:'true'}
				  ,{field:'agentTwoLevel',title:'小区合伙人人数',width:"20%",align:'center'}
				  ,{field:'agentThreeLevel',title:'大区合伙人人数',width:"20%",align:'center'}
				  ,{field:'agentFourLevel',title:'金牌合伙人人数',width:"20%",align:'center'}
				  ,{field:'totalAgent',title:'代理商总人数',width:"20%",align:'center'}
		                    ]],
		                    onLoadSuccess : function(data){
		                    	$('.iconImg').attr('src',fw.pixel_0);
		                    }
         	});
         }
         
         //过滤操作
         function filter(){
    	   var createdStartSch =  $("#searchForm input[name='createdStartSch']").val();
    	   var createdEndSch =  $("#searchForm input[name='createdEndSch']").val();
         	var grid=$("#grid").datagrid({
         		    fit:true,
                    border : false,
                    collapsible : false,
                    pagination : false,
                    singleSelect : true,
         		url:ctx+'agentManageFilter.do?createdStartSch='+createdStartSch+'&createdEndSch='+createdEndSch,
                 columns:[[
                           {field:'agentOneLevelFilter',title:'粉丝合伙人人数',width:"20%",align:'center',sortable:'true'}
         				  ,{field:'agentTwoLevelFilter',title:'小区合伙人人数',width:"20%",align:'center'}
         				  ,{field:'agentThreeLevelFilter',title:'大区合伙人人数',width:"20%",align:'center'}
         				  ,{field:'agentFourLevelFilter',title:'金牌合伙人人数',width:"20%",align:'center'}
         				  ,{field:'totalAgentFilter',title:'代理商总人数',width:"20%",align:'center'}
		          ]],
		           onLoadSuccess : function(data){
		           $('.iconImg').attr('src',fw.pixel_0);
		        }
         	});
         }
        </script>
    </body>
</html>