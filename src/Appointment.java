/**
 * Represents a single appointment linking a patient and a health professional.
 * Time is stored as minutes offset from 08:00.
 * @author Meng Lv
 */
public class Appointment implements Comparable<Appointment> {
    private Patient patient;
    private HealthProfessional professional;
    private int timeSlotMinutes;
    private String status;

    /**
     * Constructs an appointment record.
     * Callers must validate all inputs before creating an appointment.
     * Business-level validation is handled by AppointmentManager.
     * @param patient the patient who booked
     * @param professional the assigned health professional
     * @param timeSlotMinutes appointment start time in minutes from 08:00
     */
    public Appointment(Patient patient, HealthProfessional professional, int timeSlotMinutes) {
        this.patient = patient;
        this.professional = professional;
        this.timeSlotMinutes = timeSlotMinutes;
        this.status = "Booked";
    }

    /**
     * Returns the patient associated with this appointment.
     * @return patient object
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the health professional assigned to this appointment.
     * @return health professional object
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
     * Compares appointments by start time for sorting.
     * @param other the other appointment to compare with
     * @return negative if this appointment is earlier, positive if later
     */
    @Override
    public int compareTo(Appointment other) {
        return Integer.compare(this.timeSlotMinutes, other.timeSlotMinutes);
    }

    /**
     * Returns a formatted string representation of the appointment.
     * Includes time, status, patient info and full professional details.
     * @return formatted appointment string
     */
    @Override
    public String toString() {
        return "[" + ClinicSchedule.formatTime(timeSlotMinutes) + "] [" + status + "] "
                + patient.toString()
                + " | " + professional.toString();
    }
}
