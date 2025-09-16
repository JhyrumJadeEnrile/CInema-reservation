import java.io.*;
import java.util.*;

// Class responsible for saving/loading
public class FileHandler {
    private static final String FILE_NAME = "reserved.txt";

    // Save all reservations to file
    public void saveReservations(Map<String, List<reservationDetails>> reservationsByMovie) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, List<reservationDetails>> entry : reservationsByMovie.entrySet()) {
                String movieTitle = entry.getKey();
                for (reservationDetails r : entry.getValue()) {
                    String line = r.getName() + "," + r.getEmail() + "," + r.getSeatNumber() +
                            "," + r.getDay() + "," + r.getMonth() + "," + r.getYear() + "," + movieTitle;
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving reservations: " + e.getMessage());
        }
    }

    // Load all reservations from file
    public Map<String, List<reservationDetails>> loadReservations() {
        Map<String, List<reservationDetails>> reservationsByMovie = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String name = parts[0].trim();
                    String email = parts[1].trim();
                    int seatNumber = Integer.parseInt(parts[2].trim());
                    int day = Integer.parseInt(parts[3].trim());
                    int month = Integer.parseInt(parts[4].trim());
                    int year = Integer.parseInt(parts[5].trim());
                    String movieTitle = parts[6].trim();

                    reservationDetails r = new reservationDetails(name, email, seatNumber, day, month, year, movieTitle);
                    reservationsByMovie.putIfAbsent(movieTitle, new ArrayList<>());
                    reservationsByMovie.get(movieTitle).add(r);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing reservations file found.");
        } catch (IOException e) {
            System.out.println("Error loading reservations: " + e.getMessage());
        }
        return reservationsByMovie;
    }
}
