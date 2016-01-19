package com.car.dao.entity;

public class CarEntity {

	private int sno;
	private String name;
	private int price;
	private String transmission;
    private int warranty;
	private byte[] image;
	private String horsePower;

	public CarEntity(){

	}

	public CarEntity(String name, int price, String transmission,
			String horsePower) {
		this.name = name;
		this.price = price;
		this.transmission = transmission;
		this.horsePower = horsePower;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(String horsePower) {
		this.horsePower = horsePower;
	}

	@Override
	public String toString() {
		return "CarDetailsForm [name=" + name + ", price=" + price
				+ ", transmission=" + transmission + ", horsePower="
				+ horsePower + "]";
	}
}
