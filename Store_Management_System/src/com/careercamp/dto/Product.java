package com.careercamp.dto;

public class Product{
	public int prodID; //product id of the product
	public String prodName; // NAme of the product
	public int price;//Price of the product
	private int quantity; //quantity for the product
	public double discount; //Discount on the product
	
	public Product(int prodID, String prodName, int price, int quantity, double discount ){//Constructor for creating an object of product class
		this.prodID = prodID;
		this.prodName = prodName;
		this.price = price;
		this.quantity = quantity;
		this.discount = discount;
	}
	
	public int getQuantity() {//getter function for quantity as its access modifier is specified as private
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {//setter function for quantity as its access modifier is specified as private
		this.quantity = quantity;
	}
	
}
