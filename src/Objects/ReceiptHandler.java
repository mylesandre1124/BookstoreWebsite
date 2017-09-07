package Objects;

import com.sun.org.apache.xpath.internal.operations.Mult;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Myles on 4/27/17.
 */
public class ReceiptHandler {

    private String emailAddress;
    private String emailPassword;
    private String outputEmail;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getOutputEmail() {
        return outputEmail;
    }

    public void setOutputEmail(String outputEmail) {
        this.outputEmail = outputEmail;
    }

    public String htmlMessage(OrderConfirmation orderConfirm)
    {
        String message = "<h1><a href = \"http://localhost:8080/\">Order Submitted</a></h1>\n";
        message += "<br><b>Invoice:</b> " + new SimpleDateFormat().format(new Date());
        message += "<br>Bill To:";
        message += "<br>" + orderConfirm.getOrderInfo().getBillingName();
        message += "<br>" + orderConfirm.getOrderInfo().getBillingAddress();
        message += "<br>" + orderConfirm.getOrderInfo().getBillingCity() + ", " + orderConfirm.getOrderInfo().getBillingState() + " " + orderConfirm.getOrderInfo().getBillingZip();
        message += "<br><br>Ship To:";
        message += "<br>" + orderConfirm.getOrderInfo().getShippingName();
        message += "<br>" + orderConfirm.getOrderInfo().getShippingAddress();
        message += "<br>" + orderConfirm.getOrderInfo().getShippingCity() + ", " + orderConfirm.getOrderInfo().getShippingState() + " " + orderConfirm.getOrderInfo().getShippingZip();
        message += "<br><table style='border:1px solid black;border-collapse:collapse'>";
        message += "<br><tr>";
        message += "<br><th>Qty</th>";
        message += "<br><th>Description</th>";
        message += "<br><th>Unit Type</th>";
        message += "<br><th>Unit Price</th>";
        message += "<br><th>Total Price</th>";
        message += "<br></tr>";
        ShoppingCart cart = orderConfirm.getCart();
        for (int i = 0; i < cart.size(); i++) {
            message += "<tr>";
            message += "<br><td>" + cart.getBookQuantity(i) + "</td>";
            message += "<br><td>" + cart.getBook(i).getBookName()+ "</td>";
            String type = "";
            DecimalFormat format = new DecimalFormat("#.00");
            double price = 0;
            double unitPrice = 0;
            switch (cart.getBookType(i))
            {
                case 1:
                    type = "New";
                    price = cart.getBookQuantity(i) * cart.getBook(i).getNewPrice();
                    unitPrice = cart.getBook(i).getNewPrice();
                    break;
                case 2:
                    type = "Used";
                    price = cart.getBookQuantity(i) * cart.getBook(i).getUsedPrice();
                    unitPrice = cart.getBook(i).getUsedPrice();
                    break;
                case 3:
                    type = "Rental";
                    price = cart.getBookQuantity(i) * cart.getBook(i).getRentalPrice();
                    unitPrice = cart.getBook(i).getRentalPrice();
                    break;
                case 4:
                    type = "Ebook";
                    price = cart.getBookQuantity(i) * cart.getBook(i).getEbookPrice();
                    unitPrice = cart.getBook(i).getEbookPrice();
                    break;
            }
            message += "<br><td>" + type +"</td>";
            message += "<br><td>" + format.format(unitPrice) + "</td>";
            message += "<br><td>" + format.format(price) + "</td>";
            message += "</tr>";
        }
        message += "</table>";
        message += "<br><b>Subtotal:</b> $" + cart.getSubtotal();
        message += "<br><b>Tax:</b> $" + cart.getCalcTax();
        message += "<br><b>Shipping:</b> $" + new DecimalFormat("#.00").format(cart.getShipping());
        message += "<br><b>Total:</b> $" + cart.getTotalPrice();

        if(orderConfirm.getStudent() != null) {
            if (!orderConfirm.getStudent().isEmpty()) {
                message += "<br>You now have: $" + new DecimalFormat("#.00").format(orderConfirm.getStudent().getAidAmount());
                message += " in financial aid.";
            }
        }
        message += "<br><br><br>Thank You for shopping with the Kennesaw State University Bookstore!";


        return message;
    }

    public String toTextFile(OrderConfirmation orderConfirm) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("receipt.txt", "UTF-8");
        String message = "Order Submitted\n";
        message += "Invoice: " + new SimpleDateFormat().format(new Date())+ "\n\n";
        message += "Bill To:\n";
        message += orderConfirm.getOrderInfo().getBillingName() + "\n";
        message += orderConfirm.getOrderInfo().getBillingAddress()+ "\n";
        message += orderConfirm.getOrderInfo().getBillingCity() + ", ";
        message += orderConfirm.getOrderInfo().getBillingState() + " ";
        message += orderConfirm.getOrderInfo().getBillingZip() + "\n\n";
        message += "Ship To:\n";
        message += orderConfirm.getOrderInfo().getShippingName() + "\n";
        message += orderConfirm.getOrderInfo().getShippingAddress() + "\n";
        message += orderConfirm.getOrderInfo().getShippingCity() + ", ";
        message += orderConfirm.getOrderInfo().getShippingState() + " ";
        message += orderConfirm.getOrderInfo().getShippingZip() + "\n";
        ShoppingCart cart = orderConfirm.getCart();
        message += "Subtotal: $" + cart.getSubtotal() + "\n";
        message += "Tax: $" + cart.getCalcTax() + "\n";
        message += "Shipping: $" + new DecimalFormat("#.00").format(cart.getShipping()) + "\n";
        message += "Total: $" + cart.getTotalPrice() + "\n\n";
        if(orderConfirm.getStudent() != null) {
            if (!orderConfirm.getStudent().isEmpty()) {
                message += "You now have: $" + new DecimalFormat("#.00").format(orderConfirm.getStudent().getAidAmount());
                message += " in financial aid.\n\n";
            }
        }
        message += "Thank you for shopping with with Kennesaw State University Bookstore!";
        writer.println(message);
        writer.close();
        return message;
    }


    public void toEmail(String msg)
    {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(getEmailAddress(), getEmailPassword());
                    }
                });

        try {
            System.out.println("Email");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(""));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(getOutputEmail()));
            message.setSubject("Receipt");
            message.setContent(msg, "text/html");
            //message.setText(msg);

            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        ReceiptHandler receipt = new ReceiptHandler();
        OrderInfoHandler orderInfoHandler = new OrderInfoHandler();
        orderInfoHandler.setShippingName("Myles");
        orderInfoHandler.setShippingAddress("1100 S Marietta");
        orderInfoHandler.setShippingCity("Marietta");
        orderInfoHandler.setShippingState("Ga");
        orderInfoHandler.setShippingZip(30060);
        orderInfoHandler.shippingAsBilling();
        ShoppingCart cart = new ShoppingCart();
        BooksDatabase books = new BooksDatabase(new File("books.bks"));
        for (int i = 0; i < 4; i++) {
            cart.addToCart(books.getBooks().get(i), 2, 2);
        }
        Student student = new StudentsDatabase().getStudents().get("jwins22");
        System.out.println(receipt.toTextFile(new OrderConfirmation(cart, orderInfoHandler, student)));
    }
}
