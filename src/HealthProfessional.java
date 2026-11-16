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
     * Constructs a health professional with all details.
     * @param professionalId numeric ID of the professional
     * @param name full name
     * @param availableDays working days of the week
     */
    public HealthProfessional(int professionalId, String name, ArrayList<String> availableDays) {
        this.professionalId = professionalId;
        this.name = name;
        this.availableDays = availableDays;
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

    public ArrayList<String> getAvailableDays() {
        return availableDays;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailableDays(ArrayList<String> availableDays) {
        this.availableDays = availableDays;
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
