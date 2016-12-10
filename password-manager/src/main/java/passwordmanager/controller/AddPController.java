/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import org.jasypt.util.text.StrongTextEncryptor;

import passwordmanager.model.PasswordModel;
import passwordmanager.util.DBManager;
import passwordmanager.util.Password;
import passwordmanager.view.AbstractView;
import passwordmanager.view.AddOwnView;
import passwordmanager.view.AddView;
import passwordmanager.view.MainView;

/**
 *
 * @author BMamba2942
 */
public class AddPController extends PasswordController {

    private PasswordModel passwords;
    private Boolean caughtError;
    private Password pass;
    private DBManager db;
    private StrongTextEncryptor encryptor;

    public AddPController(PasswordModel passwords, String mode, DBManager db, String key) {
        this.passwords = passwords;
        this.db = db;
        pass = new Password();
        setModel(this.passwords);
        encryptor = new StrongTextEncryptor();
        encryptor.setPassword(key);
        switch (mode) {
            case MainView.ADD:
                setView(new AddView(this.passwords, this));
                break;
            case MainView.ADD_OWN:
                setView(new AddOwnView(this.passwords, this));
                break;
        }

        ((AbstractView) getView()).setVisible(true);
    }

    public void operation(String option, String name, int length, String userPass) {
        switch (option) {
            case AddView.GEN:
                if (addPassword(name, length, userPass, AddView.GEN))
                    ((AbstractView) getView()).setVisible(true); //If an exception was caught, leave view open so user may make change
                else
                    ((AbstractView) getView()).setVisible(false); //otherwise, close view
                break;
            case AddOwnView.ADD:
                if (addPassword(name, length, userPass, AddOwnView.ADD))
                    ((AbstractView) getView()).setVisible(true); //If an exception was caught, leave view open so user may make change
                else
                    ((AbstractView) getView()).setVisible(false); //otherwise, close view
                break;
        }

    }

    private Boolean addPassword(String name, int length, String userPass, String mode) {
        Boolean error = false;
        caughtError = false;
        try {
            switch (mode) {
                case AddView.GEN:
                    try {
                        pass.generate(length, name);
                        Password encrypted = new Password(pass);
                        encrypted.setPasswordName(encryptor.encrypt(name));
                        encrypted.setPasswordString(encryptor.encrypt(encrypted.getPasswordString()));
                        db.addPassword(encrypted);
                        this.passwords.add(pass);
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case AddOwnView.ADD: {
                    try {
                        pass.createPassword(userPass, name);
                        Password encrypted = new Password(pass);
                        encrypted.setPasswordName(encryptor.encrypt(name));
                        encrypted.setPasswordString(encryptor.encrypt(encrypted.getPasswordString()));
                        db.addPassword(encrypted);
                        this.passwords.add(pass);
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
        catch (IllegalArgumentException g) {
                JOptionPane.showMessageDialog((AbstractView) getView(), g.getMessage());
                error = true;
        }


        return error;

    }

}
