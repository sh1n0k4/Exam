<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Subject" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<%
    // リクエストスコープから科目情報を取得
    Subject subject = (Subject) request.getAttribute("subject");
%>

<h2>科目情報変更</h2>
<form action="SubjectUpdateExecute.action" method="post">
<label>科目コード</label><br>
<input type="text" name="cd" value="${subject.cd}" readonly><br>

<label>科目名</label><br>
<input type="text" name="name" placeholder="科目名を入力してください" required><br>
<input type="hidden" name="school_cd"><br>

<input type="submit" value="変更"><br>
</form>

<a href="SubjectList.action">戻る</a>

<%@include file="../footer.jsp" %>