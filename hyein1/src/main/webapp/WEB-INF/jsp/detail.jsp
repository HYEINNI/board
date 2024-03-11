<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>detail</title>
</head>
<body>

		번호 : ${data.board_no}<br>
		제목 : ${data.title}<br>
		작성자 : ${data.writer}<br>
		내용 : ${data.content}<br>
		날짜 : ${data.regdate}<br>
		조회수 :${data.hitnum}<br>	
		<c:if test="${data.fileName ne null}">
      <img src="/file/${data.fileName}" >파일 업로드<br>
     	</c:if>

	
	<input type="button" value="뒤로가기" onclick="location.href='/main.do'">
	
	<input type="button" value="삭제하기"
		onclick="location.href='/delete.do?boardNo=${data.board_no}'">
		
	<input type="button" value="수정하기"
		onclick="location.href='/updatePage.do?boardNo=${data.board_no}'">
</body>
</html>