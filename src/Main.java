import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        final int maxStudents = 100;
        String[] students = new String[maxStudents];// Array with space for 100 elements

        while (true) {
            int menuItem;

            System.out.println("Maim Menu");
            System.out.println("=========\n");

            System.out.println("1. Check Availability Of Seats");
            System.out.println("2. Student Registration");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Export Details TO File");
            System.out.println("6. Import Details From File");
            System.out.println("7. View Student List");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");

            // Checks if user has inputted an integer
            if (userInput.hasNextInt()) {
                menuItem = userInput.nextInt();
                userInput.nextLine();

                // Checks if the user has selected a menu item between 1 and 8
                if (menuItem > 8 || menuItem < 1) {
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
                            System.out.println("Exiting Program");
                            return;
                    }
                }
            } else { // If user inputs an invalid value an error message is printed
                System.out.println("\nMenu Item Does not exist! Please choose a valid item.\n<<Press Enter to continue>>");
                userInput.nextLine();
                userInput.nextLine();
            }

        }
    }

    private static void checkAvailableSeats() {
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
}