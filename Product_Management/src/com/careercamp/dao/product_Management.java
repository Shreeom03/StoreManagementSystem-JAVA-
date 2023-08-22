package com.careercamp.dao;

import com.careercamp.dto.Product;

public class product_Management {
	private Product[] DB; //DataBase for our product management system
	int prodCount; //variable helping to keep a count of products and also indexing the product id
	
	public product_Management(){
		DB = new Product[10]; //Initialising the array with 10 indexed space
		prodCount = 0; //Product count will be 0 in the starting as there are no products
	}
	
	public void add(String prodName, int price, int quantity, double discount) {//Method for adding a new product into Database
		if(prodCount==DB.length) {//if out database is going out of bound we will double the available memory
			Doubling(); 
		}
		DB[prodCount] = new Product(prodCount+1,prodName,price,quantity,discount); //using constructor of product class to create the information about the product
		prodCount++; //Incrementing the product count
	}
	private void Doubling() {//Method for doubling out storage of our database
		Product[] OldDB = DB;
		DB = new Product[OldDB.length*2];
		for(int i=0;i<OldDB.length;i++) {
			DB[i] = OldDB[i];
		}
	}
	
	public void Display() {//Method for displaying the available stock irrespective of any order
		if(prodCount==0) {
			System.out.println("Stock is Empty");
			return;
		}
		for(int i=0;i<prodCount;i++) {
			System.out.println("| Product ID : "+DB[i].prodID+" | Product Name : "+DB[i].prodName+
					" | Product Price : "+DB[i].price+" | Product Quantity : "+DB[i].getQuantity()+" | Product Discount : "+DB[i].discount+" |");
		}
	}
	
	public String Remove(int prodId) throws ProductNotFoundException{//Method for removing any product with specific product id and throws a exception if product id doesn't exist
			int i= 0;
			int index = -1;
			String productName="";
			for(i=0;i<prodCount;i++) {//Searching for the index in our database of the specified product id
				if(DB[i].prodID == prodId) {
					index = i;
					productName = DB[i].prodName;
					break;
				}
			}
			if(index == -1)//for throwing exception if product id doesn't exist in database
			throw new ProductNotFoundException("Product Not Found in the Stock");
			
			DB[index] = DB[prodCount-1]; //swaping the data at the index which we want to remove with data available at the last index of database
			DB[index].prodID = index; //giving the index no to product id to the product in database
			prodCount--; //incase we are trying to delete the data at last index this will make sure that pointer never reaches this index
			return productName;
	}
	
	
	public void updateProductName(int prodId, String ProdName) throws ProductNotFoundException{//Method for update product name of a specific product id and throw exception if product is not available in database
		int i= 0;
		int index = -1;
		for(i=0;i<prodCount;i++) {//For searching for the index with the specified product id
			if(DB[i].prodID == prodId) {
				index = i;
				break;
			}
		}
		if(index == -1) //for throwing exception if product id doesn't exist in database
		throw new ProductNotFoundException("Product Not Found in the Stock");
		
		DB[index].prodName = ProdName; //updating the name which is passed into method
		return;
	}
	
	public void updateProductPrice(int prodId, int price) throws ProductNotFoundException{//Method for updating product price for given product id or pass exception if product id is not available in the database
		int i= 0;
		int index = -1;
		for(i=0;i<prodCount;i++) {//Searching for the product id
			if(DB[i].prodID == prodId) {
				index = i;
				break;
			}
		}
		if(index == -1)//For throwing exception if product id doesn't exist in database
		throw new ProductNotFoundException("Product Not Found in the Stock");
		
		DB[index].price = price;//updating price for the product with given product id
		return;
	}
	
	public void updateProductDiscount(int prodId, double discount)throws ProductNotFoundException{//update product discount for the given product id throws exception if product id doesn't exist in database
		int i= 0;
		int index = -1;
		for(i=0;i<prodCount;i++) {//for searching for the product id
			if(DB[i].prodID == prodId) {
				index = i;
				break;
			}
		}
		if(index == -1) //for throwing exception if product id doesn't exist in database
		throw new ProductNotFoundException("Product Not Found in the Stock");
		
		DB[index].discount = discount; //updating the discount for the product with the given product id
		return;
	}
	
	public void updateProductQuantity(int prodId, int quantity) throws ProductNotFoundException{//Method for updating product quantity for the given product id and throws exception if product id doesn't exist in Database 
		int i= 0;
		int index = -1;
		for(i=0;i<prodCount;i++) {//for searching of the given product id
			if(DB[i].prodID == prodId) {
				index = i;
				break;
			}
		}
		if(index == -1) //For throwing exception if the product id doen't exist in the database
		throw new ProductNotFoundException("Product Not Found in the Stock");
		
		DB[index].setQuantity(quantity);//updating the quantity of the product with the given product id
		return;
	}
	
	public void SearchProductByProductId(int id) throws ProductNotFoundException{//Method for displaying the information regarding the provided product id and throws exception if product id doesn't exist in database
		int i= 0;
		int index = -1;
		for(i=0;i<prodCount;i++) {//Searching product id
			if(DB[i].prodID == id) {
				index = i;
				break;
			}
		}
		if(index == -1)//for throwing exception if program doesn't exist in the database
			throw new ProductNotFoundException("Product Not Found in the Stock");
		
		
		System.out.println("Product Info : ");
		System.out.println("| Product ID : "+DB[index].prodID+" | Product Name : "+DB[index].prodName+
				" | Product Price : "+DB[index].price+" | Product Quantity : "+DB[index].getQuantity()+" | Product Discount : "+DB[index].discount+" |");
		return;
		
	}
	
