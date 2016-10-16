<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>角色管理</title>
    <jsp:include page="/view/taglib.jsp"></jsp:include>
  </head>

  <body>
    <div class="easyui-layout" data-options="fit:true">
      <div data-options="region:'north',split:true,border:false" style="height: 80px;">
        <form id="searchForm">
          <table>
            <tr>
              <td><a id="btn_add" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-add'">添加</a></td>
              <td><a id="btn_dele" class="easyui-linkbutton" data-options="group:'g1',iconCls:'icon-remove'">删除</a></td>
              <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
            </tr>
          </table>
          <table>
            <tr>
              <td>角色名称</td>
              <td>
                <input name="roleNameSch" class="textbox" style="width: 120px; height: 22px;" />
              </td>
              <td>创建时间</td>
              <td>
                <input name="createdStartSch" class="easyui-datebox" style="width: 100px" /> -
                <input name="createdEndSch" class="easyui-datebox" style="width: 100px" />
              </td>
              <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',fw.serializeObject($('#searchForm')));">检索</a>
                <a href="javascript:void(0);" id="reset_searchFilter" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">清空检索条件</a>
              </td>
            </tr>
          </table>
        </form>
      </div>
      <div data-options="region:'center',border:false">
        <table id="grid">
        </table>
      </div>
      <div data-options="region:'east',split:true,border:false" style="width: 65%; padding: 5px;">
        <!-- 自定义表单 Begin -->
        <div title="角色信息" id="frmView">
          <form id="form" name="form" method="post" class="form">

            <fieldset>
              <legend>
                	基本信息--<span id="operateMode_title">添加模式</span>
              </legend>
              <table class="table">
                <tbody>
                  <tr>
                    <th>ID</th>
                    <td>
                      <input id="id" readonly="readonly" name="id" class="textbox" style="height: 22px;width:40px" />
                    </td>
                    <th><span style="color: red;">*</span>角色名称</th>
                    <td>
                      <input id="roleName" name="roleName" missingMessage="必填" class="textbox easyui-validatebox"  data-options="required:true" validType="length[2,10]"  style="height: 22px;" />
                    </td>
					<th>排序值</th>
                    <td>
                      <input id="sortId" name="sortId" class="easyui-numberspinner" data-options="min:0,max:100,required:true" value="0" style="width: 50px;" />
                    </td>
                  </tr>
                  <tr>
                    <th>备注</th>
                    <td colspan="5">
                      <textarea rows="3" id="remark" wrap="off" name="remark" validType="length[0,150]"  class="textbox" style="width:90%;white-space: pre-wrap;" wrap="hard"></textarea>
                    </td>
                  </tr>
                </tbody>
              </table>
            </fieldset>
           	<div style="margin-left:10px">
           	<a id="operateMode_submit" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a> <a id="operateMode_reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
           	</div>
             
            <fieldset>
              <legend>设置权限</legend>
              <div id="sysResTree"></div>
            </fieldset>
           
          </form>
        </div>
        <!-- 自定义表单 End -->
      </div>
      <!-- East End -->
    </div>
    <!-- Layout End -->
    <script type="text/javascript">
      $(function() {
        var gird;
        grid = $('#grid').datagrid({
          fit: true,
          border: false,
          collapsible: false,
          rownumbers: true,
          pagination: true,
          singleSelect: true,
          rownumbers: true,
          url: ctx + 'listAuthRole.do',
          columns: [
            [{
    		  field: 'id',
    		  align:'center',
    		  sortable:'true',
    		  title: 'ID'
  			},
             {
              field: 'roleName',
              title: '角色名称'
            }, {
              field: 'remark',
              title: '说明'
            }, {
              field: 'sortId',
              sortable: "true",
              align:'center',
              title: '排序值'
            }, {
               field: 'createdTime',
               sortable:'true',
               formatter:getDate,
               title: '创建时间'
              }]
          ],
          onLoadSuccess: function(data) {
            $('.iconImg').attr('src', fw.pixel_0);
            //执行点击事件，为id，uuid等赋默认值
            $("#btn_add").click();
            //名称检索框获取焦点，防止新增角色文本框获取焦点提示必填，影响体验
            $("#searchForm input[name='roleNameSch']").focus();
          },
          onClickRow: function(index, row) {
            $("#form").form("load", row);
            $("#operateMode_title").html("修改模式");
            //更新已有权限状态
            updateTreeStatus(row.id);
          }
        });
        //添加操作
        $("#btn_add").click(function() {
          $("#operateMode_title").html("添加模式");
          $('#grid').datagrid("unselectAll");
          $("#id").attr("value", 0);
          $("#form").form("reset");
        })

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
                var idValue = $("#form input[name='id']").val();
                $.post(ctx + "delAuthRole.do", {
                  id: idValue
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
                $.post(ctx + "updateAuthRole.do", fw.serializeObject($('form')), function(result) {
                  if (result.success) {
                    $.messager.show({
                      title: message.title.normal,
                      msg: message.update_success,
                      timeout: message.timeout,
                      showType: message.showType
                    });

                    grid.datagrid("reload");

                    //重新获取form信息内容
                    $("#form").form("load", ctx + "getAuthRoleById.do?id=" + idValue);
                  }
                }, 'json');

              } else {
                //添加
                $.post(ctx + "addAuthRole.do", fw.serializeObject($('form')), function(result) {
                  if (result.success) {
                    $.messager.show({
                      title: message.title.normal,
                      msg: message.add_success,
                      timeout: message.timeout,
                      showType: message.showType
                    });
                    grid.datagrid("reload");
                    $("#form").form("reset");
                  }
                }, 'json');
              }
            }
          })
          //重置操作
        $("#operateMode_reset").click(function() {
          var idValue = $("#form input[name='id']").val();
          if (idValue > 0) {
            $("#form").form("load", ctx + "getAuthRoleById.do?id=" + idValue);
          } else {
            $("#form").form("reset");
          }
        })

		/******************************************************************************
		* 资源功能树的加载，更新状态等操作
		* @auther jyma;
		*createtime 2015-10-28 10:47:56
		*******************************************************************************/
		//-----------------------------------------------------------------------------
		// Begin
		//-----------------------------------------------------------------------------
        //设置已有权限选中状态
        function updateTreeStatus(roleid) {
          var sfIds = [];
          $.post(ctx + 'getlistSystemFunctionIds.do', {
            id: roleid
          }, function(sysFuns) {
            for (var i = 0, len = sysFuns.length; i < len; i++) {
              sfIds.push(sysFuns[i].sysFuncId);
            }
            $sysResTree.find(':checkbox').val(sfIds);
          }, 'json');
        }

        //资源功能树的加载
        var $sysResTree = $("#sysResTree");
        $sysResTree.tree({
          url: ctx + 'listAllMenu.do',
          method: 'get',
          rownumbers: true,
          idField: 'id',
          treeField: 'sysResName',
          formatter: function(node) {
            var text = node.text;
            if (node.aSystemFunctions && node.aSystemFunctions.length > 0) {
              text += buildOperateCheckbox(node.aSystemFunctions)
            }
            return text;
          },
          loadFilter: function(menus, parent) {
            for (var i = 0, len = menus.length; i < len; i++) {
              formatMenu(menus[i]);
            }
            return menus;
          }
        });

        //功能复选框的构造
        function buildOperateCheckbox(sysFuns) {
          var html = [];
          var sysFun = null;
          for (var i = 0, len = sysFuns.length; i < len; i++) {
            sysFun = sysFuns[i];

            html.push('<label style="vertical-align:top;">')
            html.push('<input name="sysFuncId" type="checkbox" style="vertical-align:top;" value="' + sysFun.id + '"/>' + sysFun.functionName);
            html.push('</label>');
          }

          return html.join('');
        }

        //递归加载树子节点
        function formatMenu(data) {
          if (data) {
            data.attributes = {
              url: data.url,
              srId: data.systemResourceId
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
        
		//-----------------------------------------------------------------------------
		// End
		//-----------------------------------------------------------------------------

      });
    </script>
  </body>

  </html>