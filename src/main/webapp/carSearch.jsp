<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header style="background-color: grey; height: 70px;"> @LLC
		Car Store </header>
	<form action="carDetail.do" method="post">
		<h3>Please Enter the Car</h3>
		<input type="text" name="fname" /> <input type="submit"
			value="CAR_DETAILS" style="background-color: grey" />
	</form>
	<br /> Car selection...
	<br />
	<h2>${result}</h2>
	<br />
	<img src="${image_car}" />
</body>
</html>