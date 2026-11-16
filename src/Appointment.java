/**
 * Represents a single appointment linking a patient and a health professional.
 * Time is stored as minutes offset from 08:00.
 * Internal state is protected against null pointer exceptions.
 * @author Meng Lv
 */
public class Appointment implements Comparable<Appointment> {
    private Patient patient;
    private HealthProfessional professional;
    private int timeSlotMinutes;
    private String status;

    /**
     * Constructs an appointment record with defensive input cleaning.
     * Null patient values are replaced with an empty patient to avoid crashes.
     * Business-level validation should be performed by AppointmentManager.
     * @param patient the patient who booked
     * @param professional the assigned health professional
     * @param timeSlotMinutes appointment start time in minutes from 08:00
     */
    public Appointment(Patient patient, HealthProfessional professional, int timeSlotMinutes) {
        // Defensive: replace null patient with empty safe instance
        this.patient = patient != null ? patient : new Patient("", "");
        this.professional = professional;
        this.timeSlotMinutes = timeSlotMinutes;
        this.status = "Booked";
    }

    /**
     * Returns the patient associated with this appointment.
     * @return patient object; never null
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the health professional assigned to this appointment.
     * @return health professional object; may be null if constructed with invalid input
     */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /**
     * Returns the appointment start time in minutes from 08:00.
     * @return time slot in minutes
     */
    public int getTimeSlotMinutes() {
        return timeSlotMinutes;
    }

    /**
     * Returns the current status of the appointment.
     * @return appointment status (Booked / Cancelled)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Marks the appointment as cancelled (soft delete).
     * Does not remove the record from the system.
     */
    public void cancel() {
        this.status = "Cancelled";
    }

    /**
     * Compares appointments by start time for ascending sorting.
     * @param other the other appointment to compare with
     * @return negative if this appointment is earlier, positive if later, zero if same time
     */
    @Override
    public int compareTo(Appointment other) {
        return Integer.compare(this.timeSlotMinutes, other.timeSlotMinutes);
    }

    /**
     * Returns a formatted string representation of the appointment.
     * Includes time, status, patient info and full professional details.
     * Defensive against null professional values to avoid runtime crashes.
     * @return formatted appointment string
     */
    @Override
    public String toString() {
        String proInfo = professional != null
                ? professional.toString()
                : "Unknown Professional";

        return "[" + ClinicSchedule.formatTime(timeSlotMinutes) + "] [" + status + "] "
                + patient.toString()
                + " | " + proInfo;
    }
}
