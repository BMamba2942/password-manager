/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import passwordgenerator.model.PasswordModel;
import passwordgenerator.util.EmptyStringException;
import passwordgenerator.util.Password;
import passwordgenerator.util.SpaceException;
import passwordgenerator.view.AbstractView;
import passwordgenerator.view.AddOwnView;
import passwordgenerator.view.AddView;
import passwordgenerator.view.MainView;

/**
 *
 * @author BMamba2942
 */
public class AddPController extends PasswordController {
	private PasswordModel passwords;
	private Boolean caughtError;
	private Password pass, ownPass;

	public AddPController(PasswordModel passwords, String mode)
	{
		this.passwords = passwords;
		setModel(this.passwords);
		if(mode.equals(MainView.ADD))
		   setView(new AddView(this.passwords, this));
		else if(mode.equals(MainView.ADD_OWN))
			setView(new AddOwnView(this.passwords, this));
		((AbstractView)getView()).setVisible(true);
	}

	public void operation(String option, String name, int length, String userPass)
	{
		switch(option)
		{
		case AddView.GEN:  	   
			if(addPassword(name, length, userPass, AddView.GEN))
				((AbstractView)getView()).setVisible(true); //If an exception was caught, leave view open so user may make change
			else
				((AbstractView)getView()).setVisible(false); //otherwise, close view
			break;
		case AddOwnView.ADD:
			if(addPassword(name, length, userPass, AddOwnView.ADD))
				((AbstractView)getView()).setVisible(true); //If an exception was caught, leave view open so user may make change
			else
				((AbstractView)getView()).setVisible(false); //otherwise, close view
			break;
		}


	}

	private Boolean addPassword(String name, int length, String userPass, String mode)
	{
		Boolean error = false;
		try
		{
		   pass = new Password();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog((AbstractView)getView(), "Unable to open a file to save passwords."); 
		}
		caughtError = false;
		try
		{
			if(mode.equals(AddView.GEN))
			    this.passwords.add(pass.generate(length, name), false); //add password, but flag duplicates
			else if(mode.equals(AddOwnView.ADD))
				this.passwords.add(pass.createPassword(userPass, name), false);
		}
		catch(EmptyStringException e)
		{
			JOptionPane.showMessageDialog((AbstractView)getView(), "Please enter a password name");
			error = true;
		}
		catch(SpaceException f)
		{
			JOptionPane.showMessageDialog((AbstractView)getView(), "Please do not put any spaces");
			error = true;
		}
		catch(IllegalArgumentException g)
		{
			int response = JOptionPane.showConfirmDialog(null, 
                "Attempting to add password with a duplicate name, do you wish to change the name?\nYes prompts you to change the name\nNo adds the password anyway\nCancel to do nothing", "Duplicate Password", JOptionPane.YES_NO_CANCEL_OPTION);
			String newName;

			if(response == JOptionPane.YES_OPTION)
			{
				newName = JOptionPane.showInputDialog(null, "Please enter a new name for your password");
				addPassword(newName, length, userPass, mode);
			}
			else if(response == JOptionPane.NO_OPTION)
			{
				try
				{
					if(mode.equals(AddView.GEN))
				    	this.passwords.add(pass.generate(length, name), true); //duplicate ok, so add password anyway
					else if(mode.equals(AddOwnView.ADD))
						this.passwords.add(pass.createPassword(userPass, name), true);
				}
				catch(EmptyStringException e)
				{
					JOptionPane.showMessageDialog((AbstractView)getView(), "Please enter a password name");
					error = true;
				}
				catch(SpaceException f)
				{
					JOptionPane.showMessageDialog((AbstractView)getView(), "Please do not put any spaces");
					error = true;
				}
			}
		}

		return error;

	}

}

