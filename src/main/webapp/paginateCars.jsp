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

	$(document).ready(function() {
		
		$("#previous").click(function(){
			//${pageContext.request.contextPath}/showCarsWithPaginationAjax.do?page=${carPaginationForm.currentPage - 1}
			//alert("Previous...clicked");
		
			var ppage= $("#previous").attr("pageNo");
			if(ppage == 0){
			  $("#next").attr("pageNo",1);
   			  return;
			}
			alert("going to page : "+ ppage);
			
			$.getJSON(ccontextPath+ "/rest/showCarsWithPaginationAjax.do",{page : ppage}, function(jsonResponse){
				
				var tableContent = "";
				if (!jQuery.isEmptyObject(jsonResponse)) {
					
					var itemResponse = jsonResponse["carFormList"];
					
					jQuery.each(itemResponse,function(i, item) {
						// alert("Name: "+ item["name"]);
						tableContent = tableContent
								+ "<tr><td>"
								+ (i + 1)
								+ "</td><td style=\"color: blue;\">"
								+ item["name"]
								+ "</td><td>"
								+ item["price"]
								+ "</td><td>"
								+ item["transmission"]
								+ "</td><td>"
								+ item["warranty"]
								+ "</td><td>"
								+ item["horsePower"]
								+ "</td><td><img src=\"findImageById.do?cid="
								+ item["sno"]
								+ "\"style=\"width: 60px;\" class=\"img-circle\"></td>";
						tableContent = tableContent
								+ "<td><a href=\"editCarById.do?cid="
								+ item["sno"]
								+ "\"><img src=\"img/edit.jpeg\" style=\"width: 25px;\" /></a>&nbsp;&nbsp;<a href=\"javascript: deleteCarByCid("
								+ item["sno"]
								+ ")\"><img src=\"img/delete.jpeg\" style=\"width: 25px;\"/></a></td></tr>";
				    }); // jQuery.each()
						    
					var currentPage	= jsonResponse["currentPage"];
					var noOfPages = jsonResponse["noOfPages"];  
					var recordsPerPage = jsonResponse["recordsPerPage"];  
					
					if(currentPage > 1){
						$("#previous").attr("pageNo", currentPage-1);
						$("#next").attr("pageNo", currentPage-1);
					}else{
						if(currentPage != 1){
							$("#next").attr("pageNo", currentPage-1);
						}
						//alert("currentPage = 1 or less ");
						$("#previous").attr("pageNo", currentPage-1);					
						$("#previous").removeAttr("style");
		            	$("#previous").css({'text-decoration':'none', 'color':'gray', 'pointer-events':'none'});		
					}//else
					
					var startIndex = (currentPage-1)*recordsPerPage + 1;
					var totalNoOfRecords = jsonResponse["noOfRecords"];
					var endIndex = jsonResponse["noOfRecords"];;
					
					if( ( (currentPage-1)*recordsPerPage + recordsPerPage) > totalNoOfRecords){
						endIndex = jsonResponse["noOfRecords"];
						
					}else{
						endIndex = (currentPage-1)*recordsPerPage + recordsPerPage;
					}//else
					
					var paginationHeader = startIndex +" - "+ endIndex + " Of  "+totalNoOfRecords;	
					$("#paginationHeader").html(paginationHeader);
						    
				} else {
						alert("Unable to retrieve the data");
				}//else
				$("#tbodyContent").html(tableContent);	
				
			});  //$.getJSON
		});//$("#previous").click(function()
		
		$("#next").click(function(){
			//${pageContext.request.contextPath}/showCarsWithPaginationAjax.do?page=${carPaginationForm.currentPage + 1}
			//alert("Next...clicked");
			var ppage= $("#next").attr("pageNo");
			alert("going to page"+ ppage);
			$.getJSON(ccontextPath+ "/rest/showCarsWithPaginationAjax.do",{page : ppage}, function(jsonResponse){
				
				var tableContent = "";

				if (!jQuery.isEmptyObject(jsonResponse)) {
					
					var itemResponse = jsonResponse["carFormList"];
					
				    jQuery.each(itemResponse,function(i, item) {
					    // alert("Name: "+ item["name"]);
						tableContent = tableContent
								+ "<tr><td>"
								+ (i + 1)
								+ "</td><td style=\"color: blue;\">"
								+ item["name"]
								+ "</td><td>"
								+ item["price"]
								+ "</td><td>"
								+ item["transmission"]
								+ "</td><td>"
								+ item["warranty"]
								+ "</td><td>"
								+ item["horsePower"]
								+ "</td><td><img src=\"findImageById.do?cid="
								+ item["sno"]
								+ "\"style=\"width: 60px;\" class=\"img-circle\"></td>";
						tableContent = tableContent
								+ "<td><a href=\"editCarById.do?cid="
								+ item["sno"]
								+ "\"><img src=\"img/edit.jpeg\" style=\"width: 25px;\" /></a>&nbsp;&nbsp;<a href=\"javascript: deleteCarByCid("
								+ item["sno"]
								+ ")\"><img src=\"img/delete.jpeg\" style=\"width: 25px;\"/></a></td></tr>";
				    }); // jQuery.each()
						    
					var currentPage	= jsonResponse["currentPage"];
					var noOfPages = jsonResponse["noOfPages"];  
					var recordsPerPage = jsonResponse["recordsPerPage"];  
					
					if(currentPage < noOfPages){
						$("#next").attr("pageNo", currentPage + 1);
					   // $("#previous").attr("pageNo", currentPage + 1);
					}else{
						// $("#next").hide();
						$("#next").removeAttr("style");
		            	$("#next").css({'text-decoration':'none', 'color':'gray', 'pointer-events':'none'});						
					}//else
					
					var startIndex = (currentPage-1)*recordsPerPage + 1;
					var totalNoOfRecords = jsonResponse["noOfRecords"];
					var endIndex = 0;
					
					if( ( (currentPage-1)*recordsPerPage + recordsPerPage) > totalNoOfRecords){
						endIndex = jsonResponse["noOfRecords"];
						
					}else{
						endIndex = (currentPage-1)*recordsPerPage + recordsPerPage;
					}//else
					
					var paginationHeader = startIndex +" - "+ endIndex + " Of  "+totalNoOfRecords;	
					$("#paginationHeader").html(paginationHeader);
					
					//code for enabling the previous link which was initially disabled
					if(currentPage!=1){
						$("#previous").removeAttr("style");
		            	$("#previous").css({' text-decoration':'underline !important', 'color':'#075798 !important','cursor':'pointer !important'});
						$("#previous").attr("pageNo", currentPage-1);
					}
						    
				} else {
						alert("Unable to retrieve the data");
				}//else
				$("#tbodyContent").html(tableContent);	
				
			}); //$.getJSON
			
		}); //$("#next").click(function()
		
		loadDataByTransmission();
		sortByPriceAscDesc();

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
		<%-- <span style="margin-left: 10%;">Filter Cars By Transmission Type:
			 <cf:form commandName="carSubForm">
					<cf:select path="transmission" class="form-control"
						style="width: 30%; margin-left: 10%;  margin-bottom: 3%">
						<cf:options items="${transmissionList}" />
						<option>All</option>
					</cf:select>
			</cf:form>
		</span> --%>

		<%-- <span style="float: right; margin-right: 160px;">
			${carPaginationForm.currentPage} Of  ${carPaginationForm.noOfPages}
			With Total Of  ${carPaginationForm.noOfRecords} Records
	    </span>  --%>
		
		<br> <br> <br>
		<span style="float: right; margin-right: 150px;" id="paginationHeader">
			${(carPaginationForm.currentPage-1)*carPaginationForm.recordsPerPage+1} - 
			<c:if test="${(carPaginationForm.currentPage-1)*carPaginationForm.recordsPerPage+carPaginationForm.recordsPerPage gt carPaginationForm.noOfRecords}">
	          ${carPaginationForm.noOfRecords}
            </c:if> 
            <c:if test="${(carPaginationForm.currentPage-1)*carPaginationForm.recordsPerPage+carPaginationForm.recordsPerPage lt carPaginationForm.noOfRecords}">
    		   ${(carPaginationForm.currentPage-1)*carPaginationForm.recordsPerPage+carPaginationForm.recordsPerPage}
     	   </c:if> of ${carPaginationForm.noOfRecords}
		</span>
	    
	    <br>
		<br>
		<table class="table table-bordered"
			style="width: 80%; margin-left: 10%">
			<thead>
				<tr>
					<th>SNo</th>
					<th>Name</th>
					<th>Price USD$ &nbsp; <img src="img/arrow_down.jpeg"
						id="sortPrice">
					</th>
					<th>Transmission</th>
					<th>Warranty</th>
					<th>Horsepower</th>
					<th>Image</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody id="tbodyContent">

				<c:forEach items="${carPaginationForm.carFormList}" var="item" varStatus="var">
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
		
		<br> <br> 
		&nbsp;&nbsp;
		<a href="#" id="previous"
			pageNo="${carPaginationForm.currentPage - 1}"
			style="text-decoration: none; color: gray; pointer-events: none;">
			Previous
	    </a> 
			
			&nbsp;&nbsp; | &nbsp;&nbsp;

		<c:if
			test="${carPaginationForm.currentPage lt carPaginationForm.noOfPages}"> 
			<a href="#" id="next" pageNo="${carPaginationForm.currentPage + 1}">Next</a>
		</c:if>

	</div>
</body>
</html>
