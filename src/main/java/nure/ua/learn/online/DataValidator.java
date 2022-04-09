package nure.ua.learn.online;

import nure.ua.learn.online.dto.ProfileData;
import nure.ua.learn.online.dto.SignUpData;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class DataValidator {

    public boolean isLoginValid(String login) {
        return Pattern.compile("^[_A-Za-z0-9]{4,20}$").matcher(login).find();
    }

    public boolean isNameValid(String name) {
        return Pattern.compile("^[`'А-Яа-яA-Za-zІіЇїЁёЪъЫы]{2,20}$").matcher(name).find();
    }

    public boolean isEmailValid(String email) {
        return Pattern.compile("^[_A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,8})$").matcher(email).find();
    }


    public Map<String, String> validateData(SignUpData signUpData) {
        Map<String, String> validationErrors = new HashMap<>();
        if (!isLoginValid(signUpData.getLogin())) {
            validationErrors.put("wrongLogin", "The login should only consist of letters and numbers from 4 to 20 characters!");
        }
        if (!signUpData.getPassword().equals(signUpData.getRepeatedPassword())) {
            validationErrors.put("wrongRepeatedPassword", "Passwords do not match!");
        }
        if (!isNameValid(signUpData.getFirstName())) {
            validationErrors.put("wrongFirstName", "The first name should only consist of letters and apostrophe from 2 to 20 characters!");
        }
        if (!isNameValid(signUpData.getLastName())) {
            validationErrors.put("wrongLastName", "The first name should only consist of letters and apostrophe from 2 to 20 characters!");
        }
        if (!isEmailValid(signUpData.getEmail())) {
            validationErrors.put("wrongEmail", "Wrong e-mail!");
        }
        return validationErrors;
    }

    public Map<String, String> validateData(ProfileData profileData) {
        Map<String, String> validationErrors = new HashMap<>();
        if (!isNameValid(profileData.getFirstName())) {
            validationErrors.put("wrongFirstName", "The first name should only consist of letters and apostrophe from 2 to 20 characters!");
        }
        if (!isNameValid(profileData.getLastName())) {
            validationErrors.put("wrongLastName", "The first name should only consist of letters and apostrophe from 2 to 20 characters!");
        }
        if (!isEmailValid(profileData.getEmail())) {
            validationErrors.put("wrongEmail", "Wrong e-mail!");
        }
        return validationErrors;
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
