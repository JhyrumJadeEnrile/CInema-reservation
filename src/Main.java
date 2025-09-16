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

    public static int getValidMonth(Scanner sc) {
        int month;
        while (true) {
            month = getValidInt(sc, "Enter month (1-12): ");
            if (month >= 1 && month <= 12) return month;
            System.out.println("Invalid month! Must be between 1 and 12.");
        }
    }

    public static int getValidDay(Scanner sc, int month, int year) {
        int day;
        while (true) {
            day = getValidInt(sc, "Enter day: ");
            if (DateValidator.isValidDay(day, month, year)) return day;
            System.out.println("Invalid day for the given month! Try again.");
        }
    }

    public static int getValidYear(Scanner sc) {
        int year;
        while (true) {
            year = getValidInt(sc, "Enter year (must not be less than 2025): ");
            if (year >= 2025) return year;
            System.out.println("Invalid year! Year cannot be less than 2025.");
        }
    }

    public static String getValidEmail(Scanner sc) {
        String email;
        while (true) {
            System.out.print("Enter email: ");
            email = sc.nextLine().trim();
            if (email.endsWith(".com")) return email;
            System.out.println("Invalid email!");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Crud crud = new Crud();
        int choice;
        int moviech;

        do {
            System.out.println("Please Select a Movie");
            System.out.println("1. Wednesday");
            System.out.println("2. Everything Everywhere All At One");
            System.out.println("3. Pedro Penduko");
            System.out.println("4. Avengers Doom's Day");

            moviech = getValidInt(sc, "Enter your choice: ");

            String movieTitle = "";
            switch (moviech) {
                case 1:
                    movieTitle = "Wednesday";
                    break;
                case 2:
                    movieTitle = "Everything Everywhere All At Once";
                    break;
                case 3:
                    movieTitle = "Pedro Penduko";
                    break;
                case 4:
                    movieTitle = "Avengers Doom's Day";
                    break;
                default:
                    System.out.println("Invalid movie choice! Please try again.");
                    continue;
            }

            do {
                System.out.println("\n===== MOVIE THEATER RESERVATION SYSTEM (" + movieTitle + ") =====");
                System.out.println("1. Add Reservation");
                System.out.println("2. View Reservations");
                System.out.println("3. Update Reservation");
                System.out.println("4. Delete Reservation");
                System.out.println("5. Change Movie");
                System.out.println("6. Exit");

                choice = getValidInt(sc, "Enter your choice: ");

                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        String email = getValidEmail(sc);
                        System.out.println("The date format should be like this: MM/DD/YYYY");

                        int month = getValidMonth(sc);
                        int day = getValidDay(sc, month, 2025);
                        int year = getValidYear(sc);
                        while (!DateValidator.isValidDay(day, month, year)) {
                            System.out.println("Invalid day for the given month/year! Try again.");
                            day = getValidDay(sc, month, year);
                        }

                        int seat;
                        do {
                            seat = getValidInt(sc, "Enter seat number: ");
                            if (crud.isSeatTaken(movieTitle, seat, day, month, year)) {
                                System.out.println("This seat is already taken for the selected movie and date. Please choose another one.");
                            } else {
                                break;
                            }
                        } while (true);

                        reservationDetails r = new reservationDetails(name, email, seat, day, month, year, movieTitle);
                        crud.addReservation(r);
                        break;

                    case 2:
                        crud.viewReservations(movieTitle);
                        break;

                    case 3:
                        crud.viewReservations(movieTitle);
                        int indexToUpdate = getValidInt(sc, "Enter reservation number to update: ") - 1;
                        if (indexToUpdate >= 0 && indexToUpdate < crud.getReservationsForMovie(movieTitle).size()) {
                            int newSeat = getValidInt(sc, "Enter new seat number: ");
                            int newMonth = getValidMonth(sc);
                            int newDay = getValidDay(sc, newMonth, 2025);
                            int newYear = getValidYear(sc);
                            while (!DateValidator.isValidDay(newDay, newMonth, newYear)) {
                                System.out.println("Invalid day for the given month/year! Try again.");
                                newDay = getValidDay(sc, newMonth, newYear);
                            }
                            crud.updateReservation(indexToUpdate, newSeat, newDay, newMonth, newYear, movieTitle);
                        } else {
                            System.out.println("Invalid reservation number.");
                        }
                        break;

                    case 4:
                        crud.viewReservations(movieTitle);
                        int indexToDelete = getValidInt(sc, "Enter reservation number to delete: ") - 1;
                        if (indexToDelete >= 0 && indexToDelete < crud.getReservationsForMovie(movieTitle).size()) {
                            System.out.print("Are you sure you want to delete this reservation? (yes/no): ");
                            String confirm = sc.nextLine();
                            if (confirm.equalsIgnoreCase("yes")) {
                                crud.deleteReservation(indexToDelete, movieTitle);
                            } else {
                                System.out.println("Deletion cancelled.");
                            }
                        } else {
                            System.out.println("Invalid reservation number.");
                        }
                        break;
                    case 5:
                        System.out.println("Changing movie...");
                        break;
                    case 6:
                        System.out.println("Exiting... Thank you!");
                        sc.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != 5 && choice != 6);
        } while (true);
    }
}