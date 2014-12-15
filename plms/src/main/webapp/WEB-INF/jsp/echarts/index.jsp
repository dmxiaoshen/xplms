<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ECharts示例</title>
<script type="text/javascript" src="${ctx}/static/js/echarts/echarts.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/js/echarts/echarts-draw.js" charset="utf-8"></script>
<style type="text/css">
.size{
	width:200px;
	height:200px
}
</style>
<script type="text/javascript">



//var ctx = "${ctx}";

var dataSource = [{name:"中信银行",value:0.14,title:"测控"},
            {name:"五粮液",value:0.26,title:"测控"},
            {name:"平安银行",value:0.08,title:"测控"},
            {name:"中国宝安",value:0.22,title:"测控"},
            {name:"华联控股",value:0.15,title:"测控"},
            {name:"长城电脑",value:0.15,title:"测控"}];
            
$(document).ready(function(){
	//数据处理
	
	$("#dr").click(function(){
		draw(dataSource,ctx,$("#funnelChart"),$("#pieChart"),$("#barChart"));
	});
	
  
});
</script>
</head>
<body>
<div id="barChart" class="size"></div>
<div id="pieChart" class="size"></div>
<div id="funnelChart" class="size"></div>
<input type="button" value="draw" id="dr"/>
</body>
</html>
