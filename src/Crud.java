import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Crud {
    private FileHandler fileHandler;
    private Map<String, List<reservationDetails>> reservationsByMovie = new HashMap<>();

    public Crud() {
        this.fileHandler = new FileHandler();
        this.reservationsByMovie = fileHandler.loadReservations();
    }

    // Create
    public void addReservation(reservationDetails reservation) {
        String movieTitle = reservation.getMovieTitle();
        reservationsByMovie.putIfAbsent(movieTitle, new ArrayList<>());
        reservationsByMovie.get(movieTitle).add(reservation);
        System.out.println("Reservation added successfully!");
        fileHandler.saveReservations(reservationsByMovie); // Save after adding
    }

    // Read
    public void viewReservations(String movieTitle) {
        List<reservationDetails> reservations = reservationsByMovie.getOrDefault(movieTitle, new ArrayList<>());
        if (reservations.isEmpty()) {
            System.out.println("No reservations for " + movieTitle + " found.");
        } else {
            System.out.println("\n--- Reservation List for " + movieTitle + " ---");
            for (int i = 0; i < reservations.size(); i++) {
                System.out.println("[" + (i + 1) + "]");
            }
        }
    }

    // Update
    public void updateReservation(int index, int newSeat, int newDay, int newMonth, int newYear, String movieTitle) {
        List<reservationDetails> reservations = reservationsByMovie.get(movieTitle);
        if (reservations != null && index >= 0 && index < reservations.size()) {
            reservationDetails r = reservations.get(index);
            r.setSeatNumber(newSeat);
            r.setDay(newDay);
            r.setMonth(newMonth);
            r.setYear(newYear);
            fileHandler.saveReservations(reservationsByMovie); // Save updated data
            System.out.println("Reservation updated successfully.");
        } else {
            System.out.println("Invalid reservation index.");
        }
    }

    // Delete
    public void deleteReservation(int index, String movieTitle) {
        List<reservationDetails> reservations = reservationsByMovie.get(movieTitle);
        if (reservations != null && index >= 0 && index < reservations.size()) {
            reservations.remove(index);
            fileHandler.saveReservations(reservationsByMovie); // Save after deletion
            System.out.println("Reservation deleted successfully.");
        } else {
            System.out.println("Invalid reservation index.");
        }
    }

    // Helper method to get the list of reservations for a movie
    public List<reservationDetails> getReservationsForMovie(String movieTitle) {
        return reservationsByMovie.getOrDefault(movieTitle, new ArrayList<>());
    }

    // Checks if a seat is taken for a specific movie and date
    public boolean isSeatTaken(String movieTitle, int seatNumber, int day, int month, int year) {
        List<reservationDetails> reservations = reservationsByMovie.getOrDefault(movieTitle, new ArrayList<>());
        for (reservationDetails reservation : reservations) {
            if (reservation.getSeatNumber() == seatNumber &&
                    reservation.getDay() == day &&
                    reservation.getMonth() == month &&
                    reservation.getYear() == year) {
                return true;
            }
        }
        return false;
    }
}