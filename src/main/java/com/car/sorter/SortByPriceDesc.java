package com.car.sorter;

import java.util.Comparator;

import com.car.controller.model.CarDetailsForm;

public class SortByPriceDesc implements Comparator<CarDetailsForm>{

	@Override
	public int compare(CarDetailsForm c1, CarDetailsForm c2) {


		int p = c2.getPrice()-c1.getPrice();

		if(p == 0){
			p = c2.getWarranty()-c1.getWarranty();
		}
		return p;
	}

}
