package schoolmanagementsystem1;


abstract class SchoolMember  {
    
    private String name;
    private String id;
    
   
    public SchoolMember(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public abstract String getRole();

   
    public String getName() {
        return name;
    }

   
    public String getId() {
        return id;
    }
}
