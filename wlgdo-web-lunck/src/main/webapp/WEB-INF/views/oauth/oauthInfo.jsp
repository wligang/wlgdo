<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" /> 
<title>用户基本信息</title>
</head>
<body>
	<div style="text-align: center;">
		<h2>
			尊敬的用户<font color="red"><b>【${userInfo.nickName}】</b></font>
		</h2>
		<h3>
			您已对商户<font color="red"><b>【${orgInfo.orgName}】</b></font>进行了授权
		</h3>
		<h4>${date}</h4>
	</div>

</body>
</html>