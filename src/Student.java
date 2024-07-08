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

    // Getters and Setters
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

        return total/ this.modules.length;
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
}
