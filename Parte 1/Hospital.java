import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Handles patient intake and priority-based treatment.
 */
public class Hospital {
    private IPriorityQueue<Paciente> queue;

    /**
     * Constructs a hospital with the provided priority queue implementation.
     *
     * @param queue The priority queue to use (VectorHeap or WrapperPriorityQueue).
     */
    public Hospital(IPriorityQueue<Paciente> queue) {
        this.queue = queue;
    }

    /**
     * Reads patient records from a CSV file and adds them to the queue.
     *
     * @param filename Path to the CSV file.
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
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    /**
     * Treats the next patient based on priority.
     *
     * @return The attended patient, or null if none.
     */
    public Paciente attendPatient() {
        return queue.remove();
    }

    /**
     * Checks if there are more patients to attend.
     *
     * @return True if there are pending patients, false otherwise.
     */
    public boolean hasPatients() {
        return (queue.remove() != null || queue instanceof WrapperPriorityQueue); // Avoids errors on peek
    }
}
