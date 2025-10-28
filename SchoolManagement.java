package schoolmanagementsystem1; 

import java.io.*;
import java.util.*;


public class SchoolManagement  {
    
    private static final String DATA_FILE = "school_data.dat";
    private Map<String, ClassRoom> classes = new HashMap<>();

  
    private int instanceNextStudentIdNumber = 9999;

    public void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(this);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

       public static SchoolManagement load() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                System.out.println("Loading existing data...");
                return (SchoolManagement) in.readObject();
            } catch (Exception e) {
                System.out.println("Error loading data, starting new system: " + e.getMessage());
            }
        }
        System.out.println("No existing data found, starting a new system.");
        return new SchoolManagement();
    }

   
    public void classEnrollment(
            String classId, String className,
            String teacherId, String teacherName, String teacherDept,
            String studentName
    ) {
        if (classId.length() > 12 || teacherId.length() > 12) {
            throw new IllegalArgumentException("Class ID and Teacher ID must not exceed 12 characters.");
        }

        
        instanceNextStudentIdNumber++;
        String studentId = "GUR/" + instanceNextStudentIdNumber + "/16";

       
        if (studentId.length() > 12) {
            System.err.println("Warning: Generated Student ID '" + studentId + "' exceeds 12 characters.");
            System.err.println("You might encounter issues when entering this ID manually for grade assignment due to input validation rules.");
        }
        
        System.out.println("Student ID: " + studentId); 

        Teacher teacher = new Teacher(teacherName, teacherId, teacherDept);
        Student student = new Student(studentName, studentId);

        ClassRoom classroom = classes.get(classId);
        if (classroom == null) {
            classroom = new ClassRoom(classId, className, teacher);
            classes.put(classId, classroom);
        } else {
            classroom.setTeacher(teacher);
        }
        classroom.enrollStudent(student);
    }

   
    public void assignGrade(String classId, String studentId, double numericGrade) {
        if (classId.length() > 12 || studentId.length() > 12) {
            throw new IllegalArgumentException("Class ID and Student ID must not exceed 12 characters.");
        }

        ClassRoom classroom = classes.get(classId);
        if (classroom == null) {
            throw new IllegalArgumentException("Class with ID " + classId + " does not exist.");
        }
        Student student = classroom.getStudent(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + studentId + " not found in class " + classId + ".");
        }
        classroom.assignGrade(student, numericGrade);
    }

   
    public String fullReport() {
        StringBuilder sb = new StringBuilder();
        if (classes.isEmpty()) {
            sb.append("No classes enrolled yet.\n");
            return sb.toString();
        }
        sb.append("======== School Report ========\n");
        for (ClassRoom cr : classes.values()) {
            sb.append("Class Name: ").append(cr.getName()).append("\n");
            sb.append("Class ID: ").append(cr.getClassId()).append("\n");
            sb.append("Teacher: ").append(cr.getTeacher().getName()).append(" (ID: ")
              .append(cr.getTeacher().getId()).append(", Dept: ")
              .append(cr.getTeacher().getDepartment()).append(")\n");
            sb.append("Enrolled Students:\n");
            Collection<Student> students = cr.getStudents();
            if (students.isEmpty()) {
                sb.append("  (none)\n");
            }
            for (Student s : students) {
                sb.append("  Name: ").append(s.getName())
                  .append(", ID: ").append(s.getId())
                  .append(", Numeric Grade: ").append(s.getNumericGrade(cr.getClassId()))
                  .append(", Letter Grade: ").append(s.getLetterGrade(cr.getClassId()))
                  .append(", Class Name: ").append(cr.getName())
                  .append(", Teacher Name: ").append(cr.getTeacher().getName())
                  .append("\n");
            }
            sb.append("-----------------------------\n");
        }
        return sb.toString();
    }
}
