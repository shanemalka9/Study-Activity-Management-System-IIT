public class Student {

    // Private attributes
    private String stID;
    private String stName;
    private Module[] modules;

    public Student(String ID) {
        this.stID = ID;
        modules = new Module[3];
    }

    public Student(String ID, String name) {
        this.stID = ID;
        this.stName = name;
        modules = new Module[3];
    }

    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public Module[] getModules() {
        return modules;
    }
}
