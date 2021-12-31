<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO mv = (MemberVO)request.getAttribute("mv");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 변경</title>
</head>
<body>
	<!-- <form action="/ServletExam/member/update.do" method="post"> -->
	<%-- <form action="<%=request.getContextPath() %>/member/update.do" method="post"> --%>
	<form action="update.do" method="post">
		
		<table>
			<tr>
				<td>I D:</td>
				<td><%=mv.getMemId() %><input type="hidden" name="memId" value="<%=mv.getMemId()%>"></td>
			</tr>
			<tr>
				<td>이름:</td>
				<td><input type="text" name="memName" value="<%=mv.getMemName()%>"></td>
			</tr>
			<tr>
				<td>전화번호:</td>
				<td><input type="text" name="memTel" value="<%=mv.getMemTel()%>"></td>
			</tr>
			<tr>
				<td>주소:</td>
				<td><textarea name="memAddr" rows="3" cols="20"><%=mv.getMemName()%></textarea></td>
			</tr>
		</table>
		<input type="submit" value="회원등록">
	
	</form>
</body>
</html>