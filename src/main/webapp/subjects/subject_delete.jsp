<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Subject" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<%
Subject subject = (Subject) request.getAttribute("subject");
%>

<h2>科目情報削除</h2>
<form action="SubjectDeleteExecute.action" method="post">
<p>「${subject.name} (${subject.cd})」を削除してもよろしいですか</p>
<input type="hidden" name="cd" value="${subject.cd}">
<input type="submit" value="削除"><br>
</form>
<a href="SubjectList.action">戻る</a>

<%@include file="../footer.jsp" %>