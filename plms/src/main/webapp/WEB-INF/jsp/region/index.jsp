<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>省市</title>
<%@ include file="/WEB-INF/jsp/common/html-jqGrid.jsp"%>
<script type="text/javascript">
$(function(){
	$("#pro").change(function(){
		$("#city").html("");
		$.ajax({
			type:"post",
			dataType:"json",
			url:ctx+"/region/queryCity",
			data:{
				province : $("#pro").val()
			},
			success:function(data){
				var cityHtml = '<option value="">不限</option>';
				if(data){
					//console.log(data);
					
					$.each(data,function(i,val){
						cityHtml+='<option value="'+val.id+'">'+val.name+'</option>';
					});
					
				}
				$("#city").append(cityHtml);
			}
		});
	});
	
	$("#rgn").autocomplete({
		minLength:0,
		delay:300,
		appendTo:"body",
		source:function(request,response){
			$.ajax({
				url:ctx+"/region/query/autoCity",
				type:"post",
				dataType:"json",
				data:{
					name:$("#rgn").val()
				},
				success:response
			});
		},
		focus:function(event,ui){
			$("#rgn").val(ui.item.label);
			return false;
		},
		select:function(event,ui){
			$("#regionId").val(ui.item.value);
			$("#rgn").val(ui.item.label);
			return false;
		},
		change:function(event,ui){
			if(ui.item==null || ui.item.label!=$("#rgn").val()){
				$("#rgn").val("");
				$("#regionId").val("");
			}
		}
	});
	
	$("#rgn").click(function(){
		if(this.value==""){
			$(this).autocomplete("search","");
		}
	});
	
	$("#gridTable").jqGrid({
		url:ctx+"/region/province",
		datatype:"json",
		mtype:"post",
		rowNum:20,
		pager:"#paper",
		height:"auto",
		autowidth:true,
		altRows:true,
		altclass:"ui-widget-content-altclass",
		rownumbers:true,
		viewrecords:true,
		postData:{},
		jsonReader:{
			root : "result",
			page : "page",
			total : "total",
			records : "record",
			repeatitems : false
		},
		colNames:["","省","代码","等级"],
		colModel:[{name:"id",hidden:true},
		          {name:"name"},
		          {name:"code"},
		          {name:'level'}],
        subGrid:true,
        subGridRowExpanded:function(subgrid_id,row_id){
        	var rowData=$("#gridTable").jqGrid('getRowData',row_id);
        	var sub_table_id = subgrid_id+"_t";
        	var sub_paper_id = subgrid_id+"_pgr";
        	$("#"+subgrid_id).html("<table id='"+sub_table_id+"' class='scroll'></table><div id='"+sub_paper_id+"' class='scroll'></div>");
        	$("#"+sub_table_id).jqGrid({
        		url:ctx+"/region/province/child",
        		datatype:"json",
        		mtype:"post",
        		rowNum:10,
        		pager:"#"+sub_paper_id,
        		height:"auto",
        		autowidth:true,
        		altRows:true,
        		altclass:"ui-widget-content-altclass",
        		rownumbers:true,
        		viewrecords:true,
        		postData:{id:rowData['id']},
        		jsonReader:{
        			root : "result",
        			page : "page",
        			total : "total",
        			records : "record",
        			repeatitems : false
        		},
        		colNames:["市","代码","等级","所属省份"],
        		colModel:[{name:"name"},
        		          {name:"code"},
        		          {name:'level'},
        		          {name:'region.name'}],
        	});
        	}
		
	});
});
</script>
</head>
<body>
<div>
<label>省:</label><select id="pro">
<option value="">不限</option>
<c:forEach items="${proList}" var="item">
<option value="${item.id }">${item.name }</option>
</c:forEach>
</select>&nbsp;
<label>市:</label><select id="city" style="width:175px">
<option value="">不限</option>
</select>
</div>
<div>
<label>输入:</label><input type="text" id="rgn" /><input type="hidden" id="regionId" />
</div>
<div>
	<table id="gridTable"></table>
	<div id="paper"></div>
</div>
</body>
</html>
