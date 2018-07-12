package project4;
/**
 * Lyda Taing
 * CSC 142
 * June 6, 2018
 * 
 * BookListNode class creates a linked list of book class.
 */
import java.io.*;

public class BookListNode implements Serializable{
	public Book book;
	public BookListNode next;
	
	public BookListNode() {
		this(null, null); 	
	}
	
	public BookListNode(Book book) {
		this(book, null);		
	}
	
	public BookListNode(Book book, BookListNode next) {
		this.book = book;
		this.next= next;	
	}
	

}
