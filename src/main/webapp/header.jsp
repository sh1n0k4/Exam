<%@page contentType="text/html; charset=UTF-8" %>
<%@ page  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../css/style.css">
		<title>得点管理システム</title>
	</head>
	<body>
		<header>
			<h1>得点管理システム</h1>
			<span>${user.name}様
				<a href="Logout.action">ログアウト</a>
			</span>
		</header>
	