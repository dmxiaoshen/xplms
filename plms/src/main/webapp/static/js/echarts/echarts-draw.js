// JavaScript Document
/*
 *	edit by lotus
 *	2014.05.15
 */

function draw(dataSource, ctx, $funnelChart, $pieChart, $barChart) {

	var seriesBar = [];
	var dataFunnel = [];
	var dataPie = [];

	$.each(dataSource, function(i, n) {
		var tempFunnel = {};
		tempFunnel.name = dataSource[i].name;
		tempFunnel.value = changeTwoDecimal(dataSource[i].value * 100);
		dataFunnel.push(tempFunnel);
		
		var tempPie = {};
		tempPie.name = dataSource[i].name;
		tempPie.value = dataSource[i].value;
		dataPie.push(tempPie);

		var tempBar = {};
		var dArray = [];
		tempBar.name = dataSource[i].name;
		tempBar.type = 'bar';
		tempBar.stack = '汇总';
		dArray.push(changeTwoDecimal(dataSource[i].value * 100));
		tempBar.data = dArray;
		seriesBar.push(tempBar);

	});

	// 路径配置
	require.config({
		paths : {
			echarts : ctx + "/static/js/echarts"
		}
	});

	// 使用
	require([ 'echarts', 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
	'echarts/chart/pie',// 饼图
	'echarts/chart/funnel'// 漏斗图
	], drawCallBack// 自定义回调函数
	);// end require

	function drawCallBack(ec) {
		drawChartBar(ec);
		drawChartPie(ec);
		drawChartFunnel(ec);
	}

	function drawChartFunnel(ec) {
		var funnelChart = ec.init($funnelChart[0]);
		var optionFunnel = {
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}  {c}%"
			},
			calculable : true,
			series : [			
			{
				name : '',
				type : 'funnel',
				x : '50%',
				sort : 'ascending',
				itemStyle : {
					normal : {						
						label : {
							position : 'left'
						}
					}
				},
				data : dataFunnel			
			} ]
		};
		funnelChart.setOption(optionFunnel);
	}
	
	function drawChartPie(ec) {
		var pieChart = ec.init($pieChart[0]);
		var optionPie = {			
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}  {d}%"// "{a} <br/>{b} : {c} ({d}%)"
			},			
			calculable : true,
			series : [ {
				name : '',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : dataPie			
			} ]
		};
		pieChart.setOption(optionPie);
	}
	
	function drawChartBar(ec) {

		var barChart = ec.init($barChart[0]);

		var optionBar = {
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}  {c}%",
				axisPointer : {
					type : 'shadow'
				}
			},			
			calculable : true,
			xAxis : [ {
				type : 'category',
				data : [ '' ]			
			} ],
			yAxis : [ {
				type : 'value',
				axisLabel : {
					formatter : function(value) {
						return value + "%";
					}
				},
				splitArea : {
					show : true
				}
			} ],			
			series : seriesBar		
		};
		barChart.setOption(optionBar);
	}
	;
}

function changeTwoDecimal(x) {
	var f_x = parseFloat(x);
	if (isNaN(f_x)) {
		alert('function:changeTwoDecimal->parameter error');
		return false;
	}
	var f_x = Math.round(x * 100) / 100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0) {
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2) {
		s_x += '0';
	}
	return s_x;
}