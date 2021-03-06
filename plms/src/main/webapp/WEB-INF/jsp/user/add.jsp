<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增用户</title>
<script type="text/javascript"  src="${ctx}/static/js/jquery-1.7.2.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/static/js/zTree/zTreeStyle/zTreeStyle.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/js/validationEngine/css/validationEngine.jquery.css" />
<script type="text/javascript" src="${ctx}/static/js/jquery.corner.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/js/validationEngine/js/languages/jquery.validationEngine-zh_cn.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/js/validationEngine/js/jquery.validationEngine.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/js/validationEngine/js/languages/jquery.validationEngine-zh_cn.js" charset="utf-8"></script>
<script type="text/javascript">
var ctx = "${ctx}";
$(function(){
	/* var errorCode = "${errorCode}";
	var error = "${error}";
	if(errorCode){
		if(errorCode=="1"){
			$("input[name='username']").validationEngine('showPrompt', error, 'error', "centerRight" , true);		
		}
		if(errorCode=="2"){
			$("input[name='captcha']").validationEngine('showPrompt', error, 'error', "centerRight" , true);
		}
	} */

	
	
	
	/* $("#form").validationEngine({
		validationEventTrigger:"",
		promptPosition : "centerRight",
		scroll : false,
		maxErrorsPerField : 1,
/* 		customFunctions:{
			checkCaptcha:checkCaptcha
		}, */
	   /* onValidationComplete : function(form,valid){
			if(valid){
				return true;
			}
		}
	});	 */
	
	$("#formId").validationEngine({
		validationEventTrigger:"",
		promptPosition:"centerRight",
		scroll:false,
		maxErrorsPerField:1,
		customFunctions:{
			checkUsername:checkUsername,
			checkPwd:checkPwd
		},
		onValidationComplete:function(form,valid){
			if(valid){
				return true;
			}
		}
	});
	
	function checkUsername($field,rules,i,options){
		var userName = $field.val();
		
		var isOk = true;
		$.ajax({
			url:ctx+"/user/checkUsername",
			type:"post",
			dataType:"json",
			async:false,
			data:{
				username:userName
			},success:function(data){
				isOk = data;
			}
		});
		
		if(!isOk){
			return "* 用户名已经存在";
		}
	}
	
	function checkPwd($field,rules,i,options){
		var pwd = $field.val();
		var pwdPre = $("input[name='password']").val();
		if(pwd!=pwdPre){
			return "* 两次输入的密码不一致";
		}
	}
	
	 /* function checkCaptcha($field, rules, i, options){
		$(".parentFormform").remove();
		 var parmCode = $field.val();
		 
		    var isOk = false;
		    $.ajax({
		    	type:"post",
		    	async:false,
		    	url:ctx+"/login/checkCaptcha",
		    	data:{
		    		captcha:parmCode
		    	},
		    	dataType:"json",
		    	success:function(data){	    		
		    			isOk = data;	    		
		    	}
		    	
		    });
		    
		    if(isOk){
		    	//$("input[name='captcha']").validationEngine('showPrompt', "* 验证码错误", 'error', "centerRight" , true);
		    	return "* 验证码错误";
		    }
	} */ 
});
</script>
</head>
<body>
<form id="formId" action="${ctx}/user/add" method="post">
<p>
<label>用户名:</label><input type="text" name="username" class="validate[required,funcCall[checkUsername]]"  />
</p>
<p>
<label>密码:</label><input type="password" name="password" class="validate[required]"  />
</p>
<p>
<label>确认密码:</label><input type="password" name="password1" class="validate[required,funcCall[checkPwd]]" />
</p>
<p><input type="submit" value="新增" /></p>
</form>
</body>
</html>
