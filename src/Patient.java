/**
 * Represents a patient with basic contact details.
 * @author Meng Lv
 */
public class Patient {
    private String fullName;
    private String mobilePhone;

    /**
     * Constructs a patient record with the given details.
     * Invalid phone numbers will be stored as empty string.
     * @param fullName patient full name
     * @param mobilePhone 10-digit contact phone number
     */
    public Patient(String fullName, String mobilePhone) {
        this.fullName = fullName;
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
     * @return patient name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Returns the patient mobile phone number.
     * @return 10-digit phone number
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Updates the patient full name.
     * @param fullName new patient name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Updates the patient mobile phone number.
     * Only accepts valid 10-digit numeric values.
     * @param mobilePhone new phone number
     */
    public void setMobilePhone(String mobilePhone) {
        if (isValidMobile(mobilePhone)) {
            this.mobilePhone = mobilePhone;
        }
    }

    @Override
    public String toString() {
        return "Patient: " + fullName + " | Phone: " + mobilePhone;
    }
}
