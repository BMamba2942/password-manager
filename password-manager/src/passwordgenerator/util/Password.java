/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.util;
import java.util.Random;
import java.io.File;

/**
 *
 * @author Joseph
 */
public class Password {
    private static final char[] albet = 
                      {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                       'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                       'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
                       'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                       'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
                       't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', 
                       '2', '3', '4', '5', '6', '7', '8', '9'};
    
    private File file = new File("testfile");
    private String passN;
    private String pass;
    
    SimpleFileWriter writer = new SimpleFileWriter(file.getAbsoluteFile());
    
    public Password(){}
    
    /*Generates a new random password: 
     *   int size: User requested password length
     *   String name: User requested password name
     */
    public Password generate(int size, String name) throws EmptyStringException,
            SpaceException
    {
        name = name.trim();
        if(name.equals(""))
            throw new EmptyStringException();
        for(char c : name.toCharArray())
           if(c == ' ')
               throw new SpaceException(); // No spaces, plox
        
        char[] temp = new char[size];
        Random rand = new Random();
        for(int i = 0; i < size; i++)
        {
            temp[i] = albet[rand.nextInt(albet.length)];
        }
        String password = new String(temp);
        
        //writer.savePassword(name, password);
        
        return new Password(name, password);
    }
    
   
   
   public Password(String name, String pass)
   {
       this.passN = new String(name);
       this.pass = new String(pass);
   }
   
   public String getPasswordString()
   {
       return this.pass;
   }
   
   public String getPasswordName()
   {
	   return this.passN;
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
       if(name.equals(""))
           throw new EmptyStringException();
       for(char c : name.toCharArray())
           if(c == ' ')
               throw new SpaceException(); // No spaces, plox
       for(char c : pass.toCharArray())
       {
           if(c == ' ')
               throw new SpaceException();
       }
       return new Password(name, pass);
   }
   
   
   
}
