package project4;
/**
 * Lyda Taing
 * CSC 142
 * June 6, 2018
 * 
 * BookCatalog class builds list of books within input informations
 * it has method to find, remove and add the book.
 * 
 */
import java.io.Serializable;

public class BookCatalog implements Serializable {
	private int size;
	private BookListNode front;
	
	public BookCatalog() {
		front = null;
		size =0;
	}
	
	// for printing out the information of the book inside libary
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		String result = "";
		BookListNode current = front;

		while (current != null) {
			result += current.book + "\n";
			current = current.next;
		}
		return result;
	}
	
	public int size() {
		return size;
	}
	
	// check if empty.
	public boolean isEmpty() {
		return (size == 0);
	}
	
	// add the book and increase size by 1
	public void add(Book book) {
		if (this.isEmpty()) {
			front = new BookListNode(book);
			size = 1;
		} else {
			BookListNode current = front;
			BookListNode temp = new BookListNode(book);
			while (current.next != null) {
				current = current.next;
			}
			current.next = temp;
			size++;
		}
	}
	
	// Find the book by ISBN or book title
	// return true and book information if book is in the list, either way return false. 
	public boolean findBook(String input) {
		BookListNode current = front;
		while (current!=null) {
			if (current.book.getISBN().equals(input)) {
				System.out.println(current.book.toString());
				return true;
			}
			if (current.book.getTittle().equals(input)) {
				System.out.println(current.book.toString());
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	// Remove the book by ISBN or book title.
	public void remove (String input) {
		BookListNode current = front;
		if (front.book.getISBN().equals(input)) {
			front = front.next;
		}
		else {
			while(current.next != null) {
				if (current.next.book.getISBN().equals(input)) {
					current.next= current.next.next;
				}
				current = current.next;
			}
		}
	}
}
