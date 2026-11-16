import java.util.ArrayList;

/**
 * Nurse health professional type.
 * Provides patient care, vital sign checks and vaccination services.
 * @author Meng Lv
 */
public class Nurse extends HealthProfessional {
    private boolean canAdministerVaccination;

    /**
     * Constructs a nurse professional.
     * @param professionalId numeric ID
     * @param name full name
     * @param availableDays working days
     * @param canAdministerVaccination whether the nurse can administer vaccinations
     */
    public Nurse(int professionalId, String name, ArrayList<String> availableDays, boolean canAdministerVaccination) {
        super(professionalId, name, availableDays);
        this.canAdministerVaccination = canAdministerVaccination;
    }

    /**
     * Returns the role name of this professional.
     * Overrides the abstract method from the parent class.
     * @return role description
     */
    @Override
    public String getRole() {
        return "Nurse";
    }

    /**
     * Returns whether the nurse has vaccination authority.
     * @return vaccination administration status
     */
    public boolean canAdministerVaccination() {
        return canAdministerVaccination;
    }

    /**
     * Updates the vaccination authority status.
     * @param canAdministerVaccination new status
     */
    public void setCanAdministerVaccination(boolean canAdministerVaccination) {
        this.canAdministerVaccination = canAdministerVaccination;
    }

    /**
     * Simulates a vital signs check action for demonstration.
     * @param patientName name of the patient being checked
     */
    public void checkVitalSigns(String patientName) {
        System.out.println("Nurse " + getName() + " checks vital signs for " + patientName);
    }

    /**
     * Returns a formatted string with full nurse details.
     * Extends the parent toString with subclass-specific information.
     * @return formatted professional information
     */
    @Override
    public String toString() {
        return super.toString() + " | Can vaccinate: " + canAdministerVaccination;
    }
}
