package passwordmanager;

import org.junit.Test;
import passwordmanager.util.Password;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PasswordTest {

    @Test
    public void testCreatePassword() throws Exception {
        Password p = new Password();
        try{
            p.createPassword("password", "name");
        }
        catch(IllegalArgumentException e){
            fail("Create threw exception when it shouldn't have");
        }
    }

    @Test
    public void  testGeneratePassword() throws Exception {
        Password p = new Password();
        try{
            p.generate(8, "asdf");
        }catch(IllegalArgumentException e){
            fail("Generate threw exception when it shouldn't have");
        }
    }

    @Test
    public void testCreatePasswordEmptyName() throws Exception {
        Password p = new Password();
        try{
            p.createPassword("password", "");
            fail("Create password didn't throw expected exception with blank name");
        }
        catch(IllegalArgumentException e){

        }

    }

    @Test
    public void testCreatePasswordEmptyPassword() throws Exception {
        Password p = new Password();
        try{
            p.createPassword("", "name");
            fail("Create password didn't throw expected exception with blank password");
        }
        catch(IllegalArgumentException e){

        }
    }

    @Test
    public void testCreatePasswordSpaceInName() throws Exception {
        Password p = new Password();
        try{
            p.createPassword("password", "has space");
            fail("Create password didn't throw expected exception with space in name");
        }
        catch(IllegalArgumentException e){

        }

    }

    @Test
    public void testCreatePasswordSpaceInPassword() throws Exception {
        Password p = new Password();
        try{
            p.createPassword("space pass", "name");
            fail("Create password didn't throw expected exception with space in password");
        }
        catch(IllegalArgumentException e){

        }
    }

    @Test
    public void testPasswordCompare() {
        Password pass1 = new Password();
        pass1.setPasswordName("pass1");
        Password pass2 = new Password();
        pass2.setPasswordName("pass2");
        int result = new Password.PasswordComparer().compare(pass1, pass2);
        assertTrue(result < 0);
    }
}
