package org.miniproject.elearningplatform.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import org.miniproject.elearningplatform.model.submodel.UserPhoneNumber;

public class PhoneNumberValidator {
    // Function to validate a phone number
    public static boolean isValidPhoneNumber(UserPhoneNumber userPhoneNumber) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            // Parse the phone number with the given country code
            String phoneNumberWithCountryCode = userPhoneNumber.getNumber().startsWith("+") ? userPhoneNumber.getNumber() : "+" + userPhoneNumber.getNumber();
            return phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(phoneNumberWithCountryCode, userPhoneNumber.getCountryCode()));
        } catch (NumberParseException e) {
            // Invalid phone number format
            return false;
        }
    }

    public static void main(String[] args) {
        String phoneNumber = "+1234567890"; // Example phone number
        String countryCode = "US"; // Example country code
        UserPhoneNumber userPhoneNumber = new UserPhoneNumber(phoneNumber, countryCode);
        if (isValidPhoneNumber(userPhoneNumber)) {
            System.out.println("Phone number is valid.");
        } else {
            System.out.println("Phone number is not valid.");
        }
    }
}

