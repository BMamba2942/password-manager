/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator.util;
import java.util.ArrayList;
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
    
    private File file = new File("C:\\Users\\Joseph\\Documents\\NetBeansProjects\\PasswordGenerator\\src\\testfile");
    
    SimpleFileWriter writer = new SimpleFileWriter(file.getAbsoluteFile());
    
    public Password(){}
    
    public String generate(int size, String name)
    {
        
        char[] temp = new char[size];
        Random rand = new Random();
        System.out.println(albet.length);
        for(int i = 0; i < size; i++)
        {
            temp[i] = albet[rand.nextInt(albet.length)];
        }
        String password = new String(temp);
        
        writer.savePassword(name, password);
        
        return password;
    }
    
   private String passN;
   private String pass;
   
   public Password(String name, String pass)
   {
       this.passN = new String(name);
       this.pass = new String(pass);
   }
   
   public String getPassword()
   {
       return this.pass;
   }
   
   
   
}
