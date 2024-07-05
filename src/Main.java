import java.util.*;


public class Main {

    private static int studentCount = 0;
    private static final int maxStudents = 100;
    private static Student[] students = new Student[maxStudents];// Array with space for 100 elements
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            int menuItem;

            studentCount();

            System.out.println("=========\nMain Menu\n=========\n");

            System.out.println("1. Check Availability Of Seats");
            System.out.println("2. Student Registration");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Export Details TO File");
            System.out.println("6. Import Details From File");
            System.out.println("7. View Student List");
            System.out.println("8. Additional Options");
            System.out.println("9. Exit\n");

            System.out.print("Enter your choice: ");

            // Checks if user has inputted an integer
            if (userInput.hasNextInt()) {
                menuItem = userInput.nextInt();
                userInput.nextLine();

                // Checks if the user has selected a menu item between 1 and 8
                if (menuItem > 9 || menuItem < 1) {
                    System.out.println("\nMenu Item Does Not Exist.\n<<Press Enter to continue>>");
                    userInput.nextLine();
                } else {
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
                        case 9:
                            System.out.println("Exiting....");
                            return;
                    }
                }
            } else { // If user inputs an invalid value an error message is printed
                System.out.println("\nMenu Item Does not exist! Please choose a valid item.\n<<Press Enter to continue>>");
                userInput.nextLine();// Clear userInput
                userInput.nextLine();// Pauses until user presses enter
            }

        }
    }

    /**
     * Finds the number of seats already reserved
     */
    private static void studentCount() {
        for (Student student :students) {
            if (student != null){
               studentCount++;
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

    private static void register() {

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
            System.out.println("a. Add Student name");
            System.out.println("b. Add Module Marks");
            System.out.println("c. Summery");
            System.out.println("d. Report");

            System.out.println("\ne. <== Back");


            System.out.print("\nEnter your choice: ");
            String menuItem = userInput.next();
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
}