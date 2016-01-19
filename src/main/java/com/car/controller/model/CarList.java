package com.car.controller.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarList {
	private List<CarDetailsForm> carForm;

	public List<CarDetailsForm> getCarForm() {
		return carForm;
	}

	public void setCarForm(List<CarDetailsForm> carForm) {
		this.carForm = carForm;
	}

	@Override
	public String toString() {
		return "CarList [carForm=" + carForm + "]";
	}
}
