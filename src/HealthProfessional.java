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
     * Protected constructor: only callable by subclasses.
     * Subclasses are responsible for validating inputs before calling super().
     * @param professionalId validated positive ID
     * @param name validated non-blank name
     * @param availableDays cleaned list of valid weekdays
     */
    protected HealthProfessional(int professionalId, String name, ArrayList<String> availableDays) {
        this.professionalId = professionalId;
        this.name = name;
        this.availableDays = availableDays;
    }

    public abstract String getRole();

    public int getProfessionalId() {
        return professionalId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAvailableDays() {
        return new ArrayList<>(availableDays);
    }

    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
    }

    public void setAvailableDays(ArrayList<String> availableDays) {
        if (availableDays == null) return;
        ArrayList<String> cleaned = new ArrayList<>();
        for (String day : availableDays) {
            if (isValidWeekday(day) && !cleaned.contains(day)) {
                cleaned.add(day);
            }
        }
        this.availableDays = cleaned;
    }

    private boolean isValidWeekday(String day) {
        if (day == null) return false;
        return day.equals("Monday")
                || day.equals("Tuesday")
                || day.equals("Wednesday")
                || day.equals("Thursday")
                || day.equals("Friday")
                || day.equals("Saturday")
                || day.equals("Sunday");
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
