package com.car.soap.webservice;

public class Welcome {
	private String messageCode;
	private String message;

	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Welcome [messageCode=" + messageCode + ", message=" + message
				+ "]";
	}

}
