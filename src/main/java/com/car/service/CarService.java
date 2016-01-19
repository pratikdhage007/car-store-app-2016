package com.car.service;

import java.util.List;

import com.car.controller.model.CarDetailsForm;
import com.car.controller.model.CarPaginationForm;

public interface CarService {

	public String addUpdateCarInfo(CarDetailsForm carDetailsForm);
	public List<CarDetailsForm> findCars();
	public byte[] findImageByCid(String cid);
	public String deleteCarByCid(String cid);
	public CarDetailsForm findCarByCid(String cid);
	public List<String> findAllTransmission();
	public List<CarDetailsForm> findAllCarsByTransmission(String transmissionType);
	public CarPaginationForm findCarsWithPagination(int startIndex,
			int noOfRecordsPerPage);
}
