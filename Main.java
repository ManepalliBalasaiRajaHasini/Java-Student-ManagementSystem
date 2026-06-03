import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener {

    private JTextField idField, nameField, ageField, courseField;
    private JTextArea outputArea;

    private JButton addButton, viewButton, searchButton,
            deleteButton, saveButton;

    private StudentManager manager;

    public Main() {

        manager = new StudentManager();
        manager.loadFromFile();

        setTitle("Student Management System");
        setSize(900, 600);
        setLayout(null);

        getContentPane().setBackground(new Color(250, 240, 245));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("STUDENT MANAGEMENT SYSTEM");
        title.setFont(new Font("Verdana", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 102));
        title.setBounds(170, 15, 450, 30);
        add(title);

        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        idLabel.setBounds(50, 80, 120, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(180, 80, 220, 30);
        add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setBounds(50, 125, 120, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 125, 220, 30);
        add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ageLabel.setBounds(50, 170, 120, 25);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(180, 170, 220, 30);
        add(ageField);

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setFont(new Font("Arial", Font.BOLD, 14));
        courseLabel.setBounds(50, 215, 120, 25);
        add(courseLabel);

        courseField = new JTextField();
        courseField.setBounds(180, 215, 220, 30);
        add(courseField);

        addButton = new JButton("Add Record");
        addButton.setBounds(30, 280, 120, 35);
        addButton.setBackground(new Color(144, 238, 144));
        addButton.addActionListener(this);
        add(addButton);

        viewButton = new JButton("View");
        viewButton.setBounds(180, 280, 120, 35);
        viewButton.setBackground(new Color(173, 216, 230));
        viewButton.addActionListener(this);
        add(viewButton);

        searchButton = new JButton("Search");
        searchButton.setBounds(330, 280, 120, 35);
        searchButton.setBackground(new Color(255, 255, 153));
        searchButton.addActionListener(this);
        add(searchButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(480, 280, 120, 35);
        deleteButton.setBackground(new Color(255, 182, 193));
        deleteButton.addActionListener(this);
        add(deleteButton);

        saveButton = new JButton("Save");
        saveButton.setBounds(630, 280, 120, 35);
        saveButton.setBackground(new Color(255, 218, 185));
        saveButton.addActionListener(this);
        add(saveButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(30, 340, 720, 140);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            if (e.getSource() == addButton) {

                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String course = courseField.getText();

                Student student =
                        new Student(id, name, age, course);

                manager.addStudent(student);

                outputArea.setText("Student Added Successfully!");

                clearFields();
            }

            else if (e.getSource() == viewButton) {

                ArrayList<Student> students =
                        manager.getAllStudents();

                outputArea.setText("");

                if (students.isEmpty()) {

                    outputArea.setText("No Students Found.");
                }

                else {

                    for (Student s : students) {

                        outputArea.append(
                                s.toString() + "\n\n");
                    }
                }
            }

            else if (e.getSource() == searchButton) {

                int id =
                        Integer.parseInt(idField.getText());

                Student s =
                        manager.searchStudent(id);

                if (s != null) {

                    outputArea.setText(
                            s.toString());
                }

                else {

                    outputArea.setText(
                            "Student Not Found.");
                }
            }

            else if (e.getSource() == deleteButton) {

                int id =
                        Integer.parseInt(idField.getText());

                boolean deleted =
                        manager.deleteStudent(id);

                if (deleted) {

                    outputArea.setText(
                            "Student Deleted Successfully.");
                }

                else {

                    outputArea.setText(
                            "Student Not Found.");
                }
            }

            else if (e.getSource() == saveButton) {

                manager.saveToFile();

                outputArea.setText(
                        "Data Saved Successfully!");
            }

        } catch (Exception ex) {

            outputArea.setText(
                    "Please Enter Valid Data.");
        }
    }

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        courseField.setText("");
    }

    public static void main(String[] args) {

        new Main();
    }
}