<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登录</title>
<script type="text/javascript"  src="${ctx}/static/js/jquery-1.7.2.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/static/js/zTree/zTreeStyle/zTreeStyle.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/js/validationEngine/css/validationEngine.jquery.css" />
<script type="text/javascript" src="${ctx}/static/js/jquery.corner.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/js/validationEngine/js/languages/jquery.validationEngine-zh_cn.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/js/validationEngine/js/jquery.validationEngine.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/js/validationEngine/js/languages/jquery.validationEngine-zh_cn.js" charset="utf-8"></script>
<script type="text/javascript">
$(function(){
	var errorCode = "${errorCode}";
	var error = "${error}";
	if(errorCode){
		if(errorCode=="1"){
			$("input[name='username']").validationEngine('showPrompt', error, 'error', "centerRight" , true);
		}
		if(errorCode=="2"){
			$("input[name='captcha']").validationEngine('showPrompt', error, 'error', "centerRight" , true);
		}
	}
	$("#capt").click(function(){
		this.src="${ctx}/login/getCaptcha?time="+(new Date()).getTime();
	});
	
	$("#form").validationEngine({
		validationEventTrigger:"",
		promptPosition : "centerRight",
		scroll : false,
		maxErrorsPerField : 1,
	    onValidationComplete : function(form,valid){
			if(valid){
				return true;
			}
		}
	});	
});
</script>
</head>
<body>
<form id="form" action="${ctx}/login/index" method="post">
<p>
<label>用户名:</label><input type="text" name="username" class="validate[required]" value="${user.username}" />
</p>
<p>
<label>密码:</label><input type="password" name="password" class="validate[required]" value="${user.password}" />
</p>
<p><label>验证码:</label><input type="text" maxlength=4 name="captcha" class="validate[required]"/><img src="getCaptcha" id="capt"/></p>
<p><input type="submit" value="登录" /></p>
</form>
</body>
</html>
