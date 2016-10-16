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
            <div data-options="region:'north',split:true,border:false" style="height:80px;">
                <form id="searchForm">

                    <table>
                        <tr>
                            <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
                            <rms:role operateCode="delete">
                            <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
                            </rms:role>
                            
                            <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a>
                            </td>
                            <td><a id="operateclass" class="easyui-linkbutton" data-options="iconCls:'ext-icon-accept',disabled:true">学校管理</a></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>校园名称</td>
                            <td><input name="schoolNameSch" class="textbox" style="width: 120px; height: 22px;" /></td>
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
                <div style="display:none">
                <input name="user.id" class="userId" id="user.id"/>
                <input name="schoolId" id="schoolId" />
                <input name="agentId" id="agentId" value="<%=UserContext.getInstance().getUser().getId()%>"/>
                <input name="user.roleProperty" value="4"/>
                <input id="mobilePhone" name="user.mobilePhone" class="textbox "  readonly="readonly" style="background-color:#EAEAEA;height: 22px;" />
                <input name="sign_pic" id="sign_pic" value="http://www.ruiyantong.com/top_image.jpg"/>
                <input name="erweima_pic" id="erweima_pic" value="http://www.ruiyantong.com/erweima_image.png"/>
                <input name="advertisement_one" id="advertisement_one" value="http://www.ruiyantong.com/p1.png"/>
                <input name="advertisement_two" id="advertisement_two" value="http://www.ruiyantong.com/p2.png"/>
                <input name="advertisement_three" id="advertisement_three" value="http://www.ruiyantong.com/p3.png"/>
                </div>
                    <fieldset>
                        <legend> 基本信息--<span id="operateMode_title">添加模式</span> </legend>
                        <table class="table">
                            <tbody>
                                 <tr>
                                    <th>ID</th>
                                    <td style="background-color:#EAEAEA"><input id="id" readonly="readonly" value="0" name="id" class="textbox"      style="background-color:#EAEAEA;height: 22px;width:40px" /></td>
                                    <th></th>
                                    <td style="background-color:#EAEAEA">
                                       
                                    </td>
                                	
                                </tr>
                                
                                <tr>
                                    <th>登录名</th>
                                    <td>
                                        <input id="userName" name="user.userName" class="textbox easyui-validatebox" data-options="validType:'mobile'" required="true" style="height: 22px;" /><span style="color:red">*登录名为手机号</span>
                                    </td>
                                                                        <th>固定电话</th>
                                    <td>
                                        <input id="fixedPhone" name="user.fixedPhone" class="textbox " data-options="validType:'phone'" style="height: 22px;" />
                                    </td>
                                   
                                </tr>
                                <tr>
									                                    <th>密码</th>
                                    <td>
                                        <input type="password" id="userPwd" name="user.userPwd" class="textbox easyui-validatebox" required="true" validType="length[5,1000]" style="height: 22px;" />
                                    </td>
                                    <th>邮箱</th>
                                    <td>
                                        <input id="email" name="user.email" class="textbox easyui-validatebox" data-options="validType:'email'" style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr>
									<th>昵称</th>
                                    <td><input id="nickName" name="user.nickName" class="textbox easyui-validatebox" required="true" style="height: 22px;" /></td>
                                    <th>微信</th>
                                    <td>
                                        <input id="weiXin" name="user.weiXin" class="textbox" style="height: 22px;" />
                                    </td>
                                </tr>
                                <tr>
                                 <th>真实姓名</th>
                                    <td>
                                        <input id="trueName" name="user.trueName" class="textbox easyui-validatebox" required="true" style="height: 22px;" />
                                    </td>
                                    <th>QQ</th>
                                    <td>
                                        <input id="qq" name="user.qq" class="textbox easyui-numberbox" data-options="validType:'qq'" style="height: 22px; " />
                                    </td>

                                </tr>
                                <tr>
                                	<th>性别</th>
						            <td><input id="sex" name="user.sex" value='1' class="textbox easyui-validatebox"  editable="false" style="height: 22px;width:40px" /></td> 	
						    		<th>序号</th>
						            <td><input id="sortId" name="sortId"  class="textbox easyui-numberspinner"  style="height: 22px;width:40px" /></td>
                                </tr>
                                 <tr>
							         <th>地址</th>
							          <td colspan="3"><span style="display: inline-block;width: 30px; text-align:center;">省</span><input id="provinceId"  name="user.provinceId" class="textbox " editable="false"  style="height: 22px;width:25%;margin-left:5px;" />
							            <span style="display: inline-block;width: 30px; text-align:center;">市</span><input id="cityId"  name="user.cityId" class="textbox" editable="false" style="height: 22px;width:25%;margin-left:5px" />
							            <span style="display: inline-block;width: 30px; text-align:center;">区</span><input id="districtId" name="user.districtId" class="textbox" editable="false" style="height: 22px;width:25%;margin-left:5px" />
							          </td>
							     </tr>
                                <tr>
                                    <th>详细地址</th>
                                    <td colspan="3">
                                        <textarea cols="100" id="addressDetail" name="user.addressDetail" class="textbox" style="padding-top:3px;height: 22px; width:90%;"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th>备注</th>
                                    <td colspan="3">
                                        <textarea rows="3" id="remark" name="user.remark" class="textbox" style=" width:90%;white-space: pre-wrap;" wrap="hard" /></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th>校园名称</th>
                                    <td><input id="schoolName" name="schoolName" class="textbox easyui-validatebox"    required="true"  style="height: 22px;" /></td>
                                    <th>营业执照</th>
                                    <td><input id="businessLicence" name="businessLicence" class="textbox easyui-validatebox"  required="true" style="height: 22px;" /></td>
                                </tr>
                                <tr>  
                                    <th>学校类型</th>
                                    <td><input id="schoolTypeId"  name="schoolTypeId" class="textbox " editable="false"  style="height: 22px;" /></td>
                                    <th>传真</th>
                                    <td><input id="fax" name="fax" class="textbox"   style="height: 22px;" /></td>
                                </tr>
                                <tr>
   								<th>操作</th>
                                <td colspan="3"><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
                                	
                                </td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <script type="text/javascript">
	    	//定义省市区comboboxr
	    	var PS;//省
	    	var CS;//市
	    	var DS;//区
            $(function() {
            	var gird; 
 <%--           //判断是否是管理员登录
            	var urldata;
            	if(<%=UserContext.getInstance().getUser().getUserName()%>=="admin"){
            		urldata=ctx+'listScSchool.do';
            	}else{
            		urldata=ctx+'listScSchool.do?agsentIdSch='+<%=UserContext.getInstance().getUser().getId()%>;
            	} --%> 
            	
	                      grid=$('#grid').datagrid({
	                          fit:true,
	                          border : false,
	                          collapsible : false,
	                          rownumbers : true,
	                          pagination : true,
	                          singleSelect : true,
	                          rownumbers : true,
	                          url:ctx+'listScSchool.do?agentIdSch='+<%=UserContext.getInstance().getUser().getId()%>,
	                          columns:[[
	      											//{field:'id',title:'ID',sortable:true}
	      											{field:'userName',title:'用户名',formatter:function(value,row,index){
	                  		            				return row.user.userName;            				
	                  		            			},width:"20%",align:'center'} //sortable:true
	                  		            			,{field:'mobilePhone',title:'手机',formatter:function(value,row,index){
	                  		            				return row.user.mobilePhone;            				
	                  		            			},width:"20%",align:'center'}
	                  		            			,{field:'schoolName',title:'学校名称',width:"20%",align:'center'}
	                  		            			,{field:'businessLicence',title:'营业执照',width:"20%",align:'center'}
	                  		            			//{field:'sortId',title:'序号',sortable:true}
	                  		            			,{field:'createdTime',title:'创建时间',formatter:getDate,width:"20%",align:'center'}
	                  		                    ]],
	                          onLoadSuccess : function(data) {
	                              $('.iconImg').attr('src', fw.pixel_0);
	                             // $('#sex').combobox('select',1);
	                          },
	                          onClickRow : function(index, row) {
	                        	  $("#form").form("reset");
	                              $("#form").form("myLoad", row);
	                              $('#sex').combobox('loadData',[{"id":"1","name":"男"},{"id":"0","name":"女"}]);
	                             //学校类型
	
	              	            //清除省下拉框的值
	              	            $.getJSON(ctx+'listAreaProvince.do',{idSch:row.user.provinceId},function(provinceJson) {
	              	            	if(provinceJson.rows.length==0){
	              	            		PS.combobox("clear");
	              	            	}
	              	             });
	            	           //根据cityID获取当前用户的city
	            	        	$.getJSON(ctx+'listAllAreaCity.do',{idSch:row.user.cityId},function(cityJson) {
	            	        		if(cityJson.rows.length>0){		
	            		        	 	CS=$('#cityId').combobox({
	            			        		data :[{id:cityJson.rows[0].id,cityName:cityJson.rows[0].cityName,selected:true}],
	            			        	    valueField:'id',
	            			        	    textField:'cityName',  
	            			        	}) 
	            	        		}else{	
	            	        			CS.combobox("clear");
	            	        			CS.combobox('loadData',{id:1,cityName:" "});
	            	        		}
	            				});
	            	        	//根据districtId获取当前用户的city
	            	        	$.getJSON(ctx+'listAllAreaDistrict.do',{idSch:row.user.districtId},function(districJson) {
	            	        		if(districJson.rows.length>0){	
	            		        	 	DS=$('#districtId').combobox({
	            				        	data : [{id:districJson.rows[0].id,districtName:districJson.rows[0].districtName,selected:true}],
	            				        	valueField:'id',
	            				        	textField:'districtName'
	            				        })
	            	        		}else{
	            	        			DS.combobox("clear");
	            	        			DS.combobox('loadData',{id:1,districtName:""});
	            	        		}
	            	        	});
	                              $("#operateclass").linkbutton('enable');
	                              $("#operateMode_title").html("修改模式");
	                              //$("#userPwd").val("");
		              	          pwd=$("#userPwd").val();
		            	          $("#userPwd").val(pwd.substring(0,6));
	                          }

            	});
	                      
	              $("#userName").blur(function(){
	                  $("#mobilePhone").val($("#userName").val());
	               });
	             //性别        
	             CSsex = $('#sex').combobox({
	                 valueField: 'id',
	                 textField: 'name',
	                 panelHeight:'auto',  
	                 data:[{id:"1",name:"男"},{id:"0",name:"女"}]
	             });	                
                //添加操作
                $("#btn_add").click(function() {
                    $("#operateMode_title").html("添加模式");
                    $('#grid').datagrid("unselectAll");
                    $("#id").attr("value", 0);
                    $("#operateclass").linkbutton('disable');
                    DS.combobox('loadData',{id:1,districtName:""});
                    CS.combobox('loadData',{id:1,cityName:""});
                    $("#form").form("reset");
                    $("#userPwd").val("");
                    $('#sex').combobox('select',1);
                  	$.getJSON(ctx + 'listAllSysClass.do?sysClassTypeIdSch=13',function(ctypeJson) {
                	 	CS = $('#schoolTypeId').combobox({
                    		  	data :ctypeJson,
                    	        valueField:'id',
                    	        textField:'className',
                    	        panelHeight: 'auto'
                    	}) 
                	});
                })  
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
                                $.post(ctx+"delScSchool.do", {
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
                //确认提交操作
                $("#operateMode_submit").click(function() {
                    var idValue = $("#form input[name='id']").val();
                    if ($('#form').form('validate')) {
                        if (idValue > 0) {
                            //修改
                            if($("#userPwd").val()!=pwd.substring(0,6)){
    							$('#userPwd').val(faultylabs.MD5($.trim($('#userPwd').val()))); 	
                            }
				            	var name=$("#userName").val();
								$.getJSON(ctx+"listAllUser.do",{userNameSch:name,},function(resultUser){
									if(resultUser.rows.length>0){
										var rowid=resultUser.rows[0].id;
									}
									var jsonLength = 0;
									for(var item in resultUser.rows){
										jsonLength++;
									}
									//alert(jsonLength);
									if(rowid==$(".userId").val()||jsonLength==0){
			                            $.post(ctx+"updateScSchool.do", fw.serializeObject($('form')), function(result) {
			                                if (result.success) {
			                                    $.messager.show({
			                                        title : message.title.normal,
			                                        msg : message.update_success,
			                                        timeout : message.timeout,
			                                        showType : message.showType
			                                    });   
			                                    grid.datagrid("reload");
			                                    //重新获取form信息内容
			                                    $("#form").form("load", ctx+"getScSchoolById.do?id=" + idValue);
			                                    setTimeout('$("#userPwd").val($("#userPwd").val().substring(0,6))',100);
			                                    
			                                    //setTimeout('$("#userPwd").val($("#userPwd").val().substring(0,6))',100);
			                                    
			                                }
			                            }, 'json');
									}else{
										$.messager.show({
				                            title : message.title.normal,
				                            msg : message.userName_has,
				                            timeout : message.timeout,
				                            showType : message.showType
				                        });
									};
								});
                            
                            
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
                                var rname=$("#userName").val();
                                $.getJSON(ctx+"listAllUser.do",{userNameSch:rname,},function(resultUser){
                                	if(resultUser.rows.length==0){
                                        $('#userPwd').val(faultylabs.MD5($.trim($('#userPwd').val())));
                                        $.post(ctx+"addScSchool.do", fw.serializeObject($('form')), function(result) {
                                            if (result.success) {
                                                $.messager.show({
                                                    title : message.title.normal,
                                                    msg : message.add_success,
                                                    timeout : message.timeout,
                                                    showType : message.showType
                                                });
                                                grid.datagrid("reload");
                                                $("#form").form("reset");
                                            }
                                        }, 'json');
                                	}else{
                                		$.messager.show({
                                            title : message.title.normal,
                                            msg : message.userName_has,
                                            timeout : message.timeout,
                                            showType : message.showType
                                        });
                                	}
                                });

                            }
                        }
                    }
                })
                //重置操作
                $("#operateMode_reset").click(function() {
                    var idValue = $("#form input[name='id']").val();
                    if (idValue > 0) {
                        $("#form").form("load",ctx+"getScSchoolById.do?id=" + idValue);
                    } else {
                        $("#form").form("reset");
                    }
                })
    			$("#operateclass").click(function() {
    				if( $("#schoolId").val().trim().length>0 ){
    					window.location.href="ScSchoolClass.jsp?schoolId="+$("#schoolId").val();
    				}else{
    					$.messager.show({
    						title : message.title.normal,
    						msg : message.no_record,
    						timeout : message.timeout,
    						showType : message.showType
    					});
    				}
    			});
            });
	    	//
	    	$.getJSON(ctx + 'listAllSysClass.do?sysClassTypeIdSch=13',function(ctypeJson) {
	    		console.log(ctypeJson);
        	 	CS = $('#schoolTypeId').combobox({
            		  	data :ctypeJson,
            	        valueField:'id',
            	        textField:'className',
            	        panelHeight: 'auto'
            	}) 
        	});
	    	
        	/*选择省以后再显示市的下拉，在选择市的信息以后再显示区的下拉******************************/
        	/*@author:lihui***********************************************************************/
        	/*2015.10.28 createtime***************************************************************/
        	//市初始化
        	$.getJSON(ctx+'listAllAreaCity.do',function(cityJson) {
        	 	CS = $('#cityId').combobox({
            		   //data :[{id:cityJson.rows[0].id,cityName:cityJson.rows[0].cityName,selected:true}],
            	        valueField:'id',
            	        textField:'cityName',  
            	}) 
        	});
            //区初始化
            $.getJSON(ctx+'listAllAreaDistrict.do',function(districJson) {
                DS = $('#districtId').combobox({
        			//data : districJson.rows,
        		    valueField:'id',
        		    textField:'districtName'
        			}) 
             });
            $.getJSON(ctx+'listAllAreaProvince.do', function(json) {
             PS= $('#provinceId').combobox({
        	        data : json.rows,
        	        valueField:'id',
        	        textField:'provinceName',
        	        DropDownStyle:'DropDownList',
        	        onSelect:function(paramsPro){
        	        	DS.combobox("clear");
        	        	DS.combobox('loadData',{id:1,districtName:" "});
        	        	$.getJSON(ctx+'listAllAreaCity.do',{provinceIdSch:paramsPro.id},function(cityJson) {
        	        		$('#cityId').combobox({
        	        			data : cityJson.rows,
        	        	        valueField:'id',
        	        	        textField:'cityName',
        	        	        onSelect:function(paramsCity){
        	        	        	$.getJSON(ctx+'listAllAreaDistrict.do',{cityIdSch:paramsCity.id},function(districJson) {
        	        	        	DS= $('#districtId').combobox({
        	        	        			data : districJson.rows,
        	        	        			valueField:'id',
        	        	         	        textField:'districtName'
        	        	        		})
        	        	        	})
        	        	        }
        	        		})
        	        	})
        	        }
                });
            });
        </script>
    </body>
</html>