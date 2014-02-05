package uk.co.chrisfoxleyevans.RainfallData.DataQueries;

//imports
import org.jfree.data.category.DefaultCategoryDataset;

import uk.co.chrisfoxleyevans.RainfallData.DataStorage.RainfallData;

public class RainfallDataChartDataQueries
{
	//instance variables
	
	//constructor
	public RainfallDataChartDataQueries()
	{
		
	}
	
	//private methods

	//public methods
	//method to get data for all days in month
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param month The month to be used
	 * @return A dataset containing the required data
	 */
	public static DefaultCategoryDataset getDataForMonth(RainfallData[] dataSet, int year, int month)
	{
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		for (int i = 1; i < RainfallDataQueries.getDaysInMonth(year, month) + 1; i++)
		{
			data.addValue(RainfallDataQueries.getRainfallForDay(dataSet, year, month, i), "Days", Integer.toString(i));	
		}
		return data;		
	}

	//method to get data for the average of the months in a year
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return A dataset containing the required data
	 */
	public static DefaultCategoryDataset getDataForAverageOfMonthsInYear(RainfallData[] dataSet, int year)
	{
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		for (int i = 1; i < 13; i++)
		{
			data.addValue(RainfallDataQueries.getAverageRainfallForMonth(dataSet, year, i), "Months", Integer.toString(i));
		}
		return data;
	}

	//method to get data for the average of all the years +- 5 of input year
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return A dataset containing the required data
	 */
	public static DefaultCategoryDataset getDataForAverageOf5YearsAboveAndBelow(RainfallData[] dataSet, int year)
	{
		int index;
		if (year - 5 < RainfallDataQueries.getRainfallDataOldestYear(dataSet))
			index = RainfallDataQueries.getRainfallDataOldestYear(dataSet);
		else
			index = year -5;
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		for (; index < year + 6; index++)
		{
			data.addValue(RainfallDataQueries.getAverageRainfallForYear(dataSet, index), "Years", Integer.toString(index));
		}
		return data;
	}
	
	//method to get data for the average of all the given season for +- 5 of input year
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param season The season that is to be used
	 * @return A dataset containing the required data
	 */
	public static DefaultCategoryDataset getDataForAverageOf5SeasonsAboveAndBelow(RainfallData[] dataSet, int year, int season)
	{
		int index;
		if (year - 5 < RainfallDataQueries.getRainfallDataOldestYear(dataSet))
			index = RainfallDataQueries.getRainfallDataOldestYear(dataSet);
		else
			index = year -5;
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		for (; index < year + 6; index++)
		{
			data.addValue(RainfallDataQueries.getAverageRainfallForSeason(dataSet, index, season), "Years", Integer.toString(index));
		}
		return data;
	}

	//method to get the data for the total of the given month for +- 5 of the input year
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param month The month that is to be used
	 * @return A dataset containing the required data
	 */
	public static DefaultCategoryDataset getDataForTotalOfMonths(RainfallData[] dataSet, int year, int month)
	{
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		for (int i = 1; i < RainfallDataQueries.getDaysInMonth(year, month) + 1; i++)
		{
			data.addValue(RainfallDataQueries.getRainfallForDay(dataSet, year, month, i), "Days", Integer.toString(i));	
		}
		return data;
	}

	//method to get the data for the total of the give year +- 5 of the input year
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return A dataset containing the required data
	 */
	public static DefaultCategoryDataset getDataForTotalof5YearsAboveAndBelow(RainfallData[] dataSet, int year)
	{
		int index;
		if (year - 5 < RainfallDataQueries.getRainfallDataOldestYear(dataSet))
			index = RainfallDataQueries.getRainfallDataOldestYear(dataSet);
		else
			index = year -5;
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		for (; index < year + 6; index++)
		{
			data.addValue(RainfallDataQueries.getTotalRainfallForYear(dataSet, index), "Years", Integer.toString(index));
		}
		return data;
	}
}
