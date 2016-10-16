var redoFun = function() {
	var node = grid.treegrid('getSelected');
	if (node) {
		grid.treegrid('expandAll', node.id);
	} else {
		grid.treegrid('expandAll');
	}
};

var undoFun = function() {
	var node = grid.treegrid('getSelected');
	if (node) {
		grid.treegrid('collapseAll', node.id);
	} else {
		grid.treegrid('collapseAll');
	}
};

//修改时间格式
function getDate(response){
	if(response!=null){
		return response.substring(0,10);
	}else{
		return null;
	}
}
//原文地址
//http://sshe.jeasyuicn.com/jslib/syExtEasyUI.js?version=20131115
$.fn.combotree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idField, textField, parentField;
	if (opt.parentField) {
		idField = opt.idField || 'id';
		textField = opt.textField || 'text';
		parentField = opt.parentField || 'pid';
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idField]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textField];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textField];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
}

$.fn.tree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;

		var i, l, treeData = [], tmpMap = [];

		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}

		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

$.extend($.fn.treegrid.defaults, {
	loadFilter : function(data, parentId) {
		var opt = $(this).data().treegrid.options;
		var idField, treeField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			treeField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][treeField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][treeField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
});

$.extend($.fn.validatebox.defaults.rules, {
	radio: {
	validator: function (value, param) {
	var frm = param[0], groupname = param[1], ok = false;
	$('input[name="' + groupname + '"]', document[frm]).each(function () { //查找表单中所有此名称的radio
                if (this.checked) { ok = true; return false; }
            });
			return ok
		},
		message: '需要选择一项！'
	},
	checkbox: {
	validator: function (value, param) {
		var frm = param[0], groupname = param[1], checkNum = 0;
		$('input[name="' + groupname + '"]', document[frm]).each(function () { //查找表单中所有此名称的checkbox
			if (this.checked) checkNum++;
		});
 
		return checkNum > 0 && checkNum < 4;
		},
		message: '选择1~3项！'
	}
});
//修改时间格式
function getDate(response){
	if(response!=null){
		return response.substring(0,10);
	}else{
		return null;
	}
	
}