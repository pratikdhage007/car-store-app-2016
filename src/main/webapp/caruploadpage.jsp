<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<header style="background-color: grey; height: 40px;"> @LLC
		CARS !!!!!!!!!!! </header>

	<ff:form action="uploadCarInfo.do" method="post" commandName="carInfoForm" enctype="multipart/form-data">
		<center>
			<img src="cars/5.jpg" />
			<table border="0" style="width: 30%;" cellspacing="10">
			<ff:input type="hidden" path="sno"/>
				<tr>
					<td>Name :</td>
					<td><ff:input type="text" path="name" style="width: 200px;" /></td>
				</tr>

				<tr>
					<td>Price :</td>
					<td><ff:input type="text" path="price" /></td>
				</tr>

				<tr>
					<td>Transmission: </td>
					<td><ff:select path="transmission">
						<ff:options items="${transmissionList}"/>	
					</ff:select></td>
				</tr>
				
				<tr>
					<td>Warranty: </td>
					<td><ff:select path="warranty">
						<ff:options items="${warrantyList}"/>	
					</ff:select></td>
				</tr>

				<tr>
					<td>Horsepower: </td>
					<td><ff:select path="horsePower">
						<ff:options items="${horsePowerList}" />	
					</ff:select></td>
				</tr>
				
				<tr>
					<td>Upload Image : </td>
					<td><input type="file" name="image"></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Upload"
						style="background-color: grey" /></td>
				</tr>

			</table>
		</center>

	</ff:form>
	<br />
	<br />
	<br />
	<br />
	<br />
	<footer style="background-color: grey; height: 20px;">
		cars@llc.com </footer>
</body>
</html>