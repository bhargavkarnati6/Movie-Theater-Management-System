package project.xyz;

/*
This is an encryption class which encrypts the password of the users.
@author Bharghava Karnati
*/
public class PasswordEncryption {

    public static String encryptPassword(String password) { // Done by Bharghav
        char[] chars = password.toCharArray();
        String enc = "";
        for (char c : chars) {
            c += 7;
            enc += c;
        }
        return enc;
    }
}
