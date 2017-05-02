package Objects;

import Exceptions.StockException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Myles on 4/28/17.
 */
public class OrderConfirmation {

    private ShoppingCart cart;
    private OrderInfoHandler orderInfo;
    private Student student;

    public OrderConfirmation(ShoppingCart cart, OrderInfoHandler orderInfo, Student student) {
        this.cart = cart;
        this.orderInfo = orderInfo;
        this.student = student;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public OrderInfoHandler getOrderInfo() {
        return orderInfo;
    }

    public Student getStudent() {
        return student;
    }

    public Double finalStudentCheckout() throws FileNotFoundException {
        Double totalPrice = Double.parseDouble(cart.getTotalPrice());
        student.changeFinancialAid(totalPrice);
        StudentsDatabase studentsDatabase = new StudentsDatabase();
        studentsDatabase.updateStudent(student);
        return totalPrice;
    }

    public void changeBooks() throws StockException {
        BooksDatabase books = new BooksDatabase(new File("books.bks"));
        for (int i = 0; i < cart.size(); i++) {
            Book book = cart.getBook(i);
            int quantity = cart.getBookQuantity(i);
            int type = cart.getBookType(i);
            try {
            switch (type) {
                case 1:
                    book.changeNewQuantity(quantity);
                    System.out.println("New");
                    break;
                case 2:
                    book.changeUsedQuantity(quantity);
                    System.out.println("Used");
                    break;
                case 3:
                    book.changeRentalQuantity(quantity);
                    System.out.println("Rental");
                    break;
            }
            }
            catch (StockException ex)
            {
                System.out.println(ex.getMessage());
            }
            books.updateBook(book);
        }
    }

    public void studentCheckout() throws FileNotFoundException {
        try {
            finalStudentCheckout();
            changeBooks();
            ReceiptHandler receipt = new ReceiptHandler();
            String msg = receipt.htmlMessage(this);
            receipt.toEmail(msg);
        }
        catch (StockException ex)
        {
            ex.printStackTrace();
        }
    }

    public void checkout() throws FileNotFoundException {
        try {
            changeBooks();
            ReceiptHandler receipt = new ReceiptHandler();
            String msg = receipt.htmlMessage(this);
            receipt.toEmail(msg);
        }
        catch (StockException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws StockException {
        Search search = new Search();
        Book book = search.searchByISBN(9780123850638L);
        System.out.println(book.getNewQuantity());
        book.changeNewQuantity(4);
        System.out.println(book.getNewQuantity());
        new BooksDatabase(new File("books.bks")).updateBook(book);
        Book book1 = search.searchByISBN(9780123850638L);
        System.out.println("New Final: " + book1.getNewQuantity());

    }
}
