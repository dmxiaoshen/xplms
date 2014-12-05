<%@ page pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title /></title>
<meta http-equiv='Expires' content='0' />
<meta http-equiv='Pragma' content='No-cache' />
<meta http-equiv='Cache-Control' content='No-cache' />
<meta name="keywords" content="" />
<meta name="description" content="" />
<page:applyDecorator name="header" page="/WEB-INF/jsp/common/html-head.jsp" />
<decorator:head />
</head>

<body class="bodyBg">
<div class="bodycenter">
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" flush="true" />
		<div class="contentdiv">
			<%-- <jsp:include page="/WEB-INF/jsp/common/nav-left.jsp" flush="true" /> --%>
			<decorator:body />
		</div>
</div>
<div class="clear"></div>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp" flush="true" />
</body>
</html>
