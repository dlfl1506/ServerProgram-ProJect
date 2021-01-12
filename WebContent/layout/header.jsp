<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서버프로그램 구현 프로젝트 </title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  
  <c:choose>
			<c:when test="${sessionScope.user != null}">
				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/user?cmd=userlist">유저리스트</a>
							</li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/user?cmd=logout">로그아웃</a></li>
					</ul>
				</div>
			</c:when>
			
			<c:when test="${sessionScope.admin != null}">
				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/user?cmd=userlist">유저리스트</a>
							</li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/user?cmd=logout">로그아웃</a></li>
					</ul>
				</div>
			</c:when>
			
			<c:otherwise>
				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/user?cmd=userlist">유저리스트</a>
							</li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/user?cmd=joinForm">회원가입</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/user?cmd=loginForm">로그인</a></li>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>
  
  
</nav>
<br/>