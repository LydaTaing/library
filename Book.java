package project4;
/**
 * Lyda Taing
 * CSC 142
 * June 6, 2018
 * 
 * Book class build a book within input informations
 * it require ISBN, first name, last name, tittle, year and price
 * it also check each information.
 * 
 */
import java.io.*;
import java.util.*;

public class Book implements Serializable{
	private String ISBN;
	private String FirstName;
	private String LastName;
	private String tittle;
	private int year;
	private double price;

	public Book(String ISBN, String FirstName, String LastName, String tittle, int year, double price) {
		
		this.ISBN = ISBN;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.tittle = tittle;
		this.year = year;
		this.price = price;
		
		// check if the information is valid.
		if (!isValidISBN() && !checkyear() && !checkPrice()) {
			throw new IllegalArgumentException("Data is invalid");
		}
		
		
	}

	// for print the inforamtion of the book.
	public String toString() {
		String print = "ISBN= " + ISBN + ", FirstName= " + FirstName + ", LastName= " + LastName + ", tittle= " + tittle
				+ ", year= " + year + ", price= $" + price;

		return print;
	}

	// get first name of the author
	public String getFirstName() {
		return FirstName;
	}

	// set first name of the author.
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	//get last name of the author
	public String getLastName() {
		return LastName;
	}

	// set last name of the author.
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	// get the title of the book
	public String getTittle() {
		return tittle;
	}

	// set title of the book
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	// get publication year
	public int getYear() {
		return year;
	}

	// set publication year
	public void setYear(int year) {
		this.year = year;
	}

	// check the year
	private boolean checkyear() {
		if (year <= 2018) {
			return true;
		}
		return false;
	}

	// get the price of book
	public double getPrice() {
		return price;
	}

	// set price of the book
	public void setPrice(double price) {
		this.price = price;
	}

	// check price of the book. 
	// price >=0
	private boolean checkPrice() {
		if (price < 0) {
			return false;
		}
		return true;
	}

	// ISBN section.
	public String getISBN() {
		return ISBN;
	}

	public boolean isValidISBN(){
		if (!checkdash() && !num()) {
			return false;
		}
		
       return true;
    }
	private boolean checkdash(){ // check dash condition. 
		int count = 0;	// count number of dash
		if (ISBN.charAt(0) == '-') {	// check if begin with dash
			System.out.println("Beginning dash.");
			return false;	// don't need to go further
		}
		if (ISBN.charAt(ISBN.length()-1) == '-') {	// check if end with dash
			System.out.println("Ending dash.");
			return false;
		}
		for (int i = 1 ; i <= ISBN.length() - 1 ; i++){
			if (ISBN.charAt(i) == '-')
				count++;
			if (ISBN.charAt(i) == '-' && ISBN.charAt(i-1) == '-')	{	// check if sequential dash
				System.out.println("Sequential dash at position " + (i - 1) + " and " + i);
				return false;
			}
		}
		if (count == 3 || count == 0)	// having 0 or 3 dashes meets the ISBN dash condition 
			return true;
		if (count > 3) {
			System.out.println("Too many dashes. Your code has: " + count);
			return false;
		}
		if (count < 3) {
			System.out.println("Not enough dashes. Your code has: " + count);
			return false;
		}
		return false;
	}
	
	private boolean num(){		// check the digit 
		int sum = 0 , store = 0 , count = 0;
		for (int i = 0 ; i <= ISBN.length() - 2 ; i++){		// check and find sum
			if (ISBN.charAt(i) != '-')
				if ((int) ISBN.charAt(i) >= 48 && (int) ISBN.charAt(i) <= 57){	
					count++;	// count number in the given ISBN
					store = (int) ISBN.charAt(i) - 48;
					sum +=  store * count;	// sum of the ISBN
				} else {	// if the character is not dash or number than return - no need to go further
					System.out.println("Bad digit: " + ISBN.charAt(i) + " at position " + i );
					return false;
				}
		}
		if (count < 9) {
			System.out.println("Too few digits. Your code has: " + count);
			return false;
		}
		if (count > 9) {
			System.out.println("Too many digits. Your code has: " + count);
			return false;
		}
		if ((int) ISBN.charAt(ISBN.length() - 1) >= 48 && (int) ISBN.charAt(ISBN.length() - 1) <= 57)	// identify and store checksum
			store = (int) ISBN.charAt(ISBN.length() - 1) - 48;
		if (ISBN.charAt(ISBN.length() - 1) == 'X')	// store checksum = 10 if last digit is X
			store = 10;
		sum %= 11;
		if (sum != store) { // check checksum 
			System.out.println("Wrong check sum. The checksum should be: " + sum);
			return false;
		}
		return true;
	}
}
