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
    
    public Object[] getNames()
    {
        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i < passStrings.size(); i+=2)
            temp.add(passStrings.get(i));
 
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
    	   notifyChanged(new ModelEvent(this, index, "remove", (float)0.0));
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
    	notifyChanged(new ModelEvent(this, 1, "add", (float)0.0));
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
