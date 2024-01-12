package com.example.MySpringDemo.services.imp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.MySpringDemo.services.RedPrinter;

@Component
@Primary
public class RedPrinter2  implements RedPrinter{
	
	private String name;
	
	
	public RedPrinter2(@Value("${MyRedPrinter2.property}") String name) {
		this.name = name;
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return "red2 " +this.name;
	}

}
