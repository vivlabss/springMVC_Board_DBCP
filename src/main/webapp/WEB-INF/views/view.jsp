<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="update" method="post">
<table width="500" align="center" border="1">
	<tr><th colspan="2">게시글보기</th></tr>
	<tr>
		<td width="100">번호</td>
		<td>${view.idx}
			<input type="hidden" name="idx" value="${view.idx}"/>
			<%-- 글을 수정할 때, idx 를 가지고 넘어가야하지만, post 방식으로 넘어가기때문에 글 번호를 숨겨서 넘어가게 하자(혹은 보이게하되, readonly 속성 넣어서 해도됨(idx는 pk니까 함부로 바꾸게 하면 안되니까 이렇게 함)) --%>
		</td>
	</tr>	
	<tr>
		<td>조회수</td>
		<td>${view.readCount}</td>
	</tr>	
	<tr>
		<td>이름</td>
		<td>
			<%-- <input type="text" name="name" value="${view.name}"/> --%>
			<%-- <input type="text" name="name" value="${view.name}" readonly="readonly"/> --%>
			${view.name}
		</td>
	</tr>	
	<tr>
		<td>제목</td>
		<td><input type="text" name="title" value="${view.title}"/></td>
	</tr>	
	<tr>
		<td>내용</td>
		<td><textarea rows="10" cols="50" name="content">${view.content}</textarea></td>
	</tr>	
	<tr>
		<td colspan="2" align="center">
			<input type="submit"  value="수정"/>
			<input type="button"  value="삭제" onclick="location.href='delete?idx=${view.idx}'"/> <%-- 삭제할 때 idx를 가져가야하므로, 삭제페이지로 넘어갈때 idx 묶어줌 --%>
			<input type="button"  value="답변" onclick="location.href='reply?idx=${view.idx}'"/> <%-- 답변달 때 idx를 가져가야하므로, 답변 작성 페이지로 넘어갈때 idx 묶어줌 --%> 
			<input type="button"  value="목록1" onclick="location.href='list'"/> <%-- 목록으로갈 땐 idx 필요없으므로 주소만 가져가도됨 --%>
<%--		<input type="button"  value="목록2" onclick="history.back()"/> 
			<input type="button"  value="목록3" onclick="history.go(-1)"/>
			목록1은 list.jsp 를 호출하는거고, 목록2는 history 사용, 목록3은 history -1 이용.
			 목록1은 readCount가 1 증가된 상태로 목록으로 넘어가지만, 목록 2와 3은 이전페이지로 이동하기때문에 readCount가 1증가하지 않은 화면이 나온다. ==> 이전페이지로 이동한 개념이기때문에! 하지만 여기서는 increment거쳐서 오기때문에 history가 안먹...?읭 --%>
		</td>
	</tr>
</table>
</form>
</body>
</html>