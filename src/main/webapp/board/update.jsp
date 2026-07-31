<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row {
	margin: 0px auto;
	width: 800px;
}
h3 {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>수정하기</h3>
			<form name="frm" action="../board/update_ok.do" method="post">
				<table class="table">
					<tr>
						<th width="15%" class="text-center success">이름</th>
						<td width="85%">
							<input type="text" name="name" size="20" class="input-sm" value="${vo.name }" required>
							<input type="hidden" name="no" value="${vo.no }">
						</td>
					</tr>
					<tr>
						<th width="15%" class="text-center success">제목</th>
						<td width="85%">
							<input type="text" name="subject" size="60" class="input-sm" value="${vo.subject }" required>
						</td>
					</tr>
					<tr>
						<th width="15%" class="text-center success">내용</th>
						<td width="85%">
							<textarea rows="10" cols="62" name="content" required>${vo.content }</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%" class="text-center success">비밀번호</th>
						<td width="85%">
							<input type="password" name="pwd" size="10" class="input-sm" required>
							<c:if test="${msg == 'FAIL'}">
							    <script type="text/javascript">
							        alert("비밀번호가 맞지 않습니다.");
							    </script>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<button type="submit" class="btn-sm btn-danger">수정</button>
							<button type="button" class="btn-sm btn-info" onclick="javascript:history.back()">취소</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>