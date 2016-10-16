<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.durcframework.core.UserContext"%>
<jsp:include page="/view/taglib.jsp"></jsp:include>
<title>系统分类</title>
<body>
	<!-- Layout Begin -->
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',split:true,border:false" style="height: 40px;">
			<table>
				<tr>
					<td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
					<td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
					<td><a onclick="redoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-resultset_next'">展开</a></td>
					<td><a onclick="undoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-resultset_previous'">折叠</a></td>
					<td><a id="article_manage" class="easyui-linkbutton" data-options="group:'g1',iconCls:'ext-icon-application_form_edit'">信息管理</a></td>
					<td><a onclick="grid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
				</tr>
			</table>
		</div>

		<div data-options="region:'center',border:false">
			<table id="grid"></table>
		</div>

		<!-- East Begin -->
		<div data-options="region:'east',split:true,border:false" style="width: 50%; padding: 5px;">
				<!-- 自定义表单 Begin -->
				<div title="分类信息" id="frmView">
					<form id="form" name="form" method="post" class="form">
						<input type="hidden" name="gridRowId" id="gridRowId"> <input type="hidden" name="gridRowParentId" id="gridRowParentId"> <input type="hidden" name="sysClassTypeId" id="sysClassTypeId">
						<fieldset>
							<legend>
								基本信息--<span id="operateMode_title">添加模式</span>
							</legend>
							<table class="table">
								<tbody>
									<tr>
										<th>编号</th>
										<td><input id="id" name="id" readonly="readonly" class="textbox" style="height: 22px;" /></td>
									</tr>
									<tr>
										<th>信息名称</th>
										<td><input name="className" class="textbox easyui-validatebox" style="height: 22px;" required="true"" /></td>
									</tr>
									<tr>
										<th>信息描述</th>
										<td><textarea name="description" style="width:99%"></textarea></td>
									</tr>
									<tr>
										<th>排序</th>
										<td><input name="sortId" class="easyui-validatebox easyui-numberspinner" data-options="min:0,max:100,required:true" value="0" style="width: 50px;" /></td>
									</tr>
									<tr>
										<th>分类属性</th>
										<td><input name="isshow" type="checkbox" value="1" />是否显示 
											<input name="issystem" type="checkbox" value="1" />是否系统
										 </td>
									</tr>
									   <tr>
                                            <th>图片</th>
                                            <td>
                                                <select class="selectFiles">
                                                    <option>选择文件</option>
                                                </select> <a href="#" class="easyui-linkbutton uploader" data-options="iconCls:'icon-add'">选择文件</a>
                                                <input name="picId" class="selectFilesValue" type="hidden" />
                                            </td>
                                     </tr>
                                     <tr>
										<th>选择图标</th>
										<td>
										<input  id= "icon" name= "icon" class = "textbox" style ="height:22px" ></input>
										<a id="addIcons"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">选择图标</a> 
										</td>
									</tr>
									<tr>
										<th>上级资源</th>
										<td><select id="parentId" name="parentId">
										</select> <a href="#" onclick="$('#parentId').combotree('clear');">清除</a></td>
									</tr>
									<tr>
										<th>操作</th>
										<td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td>
									</tr>
								</tbody>
							</table>
						</fieldset>
					</form>
					<div id="addRoleDlg" class="easyui-dialog" style="width: 500px; height: 440px;" title="Icons" data-options="onClose:Globle.clearPanel()" closed="true" modal="true">
					<div id="roleFrame"></div>
				</div>
				<!-- 自定义表单 End -->
		</div>
		<!-- East End -->
	</div>
	<!-- Layout End -->

	<script type="text/javascript">
		var grid;
		 //绑定上传按钮功能
	    $(".uploader").on('click', uploaderFun);
	    //先对所有uploader的class对象进行遍历
	    $(".uploader")
	        .each(function () {
	            var selectObj = $(this)
	                .prev('.selectFiles');
	            var options = "";
	            //如果该文件选择器有值，则进行异步数据获取
	            if ($(this)
	                .next('.selectFilesValue')
	                .val()
	                .trim()
	                .length != 0) {
	                var ids = $(this)
	                    .next('.selectFilesValue')
	                    .val();
	                $.ajax({
	                        type: "POST",
	                        url: ctx + "findSysUploadByIds.do",
	                        data: {
	                            ids: ids
	                        }
	                    })
	                    .done(function (result) {
	                        result = JSON.parse(result);
	                        if (result.length > 0) {
	                            //对返回结果进行遍历，重组option选项，并将结果放置到相应的selectOption对象中去
	                            $(result)
	                                .each(function () {
	                                    options += "<option>" + this.clientName + "</option>";
	                                })
	                            selectObj.html(options);
	                            selectObj.prepend("<option>选择文件(选中了 " + result.length + " 个)</option>");
	                        }
	                    })
	            } else {
	                selectObj.html("<option>选择文件</option>");
	            }
	        });
		
		
		 //选择文件部分的函数操作，需要将自身对象及ids列表作为参数进行传递
	    function uploaderFun() {
	        var btn = $(this);
	        var ids = "";
	        if ($(this)
	            .next()
	            .val()
	            .trim()
	            .length != 0) {
	            ids = "?ids=" + $(this)
	                .next()
	                .val();
	        }

	        if (ids.length > 0) {
	            url = '../../uploader.jsp' + ids + '&moduleId=sys&funcId=ScAbout';
	        } else {
	            url = '../../uploader.jsp?moduleId=sys&funcId=ScAbout';
	        }

	        var dialog = fw.modalDialog({
	            title: '选择文件',
	            url: url,
	            width: 700,
	            height: 500,
	            buttons: [{
	                text: '确认选择',
	                handler: function () {
	                    dialog.find('iframe')
	                        .get(0)
	                        .contentWindow.submitForm(dialog, btn);
	                }
	            },  {
	                text: '关闭窗口',
	                handler: function () {
	                    dialog.find('iframe')
	                        .get(0)
	                        .contentWindow.closeDialog(dialog);
	                }
	            }]
	        });
		 }
		

	    $("#addIcons").click(function() {
	    	var mao=$("#icon").val()
	    	mao=mao+".png";
			$('#roleFrame').html('<iframe id="iconFrame"  src= "../sys/SysClassIcon.jsp?pigname='+ mao + '" scrolling="no" frameborder="0" style="width:100%;height:400px;"></iframe>');
			console.log($('#roleFrame'));
			$('#addRoleDlg').dialog('open');	
			$("#iconFrame").contents().find("#addIcons").click();
	    })
		
	    function getImg(){
	    	
	    	$("#icon").val($("#iconFrame").contents().find("#imgSelected").val());
	    }
	    function closeDlg(){
	    	$('#addRoleDlg').dialog('close');
	    	
	    }
	    
		//二进制字段编辑前拆分的拆分
	    function setRowBeforeEdit(row) {

	        row.isshow = 0;
	        row.issystem = 0;
	        row.isindex = 0;
	        row.islist = 0;

	        if (1 == (row.classProperty & 1)) //发布
	            row.isshow = 1;
	        if (2 == (row.classProperty & 2)) //置顶
	            row.issystem = 1;
	        if (4 == (row.classProperty & 4)) //推荐
	        	 row.isindex = 1;
	        if (8 == (row.classProperty & 8)) //滚动
	            row.islist = 1;   
	    }
	    
		//编辑后组合"",isindex,isindex
	    function setRowAfterEdit(frmdata) {
	        frmdata.classProperty = 0;
	        if (1 == frmdata.isshow) //发布
	            frmdata.classProperty += 1;
	        if (1 == frmdata.issystem) //置顶
	            frmdata.classProperty += 2;
	        if (1 == frmdata.isindex) //推荐
	            frmdata.classProperty += 4;
	        if (1 == frmdata.islist) //滚动
	            frmdata.classProperty += 8;

	    }

		$(function() {
			//常量，系统分类中校园简介的id
			var sysClassTypeId = ClassTypeId.ScAbout;
			var userId = <% out.print(UserContext.getInstance().getUser().getId());%>;
			
			$("#sysClassTypeId").val(sysClassTypeId);
			grid = $('#grid').treegrid({
				fit : true,
				idField : 'id',
				treeField : 'className',
				parentField : 'parentId',
				rownumbers : true,				
				singleSelect : true,
				url : ctx + 'listSysClass.do?sysClassTypeIdSch='+sysClassTypeId+'&userIdSch='+userId,
				columns : [ [ {
					field : 'id',
					title : 'ID',
					width : 40
				}, {
					field : 'className',
					title : '分类名称',
					width : 150
				}, 
				{
					field : 'articleCount',
					title : '篇目',
					sortable:'true',
					width : 40
				},
				{
					field : 'sortId',
					title : '排序',
					sortable:'true',
					width : 40
				},
				{
					field : 'createdTime',
					title : '创建时间',
					width : '120',
                    sortable:'true',
                    formatter:getDate 
				},
				{
					field : 'editedTime',
					title : '编辑时间',
					width : '120',
                    sortable:'true',
                    formatter:getDate 
				}
				] ],
				onLoadSuccess : function(data) {
					$('.iconImg').attr('src', fw.pixel_0);
				},
				onClickRow : function(row) {
					$("#operateMode_title").html("修改模式");
					setRowBeforeEdit(row); 
					$("#form").form("load", row);
					$("#gridRowId").val(row.id);
					if (row.parentId != 0) {
						$("#gridRowParentId").val(row.parentId);
					} else {
						$('#parentId').combotree('clear');
					}
				}
			});

			var parentId = $('#parentId').combotree({
				editable : false,
				panelHeight : 'auto',
				idField : 'id',
				textField : 'className',
				parentField : 'parentId',
				url : ctx + 'listSysClass.do?sysClassTypeIdSch='+sysClassTypeId+'&userIdSch='+userId
			});		

			//页面加载初始化
			$(window).load(function() {
				$("#operateMode_title").html("添加模式");
				$("#form").form("reset");
			});

			//添加操作
			$("#btn_add").click(function() {
				$("#operateMode_title").html("添加模式");
				$('#grid').treegrid("unselectAll");
				$("#scId").attr("value", 0);
				$('#parentId').combotree({
					reload : ctx + 'listSysClass.do?sysClassTypeIdSch='+sysClassTypeId+'&userIdSch='+userId
				});
				$("#form").form("reset");
				grid.treegrid("reload");
			})

			//删除操作
			$("#btn_dele").click(function() {
				var rows = $('#grid').treegrid('getSelections');
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
							$.post(ctx + "delSysClass.do", {
								id : idValue,
								className : rows[0].className
							}, function(result) {
								if (result.success) {
									$.messager.show({
										title : message.title.normal,
										msg : message.dele_success,
										timeout : message.timeout,
										showType : message.showType
									});
									grid.treegrid("reload");	
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
				frmdata = fw.serializeObject($('form'));
                setRowAfterEdit(frmdata);
                console.log();
				if ($('#form').form('validate')) {
					//判断parentId值
					if ($('#parentId').combotree('getValue').trim().length == 0) {
						$('#parentId').combotree('setValue', 0);
					}
					
					if (idValue > 0) {
						//当前对象的ID值与combotree选中的ID值不能相等
						if ($("#gridRowId").val() != $('#parentId').combotree('getValue')) {
							//修改
							$.post(ctx + "updateSysClass.do", frmdata, function(result) {
								if (result.success) {
									$.messager.show({
										title : message.title.normal,
										msg : message.update_success,
										timeout : message.timeout,
										showType : message.showType
									});

									grid.treegrid("reload");

									$('#parentId').combotree({
										reload : ctx + 'listSysClass.do?sysClassTypeIdSch='+sysClassTypeId+'&userIdSch='+userId
									})

									//如果对象的parentId值为0的话，comboxtree需要进行清空，目的是为了让comboxtree框中不显示0字符串内容
									if ($('#parentId').combotree('getValue') == 0) {
										$('#parentId').combotree('clear');
									}
								}
							}, 'json');
						} else {
							$.messager.show({
								title : message.title.normal,
								msg : message.target_repeat,
								timeout : message.timeout,
								showType : message.showType
							});
						}

					} else {
						//添加
						$.post(ctx + "addSysClass.do", frmdata, function(result) {
							if (result.success) {
								$.messager.show({
									title : message.title.normal,
									msg : message.add_success,
									timeout : message.timeout,
									showType : message.showType
								});
								parentId.combotree({
									reload : ctx + 'listSysClass.do?sysClassTypeIdSch='+sysClassTypeId+'&userIdSch='+userId
								})
								$('#grid').treegrid("reload");
								$("#form").form("reset");
							}
						}, 'json');
					}

				}
			})

			//重置操作
			$("#operateMode_reset").click(function() {
				var idValue = $("#form input[name='scId']").val();
				if (idValue > 0) {
					$("#form").form("load", ctx + "getSysClassById.do?scId=" + idValue);
				} else {
					$("#form").form("reset");
				}
				parentId.combotree({
					reload : ctx + 'listSysClass.do?sysClassTypeIdSch='+sysClassTypeId+'&userIdSch='+userId
				})
			})
			
            $("#article_manage").click(function() {
                if ($("#id").val().trim().length > 0) {
                    window.location.href = "ScArticle.jsp?scId=" + $("#id").val();
                } else {
                    $.messager.show({
                        title: message.title.normal,
                        msg: message.no_record,
                        timeout: message.timeout,
                        showType: message.showType
                    });
                }
            });

		});
	</script>
</body>