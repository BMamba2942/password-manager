/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager.util;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Comparator;
import java.lang.NullPointerException;
import java.lang.ClassCastException;

/**
 *
 * @author BMamba2942
 */
public class Password{
    private static final String FILE_NAME = "testfile";

    private static final char[] ALPHABET = 
                      {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                       'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                       'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
                       'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                       'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
                       't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', 
                       '2', '3', '4', '5', '6', '7', '8', '9'};
    
    private File file = new File(FILE_NAME);
    private String passName;
    private String pass;
    
    private SimpleFileWriter writer;

    static public class PasswordComparer implements Comparator<Password>
    {
      @Override
      public int compare(Password password1, Password password2) throws NullPointerException, ClassCastException
      {
        return password1.getPasswordName().compareToIgnoreCase(password2.getPasswordName()); 
      }
    }
    
    public Password() throws IOException
    {
    	try
    	{
    		writer = new SimpleFileWriter(file.getAbsoluteFile());
    	}
    	catch(IOException e)
    	{
    		throw e;
    	}
    }
    
    /*Generates a new random password: 
     *   int size: User requested password length
     *   String name: User requested password name
     */
    public Password generate(int size, String name) throws EmptyStringException,
            SpaceException
    {
        name = name.trim();
        if(name.isEmpty())
            throw new EmptyStringException();
        if(name.contains(" "))
          throw new SpaceException(); // No spaces, plox
        
        char[] temp = new char[size];
        Random rand = new Random();
        for(int i = 0; i < size; i++)
        {
            temp[i] = ALPHABET[rand.nextInt(ALPHABET.length)];
        }
        String password = new String(temp);
        
        return new Password(name, password);
    }
    
   
   
   public Password(String name, String pass)
   {
       this.passName = new String(name);
       this.pass = new String(pass);
   }
   
   public String getPasswordString()
   {
       return this.pass;
   }
   
   public String getPasswordName()
   {
       return this.passName;
   }

   public void setPasswordName(String name)
   {
       this.passName = name;
   }

   /* Generates a new password based on a user pre-defined password(for storing of already created
    * passwords by the user)
    * String name: User defined name for what the password will be stored as
    * String pass: User defined password to be stored
    */
   public Password createPassword(String pass, String name) throws EmptyStringException,
           SpaceException
   {
       name = name.trim();
       pass = pass.trim();
       if(name.isEmpty())
           throw new EmptyStringException();
       if(name.contains(" ")) 
           throw new SpaceException(); // No spaces, plox
       if(pass.contains(" "))
           throw new SpaceException();

       return new Password(name, pass);
   }
   
    
   
   
}