package schoolmanagementsystem1;



import java.util.HashMap;
import java.util.Map;

public class Student extends SchoolMember  { 
    
    private Map<String, Double> grades = new HashMap<>();

    public Student(String name, String id) {
        super(name, id);
    }

   
    public String getRole() {
        return "Student";
    }

   
    public void assignGrade(String classId, double grade) {
        grades.put(classId, grade);
    }

  
    public Double getNumericGrade(String classId) {
        return grades.get(classId);
    }

  
    public String getLetterGrade(String classId) {
        Double grade = grades.get(classId);
        if (grade == null) return "NG"; 
        if (grade >= 90) return "A+";
        if (grade >= 85) return "A";
        if (grade >= 80) return "A-";
        if (grade >= 75) return "B+"; 
        if (grade >= 70) return "B";  
        if (grade >= 65) return "B-";
        if (grade >= 60) return "C+";
        if (grade >= 50) return "C";
        if (grade >= 45) return "C-";
        if (grade >= 40) return "D";
        return "F"; 
    }
}
