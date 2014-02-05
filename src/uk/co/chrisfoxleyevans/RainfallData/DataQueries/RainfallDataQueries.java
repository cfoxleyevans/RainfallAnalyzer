package uk.co.chrisfoxleyevans.RainfallData.DataQueries;

import uk.co.chrisfoxleyevans.RainfallData.DataStorage.RainfallData;

/**
 * @author Chris Foxley-Evans:
 * 
 * This class is used to query the stored data
 */
public class RainfallDataQueries
{
	//instance variables
	private final static int MONTHS_IN_SEASON = 3;
	
	//constructor
	public RainfallDataQueries()
	{	
		
	}
	
	//private methods
	//method to calculate number of days in year
	/**
	 * @param year The year to be used
	 * @return The number of days in the year
	 */
	private static int numDaysInYear(int year)
	{
		int days = 0;
		if(isLeapYear(year))
			days = 366;
		else 
			days =365;
		return days;
	}
	
	//method to check for leap year
	/**
	 * @param year The year to be used
	 * @return true if year is leap false if not
	 */
	private static boolean isLeapYear(int year)
	{
		if (year % 400 == 0) 
		   return true;
		else if (year % 100 == 0)
		   return false;
		else if (year % 4 == 0) 
		   return true;
		else
		   return false;
	}
	
	//public methods
	//method to convert season code to string
	/**
	 * @param season the code of the season being used
	 * @return the corresponding season as a String
	 */
	public static String getSeasonString(int season)
	{
		String seasonString = "";
		switch(season)
		{	
		case 1: seasonString = "Winter"; break;
		case 2: seasonString = "Spring"; break;
		case 3: seasonString = "Summer"; break;
		case 4: seasonString = "Autumn"; break;
		}
		return seasonString;
	}
	
	//method to calculate the days in the month for averages
	/**
	 * @param year The year to be used
	 * @param month the month to be used
	 * @return The Number of days in the given month
	 */
	public static int getDaysInMonth(int year, int month)
	{
		int days = 0;
		switch (month) 
		{
		case 1:  days = 31; break;
		case 2: 
		{
			if(RainfallDataQueries.isLeapYear(year))
			{
				days = 29; 
				break;
			}
			else
			{
				days = 28; 
				break;
			}
		}
		case 3:  days = 31; break;
		case 4:  days = 30; break;
		case 5:  days = 31; break;
		case 6:  days = 30; break;
		case 7:  days = 31; break;
		case 8:  days = 31; break;
		case 9:  days = 30; break;
		case 10: days = 31; break;
		case 11: days = 30; break;
		case 12: days = 31; break;
		}
		return days;
	}

	//method to get the oldest year in the data
	/**
	 * @param dataSet The data set to be queried
	 * @return The oldest year in the DataSet
	 */
	public static int getRainfallDataOldestYear(RainfallData[] dataSet)
	{
		int oldestYear = 3000;
		for (int i = 0 ; i < dataSet.length; i++)
		{
			if (dataSet[i].getYear() < oldestYear)
				oldestYear = dataSet[i].getYear();
		}
		return oldestYear;
	}
	
	//method to get most recent year in data
	/**
	 * @param dataSet The data set to be queried
	 * @return The newest year in the DataSet
	 */
	public static int getRainfallDataNewestYear(RainfallData[] dataSet)
	{
		int newestYear = 0;
		for (int i = 0 ; i < dataSet.length; i++)
		{
			if (dataSet[i].getYear() > newestYear)
				newestYear = dataSet[i].getYear();
		}
		return newestYear;
	}
	
	//method to get rainfall value for specific day
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param month The month to be used
	 * @param day The day to be used
	 * @return The amount of rainfall on the given day
	 */
	public static float getRainfallForDay(RainfallData[] dataSet, int year, int month, int day)
	{
		float value = 0;
		for (int i = 0; i < dataSet.length; i++)
		{
			if (dataSet[i].getYear() == year && dataSet[i].getMonth() == month)
			{				
				if (dataSet[i].getData()[day - 1] == -99.99F)
				{
					value = -1;
					break;
				}
				else
				{
					value = dataSet[i].getData()[day - 1];
					break;
				}
			}
		}
		return value;
	}
	
