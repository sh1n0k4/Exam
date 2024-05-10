<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>


<h2>科目情報登録</h2>
<form action="SubjectCreateExecute.action" method="post">

<div>
<label for="cd">科目コード</label><br>
<input type="text" name="cd" id="cd" required><br>
</div>

<div>
<label for="name">科目名</label><br>
<input type="text" name="name" id="name" required><br>
</div>

<input type="submit" value="登録"><br>
</form>

<a href="SubjectList.action">戻る</a>


<%@include file="../footer.jsp" %>