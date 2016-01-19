package com.car.rest.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GCarAppExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CarAppException.class)
	@ResponseBody
	CarAppErrorMessage handleLanguageException(HttpServletRequest req,
			Exception ex) {

		CarAppErrorMessage carAppErrorMessage = new CarAppErrorMessage("3000",
				ex.getMessage(), ex.getClass().toString(), req.getRequestURL()
						.toString());

		return carAppErrorMessage;

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	CarAppErrorMessage applicationErrorMessage(HttpServletRequest request,
			Exception exc) {

		CarAppErrorMessage otherErrorMessage = new CarAppErrorMessage("9000",
				exc.getMessage(), exc.getClass().toString(), request
						.getRequestURL().toString());
		return otherErrorMessage;
	}
}
