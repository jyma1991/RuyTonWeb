<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/view/taglib.jsp"></jsp:include>
<jsp:include page="/view/ueditorlib.jsp"></jsp:include>
<title>系统分类</title>
<body>
	<!-- Layout Begin -->
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',split:true,border:false" style="height: 40px;">
			<table>
				<tr>
					<td>所属分类 <input class="easyui-combobox" style="width: 120px" id="classTypeName" name="classTypeName"></td>
					<td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
					<td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
					<td><a id="btn_formdesign" class="easyui-linkbutton" data-options="group:'g1',iconCls:'ext-icon-application_form_edit'">表单设计</a></td>
					<td><a onclick="redoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-resultset_next'">展开</a></td>
					<td><a onclick="undoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-resultset_previous'">折叠</a></td>
					<td><a onclick="grid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
				</tr>
			</table>
		</div>

		<div data-options="region:'center',border:false">
			<table id="grid"></table>
		</div>

		<!-- East Begin -->
		<div data-options="region:'east',split:true,border:false" style="width: 50%; padding: 5px;">
			<!-- Tabs Begin -->
			<div id="tt" class="easyui-tabs" data-options="plain:true" height: auto">
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
										<td><input id="scId" name="id" readonly="readonly" class="textbox" style="height: 22px;" /></td>
									</tr>
									<tr>
										<th>资源名称</th>
										<td><input name="className" class="textbox easyui-validatebox" style="height: 22px;" required="true" /></td>
									</tr>
									<tr>
										<th>资源描述</th>
										<td><textarea name="description"></textarea></td>
									</tr>
									<tr>
										<th>资源地址</th>
										<td><input name="url" class="textbox" style="height: 22px;" /></td>
									</tr>
									<tr>
										<th>弹出方式</th>
										<td><select name="urltarget" class="easyui-combobox" data-options="panelHeight:'auto'">
												<option value="">请选择弹出方式</option>
												<option value="_self">当前页面</option>
												<option value="_blank">弹出页面</option>
										</select></td>
									</tr>
									<tr>
										<th>排序</th>
										<td><input name="sortId" class="easyui-validatebox easyui-numberspinner" data-options="min:0,max:100,required:true" value="0" style="width: 50px;" /></td>
									</tr>
									<tr>
										<th>上级资源</th>
										<td><select id="parentId" name="parentId">
										</select> <a href="#" onclick="$('#parentId').combotree('clear');">清除</a></td>
									</tr>
									<tr>
										<th>分类属性</th>
										<td><input name="isshow" type="checkbox" value="1" />是否显示 
											<input name="issystem" type="checkbox" value="1" hidden="hidden"/>
										    <input name="isindex" type="checkbox" value="1" hidden="hidden"/>
										    <input name="islist" type="checkbox" value="1" />是否列表

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
										<input  id= "icon" name= "icon" class = "textbox"  readonly="readonly" style ="height:22px" ></input>
										<a id="addIcons"  class="easyui-linkbutton" data-options="iconCls:'icon-save'">选择图标</a> 
										</td>
									</tr>
									<tr>
										<th>添加时间</th>
										<td><input name="createdTime" data-options="disabled:true" class="easyui-datetimebox" style="width: 180px;" /></td>
									</tr>
									<tr>
										<th>修改时间</th>
										<td><input name="editedTime" data-options="disabled:true" class="easyui-datetimebox" style="width: 180px;" /></td>
									</tr>
									<tr>
										<th>操作</th>
										<td><a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td>
									</tr>
								</tbody>
							</table>
						</fieldset>
					</form>
					
					<div id="addRoleDlg" class="easyui-dialog" style="width: 500px; height: 440px;" title=" " data-options="onClose:Globle.clearPanel()" closed="true" modal="true">
					<div id="roleFrame"></div>
					</div>

					
					
				</div>

				<div title="表单定义" id="autoFormDesign">
					<fieldset>
						<legend>扩展字段清单</legend>
						<table class="table" id="formDesignTable" width="100%">
							<tbody id="formdesign"></tbody>
						</table>

						<br />

						<!-- 通过hidden域editMode值来区别是添加还是修改操作 -->
						<input type="hidden" name="editMode" id="editMode" value="-1" />
						<table class="table" width="100%">
							<tbody>
								<tr>
									<th>字段类型</th>
									<td><select id="ftype" name="ftype" class="easyui-combobox" data-options="panelHeight:'auto'" style="width: 120px;">
											<option value="">请选字段类型</option>
											<option value="1">文本框</option>
											<option value="2">编辑框</option>
											<option value="3">下拉框</option>
											<option value="4">复选框</option>
											<option value="5">选择文件</option>
											<option value="6">选择日期</option>
											<option value="7">富文本框</option>
									</select></td>
								</tr>
								<tr>
									<th>是否必选</th>
									<td><input type="checkbox" name="frequire" id="frequire" value="0" /></td>
								</tr>
								<tr>
									<th>验证类型</th>
									<td><select id="fchecktype" name="fchecktype" class="easyui-combobox" data-options="panelHeight:'auto'" style="width: 170px;">
											<option value="0">请选验证类型(默认不验证)</option>
											<option value="1">整数类型</option>
											<option value="2">邮箱类型</option>
											<option value="3">网址类型</option>
											<option value="4">电话类型</option>
											<option value="5">手机类型</option>
											<option value="6">电话手机</option>
											<option value="7">邮编类型</option>
											<option value="8">货币类型</option>
											<option value="9">英文类型</option>
											<option value="10">中文类型</option>
											<option value="11">QQ号码</option>
									</select></td>
								</tr>

								<tr>
									<th>字段名称</th>
									<td><input type="text" id="ename" name="ename"> (3-12个字母字串)</td>
								</tr>
								<tr>
									<th>字段说明</th>
									<td><input type="text" id="cname" name="cname" /></td>
								</tr>
								<tr>
									<th>字段数据</th>
									<td><textarea cols="40" rows="5" id="fvalue" name="fvalue" disabled="disabled" style="background: #f5f5f5;"></textarea><br />(每行为一个选择项)</td>
								</tr>
								<tr>
									<th>操作</th>
									<td><a href="#" class="easyui-linkbutton" id="fieldEdit" data-options="iconCls:'icon-add'">确认字段</a> <a href="#" class="easyui-linkbutton" id="submitNow" data-options="iconCls:'icon-ok'">确认提交</a></td>
								</tr>
							</tbody>
						</table>

					</fieldset>
				</div>
				<!-- 自定义表单 End -->
			</div>
			<!-- Tabs End -->
		</div>
		<!-- East End -->
	</div>
	<!-- Layout End -->

	<script type="text/javascript">
	
	 //绑定上传按钮功能
    $(".uploader").on('click', uploaderFun);
	function EditRecord(row){
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
	}
	
	
	
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
            url = '../../uploader.jsp' + ids + '&moduleId=sys&funcId=SysClass';
        } else {
            url = '../../uploader.jsp?moduleId=sys&funcId=SysClass';
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
		$('#roleFrame').html('<iframe id="iconFrame"  src= "SysClassIcon.jsp?pigname='+ mao + '" scrolling="no" frameborder="0" style="width:100%;height:400px;"></iframe>');
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

		/*-----------------------------------------------------------------------------
		 * 自字义表单操作部分
		 * Begin
		 * 自字义字段的结构为：英文名:中文名:是否必填写:验证类型:字段类型+多值1|多值2|多值3
		 -----------------------------------------------------------------------------*/
		$("#fieldEdit").click(function() {
			EditFields();
		})

		$("#submitNow").click(function() {
			//对formDesignTable的tr内容进行遍历操作，并将tr中的value属性值转化为数组内容
			
			var fieldString = new Array();
			
			$("#formDesignTable tr").each(function() {
				fieldString.push($(this).attr('value'));
			});
			//将字段数组转成字符串，并提交到后台进行formobjectlist字段的更新，关闭窗口

			var url = ctx + "updateSysClass.do";
			$.post(url, {
				"formObjectList" : fieldString.toString(),
				"id" : $("#gridRowId").val()
			}, function(result) {

				if (result.success) {
					$.messager.show({
						title : message.title.normal,
						msg : message.update_success,
						timeout : message.timeout,
						showType : message.showType
					});
				}
			});
		})

		//是否为英文名称，长度3-12字母长度
		function isFieldEName(s) {
			var patrn = /^[a-zA-Z]{3,12}$/;
			if (!patrn.exec(s)) {
				return false;
			}
			return true;
		}

		//添加、修改字段操作
		function EditFields() {
			//类型下拉框必须有值
			if ($('#ftype').combobox('getValue').trim().length != 0) {
				//ename判断
				if (isFieldEName($("#ename").val().trim()) == false) {
					$.messager.alert('信息提示', '字段名称只能输入3-12个字母字串!');
					return false;
				}

				//cname判断
				if ($("#cname").val().trim().length == 0) {
					$.messager.alert('信息提示', '字段说明不可为空!');
					return false;
				}

				//将表单数据转化为数组，并更改其分隔符号为:号
				fields = new Array();
				fields[0] = $("#ename").val().trim();
				fields[1] = $("#cname").val().trim();
				if ($("#frequire").prop("checked")) {
					fields[2] = 1;
				} else {
					fields[2] = 0;
				}
				fields[3] = $("#fchecktype").combobox('getValue').trim();
				fields[4] = $('#ftype').combobox('getValue').trim();
				fields = fields.join(":");

				//在下拉及复选条件下，获取其值，并转化分隔符号为|
				if ($('#ftype').combobox('getValue') == 3 || $('#ftype').combobox('getValue') == 4) {
					fvalue = $('#fvalue').val().split('\n');
					fvalue = fvalue.join("|");
					fields = fields + "+" + fvalue;
				}

				o = $("#formdesign");

				//将表单数据转化为数组内容，并调用getHtml函数
				html = getHtml(fields.split(','));

				if ($("#editMode").val() == -1) {
					//新增字段模式
					o.append(html);
				} else {
					//修改字段模式
					$('#formdesign tr').eq($("#editMode").val()).replaceWith(html);
				}

				//清空表单
				$("#ftype").combobox('setValue', '');
				$("#fchecktype").combobox('setValue', 0);
				$("#ename").val('');
				$("#cname").val('');
				$("#frequire").val(0);
				$("#frequire").prop("checked", false);
				$("#fvalue").val('');
				$("#editMode").val(-1);

				//按钮操作部分的绑定操作
				btnOperates();
			} else {
				$.messager.alert('信息提示', '请选字段类型!');
			}
		}

		function btnOperates() {
			//在html方法调用以后，元素的事件绑定操作需要在后面进行，也就是说以下代码必须放置在该位置
			$(".up_btn").on("click", function() {
				$(this).closest('tr').insertBefore($(this).closest('tr').prev());
			});

			$('.down_btn').on("click", function() {
				$(this).closest('tr').insertAfter($(this).closest('tr').next());
			})

			$('.edit_btn').on("click", function() {
				//获取当前tr的index索引位置
				$("#editMode").val($(this).closest('tr').index());

				arrSingleField = $(this).closest('tr').attr('value').split(':');
				//英文字段名称
				eName = arrSingleField[0];
				//中文字段说明
				cName = arrSingleField[1];
				//是否必选
				fRequire = arrSingleField[2];
				//验证类型
				fCheckType = arrSingleField[3];
				//字段类型，数字型
				fType = parseInt(arrSingleField[4].split('+')[0]);
				//字段的值
				fValue = arrSingleField[4].split('+')[1];

				//编辑模式时进行表单赋值操作
				$('#ftype').combobox('setValue', fType);
				$('#fchecktype').combobox('setValue', fCheckType);
				if (fRequire == 1) {
					$("#frequire").prop("checked", true);
				} else {
					$("#frequire").prop("checked", false);
				}
				$("#ename").val(eName);
				$("#cname").val(cName);
				if (fType == 3 || fType == 4) {
					$("#fvalue").val(fValue.split('|').join('\n'));
				} else {
					$("#fvalue").val(fValue);
				}

			})

			$('.delete_btn').on("click", function() {
				trObj = $(this).closest('tr');
				$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
					if (r) {
						trObj.remove();
					}
				});
			})
		}

		function getHtml(arrFields) {
			html = "";
			for (var i = 0; i < arrFields.length; i++) {
				//单个字段
				arrSingleField = arrFields[i].split(':');
				//英文字段名称
				eName = arrSingleField[0];
				//中文字段说明
				cName = arrSingleField[1];
				//是否必选
				fRequire = arrSingleField[2];
				if (fRequire == 1) {
					fRequireString = "<span style='color:red'>*</span>";
				} else {
					fRequireString = "";
				}
				//验证类型
				fCheckType = arrSingleField[3];
				arrCheckType = new Array('没有类型', '整数类型', '邮箱类型', '网址类型', '电话类型', '手机类型', '电话手机', '邮编类型', '货币类型', '英文类型', '中文类型', 'QQ号码');

				fCheckString = arrCheckType[fCheckType] + fRequireString;
				//字段类型，数字型
				fType = parseInt(arrSingleField[4].split('+')[0]);
				//字段的值
				fValue = arrSingleField[4].split('+')[1];

				operateHtml = "<td width='80'>";
				operateHtml += " <img class='iconImg ext-icon-arrow_down down_btn'/>";
				operateHtml += " <img class='iconImg ext-icon-arrow_up up_btn'/>";
				operateHtml += " <img class='iconImg ext-icon-application_edit edit_btn'/>";
				operateHtml += " <img class='iconImg ext-icon-application_delete delete_btn'/>";
				operateHtml += "</td>";

				html += "<tr value=" + arrFields[i] + ">";
				switch (fType) {
				case 1://文本框
					html += fw.formatString("<th>{1}</th><td><input type='text' name='{0}' size='40' value='文本框'> {3}</td>{2}", eName, cName, operateHtml, fCheckString);
					break;
				case 2://编辑框
					html += fw.formatString("<th>{1}</th><td><textarea name='{0}'>编辑框</textarea> {3}</td>{2}", eName, cName, operateHtml, fCheckString);
					break;
				case 3://下拉框
					optionHtml = "";
					$.each(fValue.split('|'), function() {
						optionHtml += fw.formatString("<option>{0}</option>", this.trim());
					});
					html += fw.formatString("<th>{1}</th><td><select name='{0}'>{2}</select>{4}</td> {3}", eName, cName, optionHtml, operateHtml, fCheckString);
					break;
				case 4://复选框
					checkboxHtml = "";
					$.each(fValue.split('|'), function() {
						checkboxHtml += fw.formatString("<input type='checkbox' name='{0}'> {1}", eName, this.trim());
					});
					html += fw.formatString("<th>{0}</th><td>{1} {3}</td>{2}", cName, checkboxHtml, operateHtml, fCheckString);
					break;
				case 5://选择文件
					html += fw.formatString("<th>{1}</th><td><input type='text' name='{0}' size='40' value='选择文件'> <img class='iconImg ext-icon-attach'> {3}</td>{2}", eName, cName, operateHtml, fCheckString);
					break;
				case 6://选择日期
					html += fw.formatString("<th>{1}</th><td><input type='text' name='{0}' size='40' value='选择日期'> <img class='iconImg ext-icon-note_delete'> {3}</td>{2}", eName, cName, operateHtml, fCheckString);
					break;
				case 7://富文本框
					html += fw.formatString("<th>{1}</th><td><textarea name='{0}'>富文本框</textarea> {3}</td>{2}", eName, cName, operateHtml, fCheckString);
					break;
				default:

				}
				html += "</tr>";

			}

			return html;

		}

		/*-----------------------------------------------------------------------------
		 * 自字义表单操作部分
		 * End
		 -----------------------------------------------------------------------------*/
		$(function() {
			/*-----------------------------------------------------------------------------
			 * 自字义表单操作部分
			 * Begin
			 -----------------------------------------------------------------------------*/
			//类型下拉操作的绑定，在onchange时改变textarea的disabled及background的样式
			$("#ftype").combobox({
				onChange : function() {
					if ($('#ftype').combobox('getValue') == 3 || $('#ftype').combobox('getValue') == 4) {
						$("#fvalue").prop("disabled", false).css("background", "white");
					} else {
						$("#fvalue").prop("disabled", true).css("background", "#f5f5f5");
						$("#fvalue").val('');
					}
				}
			})

			$("#btn_formdesign").click(function() {
				
				var rows = $('#grid').treegrid('getSelections');
				if (rows <= 0) {
					$.messager.show({
						title : message.title.normal,
						msg : message.grid_select,
						timeout : message.timeout,
						showType : message.showType
					});
				} else {
					//表单定义操作
					$('#tt').tabs('select', 1);
					$('#tt').tabs('enableTab', 1);

					$("#formdesign").html('');
					var idValue = $("#form input[name='id']").val();
					$.ajax({
						type : "POST",
						url : ctx + "getSysClassTypeById.do?id=" + idValue,
						data : {
							id : idValue
						}
					}).done(function(result) {
						//如果formobjectlist字段有值时才做扩展字段列表处理
						//如果数据库表内blob中是null值，那么需要判断undefined，如果进行了更新操作，但blob里又是无数据，那么就成了空值，所以得判断字符串的长度是否为空
						result = $.parseJSON(result);
						if (typeof (result.formobjectlist) != "undefined" && result.formobjectlist.trim().length != 0) {
							//将formobjectlist转化为数组
							aFields = result.formobjectlist.split(',');
							o = $("#formdesign");
							html = getHtml(aFields);
							o.html(html);
						}
						btnOperates();

					}, 'json');
				}
			})
			/*-----------------------------------------------------------------------------
			 * 自字义表单操作部分
			 * End
			 -----------------------------------------------------------------------------*/

			//获取分类类别
			var classTypeName;
			//父级资源
			var parentId;
			//列表操作
			var gird;

			// 切换分类的类别
			// 在切换分类的类别以后，需要对treegrid,还有form中的parentId字段进行初始化操作
            //	classTypeName =	$.getJSON(ctx+'listAllSysClassType.do',function(dataJson){
				
			classTypeName =	 $('#classTypeName').combobox({
					editable : false,
					panelHeight : 'auto',
					valueField : 'id',
					textField : 'name',
					url : ctx + 'listAllSysClassType.do',
					onSelect : function(record) {
						$("#sysClassTypeId").val(record.id);						
						listInit(record.id);
						$("#btn_add").click();
					},
					onLoadSuccess : function(data) {
						if (data.length > 0) {
							$('#classTypeName').combobox('select', data[0].id);

						}
					},
				});
				
			
		
		 
		 function listInit(cpid) {
				//切换到第一个Tabs项
				$('#tt').tabs('select', 0);
				//禁用第二个Tabs项
				$('#tt').tabs('disableTab', 1);

					parentId = $('#parentId').combotree({
						editable : false,
						panelHeight : 'auto',
						idField : 'id',
						textField : 'className',
						parentField : 'parentId',
						url : ctx + 'listSysClass.do?sysClassTypeIdSch=' + cpid
					});

					grid = $('#grid').treegrid({
						fit : true,
						border : false,
						collapsible : false,
						idField : 'id',
						treeField : 'className',
						parentField : 'parentId',
						rownumbers : true,
						pagination : false,
						singleSelect : true,
						rownumbers : true,
						url : ctx + 'listSysClass.do?sysClassTypeIdSch=' + cpid,
						columns : [ [ {
							field : 'id',
							title : 'ID',
							width : 40,
							sortable:true
						}, {
							field : 'className',
							title : '分类名称',
							width : 150,
							sortable:true
						}, 
						{
							field : 'sortId',
							title : '排序',
							width : '120',
							sortable:true
						},
						{
							field : 'createdTime',
							title : '创建时间',
							width : '120',
							formatter:getDate,sortable:true
						} 
						] ],
						onLoadSuccess : function(data) {
							$('.iconImg').attr('src', fw.pixel_0);
						},
						onClickRow : function(row) {
							$("#operateMode_title").html("修改模式");
							$("#form").form("clear");
							setRowBeforeEdit(row); 
							$("#form").form("load",row);
	
							$('#tt').tabs('select', 0);
							$('#tt').tabs('disableTab', 1);
	
							$("#gridRowId").val(row.id);
							if (row.parentId != 0) {
								$("#gridRowParentId").val(row.parentId);
							} else {
								$('#parentId').combotree('clear');
							}
							EditRecord(row);
						}
					});
			}
		 
		
			

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
					reload : ctx + 'listSysClass.do?sysClassTypeIdSch=' + $("#sysClassTypeId").val()
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
				if ($('#form').form('validate')) {
					//判断parentId值
					if ($('#parentId').combotree('getValue').trim().length == 0) {
						$('#parentId').combotree('setValue', 0);
					}
					   frmdata = fw.serializeObject($('form'));
                       setRowAfterEdit(frmdata);
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
										reload : ctx + 'listSysClass.do?sysClassTypeIdSch=' + $('#classTypeName').combobox('getValue')
									})

									//重新获取form信息内容
									$("#form").form("load", ctx + "getSysClassById.do?id=" + idValue);
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

						$.post(ctx + "addSysClass.do", fw.serializeObject($('form')), function(result) {
							if (result.success) {
								$.messager.show({
									title : message.title.normal,
									msg : message.add_success,
									timeout : message.timeout,
									showType : message.showType
								});
								parentId.combotree({
									reload : ctx + 'listSysClass.do?sysClassTypeIdSch=' + $("#sysClassTypeId").val()
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
			})

		});
		
		
		
	</script>
</body>