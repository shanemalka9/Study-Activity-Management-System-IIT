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
            System.out.println("0) Exit\n");
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
                        System.out.println("\nMenu Item Does Not Exist.\n<<Press Enter to continue>>");
                        userInput.nextLine();
                }

            } else { // If user inputs an invalid value an error message is printed
                System.out.println("\nMenu Item Does not exist! Please choose a valid item.\n<<Press Enter to continue>>");
                userInput.nextLine();// Clear userInput
                userInput.nextLine();// Pauses until user presses enter
            }

        }
    }

    /**
     * Subtracts total the student count form total number of students
     */
    private static void checkAvailableSeats() {
        System.out.println("Total Number of Available Seats are: " + (maxStudents - studentCount) + "\n<<Press Enter to continue>>");
        userInput.nextLine();
    }

    /**
     * Method used to register students
     * User has to input a student ID and, it is checked for its validity
     */
    private static void register() {
        String id;
        String name;

        while (true) {
            System.out.print("Enter a student ID(Ex: w1234567): ");
            id = userInput.next();
            userInput.nextLine();

            if (id.length() == 8) {// Check if length is 8 characters
                if (id.charAt(0) == 'w') {// Check if first letter is 'w'
                    if (checkSimilarID(id)) {// Check if ID already exists
                        break;
                    } else {
                        System.out.println("\nThis ID already exists. Please re-enter\n");
                    }
                } else {
                    System.out.println("\n'w' at the beginning is missing. Please re-enter\n");
                }
            } else {
                System.out.println("\nID length should be 8 characters. Please re-enter\n");
            }
        }
        System.out.print("Enter your First name: ");
        name = userInput.next().toUpperCase();

        students[studentCount] = new Student(id, name);// If id is valid then create a Student object and add it to the main array
        studentCount++;// Increment counter by 1
        System.out.println("New Student seat reserved\n<<Press Enter to continue>>");
        userInput.nextLine();
    }

    private static void delete() {
    }

    private static void search() {
    }

    private static void exportDetails() {
    }

    private static void importDetails() {
    }

    private static void view() {
    }

    private static void extraMenu() {
        while (true) {
            System.out.println("==============================\n*   Additional Menu Items    *\n==============================\n");
            System.out.println("a) Add Student name");
            System.out.println("b) Add Module Marks");
            System.out.println("c) Summery");
            System.out.println("d) Report");

            System.out.println("\ne. <== Back");


            System.out.print("\nEnter your choice: ");
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
                case "e":
                    System.out.println("Going to previous menu\n");
                    return;
                default:
                    System.out.println("\nMenu Item Does not exist! Please choose a valid item.\n<<Press Enter to continue>>");
                    userInput.nextLine();
                    userInput.nextLine();
            }
        }
    }

    private static void addStudentName() {
    }

    private static void addModuleMarks() {
    }

    private static void summery() {
    }

    private static void report() {
    }

    //* Extra Functions

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
     * Finds the number of seats already reserved
     */
    private static void studentCount() {
        for (Student student : students) {
            if (student != null) {
                studentCount++;
            }
        }
    }
}