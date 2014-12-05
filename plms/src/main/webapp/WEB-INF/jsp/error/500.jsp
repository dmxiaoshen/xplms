<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Error_500</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

</head>

<body>
	<div class="pageError">
		<div class="perRight">
			<h1>出错啦</h1>
			<h2 id="message" class="alert alert-error">
				<% if(exception!=null){out.print(exception.getLocalizedMessage());} %>
			</h2>
			<div class="butcen">
				<input type="button" class="errorBut" onclick="window.location.href='<c:url value='/'/>'" value="Error_backHome"/>
			</div>
		</div>
	</div>
</body>
</html>
