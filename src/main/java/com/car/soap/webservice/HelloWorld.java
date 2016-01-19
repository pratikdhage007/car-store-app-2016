package com.car.soap.webservice;

import javax.jws.WebService;


@WebService
//@SOAPBinding(style = Style.RPC)
public class HelloWorld {

	public Welcome poke(String message){
		Welcome welcome =new Welcome();
		welcome.setMessageCode("99999");
		welcome.setMessage("Soap based Web Services"+"/tInside poke() of Helloworld");
		return welcome;
	}
}
