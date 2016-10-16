<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>代理商提现信息</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
                                        <style>
        .datagrid-row {
  height: 33px;
}
</style>
    </head>
    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td>代理商编号</td>
                            <td><input name="agentIdSch" class="textbox" style="width: 120px; height: 22px;" /></td>
                            <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="filter();">过滤</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
                        	<td><a id="back"  href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">返回</a></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div data-options="region:'center',border:false">
                <table id="grid">
                </table>
            </div>
            <div data-options="region:'east',split:true,border:false" style="width: 30%; padding: 5px;">
                <form id="form" name="form" method="post" class="form">
                    <fieldset>
                        <legend>佣金管理</legend>
                        <table class="table">
                            <tbody>
                                   <tr>
                                    <th>ID</th>
                                    <td><input id="id" readonly="readonly" name="id" class="textbox"      style="height: 22px;" /></td>
                                </tr> 
                                <tr>
                                    <th>代理商ID</th>
                                    <td><input id="agentId" readonly="readonly" name="agentId" class="textbox"      style="height: 22px;" /></td>
                                </tr>                                                 
                                <tr>
                                    <th>姓名</th>
                                    <td><input id="name" name="name"  readonly="readonly" class="textbox" style="height: 22px;" /></td>
                                </tr>                                
                                <tr>
                                                        
                                    <th>身份证号码</th>
                                    <td><input id="identityCardNo" name="identityCardNo"  readonly="readonly" class="textbox" style="height: 22px;" /></td>
                                </tr>   
                                      <tr>
                                    <th>提现金额</th>
                                    <td><input id="withdrawAmt" name="withdrawAmt"  readonly="readonly" class="textbox" style="height: 22px;" /></td>
                                </tr>                               
                                <tr>
                                    <th>卡号</th>
                                    <td><input id="cardNo" name="cardNo"  readonly="readonly" class="textbox" style="height: 22px;" /></td>
                                </tr>                                
                                <tr>
                                    <th>手机号码</th>
                                    <td><input id="phone" name="phone"  readonly="readonly" class="textbox" style="height: 22px;" /></td>
                                </tr>                                
                                <tr><th>操作</th>
                                <td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="submitOperate();">确认</a> 
                                </tr>                             
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <script type="text/javascript">               
         //查询操作
         $(function() {
        	 query();
         });
         function query(){
         	var grid=$("#grid").datagrid({
      		     fit:true,
                 border : false,
                 collapsible : false,
                 rownumbers : true,
                 pagination : true,
                 singleSelect : true,
                 rownumbers : true,
            		url:ctx+'listIncomeAmount.do',
                    columns:[[
                     {field:'id',title:'ID',width:"10%",align:'center'}
                    ,{field:'agentId',title:'代理商ID',width:"10%",align:'center'}
          			,{field:'name',title:'姓名',width:"15%",align:'center',sortable:'true'}
          			,{field:'identityCardNo',title:'身份证号码',width:"15%",align:'center',sortable:'true'}
          			,{field:'withdrawAmt',title:'提现金额',width:"15%",align:'center',sortable:'true'}
          			,{field:'cardNo',title:'卡号',width:"15%",align:'center',sortable:'true'}
          			,{field:'phone',title:'手机号码',width:"15%",align:'center',sortable:'true'}
          	           ]],
          	              onLoadSuccess : function(data){
          	              $('.iconImg').attr('src',fw.pixel_0);
          	               },
          	               onClickRow :function(index,row){
          	               $("#form").form("load",row);	
          	            }
                    });
         	 }     
            //提交操作
            function submitOperate(){
             $.post(ctx+"updateIncomeAmount.do",fw.serializeObject($('form')),function(result){	
             if(result == "success"){
             	alert("操作成功");
             	query();
             }else{
             	alert("操作失败")
             }
      },'json'
    );
  }          		
      function filter(){
    	 var agentIdSch = $("#searchForm input[name='agentIdSch']").val();
    	  var grid=$("#grid").datagrid({
   		     fit:true,
              border : false,
              collapsible : false,
              rownumbers : true,
              pagination : true,
              singleSelect : true,
              rownumbers : true,
         		url:ctx+'filterListIncomeAmount.do?agentId='+agentIdSch,
                 columns:[[
                 {field:'id',title:'ID',width:"10%",align:'center'}
                ,{field:'agentId',title:'代理商ID',width:"10%",align:'center'}
       			,{field:'name',title:'姓名',width:"15%",align:'center',sortable:'true'}
       			,{field:'identityCardNo',title:'身份证号码',width:"15%",align:'center',sortable:'true'}
       			,{field:'withdrawAmt',title:'提现金额',width:"15%",align:'center',sortable:'true'}
       			,{field:'cardNo',title:'卡号',width:"15%",align:'center',sortable:'true'}
       			,{field:'phone',title:'手机号码',width:"15%",align:'center',sortable:'true'}
       	           ]],
       	              onLoadSuccess : function(data){
       	              $('.iconImg').attr('src',fw.pixel_0);
       	               },
       	               onClickRow :function(index,row){
       	               $("#form").form("load",row);	
       	            }
                 });
      	 }     
      $("#back").click(function() {
 			window.location.href="Login.jsp?";
 		});
            </script>
    </body>
</html>