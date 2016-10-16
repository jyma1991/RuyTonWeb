var fw = fw || {};
fw.formatString = function(str) {
	for (var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

/*
 * serializeObject方法处理的原则
 * 1.为什么需要将checkbox、radio对象单独划分出来，原因是：在我们进行数据修改的时候，如果对应字段没有传递参数过去，那么数据库中将不作更新处理
 * 但有的时候，我们需要将原来有值的内容改成没有值，那么如果不进行checkbox、radio默认值设置的话，那么这些对象的字段值将无法更新
 * 2.为什么对于扩展字段部分要加入“如果表单项的值非空，才进行序列化操作”，是因为扩展表单是将原来的横向数据转为了纵向数据，如果不管有值没值都要进行
 * 数据插入的话，冗余数据将会非常的庞大，所以一定要进行非空值操作。
 * 3.为什么扩展字段部分需要将checkbox等进行判断处理，是因为扩展字段部分checkbox如果没有选择，则不需要入扩展表数据，如果不作判断处理，那么就会
 * 有默认值插入，与我们字段设置时的字段选择将产生不一致情况。
 */
fw.serializeObject = function(form) {
	var o = {};
	// 如果只有一个参数时，默认的表单序列化操作
	if (arguments.length == 1) {
		$("input[type='checkbox'],input[type='radio']").each(function() {
			if ($('input[name="' + this['name'] + '"]').is(":checked") == false) {
				o[this['name']] = 0;
			}
		});

		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});

	} else {
		// 当传递两个参数时，第二个参数是要对扩展字段进行例外操作的部分
		var extFields = arguments[1].split(',');
		// checkbox及radio部分进行扩展字段排除处理
		$("input[type='checkbox'],input[type='radio']").each(function() {
			if ($.inArray(this['name'], extFields) == -1) {
				if ($('input[name="' + this['name'] + '"]').is(":checked") == false) {
					o[this['name']] = 0;
				}
			}
		});

		// 常用对象部分进行扩展字段处理
		$.each(form.serializeArray(), function(index) {
			if ($.inArray(this['name'], extFields) != -1) {
				if (this['value'] != undefined && this['value'].length > 0) {// 如果表单项的值非空，才进行序列化操作
					if (o[this['name']]) {
						o[this['name']] = o[this['name']] + "," + this['value'];
					} else {
						o[this['name']] = this['value'];
					}
				}
			} else {
				if (o[this['name']]) {
					o[this['name']] = o[this['name']] + "," + this['value'];
				} else {
					o[this['name']] = this['value'];
				}
			}
		});
	}
	// alert($.toJSON( o ));
	return o;
};

fw.serializeObjectWithOutNull = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (this['value'] != undefined && this['value'].length > 0) {// 如果表单项的值非空，才进行序列化操作
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		}
	});
	// alert($.toJSON( o ));
	return o;
};

fw.getFileSize = function(sizeinbytes) {
	var fSExt = new Array('Bytes', 'KB', 'MB', 'GB');
	fSize = sizeinbytes;
	i = 0;
	while (fSize > 900) {
		fSize /= 1024;
		i++;
	}
	return (Math.round(fSize * 100) / 100) + ' ' + fSExt[i];
}

String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

fw.trim = function(str) {
	alert('f')
	return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');
}

fw.modalDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 640,
		height : 480,
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};

//切换文本框及编辑器的操作
function EditorRender(obj, thisObj) {
	//动态变量赋值
	obj_editor = UE.getEditor(obj);
	if (thisObj.attr("mode") == 1) {

		obj_editor.destroy();
		obj_editor = null;
		thisObj.attr("mode", 0);
		//设置textarea的高度样式，不然会被ueditor的样式所影响，高度比较高
		$("#" + obj).css("height", 30);
		thisObj.val('切换至编辑器');
	} else {
		thisObj.attr("mode", 1);
		thisObj.val('切换至编辑框');
	}
}