/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.model;
import java.io.File;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;

import passwordgenerator.util.Password;
import passwordgenerator.util.SimpleFileWriter;
/**
 *
 * @author BMamba2942
 */
public class PasswordModel extends AbstractModel{
    private ArrayList<String> passStrings;
    private ArrayList<Password> passwords = new ArrayList<>();
    public PasswordModel()
    {
        
    }
    
    public PasswordModel(ArrayList<String> pass)
    {
        this.passStrings = pass;
        
        for(int i = 1; i <= passStrings.size(); i+=2)
       {
           passwords.add(new Password(passStrings.get(i-1), passStrings.get(i)));
       }
    }
    
    public void sortPasswordsByName()
    {
        // Empty lists/one element lists are already sorted
        if(passwords.size() <= 1) return;
        passwords.sort(new Password.PasswordComparer());

    }

    public Object[] getNames()
    {
        ArrayList<String> temp = new ArrayList<>();
        for(Password p : passwords)
            temp.add(p.getPasswordName());
 
        return temp.toArray();
    }
    
    public Password getPassword(int index)
    {
        return passwords.get(index);
    }
    
    public ArrayList<Password> getPasswords()
    {
    	return this.passwords;
    }
    
    public void removePassword(int index) throws ArrayIndexOutOfBoundsException
    {
    	try
    	{
    	   passwords.remove(index);
    	   notifyChanged(new ModelEvent(this, index, "remove"));
    	}
    	catch(ArrayIndexOutOfBoundsException e)
    	{
    		throw e;
    	}   	
    }
    
    public void add(Password password, Boolean force) throws IllegalArgumentException
    {
        for(Password p : passwords)
        {
            if(!force && p.getPasswordName().equals(password.getPasswordName()))
                throw new IllegalArgumentException();
        }
    	passwords.add(password);
        sortPasswordsByName();
    	notifyChanged(new ModelEvent(this, 1, "add"));
    }
    
    public void savePasswords(File file)
    {
    	SimpleFileWriter writer = new SimpleFileWriter(file, this.passwords);
    	writer.savePasswords();
    	
    }
    
    public String getLastPassword()
    {
    	return passwords.get(passwords.size()-1).getPasswordName();
    }
}
