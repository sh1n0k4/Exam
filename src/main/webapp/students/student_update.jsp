<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bean.Student" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<%
    // リクエストスコープから科目情報を取得
    Student student = (Student) request.getAttribute("student");
%>

<h2>学生情報変更</h2>
<form action="StudentUpdateExecute.action" method="post">
<label>入学年度</label><br>
<input type="text" name="ent_year" value="${student.entYear}" readonly><br>
<label>学生番号</label><br>
<input type="text" name="no" value="${student.no}" readonly><br>

<label>氏名</label><br>
<input type="text" name="name" value="${student.name}"><br>

<label>クラス</label><br>
<select name="class_num">
	<c:forEach var="num" items="${class_num_set}">
		<option value="${num}">${num}</option>
	</c:forEach>
</select><br>

<label>在学中</label>
<input type="checkbox" name="is_attend" value="t" 
<c:if test="${student.isAttend()}">checked</c:if> />
<br>

<input type="hidden" name="school_cd" value="${request.getAttribute("school_cd")}"><br>

<input type="submit" value="変更"><br>
</form>

<a href="StudentList.action">戻る</a>

<%@include file="../footer.jsp" %>