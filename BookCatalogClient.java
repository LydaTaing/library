package project4;
import java.io.*;
import java.util.*;

/**
 * Lyda Taing
 * CSC 142
 * June 6, 2018
 * 
 * BookCatalogClient reads the text file and add list of book to library
 * Allow user to input file, remove, find and quit base on the selection 
 * If the user wants to save, I save it to file name “tempory.txt” because my save function doesn’t run well. 
 */

public class BookCatalogClient {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		BookCatalog libary = getLibary();
		System.out.println(libary);
		Scanner console = new Scanner(System.in);
		Scanner inFind = new Scanner(System.in);
		Scanner inRemove = new Scanner(System.in);
		Scanner inAdd = new Scanner(System.in);
		menu();
		selection(console, inFind, inRemove, inAdd, libary);	
	}

	// selection of the main menu
	// q for quit and get out of the while loop.
	private static void selection(Scanner console, Scanner inFind, Scanner inRemove, Scanner inAdd, BookCatalog libary) throws FileNotFoundException, IOException {
		while (console.hasNext()) {
			String choice = console.next();
			if (!choice.equals("q")) {
				if (choice.equalsIgnoreCase("f")) {
					Find(inFind, libary);
				} else if (choice.equalsIgnoreCase("r")) {
					remove(inRemove, libary);
				} else if (choice.equalsIgnoreCase("a")) {
					Add(inAdd, libary);
				}
			} else {
				System.out.println("Save? yes or no?");
				System.out.print("---> ");
				String c = console.next();
				if (c.equalsIgnoreCase("yes")) {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tempory.txt"));
					out.writeObject(libary);
				}

				break;
			}
		}
		console.close();
		
	}

	// add the book to the library by input information
	// each token separated by tab.
	private static void Add(Scanner in, BookCatalog libary) {
		Amenu();
		String[] word = null;
		String input = in.nextLine();
		word = input.split("\t");
		String ISBN = word[0];
		String first = word[1];
		String last = word[2];
		String tittle = word[3];
		int year = Integer.parseInt(word[4]);
		double price = Double.parseDouble(word[5]);
		Book book = new Book(ISBN, first, last, tittle, year, price);
		libary.add(book);
		System.out.println("\nAdded book: " + book);
		in.close();
		menu();
	}

	// remove the book by ISBN or book title
	// print the info of book when removing
	// if book is not in a system, print the message out.
	private static void remove(Scanner inRemove, BookCatalog libary) {
		Rmenu();
		while (inRemove.hasNext()) {
			String choice = inRemove.nextLine();
			if (!choice.equalsIgnoreCase("x")) {
				if (libary.findBook(choice)) {
					libary.remove(choice);
				} else {
					System.out.println("The book is not in a system.");
				}
				System.out.println();
				Rmenu();

			} else {
				System.out.println("\nBack to main menu.");
				menu();
				break;
			}
		}

	}

	// find the book by ISBN or Title
	// print out the information of the book or message if book is not in a system.
	private static void Find(Scanner inFind, BookCatalog libary) {
		Fmenu();
		while (inFind.hasNext()) {
			String choice = inFind.nextLine();
			if (!choice.equalsIgnoreCase("x")) {
				if (libary.findBook(choice)) {
					System.out.println();
				} else {
					System.out.println("No infomation of this book.\n");
				}
				Fmenu();

			} else {
				System.out.println("\nBack to main menu.");
				menu();
				break;
			}
		}

	}

	// add menu
	private static void Amenu() {
		System.out.println("Fill the information of the Book in order by using tab to saperate.");
		System.out.print("ISBN, first name, last name, Tittle, year, price. ");
		System.out.print("---> ");

	}

	// remove menu
	private static void Rmenu() {
		System.out.println("x for back to main menu.");
		System.out.print("Remove by Tittle or ISBN: ");

	}

	// find menu
	private static void Fmenu() {
		System.out.println("x for back to main menu.");
		System.out.print("Find by Tittle or ISBN : ");

	}

	// main menu
	private static void menu() {
		System.out.println("Select one the following:");
		System.out.println("    F for finding the book.");
		System.out.println("    R for removing the book.");
		System.out.println("    A for adding the book.");
		System.out.println("    Q for quit");
		System.out.print("--->: ");
	}

	// put text of information of books in a list 
	private static BookCatalog getLibary() throws FileNotFoundException {
		File infile = new File("booklist.txt");
		Scanner console = new Scanner(infile);
		String[] list = null;
		BookCatalog libary = new BookCatalog();

		while (console.hasNext()) {
			String line = console.nextLine();
			list = line.split("\t");
			String ISBN = list[0];
			String first = list[1];
			String last = list[2];
			String tittle = list[3];
			int year = Integer.parseInt(list[4]);
			double price = Double.parseDouble(list[5]);

			Book book = new Book(ISBN, first, last, tittle, year, price);
			libary.add(book);
		}
		console.close();
		return libary;
	}

}
