package Objects;

import com.sun.org.apache.xpath.internal.operations.Mult;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Myles on 4/27/17.
 */
public class ReceiptHandler {

    public String htmlMessage(OrderConfirmation orderConfirm)
    {
        String message = "<h1>Order Submitted</h1>";
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
        message += "<tr>";
        ShoppingCart cart = orderConfirm.getCart();
        for (int i = 0; i < cart.size(); i++) {
            message += "<br><td>" + cart.getBookQuantity(i) + "</td>";
            message += "<br><td>" + cart.getBook(i).getDescription()+ "</td>";
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
                    unitPrice = cart.getBook(i).getNewPrice();
                    break;
                case 3:
                    type = "Rental";
                    price = cart.getBookQuantity(i) * cart.getBook(i).getRentalPrice();
                    unitPrice = cart.getBook(i).getNewPrice();
                    break;
                case 4:
                    type = "Ebook";
                    price = cart.getBookQuantity(i) * cart.getBook(i).getEbookPrice();
                    unitPrice = cart.getBook(i).getNewPrice();
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
        message += "<br><b>Shipping:</b> $" + cart.getShipping();
        message += "<br><b>Total:</b> $" + cart.getTotalPrice();




        message += "<br><br><br>Thank You for shopping with the Kennesaw State University Bookstore!";


        return message;
    }

    public void toEmail(String msg)
    {
        final String username = "mylesandre1124@gmail.com";
        final String password = "megamacman1124";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mylesandre1124@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("mylesandre1124@gmail.com"));
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

    public static void main(String[] args) {
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
            cart.addToCart(books.getBooks().get(i), 1, 2);
        }
        receipt.toEmail(receipt.htmlMessage(new OrderConfirmation(cart, orderInfoHandler, new Student())));
    }
}
