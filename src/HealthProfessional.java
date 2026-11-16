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
     * Constructs a health professional with the given details.
     * Invalid weekday entries and duplicates will be filtered automatically.
     * @param professionalId numeric ID of the professional
     * @param name full name of the professional
     * @param availableDays list of working weekdays
     */
    public HealthProfessional(int professionalId, String name, ArrayList<String> availableDays) {
        this.professionalId = professionalId;
        this.name = name;

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
     * Modifying the returned list will not affect the internal state.
     * @return copy of working days
     */
    public ArrayList<String> getAvailableDays() {
        return new ArrayList<>(availableDays);
    }

    /**
     * Updates the professional full name.
     * @param name new full name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Updates the available working days.
     * Invalid entries and duplicates will be filtered automatically.
     * @param availableDays new working days list
     */
    public void setAvailableDays(ArrayList<String> availableDays) {
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
