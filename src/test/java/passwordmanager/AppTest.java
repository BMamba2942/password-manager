package passwordmanager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import passwordmanager.controllers.EncryptionController;
import passwordmanager.util.DBManager;
import passwordmanager.models.Password;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testCreatePassword() {
        try {
            DBManager db = new DBManager();
            ArrayList<Password> pwords = db.getPasswords();
            assertEquals(0, pwords.size());
            db.addPassword(new Password("test", "test"));
            pwords = db.getPasswords();
            assertEquals(1, pwords.size());
        } catch (SQLException | ClassNotFoundException e) {
            fail(e.getMessage());
        }
    }

    public void testRenamePassword() {
        try {
            DBManager db = new DBManager();
            db.addPassword(new Password("test", "test"));
            ArrayList<Password> pwords = db.getPasswords();
            assertEquals(1, pwords.size());
            db.renamePassword("test", "rename");
            pwords = db.getPasswords();
            assertEquals(1, pwords.size());
            assertEquals(pwords.get(0).getName(), "rename");
        } catch (SQLException | ClassNotFoundException e) {
            fail(e.getMessage());
        }
    }

    public void testRemovePassword() {
        try {
            DBManager db = new DBManager();
            db.addPassword(new Password("test", "test"));
            db.addPassword(new Password("test2", "test2"));
            ArrayList<Password> pwords = db.getPasswords();
            assertEquals(2, pwords.size());
            db.removePassword("test");
            pwords = db.getPasswords();
            assertEquals(1, pwords.size());
            assertTrue(pwords.get(0).equals(new Password("test", "test")));
        } catch(SQLException | ClassNotFoundException e) {
            fail(e.getMessage());
        }
    }

    public void testEncrpytion() {
        EncryptionController ec = new EncryptionController();
        Password encryptedPassword = ec.encryptPassword(new Password("test", "test"), "test");
        assertFalse(encryptedPassword.getValue().equals("test"));
        Password decryptedPassword = ec.decryptPassword(encryptedPassword, "test");
        assertTrue(decryptedPassword.getValue().equals("test"));
    }

    public void testDuplicatePassword() {
        try {
            DBManager db = new DBManager();
            ArrayList<Password> pwords = db.getPasswords();
            assertEquals(0, pwords.size());
            db.addPassword(new Password("test", "test"));
            pwords = db.getPasswords();
            assertEquals(1, pwords.size());
            db.addPassword(new Password("test", "test"));
            fail("Allowed duplicate password");
        } catch (SQLException | ClassNotFoundException e) {
            // swallow exception. this is a pass state
        }
    }
}
