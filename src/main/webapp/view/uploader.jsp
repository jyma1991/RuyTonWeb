<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value='<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/" %>'/>
<title>信息管理</title>
<body>
<script type="text/javascript" src="${ctx}assets/qnupload/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}assets/qnupload/bootstrap/css/bootstrap.css" />
        <div data-options="region:'center',border:false">
            <div id="tabs" class="easyui-tabs" data-options="fit:false">
                <div title="文件信息" >
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
                        <div id="uploader">
                                    <table class="table table-striped table-hover text-left"   style="display:none" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th class="col-md-4">文件名</th>
                                                <th class="col-md-2">大小</th>
                                                <th class="col-md-6">详情</th>
                                            </tr>
                                        </thead>
                                        <tbody id="fsUploadProgress">
                                        </tbody>
                                    </table>
                        </div>
                        <div id="container" style="text-align: center; padding: 5px;">
                            <a href="#" id="pickfiles" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:90px;height:30px">上传文件</a>
                        </div>
                        <div style="display:none" id="success" class="col-md-12">
                            <div class="alert-success"> 队列全部文件处理完毕</div>
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
    
    var domain = "http://source.ruiyantong.com/";
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
        	if(1==<%=request.getParameter("source")%>){
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
            			width : 200
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
            		}, {
            			field : 'fileFullPath',
            			title : '来源',
            			width : 200,
            			align : 'center',
            			sortable : true
            		}  ] ],
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
        	}
        	else{
		        	grid = $('#grid').datagrid({
		        		fit : true,
		        		border : false,
		        		collapsible : false,
		        		pagination : true,
		        		singleSelect : false,
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
	        	}
        		function qnupload() {
        			 var uploader = Qiniu.uploader({
                        runtimes: 'html5,flash,html4',    //上传模式,依次退化
                        browse_button: 'pickfiles',       //上传选择的点选按钮，**必需**
                        uptoken_url: ctx+'getQiniuPublicToken.do',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
                        //uptoken : '', //若未指定uptoken_url,则必须指定 uptoken ,uptoken由其他程序生成
                        unique_names: true, // 默认 false，key为文件名。若开启该选项，SDK为自动生成上传成功后的key（文件名）。
                        save_key: true,   // 默认 false。若在服务端生成uptoken的上传策略中指定了 `sava_key`，则开启，SDK会忽略对key的处理
                        domain: domain,   //bucket 域名，下载资源时用到，**必需**
                        get_new_uptoken: false, 
                        container: 'container',           //上传区域DOM ID，默认是browser_button的父元素，
                        max_file_size: '100mb',           //最大文件体积限制
                        flash_swf_url: ctx + 'assets/plupload/Moxie.swf',  //引入flash,相对路径
                        max_retries: 3,                   //上传失败最大重试次数
                        dragdrop: true,                   //开启可拖曳上传
                        drop_element: 'container',        //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
                        chunk_size: '4mb',                //分块上传时，每片的体积
                        auto_start: true,                 //选择文件后自动上传，若关闭需要自己绑定事件触发上传
                        init: {
                        	'FilesAdded': function(up, files) {
                                $('table').show();
                                $('#success').hide();
                                plupload.each(files, function(file) {
                                    var progress = new FileProgress(file, 'fsUploadProgress');
                                    progress.setStatus("等待...");
                                    progress.bindUploadCancel(up);
                                });
                            },
                            'BeforeUpload': function(up, file) {
                                var progress = new FileProgress(file, 'fsUploadProgress');
                                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                                if (up.runtime === 'html5' && chunk_size) {
                                    progress.setChunkProgess(chunk_size);
                                }
                            },
                            'UploadProgress': function(up, file) {
                                var progress = new FileProgress(file, 'fsUploadProgress');
                                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                                progress.setProgress(file.percent + "%", file.speed, chunk_size);
                            },
                            'UploadComplete': function() {
                                $('#success').show();
                                $("#reset_searchFilter").click();
                            },
                            'FileUploaded': function(up, file, info) {
                            	$("#reset_searchFilter").click();
                            	info = $.parseJSON(info);
                            	var url = domain+info.key;
                                var progress = new FileProgress(file, 'fsUploadProgress');
                                progress.setComplete(up, info); 
                                var entity = {
        								'moduleId' : '<%=request.getParameter("moduleId")%>',
        								'funcId' : '<%=request.getParameter("funcId")%>',
        								'clientName': file.name,
        								'serverName':info.key,
        								'fileExt':info.ext,
        								'fileSize':Math.ceil(info.fsize/1024),
        								'fileFullPath':url,
        						};
                                if(info.avinfo !=null){
                                	entity['duration'] = info.avinfo.duration;
                                }
                                if(info.imageInfo!=null){
                                	entity['fileHeight'] = info.imageInfo.height;
                                	entity['fileWidth'] = info.imageInfo.width;
                                }
                                console.log(entity);
                                $.post(ctx+"addSysUpload.do",entity, function(result){
                                	console.log(result);
                                });
                            },
                            'Error': function(up, err, errTip) {
                                $('table').show();
                                var progress = new FileProgress(err.file, 'fsUploadProgress');
                                progress.setError();
                                progress.setStatus(errTip);
                            },
                            'Key': function(up, file) {
                                // 若想在前端对每个文件的key进行个性化处理，可以配置该函数
                                // 该配置必须要在 unique_names: false , save_key: false 时才生效
                                var key = "";
                                // do something with key here
                                return key
                            }
                        }
                    });
        			 
        			 uploader.bind('FileUploaded', function() {
        			    });
        			    $('#container').on(
        			        'dragenter',
        			        function(e) {
        			            e.preventDefault();
        			            $('#container').addClass('draging');
        			            e.stopPropagation();
        			        }
        			    ).on('drop', function(e) {
        			        e.preventDefault();
        			        $('#container').removeClass('draging');
        			        e.stopPropagation();
        			    }).on('dragleave', function(e) {
        			        e.preventDefault();
        			        $('#container').removeClass('draging');
        			        e.stopPropagation();
        			    }).on('dragover', function(e) {
        			        e.preventDefault();
        			        $('#container').addClass('draging');
        			        e.stopPropagation();
        			    });       
        			    
        			    $('body').on('click', 'table button.btn', function() {
        			        $(this).parents('tr').next().toggle();
        			    });
        
        
        			    var getRotate = function(url) {
        			        if (!url) {
        			            return 0;
        			        }
        			        var arr = url.split('/');
        			        for (var i = 0, len = arr.length; i < len; i++) {
        			            if (arr[i] === 'rotate') {
        			                return parseInt(arr[i + 1], 10);
        			            }
        			        }
        			        return 0;
        			    };
        
        			    $('#myModal-img .modal-body-footer').find('a').on('click', function() {
        			        var img = $('#myModal-img').find('.modal-body img');
        			        var key = img.data('key');
        			        var oldUrl = img.attr('src');
        			        var originHeight = parseInt(img.data('h'), 10);
        			        var fopArr = [];
        			        var rotate = getRotate(oldUrl);
        			        if (!$(this).hasClass('no-disable-click')) {
        			            $(this).addClass('disabled').siblings().removeClass('disabled');
        			            if ($(this).data('imagemogr') !== 'no-rotate') {
        			                fopArr.push({
        			                    'fop': 'imageMogr2',
        			                    'auto-orient': true,
        			                    'strip': true,
        			                    'rotate': rotate,
        			                    'format': 'png'
        			                });
        			            }
        			        } else {
        			            $(this).siblings().removeClass('disabled');
        			            var imageMogr = $(this).data('imagemogr');
        			            if (imageMogr === 'left') {
        			                rotate = rotate - 90 < 0 ? rotate + 270 : rotate - 90;
        			            } else if (imageMogr === 'right') {
        			                rotate = rotate + 90 > 360 ? rotate - 270 : rotate + 90;
        			            }
        			            fopArr.push({
        			                'fop': 'imageMogr2',
        			                'auto-orient': true,
        			                'strip': true,
        			                'rotate': rotate,
        			                'format': 'png'
        			            });
        			        }
        
        			        $('#myModal-img .modal-body-footer').find('a.disabled').each(function() {
        
        			            var watermark = $(this).data('watermark');
        			            var imageView = $(this).data('imageview');
        			            var imageMogr = $(this).data('imagemogr');
        
        			            if (watermark) {
        			                fopArr.push({
        			                    fop: 'watermark',
        			                    mode: 1,
        			                    image: '',
        			                    dissolve: 100,
        			                    gravity: watermark,
        			                    dx: 100,
        			                    dy: 100
        			                });
        			            }
        
        			            if (imageView) {
        			                var height;
        			                switch (imageView) {
        			                    case 'large':
        			                        height = originHeight;
        			                        break;
        			                    case 'middle':
        			                        height = originHeight * 0.5;
        			                        break;
        			                    case 'small':
        			                        height = originHeight * 0.1;
        			                        break;
        			                    default:
        			                        height = originHeight;
        			                        break;
        			                }
        			                fopArr.push({
        			                    fop: 'imageView2',
        			                    mode: 3,
        			                    h: parseInt(height, 10),
        			                    q: 100,
        			                    format: 'png'
        			                });
        			            }
        
        			            if (imageMogr === 'no-rotate') {
        			                fopArr.push({
        			                    'fop': 'imageMogr2',
        			                    'auto-orient': true,
        			                    'strip': true,
        			                    'rotate': 0,
        			                    'format': 'png'
        			                });
        			            }
        			        });
        
        			        var newUrl = Qiniu.pipeline(fopArr, key);
        
        			        var newImg = new Image();
        			        img.attr('src', '..\\assets\\qnupload\\loading.gif');
        			        newImg.onload = function() {
        			            img.attr('src', newUrl);
        			            img.parent('a').attr('href', newUrl);
        			        };
        			        newImg.src = newUrl;
        			        return false;
        			    });
        
        	}
        	qnupload(); 
        });
    </script>
</body>