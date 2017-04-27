package Objects;

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
        importBooks();
        isbnList = books.importToISBNList();
        Book book = isbnList.get(isbn);
        return book;
    }

    public Book searchByISBNString(String isbn)
    {
        importBooks();
        TreeMap<String, Book> book = books.importToISBNStringList();
        return book.get(isbn);

    }


    public ArrayList<Book> searchByCourseNumber(int crn) {
        importBooks();
        courseList = books.importToCourseList();
        ArrayList<Book> books = courseList.get(crn);
        return books;
    }


    public TreeMap<String, Book> searchByTitle(String search) {
        importBooks();
        titleList = books.importToTitleList();
        TreeMap<String, Book> searchResults = new TreeMap<>();
        Set set = titleList.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            //System.out.print("key is: " + mentry.getKey() + " & Value is: ");
            if (mentry.getKey().toString().toLowerCase().contains(search.toLowerCase())) {
                searchResults.put((String) mentry.getKey(), (Book) mentry.getValue());
            }
        }

        return searchResults;
    }


    public ArrayList<Book> searchByDescription(String search) {
        importBooks();
        ArrayList<Book> results = new ArrayList<>();
        for (int i = 0; i < books.getBookList().size(); i++) {
            if (books.getBookList().get(i).getDescription().contains(search)) {
                results.add(books.getBookList().get(i));
            }
        }
        return results;
    }

    public ArrayList<Book> searchByAuthor(String search)
    {
        ArrayList<Book> results = new ArrayList<>();


        return results;
    }

    public ArrayList<Book> populateKeywordSearch(String search) {
        ArrayList<Book> searchResults = new ArrayList<>();
        TreeMap<String, Book> titles = searchByTitle(search);
        //searchBy
        searchResults.add(searchByISBNString(search));
        boolean isDigit = true;
        char[] chars = search.toCharArray();
        Character[] isbnconvert = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            isbnconvert[i] = new Character(chars[i]);
        }

        for (int i = 0; i < isbnconvert.length; i++) {
            if (!Character.isDigit(isbnconvert[i])) {
                isDigit = false;
            }
        }
        if (isDigit) {
            if(!search.equals("")) {
                Book book = searchByISBN(Long.parseLong(search));
                searchResults.add(book);
            }
        }
        Set set = titles.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
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


    public ArrayList<Book> deleteDuplicates(ArrayList<Book> books) {
        TreeMap<Long, Book> check = new TreeMap<>();
        ArrayList<Book> results = new ArrayList<>();
        if(!books.isEmpty()) {
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i).getIsbn());
                check.put(books.get(i).getIsbn(), books.get(i));
            }
            Set set = check.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Book book = (Book) entry.getValue();
                results.add(book);
            }
        }
        return results;
    }

    public TreeMap<String, ArrayList<Book>> searchByProfessor(String search) {
        importBooks();
        search = convertToSearchableText(search);
        TreeMap<String, ArrayList<Book>> professors = new TreeMap<>();
        TreeMap<String, ArrayList<Book>> professorList = books.importToProfessorList();
        Set set = professorList.entrySet();
        Iterator iterator = set.iterator();
        ArrayList<String> allProfessors = new ArrayList<>();
        ArrayList<String> selectProfessors = new ArrayList<>();

        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            allProfessors.add((String) mentry.getKey());
        }
        for (int i = 0; i < allProfessors.size(); i++) {
            String converted  = allProfessors.get(i);
            converted = convertToSearchableText(converted);
            if (converted.contains(search)) {
                selectProfessors.add(allProfessors.get(i));
            }
        }

        for (int i = 0; i < selectProfessors.size(); i++) {
            professors.put(selectProfessors.get(i), professorList.get(selectProfessors.get(i)));
        }
        return professors;
    }

    public ArrayList<Book> populateProfessorSearch(String search)
    {
        TreeMap<String, ArrayList<Book>> professors = searchByProfessor(search);
        Iterator iterator = getIterator(professors);
        ArrayList<Book> results = new ArrayList<>();
        while (iterator.hasNext())
        {
            Map.Entry entry = (Map.Entry) iterator.next();
            ArrayList<Book> books = (ArrayList<Book>) entry.getValue();
            for (int i = 0; i < books.size(); i++) {
                results.add(books.get(i));
            }
        }
        return results;
    }

    public TreeMap<String, ArrayList<Book>> searchByCourseName(String search) {
        TreeMap<String, ArrayList<Book>> results = new TreeMap<>();
        importBooks();
        String courseName = convertToSearchableText(search);
        TreeMap<String, ArrayList<Book>> courseNames = books.importToCourseNameList();
        if (courseNames.containsKey(courseName)) {
            results.put(courseName, courseNames.get(courseName));
        }
        return results;
    }

    public ArrayList<Book> populateCourseSearch(String search) {
        ArrayList<Book> results = new ArrayList<>();
        search = convertToSearchableText(search);
        if (search.matches("[0-9]+")) {
            int crn = Integer.parseInt(search);
            ArrayList<Book> courses = searchByCourseNumber(crn);
            for (int i = 0; i < courses.size(); i++) {
                results.add(courses.get(i));
            }
        } else {
            TreeMap<String, ArrayList<Book>> courseNames = searchByCourseName(search);
            if(!courseNames.isEmpty()) {
                for (int i = 0; i < courseNames.get(search).size(); i++) {
                    results.add(courseNames.get(search).get(i));
                }
            }

        }
        results = deleteDuplicates(results);
        return results;
    }

    public void printArrayList(ArrayList books)
    {
        if(books.get(0) instanceof Book) {
            ArrayList<Book> books1 = (ArrayList<Book>) books;
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books1.get(i).getBookName());
            }
        }
        else if(books.get(0) instanceof String)
        {
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i));
            }
        }
    }

    public void printTreeMapBook(TreeMap treeMap)
    {
        Set set = treeMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.print("key is: " + entry.getKey() + " & Value is: ");
            if(entry.getValue() instanceof ArrayList) {
                ArrayList<Book> books = (ArrayList<Book>) entry.getValue();
                for (int i = 0; i < books.size(); i++) {
                    if (i != (books.size() - 1)) {
                        System.out.print(books.get(i).getBookName() + " + ");
                    } else {
                        System.out.print(books.get(i).getBookName());
                    }
                }
            }
            else if(entry.getValue() instanceof Book)
            {
                System.out.print(entry.getValue());
            }
            System.out.println();
        }
    }

    public void importBooks()
    {
        File file = new File("books.bks");
        BooksDatabase booksDatabase = new BooksDatabase(file);
        books.setBookList(booksDatabase.getBooks());
    }

    public String convertToSearchableText(String text)
    {
        text = text.replaceAll(" ", "");
        text = text.toLowerCase();
        return text;
    }

    public Iterator getIterator(TreeMap treeMap)
    {
        Set set = treeMap.entrySet();
        Iterator iterator = set.iterator();
        return iterator;
    }

    public static void main(String[] args) {
        Search search = new Search();
        /*TreeMap<String, Book> titleList = search.searchByTitle("agift");

        /*Book book = search.searchByISBN(9780132492676L);
        System.out.println(book.getBookName());
        BooksDatabase booksDatabase = new BooksDatabase(new File("books.bks"));
        book.setBookName("123");
        booksDatabase.deleteBook(book);
        book = search.searchByISBN(9780132492676L);
        System.out.println(book.getBookName());*/


        TreeMap<String, ArrayList<Book>> books  = search.searchByProfessor("a");
        //ArrayList<Book> booklist = books.get("es2100");
        search.printTreeMapBook(books);
        //Book book = search.searchByISBN(9789544007737L);
        //System.out.println(book.getBookName());
    }

}
