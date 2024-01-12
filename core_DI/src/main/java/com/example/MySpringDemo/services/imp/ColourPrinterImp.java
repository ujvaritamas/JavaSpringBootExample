package com.example.MySpringDemo.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.MySpringDemo.services.ColourPrinter;
import com.example.MySpringDemo.services.RedPrinter;

@Component
public class ColourPrinterImp implements ColourPrinter {
	
	private RedPrinter redPrinter;
	
	
	@Autowired
	public ColourPrinterImp(RedPrinter redPrinter) {
		super();
		this.redPrinter = redPrinter;
	}



	@Override
	public String print() {
		// TODO Auto-generated method stub
		return this.toString() + " Hello ColourPrinterImp " + redPrinter.print();
	}

}
