import java.util.ArrayList;

/**
 * Abstract base class representing a generic health professional.
 * Cannot be instantiated directly.
 * Internal weekday list is always filtered and protected from external modification.
 * @author Meng Lv
 */
public abstract class HealthProfessional {
    private int professionalId;
    private String name;
    private ArrayList<String> availableDays;

    /**
     * Protected constructor: only callable by subclasses.
     * Weekday list is filtered for valid entries and duplicates at construction time.
     * @param professionalId numeric ID of the professional
     * @param name full name of the professional
     * @param availableDays list of working weekdays
     */
    protected HealthProfessional(int professionalId, String name, ArrayList<String> availableDays) {
        this.professionalId = professionalId;
        this.name = name;
        this.availableDays = cleanWeekdayList(availableDays);
    }

    /**
     * Filters a list to keep only valid, non-duplicate weekday names.
     * Shared across parent and all subclasses for consistent validation.
     * @param days raw input list
     * @return cleaned list of valid weekdays
     */
    protected static ArrayList<String> cleanWeekdayList(ArrayList<String> days) {
        ArrayList<String> cleaned = new ArrayList<>();
        if (days == null) {
            return cleaned;
        }
        for (String day : days) {
            if (isValidWeekday(day) && !cleaned.contains(day)) {
                cleaned.add(day);
            }
        }
        return cleaned;
    }

    /**
     * Checks if a string matches a standard weekday name.
     * @param day day name to check
     * @return true if the input is a valid weekday
     */
    private static boolean isValidWeekday(String day) {
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
     * @return professional ID
     */
    public int getProfessionalId() {
        return professionalId;
    }

    /**
     * Returns the professional full name.
     * @return full name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the available working days list.
     * Modifying the returned list does not affect the internal object state.
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
     * Null input is ignored.
     * @param availableDays new working days list
     */
    public void setAvailableDays(ArrayList<String> availableDays) {
        if (availableDays != null) {
            this.availableDays = cleanWeekdayList(availableDays);
        }
    }

    /**
     * Returns a formatted string with base professional details.
     * @return formatted information string
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
     * Two professionals are equal if they share the same ID.
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
