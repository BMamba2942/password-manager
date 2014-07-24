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
import passwordgenerator.util.EmptyStringException;
import passwordgenerator.view.AddOwnView;
import passwordgenerator.util.SpaceException;

/**
 *
 * @author Joseph
 */
public class AddPController extends AbstractController {
    private ArrayList<String> passwords;
    
    public AddPController()
    {
        openView();
    }
    
     public AddPController(ArrayList<String> passwords)
    {
        this.passwords = passwords;
        setModel(new PasswordModel());
        setView(new AddView((PasswordModel)getModel(), this));
        ((AbstractView)getView()).setVisible(true);
    }
    
    public void operation(String option, String name, int length, String userPass)
    {
        switch(option)
        {
            case AddView.GEN:
                Password pass = new Password();
                try
                {
                   pass.generate(length, name);
                }
                catch(EmptyStringException e)
                {
                    // ERROR MESSAGE BOX
                    System.err.println("Please enter a password name");
                }
                catch(SpaceException f)
                {
                    // ERROR MESSAGE BOX
                    System.err.println("Please do not put any spaces");
                }
                ((AbstractView)getView()).setVisible(false);
                break;
            case AddOwnView.ADD:
                Password ownPass = new Password();
                try
                {
                   ownPass.createPassword(userPass, name);
                }
                catch(EmptyStringException e)
                {
                    // ERROR MESSAGE BOX
                    System.err.println("Please enter a password name");
                }
                catch(SpaceException f)
                {
                    // ERROR MESSAGE BOX
                    System.err.println("Please do not put any spaces");
                }
                ((AbstractView)getView()).setVisible(false);
                break;
        }
        
       
    }
    
     private void openView()
     {
        setModel(new PasswordModel());
        setView(new AddOwnView((PasswordModel)getModel(), this));
        ((AbstractView)getView()).setVisible(true);
     }
}
