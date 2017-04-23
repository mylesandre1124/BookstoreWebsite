package Exceptions;

/**
 * Created by Myles on 4/18/17.
 */
public class BookNotFoundException extends Exception {

    public BookNotFoundException(Long isbn) {
        super("Book with ISBN#: " + isbn + " was not found in the system");
        System.out.println(getMessage());
    }

    public BookNotFoundException(Integer crn) {
        super("Book with CRN#: " + crn + " was not found in the system");
        System.out.println(getMessage());
    }

    public BookNotFoundException(String message) {
        super("Book with Keyword: " + message + " was not found in the system");
        System.out.println(getMessage());
    }
}
