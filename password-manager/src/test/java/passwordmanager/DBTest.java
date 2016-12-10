package passwordmanager;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import passwordmanager.util.DBManager;
import passwordmanager.util.Password;

/**
 *
 * @author BMamba2942
 */
public class DBTest {

    static DBManager testDb;

    public DBTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        testDb = new DBManager("junit");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        testDb.closeDatabase();
        Path path = FileSystems.getDefault().getPath("", "junit.db");
        Files.delete(path);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() throws Exception {
        testDb.clearPasswords();
    }

    @Test
    public void testCreatePassword() throws Throwable {
        Password pass = new Password("test", "password");

        testDb.addPassword(pass);
        Password passRetrieved = testDb.getPassword("test");

        assertEquals(pass, passRetrieved);

    }

    @Test
    public void testRenamePassword() throws Throwable {
        Password original = new Password("test", "password");
        testDb.addPassword(original);
        original = testDb.getPassword(original.getPasswordName());
        testDb.renamePassword(original.getPasswordName(), "newTest");
        Password passRetrieved = testDb.getPassword("newTest");
        // The internal result sets will fail when looking for a password
        // that doesn't exist
        try {
            testDb.getPassword(original.getPasswordName());
            fail("Original password retrieved from backend");
        }
        catch (SQLException s) {
            //This is good!
        }
        assertNotEquals(passRetrieved.getPasswordName(), original.getPasswordName());
        assertEquals(passRetrieved.getPasswordString(), original.getPasswordString());
    }

    @Test
    public void testRemovePassword() throws Throwable {
        Password newP = new Password("test", "password");
        testDb.addPassword(newP);
        Password isSaved = testDb.getPassword(newP.getPasswordName());
        assertNotNull(isSaved);
        testDb.removePassword(isSaved.getPasswordName());
        // The internal result sets will fail when looking for a password
        // that doesn't exist
        try {
            testDb.getPassword(isSaved.getPasswordName());
            fail("Password still existed after a delete");
        }
        catch (SQLException e) {
            //This is good!
        }

    }

    @Test
    public void testUpdatePassword() throws Throwable {
        Password original = new Password("test", "password");
        testDb.addPassword(original);
        original = testDb.getPassword(original.getPasswordName());
        original.setPasswordString("newpassword");
        testDb.updatePassword(original);
        Password passRetrieved = testDb.getPassword("test");
        assertNotEquals(passRetrieved.getPasswordString(), original.getPasswordString());
        assertEquals(passRetrieved.getPasswordName(), original.getPasswordName());
    }

    @Test
    public void testCapsInName() throws Throwable {
        Password capsName = new Password("ALLCAPS", "password");
        testDb.addPassword(capsName);
        Password retrieved = testDb.getPassword(capsName.getPasswordName());
        assertTrue(retrieved.getPasswordName().equals("ALLCAPS"));
    }

    @Test
    public void testSortedNames() throws Throwable {
        Random rand = new Random();
        for (int i = 0; i < 200; ++i) {
            Password p = new Password();
            p.generate(10, "test" + i);
            String temp = p.getPasswordName();
            p.setPasswordName(p.getPasswordString());
            p.setPasswordString(temp);
            testDb.addPassword(p);
        }

        ArrayList<Password> passwords = testDb.getPasswords();
        passwords.sort(new Password.PasswordComparer());
        for (int i = 0; i < passwords.size() - 1; ++i) {
            System.out.println(passwords.get(i).toString());
            int result = new Password.PasswordComparer().compare(passwords.get(i), passwords.get(i + 1));
            assertTrue(result < 0);
        }

    }

}
