<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2>科目情報変更</h2>
<label>科目コード</label><br>
${code}<br>
<label>科目名</label><br>
<input type="text" id="name"><br>
<input type="submit" value="変更"><br>
<a href="SubjectList.action">戻る</a>

<%@include file="../footer.jsp" %>