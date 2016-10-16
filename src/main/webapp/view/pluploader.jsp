<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>信息管理</title>
</head>
<body>
<jsp:include page="ueditorlib.jsp"></jsp:include>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false">
			<div id="tabs" class="easyui-tabs" data-options="fit:false">
				<div title="文件信息" style="padding: 10px;">
					<div data-options="region:'center',border:false">
						<div id="toolbar" style="display: none;">
							<form id="searchForm">
								<input type="hidden" name="moduleIdSch" value="<%=request.getParameter("moduleId")%>"> <input type="hidden" name="funcIdSch" value="<%=request.getParameter("funcId")%>">
								<table>
									<tr>
										<td>文件名称</td>
										<td><input name="clientNameSch" class="textbox" style="width: 120px; height: 22px;" /></td>
										<td>创建时间</td>
										<td><input name="createdTimeStartSch" class="easyui-datebox" style="width: 100px" /> - <input name="createdTimeEndSch" class="easyui-datebox" style="width: 100px" /></td>
										<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',fw.serializeObject($('#searchForm')));">过滤</a> <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm not:hidden').val('');grid.datagrid('load',queryParams);">重置过滤</a></td>
										<td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
									</tr>
								</table>
							</form>
						</div>
						<div style="height: 350px;">
							<table id="grid"></table>
						</div>
					</div>
				</div>

				<div title="上传文件">
					<form id="formId" action="Submit.action" method="post">
						<div id="uploader">
							<p>您的浏览器未安装 Flash, Silverlight, Gears, BrowserPlus 或者支持 HTML5 .</p>
						</div>
						<div style="text-align: center; padding: 5px;">
							<a href="#" id="Reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width: 80px">重新上传</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
    //删除操作
    $("#btn_dele").click(function() {
        var rows = $('#grid').datagrid('getSelections');
        if (rows <= 0) {
            $.messager.show({
                title: message.title.normal,
                msg: message.grid_select,
                timeout: message.timeout,
                showType: message.showType
            });
        } else {
            $.messager.confirm(message.title.askTitle, message.dele_comfirm, function(r) {
                if (r) {
                	var idValue = [];
                	 for (var i = 0, len = rows.length; i < len; i++) {
                		 idValue.push(rows[i].id);
                	 }
                    $.post(ctx + "delSysUploadByBatch.do", {
                        ids: idValue.join(",")
                    }, function(result) {
                        if (result.success) {
                            $.messager.show({
                                title: message.title.normal,
                                msg: message.dele_success,
                                timeout: message.timeout,
                                showType: message.showType
                            });
                            grid.datagrid('reload');
                        } else {
                            $.messager.show({
                                title: message.title.normal,
                                msg: message.dele_fail,
                                timeout: message.timeout,
                                showType: message.showType
                            });
                        }
                    }, 'json');
                }
            });
        }
    })
	
		var closeDialog = function($dialog) {
			$dialog.dialog('destroy');
		}
		
		var submitForm = function($dialog, $btn)  {
			var ids = [];
			var rows = $('#grid').datagrid('getSelections');
			
			if(rows.length>0){
				var options = "<option>选择文件 ( 选中了 "+ rows.length +" 个 )</option>";
				for(var i=0; i<rows.length; i++){
					ids.push(rows[i].id);
					options +="<option>" + rows[i].clientName + "</option>";
				}
	
				$btn.next('.selectFilesValue').val(ids.join(','));
				$btn.prev('.selectFiles').html(options);
	
				if($btn.next('.selectFilesValue').attr('click')!='undefined'){
					$btn.next('.selectFilesValue').click();
				}
	
				$dialog.dialog('destroy');
			}else{
				$.messager.show({
					title : message.title.normal,
					msg : message.grid_select,
					timeout : message.timeout,
					showType : message.showType
				});
			}
		};
		
		var returnFormValue = function($dialog){
			var rows = $('#grid').datagrid('getSelections');
			if(rows.length>0){
				$dialog.dialog('destroy');
				return rows;
			}else{
				$.messager.show({
					title : message.title.normal,
					msg : message.grid_select,
					timeout : message.timeout,
					showType : message.showType
				});
			}		
		}

		var clearSelectedFiles = function($dialog, $btn)  {
			var loadData=[];
			$btn.next('.selectFilesValue').val('');
			$btn.prev('.selectFiles').html('<option>选择文件</option>');

			if($btn.next('.selectFilesValue').attr('click')!='undefined'){
				$btn.next('.selectFilesValue').click();
			}

			$dialog.dialog('destroy');
		};


		var queryParams={
				'moduleIdSch' : '<%=request.getParameter("moduleId")%>',
				'funcIdSch' : '<%=request.getParameter("funcId")%>'
		}
		var userId = <%=request.getParameter("userId")%>;
		if(userId !=null){
			queryParams['userIdSch'] = userId;
		}
		$(function() {
			var gird;
			grid = $('#grid').datagrid({
				fit : true,
				border : false,
				collapsible : false,
				pagination : true,
				singleSelect : true,
				rownumbers : true,
				sortName : 'id',
				idField : 'id',
				sortOrder : 'desc',
				toolbar : '#toolbar',
				url : ctx + 'listSysUpload.do',
				queryParams : queryParams,
				columns : [ [ {
					field : 'ck',
					checkbox : true
				}, {
					field : 'clientName',
					title : '文件名称',
					width : 300
				}, {
					field : 'createdTime',
					title : '添加时间',
					width : 120,
					align : 'center',
					sortable : true
				}, {
					field : 'fileSize',
					title : '文件大小',
					width : 80,
					align : 'center',
					sortable : true
				} ] ],
 				onLoadSuccess : function(data) {
					$('.iconImg').attr('src', fw.pixel_0);
					
					var checkedids = '<%=request.getParameter("ids")%>';
					$.each(data.rows, function(index, item) {
						if ($.inArray(item.id.toString(), checkedids.split(',')) != -1) {
							grid.datagrid('checkRow', index);
						}
					});

				}
			});

 			function plupload() {
				$("#uploader").pluploadQueue({
					// General settings   
					runtimes : 'html5,html4,flash',
					url : ctx + 'fileUpload.do',
					max_file_size : '100mb',
					unique_names : true,
					chunk_size : '2mb',
					// Specify what files to browse for   
					filters : [ {
						title : "Image files",
						extensions : "jpg,gif,png,jpeg"
					}, {
						title : "Zip files",
						extensions : "zip,rar"
					}, {
						title : "Document files",
						extensions : "doc,ppt,docx,xlsx,pptx"
					}, {
						title : "Media files",
						extensions : "mp3,mp4"
					} ],
					resize : {
						width : 640,
						height : 480,
						quality : 90
					},
					// Flash settings   
					flash_swf_url : ctx + 'assets/plupload/plupload.flash.swf',
					// Silverlight settings   
					silverlight_xap_url : ctx + 'assets/plupload/plupload.silverlight.xap',
					// 参数   
					init : {
						FileUploaded : function(up, file, info) {
						},
						BeforeUpload : function(up, file) {
							up.settings.multipart_params = {
								'filename' : file.name,
								'moduleId' : '<%=request.getParameter("moduleId")%>',
								'funcId' : '<%=request.getParameter("funcId")%>'
							};
						},
						UploadComplete : function(up, files) {
							$("#reset_searchFilter").click();
							$('#Reload').linkbutton('enable');
							$('#Reload').click();
						},
						UploadProgress : function(up, file) {
							$('#Reload').linkbutton('disable');
						},
						Error : function(up, args) {
							if (args.file) {
								//log('[error]', args, "File:", args.file);
							} else {
								//log('[error]', args);
							}
						}
					}
				});
			}
			plupload();

			$('#Reload').click(function() {
				plupload();
			}); 

		});
	</script>
</body>
