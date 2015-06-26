package passwordgenerator.util;
import java.io.File; //for File class
import java.io.FileNotFoundException; //for FileNotFoundException
import java.util.Scanner; //for Scanner class
import java.util.ArrayList;//for LinkedList class
import java.util.InputMismatchException; //for InputMismatchException
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

public class SimpleFileReader {
	private File in; //file variable
	private Scanner input; //for taking in input from the file
	private ArrayList<String> list; //for holding the input
	private BasicTextEncryptor decryptor;
	private String temp;
	/**
	 * A constructor for <code>SimpleFileReader</code> that takes in a
	 * <code>File</code> as an argument and stores it in a member variable.
	 * 
	 * @param file
	 */
	public SimpleFileReader(File file)
	{
		in = file.getAbsoluteFile();
		String password = "a";
		decryptor = new BasicTextEncryptor();
		decryptor.setPassword(password);
	}

	/**
	 * This method returns an <code>ArrayList<String></code> that contains data collected
	 * from the file being read in
	 * @return 		ArrayList<String> containing data tokens
	 */
	public ArrayList<String> readFile() throws FileNotFoundException, InputMismatchException
	{
		//int count = 0;
		try
		{
			input = new Scanner(in);
			list = new ArrayList<>(); //token holder
			
			while(input.hasNext())
			{
				temp = input.next();
				list.add(decryptor.decrypt(temp)); //add Strings to list
			}
			input.close(); //close the file
		}
		catch(FileNotFoundException e) //catch FileNotFoundException
		{
			throw e;
		}
		catch(InputMismatchException e)//catch non-String cases
		{
			System.out.println("Encountered input that was not of type String");
		}
		catch(EncryptionOperationNotPossibleException enpe)
		{
			System.out.println("An error occurred");
		}
		return list; 
	}




}
