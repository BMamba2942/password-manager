package passwordmanager.util;

import passwordmanager.models.Password;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author BMamba2942
 */
public class DBManager {

    Connection sqlConnection = null;

    public DBManager() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            sqlConnection = DriverManager.getConnection("jdbc:sqlite::memory:");
            this.createTable();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }
    }

    public DBManager(String filename) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:" + filename);
            this.createTable();
        }
        catch (SQLException | ClassNotFoundException e) {
            throw e;
        }
    }

    private void createTable() throws SQLException {
        Statement stmt = null;
        String table = "CREATE TABLE IF NOT EXISTS passwords (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME VARCHAR(50) NOT NULL UNIQUE, PASSWORD VARCHAR(120) NOT NULL)";
        try {
            stmt = sqlConnection.createStatement();
            stmt.executeUpdate(table);
            stmt.close();
        }
        catch (SQLException e) {
            throw e;
        }
    }

    public void addPassword(Password password) throws SQLException {
        PreparedStatement insert;
        String query = "INSERT INTO passwords (NAME, PASSWORD) VALUES (?, ?)";
        try {
            insert = this.sqlConnection.prepareStatement(query);
            insert.setString(1, password.getName());
            insert.setString(2, password.getValue());
            insert.executeUpdate();
        }
        catch (SQLException e) {
            throw e;
        }
        insert = this.sqlConnection.prepareStatement(query);
    }

    public String getPassword(String name) throws SQLException {
        ResultSet results;
        PreparedStatement getPassword;
        String query = "SELECT password from passwords where name = \"?\"";
        getPassword = this.sqlConnection.prepareStatement(query);
        getPassword.setString(0, name);
        results = getPassword.executeQuery();
        return results.getString("NAME");
    }

    public ArrayList<Password> getPasswords() throws SQLException {
        ResultSet results;
        PreparedStatement getPasswords;
        ArrayList<Password> passwords = new ArrayList<>();
        String query = "SELECT * FROM passwords as p ORDER by p.name";
        getPasswords = this.sqlConnection.prepareStatement(query);
        results = getPasswords.executeQuery();

        while (results.next()) {
            String name = results.getString("NAME");
            String password = results.getString("PASSWORD");
            Password row = new Password(name, password);
            passwords.add(row);
        }

        return passwords;
    }

    public void removePassword(String name) throws SQLException {
        PreparedStatement removePassword;
        String query = "DELETE FROM passwords where name= ?";
        removePassword = this.sqlConnection.prepareStatement(query);
        removePassword.setString(1, name);
        removePassword.executeUpdate();
    }

    public void renamePassword(String oldName, String newName) throws SQLException {
        PreparedStatement renamePassword;
        String query = "UPDATE passwords set name = ? where name = ?";
        renamePassword = this.sqlConnection.prepareStatement(query);
        renamePassword.setString(1, newName);
        renamePassword.setString(2, oldName);
        renamePassword.executeUpdate();
    }

}
