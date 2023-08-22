package com.careercamp.service;

import java.util.Random;
import java.util.Scanner;

import com.careercamp.dao.ProductNotFoundException;
import com.careercamp.dao.product_Management;

public class Main_Util {
	public static void main(String aegs[]){
		product_Management StoreIncharge = new product_Management();//Creating a StoreIncharge object of product management class
		Random random = new Random(); //Object of random class used to give random inputs or taking random values
		Scanner sc = new Scanner(System.in); //Object of scanner class used to take inputs from the user
		
        String characterPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";//Character pool to take input from random class with help of this String.
        
        System.out.println("***Welcome to Store Management System***");
        int choice = 0;
        
        while(choice != -1) { 
        	//Menu
        	System.out.println("Please Enter '1' to add product into stock");
        	System.out.println("Please Enter '2' to remove product into stock");
        	System.out.println("Please Enter '3' to Display all products");
        	System.out.println("Please Enter '4' to update product name");
        	System.out.println("Please Enter '5' to update product price");
        	System.out.println("Please Enter '6' to update product quantity");
        	System.out.println("Please Enter '7' to update product discount");
        	System.out.println("Please Enter '8' to search product");
        	System.out.println("Please Enter '9' to display product according to price");
        	System.out.println("Please Enter '10' to display product according to discount");
        	System.out.println("Please Enter '-1' to EXIT");
        	
        	choice = sc.nextInt();
        	
        	if(choice == -1) {//For exiting
        		break;
        	}
        	
        	else if(choice == 1) {
        		StringBuilder randomString = new StringBuilder();  //For storing random string for inserting in product name
        		
        		for (int j = 0; j < 10; j++) { //For creating Random Product name of length = 10
                    int randomIndex = random.nextInt(characterPool.length()); //Taking a random index from String character pool which we declared above.
                    char randomChar = characterPool.charAt(randomIndex); //Picking up the character from the index recieved from above from characterPool String.
                    randomString.append(randomChar); //Appending the received character into string builder
                }
        		String str = randomString.toString(); // Converting to String to directly be inserted to Add function
        		
        		StoreIncharge.add(str,random.nextInt(20,500),random.nextInt(10,50),random.nextDouble(25.0,50.0)); //Inserting the values into Database taken from random class
        		randomString = new StringBuilder(); //Referencing the StringBuilder towards new address  to flush old value stored
        	}
        	
        	else if(choice == 2) {
        		System.out.println("Please Enter the product id for removing the product from stock.");
        		int id = sc.nextInt(); //taking input for the product with product id to be removed
        		try {
        		System.out.println("Product id "+id+" "+StoreIncharge.Remove(id)+" removed from the stock");
        		}
        		catch(ProductNotFoundException e){ //Error handling incase product id doesn't exist
        			System.out.println("OOPS, Product not found");
        		}
        	}
        	
        	else if(choice == 3) {
        		System.out.println("---Available Stock---");
        		StoreIncharge.Display();
        	}
        	
        	else if(choice == 4) {
        		System.out.println("Please Enter the product id to be updated, followed by the name for the product");
        		int id = sc.nextInt(); //taking input for product id whose name has to be updated
        		String GarbageCollector = sc.nextLine();
        		String name = sc.nextLine(); //taking input for new name for the product
        		try {
        		StoreIncharge.updateProductName(id, name);
        		}
        		catch(ProductNotFoundException e) { //Error handling incase product not found
        			System.out.println("OOPS, Product not found");
        		}
        	}
        	
        	else if(choice == 5) {
        		System.out.println("Please enter the product id to be updated, followed by the new price");
        		int id = sc.nextInt(); //taking input for product id whose price has to be updated
        		int price = sc.nextInt(); //taking input for new price for the product
        		try {
        			StoreIncharge.updateProductPrice(id, price);
        		}
        		catch(ProductNotFoundException e){ //Error handling incase product id doesn't exist
        			System.out.println("OOPS, Product not found"); 
        		}
        	}
        	
        	else if(choice == 6) {
        		System.out.println("Please enter the product id to be updated, followed by the new quantity of the product");
        		int id = sc.nextInt(); //taking input for product id whose price has to be updated
        		int quantity = sc.nextInt(); //taking input for new quantity of the product
        		try {
        			StoreIncharge.updateProductQuantity(id, quantity);
        		}
        		catch(ProductNotFoundException e){ //Error handling incase product id doesn't exist
        			System.out.println("OOPS, Product not found");
        		}
        	}
        	
        	else if(choice == 7) {
        		System.out.println("Please enter the product id to be updated, followed by the new discount for the product");
        		int id = sc.nextInt(); //taking input for product id whose price has to be updated
        		double discount = sc.nextDouble(); //taking input for new discount on the product
        		try {
        			StoreIncharge.updateProductDiscount(id, discount);
        		}
        		catch(ProductNotFoundException e){ //Error handling incase product id doesn't exist
        			System.out.println("OOPS, Product not found");
        		}
        	}
        	
        	else if(choice == 8) {
        		System.out.println("Please enter '1' to search with product id or enter '2' to search with product name");
        		int option = sc.nextInt(); //Taking input for the choice either product to be searched with product id or name.
        		if(option == 1) { // while searching product with product id
        			int id = sc.nextInt(); //Taking input of the id to be searched
        			try {
        				StoreIncharge.SearchProductByProductId(id);
        			}
        			catch(ProductNotFoundException e) {
        				System.out.println("OOPS, Product not found");
        			}
        		}
        		
        		else if(option == 2) { // while searching product with name
        			System.out.println("Please enter the product name to be searched");
        			String GarbageCollector = sc.nextLine();
        			String name = sc.nextLine(); //taking input for the product name to be searched
        			try {
        				StoreIncharge.SearchProductByName(name);
        			}
        			catch(ProductNotFoundException e){
        				System.out.println("OOPS, Product not found");
        			}
        		}
        		
        		else {
        			System.out.println("Invalid input!");
        		}
        	}
        	
        	else if(choice == 9) {
        		System.out.println("Please enter '1' to display products in ascending order of price or enter '2' to display products in descending order of price");
        		int option = sc.nextInt();//Taking input for displaying according to price in ascending or descending order
        		if(option == 1) {//Displaying according to price ascending order
        			StoreIncharge.DisplayasAscendingPrice();
        		}
        		else if(option == 2) { //Displaying according to price descending order
        			StoreIncharge.DisplayasDescendingPrice();
        		}
        		else {
        			System.out.println("Invalid Input!");
        		}
        	}
        	
        	else if(choice == 10) {
        		System.out.println("Please enter '1' to display products in ascending order of discount or enter '2' to display products in descending orderof discount");
        		int option = sc.nextInt();//Taking input either to be displayed in the ascending or descending order of discount
        		if(option ==1) {//Displaying in the ascending order of discount
        			StoreIncharge.DisplayasAscendingDiscount();
        		}
        		else if(option ==2){//Displaying in the descending order of discount
        			StoreIncharge.DisplayasDescendingDiscount();
        		}
        		else {
        			System.out.println("Invalid Input");
        		}
        	}
        }
        System.out.println("Thank You!");
        sc.close();
	}
}
