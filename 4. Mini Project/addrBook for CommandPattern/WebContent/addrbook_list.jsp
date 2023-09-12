<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,com.addrbook.model.*"
    errorPage="addrbook_error.jsp"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 목록화면</title>
</head>
<body>
  <div align="center">
   <h1>주소록: 목록화면</h1>
   <hr>
   <h2><a href="addrbook_form.jsp">주소록 등록</a></h2>
   <table border="1">
    <tr align="center">
     <td>번호</td>
     <td>이름</td> 
     <td>전화번호</td> 
     <td>생일</td> 
     <td>회사</td> 
     <td>이메일</td>
     <td>메모</td> 
     <td>비고</td> 
     </tr>
     
      
    <% ArrayList<AddrDto> addrList= (ArrayList<AddrDto>) request.getAttribute("addrList");
    if(addrList != null) {
      for(AddrDto addr : addrList) { %>
        <tr align="center">
          <td><%= addr.getId() %></td>
          <td><%= addr.getName() %></td>
          <td><%= addr.getTel() %></td>
          <td><%= addr.getBirth() %></td>
          <td><%= addr.getComdept() %></td>
           <td><%= addr.getEmail() %></td>
          <td><%= addr.getMemo() %></td>
          <td><a href="addrbook_edit_form.jsp?id=<%= addr.getId() %>">수정</a></td>
        </tr>
      <%
      }
    } 
    %>
    	
   </table>
  </div>

</body>
</html>