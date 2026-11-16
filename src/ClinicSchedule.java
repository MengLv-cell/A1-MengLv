/**
 * Fixed daily schedule configuration for the clinic.
 * All time values are represented as minutes from 08:00.
 * @author Meng Lv
 */
public class ClinicSchedule {
    public static final int OPENING_MINUTES = 0;
    public static final int CLOSING_MINUTES = 480;
    public static final int SLOT_INTERVAL_MINUTES = 30;

    /**
     * Checks whether a given time offset is a valid booking slot.
     * @param minutes time offset from 08:00
     * @return true if the slot is within opening hours and aligned to interval
     */
    public static boolean isValidSlot(int minutes) {
        return minutes >= OPENING_MINUTES
                && minutes <= CLOSING_MINUTES
                && minutes % SLOT_INTERVAL_MINUTES == 0;
    }

    /**
     * Formats a minute offset into HH:MM display text.
     * @param minutes time offset from 08:00
     * @return formatted time string
     */
    public static String formatTime(int minutes) {
        int hour = 8 + minutes / 60;
        int minute = minutes % 60;
        return String.format("%02d:%02d", hour, minute);
    }
}
