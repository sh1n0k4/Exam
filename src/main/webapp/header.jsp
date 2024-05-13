<%@page contentType="text/html; charset=UTF-8" %>
<%@ page  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head> 
		<meta charset="UTF-8">
		<title>得点管理システム</title>
		<link rel="stylesheet" href="../css/style.css">
	</head>
	<body>
		<header>
				<h1>得点管理システム
				<span id="logout">
					${user.name}様
					<a href="Logout.action">ログアウト</a>
				</span>
				</h1>
		</header>
	