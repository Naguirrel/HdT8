import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Manages the patient queue in a hospital emergency room.
 */
public class Hospital {
    private IPriorityQueue<Paciente> patients;

    /**
     * Constructs a hospital with a specified priority queue type.
     *
     * @param useVectorHeap If true, uses VectorHeap; otherwise, uses WrapperPriorityQueue.
     */
    public Hospital(boolean useVectorHeap) {
        patients = PriorityQueueFactory.<Paciente>createQueue(useVectorHeap);
    }

    /**
     * Reads patient data from a CSV file and adds them to the priority queue.
     *
     * @param filename The name of the CSV file containing patient data.
     */
    public void readCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String name = data[0].trim();
                    String symptom = data[1].trim();
                    char priority = data[2].trim().charAt(0);

                    if (priority >= 'A' && priority <= 'E') {
                        patients.add(new Paciente(name, symptom, priority));
                    } else {
                        System.out.println("Invalid priority for: " + name);
                    }
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Retrieves and removes the highest-priority patient from the queue.
     *
     * @return The patient with the highest priority.
     */
    public Paciente attendPatient() {
        return patients.remove();
    }
}
