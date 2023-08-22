package com.careercamp.dao;

public class ProductNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ProductNotFoundException(String str){//For encountering the exception if product doesn't exist in database
		super(str);
	}
}
