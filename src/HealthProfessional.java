import java.util.ArrayList;

/**
 * Abstract base class representing a generic health professional.
 * Cannot be instantiated directly.
 * Internal state is always kept safe and free of null values.
 * @author Meng Lv
 */
public abstract class HealthProfessional {
    private int professionalId;
    private String name;
    private ArrayList<String> availableDays;

    /**
     * Constructs a health professional with validated details.
     * Invalid inputs are cleaned to safe default values to avoid invalid state.
     * Invalid weekday entries and duplicates are filtered automatically.
     * @param professionalId numeric ID of the professional; non-positive values are stored as 0
     * @param name full name of the professional; null or blank values are stored as empty string
     * @param availableDays list of working weekdays
     */
    public HealthProfessional(int professionalId, String name, ArrayList<String> availableDays) {
        // Clean ID: non-positive values are set to 0 (unassigned)
        this.professionalId = professionalId > 0 ? professionalId : 0;

        // Clean name: ensure no null value inside the object
        this.name = (name != null && !name.isBlank()) ? name : "";

        // Clean available days: filter invalid entries and remove duplicates
        ArrayList<String> cleanedDays = new ArrayList<>();
        if (availableDays != null) {
            for (String day : availableDays) {
                if (isValidWeekday(day) && !cleanedDays.contains(day)) {
                    cleanedDays.add(day);
                }
            }
        }
        this.availableDays = cleanedDays;
    }

    /**
     * Checks if a string is a valid standard weekday name.
     * @param day day name to check
     * @return true if the day is a valid weekday
     */
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

    /**
     * Returns the role/type of this health professional.
     * Each subclass must provide its own implementation.
     * @return role description
     */
    public abstract String getRole();

    /**
     * Returns the professional ID number.
     * @return professional ID; returns 0 if ID was invalid
     */
    public int getProfessionalId() {
        return professionalId;
    }

    /**
     * Returns the professional full name.
     * @return full name; never returns null
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the available working days list.
     * Modifying the returned list will not affect the internal state.
     * @return copy of working days
     */
    public ArrayList<String> getAvailableDays() {
        return new ArrayList<>(availableDays);
    }

    /**
     * Updates the professional full name.
     * Blank or null values are ignored to preserve valid state.
     * @param name new full name
     */
    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
    }

    /**
     * Updates the available working days.
     * Invalid entries and duplicates are filtered automatically.
     * Null input is ignored to preserve valid state.
     * @param availableDays new working days list
     */
    public void setAvailableDays(ArrayList<String> availableDays) {
        if (availableDays == null) {
            return;
        }
        ArrayList<String> cleanedDays = new ArrayList<>();
        for (String day : availableDays) {
            if (isValidWeekday(day) && !cleanedDays.contains(day)) {
                cleanedDays.add(day);
            }
        }
        this.availableDays = cleanedDays;
    }

    /**
     * Returns a formatted string with professional base details.
     * @return formatted professional information
     */
    @Override
    public String toString() {
        return "ID: " + professionalId
                + " | Name: " + name
                + " | Role: " + getRole()
                + " | Working days: " + availableDays;
    }

    /**
     * Checks equality based on professional ID.
     * Two professionals are considered equal if they share the same ID.
     * @param other the object to compare with
     * @return true if IDs match
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof HealthProfessional)) return false;
        HealthProfessional that = (HealthProfessional) other;
        return this.professionalId == that.professionalId;
    }

    /**
     * Returns a hash code based on professional ID.
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(professionalId);
    }
}
