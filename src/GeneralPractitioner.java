import java.util.ArrayList;

/**
 * General Practitioner (GP) health professional type.
 * Provides general consultation services.
 * @author Meng Lv
 */
public class GeneralPractitioner extends HealthProfessional {
    private boolean canPrescribeMedication;

    private GeneralPractitioner(int professionalId, String name, ArrayList<String> availableDays, boolean canPrescribeMedication) {
        super(professionalId, name, availableDays);
        this.canPrescribeMedication = canPrescribeMedication;
    }

    /**
     * Static factory method to create a valid GP object.
     * Returns null if core fields are invalid or no working days are provided.
     * @param professionalId positive numeric ID
     * @param name non-blank full name
     * @param availableDays list of working weekdays
     * @param canPrescribeMedication prescription authority flag
     * @return valid GP object, or null if validation fails
     */
    public static GeneralPractitioner create(int professionalId, String name, ArrayList<String> availableDays, boolean canPrescribeMedication) {
        if (professionalId <= 0) return null;
        if (name == null || name.isBlank()) return null;
        if (availableDays == null) return null;

        ArrayList<String> cleanedDays = HealthProfessional.cleanWeekdayList(availableDays);
        if (cleanedDays.isEmpty()) return null;

        return new GeneralPractitioner(professionalId, name, availableDays, canPrescribeMedication);
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
     * Simulates a general consultation action.
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
