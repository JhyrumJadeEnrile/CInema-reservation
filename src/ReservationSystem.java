import java.util.Scanner;
import java.util.InputMismatchException;


public abstract class ReservationSystem {
   Scanner sc = new Scanner(System.in);
   Crud crud = new Crud();

    // Abstract method
    public abstract void start();

    // Helper methods
    public int getValidInt(String prompt) {
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

    public int getValidMonth() {
        int month;
        while (true) {
            month = getValidInt("Enter month (1-12): ");
            if (month >= 1 && month <= 12) return month;
            System.out.println("Invalid month! Must be between 1 and 12.");
        }
    }

    public int getValidDay(int month, int year) {
        int day;
        while (true) {
            day = getValidInt("Enter day: ");
            if (DateValidator.isValidDay(day, month, year)) return day;
            System.out.println("Invalid day for the given month! Try again.");
        }
    }

    public int getValidYear() {
        int year;
        while (true) {
            year = getValidInt("Enter year (must not be less than 2025): ");
            if (year >= 2025) return year;
            System.out.println("Invalid year! Year cannot be less than 2025.");
        }
    }

    public String getValidEmail() {
        String email;
        while (true) {
            System.out.print("Enter email: ");
            email = sc.nextLine().trim();
            if (email.endsWith(".com")) return email;
            System.out.println("Invalid email!");
        }
    }

    // Method to display the movie selection menu
    public String selectMovie() {
        int moviech;
        String movieTitle;
        do {
            System.out.println("Please Select a Movie:");
            System.out.println("1. Wednesday");
            System.out.println("2. Everything Everywhere All At Once");
            System.out.println("3. Pedro Penduko");
            System.out.println("4. Avengers Doom's Day");

            moviech = getValidInt("Enter your choice: ");

            switch (moviech) {
                case 1 -> movieTitle = "Wednesday";
                case 2 -> movieTitle = "Everything Everywhere All At Once";
                case 3 -> movieTitle = "Pedro Penduko";
                case 4 -> movieTitle = "Avengers Doom's Day";
                default -> {
                    System.out.println("Invalid movie choice! Please try again.");
                    continue;
                }
            }
            return movieTitle;
        } while (true);
    }
}
