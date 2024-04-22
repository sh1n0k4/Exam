<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2>科目情報登録</h2>
<form id="subjectForm">
<label>科目コード</label><br>
<input type="text" id="cd"><br>
<label>科目名</label><br>
<input type="text" id="name"><br>
<input type="submit" value="登録" a href="SubjectCreateExecute.action">
</form>


<%@include file="../footer.jsp" %>