package uk.co.chrisfoxleyevans.RainfallData.GUI;

//imports
import uk.co.chrisfoxleyevans.RainfallData.Charts.RainfallDataChartBuilder;
import uk.co.chrisfoxleyevans.RainfallData.DataQueries.RainfallDataPrinter;
import uk.co.chrisfoxleyevans.RainfallData.DataQueries.RainfallDataQueries;
import uk.co.chrisfoxleyevans.RainfallData.DataStorage.RainfallData;
import uk.co.chrisfoxleyevans.RainfallData.FileIO.RainfallDataFileReader;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;

/**
 * @author Chris Foxley-Evans:
 * 
 * This class is used to create an instance of the GUI for the application
 * the application is controlled from this class calling methods and classes as required
 */
public class RainfallDataGUI extends JFrame implements ActionListener
{
	//instance variables
	private static final long serialVersionUID = 1L;
	
	//data objects
	private RainfallDataPrinter rp;
	private RainfallDataFileReader rf;	
	private RainfallData rd; 
	private RainfallData[] rainData;
	
	//J panels
	private JPanel mainPanel;
	private JPanel averagesPanel;
	private JPanel totalsPanel;
	private JPanel singleDayDataPanel;
	private JPanel monthlyAveragePanel;
	private JPanel yearlyAveragePanel;
	private JPanel seasonalAveragePanel;
	private JPanel monthlyTotalPanel;
	private JPanel yearlyTotalPanel;
	
	//JComboBox
	private JComboBox cboxSingleDayYears;
	private JComboBox cboxSingleDayMonths;
	private JComboBox cboxSingleDayDays;
	private JComboBox cboxMonthlyAverageYears;
	private JComboBox cboxMonthlyAverageMonths;
	private JComboBox cboxYearlyAverageYears;
	private JComboBox cboxSeasonalAverageSeason;
	private JComboBox cboxSeasonalAverageYear;
	private JComboBox cboxMonthlyTotalYears;
	private JComboBox cboxMonthlyTotalMonths;
	private JComboBox cboxYearlyTotalYears;

	//JTextField
	private JTextField tfSingleDayData;
	private JTextField tfMonthlyAverage;
	private JTextField tfYearlyAverage;
	private JTextField tfSeasonalAverage;
	private JTextField tfMonthlyTotal;
	private JTextField tfYearlyTotal;
	
	//JButton
	private JButton btnSingleDayData;
	private JButton btnMonthlyAverage;
	private JButton btnYearlyAverage;
	private JButton btnSeasonalAverage;
	private JButton btnMonthlyTotal;
	private JButton btnYearlyTotal;

	//JLable
	private JLabel lblSingleDay;
	private JLabel lblMonthlyAverage;
	private JLabel lblYearlyAverage;
	private JLabel lblSeasonalAverage;
	private JLabel lblMonthlyTotal;
	private JLabel lblYearlyTotal;
	
	//ChartPanel
	ChartPanel chartPanel;
	
