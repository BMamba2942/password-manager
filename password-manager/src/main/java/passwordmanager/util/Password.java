/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager.util;

import java.util.Random;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author BMamba2942
 */
public class Password {

    private static final char[] ALPHABET
            = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
                'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1',
                '2', '3', '4', '5', '6', '7', '8', '9'};

    private String passName;
    private String pass;

    static public class PasswordComparer implements Comparator<Password> {

        @Override
        public int compare(Password password1, Password password2) throws NullPointerException, ClassCastException {
            return password1.getPasswordName().compareTo(password2.getPasswordName());
        }
    }

    /*Generates a new random password:
     *   int size: User requested password length
     *   String name: User requested password name
     */
    public Password generate(int size, String name) throws IllegalArgumentException {
        name = name.trim();
        if (name.isEmpty())
            throw new IllegalArgumentException("Password name cannot be empty");
        if(name.contains(" "))
            throw new IllegalArgumentException("Password name cannot contain whitespace");

        char[] temp = new char[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++)
            temp[i] = ALPHABET[rand.nextInt(ALPHABET.length)];
        String password = new String(temp);
        this.passName = name;
        this.pass = password;
        return this;
    }

    public Password() {

    }

    public Password(String name, String pass) {
        this.passName = name;
        this.pass = pass;
    }

    public Password(Password p) {
        this.passName = p.passName;
        this.pass = p.pass;
    }

    public void setPasswordString(String pass) {
        this.pass = pass;
    }

    public String getPasswordString() {
        return this.pass;
    }

    public String getPasswordName() {
        return this.passName;
    }

    public void setPasswordName(String name) {
        this.passName = name;
    }

    /* Generates a new password based on a user pre-defined password(for storing of already created
    * passwords by the user)
    * String name: User defined name for what the password will be stored as
    * String pass: User defined password to be stored
     */
    public Password createPassword(String pass, String name) throws IllegalArgumentException {
        name = name.trim();
        pass = pass.trim();
        if (name.isEmpty())
            throw new IllegalArgumentException("Password name cannot be empty");
        if(pass.isEmpty())
            throw new IllegalArgumentException("Password cannot be empty");
        if(name.contains(" "))
            throw new IllegalArgumentException("Password name cannot contain whitespace");
        if(pass.contains(" "))
            throw new IllegalArgumentException("Password cannot contain whitespace");
        this.pass = pass;
        this.passName = name;
        return this;
    }

    @Override
    public String toString() {
        return "Name: " + this.getPasswordName() + ", Password: " + this.getPasswordString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        Password p = (Password) obj;
        if (!(p instanceof Password))
            throw new ClassCastException();

        return p.pass.equals(this.pass) && p.passName.equals(this.passName); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.passName);
        hash = 53 * hash + Objects.hashCode(this.pass);
        return hash;
    }

}
