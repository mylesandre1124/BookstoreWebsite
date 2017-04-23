package Objects;
import Exceptions.BookNotFoundException;
import com.sun.tools.javac.util.ArrayUtils;

import java.io.File;
import java.util.*;

/**
 * Created by Myles on 3/29/17.
 */
public class Search {

    private ImportSpreadsheet books = new ImportSpreadsheet();
    private TreeMap<Long, Book> isbnList;
    private TreeMap<Integer, ArrayList<Book>> courseList;
    private TreeMap<String, Book> titleList;
    private TreeMap<String, ArrayList<Book>> courseNameList;



    public Search() {
    }

    public Book searchByISBN(Long isbn) {
        File file = new File("books.bks");
        BooksDatabase booksDatabase = new BooksDatabase(file);
        //books.setBookSpreadsheet(file);
        books.setBookList(booksDatabase.getBooks());
        isbnList = books.importToISBNList();
        Book book = isbnList.get(isbn);
        return book;
    }


    public ArrayList<Book> searchByCourseNumber(int crn) {
        File file = new File("books.bks");
        BooksDatabase booksDatabase = new BooksDatabase(file);
        books.setBookList(booksDatabase.getBooks());
        courseList = books.importToCourseList();
        ArrayList<Book> books = courseList.get(crn);
        return books;
    }

    public Book serverSearch(String isbn) {
        Long isbn1 = Long.parseLong(isbn);
        Book book = searchByISBN(isbn1);
        return book;
    }

    public TreeMap<String, Book> searchByTitle(String search)
    {
        File file = new File("books.bks");
        BooksDatabase booksDatabase = new BooksDatabase(file);
        books.setBookList(booksDatabase.getBooks());
        titleList = books.importToTitleList();
        TreeMap<String, Book> searchResults = new TreeMap<>();
        Set set = titleList.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            //System.out.print("key is: " + mentry.getKey() + " & Value is: ");
            if(mentry.getKey().toString().toLowerCase().contains(search.toLowerCase()))
            {
                searchResults.put((String) mentry.getKey(), (Book)mentry.getValue());
            }
        }

        return searchResults;
    }


    public TreeMap<String, Book> searchByProfessor(String search)
    {
        File file = new File("books.bks");
        TreeMap<String, Book> professors = new TreeMap<>();
        BooksDatabase booksDatabase = new BooksDatabase(file);
        books.setBookList(booksDatabase.getBooks());
        TreeMap<String, ArrayList<Book>> professorList =books.importToProfessorList();
        Set set = professorList.entrySet();
        Iterator iterator = set.iterator();
        ArrayList<String> allProfessors = new ArrayList<>();
        ArrayList<String> selectProfessors = new ArrayList<>();

        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            allProfessors.add((String) mentry.getKey());
        }
        for (int i = 0; i < allProfessors.size(); i++) {
            if(allProfessors.get(i).contains(search))
            {
                selectProfessors.add(allProfessors.get(i));
            }
        }

        for (int i = 0; i < professorList.size(); i++) {
            professorList.entrySet();
        }
        return professors;
    }

    public ArrayList<Book> searchByDescription(String search)
    {
        File file = new File("books.bks");
        BooksDatabase booksDatabase = new BooksDatabase(file);
        books.setBookList(booksDatabase.getBooks());
        ArrayList<Book> results = new ArrayList<>();
        for (int i = 0; i < books.getBookList().size(); i++) {
            if(books.getBookList().get(i).getDescription().contains(search))
            {
                results.add(books.getBookList().get(i));
            }
        }
        return results;
    }


    public ArrayList<Book> populateKeywordSearch(String search)
    {
        ArrayList<Book> searchResults = new ArrayList<>();
        TreeMap<String, Book> titles = searchByTitle(search);
        //searchBy
        boolean isDigit = true;
        char[] chars = search.toCharArray();
        Character[] isbnconvert = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            isbnconvert[i] = new Character(chars[i]);
        }

        for (int i = 0; i < isbnconvert.length; i++) {
            if(!Character.isDigit(isbnconvert[i]))
            {
                isDigit = false;
            }
        }
        if (isDigit) {
            Book book = searchByISBN(Long.parseLong(search));
            searchResults.add(book);
        }
        Set set = titles.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            searchResults.add((Book) mentry.getValue());
        }
        ArrayList<Book> descriptionSearch = searchByDescription(search);
        for (int i = 0; i < descriptionSearch.size(); i++) {
            searchResults.add(descriptionSearch.get(i));
        }
        searchResults = deleteDuplicates(searchResults);
        return searchResults;
    }



    public ArrayList<Book> deleteDuplicates(ArrayList<Book> books)
    {
        TreeMap<Long, Book> check = new TreeMap<>();
        ArrayList<Book> results = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            check.put(books.get(i).getIsbn(), books.get(i));
        }
        Set set = check.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
        {
            Map.Entry entry = (Map.Entry) iterator.next();
            Book book = (Book) entry.getValue();
            results.add(book);
        }
        return results;
    }

    public TreeMap<String, ArrayList<Book>> searchByCourseName(String search)
    {
        TreeMap<String, ArrayList<Book>> results = new TreeMap<>();
        File file = new File("books.bks");
        BooksDatabase booksDatabase = new BooksDatabase(file);
        books.setBookList(booksDatabase.getBooks());
        String courseName = search.replace(" ", "");
        courseName = courseName.toLowerCase();
        TreeMap<String, ArrayList<Book>> courseNames = books.importToCourseNameList();
        if(courseNames.containsKey(courseName))
        {
            results.put(courseName, courseNames.get(courseName));
        }
        return results;
    }

    public ArrayList<Book> populateCourseSearch(String search)
    {
        ArrayList<Book> results = new ArrayList<>();
        if (search.matches("[0-9]+"))
        {
            int crn = Integer.parseInt(search);
            ArrayList<Book> courses = searchByCourseNumber(crn);
            for (int i = 0; i < courses.size(); i++) {
                results.add(courses.get(i));
            }
        }
        else
        {
            TreeMap<String, ArrayList<Book>> courseNames = searchByCourseName(search);
            for (int i = 0; i < courseNames.size(); i++) {
                for (int j = 0; j < 4; j++) {
                    //results.add(courseNames.get(i));
                }
            }
        }
        results = deleteDuplicates(results);
        return results;
    }



    public static void main(String[] args) {
        Search search = new Search();
        /*TreeMap<String, Book> titleList = search.searchByTitle("agift");
        Set set = titleList.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("key is: " + mentry.getKey() + " & Value is: " + mentry.getValue());
        }*/
        /*Book book = search.searchByISBN(9780132492676L);
        System.out.println(book.getBookName());
        BooksDatabase booksDatabase = new BooksDatabase(new File("books.bks"));
        book.setBookName("123");
        booksDatabase.deleteBook(book);
        book = search.searchByISBN(9780132492676L);
        System.out.println(book.getBookName());*/





        search.searchByProfessor("zey");
        //Book book = search.searchByISBN(9789544007737L);
        //System.out.println(book.getBookName());
    }

}
