import java.util.ArrayList;

/**
 * Nurse health professional type.
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
     * Returns null if core inputs are invalid.
     * @param professionalId positive numeric ID
     * @param name non-blank full name
     * @param availableDays list of working weekdays
     * @param canAdministerVaccination vaccination authority
     * @return valid Nurse object, or null if validation fails
     */
    public static Nurse create(int professionalId, String name, ArrayList<String> availableDays, boolean canAdministerVaccination) {
        if (professionalId <= 0) return null;
        if (name == null || name.isBlank()) return null;
        if (availableDays == null) return null;
        return new Nurse(professionalId, name, availableDays, canAdministerVaccination);
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    public boolean canAdministerVaccination() {
        return canAdministerVaccination;
    }

    public void setCanAdministerVaccination(boolean canAdministerVaccination) {
        this.canAdministerVaccination = canAdministerVaccination;
    }

    public void checkVitalSigns(String patientName) {
        System.out.println("Nurse " + getName() + " checks vital signs for " + patientName);
    }

    @Override
    public String toString() {
        return super.toString() + " | Can vaccinate: " + canAdministerVaccination;
    }
}
