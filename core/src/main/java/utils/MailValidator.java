package utils;

import user.model.User;

public class MailValidator {
    public static boolean validateMail(String mail) {
        if(mail == null || mail.isEmpty()){
            return false
        }
        return true;
    }
}
