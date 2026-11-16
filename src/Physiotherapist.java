import java.util.ArrayList;

/**
 * Physiotherapist health professional type.
 * Provides physical therapy and rehabilitation services.
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

    /**
     * Returns the role name of this professional.
     * @return role description
     */
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
     * Simulates a therapy session action for demonstration.
     * @param patientName name of the patient receiving therapy
     */
    public void conductTherapySession(String patientName) {
        System.out.println("Physiotherapist " + getName() + " conducts " + treatmentFocus + " therapy for " + patientName);
    }

    /**
     * Returns a formatted string with full physiotherapist details.
     * @return formatted professional information
     */
    @Override
    public String toString() {
        return super.toString() + " | Treatment focus: " + treatmentFocus;
    }
}
