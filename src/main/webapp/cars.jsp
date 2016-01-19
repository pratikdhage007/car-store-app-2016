<%@ taglib uri="http://www.springframework.org/tags/form" prefix="cf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Car Display Form</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- <link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
	 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="js/cars.js"></script>
<script type="text/javascript">
			
		//http://localhost:8090/car-store-app/
		var ccontextPath = "${pageContext.request.contextPath}";	
		
		$(document).ready(function(){	
		    
			loadDataByTransmission();		  
			sortByPriceAscDesc() ;
			 
		}); //end of ready handler..............
		
	</script>
<style type="text/css">
.img.zoom {
	width: 60px;
	-webkit-transition: all .2s ease-in-out;
	-moz-transition: all .2s ease-in-out;
	-o-transition: all .2s ease-in-out;
	-ms-transition: all .2s ease-in-out;
}

.transition {
	-webkit-transform: scale(4);
	-moz-transform: scale(4);
	-o-transform: scale(4);
	transform: scale(4);
}
</style>
</head>

<body>
	<!-- <img src="cars/4.jpg" /> -->
	<div class="bs-example">
		<span style="margin-left: 10%;">Filter Cars By Transmission Type:
			 <cf:form commandName="carSubForm">
					<cf:select path="transmission" class="form-control"
						style="width: 30%; margin-left: 10%;  margin-bottom: 3%">
						<cf:options items="${transmissionList}" />
						<option>All</option>
					</cf:select>
			</cf:form>
		</span>

		<table class="table table-bordered"
			style="width: 80%; margin-left: 10%">
			<thead>
				<tr>
					<th>SNo</th>
					<th>Name</th>
					<th>Price USD$ &nbsp; <img src="img/arrow_down.jpeg" id="sortPrice"> </th>
					<th>Transmission</th>
					<th>Warranty</th>
					<th>Horsepower</th>
					<th>Image</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody id="tbodyContent">

				<c:forEach items="${carForms}" var="item" varStatus="var">
					<tr>
						<td>${var.count}</td>
						<td style="color: blue;">${item.name}</td>
						<td>${item.price}</td>
						<td>${item.transmission}</td>
						<td>${item.warranty}</td>
						<td>${item.horsePower}</td>
						<td><img src="findImageById.do?cid=${item.sno}"
							style="width: 60px;" class="img-circle"></td>
						<td><a href="editCarById.do?cid=${item.sno}"><img
								src="img/edit.jpeg" style="width: 25px;" /></a>&nbsp; <a
							href="deleteCarById.do?cid=${item.sno}"><img
								src="img/delete.jpeg" style="width: 25px;" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
