<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/view/taglib.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>My JSP 'charts.jsp' starting page</title> 
	<meta name="viewport" content="width=device-width,target-densitydpi=medium-dpi, initial-scale=0.8,minimum-scale=1,maximum-scale=1,user-scalable=no" />
  	
  </head>
  
  <body>
  	<div style="height:40px; line-height:40px; text-align:center;">太仓市年末户籍总人口</div>
    <div id="chartContainer1"></div>
    <div style="height:40px; line-height:40px; text-align:center;">太仓市年末户籍总人口中：女性</div>
    <div id="chartContainer2"></div>
    <div style="height:40px; line-height:40px; text-align:center;">太仓市年末户籍总人口中：非农业人口</div>
    <div id="chartContainer3"></div>
    <div style="height:40px; line-height:40px; text-align:center;">太仓市年末户籍总户口</div>
    <div id="chartContainer4"></div>
    
    
    <script type="text/javascript">

		/*
		 * 定义json格式的字符串
		 * 可作为模板，显示多个图表时进行复用，图表样式统一在chart中设置
		 * 作为模板，此处未直接定义为一个json对象，避免第一个图表对data进行修改后，
		 * 第二个图表再次使用json对象时，值已发生变化（每次使用前清除data中元素可解决该问题）。
		 */
		 
		var jsondata = '{' +
		            '"chart":{' +
		                '"xAxisName":"",' +
		                '"yAxisName":""' +',"bgcolor": ""'+
		                ',"palettecolors": "e44a00"'+
		                ',"theme": "fint"'+
		                ',"showborder":"1"'+
		                ',"yAxisMinValue":"350000"'+
		                ',"yAxisMaxValue":"500000"'+
		                ',"numberScaleValue":"10,1000"'+
		                ',"numberScaleUnit":"K,W"'+
		                ',"showLegend":"1"'+
		                //',"anchorRadius":"5"'+//折线图描点直径
		                ',"useRoundEdges":"1"'+//对2d柱形图和3D饼图使用圆角和玻璃效果填充
		                ',"showPlotBorder":"0"'+//是否显示（柱形，折现，饼图...）的边框
		                //',"outCnvbaseFontColor":"ff0000"'+//画布以外部分区域字体颜色
		                //',"divLineIsDashed":"1"'+//虚线效果
		            '},"data":[]' +
		        '}';
		$("document").ready(function(){
			var jsonobj = JSON.parse(jsondata);

			/*var elemSet = {"label":"2000","value":449406,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2001","value":448914,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2002","value":449319,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2003","value":451116,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2004","value":454554,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2005","value":457648,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2006","value":461441,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2007","value":463825,"color":"76A5DB"};
			jsonobj.data.push(elemSet);*/
			var elemSet = {"label":"2008","value":466283,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2009","value":467168,"color":"B53A3A"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2010","value":468904,"color":"0D8080"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2011","value":470373,"color":"F1AB7F"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2012","value":472561,"color":"8DB127"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2013","value":474461,"color":"D1A00D"};
			jsonobj.data.push(elemSet);
			
			var prefix = "";//./component/fusioncharts/
			// 生成图表的id用时间标识，解决刷新页面时，报图表id已存在js错误
			var chart = new FusionCharts("Pie3d.swf",new Date().getTime(),"100%",200,0,1);
			// 此处若报js错误：setJSONData，很有可能是fusioncharts.js版本有问题，
			// 可先使用官网试用版进行测试
			chart.setJSONData(jsonobj);
			chart.render("chartContainer1");

			/*
			 * jquery创建图表的一种方法，
			 * 若报js错误，可能是fusioncharts.js版本有问题，或jquery-fusioncharts.js未引入，
			 * 若不报js错误，可能是$("#id")未加#号
			 
			$("#chartContainer2").insertFusionCharts({
		    	swfUrl:	prefix + "Line.swf",
		    	dataSource:	jsonobj,
		    	dataFormat:	"json",
		    	width:	"100%",
		    	height:	"200",
		    	id:	new Date().getTime()
			});
			*/
			jsonobj = JSON.parse(jsondata);
			elemSet = {"label":"2008","value":239126,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2009","value":239816,"color":"B53A3A"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2010","value":240962,"color":"0D8080"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2011","value":241964,"color":"F1AB7F"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2012","value":202698,"color":"8DB127"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2013","value":265498,"color":"D1A00D"};
			jsonobj.data.push(elemSet);
			
			var chart = new FusionCharts("Column2d.swf","jsonobj2","100%",200,0,1);
			chart.setJSONData(jsonobj);
			chart.render("chartContainer2");
			
			
			jsonobj = JSON.parse(jsondata);
			elemSet = {"label":"2008","value":203063,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2009","value":203152,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2010","value":202813,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2011","value":201252,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2012","value":243399,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2013","value":244520,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			
			var chart = new FusionCharts("Line.swf","jsonobj3","100%",200,0,1);
			chart.setJSONData(jsonobj);
			chart.render("chartContainer3");
			
			
			jsonobj = JSON.parse(jsondata);
			elemSet = {"label":"2008","value":149070,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2009","value":148545,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2010","value":147233,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2011","value":147096,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2012","value":146292,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			elemSet = {"label":"2013","value":146767,"color":"76A5DB"};
			jsonobj.data.push(elemSet);
			
			var chart = new FusionCharts("Column3d.swf","jsonobj4","100%",200,0,1);
			chart.setJSONData(jsonobj);
			chart.render("chartContainer4");
		});
	</script>
  </body>

  <!-- <script src="${pageContext.request.contextPath }/jjsj/js/fusioncharts.maps.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath }/jjsj/js/fusioncharts.powercharts.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath }/jjsj/js/fusioncharts.widgets.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath }/jjsj/js/fusioncharts.gantt.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath }/jjsj/js/fusioncharts.charts.js" type="text/javascript"></script> -->

  
</html>
