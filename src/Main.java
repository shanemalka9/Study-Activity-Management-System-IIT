import java.util.*;


public class Main {

    private static int studentCount = 0;
    private static final int maxStudents = 100;
    private static Student[] students = new Student[maxStudents];// Array with space for 100 elements
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        studentCount();

        while (true) {
            int menuItem;

            System.out.println("==============================\n*         Main Menu          *\n==============================\n");
            System.out.println("1) Check Availability Of Seats");
            System.out.println("2) Student Registration");
            System.out.println("3) Delete Student");
            System.out.println("4) Search Student");
            System.out.println("5) Export Details TO File");
            System.out.println("6) Import Details From File");
            System.out.println("7) View Student List");
            System.out.println("8) Additional Options");
            System.out.println("0) Exit");
            System.out.print("Enter your choice: ");
            // Checks if user has inputted an integer
            if (userInput.hasNextInt()) {
                menuItem = userInput.nextInt();
                userInput.nextLine();
                // Checks if the user has selected a menu item between 1 and 8
                switch (menuItem) {
                    case 1:
                        checkAvailableSeats();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        delete();
                        break;
                    case 4:
                        search();
                        break;
                    case 5:
                        exportDetails();
                        break;
                    case 6:
                        importDetails();
                        break;
                    case 7:
                        view();
                        break;
                    case 8:
                        extraMenu();
                        break;
                    case 0:
                        System.out.println("Exiting....");
                        return;
                    default:
                        System.out.println("\nMenu Item Does Not Exist.\n>>Press Enter to continue<<");
                        userInput.nextLine();
                }

            } else { // If user inputs an invalid value an error message is printed
                System.out.println("\nMenu Item Does not exist! Please choose a valid item.\n>>Press Enter to continue<<");
                userInput.nextLine();// Clear userInput
                userInput.nextLine();// Pauses until user presses enter
            }
        }
    }

    /**
     * Subtracts total the student count form total number of students
     */
    private static void checkAvailableSeats() {
        System.out.println("\nTotal Number of Available Seats are: " + (maxStudents - studentCount) + "\n>>Press Enter to continue<<");
        userInput.nextLine();
    }

    /**
     * Method used to register students
     * User has to input a student ID and, it is checked for its validity
     */
    private static void register() {
        String id;

        do {
            System.out.print("\nEnter a student ID(Ex: w1234567): ");
            id = userInput.next();
            userInput.nextLine();
        } while (idValidation(id, true));

        System.out.print("Enter your name: ");
        String name = userInput.nextLine().toUpperCase();
        students[studentCount] = new Student(id, name);// If id is valid then create a Student object and add it to the main array

        studentCount++;// Increment counter by 1
        System.out.println("*** New Student seat reserved ***\n>>Press Enter to continue<<");
        userInput.nextLine();
    }

    /**
     * This function is used to delete entries in the array.
     */
    private static void delete() {
        String id;

        do {
            System.out.print("Enter Student ID of entry you want to delete: ");
            id = userInput.next();
        } while (idValidation(id, false));


        for (int i = 0; i < studentCount; i++) {// Loop through all elements in array
            if (students[i].getStID().equals(id)) {// Check until Student ID is equal to the user input
                for (int j = i; j < studentCount - 1; j++) {
                    students[j] = students[j + 1];// Shift all elements 1 step to the left
                }
                students[studentCount - 1] = null; // Set the last element to null after shifting
                break; // Exit the loop once the element is deleted
            }
        }
        System.out.println("Student entry deleted successfully.\n>>Press Enter to continue<<");
        studentCount--;
        userInput.nextLine();
    }

    /**
     * Method used to search for a student using Student ID
     */
    private static void search() {
        String id;

        do {
            System.out.print("\nEnter student ID to search: ");
            id = userInput.next();
            userInput.nextLine();
        } while (idValidation(id, false));

        for (Student student : students) {
            if (student == null) {
                break;
            }
            if (student.getStID().equals(id)) {

                System.out.println(">> Search results <<");
                System.out.println("Student ID: " + student.getStID());
                System.out.println("Student Name: " + student.getStName());
                System.out.println("Student marks: ");

                for (int i = 0; i < student.getModules().length; i++) {
                    System.out.print("Module " + (i + 1) + " : ");
                    if (student.getModules()[i] == null) {
                        System.out.println("Marks Unavailable");
                    } else {
                        System.out.println(student.getModules()[i].getModuleMarks());
                    }
                }
            }
        }
        System.out.println("\n>>Press Enter to continue<<");
        userInput.nextLine();
    }

    private static void exportDetails() {
    }

    private static void importDetails() {
    }

    /**
     * This function views all students names and ID's by sorting them in ascending order by name
     */
    private static void view() {
        Student[] studentCopy = students.clone();// Cloned array as to not change original array
        // Bubble sort for view functions
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - 1 - i; j++) {
                if (studentCopy[j].getStName().compareTo(studentCopy[j + 1].getStName()) > 0) {
                    Student temp = studentCopy[j];
                    studentCopy[j] = studentCopy[j + 1];
                    studentCopy[j + 1] = temp;
                }
            }
        }
        // Print out sorted array
        System.out.println("\n          *** Student List ***");
        for (int i = 0; i < studentCount; i++) {
            System.out.println("----------------------------------------");
            System.out.println("Student " + (i + 1));
            System.out.println("==>Student ID: " + studentCopy[i].getStID());
            System.out.println("==>Student Name: " + studentCopy[i].getStName());
        }
        System.out.println("----------------------------------------\n>>Press Enter to continue<<");
        userInput.nextLine();
    }

    //* Task 2

    /**
     * Extra Menu Items in task 2
     */
    private static void extraMenu() {
        while (true) {
            System.out.println("\n==============================\n*   Additional Menu Items    *\n==============================\n");
            System.out.println("a) Add Student name");
            System.out.println("b) Add Module Marks");
            System.out.println("c) Summery");
            System.out.println("d) Report");
            System.out.println("q) <== Back");


            System.out.print("Enter your choice: ");
            String menuItem = userInput.next().toLowerCase();
            switch (menuItem) {
                case "a":
                    addStudentName();
                    break;
                case "b":
                    addModuleMarks();
                    break;
                case "c":
                    summery();
                    break;
                case "d":
                    report();
                    break;
                case "q":
                    System.out.println("\nGoing to previous menu...\n");
                    return;
                default:
                    System.out.println("\nMenu Item Does not exist! Please choose a valid item.\n>>Press Enter to continue<<");
                    userInput.nextLine();
                    userInput.nextLine();
            }
        }
    }

    /**
     * Function used to add name to a student after registering
     * If student name has already been assigned the program asks if the user wants to update the name
     */
    private static void addStudentName() {
        String id;
        String name;

        System.out.println("\n");
        // Loop until Correct ID is provided
        do {
            System.out.print("Enter student ID to add name: ");
            id = userInput.next();
            userInput.nextLine();
        } while (idValidation(id, false));
        // Loop to iterate through the students
        for (Student student : students) {
            // If student array element is null then break the loop
            if (student == null) {
                break;
            }
            if (student.getStID().equals(id)) {// Checks for the similar id
                System.out.print("\nUpdate student name: ");
                name = userInput.nextLine().toUpperCase();
                student.setStName(name);
                System.out.println("\n*** Student name updated successfully ***\n>>Press Enter to continue<<");
                userInput.nextLine();
            }
        }
    }

    /**
     * Method adds Module marks to each student
     */
    private static void addModuleMarks() {
        String id;
        System.out.println("\n");
        do {
            System.out.print("Enter student ID to add module marks: ");
            id = userInput.next();
            userInput.nextLine();
        } while (idValidation(id, false));

        // Loop to iterate through the students
        for (Student student : students) {
            // If student array element is null then break the loop
            if (student == null) {
                break;
            }
            // IF ID matches then update module marks
            if (student.getStID().equals(id)) {// Checks for the similar id
                for (int i = 0; i < student.getModules().length; i++) {
                    while (true) {
                        try {
                            System.out.print("Enter marks of module " + (i + 1) + ": ");
                            double mark = userInput.nextDouble();
                            userInput.nextLine();

                            if (mark < 0 || mark > 100) {
                                System.out.println("Invalid Marks");
                                continue;
                            }

                            student.getModules()[i] = new Module(mark);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Enter a Number");
                            userInput.nextLine();
                        }
                    }
                }
                System.out.println("\n*** Module marks have been updated ***\n>>Press Enter to continue<<");
                userInput.nextLine();
                break;
            }
        }
    }

    private static void summery() {
    }

    private static void report() {
    }

    //* Extra Functions

    /**
     * Method used to check the validity of any given ID
     *
     * @param id-this   is the provided ID
     * @param register- This is to differentiate between the 'register' and ' delete' methods
     * @return boolean value depending on the method calling it
     */
    private static boolean idValidation(String id, boolean register) {
        if (id.length() == 8) {// Check if length is 8 characters
            if (id.charAt(0) == 'w') {// Check if first letter is 'w'
                if (checkSimilarID(id)) {// Check if ID already exists
                    if (register) {
                        return false;
                    } else {
                        System.out.println("\nThis ID does not exist. Please re-enter\n");
                        return true;
                    }
                } else {
                    if (register) {
                        System.out.println("\nThis ID already exists. Please re-enter\n");
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                System.out.println("\n'w' at the beginning is missing. Please re-enter\n");
                return true;
            }
        } else {
            System.out.println("\nID length should be 8 characters. Please re-enter\n");
            return true;
        }
    }

    /**
     * A function specifically used to see if the ID that the user has input is unique and is not already in use
     *
     * @param ID- User input ID
     * @return boolean value if 'find' is true or false
     */
    private static boolean checkSimilarID(String ID) {
        boolean find = false;
        for (Student student : students) {
            if (student == null) {
                break;
            } else {
                if (student.getStID().equals(ID)) {
                    find = true;
                }
            }
        }
        return !find;
    }

    /**
     * Finds the number of seats already reserved by counting till null
     */
    private static void studentCount() {
        for (Student student : students) {
            if (student != null) {
                studentCount++;
            }
        }
    }
}