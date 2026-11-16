import java.util.ArrayList;

/**
 * General Practitioner (GP) health professional type.
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

    @Override
    public String getRole() {
        return "General Practitioner";
    }

    /**
     * Returns whether the GP can prescribe medication.
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
     * Prints a consultation action for demonstration.
     * @param patientName name of the patient
     */
    public void conductConsultation(String patientName) {
        System.out.println("Dr. " + getName() + " conducts a general consultation for " + patientName);
    }

    @Override
    public String toString() {
        return super.toString() + " | Can prescribe: " + canPrescribeMedication;
    }
}
