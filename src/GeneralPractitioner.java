import java.util.ArrayList;

/**
 * General Practitioner (GP) health professional type.
 * Provides general consultation and prescription services.
 * @author Meng Lv
 */
public class GeneralPractitioner extends HealthProfessional {
    private boolean canPrescribeMedication;

    /**
     * Constructs a general practitioner.
     * @param professionalId numeric ID
     * @param name full name
     * @param availableDays working days
     * @param canPrescribeMedication whether the GP can prescribe medication
     */
    public GeneralPractitioner(int professionalId, String name, ArrayList<String> availableDays, boolean canPrescribeMedication) {
        super(professionalId, name, availableDays);
        this.canPrescribeMedication = canPrescribeMedication;
    }

    /**
     * Returns the role name of this professional.
     * @return role description
     */
    @Override
    public String getRole() {
        return "General Practitioner";
    }

    /**
     * Returns whether the GP has prescription authority.
     * @return prescription authority status
     */
    public boolean canPrescribeMedication() {
        return canPrescribeMedication;
    }

    /**
     * Updates the prescription authority status.
     * @param canPrescribeMedication new status
     */
    public void setCanPrescribeMedication(boolean canPrescribeMedication) {
        this.canPrescribeMedication = canPrescribeMedication;
    }

    /**
     * Simulates a general consultation action for demonstration.
     * @param patientName name of the patient receiving consultation
     */
    public void conductConsultation(String patientName) {
        System.out.println("Dr. " + getName() + " conducts a general consultation for " + patientName);
    }

    /**
     * Returns a formatted string with full GP details.
     * @return formatted professional information
     */
    @Override
    public String toString() {
        return super.toString() + " | Can prescribe: " + canPrescribeMedication;
    }
}
