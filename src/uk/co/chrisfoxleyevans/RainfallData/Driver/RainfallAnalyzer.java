package uk.co.chrisfoxleyevans.RainfallData.Driver;

import uk.co.chrisfoxleyevans.RainfallData.GUI.RainfallDataGUI;

/**
 * @author Chris Foxley-Evans:
 * 
 * This class is used to start the application and instant an instance of the GUI 
 * which will then take over execution of the program
 */
public class RainfallAnalyzer
{
	/**
	 * @param args Arguments passed to the program
	 */
	public static void main(String args[])
	{
		@SuppressWarnings("unused")
		RainfallDataGUI rg = new RainfallDataGUI();	
	}
}