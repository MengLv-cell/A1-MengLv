/**
 * Represents a patient with basic contact details.
 * Patient records may be created with incomplete phone information.
 * Appointment-level validation ensures only complete valid data enters bookings.
 * @author Meng Lv
 */
public class Patient {
    private String fullName;
    private String mobilePhone;

    /**
     * Private constructor: internal use only.
     * Callers must use the create() factory method to obtain instances.
     * Invalid phone numbers are stored as empty string.
     * @param fullName validated patient name
     * @param mobilePhone phone number, may be empty if invalid
     */
    private Patient(String fullName, String mobilePhone) {
        this.fullName = fullName;
        this.mobilePhone = mobilePhone;
    }

    /**
     * Static factory method to create a Patient object.
     * Returns null if the name is blank or null.
     * Invalid phone numbers are stored as empty string; patient record can still exist.
     * Full phone validation is enforced at appointment booking level.
     * @param fullName patient full name, must not be blank
     * @param mobilePhone 10-digit contact phone number
     * @return valid Patient object, or null if name is invalid
     */
    public static Patient create(String fullName, String mobilePhone) {
        if (fullName == null || fullName.isBlank()) {
            return null;
        }
        String validPhone = isValidMobile(mobilePhone) ? mobilePhone : "";
        return new Patient(fullName, validPhone);
    }

    /**
     * Checks if a phone number is a valid 10-digit numeric string.
     * @param phone phone number to check
     * @return true if valid
     */
    private static boolean isValidMobile(String phone) {
        if (phone == null || phone.length() != 10) {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the patient full name.
     * @return patient name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Returns the patient mobile phone number.
     * @return 10-digit phone number, or empty string if invalid
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Updates the patient full name.
     * Blank or null values are ignored to preserve valid state.
     * @param fullName new patient name
     */
    public void setFullName(String fullName) {
        if (fullName != null && !fullName.isBlank()) {
            this.fullName = fullName;
        }
    }

    /**
     * Updates the patient mobile phone number.
     * Only accepts valid 10-digit numeric values; invalid input is ignored.
     * @param mobilePhone new phone number
     */
    public void setMobilePhone(String mobilePhone) {
        if (isValidMobile(mobilePhone)) {
            this.mobilePhone = mobilePhone;
        }
    }

    /**
     * Returns a formatted string with patient details.
     * @return formatted patient information
     */
    @Override
    public String toString() {
        return "Patient: " + fullName + " | Phone: " + mobilePhone;
    }
}
