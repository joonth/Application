<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
</head>
<body>
  <%
    Member member = (Member)request.getAttribute("member");
  %>
  <h1>회원정보수정</h1>
  <form action="Update" method="post">
    회원번호 : <input type="text" name="mno" value="<%=member.getMno()%>" readonly><br>
    회원이름 : <input type="text" name="mname" value="<%=member.getMname()%>"><br>
    이메일 : <input type="text" name="email" value="<%=member.getEmail()%>"><br>
    가입일 : <input type="text" name="cre_date" value="<%=member.getCre_date()%>" readonly><br>
    <input type="submit" value="수정">
    <input type="button" value="취소" onclick="location.href='List'">
    <input type="button" value="삭제" onclick="location.href='Delete?mno=<%=member.getMno()%>'">
  </form>
</body>
</html>
