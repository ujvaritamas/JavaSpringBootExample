package com.example.MySpringDemo.services.imp;

import org.springframework.stereotype.Component;

import com.example.MySpringDemo.services.RedPrinter;

@Component
public class RedPrinterImp implements RedPrinter{

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return "red";
	}

}
