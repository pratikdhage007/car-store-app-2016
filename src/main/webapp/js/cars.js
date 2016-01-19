function loadDataFromServer(jsonResponse) {
	var tableContent = "";

	if (!jQuery.isEmptyObject(jsonResponse)) {
		jQuery
				.each(
						jsonResponse,
						function(i, item) {
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
						});
	} else {

	}
	$("#tbodyContent").html(tableContent);
}// loadDataFromServer()

function deleteCarByCid(cid) {
	alert("--------()()()()()()" + cid);
	// This call will a record from database using primary key
	// and remaining result will be rendered to the GUI

	$.getJSON(ccontextPath + "/rest/car/" + cid, function(jsonResponse) {
		loadDataFromServer(jsonResponse);
	});
}// deleteCarByCid()

function loadDataByTransmission() {
	$("#transmission").change(function() {
		// accessing selected value inside drop down
		var selectedOption = $("#transmission").val();

		$.getJSON(ccontextPath + "/rest/cars/bytransmission", {
			transmission : selectedOption
		}, function(jsonResponse) {
			loadDataFromServer(jsonResponse);
		});// end of getJSON

	})
	// alert("selectedOption: "+ selectedOption);
}// loadDataByTransmission()

function sortByPriceAscDesc() {
	$("#sortPrice").click(
			function() {
				
				var transmissionOption = $("#transmission").val();
				var sourceValue = $("#sortPrice").attr("src");

				if (sourceValue == "img/arrow_down.jpeg") {
					$("#sortPrice").attr("src", "img/arrow_up.jpeg");
					// sortCars/{porder}
					$.getJSON(ccontextPath + "/rest/sortCars/asc",{ transmissionType : transmissionOption }, function(
							jsonResponse) {
						loadDataFromServer(jsonResponse);
					});

				} else {
					$("#sortPrice").attr("src", "img/arrow_down.jpeg");
					// sortCars/{porder}
					$.getJSON(ccontextPath + "/rest/sortCars/desc", { transmissionType : transmissionOption }, function(
							jsonResponse) {
						loadDataFromServer(jsonResponse);
					});
				}
			});
}// sortByPriceAscDesc()

