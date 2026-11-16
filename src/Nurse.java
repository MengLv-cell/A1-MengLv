import java.util.ArrayList;

/**
 * Nurse health professional type.
 * Provides patient care and clinical support services.
 * @author Meng Lv
 */
public class Nurse extends HealthProfessional {
    private boolean canAdministerVaccination;

    private Nurse(int professionalId, String name, ArrayList<String> availableDays, boolean canAdministerVaccination) {
        super(professionalId, name, availableDays);
        this.canAdministerVaccination = canAdministerVaccination;
    }

    /**
     * Static factory method to create a valid Nurse object.
     * Returns null if core fields are invalid or no working days are provided.
     * @param professionalId positive numeric ID
     * @param name non-blank full name
     * @param availableDays list of working weekdays
     * @param canAdministerVaccination vaccination authority flag
     * @return valid Nurse object, or null if validation fails
     */
    public static Nurse create(int professionalId, String name, ArrayList<String> availableDays, boolean canAdministerVaccination) {
        if (professionalId <= 0) return null;
        if (name == null || name.isBlank()) return null;
        if (availableDays == null) return null;

        ArrayList<String> cleanedDays = HealthProfessional.cleanWeekdayList(availableDays);
        if (cleanedDays.isEmpty()) return null;

        return new Nurse(professionalId, name, availableDays, canAdministerVaccination);
    }

    /**
     * Returns the role name of this professional.
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
     * Simulates a vital signs check action.
     * @param patientName name of the patient being checked
     */
    public void checkVitalSigns(String patientName) {
        System.out.println("Nurse " + getName() + " checks vital signs for " + patientName);
    }

    /**
     * Returns a formatted string with full nurse details.
     * @return formatted professional information
     */
    @Override
    public String toString() {
        return super.toString() + " | Can vaccinate: " + canAdministerVaccination;
    }
}
