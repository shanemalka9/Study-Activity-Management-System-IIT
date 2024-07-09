import java.util.*;
import java.io.*;

//********* Task 1 *********

public class Main {

    private static int studentCount = 0;
    private static final int maxStudents = 100;
    private static Student[] students = new Student[maxStudents];// Array with space for 100 elements
    private static Scanner userInput = new Scanner(System.in);
    private static String fileName = "student.txt";

    public static void main(String[] args) {
        importDetails(true);

        while (true) {
            System.out.println("==============================");
            System.out.println("*         Main Menu          *");
            System.out.println("==============================");
            System.out.println("Note: Typing in 'q' while inputting student ID and submitting will quit the procedure.");
            System.out.println("------------------------------\n");
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
            try {
                int menuItem = userInput.nextInt();
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
                        importDetails(false);
                        break;
                    case 7:
                        view();
                        break;
                    case 8:
                        extraMenu();
                        break;
                    case 0:
                        exportDetails();
                        userInput.close();
                        System.out.println("Exiting....");
                        return;
                    default:
                        System.out.println("\nMenu Item Does Not Exist.\n>>Press Enter to continue<<");
                        userInput.nextLine();
                }
            } catch (InputMismatchException e) { // If user inputs an invalid value an error message is printed
                System.out.println("\nMenu Item Does not exist! Please choose a valid item.\n>>Press Enter to continue<<");
                userInput.nextLine();// Clear userInput
                userInput.nextLine();// Pauses until user presses enter
            }
        }
    }


    /**
     * Subtracts total the student count form total number of seats to get remaining number of seats
     */
    private static void checkAvailableSeats() {
        System.out.println("\nTotal Number of Available Seats are: " + (maxStudents - studentCount) + "\n\n>>Press Enter to continue<<");
        userInput.nextLine();
    }


    /**
     * Method used to register students
     * User has to input a student ID and, it is checked for its validity
     */
    private static void register() {
        String id;
        System.out.println("\n");

        // Get ID and verify using idValidation() Method in register mode
        do {
            System.out.print("Enter a student ID(Ex: w1234567): ");
            id = userInput.next();
            userInput.nextLine();
            // if user enters "q" then it exits the method
            if (id.equals("q")) {
                return;
            }
        } while (idValidation(id, true));

        String name = nameEmptyCheck();

        students[studentCount] = new Student(id, name);// If id is valid then create a Student object and add it to the main array

        studentCount++;// Increment counter by 1
        System.out.println("\n*** New Student seat reserved ***\n>>Press Enter to continue<<");
        userInput.nextLine();
    }


    /**
     * This function is used to delete entries in the array.
     */
    private static void delete() {
        // Check if there are no students
        if (studentCount == 0) {
            System.out.println("\nNo Students registered.");
        } else {
            String id;
            // Prompts the user a question asking if they want to view the list of students to find the student ID needed to be deleted
            askView();

            // ID validation using idValidation() method in normal mode
            do {
                System.out.print("Enter Student ID of entry you want to delete: ");
                id = userInput.next();
                userInput.nextLine();
                if (id.equals("q")) {
                    return;
                }
            } while (idValidation(id, false));

            // Shift element to the left by one replacing the element wanted to be deleted
            for (int i = 0; i < studentCount; i++) {// Loop through all elements in array
                if (students[i].getStID().equals(id)) {// Check until Student ID is equal to the user input
                    for (int j = i; j < studentCount - 1; j++) {
                        students[j] = students[j + 1];// Shift all elements 1 step to the left
                    }
                    students[studentCount - 1] = null; // Set the last element to null after shifting
                    break; // Exit the loop once the element is deleted
                }
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
        // Check if there are no students
        if (studentCount == 0) {
            System.out.println("\nNo Students registered.");
        } else {
            String id;
            do {
                System.out.print("\nEnter student ID to search: ");
                id = userInput.next();
                userInput.nextLine();
                if (id.equals("q")) {
                    return;
                }
            } while (idValidation(id, false));

            // Iterate through the students array
            for (Student student : students) {
                // Until Student becomes null
                if (student == null) {
                    break;// Break loop if student is null.
                }
                // True if user input ID is equal to an ID in an object in students array
                if (student.getStID().equals(id)) {

                    System.out.println(">> Search results <<");
                    System.out.println("Student ID: " + student.getStID());
                    System.out.println("Student Name: " + student.getStName());
                    System.out.println("Student marks");

                    // Loop to check if marks ara available or not and print them
                    for (int i = 0; i < student.getModules().length; i++) {
                        System.out.print("==> Module " + (i + 1) + " : ");
                        if (student.getModules()[i] == null) {
                            System.out.println("Marks Unavailable");
                        } else {
                            System.out.println(student.getModules()[i].getModuleMarks());
                        }
                    }
                }
            }
        }
        System.out.println("\n>>Press Enter to continue<<");
        userInput.nextLine();
    }


    /**
     * Method used to export Student details to 'student.txt'
     */
    private static void exportDetails() {
        boolean flag = false;

        // Try-with-resource statement to write to file
        try (FileWriter fileWrite = new FileWriter(fileName)) {
            // Iterate through array until student becomes null
            for (Student student : students) {
                // Break from loop when student is equal to null
                if (student == null) {
                    fileWrite.close();
                    break;
                }
                // Check if Module array has null entries
                for (int i = 0; i < student.getModules().length; i++) {
                    if (student.getModules()[i] == null) {
                        flag = true;
                    }
                }
                // If a null value is found in module then write only the ID and name
                if (flag) {
                    fileWrite.write(student.getInfo() + "\n");
                    continue;
                }

                // If module is not null then write all
                fileWrite.write(student.getAllInfo() + "\n");
            }
            System.out.println("All data has successfully been saved to the file.");
            System.out.println("\n>>Press Enter to continue<<");
            userInput.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method used to import data from 'student.txt'
     * @param begin- used to check if the function is called at the beginning or later
     *             because if it's called later we need to check if there are similar student IDs, so we don't get repeating values
     */
    private static void importDetails(boolean begin) {
        try {
            File file = new File(fileName);

            // Check if file exists
            if (!file.exists()) {
                exportDetails();// If not export method is called thus creating a file
                System.out.println("File does not exist! New file created.");
                System.out.println(">>Press Enter to continue<<");
                userInput.nextLine();
                return;
            }
            // Check if file has data
            if (file.length() == 0) {
                System.out.println("File is empty!");
                System.out.println(">>Press Enter to continue<<");
                userInput.nextLine();
                return;
            }
            Scanner readFile = new Scanner(file);

            // If file has next line
            while (readFile.hasNextLine()) {
                String ID = readFile.next();// Read ID
                // If called mid-program then check if ID is repeated
                if (!begin) {
                    boolean flag = false;
                    for (int i = 0; i < studentCount; i++) {
                        if (students[i].getStID().equals(ID)) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        continue;// If id is repeated then skip that iteration
                    }
                }
                String name = readFile.next();
                // Check if file has marks
                if (readFile.hasNextDouble()) {// If it does then assign to variables and create object with it
                    double mark1 = readFile.nextDouble();
                    double mark2 = readFile.nextDouble();
                    double mark3 = readFile.nextDouble();
                    students[studentCount] = new Student(ID, name, mark1, mark2, mark3);
                    studentCount++;
                } else { // else call create object with no marks
                    students[studentCount] = new Student(ID, name);
                    studentCount++;
                }
            }
            readFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            return;
        } catch (Exception e) {
            System.out.println(">> " + e.getMessage());
        }
    }


    /**
     * This function views all students names and ID's by sorting them in ascending order by name
     */
    private static void view() {
        Student[] studentCopy = students.clone();// Cloned array as to not change original array

        // CHeck if there are any students
        if (studentCopy[0] == null) {
            System.out.println("\nNo students have registered. Going back.\n>>Press Enter to continue<<");
            userInput.nextLine();
            return;
        }

        // Bubble sort for cloned array to view
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
        System.out.println("----------------------------------------\n\n>>Press Enter to continue<<");
        userInput.nextLine();
    }


    //********** Task 2 **********


    /**
     * Extra Menu Items in task 2
     */
    private static void extraMenu() {
        while (true) {
            System.out.println("\n==============================");
            System.out.println("*   Additional Menu Items    *");
            System.out.println("==============================\n");
            System.out.println("1) Add Student name");
            System.out.println("2) Add Module Marks");
            System.out.println("3) Summery");
            System.out.println("4) Report");
            System.out.println("0) <== Back");
            System.out.print("Enter your choice: ");

            try {
                int menuItem = userInput.nextInt();
                userInput.nextLine();
                switch (menuItem) {
                    case 1:
                        addStudentName();
                        break;
                    case 2:
                        addModuleMarks();
                        break;
                    case 3:
                        summery();
                        break;
                    case 4:
                        report();
                        break;
                    case 0:
                        System.out.println("\nGoing to previous menu...\n");
                        return;
                    default:
                        System.out.println("\nMenu Item Does not exist! Please choose a valid item.\n>>Press Enter to continue<<");
                        userInput.nextLine();
                }
            } catch (InputMismatchException e) { // If user inputs an invalid value an error message is printed
                System.out.println("\nMenu Item Does not exist! Please choose a valid item.\n>>Press Enter to continue<<");
                userInput.nextLine();// Clear userInput
                userInput.nextLine();// Pauses until user presses enter
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

        askView();

        // Loop until Correct ID is provided
        do {
            System.out.print("Enter student ID to add name: ");
            id = userInput.next();
            userInput.nextLine();
            if (id.equals("q")) {
                return;
            }
        } while (idValidation(id, false));
        // Loop to iterate through the students
        for (Student student : students) {
            // If student array element is null then break the loop
            if (student == null) {
                break;
            }
            if (student.getStID().equals(id)) {// Checks for the similar id
                name = nameEmptyCheck();
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

        askView();

        do {
            System.out.print("Enter student ID to add module marks: ");
            id = userInput.next();
            userInput.nextLine();
            if (id.equals("q")) {
                return;
            }
        } while (idValidation(id, false));

        System.out.println("\n");

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
                            // Check mark validity
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


    /**
     * Gives a summery if the batch of students
     * Totals number of students registered
     * Total number of students who has got Marks >= 40 in each Module
     */
    private static void summery() {
        int count = 0;
        System.out.println("\n      *** Summery ***\n-------------------------");
        // Check if student count is 0
        if (studentCount == 0) {
            System.out.println("\nNo Students registered.");
        } else {
            System.out.println("The total number of students registered is: " + studentCount);
            for (Student student : students) {
                if (student == null) {
                    break;
                }
                if (student.getModules()[0] == null && student.getModules()[1] == null && student.getModules()[2] == null) {
                    continue;
                }
                if (student.getModules()[0].getModuleMarks() >= 40 && student.getModules()[1].getModuleMarks() >= 40 && student.getModules()[2].getModuleMarks() >= 40) {
                    count++;
                }
            }
            System.out.println("Total number of students with marks for each module above 40: " + count);
        }
        System.out.println("\n>>Press Enter to continue<<");
        userInput.nextLine();
    }

    /**
     * Method to print detailed report of all students
     */
    private static void report() {
        Student[] studentCopy = students.clone();// Cloned array as to not change original array
        boolean flag = true;

        if (studentCopy[0] == null) {
            System.out.println("\nNo students have registered. Going back.\n>>Press Enter to continue<<");
            userInput.nextLine();
            return;
        }

        // Bubble sort according to average for cloned array to view in report in descending order
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - 1 - i; j++) {
                if (studentCopy[j].getModuleAverage(studentCopy[j].getModuleTotal()) < studentCopy[j + 1].getModuleAverage(studentCopy[j + 1].getModuleTotal())) {
                    Student temp = studentCopy[j];
                    studentCopy[j] = studentCopy[j + 1];
                    studentCopy[j + 1] = temp;
                }
            }
        }
        System.out.println("\n-------------------------\n *** Complete Report ***\n-------------------------");

        for (int i = 0; i < studentCount; i++) {
            double total = studentCopy[i].getModuleTotal();
            double average = studentCopy[i].getModuleAverage(total);
            String grade = studentCopy[i].getModuleGrade(average);

            System.out.println("----------------------------------------");
            System.out.println(" Student " + (i + 1));
            System.out.println("-------------");
            System.out.println(" Student ID: " + studentCopy[i].getStID());
            System.out.println(" Student Name: " + studentCopy[i].getStName());
            // For loop to iterate through each module and get marks
            for (int j = 0; j < studentCopy[i].getModules().length; j++) {
                System.out.print("====> Module " + (j + 1) + " marks: ");
                // If module is null execute
                if (studentCopy[i].getModules()[j] == null) {
                    System.out.println("Marks Unavailable");
                    flag = false;
                } else {// else print marks
                    System.out.println(studentCopy[i].getModules()[j].getModuleMarks());
                }
            }
            // if marks are available then print the total average and grade
            if (flag) {
                System.out.println("==> Total Marks: " + total);
                System.out.println("==> Average Marks: " + average);
                System.out.println("==> Module Grade: " + grade);
            }
        }
        System.out.println("----------------------------------------\n\n>>Press Enter to continue<<");
        userInput.nextLine();
    }


    //********** Extra Functions **********


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
                try {// Try statement to catch error if characters ID after 'w' are all digits and not letters
                    Integer.parseInt(id.substring(1));
                    if (checkSimilarID(id)) {// Check if ID already exists
                        if (register) {// If called from register method this is executed
                            return false;
                        } else {
                            System.out.println("\nThis ID does not exist. Please try again!\n");
                            return true;
                        }
                    } else {
                        if (register) {// If called from register method this is executed
                            System.out.println("\nThis ID already exists. Please try again!\n");
                            return true;
                        } else {
                            return false;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nInvalid ID. Please try again!\n");
                    return true;
                }
            } else {
                System.out.println("\n'w' at the beginning is missing. Please try again!\n");
                return true;
            }
        } else {
            System.out.println("\nID length should be 8 characters. Please try again!\n");
            return true;
        }
    }


    /**
     * Gets Name and checks if it is empty
     *
     * @return verified name
     */
    private static String nameEmptyCheck() {
        while (true) {
            System.out.print("Enter your name: ");
            String name = userInput.nextLine().toUpperCase();
            if (name.isEmpty()) {
                System.out.println("\nNo name added!\n");
                continue;
            }
            return name;
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
     * Ask user if he wants to view all students and if he does then call the view Method
     */
    private static void askView() {
        while (true) {
            System.out.print("Would you like to view student list? (y/n) ");
            String choice = userInput.next().toLowerCase();
            userInput.nextLine();
            if (choice.equals("y") || choice.equals("yes")) {
                view();
                break;
            } else if (choice.equals("n") || choice.equals("no")) {
                break;
            } else {
                System.out.println("\nInvalid response. Enter (y / yes) or (n / no)\n");
            }
        }
    }
}