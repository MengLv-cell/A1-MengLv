/**
 * Represents a single appointment linking a patient and a health professional.
 * Time is stored as minutes offset from 08:00.
 * Valid appointments require complete patient contact details and a valid time slot.
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
     * @param patient validated patient object
     * @param professional validated health professional object
     * @param timeSlotMinutes validated time slot
     */
    private Appointment(Patient patient, HealthProfessional professional, int timeSlotMinutes) {
        this.patient = patient;
        this.professional = professional;
        this.timeSlotMinutes = timeSlotMinutes;
        this.status = "Booked";
    }

    /**
     * Static factory method to create an appointment.
     * Rejects incomplete patient data, null references and invalid time slots.
     * Full business validation is still enforced by AppointmentManager.
     * @param patient patient object with valid name and 10-digit phone
     * @param professional health professional object, must not be null
     * @param timeSlotMinutes valid 30-minute slot within opening hours
     * @return valid Appointment object, or null if validation fails
     */
    public static Appointment create(Patient patient, HealthProfessional professional, int timeSlotMinutes) {
        if (patient == null || professional == null) {
            return null;
        }
        // Reject appointments with incomplete patient details
        if (patient.getFullName() == null || patient.getFullName().isBlank()) {
            return null;
        }
        if (!isValidMobile(patient.getMobilePhone())) {
            return null;
        }
        if (!ClinicSchedule.isValidSlot(timeSlotMinutes)) {
            return null;
        }
        return new Appointment(patient, professional, timeSlotMinutes);
    }

    /**
     * Checks if a phone number is a valid 10-digit numeric string.
     * @param phone phone number to check
     * @return true if valid
     */
    private static boolean isValidMobile(String phone) {
        if (phone == null || phone.length() != 10) {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
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
