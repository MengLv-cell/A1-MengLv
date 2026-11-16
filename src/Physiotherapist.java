import java.util.ArrayList;

/**
 * Physiotherapist health professional type.
 * @author Meng Lv
 */
public class Physiotherapist extends HealthProfessional {
    private String treatmentFocus;

    /**
     * Constructs a physiotherapist professional.
     * @param professionalId numeric ID
     * @param name full name
     * @param availableDays working days
     * @param treatmentFocus area of physical therapy
     */
    public Physiotherapist(int professionalId, String name, ArrayList<String> availableDays, String treatmentFocus) {
        super(professionalId, name, availableDays);
        this.treatmentFocus = treatmentFocus;
    }

    @Override
    public String getRole() {
        return "Physiotherapist";
    }

    /**
     * Returns the physical therapy focus area.
     * @return treatment focus area
     */
    public String getTreatmentFocus() {
        return treatmentFocus;
    }

    /**
     * Updates the physical therapy focus area.
     * @param treatmentFocus new treatment focus
     */
    public void setTreatmentFocus(String treatmentFocus) {
        this.treatmentFocus = treatmentFocus;
    }

    /**
     * Prints a therapy session action for demonstration.
     * @param patientName name of the patient
     */
    public void conductTherapySession(String patientName) {
        System.out.println("Physiotherapist " + getName() + " conducts " + treatmentFocus + " therapy for " + patientName);
    }

    @Override
    public String toString() {
        return super.toString() + " | Treatment focus: " + treatmentFocus;
    }
}
