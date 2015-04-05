package passwordgenerator.util;
import java.io.File; //for File class
import java.util.InputMismatchException; //for InputMismatchException
import java.util.Scanner; //for Scanner class
import java.util.ArrayList; //for LinkedList class
import java.io.FileNotFoundException;
import java.io.FileWriter;


public class SimpleFileProcessor {
	FileWriter fileWrite;
	File file;
	public ArrayList<String> process()
	{
		Scanner processName, //Scanners that process names
		processEmail; //and emails, respectively

		ArrayList<String> list = null, //Linked lists hold the raw data
				names, //data that is determined as a name
				email; //data that is determined as an email
		SimpleFileReader read; //Class that reads the file
		
		String outName; 

		Boolean fileExisted;


		try
		{
			file = new File("testfile");
			read = new SimpleFileReader(file);
			list = read.processFile();
			names = new ArrayList<>();
			email = new ArrayList<>();
			fileExisted = true;
		}
		catch(FileNotFoundException e)
		{
			try 
			{
				fileWrite = new FileWriter("testfile");
				fileWrite.close();
			}
			catch(Exception f){}
			fileExisted = false;
		}

		return fileExisted ? list : new ArrayList<String>();
	}
	
	public File getFile()
	{
		return this.file;
	}
	


}
