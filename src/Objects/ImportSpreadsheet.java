package Objects;
/**
 * Created by Myles on 3/28/17.
 */

import au.com.bytecode.opencsv.CSVReader;

import java.io.*;
import java.util.*;

public class ImportSpreadsheet{

    private File bookSpreadsheet;
    private ArrayList<Book> bookList = new ArrayList<>();
    private TreeMap<Long, Book> isbnList;


    /**
     * Create a file object that references the books.csv file in java and pass it to this constructor so that the object
     * knows where to import the spreadsheet
     *
     * @param bookSpreadsheet
     */
    public ImportSpreadsheet(File bookSpreadsheet) {
        this.bookSpreadsheet = bookSpreadsheet;
    }

    /**
     * Creates empty Import Books object
     */
    public ImportSpreadsheet() {
    }

    /**
     * Returns book spreadsheet file
     * @return File
     */
    public File getBookSpreadsheet() {
        return bookSpreadsheet;
    }

    /**
     * Pass book spreadsheet file to set the file
     * @param bookSpreadsheet Spreadsheet file
     */
    public void setBookSpreadsheet(File bookSpreadsheet) {
        this.bookSpreadsheet = bookSpreadsheet;
    }

    /**
     * Gets the book arraylist
     * @return ArrayList
     */
    public ArrayList<Book> getBookList() {
        return bookList;
    }

