package Objects;

import Exceptions.StockException;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Myles on 4/18/17.
 */
public class BooksDatabase {

    private File bookFile;
    private ArrayList<Book> books;

    public BooksDatabase(File bookFile) {
        this.bookFile = bookFile;
        if(!this.bookFile.exists())
        {
            this.books = new ImportSpreadsheet(new File("books.csv")).importSpreadSheet();
            ObjectIO objectIO = new ObjectIO(getBookFile());
            objectIO.writeObject(getBooks());
        }
        if(getBookFile().exists())
        {
            ObjectIO objectIO = new ObjectIO(getBookFile());
            this.books = (ArrayList<Book>) objectIO.readObject();
        }
    }


    public File getBookFile() {
        return bookFile;
    }

    public void setBookFile(File bookFile) {
        this.bookFile = bookFile;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void updateBook(Book book)
    {
        for (int i = 0; i < getBooks().size(); i++) {
            if (getBooks().get(i).getIsbn() == book.getIsbn())
            {
                getBooks().remove(i);
                getBooks().add(i, book);
            }
        }
        String filename = bookFile.getName();
        bookFile.delete();
        bookFile = new File(filename);
        ObjectIO objectIO = new ObjectIO(bookFile);
        objectIO.writeObject(getBooks());
    }

    public void deleteBook(Book book)
    {
        for (int i = 0; i < getBooks().size(); i++) {
            if(getBooks().get(i).getIsbn() == book.getIsbn())
            {
                getBooks().remove(i);
            }
        }
        String filename = bookFile.getName();
        bookFile.delete();
        bookFile = new File(filename);
        ObjectIO objectIO = new ObjectIO(bookFile);
        objectIO.writeObject(getBooks());
    }



    public static void main(String[] args) throws StockException {
        BooksDatabase booksDatabase = new BooksDatabase(new File("books.bks"));
        ArrayList<Book> books = booksDatabase.getBooks();
        for (int i = 0; i < books.size(); i++) {
            if (booksDatabase.getBooks().get(i).getIsbn() == 9780132492676L) {
                Book book = books.get(i);
                System.out.println(book.getBookName());
                System.out.println("1 " + booksDatabase.getBooks().get(i).getNewQuantity());
                book.changeNewQuantity(6);
                booksDatabase.updateBook(book);
                System.out.println("2 " + booksDatabase.getBooks().get(i).getNewQuantity());
            }
        }
        ObjectIO objectIO = new ObjectIO(new File("books.bks"));
        ArrayList<Book> book = (ArrayList<Book>) objectIO.readObject();
        for (int i = 0; i < book.size(); i++) {
            if (book.get(i).getIsbn() == 9780132492676L) {
                System.out.println("3 " + book.get(i).getNewQuantity());
            }
        }
    }

}
