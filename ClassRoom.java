package schoolmanagementsystem1;



import java.util.*;

public class ClassRoom   { 


    private String classId;
    private String name;
    private Teacher teacher;
    private Map<String, Student> students = new HashMap<>();

    public ClassRoom(String classId, String name, Teacher teacher) {
        this.classId = classId;
        this.name = name;
        this.teacher = teacher;
    }

    public String getClassId() {
        return classId;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    
    public void enrollStudent(Student student) {
        students.put(student.getId(), student);
    }

  
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

   
    public void assignGrade(Student student, double grade) {
        if (!students.containsKey(student.getId())) {
            throw new IllegalArgumentException("Student is not enrolled in this class.");
        }
        student.assignGrade(classId, grade);
    }

    public Collection<Student> getStudents() {
        return students.values();
    }
}
