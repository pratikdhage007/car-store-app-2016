package com.car.rest.webservice;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.constant.CarAppURI;
import com.car.controller.model.CarDetailsForm;
import com.car.controller.model.CarList;
import com.car.controller.model.CarPaginationForm;
import com.car.service.CarService;
import com.car.sorter.SortByPriceAsc;
import com.car.sorter.SortByPriceDesc;

@Controller
@Scope("request")
public class CarRestController {

	@Autowired
	@Qualifier("ImplCarService")
	private CarService carService;

	@RequestMapping(value = "domessage", method = RequestMethod.GET)
	@ResponseBody
	public String message() {
		return "In CarRestController's ----> message()...n thats a resource  ";
	}

	@RequestMapping(value = "showCarsWithPaginationAjax.do", method = RequestMethod.GET, produces= {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public CarPaginationForm carsWithPaginationAjax(@RequestParam(value = "page", required = false) String page) {
		int noOfRecordsPerPage = 3;
		int currentPage = 0;

		if(page == null){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(page);
		}
		CarPaginationForm carPaginationForm = carService
				.findCarsWithPagination((currentPage-1)*noOfRecordsPerPage,
						noOfRecordsPerPage);

		carPaginationForm.setCurrentPage(currentPage);
		carPaginationForm.initPagination();
		return carPaginationForm;
	}

	@RequestMapping(value = CarAppURI.FIND_ALL_CARS, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public CarList showCars() {
		CarList carList = new CarList();
		List<CarDetailsForm> cfList = carService.findCars();
		carList.setCarForm(cfList);
		return carList;
	}

	@RequestMapping(value = CarAppURI.FIND_ALL_CARS_AS_XML, method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE})
	@ResponseBody
	public CarList showCarsAsXml(){
		CarList carList = new CarList();
		List<CarDetailsForm> cfList = carService.findCars();
		carList.setCarForm(cfList);
		return carList;
	}

	@RequestMapping(value = CarAppURI.ADD_UPDATE_CAR, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String uploadCarData(@RequestBody CarDetailsForm cdForm) {

		carService.addUpdateCarInfo(cdForm);
		return "Car Added Successfully!!!";
	}

	// sending data as a part of URI
	// http://localhost:8090/car-store-app/rest/car/9
	@RequestMapping(value = CarAppURI.DELETE_CAR_BY_CID, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<CarDetailsForm> deleteCar(@PathVariable("cid") String cid) {
		String restOfData = carService.deleteCarByCid(cid);
		List<CarDetailsForm> carForms = carService.findCars();
		return carForms;
	}

	@RequestMapping(value = CarAppURI.ADD_UPDATE_CAR, method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String updateCarData(@RequestBody CarDetailsForm cdForm) {

		carService.addUpdateCarInfo(cdForm);
		return "Car Added Successfully!!!";
	}

	// Here calling is coming from AJAX and not GUI
	@RequestMapping(value = CarAppURI.FIND_ALL_CARS_BY_TRANSMISSION, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<CarDetailsForm> showCarsByTransmission(@RequestParam("transmission")String transmissionType) {
		List<CarDetailsForm> carForms = null;
		if((transmissionType).equalsIgnoreCase("All")){
			carForms = carService.findCars();
		}else{
			carForms = carService.findAllCarsByTransmission(transmissionType);
		}
		return carForms;
	}

	@RequestMapping(value = CarAppURI.SORT_CARS_BY_PRICE, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
    public List <CarDetailsForm> sortCarsByPrice(@PathVariable("porder")String orderBy, @RequestParam("transmissionType")String transmission){

		List <CarDetailsForm> carForm = null;
		if(transmission.equalsIgnoreCase("All")){
			carForm = carService.findCars();
		}else{
			carForm = carService.findAllCarsByTransmission(transmission);
		}

		if(orderBy.equalsIgnoreCase("asc")){
			Collections.sort(carForm, new SortByPriceAsc());
		}else{
			Collections.sort(carForm, new SortByPriceDesc());
		}
		return carForm;
	}

	@RequestMapping(value = "car/temp", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public CarDetailsForm testData() {
		CarDetailsForm cForm = new CarDetailsForm();
		cForm.setName("Rolls Royce Phantom");
		cForm.setPrice(205000);
		cForm.setTransmission("8-Speed Auto");
		cForm.setWarranty(3);
		cForm.setHorsePower("600bhp @ 6,300RPM");
		return cForm;
	}
}