	//constructor
	public RainfallDataGUI()
	{
		//instant data objects
		rd = new RainfallData();
		rp = new RainfallDataPrinter();
		rf = new RainfallDataFileReader("2RainfallDataLanc.txt");
		rainData = rd.storeData(rf.getCleanData());
		
		//instant components
		this.initMainPanel();
		
		//set up average panel panels
		this.initDailyDataPanel();
		this.initMonthlyAveragePanel();
		this.initYearlyAveragePanel();
		this.initSeasonalAveragePanel();
		
		//add average panels to averages panel
		averagesPanel.add(singleDayDataPanel);
		averagesPanel.add(monthlyAveragePanel);
		averagesPanel.add(yearlyAveragePanel);
		averagesPanel.add(seasonalAveragePanel);
		
		//set up total panel panels
		this.initMontlyTotalPanel();
		this.initYearlyTotalPanel();
		
		//add total panels to totals panel
		totalsPanel.add(monthlyTotalPanel);
		totalsPanel.add(yearlyTotalPanel);
		
		//tidy up
		//this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	//private methods
	//panel methods
	private void initMainPanel()
	{
		this.setTitle("Rainfall Data Analyzer");
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.setContentPane(mainPanel);
		this.setLayout(new BorderLayout());
		
		averagesPanel = new JPanel();
		averagesPanel.setLayout(new GridLayout(4,1));
		this.add(BorderLayout.WEST, averagesPanel);
		
		totalsPanel = new JPanel();
		totalsPanel.setLayout(new GridLayout(4,1));
		this.add(BorderLayout.EAST, totalsPanel);
		
		chartPanel = new ChartPanel(RainfallDataChartBuilder.buildChartForVaulesInMonth(rainData, 1974, 1));
		this.add(BorderLayout.CENTER, chartPanel);
		
	}
	
	private void initDailyDataPanel()
	{
		//instant components
		lblSingleDay = new JLabel("Single Day Data:");
		
		tfSingleDayData = new JTextField("Daily Data");
		
		cboxSingleDayYears = new JComboBox();
		this.initYearComboBox(cboxSingleDayYears);
		
		cboxSingleDayMonths = new JComboBox();
		this.initMonthComboBox(cboxSingleDayMonths);
		
		cboxSingleDayDays = new JComboBox();
		this.initDaysComboBox(cboxSingleDayDays);
		
		btnSingleDayData = new JButton("Go");
		btnSingleDayData.addActionListener(this);
		
		//set up daily data panel
		singleDayDataPanel = new JPanel();
		singleDayDataPanel.setLayout(new GridLayout(4,1));
		
		//set up daily data panel date picker panel
		JPanel singleDayDataDatePickerPanel = new JPanel();
		singleDayDataDatePickerPanel.add(cboxSingleDayYears);
		singleDayDataDatePickerPanel.add(cboxSingleDayMonths);
		singleDayDataDatePickerPanel.add(cboxSingleDayDays);

		//add components
		singleDayDataPanel.add(lblSingleDay);
		singleDayDataPanel.add(singleDayDataDatePickerPanel);
		singleDayDataPanel.add(tfSingleDayData);
		singleDayDataPanel.add(btnSingleDayData);
	}

	private void initMonthlyAveragePanel()
	{
		//instant components
		lblMonthlyAverage = new JLabel("Monthly Averages:");
		
		tfMonthlyAverage = new JTextField("Monthly Average");
		
		cboxMonthlyAverageYears = new JComboBox();
		this.initYearComboBox(cboxMonthlyAverageYears);
		
		cboxMonthlyAverageMonths = new JComboBox();
		this.initMonthComboBox(cboxMonthlyAverageMonths);
		
		btnMonthlyAverage = new JButton("Go");
		btnMonthlyAverage.addActionListener(this);
		
		//set up monthly average panel
		monthlyAveragePanel = new JPanel();
		monthlyAveragePanel.setLayout(new GridLayout(4,1));
		
		//set up monthly average panel date picker panel
		JPanel monthlyAverageDatePickerPanel = new JPanel();
		monthlyAverageDatePickerPanel.add(cboxMonthlyAverageYears);
		monthlyAverageDatePickerPanel.add(cboxMonthlyAverageMonths);
		
		//add components
		monthlyAveragePanel.add(lblMonthlyAverage);
		monthlyAveragePanel.add(monthlyAverageDatePickerPanel);
		monthlyAveragePanel.add(tfMonthlyAverage);
		monthlyAveragePanel.add(btnMonthlyAverage);
	}
	
	private void initYearlyAveragePanel()
	{
		//instant components
		lblYearlyAverage = new JLabel("Yearly Averages:");
		
		tfYearlyAverage = new JTextField("Yearly Average");
		
		cboxYearlyAverageYears = new JComboBox();
		this.initYearComboBox(cboxYearlyAverageYears);
		
		btnYearlyAverage = new JButton("Go");
		btnYearlyAverage.addActionListener(this);

		//set up yearly average panel
		yearlyAveragePanel = new JPanel();
		yearlyAveragePanel.setLayout(new GridLayout(4,1));

		//set up yearly average panel date picker panel
		JPanel yearlyAverageDatePickerPanel = new JPanel();
		yearlyAverageDatePickerPanel.add(cboxYearlyAverageYears);

		//add components
		yearlyAveragePanel.add(lblYearlyAverage);
		yearlyAveragePanel.add(yearlyAverageDatePickerPanel);
		yearlyAveragePanel.add(tfYearlyAverage);
		yearlyAveragePanel.add(btnYearlyAverage);	
	}
	
	private void initSeasonalAveragePanel()
	{
		//instant components
		lblSeasonalAverage = new JLabel("Seasonal Averages:");
		
		tfSeasonalAverage = new JTextField("Seasonal Average");
		
		cboxSeasonalAverageSeason = new JComboBox();
		this.initSeasonsComboBox(cboxSeasonalAverageSeason);
		
		cboxSeasonalAverageYear = new JComboBox();
		this.initYearComboBox(cboxSeasonalAverageYear);
		
		btnSeasonalAverage = new JButton("Go");
		btnSeasonalAverage.addActionListener(this);
		
		//set up seasonal average panel
		seasonalAveragePanel = new JPanel();
		seasonalAveragePanel.setLayout(new GridLayout(4,1));
		
		//set up seasonal average date picker panel
		JPanel seasonalAverageDatePickerPanel = new JPanel();
		seasonalAverageDatePickerPanel.add(cboxSeasonalAverageSeason);
		seasonalAverageDatePickerPanel.add(cboxSeasonalAverageYear);
		
		//add components
		seasonalAveragePanel.add(lblSeasonalAverage);
		seasonalAveragePanel.add(seasonalAverageDatePickerPanel);
		seasonalAveragePanel.add(tfSeasonalAverage);
		seasonalAveragePanel.add(btnSeasonalAverage);
	}

	private void initMontlyTotalPanel()
	{
		//instant components
		lblMonthlyTotal = new JLabel("Monthly Totals:");

		tfMonthlyTotal = new JTextField("Monthly Total");

		cboxMonthlyTotalYears = new JComboBox();
		this.initYearComboBox(cboxMonthlyTotalYears);

		cboxMonthlyTotalMonths = new JComboBox();
		this.initMonthComboBox(cboxMonthlyTotalMonths);

		btnMonthlyTotal = new JButton("Go");
		btnMonthlyTotal.addActionListener(this);

		//set up seasonal average panel
		monthlyTotalPanel = new JPanel();
		monthlyTotalPanel.setLayout(new GridLayout(4,1));

		//set up seasonal average date picker panel
		JPanel monthlyTotalDatePickerPanel = new JPanel();
		monthlyTotalDatePickerPanel.add(cboxMonthlyTotalYears);
		monthlyTotalDatePickerPanel.add(cboxMonthlyTotalMonths);

		//add components
		monthlyTotalPanel.add(lblMonthlyTotal);
		monthlyTotalPanel.add(monthlyTotalDatePickerPanel);
		monthlyTotalPanel.add(tfMonthlyTotal);
		monthlyTotalPanel.add(btnMonthlyTotal);

	}

	private void initYearlyTotalPanel()
	{
		//instant components
		lblYearlyTotal = new JLabel("Yearly Totals:");

		tfYearlyTotal = new JTextField("Yearly Total");

		cboxYearlyTotalYears = new JComboBox();
		this.initYearComboBox(cboxYearlyTotalYears);

		btnYearlyTotal = new JButton("Go");
		btnYearlyTotal.addActionListener(this);

		//set up seasonal average panel
		yearlyTotalPanel = new JPanel();
		yearlyTotalPanel.setLayout(new GridLayout(4,1));

		//set up seasonal average date picker panel
		JPanel yearlyTotalDatePickerPanel = new JPanel();
		yearlyTotalDatePickerPanel.add(cboxYearlyTotalYears);

		//add components
		yearlyTotalPanel.add(lblYearlyTotal);
		yearlyTotalPanel.add(yearlyTotalDatePickerPanel);
		yearlyTotalPanel.add(tfYearlyTotal);
		yearlyTotalPanel.add(btnYearlyTotal);
	}
	
	//component methods
	/**
	 * @param cb The JComboBox that is to be set up
	 */
	private void initYearComboBox(JComboBox cb)
	{
		for (int i = RainfallDataQueries.getRainfallDataOldestYear(rainData); i <= RainfallDataQueries.getRainfallDataNewestYear(rainData); i++)
		{
			cb.addItem(i);
		}
	}
	
	/**
	 * @param cb The JComboBox that is to be set up
	 */
	private void initMonthComboBox(JComboBox cb)
	{
		for (int i = 1; i < 13; i++)
		{
			cb.addItem(i);			
		}
	}
	
	/**
	 * @param cb The JComboBox that is to be set up
	 */
	private void initDaysComboBox(JComboBox cb)
	{
		for (int i = 1; i < 32; i++)
		{
			cb.addItem(i);
		}
	}
	
	/**
	 * @param cb The JComboBox that is to be set up
	 */
	private void initSeasonsComboBox(JComboBox cb)
	{
		cb.addItem("Winter");
		cb.addItem("Spring");
		cb.addItem("Summer");
		cb.addItem("Autumn");
	}
	
	private int convertSeasonToInteger()
	{
		int seasonCode = 0;
		if (this.cboxSeasonalAverageSeason.getSelectedItem() == "Winter")
			seasonCode = 1;
		else if (this.cboxSeasonalAverageSeason.getSelectedItem() == "Spring")
			seasonCode = 2;
		else if (this.cboxSeasonalAverageSeason.getSelectedItem() == "Summer")
			seasonCode = 3;
		else if (this.cboxSeasonalAverageSeason.getSelectedItem() == "Autumn")
			seasonCode = 4;
		return seasonCode;
	}
	
	//public methods
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnSingleDayData)
		{
			tfSingleDayData.setText(rp.printRainfallDataForDay(rainData, 
					(Integer) cboxSingleDayYears.getSelectedItem(), (Integer) cboxSingleDayMonths.getSelectedItem(),
					(Integer) cboxSingleDayDays.getSelectedItem()));
			
			chartPanel.setChart(RainfallDataChartBuilder.buildChartForVaulesInMonth(rainData, (Integer) this.cboxSingleDayYears.getSelectedItem(), 
					(Integer) this.cboxSingleDayMonths.getSelectedItem()));
		}
					
