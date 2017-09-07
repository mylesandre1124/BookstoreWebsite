package Objects;

import Exceptions.StockException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeMap;

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
        ObjectIO objectIO = new ObjectIO(new File("students.sts"));
        TreeMap<String, Student> student = (TreeMap<String, Student>) objectIO.readObject();
        Student student1 = student.get("twrea24");
        System.out.println(student1.getAidAmount());
    }
}
