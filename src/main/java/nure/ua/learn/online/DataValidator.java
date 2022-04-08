package nure.ua.learn.online;

import nure.ua.learn.online.dto.SignUpData;

import java.util.HashMap;
import java.util.Map;

public class DataValidator {

    public static Map<String, String> validateData(SignUpData signUpData){
        Map<String, String> validationErrors = new HashMap<>();
        if(!signUpData.getPassword().equals(signUpData.getRepeatedPassword())){
            validationErrors.put("wrongRepeatedPassword","Passwords do not match!");
        }
        return validationErrors;
    }
}
