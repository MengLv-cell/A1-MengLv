import java.util.ArrayList;

/**
 * Nurse health professional type.
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

    @Override
    public String getRole() {
        return "Nurse";
    }

    /**
     * Returns whether the nurse can administer vaccinations.
     * @return vaccination authority status
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
     * Prints a vital signs check action for demonstration.
     * @param patientName name of the patient
     */
    public void checkVitalSigns(String patientName) {
        System.out.println("Nurse " + getName() + " checks vital signs for " + patientName);
    }

    @Override
    public String toString() {
        return super.toString() + " | Can vaccinate: " + canAdministerVaccination;
    }
}
