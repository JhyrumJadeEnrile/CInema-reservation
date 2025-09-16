class MyReservationSystem extends ReservationSystem {

    @Override
    public void start() {
        int choice;
        String movieTitle;

        do {
            movieTitle = selectMovie();

            do {
                System.out.println("\n===== MOVIE THEATER RESERVATION SYSTEM (" + movieTitle + ") =====");
                System.out.println("1. Add Reservation");
                System.out.println("2. View Reservations");
                System.out.println("3. Update Reservation");
                System.out.println("4. Delete Reservation");
                System.out.println("5. Change Movie");
                System.out.println("6. Exit");

                choice = getValidInt("Enter your choice: ");

                switch (choice) {
                    case 1 -> addReservation(movieTitle);
                    case 2 -> crud.viewReservations(movieTitle);
                    case 3 -> updateReservation(movieTitle);
                    case 4 -> deleteReservation(movieTitle);
                    case 5 -> System.out.println("Changing movie...");
                    case 6 -> {
                        System.out.println("Exiting... Thank you!");
                        sc.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != 5);

        } while (true);
    }

    private void addReservation(String movieTitle) {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        String email = getValidEmail();

        int month = getValidMonth();
        int day = getValidDay(month, 2025);
        int year = getValidYear();

        int seat;
        do {
            seat = getValidInt("Enter seat number: ");
            if (crud.isSeatTaken(movieTitle, seat, day, month, year)) {
                System.out.println("This seat is already taken. Choose another one.");
            } else break;
        } while (true);

        reservationDetails r = new reservationDetails(name, email, seat, day, month, year, movieTitle);
        crud.addReservation(r);
    }

    private void updateReservation(String movieTitle) {
        crud.viewReservations(movieTitle);
        int indexToUpdate = getValidInt("Enter reservation number to update: ") - 1;
        if (indexToUpdate >= 0 && indexToUpdate < crud.getReservationsForMovie(movieTitle).size()) {
            int newSeat = getValidInt("Enter new seat number: ");
            int newMonth = getValidMonth();
            int newDay = getValidDay(newMonth, 2025);
            int newYear = getValidYear();
            crud.updateReservation(indexToUpdate, newSeat, newDay, newMonth, newYear, movieTitle);
        } else {
            System.out.println("Invalid reservation number.");
        }
    }

    private void deleteReservation(String movieTitle) {
        crud.viewReservations(movieTitle);
        int indexToDelete = getValidInt("Enter reservation number to delete: ") - 1;
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
    }
}
