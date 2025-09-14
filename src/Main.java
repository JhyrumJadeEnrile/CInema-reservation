import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

 
    public static int getValidInt(Scanner sc, String prompt) {
        int value;
        while (true) {
            try {
                System.out.print(prompt);
                value = sc.nextInt();
                sc.nextLine(); 
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine(); 
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Crud crud = new Crud();
        int choice = 0;

        do {
            System.out.println("\n===== MOVIE THEATER RESERVATION SYSTEM =====");
            System.out.println("1. Add Reservation");
            System.out.println("2. View Reservations");
            System.out.println("3. Update Reservation");
            System.out.println("4. Delete Reservation");
            System.out.println("5. Exit");

            choice = getValidInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    int seat = getValidInt(sc, "Enter seat number: ");
                    System.out.println("The date format should be like this: 01/02/2000");
                    int day = getValidInt(sc, "Enter day: ");
                    int month = getValidInt(sc, "Enter month: ");
                    int year = getValidInt(sc, "Enter year: ");
                    reservationDetails r = new reservationDetails(name, email, seat, day, month, year);
                    crud.addReservation(r);
                    break;

                case 2:
                    crud.viewReservations();
                    break;

                case 3:
                    crud.viewReservations();
                    int indexToUpdate = getValidInt(sc, "Enter reservation number to update: ") - 1;
                    int newSeat = getValidInt(sc, "Enter new seat number: ");
                    int newDay = getValidInt(sc, "Enter new day: ");
                    int newMonth = getValidInt(sc, "Enter new month: ");
                    int newYear = getValidInt(sc, "Enter new year: ");
                    crud.updateReservation(indexToUpdate, newSeat, newDay, newMonth, newYear);
                    break;

                case 4:
                    crud.viewReservations();
                    int indexToDelete = getValidInt(sc, "Enter reservation number to delete: ") - 1;

                    if (indexToDelete >= 0 && indexToDelete < crud.reservations.size()) {
                        System.out.print("Are you sure you want to delete this reservation? (yes/no): ");
                        String confirm = sc.nextLine();
                        if (confirm.equalsIgnoreCase("yes")) {
                            crud.deleteReservation(indexToDelete);
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                    } else {
                        System.out.println("Invalid reservation number.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}