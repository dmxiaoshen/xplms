<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <title></title>
		<script type="text/javascript">
		var ctx='${ctx}';
		</script>
		<script type="text/javascript"  src="${ctx}/static/js/jquery-1.7.2.js" charset="utf-8"></script>
		<script type="text/javascript">
		$.fn.extend({
			nextX:function(expr,untilExpr){
				var t=this.next();
				while(!t.is(expr)){
					t=t.next();
					if(!t.length||t.is(untilExpr)){
						return $([]);
					}
				}
				return t;
			},
			prevX:function(expr,untilExpr){
				var t=this.prev();
				while(!t.is(expr)){
					t=t.prev();
					if(!t.length||t.is(untilExpr)){
						return $([]);
					}
				}
				return t;
			}
		});
		</script>
		<link type="text/css" rel="stylesheet" href="${ctx}/static/css/base/base.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/static/css/base/global.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/static/js/zTree/zTreeStyle/zTreeStyle.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/static/js/common/jquery-plugin-boxy/css/boxy.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/static/js/validationEngine/css/validationEngine.jquery.css" />
		<script type="text/javascript"  src="${ctx}/static/js/datePicker/WdatePicker.js" charset="utf-8"></script>		
		<script type="text/javascript" src="${ctx}/static/js/jquery.corner.js" charset="utf-8"></script>
		<script type="text/javascript" src="${ctx}/static/js/validationEngine/js/languages/jquery.validationEngine-zh_cn.js" charset="utf-8"></script>
		<script type="text/javascript" src="${ctx}/static/js/validationEngine/js/jquery.validationEngine.js" charset="utf-8"></script>
		<script type="text/javascript" src="${ctx}/static/js/validationEngine/js/languages/jquery.validationEngine-zh_cn.js" charset="utf-8"></script>
		<script type="text/javascript" src="${ctx}/static/js/jquery-plugin-boxy/js/jquery.boxy.js" charset="utf-8"></script>
		<script type="text/javascript" src="${ctx}/static/js/zTree/jquery.ztree.all-3.5.js" charset="utf-8"></script>
    </head>
    <body>
    </body>
</html>
