/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.controller;

import passwordgenerator.model.PasswordModel;
import passwordgenerator.view.AbstractView;
import passwordgenerator.view.AddView;
import passwordgenerator.util.Password;
import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class AddPController extends AbstractController {
    private ArrayList<String> passwords;
    
    public AddPController()
    {
        setModel(new PasswordModel());
        setView(new AddView((PasswordModel)getModel(), this));
        ((AbstractView)getView()).setVisible(true);
    }
    
     public AddPController(ArrayList<String> passwords)
    {
        this.passwords = passwords;
        setModel(new PasswordModel());
        setView(new AddView((PasswordModel)getModel(), this));
        ((AbstractView)getView()).setVisible(true);
    }
    
    public void operation(String option, String name, int length)
    {
        if(option.equals("Generate"))
        {
            Password pass = new Password();
            ((AbstractView)getView()).setVisible(false);
        }
    }
}
