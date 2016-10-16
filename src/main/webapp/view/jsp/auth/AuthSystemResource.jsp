<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Insert title here</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
 </head>
<body class="easyui-layout layout panel-noscroll" data-options="fit:true,border:false">
	<div data-options="region:'west',title:'菜单列表',split:true,border:false" style="width: 320px; padding: 10px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addRootMenu(); return false;">添加根节点</a>
		<table id="sysResTree"></table>
	</div>
	<div data-options="region:'center',title:'菜单信息',border:false">
		<div id="cont_tip" style="padding: 10px;">
			<h3>☜点击菜单查看信息</h3>
		</div>
		<div id="resInfo" style="display: none;">
			<div class="res-item">
				<fieldset>
					<legend>基础信息-修改</legend>
					<form id="sysForm" method="post">
						<input name="parentId" type="hidden">
						<input name="id" type="hidden">
						<table class="table">
							<tbody>
								<tr>
									<th>id</th>
									<td><input name="id" type="text" class="easyui-validatebox" readonly></td>
								</tr>
								<tr>
									<th>菜单名称</th>
									<td><input name="sysResName" type="text" class="easyui-validatebox"   required="true"></td>
								</tr>
								<tr>
									<th>url</th>
									<td><input name="url" type="text" class="easyui-validatebox" style="width: 300px;"></td>
								</tr>
								<tr>
									<th>操作</th>
									<td><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRes(); return false;">保存</a></td>
								</tr>
							</tbody>
						</table>
					</form>
				</fieldset>
			</div>
			<div class="res-item">
				<fieldset>
					<legend>设置操作点</legend>
					<div id="setOptDiv" style="height: 400px;">
						<div data-options="region:'center'" style="padding: 10px;">
							<div id="toolbar">
								<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addOpt()">添加操作点</a>
							</div>
							<table id="dg">
								
							</table>
						</div>
						<div data-options="region:'east',split:true,collapsed:true,title:'操作点列表'" style="width: 50%;">
							<div style="padding: 5px; background-color: #fafafa; margin-bottom: 10px;" data-options="iconCls:'icon-search'">
								<form id="optAddfm">
									<table>
										<tr>
											<td>操作代码:</td>
											<td><input id="operateCodeSch" type="text" class="easyui-validatebox"></td>
											<td>操作名称:</td>
											<td><input id="operateNameSch" type="text" class="easyui-validatebox"></td>
											<td><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchOpt(); return false;">查询</a></td>
										</tr>
									</table>
								</form>
							</div>
							<div id="optAddGrid"></div>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>

	<div id="addRootDl" class="easyui-dialog" style="width: 480px; height: 180px; " closed="true" modal="true" buttons="#addRootDldlg-buttons">
		<form id="addRootfm" method="post">
			<input id="parentId" name="parentId" type="hidden">
			<table class="table">
				<tbody>
					<tr>
						<th>菜单名称:</th>
						<td><input name="sysResName" type="text" class="easyui-validatebox"  validtype="length[2,20]" data-options="required:true"></td>
					</tr>
					<tr>
						<th>url:</th>
						<td><input name="url" type="text" class="easyui-validatebox" style="width: 300px;"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div id="addRootDldlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRootMenu(); return false;">保存</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="$addRootDl.dialog('close'); return false;">取消</a>
	</div>
	<script type="text/javascript">
		
		var that = this;
		var selectedNode;
		var $contTip = $('#cont_tip');
		var $resInfo = $('#resInfo');
		var $addRootDl = $('#addRootDl');
		var $sysForm = $('#sysForm');
		var $addRootfm = $('#addRootfm');
		var $setOptDiv = $('#setOptDiv');
		var layoutInit = false;

		var $sysResTree = $("#sysResTree");
		$sysResTree.tree({
			url : ctx + 'listAllMenu.do',
			onClick : function(node) {
				selectedNode = node;
				var attr = node.attributes;
				$sysForm.form('load', {
					parentId : node.parentId,
					sysResName : node.sysResName,
					url : attr.url,
					id : node.id,
				});

				$contTip.hide();

				
				reloadOptCode();
				initOptAddGird();
				
				initLayout();
				//显示全部操作点列表  
				addOpt();
				$resInfo.show();
				
			},
			formatter : function(node) {
				var text = node.text;
				text += buildTreeButton(node);
				return text;
			},
			loadFilter : function(menus, parent) {
				for (var i = 0, len = menus.length; i < len; i++) {
					formatMenu(menus[i]);
				}
				return menus;
			}
		});

		function buildTreeButton(node) {
			var html = [];
			html.push(' <a href="#" onclick="' + FunUtil.createFun(window, 'addChildNode', node) + ' return false;">[添加子节点]</a> ');
			if (node.children.length == 0) {
				html.push(' <a href="#" onclick="' + FunUtil.createFun(window, 'delSysRes', node) + ' return false;">[删除节点]</a> ');
			}

			return html.join('');
		}
		
		function showAddNode(node){
			 addChildNode(node);
			 if (typeof window.event.preventDefault === "function") {
					 window.event.preventDefault();
					 window.event.stopPropagation()
	            } else {
	            	 window.event.returnValue = false;
	            	 window.event.cancelBubble = true;
	            }
			
			return false;
		}
		//-----------------------------------------------------------------------------
		//--------------------------------- 封装工具  -------------------------------------	
		//-----------------------------------------------------------------------------	
		var FunUtil = (function() {
		    var b = 0;
		    var a = [];
		    return {
		        createFun: function(h, f) {
		            var e = b++;
		            var d = [];
		            for (var g = 2, c = arguments.length; g < c; g++) {
		                d.push(arguments[g])
		            }
		            a[e] = (function(k, j, i) {
		                return function() {
		                    k[j].apply(k, i)
		                }
		            }(h, f, d));
		            return "FunUtil._runFun(event," + e + ");"
		        },
		        _runFun: function(f, c) {
		            var d = a[c];
		            d();
		            if (typeof f.preventDefault === "function") {
		                f.preventDefault();
		                f.stopPropagation()
		            } else {
		                f.returnValue = false;
		                f.cancelBubble = true
		            }
		        }
		    }
		})();
		var MsgUtil = {
			    topMsg: function(b, a) {
			        a = a || "提示";
			        this.getJQ().messager.show({
			            title: a,
			            msg: b,
			            showSpeed: 300,
			            style: {
			                right: "",
			                top: document.body.scrollTop + document.documentElement.scrollTop,
			                bottom: ""
			            }
			        })
			    },
			    alert: function(c, b, a) {
			        b = b || "提示";
			        a = a || "info";
			        if (c && c.length > 1000) {
			            this.getJQ().messager.show({
			                title: b,
			                msg: '<div style="height:300px;overflow-y: auto; overflow-x:hidden;">' + c + "</div>",
			                width: 600,
			                height: 350,
			                showType: null,
			                timeout: 0,
			                style: {
			                    right: "",
			                    bottom: ""
			                }
			            })
			        } else {
			            this.getJQ().messager.alert(b, c, a)
			        }
			    },
			    error: function(b, a) {
			        a = a || "错误";
			        this.alert(b, a, "error")
			    },
			    confirm: function(b, c, a) {
			        a = a || "确认";
			        this.getJQ().messager.confirm(a, b, function(d) {
			            if (d) {
			                c()
			            }
			        })
			    },
			    getJQ: function() {
			        return top.$ || $
			    }
		};
		//-----------------------------------------------------------------------------
		//---------------------------------  end  -------------------------------------	
		//-----------------------------------------------------------------------------	
		function initLayout() {
			if (!layoutInit) {
				layoutInit = true;
				$setOptDiv.layout();
			}
		}

		function formatMenu(data) {
			if (data) {
				data.attributes = {
					url : data.url,
					systemResourceId : data.id
				}
			}
			var children = data.children;
			if (children && children.length > 0) {
				data.isParent = true;
				for (var i = 0, len = children.length; i < len; i++) {
					formatMenu(children[i]);
				}
			}
		}

		function saveRes() {
			var id = selectedNode ? selectedNode.id : '';
			var url = ctx + 'updateAuthSystemResource.do?id=' + id;
			$sysForm.form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(resultTxt) {
					var result = $.parseJSON(resultTxt);
					if(result.success){
						MsgUtil.topMsg('修改成功');
						$sysResTree.tree('reload');
					}
				}
			});
		}

		function delSysRes(node) {
			var self = this;
			if (node) {
				var msg = '确定要删除<strong>' + node.sysResName + '</strong>吗?';
				$.messager.confirm('Confirm', msg, function(r) {
					if (r) {
						$.ajax({
							url:ctx + 'delAuthSystemResource.do',
							type:'post',
							data:{id:node.id},
							dataType:'json',
							success:function(result){
								if(result.success){
									MsgUtil.topMsg('删除成功');
									$sysResTree.tree('reload'); // reload the user data
									reset();
								}
							}
						})
					}
				});
			}
		}

		function reset() {
			selectedNode = null;
			$resInfo.hide();
			$contTip.show();
		}

		function addRootMenu() {
			$('#parentId').val(0);
			this.$addRootDl.dialog('open').dialog('setTitle', '添加根节点');
			this.$addRootfm.form('reset');
		}

		function addChildNode(node) {
			$('#parentId').val(node.id);//这里的parentId的值应该为id的值 
			this.$addRootDl.dialog('open').dialog('setTitle', '添加子节点');
			this.$addRootfm.form('reset');
		}

		function saveRootMenu() {
			if($addRootfm.form('validate')){  
				var sysResName = $("input[name='sysResName']").get(1).value;
				var url = $("input[name='url']").get(1).value;
				$.ajax({
					url:ctx + 'addAuthSystemResource.do',
					type:'post',
					data:{parentId:$("#parentId").val(),sysResName:sysResName,url:url},
					dataType:'json',
					success:function(data){
						if(data.success){
							MsgUtil.topMsg('添加成功');
							$addRootDl.dialog('close');
							$sysResTree.tree('reload'); 
						}else{
							MsgUtil.topMsg('添加失败');
							$addRootDl.dialog('close');
							$sysResTree.tree('reload'); 
						}
					}
				})
			}
		}

		// --------------设置操作点--------------
		
	
		var dg;
		var $dg = $('#dg');
		dg=$('#dg').datagrid({
           fit:true, 
           border : false,
           collapsible : false,
           pagination : true,
           singleSelect : true,
           url:ctx+'listAuthSystemFunction.do',
           toolbar: '#toolbar',
           columns:[[
                {field:'id',title:'id',hidden:true}
       			,{field:'operateCode',title:'操作代码'}
       			,{field:'operateName',title:'操作名称'}
       			,{field:'newField',title:'操作',formatter: function(value, row, index){
       			    value="<a href='javascript:void(0)' onclick='delPermission("+row.id+")' >删除</a>";
       			    return value;
       			}}
               ]],
           onLoadSuccess : function(data) {
               
               
           },
           onClickRow : function(index, row) {
           }
       });
		function delPermission(id) {
			$.ajax({
				url:ctx + 'delAuthSystemFunction.do',
				type:'post',
				data:{id:id},
				dataType:'json',
				success:function(data){
					if(data.success){
						MsgUtil.topMsg('删除成功');
						reloadOptCode();
					}else{
						MsgUtil.topMsg('删除失败');
						reloadOptCode();

					}
				}
			})
		}
		function reloadOptCode() {
			if (selectedNode) {
				$dg.datagrid({
					url : ctx + 'listAuthSystemFunctionBySearch.do?systemResourceIdSch=' + selectedNode.id
				})
			}
		}

		function addOpt() {
			$setOptDiv.layout('expand', 'east');
			//$(".layout-button-right").remove();
		}
		
		// --------------读取操作点列表--------------
		var optAddGrid;
		var $optAddGrid = $('#optAddGrid');
		optAddGrid=$('#optAddGrid').datagrid({
	           fit:true, 
	           border : false,
	           collapsible : false,
	           pagination : true,
	           singleSelect : true,
	           rownumbers : true,
	           url:ctx+'listAuthSytemsOperate.do',
	           columns:[[
	                {field:'id',title:'id',hidden:true}
	       			,{field:'operateCode',title:'操作代码'}
	       			,{field:'operateName',title:'操作名称'}
	       			,{field:'newField',title:'操作',formatter: function(value, row, index){
	       			    //value="<a href='javascript:void(0)' onclick='addOperateById("+row.id+",'"+row.operateCode+"')' >添加</a>";
	       			    value="<a href='javascript:void(0)' onclick=\"addOperateById('"+row.operateCode+"')\">添加</a>";
	       			    return value;
	       			}}
	               ]],
	           onLoadSuccess : function(data) {
	               
	               
	           },
	           onClickRow : function(index, row) {
	           }
	       });
		function addOperateById(operateCode) {
			var id=$("INPUT[name='id']").val();
			$.post(ctx + 'addAuthSystemFunction.do', {id:id,operateCode:operateCode}, function(data){
				var data = $.parseJSON(data);
				if(data.success){
					/*
					$.messager.show({
						title : message.title.normal,
						msg : '添加成功',
						timeout : message.timeout,
						showType : message.showType
					});*/
					MsgUtil.topMsg('添加成功');
				}else{
					/*
					$.messager.show({
						title : message.title.normal,
						msg : data.message,
						timeout : message.timeout,
						showType : message.showType
					});*/
					MsgUtil.topMsg(data.message);
				}
				$dg.datagrid('reload');
			});
		}
		function searchOpt() {
			var data = {
				operateCodeSch : $.trim($('#operateCodeSch').val()),
				operateNameSch : $.trim($('#operateNameSch').val())
			};
			$optAddGrid.datagrid('reload', data);
		}

		function initOptAddGird() {
			if (!this.optAddInit) {
				this.optAddInit = true;
				$optAddGrid.datagrid({
					url : ctx + 'listAuthSytemsOperate.do'
				});
			}
		}

	</script>
</body>
</html>