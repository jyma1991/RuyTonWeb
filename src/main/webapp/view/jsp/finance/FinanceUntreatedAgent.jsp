<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>申请人信息</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>
    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td><a onclick="generatorGrid();grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
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
                        <legend>申请人信息</legend>
                        <table class="table">
                            <tbody>
                            <tr>                                                                                                                          
                                  <th>ID</th>
                                    <td><input id="id" name="id" readonly="readonly" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                 <tr>                                                                                                                          
                                  <th>姓名</th>
                                    <td><input id="name" name="name" readonly="readonly" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                <tr> 
                                    <th>身份证号码</th>
                                    <td><input id="identityCardNo" name="identityCardNo" readonly="readonly" class="textbox"      style="height: 22px;" /></td>
                                </tr>
                                <tr>
                                    <th>银行卡账号</th>
                                    <td><input id="cardNo"  name="cardNo" readonly="readonly" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                <tr>
                                    <th>推荐人ID</th>
                                    <td><input id="agentId"  name="agentId" readonly="readonly" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                <tr>
                                    <th>手机号码</th>
                                    <td><input id="phone"  name="phone" readonly="readonly" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                <tr>
                                <th>操作</th>
                                <td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="submitOperate();">确定</a>
                                </tr>                                                                            
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <script type="text/javascript"> 
      //查询操作
      	var grid;
		$(function() {
			grid=$("#grid").datagrid({
				fit:true,
                border : false,
                collapsible : false,
                rownumbers : true,
                pagination : true,
                singleSelect : true,
                rownumbers : true,
           		url:ctx+'listDeScAgent.do',
                   columns:[[
                     {field:'id',title:'ID',width:"15%",align:'center'}
         			,{field:'name',title:'姓名',width:"15%",align:'center',sortable:'true'}
         			,{field:'identityCardNo',title:'身份证号码',width:"15%",align:'center',sortable:'true'}
         			,{field:'cardNo',title:'银行卡账号',width:"15%",align:'center'}
         			,{field:'agentId',title:'推荐人ID',width:"15%",align:'center'}
         			,{field:'phone',title:'手机号码',width:"15%",align:'center'}
         	          ]],
				onLoadSuccess : function(data){
					$('.iconImg').attr('src',fw.pixel_0);
 				},
				onClickRow :function(index,row){
					$("#form").form("load",row);	
				}
			});
		});    
           //提交操作
		function submitOperate(){
			$.post(ctx+"agreeScAgent.do",fw.serializeObject($('form')),function(result){	
			if(result.success){
				//alert("操作成功")
				 $.messager.show({
					title : message.title.normal,
					msg : message.update_success,
					timeout : message.timeout,
					showType : message.showType
				});   
				grid.datagrid("reload");
				//query();
				$.post(ctx+"listAgentUpgrade.do",fw.serializeObject($('form')),function(result){},'json')
			}else{
				 $.messager.show({
						title : message.title.normal,
						msg : result.message,
						timeout : message.timeout,
						showType : message.showType
					});  
			}
			},'json');
		}
		$("#back").click(function() {
			window.location.href="Login.jsp";
		});
        </script>
    </body>
</html>