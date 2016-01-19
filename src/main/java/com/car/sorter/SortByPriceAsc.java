package com.car.sorter;

import java.util.Comparator;

import com.car.controller.model.CarDetailsForm;

public class SortByPriceAsc implements Comparator<CarDetailsForm> {

	@Override
	public int compare(CarDetailsForm c1, CarDetailsForm c2) {

		int p = c1.getPrice()-c2.getPrice();

		if(p == 0){
			p = c1.getWarranty()-c2.getWarranty();
		}
		return p;
	}

}
