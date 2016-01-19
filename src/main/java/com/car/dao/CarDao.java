package com.car.dao;

import java.util.List;

import com.car.dao.entity.CarEntity;
import com.car.dao.entity.CarPaginationEntity;

public interface CarDao {
	public String addUpdateCarInfo(CarEntity carEntity);
	public List<CarEntity> findCars();
	public byte[] findImageByCid(String cid);
	public String deleteCarByCid(String cid);
	public CarEntity findCarByCid(String cid);
	public List<String> findAllTransmission();
	public List<CarEntity> findAllCarsByTransmission(String transmissionType);
	public CarPaginationEntity findCarsWithPagination(int startIndex,
			int noOfRecordsPerPage);
}
