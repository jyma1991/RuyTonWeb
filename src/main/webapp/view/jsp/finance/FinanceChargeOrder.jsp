<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Insert title here</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>
    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">
                    <table>
                        <tr>
                            <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
                            <!-- <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td> -->
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
                            <td><a id="btn_export"  href="#" class="easyui-linkbutton btn_scStudent" data-options="iconCls:'ext-icon-page_white_excel'">下载模板</a></td>
                         	<td><a id="btn_import"  href="#" class="easyui-linkbutton btn_scStudent" data-options="iconCls:'ext-icon-page_excel'">导入(仅*.xlsx,请按班级分次导入)</a></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>产品名称</td>
                            <td><input id="productIdSch" name="productIdSch" class="textbox" style="width: 120px; height: 22px;" /></td>
                            <td>充值账号</td>
                            <td><input id="chargeAccountIdSch" name="chargeAccountIdSch" class="textbox" style="width: 120px; height: 22px;" /></td>
                            <td>创建时间</td>
                            <td><input name="createdStartSch" class="easyui-datebox" style="width: 100px" />
                                -
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
            <div data-options="region:'east',split:true,border:false" style="width: 50%; padding: 5px;">
                <form id="form" name="form" method="post" class="form">
                    <input type="hidden" id="userId" name="userId" class="textbox" style="height: 22px;" />
                    <input type="hidden" id="userName" name="userName" class="textbox" style="height: 22px;" />
                    <input type="hidden" id="uuid" name="uuid" class="textbox" value="0" style="height: 22px;" />
                    <input type="hidden" id="isDeleted" value="0" name="isDeleted" class="textbox" style="height: 22px;" />
                    <input type="hidden" id="operaterId" value="0" name="operaterId" class="textbox" style="height: 22px;" />
                    <fieldset>
                        <legend> 基本信息--<span id="operateMode_title">添加模式</span> </legend>
                        <table class="table">
                            <tbody>
                                                                                                                                                                                                                                                                                                                            <tr>
                                    <th>ID</th>
                                    <td><input id="id" readonly="readonly" value="0" name="id" class="textbox"   style="height: 22px; width:80px; background:#eee;" /></td>
                                </tr>
                                                                                                                                                                                                <tr>
                                    <th>产品名称</th>
                                    <td><input id="productId" name="productId" class="textbox" required="true" editable="false"  style="height: 22px; width:120px;" /></td>
                                </tr>
                                <!--                                                                                                                                                                                                                                                                                                                                                                 <tr>
                                    <th>订单类型</th>
                                    <td><input id="orderType" value="0" name="orderType" class="textbox"   style="height: 22px;width:80px;" /></td>
                                </tr> -->
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>数量</th>
                                    <td><input id="quantity" value="0" name="quantity" class="textbox  easyui-numberspinner"   style="height: 22px;width:50px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>支付金额</th>
                                    <td><input id="totalFee" value="0" name="totalFee" class="textbox"   style="height: 22px;width:80px;" /> 元</td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>支付方式</th>
                                    <td><input id="payType" name="payType" class="textbox" required="true" editable="false" style="height: 22px;width:100px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>支付状态</th>
                                    <td><input id="pStatus" class="textbox"   style="height: 22px;width:60px;background:#eee;" value="未支付" readonly /><input type="hidden" id="payStatus" value="0" name="payStatus" class="textbox"   style="height: 22px;width:50px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>购买者账号</th>
                                    <td><input id="buyerEmail" name="buyerEmail" class="textbox"   style="height: 22px;width:180px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>充值账号</th>
                                    <td><input id="chargeAccountId" name="chargeAccountId" class="textbox"  required="true"  style="height: 22px;width:180px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>购买时间</th>
                                    <td><input id="payTime" name="payTime" data-options="disabled:true" class="easyui-datetimebox"  style="width: 180px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>审核时间</th>
                                    <td><input id="confirmTime" name="confirmTime" data-options="disabled:true" class="easyui-datetimebox"  style="width: 180px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>代理商</th>
                                    <td><input id="agentId" name="agentId" class="textbox"  required="true" editable="false"  style="height: 22px;width:100px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>学校</th>
                                    <td><input id="schoolId"  name="schoolId" class="textbox"  required="true"  editable="false"  style="height: 22px;width:100px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>班级</th>
                                    <td><input id="classId"  name="classId" class="textbox"  required="true" editable="false"  style="height: 22px;width:100px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                <tr>
                                    <th>学生姓名</th>
                                    <td><input id="studentId" name="studentId" class="textbox"  required="true" editable="false"  style="height: 22px;width:100px;" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                <tr>
                                    <th>审核人</th>
                                    <td><input id="comfirmUserName" value=""  class="textbox"   style="height: 22px;width:100px;background:#eee;" readonly/><input id="confirmUserId" value="0" name="confirmUserId" class="textbox"   style="display:none" /></td>
                                </tr>
                                                                                                                                                                                                                                                                                                                                <tr>
                                    <th>序号</th>
                                    <td><input id="sortId" value="0" name="sortId" class="textbox easyui-numberspinner"  style="height: 22px;width:60px" /></td>
                                </tr>
                                <tr>
                                	<th>操作</th>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
                                	<td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <script type="text/javascript">
	      	//代理商
	        var AS;
	        //学校
	        var SS;
	        var SSloadFlag = false;
	        //班级
	        var CS;
	        var CSloadFlag = false;
	        //学生
	        var ST;
	        var STloadFlag = false;
            $(function() {
                var gird;
                //产品
                var PS;
                //支付方式
                var PAY;
                grid=$('#grid').datagrid({
                    fit:true,
                    border : false,
                    collapsible : false,
                    rownumbers : true,
                    pagination : true,
                    singleSelect : true,
                    rownumbers : true,
                    url:ctx+'listFinanceChargeOrder.do',
                    columns:[[
		                       
                  		{ sortable:true,field:'id',title:'id'}                   
            			
                  		,{ sortable:true,field:'productId',title:'产品名称',formatter:function(value,row,index){
                  			if(row.product){
                  				return row.product.productName;
                  			}
                  		}}
            		                       
            			<!--,{ sortable:true,field:'orderType',title:'订单类型'}-->
            		                       
            			,{ sortable:true,field:'quantity',title:'数量'}
            		                                       
            			,{ sortable:true,field:'totalFee',title:'支付金额'}
            			
            			<!--,{ sortable:true,field:'payType',title:'支付方式'}-->
            			
            			,{ sortable:true,field:'payStatus',title:'支付状态',formatter:function(value,row,index){
            				if(value == 1){
            					return '已完成';
            				}else if(value == 0){
            					return '未支付';
            				}else{
            					return '取消';
            				}
            				
            			}}
            			
            			,{ sortable:true,field:'chargeAccountId',title:'充值账号'}
            			<!--,{ sortable:true,field:'buyerEmail',title:'购买者账号'}-->
            		                       
            			<!--,{ sortable:true,field:'chargeAccountId',title:'充值账号'}-->
            		                       
            			,{ sortable:true,field:'payTime',title:'购买时间',formatter:function(value,row,index){
            				return value.substring(0,10);
            			}}
            		                       
            			<!--,{ sortable:true,field:'confirmUserId',title:'审核人'}-->
            		                       
            			<!--,{ sortable:true,field:'confirmTime',title:'审核时间'}-->
            		                       
            			,{ sortable:true,field:'studentId',title:'学生姓名',formatter:function(value,row,index){
            				if(row.student){
                  				return row.student.trueName;
                  			}
            			}}
            		                       
            			<!--,{ sortable:true,field:'classId',title:'classId'}-->
            		                       
            			<!--,{ sortable:true,field:'schoolId',title:'schoolId'}-->
            		                       
            			,{ sortable:true,field:'createdTime',title:'添加时间',formatter:function(value,row,index){
            				return value.substring(0,10);
            			}}
            		    ]],
                    onLoadSuccess : function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    },
                    onClickRow : function(index, row) {
                        $("#form").form("load", row);
                        //格式化支付状态
	   	                 if(row.payStatus == 1){
	   	                	 $("#pStatus").val("已完成");
	   	                 }else if(row.payStatus == 2){
	   	                	 $("#pStatus").val("取消");
	   	                 }else{
	   	                	$("#pStatus").val("未支付");
	   	                 }
                        //格式化审核人
                        if(row.comfirmUser){
                        	$("#comfirmUserName").val(row.comfirmUser.trueName);
                        }
                        SS.combobox("clear");
                        CS.combobox("clear");
                        ST.combobox("clear");
                    	loadItem(row);
                        $("#operateMode_title").html("修改模式");
                        
                    }
                });
                
              //初始化产品
                $.getJSON(ctx+'listAllFinanceChargeProduct.do',function(productJson){
                    if (productJson.rows.length == 0) {
                    	PS = $('#productId').combobox();
                    	PS.combobox("clear");
                    } else {
                    	PS = $('#productId').combobox({
                            data: productJson.rows,
                            valueField: 'id',
                            textField: 'productName',
                            DropDownStyle: 'DropDownList',
                            onSelect: function(row) {
                            				
                            }
                        });
                    }
                });
              
              //初始化支付方式       16表示的是支付方式
                $.getJSON(ctx+'listAllSysClass.do?sysClassTypeIdSch=16',function(payJson){
                    if (payJson.length == 0) {
                    	PAY = $('#payType').combobox();
                    	PAY.combobox("clear");
                    } else {
                    	PAY = $('#payType').combobox({
                            data: payJson,
                            valueField: 'id',
                            textField: 'className',
                            DropDownStyle: 'DropDownList',
                            onSelect: function(row) {
                            				
                            }
                        });
                    }
                });
              
              	//初始化代理商
                $.getJSON(ctx+'listAllScAgent.do',function(agentJson){
                    if (agentJson.rows.length == 0) {
                        AS = $('#agentId').combobox();
                        AS.combobox("clear");
                        //DS.combobox('loadData',{schoolId:0,schoolName:" "});
                        //ES.combobox('loadData',{id:0,className:" "});
                    } else {
                        AS = $('#agentId').combobox({
                            data: agentJson.rows,
                            valueField: 'agentId',
                            textField: 'trueName',
                            DropDownStyle: 'DropDownList',
                            onSelect: function(row) {
                            	SSloadFlag = false;
                            	if(row){
                            		if(CS){
                            			CS.combobox("clear");
                            		}
                            		if(SS){
                    	           		SS.combobox("clear");
                            		}
                            		if(ST){
                            			ST.combobox("clear");
                            		}
	                            	//初始化学校
	                    	        $.getJSON(ctx+'listAllScSchool.do',{agentIdSch:row.agentId},function(schoolJson) {
	                    	           	if(schoolJson.rows.length==0){
	                    	           		SS = $('#schoolId').combobox();
	                    	           		SS.combobox("clear");
	                    	           	}else{
	                    	           		SS = $('#schoolId').combobox({
	                    	           		    data:schoolJson.rows,
	                    	           	        valueField:'schoolId',
	                    	           	        textField:'schoolName',
	                    	           	        DropDownStyle:'DropDownList',
	                    	           	        onSelect:function(row){
	                    	           	        	CSloadFlag = false;
	                    	           	        	if(row){
	                   		                        	//班级初始化  教室为69  公共场地为70
	                   		                         	$.getJSON(ctx+'listAllScClass.do',{schoolIdSch:row.schoolId,placeTypeIdSch:69},function(classJson) {
	                   		            	            	if(classJson.rows.length==0){
	                   		            	            		$("#classId").val(null);
	                   		            	            		CS = $('#className').combobox();
	                   		            	            		CS.combobox("clear");
	                   		            	            		CS.combobox('loadData',{id:0,className:" "});
	                   		            	            		//DS.combobox('loadData',{schoolId:0,schoolName:" "});
	                   		            	                 	//ES.combobox('loadData',{id:0,className:" "});
	                   		            	            	}else{
	                   		            	            		CS = $('#classId').combobox({
	                   		            	            		    data:classJson.rows,
	                   		            	            	        valueField:'id',
	                   		            	            	        textField:'className',
	                   		            	            	        DropDownStyle:'DropDownList',
	                   		            	            	        onSelect:function(row){
	                   		            	            	        	STloadFlag = false;
	                   		            	            	        	//初始化学校
	                   		                                	        $.getJSON(ctx+'getScStudentUserByClassId.do',{classId:row.id},function(studentJson) {
	                   		                                	           	if(studentJson.length==0){
	                   		                                	           		ST = $('#studentId').combobox();
	                   		                                	           		ST.combobox("clear");
	                   		                                	           	}else{
	                   		                                	           		ST = $('#studentId').combobox({
	                   		                                	           		    data:studentJson,
	                   		                                	           	        valueField:'id',
	                   		                                	           	        textField:'trueName',
	                   		                                	           	        DropDownStyle:'DropDownList',
	                   		                                	           	        onSelect:function(row){
	                   		                                	           	        	
	                   		                                	           	        },
	                   		                                	           	  		onLoadSuccess:function(){
	                  			    		        	        	        			STloadFlag = true;
	                  			    		                    	        		}
	                   		                                	           		});
	                   		                                	           	}
	                   		                                	        });
	                   		            	            	        },
	                   		            	            	     	onLoadSuccess:function(){
	     			    		        	        	        		CSloadFlag = true;
	     			    		                    	        	}
	                   		            	            		});
	                   		            	            	}
				    		                    	        
	                   		            	             });
	                    	           	        	}
	                    	           	        },
		                    	           	     onLoadSuccess:function(){
		 		        	        	        	SSloadFlag = true;
		 		                    	        }
	                    	           		});
	                    	           	}
	                    	        });
                            	}
                            }
                        });
                    }
                });
              	
                $("#btn_export").click(function() {
            		window.open('../../../template/importOrderTemplate.xlsx');
	            });
	            
	            $("#btn_import").click(function() {
	                    var btn = $(this);
	                    url = '../../pluploader.jsp?moduleId=finance&funcId=importOrder';//+'&userId=' + userId;
	
	                    var dialog = fw.modalDialog({
	                        title: '选择文件',
	                        url: url,
	                        width: 700,
	                        height: 500,
	                        buttons: [{
	                            text: '确认选择',
	                            handler: function() {
	                                var rows = dialog.find('iframe').get(0).contentWindow.returnFormValue(dialog);
	                                $.post(ctx + "importOrder.do",rows[0], function(result) {
	                                    if (result.success) {
	                                    	if(result.message ==""){
	                                    		$.messager.show({
		                                            title: message.title.normal,
		                                            msg: message.add_success,
		                                            timeout: message.timeout,
		                                            showType: message.showType
		                                        });
	                                    	}else{
	                                    		$.messager.show({
		                                            title: message.title.normal,
		                                            msg: result.message,
		                                            timeout: message.timeout,
		                                            showType: message.showType,
		                                            timeout:100000,
		                                            width:'300px',
		                                            height:'160px'
		                                            
		                                        });
	                                    	}
	                                        
	                                        grid.datagrid("reload");
	                                        $("#form").form("reset");
	                                    }else
	                                    	{
		                                    	$.messager.show({
		                                            title: message.title.normal,
		                                            msg: message.add_fail+result.message,
		                                            timeout: message.timeout,
		                                            showType: message.showType
		                                        });
	                                    	}
	                                }, 'json');
	                                
	                            }
	                        }, {
	                            text: '清除文件',
	                            handler: function() {
	                                dialog.find('iframe').get(0).contentWindow.clearSelectedFiles(dialog, btn);
	                            }
	                        }, {
	                            text: '关闭窗口',
	                            handler: function() {
	                                dialog.find('iframe').get(0).contentWindow.closeDialog(dialog);
	                            }
	                        }]
	                    });
	            });
            
                //添加操作
                $("#btn_add").click(function() {
                    $("#operateMode_title").html("添加模式");
                    $('#grid').datagrid("unselectAll");
                    $("#id").attr("value", 0);
                    $("#form").form("reset");
                })
            	
                /**
                //删除操作
                $("#btn_dele").click(function() {
                    var rows = $('#grid').datagrid('getSelections');
                    if (rows <= 0) {
                        $.messager.show({
                            title : message.title.normal,
                            msg : message.grid_select,
                            timeout : message.timeout,
                            showType : message.showType
                        });
                    } else {
                        $.messager.confirm(message.title.askTitle, message.dele_comfirm, function(r) {
                            if (r) {
                                var idValue = $("#form input[name='id']").val();
                                $.post(ctx+"delFinanceChargeOrder.do", {
                                    id : idValue
                                }, function(result) {
                                    if (result.success) {
                                        $.messager.show({
                                            title : message.title.normal,
                                            msg : message.dele_success,
                                            timeout : message.timeout,
                                            showType : message.showType
                                        });
                                        grid.datagrid('reload');
                                    } else {
                                        $.messager.show({
                                            title : message.title.normal,
                                            msg : message.dele_fail,
                                            timeout : message.timeout,
                                            showType : message.showType
                                        });
                                    }
                                }, 'json');
                                $("#btn_add").click();
                            }
                        });
                    }
                })
            	**/
                //确认提交操作
                $("#operateMode_submit").click(function() {
                    var idValue = $("#form input[name='id']").val();
                    if ($('#form').form('validate')) {
                        if (idValue > 0) {
                            //修改
                            $.post(ctx+"updateFinanceChargeOrder.do", fw.serializeObject($('form')), function(result) {
                                if (result.success) {
                                    $.messager.show({
                                        title : message.title.normal,
                                        msg : message.update_success,
                                        timeout : message.timeout,
                                        showType : message.showType
                                    });
            
                                    grid.datagrid("reload");
            
                                    //重新获取form信息内容
                                    $("#form").form("load", ctx+"getFinanceChargeOrderById.do?id=" + idValue);
                                }
                            }, 'json');
                        } else {
                            if(idValue.trim().length == 0){
                                $.messager.show({
                                    title : message.title.normal,
                                    msg : message.add_button_click,
                                    timeout : message.timeout,
                                    showType : message.showType
                                });
                            }else{
                                //添加
                                $.post(ctx+"addFinanceChargeOrder.do", fw.serializeObject($('form')), function(result) {
                                	if (result.success) {
                                        $.messager.show({
                                            title : message.title.normal,
                                            msg : message.add_success,
                                            timeout : message.timeout,
                                            showType : message.showType
                                        });
                                        grid.datagrid("reload");
                                        $("#form").form("reset");
                                    }else{
                                    	$.messager.show({
                                            title : message.title.normal,
                                            msg : result.message,
                                            timeout : message.timeout,
                                            showType : message.showType
                                        });
                                    }
                                }, 'json');
                            }
                        }
                    }
                })
                //重置操作
                $("#operateMode_reset").click(function() {
                    var idValue = $("#form input[name='id']").val();
                    if (idValue > 0) {
                        $("#form").form("load",ctx+"getFinanceChargeOrderById.do?id=" + idValue);
                    } else {
                        $("#form").form("reset");
                    }
                })
            });
            
          	//学校初始化
            SS = $('#schoolId').combobox();
            //班级初始化
            CS = $('#classId').combobox();
            //学生初始化
            ST = $("#studentId").combobox();
            
          	//读取代理商学校以及班级下拉框的数据
            function loadItem(row){
            	 if(row){
            		agentId = row.agentId;
	                 //代理商初始化
	                 $.getJSON(ctx+'listAllScAgent.do',function(agentJson) {
	   	            	if(agentJson.rows.length==0){
	   	            		AS.combobox("clear");
	   	            		SS.combobox('loadData',{schoolId:0,schoolName:" "});
	   	                 	CS.combobox('loadData',{id:0,className:" "});
	   	                 	ST.combobox('loadData',{id:0,trueName:" "});
	   	            	}else{
	   	            		AS.combobox("clear").combobox("select",agentId);
	   	            		var Did=setInterval(function(){
	   	            			if(SSloadFlag){
	   	            				clearInterval(Did);
	   	            				SS.combobox("clear").combobox("select",row.schoolId);
	   	            				var Eid = setInterval(function(){
	   		   	            			if(CSloadFlag){
	   		   	            				clearInterval(Eid);
	   		   	            				CS.combobox("clear").combobox("select",row.classId);
		   		   	            			var Tid = setInterval(function(){
		   			   	            			if(STloadFlag){
		   			   	            				clearInterval(Tid);
		   			   	            				ST.combobox("clear").combobox("select",row.studentId);
		   			   	            			}
		   			   	            			
		   			   	            		}, 5);
	   		   	            			}
	   		   	            		}, 5);
	   	            			}
	   	            		}, 5);
	   	            	}
	   	             });
            	 }
            }

            
          //初始化产品
            $.getJSON(ctx+'listAllFinanceChargeProduct.do',function(productJson){
                if (productJson.rows.length == 0) {
                	$('#productIdSch').combobox();
                	$('#productIdSch').combobox("clear");
                } else {
                	$('#productIdSch').combobox({
                        data: productJson.rows,
                        valueField: 'id',
                        textField: 'productName',
                        DropDownStyle: 'DropDownList',
                        onSelect: function(row) {
                        	grid.datagrid('load',fw.serializeObject($('#searchForm')));
                        }
                    });
                }
            });
        
        </script>
    </body>
</html>