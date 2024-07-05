public class Student {

    // Private attributes
    private String stID;
    private String stName;
    private Module[] modules;

    public Student(String stID, String stName) {
        this.stID = stID;
        this.stName = stName;
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

}