    /**
     * Sets the book arraylist
     */
    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }


    /**
     * Imports spreadsheet file from this object and adds the books to the booklist Arraylist and returns the list.
     *
     * <pre>
     *     An example of how to use this is as follows:
     *     {@code
     *          ImportSpreadsheet importSpreadsheet = new ImportSpreadsheet();
     *          importSpreadsheet.setBookSpreadsheet(new File("books.csv"));
     *          ArrayList<Book> books = importSpreadsheet.importSpreadsheet();
     *     }
     * </pre>
     * @return ArrayList
     */
    public ArrayList<Book> importSpreadSheet()
    {
        try
        {
            CSVReader reader = null;
            reader = new CSVReader(new FileReader(getBookSpreadsheet()), ',', '"', 0);
            String[] nextLine;
            Integer[] strings = new Integer[]{1, 2, 3, 4, 6, 8, 17};
            Integer[] integers = new Integer[]{0, 5, 7, 9, 10, 11, 12};
            Integer[] doubles = new Integer[]{13, 14, 15, 16};
            while((nextLine = reader.readNext()) != null)
            {
                Book book = new Book();
                for (int increment = 0; increment < nextLine.length; increment++) {
                if (Arrays.asList(strings).contains(increment)) {
                    String string = nextLine[increment];
                    switch (increment) {
                        case 1:
                            book.setBookName(string);
                            break;
                        case 2:
                            String[] authors = string.split("(; )|(, )|(& )|(,)|(,  )|(and)|(, & )");
                            for (int i = 0; i < authors.length; i++) {
                                authors[i] = authors[i].replace(" and ", "");
                            }
                            book.setAuthor(authors);
                            break;
                        case 3:
                            book.setSemester(string);
                            break;
                        case 4:
                            book.setCourse(string);
                            break;
                        case 6:
                            book.setProfessor(string);
                            break;
                        case 8:
                            book.setBuyDemand(string);
                            break;
                        case 17:
                            book.setDescription(string);
                            break;
                    }
                }
                else if (Arrays.asList(integers).contains(increment)) {
                    int integer = -1;
                    long long1 = -1;
                    try {
                        if (increment != 0) {
                            integer = Integer.parseInt(nextLine[increment]);
                        } else {
                            book.setIsbnForCovers(nextLine[increment]);
                            long1 = Long.parseLong(nextLine[increment].replace("-", ""));
                        }
                    }
                    catch (NumberFormatException ex)
                    {
                    }
                    switch (increment){
                        case 0:
                            book.setIsbn(long1);
                            break;
                        case 5:
                            book.setSection(integer);
                            break;
                        case 7:
                            book.setCrn(integer);
                            break;
                        case 9:
                            book.setNewQuantity(integer);
                            break;
                        case 10:
                            book.setUsedQuantity(integer);
                            break;
                        case 11:
                            book.setRentalQuantity(integer);
                            break;
                        case 12:
                            book.setEbookQuantity(integer);
                            break;
                    }
                }
                else if (Arrays.asList(doubles).contains(increment)) {
                    double double1 = Double.parseDouble(nextLine[increment]);
                    switch (increment) {
                        case 13:
                            book.setNewPrice(double1);
                            break;
                        case 14:
                            book.setUsedPrice(double1);
                            break;
                        case 15:
                            book.setRentalPrice(double1);
                            break;
                        case 16:
                            book.setEbookPrice(double1);
                            break;
                    }
                }
                }
                this.getBookList().add(book);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.getBookList();
    }

    public TreeMap<Long, Book> importToISBNList()
    {
        TreeMap<Long, Book> isbnList = new TreeMap<>();
        for (int i = 0; i < this.bookList.size(); i++) {
            isbnList.put(this.bookList.get(i).getIsbn(), this.bookList.get(i));
        }
        this.isbnList = isbnList;
        return isbnList;
        /*File file = new File("isbn.lis");
        ObjectIO objectIO = new ObjectIO(file);
        objectIO.writeObject(isbnList);*/
    }

    /**
     * Converts the Book arraylist to a TreeMap that acts like a database that holds
     * the CRN as a primary key and holds the book ArrayList object as it's value. Just enter the
     * CRN in the TreeMap and the Arraylist containing all of the books that are necessary for that particular
     * course are returned.
     *
     *
     * <pre>
     *     An example of how to use this is as follows:
     *     {@code
     *          ImportSpreadsheet importSpreadsheet = new ImportSpreadsheet();
     *          importSpreadsheet.setBookSpreadsheet(new File("books.csv"));
     *          TreeMap<Integer, ArrayList<Book>> courses = importSpreadsheet.importToCourseList();
     *          ArrayList<Book> booksForThisCRN = courses.get(2417);
     *      }
     *      The rest of the code to search by CRN is in the Search Object
     * </pre>
     *
     * @return TreeMap
     */
    public TreeMap<Integer, ArrayList<Book>> importToCourseList()
    {
        TreeMap<Integer, ArrayList<Book>> courseList = new TreeMap<>();
        for (int i = 0; i < this.bookList.size(); i++) {
            courseList.put(this.bookList.get(i).getCrn(), new ArrayList<Book>());
        }
        for (int i = 0; i < this.bookList.size(); i++) {
            ArrayList<Book> courses = courseList.get(this.bookList.get(i).getCrn());
            courses.add(this.bookList.get(i));
            courseList.replace(this.bookList.get(i).getCrn(), courses);
        }
        /*
        Set set = courseList.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
            ArrayList<Book> books = (ArrayList<Book>) mentry.getValue();
            for (int i = 0; i < books.size(); i++) {
                if(books.size() == 1) {
                    System.out.print(books.get(i).getBookName());
                }
                else if(i != (books.size() - 1)){
                    System.out.print(books.get(i).getBookName() + " + ");
                }
                else if(i == (books.size() - 1)){
                    System.out.print(books.get(i).getBookName());
                }
            }
            System.out.println();
        }*/
        return courseList;
    }


    public TreeMap<String, Book> importToTitleList()
    {
        TreeMap<String, Book> titleList = new TreeMap<>();
        for (int i = 0; i < this.bookList.size(); i++) {
            titleList.put(this.bookList.get(i).getBookName(), this.bookList.get(i));
        }
        return titleList;
    }

    public TreeMap<String, ArrayList<Book>> importToProfessorList()
    {
        TreeMap<String, ArrayList<Book>> professorList = new TreeMap<>();
        for (int i = 0; i < this.bookList.size(); i++) {
            professorList.put(this.bookList.get(i).getProfessor(), new ArrayList<Book>());
        }
        for (int i = 0; i < this.bookList.size(); i++) {
            ArrayList<Book> professors = professorList.get(this.bookList.get(i).getProfessor());
            professors.add(this.bookList.get(i));
            professorList.replace(this.bookList.get(i).getProfessor(), professors);
        }
        return professorList;
    }

    public TreeMap<String, ArrayList<Book>> importToCourseNameList()
    {
        TreeMap<String, ArrayList<Book>> courseNameList = new TreeMap<>();
        for (int i = 0; i < this.bookList.size(); i++) {
            String courseName = this.bookList.get(i).getCourse();
            courseName = courseName.replace(" ", "");
            courseName = courseName.toLowerCase();
            courseNameList.put(courseName, new ArrayList<>());
        }
        for (int i = 0; i < this.bookList.size(); i++) {
            String courseName = this.bookList.get(i).getCourse();
            courseName = courseName.replace(" ", "");
            courseName = courseName.toLowerCase();
            ArrayList<Book> courses = courseNameList.get(courseName);
            courses.add(bookList.get(i));
            courseNameList.replace(courseName, courses);
        }
        return courseNameList;
    }

    /*public void printTreeMap(TreeMap treeMap)
    {
        Set set = treeMap.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print("key is: " + mentry.getKey() + " & Value is: ");

        }
    }*/


    public static void main(String[] args) {

        File file = new File("books.csv");
        System.out.println(file.getAbsolutePath());
        ImportSpreadsheet importSpreadsheet= new ImportSpreadsheet(file);
        importSpreadsheet.importSpreadSheet();
        TreeMap<String, ArrayList<Book>> professorList = importSpreadsheet.importToProfessorList();
        System.out.println(professorList.toString());
        Set set = professorList.entrySet();
        Iterator iterator = set.iterator();
        /*while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print("key is: " + mentry.getKey() + " & Value is: ");
            ArrayList<Book> books = (ArrayList<Book>) mentry.getValue();
            for (int i = 0; i < books.size(); i++) {
                System.out.print(books.get(i).getBookName() +  " + ");
            }
            System.out.println();
        }/*
        /*
        for (int i = 0; i < importSpreadsheet.getBookList().size(); i++) {
            for (int j = 0; j < importSpreadsheet.getBookList().get(i).getAuthor().length; j++) {
                System.out.println(importSpreadsheet.getBookList().get(i).getAuthor()[j]);
            }
        }*/
        //TreeMap<Long, Book> isbnlist = importSpreadsheet.importToISBNList();
        //importSpreadsheet.importToCourseList();
        //Book book = importSpreadsheet.searchISBN(new Long(9780321267160L), isbnlist);
        //System.out.println(book.getBookName());

    }


}
