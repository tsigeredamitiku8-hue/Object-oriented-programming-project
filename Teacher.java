package schoolmanagementsystem1;



import java.util.HashSet;
import java.util.Set;

public class Teacher extends SchoolMember  { 
  
    private String department;
    private Set<String> classesTeaching = new HashSet<>();

    public Teacher(String name, String id, String department) {
        super(name, id);
        this.department = department;
    }

   
    
    public String getRole() {
        return "Teacher";
    }

   
    public String getDepartment() {
        return department;
    }

   
    public void assignClass(String classId) {
        classesTeaching.add(classId);
    }

  
    public Set<String> getClassesTeaching() {
        return new HashSet<>(classesTeaching); 
    }
}
