package passwordgenerator.util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;



public class SimpleFileProcessor {
	FileWriter fileWrite;
	File file;
	public ArrayList<String> process()
	{
		ArrayList<String> list = null;
		SimpleFileReader read; //Class that reads the file
		 

		Boolean fileExisted;


		try
		{
			file = new File("testfile");
			read = new SimpleFileReader(file);
			list = read.processFile();
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
