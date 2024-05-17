<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>
<%@include file="test_list.jsp" %>

<c:choose>
		<c:when test="${tlst.size()>0}">
		<table class="table table-hover">
			<tr>
				<th>科目名</th>
				<th>科目コード</th>
				<th>回数</th>
				<th>点数</th>
			</tr>
			<c:forEach var="student" items="${tlst}">
				<tr>
					<td>${tlst.name}</td>
					<td>${tlst.cd}</td>
					<td>${tlst.no}</td>
					<td>${tlst.point}</td>
					
				</tr>
			</c:forEach>
		</table>
		</c:when>
		<c:otherwise>
			<div>氏名：${student.name}(${student.no})
			成績情報が存在しませんでした
			</div>
		</c:otherwise>
	</c:choose>

<%@include file="../footer.jsp" %>