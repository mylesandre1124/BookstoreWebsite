package Objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Myles on 4/27/17.
 */
public class StudentsDatabase {

    private TreeMap<String, Student> students = new TreeMap<>();

    public StudentsDatabase() throws FileNotFoundException {
        File studentsFile = new File("students.sts");
        ObjectIO objectIO = new ObjectIO(studentsFile);
        if(!studentsFile.exists()) {
            students = importStudents();
            objectIO.writeObject(students);
        }
        else if(studentsFile.exists())
        {
            try {
                students = (TreeMap<String, Student>) objectIO.readObject();
            }
            catch (ClassCastException ex)
            {
                reset();
            }
        }
    }

    public TreeMap<String, Student> importStudents() throws FileNotFoundException {
        File file = new File("students.txt");
        Scanner input = new Scanner(file);
        TreeMap<String, Student> students = new TreeMap<>();
        while (input.hasNext()) {
            Student student = new Student();
            student.setFirstName(input.next());
            student.setLastName(input.next());
            student.setUsername(input.next());
            student.setPassword(input.next());
            student.setAidAmount(input.nextDouble());
            students.put(student.getUsername(), student);
        }
        return students;
    }

    public TreeMap<String, Student> getStudents() {
        return students;
    }

    public void setStudents(TreeMap<String, Student> students) {
        this.students = students;
    }

    public Student login(String username, String password)
    {
        TreeMap<String, Student> students = getStudents();
        if(students.containsKey(username))
        {
            Student student = students.get(username);
            String studentPassword = student.getPassword();
            if(studentPassword.equals(password))
            {
                return student;
            }
        }
        return null;
    }

    public void updateStudent(Student student)
    {
        File studentFile = new File("students.sts");
        students.replace(student.getUsername(), student);
        studentFile.delete();
        studentFile = new File("students.sts");
        ObjectIO objectIO = new ObjectIO(studentFile);
        objectIO.writeObject(students);
    }

    public void reset() {
        File studentFile = new File("students.sts");
        studentFile.delete();
        try {
            this.students = importStudents();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectIO objectIO = new ObjectIO(studentFile);
        objectIO.writeObject(this.students);
    }

    public static void main(String[] args) throws FileNotFoundException {
        StudentsDatabase sd = new StudentsDatabase();
        Student student = sd.getStudents().get("jwins22");
        student.changeFinancialAid(50.0);
        sd.updateStudent(student);
        sd = new StudentsDatabase();
        System.out.println(sd.getStudents().get("jwins22").getAidAmount());
    }

}
