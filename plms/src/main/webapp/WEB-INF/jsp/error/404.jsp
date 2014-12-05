<%@ page pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>

<%response.setStatus(200);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Error_404</title>
</head>

<body>
	<div class="pageNull">
		<div class="perRight">
			<h1>Error_404</h1>
			<h2>页面没找到</h2>
			<div class="butcen">
				<input type="button" class="errorBut" onclick="window.location.href='<c:url value='/'/>'" value="Error_backHome"/>
			</div>
		</div>
	</div>
</body>
</html>
