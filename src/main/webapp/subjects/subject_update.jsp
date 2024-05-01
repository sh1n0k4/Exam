<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Subject" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<%
    // リクエストスコープから科目情報を取得
    Subject subject = (Subject) request.getAttribute("subject");
    String error_msg_s = (String) request.getAttribute("error_msg_s");
    String error_msg = (String) request.getAttribute("error_msg");
%>

<h2>科目情報変更</h2>
<form action="SubjectUpdateExecute.action" method="post">
<label>科目コード</label><br>
<p><input type="text" name="cd" value="${subject.cd}" readonly></p><br>
<% 
if (error_msg_s != null) {
%>
    <div>
        <%= error_msg_s %>
    </div>
<%
}
%>
<label>科目名</label><br>
<input type="text" name="name"><br>
<% 
if (error_msg != null) {
%>
    <div>
        <%= error_msg %>
    </div>
<%
}
%>
   <input type="hidden" name="school_cd" value="${request.getAttribute("school_cd")}"><br>

<input type="submit" value="変更"><br>
</form>

<a href="SubjectList.action">戻る</a>

<%@include file="../footer.jsp" %>