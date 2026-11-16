import java.util.ArrayList;

/**
 * General Practitioner (GP) health professional type.
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
     * Returns null if core inputs are invalid.
     * @param professionalId positive numeric ID
     * @param name non-blank full name
     * @param availableDays list of working weekdays
     * @param canPrescribeMedication prescription authority
     * @return valid GP object, or null if validation fails
     */
    public static GeneralPractitioner create(int professionalId, String name, ArrayList<String> availableDays, boolean canPrescribeMedication) {
        if (professionalId <= 0) return null;
        if (name == null || name.isBlank()) return null;
        if (availableDays == null) return null;
        return new GeneralPractitioner(professionalId, name, availableDays, canPrescribeMedication);
    }

    @Override
    public String getRole() {
        return "General Practitioner";
    }

    public boolean canPrescribeMedication() {
        return canPrescribeMedication;
    }

    public void setCanPrescribeMedication(boolean canPrescribeMedication) {
        this.canPrescribeMedication = canPrescribeMedication;
    }

    public void conductConsultation(String patientName) {
        System.out.println("Dr. " + getName() + " conducts a general consultation for " + patientName);
    }

    @Override
    public String toString() {
        return super.toString() + " | Can prescribe: " + canPrescribeMedication;
    }
}
