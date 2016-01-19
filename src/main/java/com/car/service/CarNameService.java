package com.car.service;

import org.springframework.stereotype.Service;

@Service("CarNameService")
public class CarNameService {
	String info;
	public String carInfo(int num){

		if(num==1){
			info = "Concept";
		}else if(num == 2){
            info = "Bentley Continental";
		}else if(num == 3){
            info = "Jaguar XJ";
		}else if(num == 4){
            info = "Maserati";
		}else if(num == 5 ){
            info = "Rolls Royce Ghost";
		}else{
            info = "SORRY CAR NOT AVAILABLE !!!! ";
            System.out.println("SORRY CAR NOT AVAILABLE !!!! ");
		}
		return info;

	}
}
