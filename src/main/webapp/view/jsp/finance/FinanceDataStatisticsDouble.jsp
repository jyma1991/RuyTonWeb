<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>数据统计</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>
<!--     <frameset rows="50%,50%" frameborder="NO" border="0" framespacing="0" >
    <frame noresize="noresize" src="FinanceDataStatisticsAgent.jsp" name="leftFrame" >
    <frame noresize="noresize" src="FinanceDataStatisticsPayments.jsp" name="rightFrame">
    </frameset>  -->

<body>
<div data-options="region:'center',border:false">
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
	 <table id="grid">
     </table>
</div>
<div data-options="region:'east',split:true,border:false" style="width: 50%; padding: 5px;">
	<form id="searchForm1">
		<table>
			<tr>
			<td>创建时间</td>
			<td><input name="createdStartSch1" class="easyui-datebox" data-options="disabled:false" editable="false"  style="width: 100px" />
				-
				<input name="createdEndSch1"   class="easyui-datebox" data-options="disabled:false" editable="false"  style="width: 100px" />
			</td>
			<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="filter1();">过滤</a> <a href="javascript:void(0);" id="reset_searchFilter1" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm1 input').val('');grid1.datagrid('load',{});">重置过滤</a></td>
			</tr>
		</table>
	</form>
	<table id="grid1">
     </table>
</div>
<script type="text/javascript">
//left 查询操作
	$(function() {
		query();
		query1();
	});
	function query(){
		var grid=$("#grid").datagrid({
			fit:true,
			border : false,
			collapsible : false,
			pagination : false,
			singleSelect : true,
			url:ctx+'agentManage.do',
			columns:[[
				{field:'agentOneLevel',title:'粉丝合伙人人数',width:"20%",align:'center'}  //,sortable:'true'
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
				{field:'agentOneLevelFilter',title:'粉丝合伙人人数',width:"20%",align:'center'	} //,sortable:'true'
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
//right 查询操作
	function query1(){
		var grid1=$("#grid1").datagrid({
			fit:true,
			border : false,
			collapsible : false,
			pagination : false,
			singleSelect : true,
			url:ctx+'financePayments.do',
			columns:[[
				{field:'agentDis',title:'代理商推荐收入',width:"20%",align:'center'}
				,{field:'serviceDis',title:'服务费收入',width:"20%",align:'center'}
				//,{field:'serviceIn',title:'服务费提成',width:"20%",align:'center'}
				,{field:'marketDis',title:'衍生品收入',width:"20%",align:'center'}
				,{field:'totalIn',title:'总收入',width:"20%",align:'center'}
				,{field:'beWithdraw',title:'已提现',width:"20%",align:'center'}
			]],
			onLoadSuccess : function(data){
				$('.iconImg').attr('src',fw.pixel_0);
			}
		});
	}
       //过滤操作
	function filter1(){
		var createdStartSch1 =  $("#searchForm1 input[name='createdStartSch1']").val();
		var createdEndSch1 =  $("#searchForm1 input[name='createdEndSch1']").val();
		var grid1=$("#grid1").datagrid({
			fit:true,
			border : false,
			collapsible : false,
			pagination : false,
			singleSelect : true,
			url:ctx+'financePaymentsFilter.do?createdStartSch='+createdStartSch1+'&createdEndSch='+createdEndSch1,
			columns:[[
{field:'agentDis',title:'代理商推荐收入',width:"20%",align:'center'}
,{field:'serviceDis',title:'服务费收入',width:"20%",align:'center'}
//,{field:'serviceIn',title:'服务费提成',width:"20%",align:'center'}
,{field:'marketDis',title:'衍生品收入',width:"20%",align:'center'}
,{field:'totalIn',title:'总收入',width:"20%",align:'center'}
,{field:'beWithdraw',title:'已提现',width:"20%",align:'center'}
			]],
			onLoadSuccess : function(data){
				$('.iconImg').attr('src',fw.pixel_0);
			}
		});
	}
</script>
</body>
</html>