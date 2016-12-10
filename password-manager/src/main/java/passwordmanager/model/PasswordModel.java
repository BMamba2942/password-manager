/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager.model;

import java.util.ArrayList;

import passwordmanager.util.Password;

/**
 *
 * @author BMamba2942
 */
public class PasswordModel extends AbstractModel {

    private ArrayList<Password> passwords = new ArrayList<>();

    public PasswordModel(ArrayList<Password> pass) {
        this.passwords = pass;
    }

    private void sortPasswordsByName() {
        // Empty lists/one element lists are already sorted
        if (passwords.size() <= 1)
            return;
        passwords.sort(new Password.PasswordComparer());

    }

    public Object[] getNames() {
        ArrayList<String> temp = new ArrayList<>();
        for (Password p : passwords)
            temp.add(p.getPasswordName());

        return temp.toArray();
    }

    public Password getPassword(int index) {
        return passwords.get(index);
    }

    public void renamePassword(Password newP, int index) {
        passwords.set(index, newP);
        sortPasswordsByName();
        // An add ModelEvent can handle renaming as well
        notifyChanged(new ModelEvent(this, index, "add"));
    }

    public ArrayList<Password> getPasswords() {
        return this.passwords;
    }

    public void removePassword(int index) throws ArrayIndexOutOfBoundsException {
        passwords.remove(index);
        notifyChanged(new ModelEvent(this, index, "remove"));
    }

    public void add(Password password) throws IllegalArgumentException {
        for (Password p : passwords)
            if (p.getPasswordName().equals(password.getPasswordName()))
                throw new IllegalArgumentException("Password with name " + password.getPasswordName() + " already exists");
        passwords.add(password);
        sortPasswordsByName();
        notifyChanged(new ModelEvent(this, 1, "add"));
    }
}
