public class Student {

    // Private attributes
    private String stID;
    private String stName;
    private Module[] modules;
    private String moduleGrade;

    public Student(String ID, String name) {
        this.stID = ID;
        this.stName = name;
        this.modules = new Module[3];
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

    public double getMarks(){
        for (int i = 0; i < modules.length; i++) {
            if (this.modules[i] != null){
                System.out.println();
                //TODO: FFinish this function
            }
        }
    }
}
