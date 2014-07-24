package passwordgenerator.util;
import java.io.File; //for File class
import java.util.InputMismatchException; //for InputMismatchException
import java.util.Scanner; //for Scanner class
import java.util.ArrayList; //for LinkedList class
import java.io.FileNotFoundException;
import java.io.FileWriter;


public class SimpleFileProcessor {
	//public ArrayList<String> process(String[] args)
        public ArrayList<String> process()
	{
		Scanner processName, //Scanners that process names
		processEmail; //and emails, respectively
		                                   
		ArrayList<String> list = null, //Linked lists hold the raw data
		names, //data that is determined as a name
		email; //data that is determined as an email
		SimpleFileReader read; //Class that reads the file
		//SimpleFileWriter writer; //Class that writes the file
		String outName; //file name for the html output file
                
                Boolean fileExisted;
                
                /*
		if(args.length < 1) //Check that there is an argument
		{
			System.out.println("Please indicate a file name as an argument");
			System.out.println("Terminating program.");
			System.exit(1);
		}
                */
                
                
                try
                {
                    read = new SimpleFileReader(new File("testfile"));
                    list = read.processFile();
                    names = new ArrayList<>();
                    email = new ArrayList<>();
                    fileExisted = true;
                }
                catch(FileNotFoundException e)
                {
                    try 
                    {
                        FileWriter file = new FileWriter("testfile");
                        file.close();
                    }
                    catch(Exception f){}
                    fileExisted = false;
                }
                
                
                /*
		read = new SimpleFileReader(new File("C:\\Users\\Joseph\\Documents\\NetBeansProjects\\PasswordGenerator\\src\\testfile"));
		list = read.processFile();
		names = new ArrayList<String>();
		email = new ArrayList<String>();
                */
                
		
		
		
                
                return fileExisted ? list : new ArrayList<String>();
        }
		
		//outName = args[0].substring(0, args[0].indexOf('.'));
		//writer = new SimpleFileWriter(new File(outName + ".html"), names, email);
		
		
	    //writer.writeHTML();
		
	
}
