<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    errorPage="addrbook_error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset ="UTF-8">
<title>주소록 작성화면</title>
</head>
<body>
<div align="center">
  <h1>주소록 작성화면</h1>
  <HR>
   <a href="list.do">[주소록 목록으로]</a><p>
   <form action="insert.do" name="inputForm" method="post" >
    <table border="1">
     <tr>
      <td>이름</td>
       <td><input type="text" name="name"></td>
     </tr>
     <tr> 
       <td>email</td>
       <td><input type="email" name="email"></td>
     </tr>
     <tr>
       <td>전화번호</td>
       <td><input type="text" name="tel"></td>
     </tr>
     <tr>
       <td>생일</td>
       <td><input type="text" name="birth"></td>
     </tr>
     <tr>
      <td>회사</td>
      <td><input type="text" name="comdept" ></td>
     </tr>
     <tr>
       <td>메모</td>
       <td><input type="text" name="memo"></td>
     </tr>
     <tr>
      <td><input type="submit" value="저장"/></td>
      <td><input type="reset" value="취소"/></td>
     </tr>
</table>
</form>
</div>

</body>
</html>