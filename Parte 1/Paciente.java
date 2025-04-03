/**
 * Represents a patient in the hospital emergency system.
 */
public class Paciente implements Comparable<Paciente> {
    private String name;
    private String symptom;
    private char priority;

    /**
     * Constructs a new patient.
     *
     * @param name     The patient's name.
     * @param symptom  The patient's symptom.
     * @param priority The emergency priority (A = highest, E = lowest).
     */
    public Paciente(String name, String symptom, char priority) {
        this.name = name;
        this.symptom = symptom;
        this.priority = priority;
    }

    /**
     * Gets the patient's priority.
     *
     * @return The priority letter.
     */
    public char getPriority() {
        return priority;
    }

    /**
     * Compares two patients based on priority.
     *
     * @param other The other patient to compare.
     * @return Negative if this patient has higher priority, positive otherwise.
     */
    @Override
    public int compareTo(Paciente other) {
        return Character.compare(this.priority, other.priority); // Lower letters (A) have higher priority
    }

    /**
     * Returns a string representation of the patient.
     *
     * @return A formatted string with name, symptom, and priority.
     */
    @Override
    public String toString() {
        return name + ", " + symptom + ", " + priority;
    }
}
