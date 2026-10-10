package com.grademanager;

import java.util.Scanner;

public class Main {
    private static final GradeManager manager = new GradeManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Student Grade Manager!");

        while (true) {
            printMenu();

            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewAllStudents();
                    case 3 -> searchStudent();
                    case 4 -> updateMarks();
                    case 5 -> deleteStudent();
                    case 6 -> {
                        System.out.println("Exiting Student Grade Manager.");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("========== STUDENT GRADE MANAGER ==========");
        System.out.println("1. Add Student");
        System.out.println("2. View All Results");
        System.out.println("3. Search Student");
        System.out.println("4. Update Marks");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
    }

    private static void addStudent() {
        String id = readText("Enter Student ID: ");
        String name = readText("Enter Student Name: ");

        Student student = new Student(id, name);

        int count = readInt("Number of subjects: ");

        if (count < 1 || count > 20) {
            throw new IllegalArgumentException(
                "Number of subjects must be between 1 and 20"
            );
        }

        for (int i = 0; i < count; i++) {
            String subject = readText("Enter subject name: ");
            int mark = readInt("Enter marks for " + subject + ": ");
            student.addOrUpdateMark(subject, mark);
        }

        manager.addStudent(student);
        System.out.println("Student added successfully!");
        displayStudent(student);
    }

    private static void viewAllStudents() {
        if (manager.getAllStudents().isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student student : manager.getAllStudents()) {
            displayStudent(student);
        }
    }

    private static void searchStudent() {
        String id = readText("Enter Student ID: ");

        manager.findStudent(id).ifPresentOrElse(
            Main::displayStudent,
            () -> System.out.println("Student not found.")
        );
    }

    private static void updateMarks() {
        String id = readText("Enter Student ID: ");

        Student student = manager.findStudent(id)
            .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        String subject = readText("Enter subject name: ");
        int mark = readInt("Enter new marks: ");

        student.addOrUpdateMark(subject, mark);
        System.out.println("Marks updated successfully.");
        displayStudent(student);
    }

    private static void deleteStudent() {
        String id = readText("Enter Student ID: ");

        if (manager.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void displayStudent(Student student) {
        System.out.println("\n========== STUDENT RESULT ==========");
        System.out.println("Student ID : " + student.getId());
        System.out.println("Name       : " + student.getName());

        student.getMarks().forEach(
            (subject, mark) ->
                System.out.println(subject + " : " + mark + "/100")
        );

        System.out.println("Total      : " + student.getTotal());
        System.out.printf("Average    : %.2f%%%n", student.getAverage());
        System.out.println("Grade      : " + student.getGrade());
        System.out.println("====================================");
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid integer");
        }
    }
}
