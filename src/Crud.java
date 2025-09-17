import java.util.Map;
import java.util.List;
import java.util.ArrayList;


// Class to handle CRUD operations for reservations
public class Crud {
    private FileHandler fileHandler;
    private Map<String, List<reservationDetails>> reservationsByMovie;

    // Constructor
    public Crud() {
        this.fileHandler = new FileHandler();
        this.reservationsByMovie = fileHandler.loadReservations();
    }

    // Create reservation
    public void addReservation(reservationDetails reservation) {
        String movieTitle = reservation.getMovieTitle();
        reservationsByMovie.putIfAbsent(movieTitle, new ArrayList<>());

        // check if seat is already taken for that movie and date
        if (isSeatTaken(movieTitle,
                reservation.getSeatNumber(),
                reservation.getDay(),
                reservation.getMonth(),
                reservation.getYear())) {
            System.out.println("❌ Seat " + reservation.getSeatNumber() + " is already taken for "
                    + movieTitle + " on " + reservation.getMonth() + "/" + reservation.getDay() + "/" + reservation.getYear());
            return;
        }

        // if free, add reservation
        reservationsByMovie.get(movieTitle).add(reservation);
        fileHandler.saveReservations(reservationsByMovie);
        System.out.println("✅ Reservation added successfully!");
    }

    // View all reservations for a movie
    public void viewReservations(String movieTitle) {
        List<reservationDetails> reservations = reservationsByMovie.getOrDefault(movieTitle, new ArrayList<>());
        if (reservations.isEmpty()) {
            System.out.println("No reservations for " + movieTitle + " found.");
        } else {
            System.out.println("\n--- Reservation List for " + movieTitle + " ---");
            for (int i = 0; i < reservations.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + reservations.get(i));
            }
        }
    }

    // Update a reservation
    public void updateReservation(int index, int newSeat, int newDay, int newMonth, int newYear, String movieTitle) {
        List<reservationDetails> reservations = reservationsByMovie.get(movieTitle);
        if (reservations != null && index >= 0 && index < reservations.size()) {
            reservationDetails r = reservations.get(index);
            r.setSeatNumber(newSeat);
            r.setDay(newDay);
            r.setMonth(newMonth);
            r.setYear(newYear);
            fileHandler.saveReservations(reservationsByMovie);
            System.out.println("Reservation updated successfully.");
        } else {
            System.out.println("Invalid reservation index.");
        }
    }

    // Delete a reservation
    public void deleteReservation(int index, String movieTitle) {
        List<reservationDetails> reservations = reservationsByMovie.get(movieTitle);
        if (reservations != null && index >= 0 && index < reservations.size()) {
            reservations.remove(index);
            fileHandler.saveReservations(reservationsByMovie);
            System.out.println("Reservation deleted successfully.");
        } else {
            System.out.println("Invalid reservation index.");
        }
    }

    // Get reservations for a movie
    public List<reservationDetails> getReservationsForMovie(String movieTitle) {
        return reservationsByMovie.getOrDefault(movieTitle, new ArrayList<>());
    }

    // Check if a seat is taken
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