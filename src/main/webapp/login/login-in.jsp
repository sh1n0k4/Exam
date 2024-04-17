<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.html"%>

<!-- Lonin.actionにアクセス -->
<form action="Login.action" method="post">
	<p>
		ID<input type="text" name="id">
	</p>
	<p>
		パスワード<input type="password" name="password">
	</p>
	<p>
		<input type="submit" value="ログイン">
	</p>
</form>

<%@include file="../footer.html"%>
