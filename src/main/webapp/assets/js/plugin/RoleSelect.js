/**
 * RoleSelect.create(gridId);
 * RoleSelect.create(gridId,function(){});
 */
var RoleSelect = (function(){
	
	var roleGridTbIndex = 0;
	var gridMap = {};
	
	function createToolbar(gridId){
		var toolbarId = gridId + '_tb';
		var html = [
			'<div id="'+toolbarId+'">'
				,'角色名:&nbsp;<input type="text" />'	
			,'</div>'
		]
		
		$('body').append(html.join(''));
		
		$('#'+toolbarId).find('input').searchbox({ 
			searcher:function(value,name){ 
				searchRole(value,gridId);
			}, 
			prompt:'搜索角色,支持模糊' 
		}); 
		
		return toolbarId;
	}
	
	function searchRole(value,gridId){
		gridMap[gridId].datagrid('load',{
			roleNameSch: value
		});
	}
	
	return {
		/**
		 * 创建角色选择器
		 */
		create:function(gridId,callback) {
			gridMap[gridId] = $('#'+gridId);
			var toobarId = createToolbar(gridId);
			return gridMap[gridId].datagrid({
				idField:'roleId', // 主键
				toolbar:'#' + toobarId,
			    url:ctx + 'listAllRRole.do',
			    columns:[[    
			        {field:'roleId',checkbox:true}  
			        ,{field:'roleName',title:'角色'}  
			    ]]
				,fit:true
				,border:false
			    ,striped:true
			    ,fitColumns:true
			    ,height:300
			    ,onLoadSuccess:function(e){
			    	if(e.errorMsg){
						MsgUtil.error(e.errorMsg);
						return;
					}
					callback && callback(gridMap[gridId]);
			    }
			});
		}
		,searchRole:function(gridId){
			return function(value){
				searchRole(value,gridId);
			}
		}
	}
}());