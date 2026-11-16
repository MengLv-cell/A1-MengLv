/**
 * Fixed daily schedule configuration for the clinic.
 * All time values are represented as minutes offset from 08:00.
 * Last valid appointment starts at 15:30 and ends at 16:00 (closing time).
 * @author Meng Lv
 */
public class ClinicSchedule {
    /** Clinic opening time, represented as 0 minutes from 08:00. */
    public static final int OPENING_MINUTES = 0;
    /** Last valid appointment start time, 450 minutes = 7.5 hours from 08:00 = 15:30. */
    public static final int LAST_SLOT_MINUTES = 450;
    /** Length of each appointment slot in minutes. */
    public static final int SLOT_INTERVAL_MINUTES = 30;

    /**
     * Checks whether a given time offset is a valid booking slot.
     * A valid slot falls within opening hours and aligns to the 30-minute interval.
     * @param minutes time offset from 08:00
     * @return true if the slot is valid for booking
     */
    public static boolean isValidSlot(int minutes) {
        return minutes >= OPENING_MINUTES
                && minutes <= LAST_SLOT_MINUTES
                && minutes % SLOT_INTERVAL_MINUTES == 0;
    }

    /**
     * Formats a minute offset into a HH:MM display string.
     * @param minutes time offset from 08:00
     * @return formatted time in HH:MM format
     */
    public static String formatTime(int minutes) {
        int hour = 8 + minutes / 60;
        int minute = minutes % 60;
        return String.format("%02d:%02d", hour, minute);
    }
}
