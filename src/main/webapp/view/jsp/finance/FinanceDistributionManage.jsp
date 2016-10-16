<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>分销管理信息</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
                        <style>
        .datagrid-row {
  height: 33px;
}
</style>
    </head>
    <body onload="query();">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'" onclick="add();">添加</a></td>
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>代理商编号</td>
                            <td><input name="agentIdSch" class="textbox" style="width: 120px; height: 22px;" /></td>
                            <td>分销方式</td>
                               <td><select   name="saleTypeSch" class="textbox"   style="width: 120px;height: 22px;">
                                        <option value ="">请选择</option>
                                        <option value ="1">推荐分销</option>
                                        <option value ="5">间接推荐分销</option>
                                        
                                        <!-- <option value="2">服务费分销</option>
                                        <option value="3">服务费提成</option>
                                        <option value="4">商场分销</option>-->
                                        <option value="3">服务费提成</option>
                                        <option value="6">服务费直接分销</option>
                                        <option value="7">服务费间接分销</option>
                                        <option value="8">服务费再间接分销</option>
                                        <option value="9">衍生品直接分销</option>
                                        <option value="10">衍生品间接分销</option>
                                        <option value="11">衍生品再间接分销</option>
                                        <option value="14">衍生品销售收益</option>
                                        </select>
                                    </td>
                            <td>创建时间</td>
                            <td><input name="createdStartSch" class="easyui-datebox" data-options="disabled:false" editable="false"  style="width: 100px" />
                                -
                                <input name="createdEndSch" class="easyui-datebox" data-options="disabled:false" editable="false"  style="width: 100px" />
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
            <div data-options="region:'east',split:true,border:false" style="width: 30%; padding: 5px;">
                <form id="form" name="form" method="post" class="form">
                    <fieldset>
                        <legend>商场分销--<span id="operateMode_title">添加模式</span> </legend>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th>代理商ID</th>
                                    <td><input id="agentId" name="agentId" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                            <tr>
                                    <th>代理商姓名</th>
                                    <td><input id="name" name="name" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                <tr>
                                    <th>代理商等级</th>
                                    <td><select id="levelId"  name="levelId" class="textbox"   style="height: 22px;">
                                        <option value ="">请选择</option>
                                        <option value ="1">粉丝合伙人</option>
                                        <option value="2">小区合伙人</option>
                                        <option value="3">大区合伙人</option>
                                        <option value="4">金牌合伙人</option>
                                        </select>
                                    </td>
                                </tr>
                                 <tr>
                                    <th>分销方式</th>
                                    <td><select id="saleType"  name="saleType" class="textbox"   style="height: 22px;">
                                        <option value ="">请选择</option>
                                        <option value ="1">推荐分销</option>
                                        <option value ="5">间接推荐分销</option>
                                        <!-- <option value="2">服务费分销</option>
                                        <option value="3">服务费提成</option>
                                        <option value="4">商场分销</option>
                                        <option value="3">服务费提成</option>-->
                                        <option value="6">服务费直接分销</option>
                                        <option value="7">服务费间接分销</option>
                                        <option value="8">服务费再间接分销</option>
                                        <option value="9">衍生品直接分销</option>
                                        <option value="10">衍生品间接分销</option>
                                        <option value="11">衍生品再间接分销</option>
                                        <option value="14">衍生品销售收益</option>
                                        </select>
                                    </td>
                                </tr>
                                 <tr>
                                    <th>分销金额</th>
                                    <td><input id="amount"  name="amount" class="textbox easyui-validatebox"  data-options="validType:'floatOrInt'" style="height: 22px;" /></td>
                                </tr>
                                <tr>
                                    <th>收益金额</th>
                                    <td><input id="income"  name="income" class="textbox easyui-validatebox" data-options="validType:'floatOrInt'" style="height: 22px;"  /></td>
                                </tr>
                                <tr><th>操作</th>
                                <td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="submitOperate();">确定</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="reset();">重置</a></td>
                                </tr>                             
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>

        <script type="text/javascript">
      
       //重置操作  
         function reset(){
           	var idValue = $("#form input[name='id']").val();
           	if(idValue>0){
           		$("#form").form("load",ctx+"getFinanceDistributionManageById.do?id="+idValue);
           	}else{
           		$("#form").form("reset");//初始值恢复
           	}
           }       
         //查询操作
         function query(){
         	var grid=$("#grid").datagrid({
         		   fit:true,
                    border : false,
                    collapsible : false,
                    rownumbers : true,
                    pagination : true,
                    singleSelect : true,
                    rownumbers : true,
         		url:ctx+'listFinanceDistributionManage.do',
                 columns:[[
	{field:'agentId',title:'代理商ID',width:"10%",align:'center'}
	,{field:'name',title:'代理商姓名',width:"10%",align:'center'}
	,{field:'levelId' ,width:"10%",align:'center',title:'代理商等级'
		,formatter:function(value){
			if(value == 1){
				return "粉丝合伙人";
			}else if(value == 2){
				return "小区合伙人";
			}else if(value == 3){
				return "大区合伙人";
			}else{
				return "金牌合伙人";
			}
		}
	}
	,{field:'saleType',width:"10%",align:'center',title:'分销方式'
		,formatter:function(value){
			if(value == 1){
				return "推荐分销";
			}else if(value == 2){
				return "服务费分销";
			}else if(value ==3){
				return "服务费提成";
			}else if(value==4){
				return "商场分销"
			}
			else if(value==6){
				return"服务费直接分销";
			}
			else if(value==7){
				return"服务费间接分销";
			}
			else if(value==8){
				return"服务费再间接分销";
			}
			else if(value==9){
				return"衍生品直接分销";
			}
			else if(value==10){
				return"衍生品间接分销";
			}
			else if(value==11){
				return"衍生品再间接分销";
			}
			else if(value==14){
				return"衍生品销售收益";
			}
			else{
				return "间接推荐分销"
			}	
		}
	}
		,{field:'amount',title:'分销金额',width:"10%",align:'center',formatter:function(value){return value}}
		,{field:'income',title:'收益金额',width:"10%",align:'center',formatter:function(value){return value}}
	//,{field:'editedTime',title:'更新时间',width:"15%",align:'center'}
	,{field:'createdTime',title:'创建时间',width:"15%",align:'center'}
		                    ]],
		                    onLoadSuccess : function(data){
		                    	$('.iconImg').attr('src',fw.pixel_0);
		                    },
		                    onClickRow :function(index,row){
		                    $("#form").form("load",row);	
		                    $("#operateMode_title").html("修改模式");
		                    }
         	});
         }
         var grid=$("#grid").datagrid({
   		   fit:true,
              border : false,
              collapsible : false,
              rownumbers : true,
              pagination : true,
              singleSelect : true,
              rownumbers : true,
   		url:ctx+'listFinanceDistributionManage.do',
           columns:[[
                  	 	{field:'agentId',title:'代理商ID',width:"10%",align:'center'}
	                ,{field:'name',title:'代理商姓名',width:"10%",align:'center'}
     				,{field:'levelId' ,width:"10%",align:'center',title:'代理商等级'
     					,formatter:function(value){
     						if(value == 1){
     							return "粉丝合伙人";
     						}else if(value == 2){
     							return "小区合伙人";
     						}else if(value == 3){
     							return "大区合伙人";
     						}else{
     							return "金牌合伙人";
     						}
     					}
     				}
     				,{field:'saleType',width:"10%",align:'center',title:'分销方式'
     					,formatter:function(value){
     						if(value == 1){
     							return "推荐分销";
     						}else if(value == 2){
     							return "服务费分销";
     						}else if(value ==3){
     							return "服务费提成";
     						}else if(value==4){
     							return "商场分销"
     						}
     						else if(value==6){
    							return"服务费直接分销";
    						}
    						else if(value==7){
    							return"服务费间接分销";
    						}
    						else if(value==8){
    							return"服务费再间接分销";
    						}
    						else if(value==9){
    							return"衍生品直接分销";
    						}
    						else if(value==10){
    							return"衍生品间接分销";
    						}
    						else if(value==11){
    							return"衍生品再间接分销";
    						}
    						else if(value==14){
    							return"衍生品销售收益";
    						}
     						else{
     							return "间接推荐分销"
     						}	
     					}
     				}
     				,{field:'amount',title:'分销金额',width:"10%",align:'center',formatter:function(value){return value}}
     				,{field:'income',title:'收益金额',width:"10%",align:'center',formatter:function(value){return value}}
     				//,{field:'editedTime',title:'更新时间',width:"15%",align:'center'}
         			,{field:'createdTime',title:'创建时间',width:"15%",align:'center'}
	                    ]],
	                    onLoadSuccess : function(data){
	                    	$('.iconImg').attr('src',fw.pixel_0);
	                    },
	                    onClickRow :function(index,row){
	                    $("#form").form("load",row);	
	                    $("#operateMode_title").html("修改模式");
	                    }
   	});
         //添加操作
         function add(){
        	 $("#operateMode_title").html("添加模式");
        	 $('#gird').datagrid("unselectAll");
        	 $("#form").form("reset");
         }
         //提交操作
         function submitOperate(){
            if($('#saleType').val()==null || $('#saleType').val()==""){
            	alert("分销方式不能为空"); return;
            	}   	 
            if($('#levelId').val()==null || $('#levelId').val()==""){
            	alert("代理商等级不能为空"); return;
            	}   	 
            if($('#agentId').val()==null || $('#agentId').val()==""){
            	alert("代理商ID不能为空"); return;
            	}   	 
          	var idValue = $("form input[name='id']").val();
          		if(idValue > 0){  //修改
          			$.post(ctx+"updateFinanceDistributionManage.do",fw.serializeObject($('form')),function(result){
          				if(result.success){
          					$.messager.show(
          							{
          								title : message.title.normal,
          								msg : message.update_success,
          								timeout : message.timeout,
          								showType : message.showType
          							}
          							);
          					grid.datagrid("reload");
          					
          					$("#form").form("load",ctx+"getFinanceDistributionManageById.do?id="+idValue);
          				}
          			}, 'json');
          	
          		}else{
          				//添加
          				  $.post(ctx+"addFinanceDistributionManage.do",fw.serializeObject($('form')),function(result){
          					
          					  if(result.success){
          						 $.messager.show(
          						{
          						title : message.title.normal,
          						msg:message.add_success,
          						timeout:message.timeout,
          						showType:message.showType
          						}		 
          						 );
          						 $("#form").form("reset");
          						 query();
          					  }
          				  },'json'
          						  );
          			}
          		}
          		 //删除
                function del(){
             
                	var rows = $('#grid').datagrid('getSelections');
        
                	if(rows<=0){
                		$.messager.show({
                		title : message.title.normal,
                		msg:message.grid_select,
                		timeout : message.timeout,
                		showType :message.showType
                		})
                	}else{
                		$.messager.confirm(message.title.askTitle,message.dele_comfirm,function(r){
                          if(r){
                			var idValue = $("#form input[name='id']").val();
                		$.post(ctx+"delFinanceDistributionManage.do",{id:idValue},function(result){
                			if(result.success){
                				$.messager.show(
                						{
                				title : message.title.normal,
                				msg:message.dele_success,
                				timeout : message.timeout,
                				showType : message.showType
                						}
                			);
                				query();
                			}else{
                				$.messager.show(
                				{
                					title : message.title.normal,
                					msg : message.dele_fail,
                					timeout : message.timeout,
                					showType : message.showType
                				}		
                				)
                			}
                		},'json'
                				);
                		$("#operateMode_title").html("添加模式");
                        $('#grid').datagrid("unselectAll");
                        $("#form").form("reset");
                          }
                		})
                	}
                }
        </script>
        
    </body>
</html>