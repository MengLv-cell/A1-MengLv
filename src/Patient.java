/**
 * Represents a patient with basic contact details.
 * Internal state is always kept safe and free of null values.
 * @author Meng Lv
 */
public class Patient {
    private String fullName;
    private String mobilePhone;

    /**
     * Constructs a patient record with validated details.
     * Invalid inputs are cleaned to safe default values.
     * @param fullName patient full name; null or blank values are stored as empty string
     * @param mobilePhone 10-digit contact phone number; invalid values are stored as empty string
     */
    public Patient(String fullName, String mobilePhone) {
        // Clean name: ensure no null value inside the object
        this.fullName = (fullName != null && !fullName.isBlank()) ? fullName : "";

        // Clean phone: only valid 10-digit numbers are stored
        if (isValidMobile(mobilePhone)) {
            this.mobilePhone = mobilePhone;
        } else {
            this.mobilePhone = "";
        }
    }

    /**
     * Checks if a phone number is a valid 10-digit numeric string.
     * @param phone phone number to check
     * @return true if valid
     */
    private boolean isValidMobile(String phone) {
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
     * @return patient name; never returns null
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
