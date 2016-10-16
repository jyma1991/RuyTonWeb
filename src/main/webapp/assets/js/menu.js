var mainMenu;
var delayTime;
/*
为每个Tabs页面打开时加入Loading显示，如果页面完整显示，则移除Loading提示对象

需要在修饰器母板文件中加入:
<div id='loading' style="position: absolute; z-index: 1000;top: 0px; left: 0px; width: 100%; height: 100%; background: #ffffff;text-align: center; padding-top: 20%;">页面加载中，请稍后</div>
*/

function iframeLoadingShow() {
	$("#loading").fadeOut("normal", function() {
		$(this).remove();
	});
}

$.parser.onComplete = function() {
	if (delayTime)
		clearTimeout(delayTime);
	delayTime = setTimeout(iframeLoadingShow, 200);
}

$(function() {
	// 动态菜单数据
	mainMenu = $("#tree").tree({
		url : ctx+'listUserMenu.do',
		method : 'get',
		parentField : "resourceid",
		textFiled : "text",
		idFiled : "id",
		onClick : function(node) {
			if (node.url) {
				if(node.url.indexOf('?')>0){
					Open(node.sysResName, node.url+ '&systemResourceId='+node.id);
				}else{
					Open(node.sysResName, node.url+ '?systemResourceId='+node.id);
				}
			}
		}
	});

	// 在右边center区域打开菜单，新增tab
	function Open(text, url) {
		if ($("#tabs").tabs('exists', text)) {
			$('#tabs').tabs('select', text);
		} else {
			var content = '<iframe id="mainFrame" scrolling="no" allowTransparency="true" frameborder="0"   src="' + url + '" style="border:0;width:100%;height:99%;"></iframe>';
			$('#tabs').tabs('add', {
				title : text,
				closable : true,
				content : content,
				border : false,
				fit : true
			});

		}
	}

	// 绑定tabs的右键菜单
	$("#tabs").tabs({
		onContextMenu : function(e, title) {
			e.preventDefault();
			$('#tabsMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data("tabTitle", title);
		}
	});

	// 实例化menu的onClick事件
	$("#tabsMenu").menu({
		onClick : function(item) {
			CloseTab(this, item.name);
		}
	});

	// 几个关闭事件的实现
	function CloseTab(menu, type) {
		var curTabTitle = $(menu).data("tabTitle");
		var tabs = $("#tabs");
		if (type === "close" && curTabTitle != "首页") {
			tabs.tabs("close", curTabTitle);
			return;
		}
		var allTabs = tabs.tabs("tabs");
		var closeTabsTitle = [];
		$.each(allTabs, function() {
			var opt = $(this).panel("options");
			if (opt.closable && opt.title != curTabTitle && type === "Other") {
				closeTabsTitle.push(opt.title);
			} else if (opt.closable && type === "All") {
				closeTabsTitle.push(opt.title);
			}
		});
		for (var i = 0; i < closeTabsTitle.length; i++) {
			tabs.tabs("close", closeTabsTitle[i]);
		}
	}
});