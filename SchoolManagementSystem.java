package schoolmanagementsystem1;

import java.util.Scanner;


public class SchoolManagementSystem   { 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SchoolManagement system = SchoolManagement.load(); 

        final String ADMIN_NAME = "admin";
        final String ADMIN_PASSWORD = "1234";

        System.out.println("Welcome to School Management System");
        System.out.print("Enter Admin Name: ");
        String adminName = scanner.nextLine();
        System.out.print("Enter Admin Password : ");
        String adminPassword = scanner.nextLine();

        
        if (!adminName.equals(ADMIN_NAME) || !adminPassword.equals(ADMIN_PASSWORD)) {
            System.out.println("Access denied. Exiting.");
            scanner.close(); 
            return;
        }

        if (adminPassword.length() != 4) {
            System.out.println("Password must be exactly 4 characters.");
            scanner.close(); 
            return;
        }

        System.out.println("Login successful. Welcome, " + adminName + "!");
        boolean running = true;
        while (running) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Class Enrollment");
            System.out.println("2. Grade Assignment");
            System.out.println("3. Report All Information");
            System.out.println("4. Exit & Save");
            System.out.print("Select option: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    
                    System.out.println("== Class Enrollment ==");
                    System.out.print("Class Name: ");
                    String className = scanner.nextLine();
                    String classId = getValidatedId(scanner, "Class ID: ");
                    System.out.print("Teacher Name: ");
                    String teacherName = scanner.nextLine();
                    String teacherId = getValidatedId(scanner, "Teacher ID : ");
                    System.out.print("Teacher Department: ");
                    String teacherDept = scanner.nextLine();
                    System.out.print("Student Name: ");
                    String studentName = scanner.nextLine();
                    
                    try {
                        system.classEnrollment(classId, className, teacherId, teacherName, teacherDept, studentName);
                        System.out.println("Enrollment complete."); 
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error during enrollment: " + e.getMessage());
                    }
                    break;
                case "2":
                   
                    System.out.println("== Grade Assignment ==");
                   
                   classId = getValidatedId(scanner, "Class ID: ");
                 
                    String studentId = getValidatedId(scanner, "Student ID: ");
                    
                    double numericGrade = getValidatedGrade(scanner, "Numeric Grade: ");
                    try {
                        system.assignGrade(classId, studentId, numericGrade);
                        System.out.println("Grade assigned.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "3":

                    System.out.println(system.fullReport());
                    break;
                case "4":
                    
                    system.save();
                    System.out.println("Data saved. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close(); 
    }

    private static String getValidatedId(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String id = scanner.nextLine();
            if (id.length() > 0 && id.length() <= 12) return id;
          
            System.out.println("Invalid ID format. Please try again.");
        }
    }

   
    private static double getValidatedGrade(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double grade = Double.parseDouble(scanner.nextLine());
                if (grade >= 0 && grade <= 100) return grade;
               
                else System.out.println("Invalid grade value. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric grade. Try again.");
            }
        }
    }
}
