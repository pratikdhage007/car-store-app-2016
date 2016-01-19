package com.car.dao.entity;

import java.util.List;

public class CarPaginationEntity {

	private int noOfRecords;
	private List<CarEntity> carEntityList;

	public int getNoOfRecords() {
		return noOfRecords;
	}

	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public List<CarEntity> getCarEntityList() {
		return carEntityList;
	}

	public void setCarEntityList(List<CarEntity> carEntityList) {
		this.carEntityList = carEntityList;
	}

	@Override
	public String toString() {
		return "CarPaginationEntity [noOfRecords=" + noOfRecords
				+ ", carEntityList=" + carEntityList + "]";
	}

}