	public void SearchProductByName(String ProdName) throws ProductNotFoundException{//Method for Searching and displaying a product by its name and throws exception it is not available in the data base.
		int i= 0;
		int index = -1;
		for(i=0;i<prodCount;i++) {//Searching for the product with its name
			if(DB[i].prodName.equalsIgnoreCase(ProdName)) {
				index = i;
				break;
			}
		}
		if(index == -1)//for throwing exception if name is not available in the database
			throw new ProductNotFoundException("Product Not Found in the Stock");
		
		System.out.println("Product Info : ");
		System.out.println("| Product ID : "+DB[index].prodID+" | Product Name : "+DB[index].prodName+
				" | Product Price : "+DB[index].price+" | Product Quantity : "+DB[index].getQuantity()+" | Product Discount : "+DB[index].discount+" |");
		return;
	}
	
	public static void mergeSortByPrice(Product arr[], int l, int r){//Sorting according the price with merge sort algorithm
        if(l==r){
            return;
        }

        int mid = l+(r-l)/2;;

        mergeSortByPrice(arr, l, mid);
        mergeSortByPrice(arr, mid+1, r);
        merge(arr,l,mid,r);
    
    }

    public static void merge(Product arr[],int l,int mid, int r){
        int i = l;
        int j = mid+1;
        Product temp[] = new Product[r-l+1];
        int index = 0;
        while(i<=mid && j<=r){
                if(arr[i].price<arr[j].price){
                    temp[index] = arr[i];
                    i++;
                }
                else{
                    temp[index] = arr[j];
                    j++;
                }
                index++;
        }

        while(i<=mid){
             temp[index++] = arr[i];
                    i++;
        }

        while(j<=r){
            temp[index++] = arr[j];
                    j++;
        }

        for(i=l,j=0;j<temp.length;i++,j++){
            arr[i] = temp[j];
        }
    }
	
	 public static void mergeSortByDiscount(Product arr[], int l, int r){//Soritng according to discount with merge sort algorithm
	        if(l==r){
	            return;
	        }

	        int mid = l+(r-l)/2;;

	        mergeSortByDiscount(arr, l, mid);
	        mergeSortByDiscount(arr, mid+1, r);
	        mergeDiscount(arr,l,mid,r);
	    
	    }

	    public static void mergeDiscount(Product arr[],int l,int mid, int r){
	        int i = l;
	        int j = mid+1;
	        Product temp[] = new Product[r-l+1];
	        int index = 0;
	        while(i<=mid && j<=r){
	                if(arr[i].discount<arr[j].discount){
	                    temp[index] = arr[i];
	                    i++;
	                }
	                else{
	                    temp[index] = arr[j];
	                    j++;
	                }
	                index++;
	        }

	        while(i<=mid){
	             temp[index++] = arr[i];
	                    i++;
	        }

	        while(j<=r){
	            temp[index++] = arr[j];
	                    j++;
	        }

	        for(i=l,j=0;j<temp.length;i++,j++){
	            arr[i] = temp[j];
	        }
	    }
	    
	    public void DisplayasAscendingDiscount() {//Method for displaying the information of products according to the ascending order of discount
	    	if(prodCount==0) {//checking if the database if the database is empty
				System.out.println("Stock is Empty");
				return;
			}
	    	mergeSortByDiscount(DB,0,prodCount-1); //sorting it according to discount values of the product in database.
			for(int i=0;i<prodCount;i++) { //for printing the information of the products
				System.out.println("| Product ID : "+DB[i].prodID+" | Product Name : "+DB[i].prodName+
						" | Product Price : "+DB[i].price+" | Product Quantity : "+DB[i].getQuantity()+" | Product Discount : "+DB[i].discount+" |");
			}
	    }
	    
	    public void DisplayasDescendingDiscount() {//Method for displaying the information of products according to the descending order of discount
	    	if(prodCount==0) {//checking if the database is empty
				System.out.println("Stock is Empty");
				return;
			}
	    	mergeSortByDiscount(DB,0,prodCount-1);//sorting it according to discount values of the product in database.
			for(int i=prodCount-1;i>=0;i--) {//Just the printing it in reverse to print it in descending order
				System.out.println("| Product ID : "+DB[i].prodID+" | Product Name : "+DB[i].prodName+
						" | Product Price : "+DB[i].price+" | Product Quantity : "+DB[i].getQuantity()+" | Product Discount : "+DB[i].discount+" |");
			}
	    }
	    
	    public void DisplayasDescendingPrice() {//Method for displaying the information of products according to the descending order of the price
	    	if(prodCount==0) {//checking if the database is empty
				System.out.println("Stock is Empty");
				return;
			}
	    	mergeSortByPrice(DB,0,prodCount-1);//Sorting the database according to price 
			for(int i=prodCount-1;i>=0;i--) { // printing the information in the reverse fashion to display it in descending order
				System.out.println("| Product ID : "+DB[i].prodID+" | Product Name : "+DB[i].prodName+
						" | Product Price : "+DB[i].price+" | Product Quantity : "+DB[i].getQuantity()+" | Product Discount : "+DB[i].discount+" |");
			}
	    }
	    
	    public void DisplayasAscendingPrice() {//Method for displaying the information of products according to the ascending order of the price
	    	if(prodCount==0) {//checking if database is empty
				System.out.println("Stock is Empty");
				return;
			}
	    	mergeSortByPrice(DB,0,prodCount-1);//Sorting the database according to price
			for(int i=0;i<prodCount;i++) {//displaying the information of the products according to ascending order of price
				System.out.println("| Product ID : "+DB[i].prodID+" | Product Name : "+DB[i].prodName+
						" | Product Price : "+DB[i].price+" | Product Quantity : "+DB[i].getQuantity()+" | Product Discount : "+DB[i].discount+" |");
			}
	    }
}
