package uk.co.chrisfoxleyevans.RainfallData.DataQueries;

//imports
import java.text.DecimalFormat;

import uk.co.chrisfoxleyevans.RainfallData.DataStorage.RainfallData;

/**
 * @author Chris Foxley-Evans:
 * 
 * This class is used to format and print the results of queries on the data
 */
public class RainfallDataPrinter
{
	//instance variables
	DecimalFormat df;
	
	//constructor
	public RainfallDataPrinter()
	{
		df = new DecimalFormat("##.##");
	}
	
	//private methods
	
	//public methods
	//prints out the data of a given date
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param month The month to be used
	 * @param day The day to be used
	 * @return A String containing the results of the query
	 */
	public String printRainfallDataForDay(RainfallData[] dataSet, int year, int month, int day)
	{
		return "Rainfall for: " + year + "\\" + month + "\\" + day + " = " +
				df.format(RainfallDataQueries.getRainfallForDay(dataSet, year, month, day)) + "mm";
	}

	//prints out average rainfall for a month
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param month The month to be used
	 * @return A String containing the results of the query
	 */
	public String printRainfallDataAverageForMonth(RainfallData[] dataSet, int year, int month)
	{
		return "Average for: " + year + "\\" + month +": "  + 
				df.format(RainfallDataQueries.getAverageRainfallForMonth(dataSet, year, month)) + "mm";
	}

	//prints out average rainfall for a year	
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return A String containing the results of the query
	 */
	public String printRainfallDataAverageForYear(RainfallData[] dataSet, int year)
	{
		return "Average for: " + year + ": " + 
				df.format(RainfallDataQueries.getAverageRainfallForYear(dataSet, year)) + "mm";
	}

	//prints out average rainfall for a given season
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param season the season that is to be used
	 * @return A String containing the results of the query
	 */
	public String printRainfallDataAverageForSeason(RainfallData[] dataSet, int year, int season)
	{
		return "Average for: " + RainfallDataQueries.getSeasonString(season) + " " + year + ": " +
				df.format(RainfallDataQueries.getAverageRainfallForSeason(dataSet, year, season)) + "mm";
	}

	//prints out total amount of rainfall for a month
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param month The month to be used
	 * @return A String containing the results of the query
	 */
	public String printRainfallDataTotalForMonth(RainfallData[] dataSet, int year,int month)
	{
		return "Total for: " + year + "\\" + month +": "  +
				df.format(RainfallDataQueries.getTotalRainfallForMonth(dataSet, year, month)) + "mm";
	}

	//prints out total amount of rainfall for a year
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return A String containing the results of the query
	 */
	public String printRainfallDataTotalForYear(RainfallData[] dataSet, int year)
	{
		return "Total for: " + year + " " +
				df.format(RainfallDataQueries.getTotalRainfallForYear(dataSet, year)) + "mm";
	}
}	