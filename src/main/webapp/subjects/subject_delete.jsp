<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2>科目情報削除</h2>
<p>「${name} (${code})」を削除してもよろしいですか
<input type="submit" value="削除"><br>
<a href="SubjectList.action">戻る</a>

<%@include file="../footer.jsp" %>