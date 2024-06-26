<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2>科目管理</h2>
<a href="SubjectCreate.action">新規登録</a>

<table>
	<tr>
		<th>科目コード</th>
		<th>科目名</th>
	</tr>
	<c:forEach var="subject" items="${subjects}">
		<tr> 
			<td>${subject.cd}</td>
			<td>${subject.name}</td>
			<td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
			<td><a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td>
		</tr>
	</c:forEach>
</table>

<%@include file="../footer.jsp" %>