<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<!--  cdn  -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- js 연결  -->
<script type="text/javascript" src="/js/main.js"></script>

<!-- Toast UI Grid CSS -->
<link rel="stylesheet" href="https://uicdn.toast.com/grid/latest/tui-grid.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/tui.pagination/latest/tui-pagination.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/chart/latest/toastui-chart.min.css" />

<!-- Toast UI Grid JavaScript -->
<script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
<script src="https://uicdn.toast.com/tui.pagination/latest/tui-pagination.js"></script>
<script src="https://uicdn.toast.com/chart/latest/toastui-chart.min.js"></script>


<body>
	<select id = "searchOption">
		<option value="">--선택--</option>
		<option value="title">제목</option>
		<option value="writer">작성자</option>
	</select>
	
	<input type="text" id="keyword" placeholder="검색어 입력">
	<button onclick="pageList(1)">검색</button>
	
	<select id = "limit" onchange="onclickLimit()">
		<option value=10>10</option>
		<option value=20>20</option>
		<option value=30>30</option>
		<option value=40>40</option>
	</select>
	
	<div id="grid"></div>
	<div id="pageNum"></div>
	<div id="chart-area"></div>
	<div id="chart"></div>
	
	<input type="button" value="작성하기" 
		onclick="location.href='/insertPage.do'">
		
	<input type="button" value="지도" 
		onclick="location.href='/map.do'">

</body>
</html>