/**
 * Represents a patient with a name, symptom, and priority level.
 * Implements Comparable to determine priority order.
 */
public class Paciente implements Comparable<Paciente> {
    private String name;
    private String symptom;
    private char priority;

    /**
     * Constructs a new patient.
     * 
     * @param name     The patient's name.
     * @param symptom  The patient's symptom description.
     * @param priority The priority code (A-E, where A is the highest priority).
     */
    public Paciente(String name, String symptom, char priority) {
        this.name = name;
        this.symptom = symptom;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public String getSymptom() {
        return symptom;
    }

    public char getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Paciente other) {
        return Character.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return name + ", " + symptom + ", " + priority;
    }
}
