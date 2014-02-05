package uk.co.chrisfoxleyevans.RainfallData.FileIO;

//imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * @author Chris Foxley-Evans: 
 * 
 * This class is used to open the file and read the content
 */ 
public class RainfallDataFileReader 
{
	//instance variables
	private String filename;

	//constructor
	/**
	 * @param fn The path to the file
	 */
	public RainfallDataFileReader(String fn ) 
	{
		filename = fn;
	}

	//private methods
	//reads in whole file
	/**
	 * @return A String that contains all of the file text
	 */
	private String getAllData() 
	{
		try 
		{
			System.out.print("Opening file: " + filename + "... \n");
			
			BufferedReader reader = new BufferedReader( new FileReader (filename));
		    String line  = null;
		    StringBuilder stringBuilder = new StringBuilder();
		    String ls = System.getProperty("line.separator");
		    while( ( line = reader.readLine() ) != null ) {
		        stringBuilder.append( line );
		        stringBuilder.append( ls );
		    }
		    
		    System.out.println("Closing file: " + filename + "... \n");
		    return stringBuilder.toString();	
		}
		catch (IOException e) 
		{
			System.out.println("I/O Error reading file" + e.toString());
			return ("readFile():Error");
		}
		catch (Exception e) 
		{
			System.out.println("Error reading file" + e.toString());
			return ("readFile():Error");
		}

	}

	//split file into lines
	/**
	 * @return A String[] that holds the data as individual lines
	 */
	private String[] getLinesOfData() 
	{
		String dataStr = getAllData();
		String[] linesOfData = dataStr.split("\n");
		return linesOfData;		
	}

	//clean up input	
	/**
	 * @param data The String[] containing lines of data
	 * @return A String[] with the data with any extra spaces removed
	 */
	private String[] cleanInput(String[] data)
	{
		String[] cleanData = new String[data.length];

		for (int i = 0; i < data.length; i++)
		{
			cleanData[i] = data[i].replaceAll("\\s+", " ");
		}
		return cleanData;
	}

	//extract useful data
	/**
	 * @param data The String[] containing lines of clean data
	 * @return Only lines that match the expected format
	 */
	private String[] extractData(String[] data)
	{
		final int EXPECTED_ITEMS = 33;		

		Vector<String> v = new Vector<String>();

		for (int i = 0; i<data.length; i++ ) 
		{			
			String[] temp = data[i].split(" ");
			if ( temp.length == EXPECTED_ITEMS ) 			
				v.add(data[i]);
		}

		String usefulData[] = new String[v.size()];
		for ( int i=0; i<v.size(); i++ ) {
			usefulData[i] = v.elementAt(i);
		}		
		return usefulData;
	}

	//public methods
	//perform all actions at once
	/**
	 * @return A String[] containing the data from the fileReturns all of the data in a usable format one array index holds one line from the file
	 */
	public String[] getCleanData()
	{
		String [] data = this.getLinesOfData();
		data = this.cleanInput(data);
		data = this.extractData(data);
		return data;
	}
}