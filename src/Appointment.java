/**
 * Represents a single appointment linking a patient and a health professional.
 * Time is stored as minutes offset from 08:00.
 * Appointments can only be created with complete valid patient and professional details.
 * @author Meng Lv
 */
public class Appointment implements Comparable<Appointment> {
    private Patient patient;
    private HealthProfessional professional;
    private int timeSlotMinutes;
    private String status;

    /**
     * Private constructor: internal use only.
     * Callers must use the create() factory method to obtain instances.
     * @param patient validated patient object with valid phone
     * @param professional validated health professional object
     * @param timeSlotMinutes valid time slot
     */
    private Appointment(Patient patient, HealthProfessional professional, int timeSlotMinutes) {
        this.patient = patient;
        this.professional = professional;
        this.timeSlotMinutes = timeSlotMinutes;
        this.status = "Booked";
    }

    /**
     * Static factory method to create an appointment.
     * Rejects incomplete patient details, null references and invalid time slots.
     * Patient must have a valid 10-digit phone number for an appointment to be created.
     * Additional business validation is enforced by AppointmentManager.
     * @param patient patient object with valid name and 10-digit phone
     * @param professional health professional object, must not be null
     * @param timeSlotMinutes valid 30-minute slot within opening hours
     * @return valid Appointment object, or null if validation fails
     */
    public static Appointment create(Patient patient, HealthProfessional professional, int timeSlotMinutes) {
        if (patient == null || professional == null) {
            return null;
        }
        // Appointment-level rule: patient phone must be valid to create a booking
        if (patient.getFullName() == null || patient.getFullName().isBlank()) {
            return null;
        }
        if (patient.getMobilePhone() == null || patient.getMobilePhone().length() != 10) {
            return null;
        }
        if (!ClinicSchedule.isValidSlot(timeSlotMinutes)) {
            return null;
        }
        return new Appointment(patient, professional, timeSlotMinutes);
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
     * Compares appointments by start time for ascending sorting.
     * @param other the other appointment to compare with
     * @return negative if earlier, positive if later, zero if same time
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
