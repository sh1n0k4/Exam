<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<%@include file="../sidebar.jsp" %>

<section>
	<h2>学生管理</h2>
	<div>
		<a href="StudentCreate.action">新規登録</a>
	</div>
	
	<form method="get">
		<div id="filter">
			<div>
				<label class="form-label" for="student-f1-select">入学年度</label>
				<select class="form-select" id="student-f1-select" name="f1">
					<option value="0">--------</option>
					<c:forEach var="year" items="${ent_year_set}">
					<%-- 現在のyear選択されていたf1が一致していた場合selectedを追記 --%>
					<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
					</c:forEach>
				</select>
			</div>
			
			<div>
				<label class="form-label" for="student-f2-select">クラス</label>
				<select class="form-select" id="student-f2-select" name="f2">
					<option value="0">--------</option>
					<c:forEach var="num" items="${class_num_set}">
					<%-- 現在のnum選択されていたf2が一致していた場合selectedを追記 --%>
					<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
			</div>
			
			<div>
				<label class="form-check-label" for="student-f3-check">在学中
					<%-- パラメータf3が存在している場合はcheckを追記 --%>
					<input class="form-check-input" type="checkbox" 
					id="student-f3-check" name="f3" value="t"
					<c:if test="${!empty f3}">checked</c:if> />
				</label>
			</div>
			
			<div>
				<button class="btn-secondary" id="filter-button">絞込み</button>
			</div>
			<div class="mt-2 text-warning">${errors.get("f1")}</div>
		</div>
	</form>
	<c:choose>
		<c:when test="${students.size()>0}">
		<div>検索結果：${students.size()}件</div>
		<table class="table table-hover">
			<tr>
				<th>入学年度</th>
				<th>学生番号</th>
				<th>氏名</th>
				<th>クラス</th>
				<th class="text-center">在学中</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="student" items="${students}">
				<tr>
					<td>${student.entYear}</td>
					<td>${student.no}</td>
					<td>${student.name}</td>
					<td>${student.classNum}</td>
					<td class="text-center">
						<%-- 在学フラグがたっている場合「○」それ以外は「✕」を表示 --%>
						<c:choose>
							<c:when test="${student.isAttend()}">
								○
							</c:when>
							<c:otherwise>
								✕
							</c:otherwise>
						</c:choose>
					</td>
					<td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
					<td><a href="StudentDelete.action?no=${student.no}">削除</a></td>
				</tr>
			</c:forEach>
		</table>
		</c:when>
		<c:otherwise>
			<div>学生情報が存在しませんでした</div>
		</c:otherwise>
	</c:choose>
</section>

<%@include file="../footer.jsp" %>