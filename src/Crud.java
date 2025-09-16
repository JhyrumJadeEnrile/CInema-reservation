import java.util.ArrayList;

public class Crud {
    ArrayList<reservationDetails> reservations = new ArrayList<>();
    private FileHandler fileHandler;

    public Crud() {
        this.fileHandler = new FileHandler();
        this.reservations = new ArrayList<>(fileHandler.loadReservations());
    }

    // Create
    public void addReservation(reservationDetails reservation) {
        reservations.add(reservation);
        fileHandler.saveReservations(reservations); // ✅ save to file
        System.out.println("Reservation added successfully.");
    }

    // Read
    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("\n--- Reservation List ---");
            for (int i = 0; i < reservations.size(); i++) {
                System.out.println("[" + (i + 1) + "]");
                reservations.get(i).personalInfo();
                System.out.println("-----------------------");
            }
        }
    }

    // Update
    public void updateReservation(int index, int newSeat, int newDay, int newMonth, int newYear) {
        if (index >= 0 && index < reservations.size()) {
            reservationDetails r = reservations.get(index);
            r.setSeatNumber(newSeat);
            r.setDay(newDay);
            r.setMonth(newMonth);
            r.setYear(newYear);
            fileHandler.saveReservations(reservations); // ✅ save updated data
            System.out.println("Reservation updated successfully.");
        } else {
            System.out.println("Invalid reservation index.");
        }
    }

    // Delete
    public void deleteReservation(int index) {
        if (index >= 0 && index < reservations.size()) {
            reservations.remove(index);
            fileHandler.saveReservations(reservations); // ✅ save after deletion
            System.out.println("Reservation deleted successfully.");
        } else {
            System.out.println("Invalid reservation index.");
        }
    }

}

