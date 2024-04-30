<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>

<h2>ログイン</h2>

<% 
String error_msg = (String) request.getAttribute("error_msg");
if (error_msg != null) {
%>
    <div>
        <%= error_msg %>
    </div>
<%
}
%>

<form action="LoginExecute.action" method="post">
	<p>
		ID<input type="text" name="id">
	</p>
	<p>
		パスワード<input type="password" name="password">
	</p>
	<input type="checkbox" name="chk_d_ps">
	<label>	パスワードを表示</label>
	<p>
		<input type="submit" value="ログイン">
	</p>
</form>

<%@include file="../footer.jsp"%>
