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

import javax.swing.JOptionPane;
import org.jasypt.util.text.StrongTextEncryptor;

import passwordmanager.model.PasswordModel;
import passwordmanager.view.AbstractView;
import passwordmanager.view.MainView;
import passwordmanager.util.DBManager;
import passwordmanager.util.Password;

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

    public PasswordController() {

    }

    public PasswordController(DBManager db) {
        password = JOptionPane.showInputDialog("Enter password", null);
        if (password == null || password.isEmpty())
            System.exit(0);
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
                p.setPasswordName(decryptor.decrypt(pName));
                p.setPasswordString(decryptor.decrypt(pString));
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog((AbstractView) this.getView(), "Error accessing database", "Database error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        pModel = new PasswordModel(passwords);
        setModel(pModel);
        setView(new MainView((PasswordModel) getModel(), this));
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
                    if (JOptionPane.showConfirmDialog(null,
                            "Are you sure you wish to remove "
                            + pModel.getPassword(index).getPasswordName()
                            + "?", "Remove Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                        try {
                            db.removePassword(pModel.getPassword(index).getPasswordName());
                            pModel.removePassword(index);
                        }
                        catch (SQLException e) {
                            JOptionPane.showMessageDialog((AbstractView) getView(), "An error occurred while removing " + pModel.getPassword(index).getPasswordName(), "Database error", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog((AbstractView) getView(), "No passwords to remove, list is empty.");
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
                        else {
                            Password password = ((PasswordModel) getModel()).getPassword(index);
                            try {
                                String encrypted = encryptor.encrypt(password.getPasswordName());
                                db.renamePassword(password.getPasswordName(), encryptor.encrypt(newName));
                                password.setPasswordName(newName);
                                ((PasswordModel) getModel()).setPassword(password, index);
                            }
                            catch (SQLException e) {
                                JOptionPane.showMessageDialog((AbstractView) getView(), "An error occurred while renaming " + password.getPasswordName(), "Database error", JOptionPane.ERROR_MESSAGE);
                                e.printStackTrace();
                            }
                        }

                break;
            case MainView.EXIT:
                System.exit(0);
                break;
        }
    }
}
