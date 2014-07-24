/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.controller;
//import passwordgenerator.controller.AbstractController;
import passwordgenerator.view.MainView;
//import passwordgenerator.model.PasswordModel;
import passwordgenerator.model.PasswordModel;
import passwordgenerator.view.AbstractView;
//import passwordgenerator.controller.AddPController;
import java.util.ArrayList;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
//import passwordgenerator.util.Password;
/**
 *
 * @author Joseph
 */
public class PasswordController extends AbstractController{
    
    private ArrayList<String> passwords;
    
    public PasswordController(ArrayList<String> passwords)
    {
        this.passwords = passwords;
        setModel(new PasswordModel(passwords));
        setView(new MainView((PasswordModel)getModel(), this));
        ((AbstractView)getView()).setVisible(true);
    }
    
    public void operation(String option, int index)
    {
        /* When a button is pressed, it will pass its name as an option*/
        switch(option)
        {
            case MainView.ADD:
                new AddPController(passwords);
                break;
            case MainView.ADD_OWN:
                new AddPController();
                break;
            case MainView.COPY:
                try
                {
                    String temp = ((PasswordModel)getModel()).getPassword(index);
                    StringSelection stringSelection = new StringSelection(temp);
                    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clpbrd.setContents(stringSelection, null);
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    //No password there, so just do nothing
                }
                break;
        }
    }
}
