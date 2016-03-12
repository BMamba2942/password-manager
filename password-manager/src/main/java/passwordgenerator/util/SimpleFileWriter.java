package passwordgenerator.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jasypt.util.text.BasicTextEncryptor;


public class SimpleFileWriter {
	private FileWriter writing;
	private File out;
	private ArrayList<Password> passwords;
	private BasicTextEncryptor encryptor;

	/**
	 * A constructor for <code>SimpleFileWriter</code> that takes in a 
	 * <code>File</code>
	 * 
	 * @param file				The file to output to
	 */
	public SimpleFileWriter(File file, ArrayList<Password> passwords)
	{
		try
		{
			out = file.getAbsoluteFile();
			this.passwords = passwords;
			writing = new FileWriter(out, false);
			String password = "a";
			encryptor = new BasicTextEncryptor();
			encryptor.setPassword(password);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private void swap(int index1, int index2)
	{
		Password temp = passwords.get(index1);
		passwords.set(index1, passwords.get(index2));
		passwords.set(index2, temp);
	}

	public SimpleFileWriter(File file) throws IOException
	{
		try
		{
			out = file.getAbsoluteFile();
			writing = new FileWriter(out, false); // Always overwrite the file, since the data for it is stored in memory
		}
		catch(IOException e)
		{
			throw e;
		}
	}

	public void savePassword(String name, String password)
	{
		try{
			writing.write(name + " " + password + "\n");
		}catch(Exception e){e.printStackTrace();}
	}

	public void savePasswords()
	{
		for(int i = 0; i < passwords.size(); i++)
			savePassword(encryptor.encrypt(passwords.get(i).getPasswordName()), 
				encryptor.encrypt(passwords.get(i).getPasswordString()));
		try {
			writing.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}
