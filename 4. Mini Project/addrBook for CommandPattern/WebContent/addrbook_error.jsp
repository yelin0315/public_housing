<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<% response.setStatus(200); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외처리 페이지</title>
</head>
<body>
<H2>**예외 발생**</H2><br/>
메시지 내용 : <%=exception.getMessage() %>
</body>
</html>