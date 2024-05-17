<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bean.Subject" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<h2>成績参照</h2>

<!-- setTestListSubject()を呼び出すフォーム -->
<form action="TestList.action" method="post">
    <input type="hidden" name="action" value="setSubject">
		<div>
			<th><label>入学年度</label><th>
			<select name="ent_year">
			<option value="0">--------</option>
			<c:forEach var="year" items="${ent_year_set}">
				<option value="${year}">${year}</option>
			</c:forEach>
			</select><br>
		</div>
			
		<div>
			<label>クラス</label>
			<select name="class_num">
			<option value="0">--------</option>
			<c:forEach var="num" items="${class_num_set}">
				<option value="${num}">${num}</option>
			</c:forEach>
			</select><br>
		</div>
			
			<div>
	            <label class="form-label" for="subjectCd-select">科目</label>
	            <select class="form-select" id="subjectCd-select" name="subjectCd">
	                <option value="0">--------</option>
	                <c:forEach var="subject" items="${subject_set}">
	                    <option value="${subject.cd}" <c:if test="${subject.cd == param.subjectCd}">selected</c:if>>${subject.name}</option>
	                </c:forEach>
	            </select>
	        </div>
	</div>


    <input type="submit" value="検索">
</form>

<!-- setTestListStudent()を呼び出すフォーム -->
<form action="TestList.action" method="post">
    <input type="hidden" name="action" value="setStudent">
    	<div>
    	<labal>学生番号</labal>
    	<input type="text" name="student_no" id="student_id">
    	</div>
    <!-- その他のフォームフィールド -->
    <input type="submit" value="検索">
</form>
