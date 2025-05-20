<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<%@include file="includes/head.jsp" %>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>

<div class="container">
<div class="card w-50 mx-auto my-4"> <!-- mx-auto== centro, my-5 == allieamento -->
<div class="card-header text-center"> User Login</div>
<div class="card.body">
<form action="" method="post">

<form>
  <div class="mb-3">
    <label for="InputUsername" class="form-label">Username</label>
    <input type="email" class="form-control" id="InputUsername" placeholder="Enter Your Username" required>
  </div>
  <div class="mb-3">
    <label for="InputPassword1" class="form-label">Password</label>
    <input type="password" class="form-control" id="InputPassword1" placeholder="******" required>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

<%@include file="includes/footer.jsp" %>
</body>
</html>