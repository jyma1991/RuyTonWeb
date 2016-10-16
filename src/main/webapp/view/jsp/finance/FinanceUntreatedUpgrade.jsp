<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>代理商升级信息</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
                        <style>
        .datagrid-row {
  height: 33px;
}
</style>
    </head>
    <body >
        <div class="easyui-layout" data-options="fit:true">
        	 <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
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
                                    <th>代理商ID</th>
                                    <td><input id="agentId" readonly="readonly" name="agentId" class="textbox" style="height: 22px;" /></td>
                                </tr>                                                 
                                <tr>
                                    <th>代理商等级</th>
                                    <td><input id="levelId" name="levelId" readonly="readonly" class="textbox" style="height: 22px;" /></td>
                                </tr>                                
                                <tr>
                                    <th>代理商姓名</th>
                                    <td><input id="userName" name="userName" readonly="readonly" class="textbox" style="height: 22px;" /></td>
                                </tr>                                
                                <tr>
                                    <th>身份证号码</th>
                                    <td><input id="identityCardNo" name="identityCardNo" readonly="readonly" class="textbox" style="height: 22px;" /></td>
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
                    pagination : true,
                    singleSelect : true,
         		url:ctx+'listAgentUpgrade.do',
                 columns:[[
                   {field:'agentId',title:'代理商ID',width:"10%",align:'center',sortable:'true'}
				  ,{field:'levelId',title:'代理商等级',width:"10%",align:'center'}
				  ,{field:'identityCardNo',title:'身份证号码',width:"15%",align:'center'}
				  ,{field:'cardNo',title:'卡号',width:"15%",align:'center'}
				  ,{field:'userName',title:'代理商姓名',width:"15%",align:'center'}
				  ,{field:'editedTime',title:'更新时间',width:"15%",align:'center'}
				  ,{field:'createdTime',title:'创建时间',width:"15%",align:'center'}				
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
		          $.post(ctx+"submitAgentUpgrade.do",fw.serializeObject($('form')),function(result){	
		          if(result == "success"){
		          	alert("操作成功");
		          	query();
		          }else{
		          	alert("操作失败");
		          }
		   },'json'
		 );
		}
		$("#back").click(function() {
		   window.location.href="Login.jsp?";
		});
        </script>
    </body>
</html>