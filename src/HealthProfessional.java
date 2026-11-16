import java.util.ArrayList;

/**
 * Abstract base class representing a generic health professional.
 * Cannot be instantiated directly.
 * @author Meng Lv
 */
public abstract class HealthProfessional {
    private int professionalId;
    private String name;
    private ArrayList<String> availableDays;

    /**
     * Constructs a health professional with validated details.
     * Invalid inputs will be replaced with safe default values.
     * @param professionalId numeric ID of the professional, must be positive
     * @param name full name, must not be blank
     * @param availableDays working days of the week, must not be null
     */
    public HealthProfessional(int professionalId, String name, ArrayList<String> availableDays) {
        if (professionalId > 0) {
            this.professionalId = professionalId;
        } else {
            this.professionalId = 1;
        }

        if (name != null && !name.isBlank()) {
            this.name = name;
        } else {
            this.name = "Unnamed Professional";
        }

        if (availableDays != null) {
            this.availableDays = new ArrayList<>(availableDays);
        } else {
            this.availableDays = new ArrayList<>();
        }
    }

    /**
     * Returns the role/type of this health professional.
     * @return role description
     */
    public abstract String getRole();

    public int getProfessionalId() {
        return professionalId;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the available days list.
     * Modifying the returned list will not affect the internal state.
     * @return copy of working days
     */
    public ArrayList<String> getAvailableDays() {
        return new ArrayList<>(availableDays);
    }

    /**
     * Updates the professional name with validation.
     * @param name new full name, must not be blank
     */
    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
    }

    /**
     * Updates the available working days with validation.
     * @param availableDays new working days list, must not be null
     */
    public void setAvailableDays(ArrayList<String> availableDays) {
        if (availableDays != null) {
            this.availableDays = new ArrayList<>(availableDays);
        }
    }

    @Override
    public String toString() {
        return "ID: " + professionalId
                + " | Name: " + name
                + " | Role: " + getRole()
                + " | Working days: " + availableDays;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof HealthProfessional)) return false;
        HealthProfessional that = (HealthProfessional) other;
        return this.professionalId == that.professionalId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(professionalId);
    }
}
