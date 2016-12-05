<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글보기</title>
</head>
<body>
<table width="500" align="center" border="1">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>제목</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<c:forEach items="${list}" var="vo"> <%-- 향상된 for문 --%>
		<tr>
			<td>${vo.idx}</td>
			<td>${vo.name}</td>
			<td>
				
				<c:forEach begin="1" end="${vo.indent}" step="1" > <%-- 일반 for문. begin부터 end까지 step씩 증가하라! (step 생략시 1이 자동으로 들어감) --%>
					☞
				</c:forEach>
				
				<a href="increment?idx=${vo.idx}">${vo.title}</a> <%-- 얘를 클릭하면 컨트롤러에 "/increment" 해당으로 ==> readCount 1 증가하기를 위해! --%>
			</td>
			<%-- <td>${vo.writeDate}</td> --%> <%--왼쪽 대신에 아랫줄로--%>
			<td><fmt:formatDate value="${vo.writeDate}" pattern="yyyy.MM.dd(E) a h:mm"/></td>
			<td>${vo.readCount}</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="5" align="right">
			<input type="button" value="글쓰기" onclick="location.href='write'"/>
		</td>
	</tr>
</table>
</body>
</html>