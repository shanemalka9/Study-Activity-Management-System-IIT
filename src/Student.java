public class Student {

    // Private attributes
    private String stID;
    private String stName;
    private Module[] modules;
    private String moduleGrade;

    /**
     * Constructor method for student
     * @param ID- Student ID
     * @param name- Student Name
     */
    public Student(String ID, String name) {
        this.stID = ID;
        this.stName = name;
        this.modules = new Module[3];
    }

    /**
     * Constructor for Student with marks included
     * Used in importing details from the file
     * @param ID- student id
     * @param name- student name
     * @param mark1- module 1 marks
     * @param mark2- module 2 marks
     * @param mark3- module 3 marks
     */
    public Student(String ID, String name, double mark1, double mark2, double mark3) {
        this.stID = ID;
        this.stName = name;
        this.modules = new Module[3];
        this.modules[0] = new Module(mark1);
        this.modules[1] = new Module(mark2);
        this.modules[2] = new Module(mark3);
    }

    // Getters and Setters
    public String getStID() {
        return stID;
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


    /**
     * Method to get total marks
     * @return total marks
     */
    public double getModuleTotal() {
        double total = 0;

        for (Module module: getModules()) {
            if (module == null) {
                continue;
            }
            total += module.getModuleMarks();
        }
        return total;
    }

    /**
     * Method to get average marks
     * @param total- total marks
     * @return average- average marks
     */
    public double getModuleAverage(double total) {
        String fAvg = String.format("%.2f", total/ this.modules.length);
        return  Double.parseDouble(fAvg);
    }

    /**
     * Method to Grade Average Module marks
     * @param average- average module marks
     * @return String value depending on the marks
     */
    public String getModuleGrade(double average) {
        if (average >= 80) {
            return "Distinction";
        } else if (average >= 70) {
            return "Merit";
        } else if (average >= 40) {
            return "Pass";
        } else {
            return "fail";
        }
    }

    /**
     * Method to get all information in a specific format
     * @return student ID, Student name, mark 1, mark 2, mark 3.
     */
    public String getAllInfo() {
        return String.format("%s,%s,%.2f,%.2f,%.2f", stID, stName, modules[0].getModuleMarks(), modules[1].getModuleMarks(), modules[2].getModuleMarks());
    }

    /**
     * Method to get ID and name in a specific format
     * @return student ID, Student name.
     */
    public String getBasicInfo(){
        return String.format("%s,%s", stID, stName);
    }
}