		else if (e.getSource() == this.btnMonthlyAverage)
		{
			tfMonthlyAverage.setText(rp.printRainfallDataAverageForMonth(rainData, 
					(Integer) cboxMonthlyAverageYears.getSelectedItem(), (Integer) cboxMonthlyAverageMonths.getSelectedItem()));
			
			chartPanel.setChart(RainfallDataChartBuilder.buildChatForAveragesOfMonthsInYear(rainData, 
					(Integer) this.cboxMonthlyAverageYears.getSelectedItem())); 
		}
			
		else if (e.getSource() == this.btnYearlyAverage)
		{
			tfYearlyAverage.setText(rp.printRainfallDataAverageForYear(rainData, 
					(Integer) cboxYearlyAverageYears.getSelectedItem()));
			
			chartPanel.setChart(RainfallDataChartBuilder.buildChartForAveragesOfYears5AboveAndBelow(rainData, 
					(Integer) this.cboxYearlyAverageYears.getSelectedItem()));
		}
		
		else if (e.getSource() == this.btnSeasonalAverage)
		{
			tfSeasonalAverage.setText(rp.printRainfallDataAverageForSeason(rainData, (Integer) this.cboxSeasonalAverageYear.getSelectedItem(),
					this.convertSeasonToInteger()));
			
			chartPanel.setChart(RainfallDataChartBuilder.buildChartForAveragesOfSeasons5AboveAndBelow(rainData,
					(Integer) this.cboxSeasonalAverageYear.getSelectedItem(), this.convertSeasonToInteger()));
		}
		
		else if (e.getSource() == this.btnMonthlyTotal)
		{
			tfMonthlyTotal.setText(rp.printRainfallDataTotalForMonth(rainData, (Integer) this.cboxMonthlyTotalYears.getSelectedItem(),
					(Integer) this.cboxMonthlyTotalMonths.getSelectedItem()));
			
			chartPanel.setChart(RainfallDataChartBuilder.buildChartForTotalsOfMonths(rainData, 
					(Integer) this.cboxMonthlyTotalYears.getSelectedItem(), (Integer) this.cboxMonthlyTotalMonths.getSelectedItem()));
		}
		
		else if (e.getSource() == this.btnYearlyTotal)
		{
			tfYearlyTotal.setText(rp.printRainfallDataTotalForYear(rainData, (Integer) this.cboxYearlyTotalYears.getSelectedItem()));
			
			chartPanel.setChart(RainfallDataChartBuilder.buildchartForTotalsOfYears(rainData, 
					(Integer) this.cboxYearlyTotalYears.getSelectedItem()));
			
		}
	}
}