	//total methods
	//method to get total rainfall for month
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param month The month to be used
	 * @return The total rainfall for the given month
	 */
	public static float getTotalRainfallForMonth(RainfallData[] dataSet, int year, int month)
	{
		float total = 0;
		for (int i = 0; i < dataSet.length; i++)
		{
			if (dataSet[i].getYear() == year && dataSet[i].getMonth() == month)
			{
				for (int j = 0; j < dataSet[i].getData().length; j++)
				{
					if (dataSet[i].getData()[j] == -99.99F)
						total += 0;
					else
						total += dataSet[i].getData()[j];
				}
			}
		}
		return total;		
	}
	
	//method to get totalRainfallForYear
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return The total rainfall for the given year
	 */
	public static float getTotalRainfallForYear(RainfallData[] dataSet, int year)
	{
		float total = 0;
		for (int i = 1; i < 13; i++)
		{
			total += RainfallDataQueries.getTotalRainfallForMonth(dataSet, year, i);
		}
		return total;
	}
	
	//method to get total rainfall for winter
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return The total rainfall for the winter of the given year
	 */
	public static float getTotalRainfallForWinter(RainfallData[] dataset, int year)
	{
		float total  = RainfallDataQueries.getAverageRainfallForMonth(dataset, year, 12);
		total+= RainfallDataQueries.getAverageRainfallForMonth(dataset, year + 1, 1);
		total += RainfallDataQueries.getAverageRainfallForMonth(dataset, year + 1, 2);
		return total;		
	}
	
	//method to get average rainfall in spring
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return The total rainfall for the spring of the given year
	 */
	public static float getTotalRainfallForSpring(RainfallData[] dataset, int year)
	{
		float total  = RainfallDataQueries.getAverageRainfallForMonth(dataset, year, 3);
		total+= RainfallDataQueries.getAverageRainfallForMonth(dataset, year, 4);
		total += RainfallDataQueries.getAverageRainfallForMonth(dataset, year, 5);
		return total;		
	}
	
	//method to get average rainfall in summer
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return The total rainfall for the summer of the given year
	 */
	public static float getTotalRainfallForSummer(RainfallData[] dataset, int year)
	{
		float total  = RainfallDataQueries.getAverageRainfallForMonth(dataset, year, 6);
		total+= RainfallDataQueries.getAverageRainfallForMonth(dataset, year, 7);
		total += RainfallDataQueries.getAverageRainfallForMonth(dataset, year, 8);
		return total;		
	}
	
	//method to get average rainfall in autumn
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return The total rainfall for the autumn of the given year
	 */
	public static float getTotalRainfallForAutumn(RainfallData[] dataset, int year)
	{
		float total  = RainfallDataQueries.getAverageRainfallForMonth(dataset, year, 9);
		total+= RainfallDataQueries.getAverageRainfallForMonth(dataset, year, 10);
		total += RainfallDataQueries.getAverageRainfallForMonth(dataset, year, 11);
		return total;		
	}
		
	//average methods
	//method to get average rainfall for month
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param month The month to be used
	 * @return The total rainfall for the given month
	 */
	public static float getAverageRainfallForMonth(RainfallData[] dataSet, int year, int month)
	{
		return RainfallDataQueries.getTotalRainfallForMonth(dataSet, year, month)/RainfallDataQueries.getDaysInMonth(year, month);
	}
	
	//method to calculate the average rainfall for year
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return The average rainfall for the given year
	 */
	public static float getAverageRainfallForYear(RainfallData[] dataSet, int year)
	{
		float average = 0;
		for (int i = 0; i < 12; i++)
		{
			average += RainfallDataQueries.getTotalRainfallForMonth(dataSet, year, i + 1);
		}
		return average/numDaysInYear(year);
	}

	//method to calculate the average rainfall for a given season

	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param season the season that is to be used
	 * @return The average rainfall for the given season
	 */
	public static float getAverageRainfallForSeason(RainfallData[] dataSet, int year, int season)
	{
		float average = 0;
		switch (season)
		{
			case 1:	average = RainfallDataQueries.getTotalRainfallForWinter(dataSet, year); break;
			case 2: average = RainfallDataQueries.getTotalRainfallForSpring(dataSet, year); break;
			case 3: average = RainfallDataQueries.getTotalRainfallForSummer(dataSet, year); break;
			case 4: average = RainfallDataQueries.getTotalRainfallForAutumn(dataSet, year); break;
		}
		return average/MONTHS_IN_SEASON;
	}
}