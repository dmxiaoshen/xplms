<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户列表</title>
<%@ include file="/WEB-INF/jsp/common/html-jqGrid.jsp"%>
<script type="text/javascript">
var ctx = "${ctx}";
var colNames = ["姓名","状态","创建时间","更新时间"];
$(function(){
	$("#gridTable").jqGrid({
		url:ctx+"/user/list",
		datatype:"json",
		mtype:"post",
		rowNum:5,
		pager:"#paper",
		height:"100%",
		autowidth:true,
		altRows:true,
		altclass:'ui-widget-content-altclass',//隔行变色样式
		rownumbers : true,
		viewrecords : true,
		jsonReader : {
			root : "result",
			page : "page",
			total : "total",
			records : "record",
			repeatitems : false
		},
		postData : {
		},
		colNames : colNames,
		colModel :[{name:'username'},
		           {name:'status.name',index:"status"},
		           {name:'createTime'},
		           {name:'updateTime'}]
		
	});
});
</script>
</head>
<body>
<p>用户列表</p>
<div>
	<table id="gridTable"></table>
	<div id="paper"></div>
</div>
</body>
</html>
