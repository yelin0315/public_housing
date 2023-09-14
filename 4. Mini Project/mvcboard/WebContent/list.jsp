<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC Board</title>
</head>
<body>

  <table width="500" cellpadding="0" cellspacing="0" border="1">
    <tr>
      <td>번호</td>
      <td>이름</td>
      <td>제목</td>
      <td>날짜</td>
      <td>히트</td>
    </tr>    
    <c:forEach items="${list}" var="dto">
    <tr>
      <td>${dto.bId}</td>
      <td>${dto.bName}</td>
      <td>
      
     <c:if test="${dto.bIndent > 1}">
    <c:forEach begin="1" end="${dto.bIndent - 1}">&nbsp;</c:forEach>
    </c:if>
    └
        
        <a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a></td>
      <td>${dto.bDate}</td>
      <td>${dto.bHit}</td>
    </tr>    
    </c:forEach>
    <tr>
      <td colspan="5"><a href="write_view.do">글작성</a></td>
    </tr>
    
  </table>
 

</body>
</html>