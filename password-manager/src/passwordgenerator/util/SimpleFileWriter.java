package passwordgenerator.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//to output

public class SimpleFileWriter {
	private FileWriter writing;
	private File out;
	private ArrayList<Password> passwords;
	/**
	 * A constructor for <code>SimpleFileWriter</code> that takes in a 
	 * <code>File</code> and two sets of <code>LinkedList<String></code>
	 * 
	 * @param file				The file to output to
	 * @param readDataNames		The data determined to be names
	 * @param readDataEmail		The data determined to be emails
	 */
	public SimpleFileWriter(File file, ArrayList<Password> passwords)
	{
		try
		{
			out = file.getAbsoluteFile();
			this.passwords = passwords;
			writing = new FileWriter(out, true);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public SimpleFileWriter(File file)
	{
		try
		{
			out = file.getAbsoluteFile();
			writing = new FileWriter(out, false); // Always overwrite the file, since the data for it is stored in memory
		}
		catch(Exception e)
		{}
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
			savePassword(passwords.get(i).getPasswordName(), passwords.get(i).getPasswordString());
		try {
			writing.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}
