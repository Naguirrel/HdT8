import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

/**
 * Represents a hospital emergency system using Java's built-in PriorityQueue.
 */
public class Hospital {
    private PriorityQueue<Paciente> queue;

    /**
     * Constructs a hospital with an empty priority queue.
     */
    public Hospital() {
        queue = new PriorityQueue<>();
    }

    /**
     * Reads patient data from a CSV file and adds them to the queue.
     *
     * @param filename The file containing patient records.
     */
    public void readCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Paciente paciente = new Paciente(data[0].trim(), data[1].trim(), data[2].trim().charAt(0));
                    queue.add(paciente);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Attends the next patient in the priority queue.
     *
     * @return The attended patient, or null if the queue is empty.
     */
    public Paciente attendPatient() {
        return queue.poll(); // Removes and returns the highest-priority patient
    }

    /**
     * Checks if there are patients waiting.
     *
     * @return True if there are patients in the queue, false otherwise.
     */
    public boolean hasPatients() {
        return !queue.isEmpty();
    }
}
