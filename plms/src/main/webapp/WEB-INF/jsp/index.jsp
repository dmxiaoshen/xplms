<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%-- <%@ include file="/WEB-INF/jsp/common/common.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>主页</title>
<link rel="stylesheet" href="${ctx}/static/css/demo.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/base/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common/jquery.ztree.all-3.5.js"></script>

<script type="text/javascript" src="${ctx}/static/js/common/jquery-ui/jquery-ui-1.8.22.custom.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/common/jqGrid/css/simplicity.ui.jqgrid.css" />
<script type="text/javascript" src="${ctx}/static/js/common/jqGrid/js/jquery.jqGrid.src.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/common/jquery-ui/simplicity.ui.css"/>
<script type="text/javascript" src="${ctx}/static/js/common/jqGrid/js/i18n/grid.locale-zh_cn.js"></script>
<style type="text/css">
.my{
	float: left;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	 /* var zNodes;
	$.ajax({
		async:false,
		url:"${ctx}/login/getData",
		type:"post",
		dataType:"json",
		success:function(data){
			zNodes = data;
		}
	}); 
	var setting = {
			view: {
				showLine: false
			},
			check: {
				enable: true
			}, 
			data: {
				simpleData: {
					enable: true
				}
			}
		};

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});  */
	
	$("#insert").click(function(){
		$.ajax({
			type:"post",
			url:"${ctx}/login/insertFile",
			dataType:"text",
			success:function(data){
				if(data=="success"){
					alert("导入成功");
				}
			}
		});
	});
	
 	var setting = {
			async : {
				enable : true,
				type : "post",
				url : "${ctx}/login/sub?a=" + new Date().getTime(),
				autoParam : [ "code" ],
				dataFilter : filter
			}, 
			check: {
				enable: true
			}, 
			 view : {
				showLine: false,
				expandSpeed : "",
				selectedMulti : false
			}, 
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				onDblClick : zTreeOnDblClick
			}
		};

		function zTreeOnDblClick(event, treeId, treeNode) {
			if (null != treeNode) {
				$("#parentCode").val(treeNode.code);
				$("#parentName").val(treeNode.name);
			} else {
				$("#parentCode").val("");
				$("#parentName").val("");
			}
			close();
		};

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes)
				return null;
			for ( var i = 0, l = childNodes.length; i < l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		
		$(document).ready(function() {
			
			$.fn.zTree.init($("#treeDemo"), setting);
			
			$("#save").click(function(){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var nodes = zTree.getCheckedNodes(true);
		    	  var list ="";
		          if(null==nodes|| nodes.length==0){
		              alert("请先选择。");
		              return false;
		          }
		         /*  var flag = confirm("确定要保存吗？");
		          if (!flag){
		              return false;
		          } */
		          for(var i=0;i<nodes.length;i++){
		              list = list+"," + nodes[i].fileNodeId;
		          }
		          //alert(list);
		          $("#gridTable").jqGrid('setGridParam',{postData:{text:list}}).trigger("reloadGrid");   

			});
			
			$("#reset").click(function(){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.checkAllNodes(false);
				$("#gridTable").jqGrid('setGridParam',{postData:{text:""}}).trigger("reloadGrid"); 
			});
			
			
			$("#gridTable").jqGrid({
				url : ctx+'/login/fileNode/query',
				datatype: "json",
				mtype : 'post',
				rowNum:20,
				pager: '#paper',
				height : "100%",
				autowidth : true,
				altRows:true,//隔行变色
				altclass:'ui-widget-content-altclass',//隔行变色样式
				rownumbers : true,
				viewrecords : true,
				jsonReader : {	//default
					root : "result",	//表格需要的数据从哪里开始
					page : "page",	//当前第几页
					total : "total",	//总页数
					records : "record",	//查询出的记录数
					repeatitems : false	//default is true
				},
				postData : {
					  text:"" 
					//text : function(){return $("#text").attr("value");},
					//type : function(){return $("#securityGradeType").attr("value");}
				},
				colNames : ["名称","路径","是否文件夹"],
				colModel:[{name:'fileName',index:'fileName',width:100}, 
				          {name:'path',index:'path'},
				          {name:'isDir',index:'isDir'}
				          //{name:'stockGrade',index:'stockGrade',align : 'right'}
				         // {name:'sedol',index:'sedol'},
				          //{name:'isin',index:'isin'},
				         // {name:'underlyingCode',index:'underlying.secCode'},
				         // {name:'secCcy.itemName',index:'secCcy.itemCnName'},
				         /* {name:'id',sortable:false,align:'center',width:250,formatter:function(cellvalue, options, rowObject ){
				        		  return "<a href='javascript:;' onclick='positionHeld("+cellvalue+")'>"
				        		  +position+"</a>&nbsp;&nbsp;<a href='javascript:;' onclick='priceHistory("+cellvalue+")'>"
				        		  +priceHistoryMsg +"</a>&nbsp;&nbsp;<a href='javascript:;' onclick='edit("+cellvalue+")'>"
				        		  +editMsg+"</a>";
				          	}
				          }*/],
				 //ondblClickRow : function(rowid, iRow, iCol, e){
				//	 var url=ctx+"/charts/exchange/secDetail?secId="+rowid;
				//	Boxy.loadIframe(url,{title:stockCharts,width:840,height:525,afterShow:function(){this.getContent().addClass("nopadding");}});
				// }
			});
		}); 
});
function logout(){
	window.location.href = "${ctx}/login/logout";
}
</script>
</head>
<body>
<h2>Hello World!  home</h2>
<input type="button" onclick="logout()" value="退出" />
<input type="button" value="导入数据库" id="insert"/>
<a href="${ctx}/login/box">jquery box</a>
<div class="">
		<ul id="treeDemo" class="ztree"></ul>
		
		
</div>
<div>
			<input type="button" value="选中" id="save"/>
			<input type="button" value="重置" id="reset"/>
		</div>
<div>
			<table id="gridTable"></table>
			<div id="paper"></div>
		</div>
</body>
</html>
