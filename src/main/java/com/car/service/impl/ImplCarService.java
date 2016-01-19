package com.car.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.car.controller.model.CarDetailsForm;
import com.car.controller.model.CarPaginationForm;
import com.car.dao.CarDao;
import com.car.dao.entity.CarEntity;
import com.car.dao.entity.CarPaginationEntity;
import com.car.service.CarService;

@Service("ImplCarService")
public class ImplCarService implements CarService {

	@Autowired
	@Qualifier("ImplCarDao")
	private CarDao carDao;

	@Override
	public String addUpdateCarInfo(CarDetailsForm carDetailsForm) {
		CarEntity carEntity = new CarEntity();
		BeanUtils.copyProperties(carDetailsForm, carEntity);
		String result = carDao.addUpdateCarInfo(carEntity);
		return result;
	}

	@Override
	public List<CarDetailsForm> findCars() {
		List<CarDetailsForm> carDetailsList = new ArrayList<CarDetailsForm>();
		List<CarEntity> carEntities = carDao.findCars();
		for(CarEntity cEntity : carEntities){
			CarDetailsForm cdForm = new CarDetailsForm();
			BeanUtils.copyProperties(cEntity, cdForm);
			carDetailsList.add(cdForm);
		}
		return carDetailsList;
	}

	@Override
	public byte[] findImageByCid(String cid) {
		return carDao.findImageByCid(cid);
	}

	@Override
	public CarDetailsForm findCarByCid(String cid){
		CarDetailsForm carDetailsForm= new CarDetailsForm();
		CarEntity carEntity= carDao.findCarByCid(cid);
		BeanUtils.copyProperties(carEntity, carDetailsForm);
		return carDetailsForm;
	}

	@Override
	public String deleteCarByCid(String cid){
		return carDao.deleteCarByCid(cid);

	}

	@Override
	public List<String> findAllTransmission() {
		return carDao.findAllTransmission();
	}

	@Override
	public List<CarDetailsForm> findAllCarsByTransmission(String transmissionType) {
		List<CarDetailsForm> carsByTransmissionFormList = new ArrayList<CarDetailsForm>();
		List<CarEntity> carEntityTransmission = carDao.findAllCarsByTransmission(transmissionType);
		for(CarEntity cEntity: carEntityTransmission){
			CarDetailsForm cdForm = new CarDetailsForm();
			BeanUtils.copyProperties(cEntity, cdForm);
			carsByTransmissionFormList.add(cdForm);
		}
		return carsByTransmissionFormList;
	}

	@Override
	public CarPaginationForm findCarsWithPagination(int startIndex,
			int noOfRecordsPerPage) {

		CarPaginationEntity carPaginationEntity = carDao.findCarsWithPagination(startIndex, noOfRecordsPerPage);
		CarPaginationForm carPaginationForm = new CarPaginationForm();
		BeanUtils.copyProperties(carPaginationEntity, carPaginationForm);

		List<CarDetailsForm> carFormList = new ArrayList<CarDetailsForm>();
		List<CarEntity> carEntityList = carPaginationEntity.getCarEntityList();

		for(CarEntity ce : carEntityList){
			CarDetailsForm form = new CarDetailsForm();
			BeanUtils.copyProperties(ce, form);
			carFormList.add(form);
		}

		carPaginationForm.setCarFormList(carFormList);
		return carPaginationForm;
	}
}
