package com.sist.commons;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonsRestControllerException {
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex) {
		System.out.println("====== Controller 에서 Exception 발생 ======");
		ex.printStackTrace();
	}
	
	@ExceptionHandler(Throwable.class)
	public void throwable(Throwable ex) {
		System.out.println("====== Controller 에서 Throwable 발생 ======");
		ex.printStackTrace();
	}
}
