<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/project/user?cmd=join" method="post" onsubmit="return valid()">
		
	
		<div class="form-group">
			<input type="text" name="username"  class="form-control" placeholder="Enter Username"  required/>
		</div>
		
		<div class="form-group">
		<input type="password" name="password"  class="form-control" placeholder="Enter Password"  required/>
		</div>
		
		<div class="form-group">
			<input type="email"  name="email"  class="form-control" placeholder="Enter Email" required />
		</div>
		
		<div class="form-group">
			<select name="role">
			<option>user</option>
			<option>admin</option>
			</select>
		</div>
		
		<br/>
		<button type="submit" class="btn btn-primary">회원가입완료</button>
	</form>
</div>
</body>
</html>