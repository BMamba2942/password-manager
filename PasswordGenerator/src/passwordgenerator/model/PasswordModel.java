/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.model;
import java.util.ArrayList;
import passwordgenerator.util.Password;
/**
 *
 * @author Joseph
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
    
    public String getPassword(int index)
    {
        return passwords.get(index).getPassword();
    }
}
