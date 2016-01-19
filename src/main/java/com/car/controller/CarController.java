package com.car.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.car.controller.model.CarDetailsForm;
import com.car.controller.model.CarPaginationForm;
import com.car.service.CarNameService;
import com.car.service.CarService;
import com.car.sorter.SortByPriceAsc;

@Controller
public class CarController {

	@Autowired
	@Qualifier("CarNameService")
	private CarNameService carNameService;

	@Autowired
	@Qualifier("ImplCarService")
	private CarService carService;

	public CarController() {
		System.out.println("in CarController");
	}

	@RequestMapping(value = "deleteCarById.do", method = RequestMethod.GET)
	public String deleteCarById(@RequestParam("cid") String cid, Model model) {
		String carDeleted = carService.deleteCarByCid(cid);
		List<CarDetailsForm> restOfData = carService.findCars();
		model.addAttribute("carForms", restOfData);
		model.addAttribute("entryDeleted", carDeleted);
		return "cars";
	}

	@RequestMapping(value = "editCarById.do", method = RequestMethod.GET)
	public String editCarById(@RequestParam("cid") String cid, Model model) {
		CarDetailsForm result = carService.findCarByCid(cid);
		model.addAttribute("carInfoForm", result);
		return "caruploadpage";
	}

	@RequestMapping(value = "findImageById.do", method = RequestMethod.GET)
	public void findImageById(HttpServletRequest request,
			HttpServletResponse response) {
		String carId = request.getParameter("cid");
		byte[] carImage = carService.findImageByCid(carId);
		ServletOutputStream outputStream;
		try {
			outputStream = response.getOutputStream();
			outputStream.write(carImage);
			outputStream.flush();
		} catch (IOException ie) {

		}
	}

	@RequestMapping(value = "cars.do", method = RequestMethod.GET)
	public String showCars(Model model) {
		CarDetailsForm carForm = new CarDetailsForm();
		// carForm.setTransmission("All");
		model.addAttribute("carSubForm", carForm);
		List<CarDetailsForm> carDetailstList = carService.findCars();
		if (carDetailstList != null) {
			Collections.sort(carDetailstList, new SortByPriceAsc());
		}
		model.addAttribute("carForms", carDetailstList);
		return "cars";
	}

	@RequestMapping(value = "uploadCarInfo.do", method = RequestMethod.GET)
	public String getCarPage(Model model) {

		CarDetailsForm carDetailsForm = new CarDetailsForm();
		model.addAttribute("carInfoForm", carDetailsForm);
		return "caruploadpage";
	}

	@RequestMapping(value = "uploadCarInfo.do", method = RequestMethod.POST)
	public String uploadCarData(
			@ModelAttribute("carInfoForm") CarDetailsForm cdForm, Model model) {

		System.out.println("in....POST method");
		System.out.println(cdForm.toString());

		carService.addUpdateCarInfo(cdForm);
		model.addAttribute("message",
				"car details are uploaded successfully!!!!!");
		if (cdForm.getSno() != 0) {
			return "redirect:/cars.do";
		}
		return "carInfoSucces";
	}

	@RequestMapping(value = "showCarsWithPagination.do", method = RequestMethod.GET)
	public String carsWithPagination(@RequestParam(value = "page", required = false) String page, Model model) {
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
		model.addAttribute("carPaginationForm", carPaginationForm);
		return "paginateCars";
	}

	@RequestMapping(value = "carDetail.do", method = RequestMethod.POST)
	public String viewDetials(HttpServletRequest request, Model model) {

		String carNum = request.getParameter("fname");
		try {
			int num = Integer.parseInt(carNum);
			String cInfo = carNameService.carInfo(num);
			model.addAttribute("result", cInfo);

		} catch (NumberFormatException nfe) {
			model.addAttribute("result",
					"Sorry Car info can not be displayed because " + carNum
							+ " is not a number...");
		}

		carNum = "cars/" + carNum + ".jpg";
		model.addAttribute("image_car", carNum);
		return "carSearch";

	}

	@ModelAttribute("transmissionList")
	public List<String> populateTransmissionList() {

		List<String> transList = carService.findAllTransmission();
		// List<String> transmissionList = new ArrayList<String>();
		// transmissionList.add("8-speed automatic");
		// transmissionList.add("6-speed automatic");
		// transmissionList.add("continously variable automatic");
		return transList;
	}

	@ModelAttribute("warrantyList")
	public List<String> populateWarrantyList() {
		List<String> warrantyList = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			warrantyList.add(i + " ");
		}
		return warrantyList;
	}

	@ModelAttribute("horsePowerList")
	public List<String> populatehorsePowerList() {
		List<String> horsePowerList = new ArrayList<String>();
		horsePowerList.add("500bhp @ 6,100RPM");
		horsePowerList.add("600bhp @ 6,300RPM");
		return horsePowerList;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
}
