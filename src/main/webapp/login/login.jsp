<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp"%>

<div class="form-container">
<form action="LoginExecute.action" method="post">
	<h2>ログイン</h2>

	<div class="mt-2 text-warning">${errors.get("login")}</div>
	<p>
		<input type="text" name="id" placeholder="ID" required>
	</p>
	<p>
		<input type="password" name="password" placeholder="パスワード" required>
	</p>
	<input type="checkbox" name="chk_d_ps">
	<label for="chk_d_ps">パスワードを表示</label>
	<p>
		<input type="submit" value="ログイン">
	</p>
</form>
</div>

<%@include file="../footer.jsp"%>
