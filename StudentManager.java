import java.io.*;
import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManager() {
        students = new ArrayList<>();
    }

    // Add Student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Search Student
    public Student searchStudent(int id) {

        for (Student s : students) {

            if (s.getId() == id) {
                return s;
            }
        }

        return null;
    }

    // Delete Student
    public boolean deleteStudent(int id) {

        Student s = searchStudent(id);

        if (s != null) {
            students.remove(s);
            return true;
        }

        return false;
    }

    // Return all students for GUI display
    public ArrayList<Student> getAllStudents() {
        return students;
    }

    // Save Data
    public void saveToFile() {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_NAME));

            out.writeObject(students);

            out.close();

        } catch (Exception e) {

            System.out.println("Error Saving Data");
        }
    }

    // Load Data
    @SuppressWarnings("unchecked")
    public void loadFromFile() {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream(FILE_NAME));

            students =
                    (ArrayList<Student>) in.readObject();

            in.close();

        } catch (Exception e) {

            students = new ArrayList<>();
        }
    }
}