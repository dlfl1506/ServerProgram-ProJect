<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container">
   <c:choose>
			<c:when test="${sessionScope.user != null}">
	<table class="table table-bordered">
    <thead>
      <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>Role</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
    
    	<c:forEach var="result" items="${list}">
		  <tr>
      			<td><c:out value="${result.id}"/></td>
				<td><c:out value="${result.username }"/></td>
				<td><c:out value="${result.email}" /></td>
				<td><c:out value="${result.role}"/></td>
				<c:choose>
				<c:when test="${sessionScope.user.id == result.id}">
        		<td><button onClick="deleteById(${result.id})">삭제</button></td>
        		</c:when>
        		<c:otherwise>
        		<td></td>
        		</c:otherwise>
        		</c:choose>
      </tr>
	</c:forEach>
    </tbody>
  </table>
  
	</c:when>
	
	
	<c:when test="${sessionScope.admin != null}">
	<table class="table table-bordered">
    <thead>
      <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>Role</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
    
    	<c:forEach var="result" items="${list}">
		  <tr>
      			<td><c:out value="${result.id}"/></td>
				<td><c:out value="${result.username }"/></td>
				<td><c:out value="${result.email}" /></td>
				<td><c:out value="${result.role}"/></td>
        		<td><button onClick="deleteById(${result.id})">삭제</button></td>
      </tr>
	</c:forEach>
    </tbody>
  </table>
  
			</c:when>
			<c:otherwise>
			<table class="table table-bordered">
    <thead>
      <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>Role</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
 
    	<c:forEach var="result" items="${list}">
		  <tr>
      			<td><c:out value="${result.id}"/></td>
				<td><c:out value="${result.username }"/></td>
				<td><c:out value="${result.email}" /></td>
				<td><c:out value="${result.role}"/></td>
				<td></td>
      </tr>
	</c:forEach>
    </tbody>
  </table>
			</c:otherwise>
		</c:choose>          
  
  
</div>
<script>
function deleteById(userId){

	$.ajax({
		type: "post",
		url: "/project/user?cmd=delete&id="+userId,
		dataType: "json"
	}).done(function(result){
		if (result.statusCode == 1) {
			console.log(result);
			location.href="index.jsp";
		} else {
			alert("삭제 실패");
		}
	});
}
</script>

</body>
</html>