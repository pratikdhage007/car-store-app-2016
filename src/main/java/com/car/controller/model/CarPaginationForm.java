package com.car.controller.model;

import java.util.List;

public class CarPaginationForm {

	private int noOfRecords;
	private List<CarDetailsForm> carFormList;

	private final int recordsPerPage = 3;
	private int noOfPages;
	private int currentPage;

	public void initPagination(){
		noOfPages = (int) Math.ceil(noOfRecords* 1.0 / recordsPerPage);
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public List<CarDetailsForm> getCarFormList() {
		return carFormList;
	}

	public void setCarFormList(List<CarDetailsForm> carFormList) {
		this.carFormList = carFormList;
	}

	public int getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	@Override
	public String toString() {
		return "CarPaginationForm [noOfRecords=" + noOfRecords
				+ ", carFormList=" + carFormList + "]";
	}

}
