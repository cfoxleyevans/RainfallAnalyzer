package uk.co.chrisfoxleyevans.RainfallData.Charts;

//imports
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;

import uk.co.chrisfoxleyevans.RainfallData.DataQueries.RainfallDataChartDataQueries;
import uk.co.chrisfoxleyevans.RainfallData.DataQueries.RainfallDataQueries;
import uk.co.chrisfoxleyevans.RainfallData.DataStorage.RainfallData;

public class RainfallDataChartBuilder
{
	//instance variables
	
	//constructor
	public RainfallDataChartBuilder()
	{
		
	}
	
	//private methods
	
	//public methods
	//build bar chart for values in month
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param month The month to be used
	 * @return a JFreeChart built using the data provided
	 */
	public static JFreeChart buildChartForVaulesInMonth(RainfallData[] dataSet, int year, int month)
	{
		String title = "Rainfall For: " + year + "\\" + month + "."; 
		JFreeChart chart = ChartFactory.createBarChart(
	            title, "Days", "Value in mm", RainfallDataChartDataQueries.getDataForMonth(dataSet, year, month), 
	            PlotOrientation.VERTICAL, true, true, false);		
		return chart;
	}
	
	//build bar chart for average of months in year
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return a JFreeChart built using the data provided
	 */
	public static JFreeChart buildChatForAveragesOfMonthsInYear(RainfallData[] dataSet, int year)
	{
		String title = "Average Rainfall For: " + year + "."; 
		JFreeChart chart = ChartFactory.createBarChart(
	            title, "Month", "Value in mm", RainfallDataChartDataQueries.getDataForAverageOfMonthsInYear(dataSet, year),
	            PlotOrientation.VERTICAL, true, true, false);		
		return chart;
	}

	//build bar chart for average +-5 years of input year
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @return a JFreeChart built using the data provided
	 */
	public static JFreeChart buildChartForAveragesOfYears5AboveAndBelow(RainfallData[] dataSet, int year)
	{
		String title = "Average Rainfall For: " + year + " +- 5 Years."; 
		JFreeChart chart = ChartFactory.createBarChart(
	            title, "Year", "Value in mm", RainfallDataChartDataQueries.getDataForAverageOf5YearsAboveAndBelow(dataSet, year),
	            PlotOrientation.VERTICAL, true, true, false);
		return chart;
	}
	
	//build bar chart for average seasons
	/**
	 * @param dataSet The data set to be queried
	 * @param year The year to be used
	 * @param season The season that is to be used
	 * @return a JFreeChart built using the data provided
	 */
	public static JFreeChart buildChartForAveragesOfSeasons5AboveAndBelow(RainfallData[] dataSet, int year, int season)
	{
		String title = "Average Rainfall For : " + RainfallDataQueries.getSeasonString(season) + " " + year + " +- 5 Years."; 
		JFreeChart chart = ChartFactory.createBarChart(
	            title, "Year", "Value in mm", RainfallDataChartDataQueries.getDataForAverageOf5SeasonsAboveAndBelow(dataSet, year, season),
	            PlotOrientation.VERTICAL, true, true, false);
		return chart;
	}
	
	//build bar chart for the totals of months
	/**
	 * @param dataSet The data set that is to be queried
	 * @param year The year that is to be used 
	 * @param month The month that is to be used
	 * @return a JFreeChart built using the data provided
	 */
	public static JFreeChart buildChartForTotalsOfMonths(RainfallData[] dataSet, int year, int month)
	{
		String title = " Total Rainfall For: " + year + "\\" + month;  
		JFreeChart chart = ChartFactory.createBarChart(
	            title, "Days", "Value in mm", RainfallDataChartDataQueries.getDataForTotalOfMonths(dataSet, year, month),
	            PlotOrientation.VERTICAL, true, true, false);
		return chart;
	}
	
	//build bar chart for the totals of years
	/**
	 * @param dataSet The data set that is to be queried
	 * @param year The year that is to be used
	 * @return a JFreeChart built using the data provided
	 */
	public static JFreeChart buildchartForTotalsOfYears(RainfallData[] dataSet, int year)
	{
		String title = " Total Rainfall For: " + year + " +- 5 Years.";  
		JFreeChart chart = ChartFactory.createBarChart(
	            title, "Year", "Value in mm", RainfallDataChartDataQueries.getDataForTotalof5YearsAboveAndBelow(dataSet, year),
	            PlotOrientation.VERTICAL, true, true, false);
		return chart;
	}
}