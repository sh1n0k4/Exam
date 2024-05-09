<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>


<h2>学生情報登録</h2>
<form action="StudentCreateExecute.action" method="post">

<div>
	<label>入学年度</label><br>
	<select name="ent_year">
		<c:forEach var="year" items="${ent_year_set}">
			<option value="${year}">${year}</option>
		</c:forEach>
	</select><br>
</div>

<div>
<label for="no">学生番号</label><br>
<input type="text" name="no" id="no" placeholder="学生番号を入力してください" required><br>
</div>

<div>
<label for="name">氏名</label><br>
<input type="text" name="name" id="name" placeholder="氏名を入力して下さい" required><br>
</div>

<div>
<label>クラス</label><br>
<select name="class_num">
	<c:forEach var="num" items="${class_num_set}">
		<option value="${num}">${num}</option>
	</c:forEach>
</select><br>
</div>

<input type="submit" name="end" value="登録して終了"><br>
</form>

<a href="StudentList.action">戻る</a>


<%@include file="../footer.jsp" %>