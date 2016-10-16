<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="org.durcframework.core.UserContext"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>代理商设备详细列表</title>
        <jsp:include page="/view/taglib.jsp"></jsp:include>
    </head>

    <body>
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',split:true,border:false" style="height:50px;">
                <form id="searchForm">
                    <table>

                        <tr>
                            <td>代理商</td>
                            <td>
                                <input id="agentIdSch" name="agentIdSch" class="textbox" editable="false" style="width: 120px; height: 22px;" />
                            </td>
                            <td>场所类型</td>
                            <td>
                                <input id="placeTypeIdSch" name="placeTypeIdSch" class="textbox" editable="false" style="width: 120px; height: 22px;" />
                            </td>
                            <td>创建时间</td>
                            <td>
                                <input name="createdStartSch" class="easyui-datebox" style="width: 100px" /> -
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
        </div>
        <script type="text/javascript">
        var SS;
        var gird;
        var CS;
        var sysTypeArray = new Array();
        
      	//初始化代理商
        $.getJSON(ctx+'listAllScAgent.do',function(agentJson){
            if (agentJson.rows.length == 0) {
                SS = $('#agentIdSch').combobox();
                SS.combobox("clear");
            } else {
                SS = $('#agentIdSch').combobox({
                    data: agentJson.rows,
                    valueField: 'agentId',
                    textField: 'trueName',
                    DropDownStyle: 'DropDownList',
                    onSelect: function(row) {
                    	grid.datagrid('load',fw.serializeObject($('#searchForm')));				
                    }
                });
            }
        });
      
      	//初始化场地类型
        $.getJSON(ctx+'/listScClassType.do',function(classTypeJson){
            if (!classTypeJson) {
                CS = $('#placeTypeIdSch').combobox();
                CS.combobox("clear");
            } else {
                CS = $('#placeTypeIdSch').combobox({
                    data: classTypeJson,
                    valueField: 'id',
                    textField: 'className',
                    DropDownStyle: 'DropDownList',
                    onSelect: function(row) {
                    	grid.datagrid('load',fw.serializeObject($('#searchForm')));				
                    }
                });
                sysTypeArray = CS.combobox("getData");
            }
        });
      
        
        //当前登录人的ID 该页面应该是园长 即schoolId
            $(function() {

                grid = $('#grid').datagrid({
                    fit: true,
                    border: false,
                    collapsible: false,
                    rownumbers: true,
                    pagination: true,
                    singleSelect: true,
                    rownumbers: true,
                    url: ctx + 'listStatisticScClass.do',
                    columns: [
                        [{
                            field: 'id',
                            title: 'ID',
                            sortable:'true'
                        }, {
                            field: 'className',
                            title: '场地名称',
                            sortable:'true'
                        }, {
                            field: 'streamId',
                            title: '设备名称',
                            sortable:'true',
                            formatter: function(value, row) {
                            	if(row.stream){
                            		return row.stream.deviceName;
                            	}
                            }
                        }, {
                            field: 'grade',
                            title: '年级',
                            sortable:'true'
                        }, {
                            field: 'amount',
                            title: '现有人数',
                            sortable:'true'
                        }, {
                            field: 'startDate',
                            title: '开班日期',
                            sortable:'true',
                            formatter:getDate
                        }, {
                            field: 'remark',
                            title: '说明',
                            sortable:'true'
                        }, {
                            field: 'placeTypeId',
                            title: '场所类型',
                            sortable:'true',
                            formatter: function(value, row) {
                            	for(var i = 0; i<sysTypeArray.length;i++){
                            		if(sysTypeArray[i].id == value){
                            			return sysTypeArray[i].className;
                            		}
                            	}
                            }
                        }, {
                            field: 'videoStart',
                            title: '视频开放时间',
                            sortable:'true',
                            formatter:function(value,row){
                            	if(value){
                            		var videoEnd = getDate(row.videoStop) == null ? "24:00":getDate(row.videoStop).substring(0,5);
                            		return getDate(row.videoStart).substring(0,5)+"-"+videoEnd;
                            	}else{
                            		return "";
                            	}
                            	
                            }
                        }, {
                            field: 'createdTime',
                            title: '创建时间',
                            sortable:'true',
                            formatter:getDate
                        }]
                    ],
                    onLoadSuccess: function(data) {
                        $('.iconImg').attr('src', fw.pixel_0);
                    },
                    onClickRow: function(index, row) {
                    }
                });
            });

          	
        </script>
    </body>

    </html>