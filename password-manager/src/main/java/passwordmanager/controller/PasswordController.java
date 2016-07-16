/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.StrongTextEncryptor;

import passwordmanager.model.PasswordModel;
import passwordmanager.util.DBManager;
import passwordmanager.util.Password;
import passwordmanager.view.AbstractView;
import passwordmanager.view.MainView;

/**
 *
 * @author BMamba2942
 */
public class PasswordController extends AbstractController {

    private ArrayList<Password> passwords;
    private PasswordModel pModel;
    private DBManager db;
    private StrongTextEncryptor decryptor;
    private StrongTextEncryptor encryptor;
    private String password;
    private static final int MAX_ATTEMPTS = 5;
    // Since the encryption used is non-deterministic, we need to map the
    // decrypted password names back to the encrypted names in the db to support
    // removing/renaming passwords
    private Map<String, String> passMap;

    public PasswordController() {

    }

    public PasswordController(DBManager db) {
        // First, let's see if this is the user's first time using the app.
        // If so, let's have them enter a master password
        try {
            if (db.getPasswords().isEmpty())
                JOptionPane.showMessageDialog(null, "It is time to create a master password. You will need this password in order to access passwords stored on this computer.\n"
                        + "Note: This password won't take effect until you use the app to store/generate a password. Once the master password is entered, it "
                        + "CANNOT be changed!");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "There was an error, exiting");
            System.exit(-1);
        }
        passMap = new HashMap<>();

        // Build JOptionPane for taking in password
        JPanel passwordPrompt = new JPanel();
        JLabel label = new JLabel("Password: ");
        JPasswordField passField = new JPasswordField(20);
        String[] options = {"Ok", "Cancel"};
        passwordPrompt.add(label);
        passwordPrompt.add(passField);

        boolean success = false;
        int attempts = 0;
        int selection = JOptionPane.showOptionDialog(null, passwordPrompt, "Password", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (selection == 0)
            password = new String(passField.getPassword());

        if (password == null || password.isEmpty())
            System.exit(0);

        while (!success) {
            encryptor = new StrongTextEncryptor();
            encryptor.setPassword(password);
            decryptor = new StrongTextEncryptor();
            decryptor.setPassword(password);

            try {
                this.db = db;
                this.passwords = db.getPasswords();
                for (Password p : passwords) {
                    String pName = p.getPasswordName();
                    String pString = p.getPasswordString();
                    p.setPasswordName(encryptor.decrypt(pName));
                    p.setPasswordString(encryptor.decrypt(pString));
                    passMap.put(p.getPasswordName(), pName);
                }
                success = true;
            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error accessing database", "Database error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
            catch (EncryptionOperationNotPossibleException ex) {
                //If the user got here, that means they entered the wrong password
                JOptionPane.showMessageDialog(null, "Incorrect password entered");
                int result = JOptionPane.showOptionDialog(null, passwordPrompt, "Password", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (result == 0)
                    password = new String(passField.getPassword());
                else
                    System.exit(0);
                attempts++;
                if (attempts == MAX_ATTEMPTS)
                    System.exit(0);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        pModel = new PasswordModel(passwords);
        for (Password p : passwords)
            System.out.println(p.toString());
        setModel(pModel);

        setView(
                new MainView((PasswordModel) getModel(), this));
        ((AbstractView) getView()).setVisible(true);
    }

    public void operation(String option, int index) {
        /* When a button is pressed, it will pass its name as the option variable*/
        switch (option) {
            case MainView.ADD:
                new AddPController(pModel, MainView.ADD, db, password);
                break;
            case MainView.ADD_OWN:
                new AddPController(pModel, MainView.ADD_OWN, db, password);
                break;
            case MainView.COPY:
                try {
                    String temp = ((PasswordModel) getModel()).getPassword(index).getPasswordString();
                    StringSelection stringSelection = new StringSelection(temp);
                    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clpbrd.setContents(stringSelection, null);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog((AbstractView) getView(), "No password to copy to clipboard");
                }
                break;
            case MainView.REMOVE:
                try {
                    Password passToRemove = pModel.getPassword(index);
                    if (JOptionPane.showConfirmDialog(null,
                            "Are you sure you wish to remove "
                            + passToRemove.getPasswordName()
                            + "?", "Remove Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                        try {
                            db.removePassword(passMap.get(passToRemove.getPasswordName()));
                            pModel.removePassword(index);
                        }
                        catch (SQLException e) {
                            JOptionPane.showMessageDialog((AbstractView) getView(), "An error occurred while removing " + passToRemove.getPasswordName(), "Database error", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog((AbstractView) getView(), "No passwords to remove, list is empty.");
                    e.printStackTrace();
                }
                catch (Exception e) {

                }
                break;
            case MainView.RENAME:
                String newName = JOptionPane.showInputDialog("Please enter the new name of the password");
                if (newName.isEmpty())
                    JOptionPane.showMessageDialog((AbstractView) getView(), "Password name cannot be blank");
                else if (newName.contains(" "))
                    JOptionPane.showMessageDialog((AbstractView) getView(), "Please do not include any spaces in the name of the password\n\nNew password name not set");
                else
                    for (Password p : pModel.getPasswords())
                        if (p.getPasswordName().equals(newName))
                            JOptionPane.showMessageDialog((AbstractView) getView(), "Password with this name already exists");

                Password password = ((PasswordModel) getModel()).getPassword(index);
                try {
                    String encrypted = encryptor.encrypt(newName);
                    db.renamePassword(passMap.get(password.getPasswordName()), encrypted);
                    password.setPasswordName(newName);
                    ((PasswordModel) getModel()).setPassword(password, index);
                }
                catch (SQLException e) {
                    JOptionPane.showMessageDialog((AbstractView) getView(), "An error occurred while renaming " + password.getPasswordName(), "Database error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
                catch (Exception e) {

                }

                break;

            case MainView.EXIT:
                System.exit(0);
                break;
        }
    }
}
