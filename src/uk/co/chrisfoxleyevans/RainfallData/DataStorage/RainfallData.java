package uk.co.chrisfoxleyevans.RainfallData.DataStorage;

//imports
import java.util.Vector;

/**
 * @author Chris Foxley-Evans:
 * 
 * This class is used to construct objects to hold the data and store it
 */
public class RainfallData
{
	//instance variables
	private int year;
	private int month;	
	private float[] data;
	
	//constructor
	public RainfallData()
	{
		year = 0;
		month = 0;
		data = new float[31];
	}

	//private methods
	
	//public methods
	//store data in array of RainfallData objects
	/**
	 * @param cleanDataSet a data set that has been obtained from the file
	 * @return A RainfallData[] that contains the stored data
	 */ 
	public RainfallData[] storeData(String[] cleanDataSet)
	{
		//dont know how many RainfallData objects will be needed
		Vector<RainfallData> v = new Vector<RainfallData>();
		
		//print message to console
		System.out.println("Storing Rainfall Data...");
		
		//loop through data storing each line in RainfallData object
		for (int i = 0; i < cleanDataSet.length; i++)
		{
			//temp RainfallData object
			RainfallData rd = new RainfallData();
			
			String[] lineOfData = cleanDataSet[i].split(" ");
			rd.year = Integer.parseInt(lineOfData[0]);
			rd.month = Integer.parseInt(lineOfData[1]);
			
			for (int j = 2; j < lineOfData.length; j++)
				rd.data[j-2] = Float.parseFloat(lineOfData[j]);
			
			v.add(rd);
		}
		
		RainfallData[] data = new RainfallData[v.size()];
		for ( int i=0; i<v.size(); i++ ) 
			data[i] = v.elementAt(i);
		
		System.out.println("Rainfall Data Stored\n");
		
		return data;
	}

	//get year of object
	/**
	 * @return Objects year
	 */
	public int getYear()
	{
		return this.year;
	}
	
	//got month of object
	/**
	 * @return Objects month
	 */
	public int getMonth()
	{
		return this.month;
	}
	
	//get data of object
	/**
	 * @return Objects data
	 */
	public float[] getData()
	{
		return this.data;
	}
}