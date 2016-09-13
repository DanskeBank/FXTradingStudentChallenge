package programmeringsstuga;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class PlotDemo {
	
	public static void main(String[] args){
		//Read data
		String path = "data/ticks/quotes_sample.txt";
		TicksReader tr = new TicksReader(path);
		
		//Plot data
		Iterator<Tick> data = tr.iterator();
		TimeSeriesDemo demo = new TimeSeriesDemo("Title Demo",data);
	    demo.pack( );         
	    RefineryUtilities.positionFrameRandomly(demo); 
	    demo.setVisible(true);
	}
	
	
	private static class TimeSeriesDemo extends ApplicationFrame {
		
		   public TimeSeriesDemo(final String title,Iterator<Tick> data) {
		      super(title);
		      final XYDataset dataset = createDataset (data); 
		      final JFreeChart chart = createChart(dataset);
		      chart.setTitle(title);
		      final ChartPanel chartPanel = new ChartPanel(chart);         
		      chartPanel.setPreferredSize(new java.awt.Dimension(560,370));         
		      chartPanel.setMouseZoomable(true,false);         
		      setContentPane(chartPanel);
		   }
	   
		   
		   private XYDataset createDataset(Iterator<Tick> data) {
			   
			   //Name of data and time resolution
			   TimeSeries bidSeries = new TimeSeries("Bid/1000000",Millisecond.class);
			   Millisecond currentMs = null; //ms since epoch
			  
			   SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS"); 
			   Date tickTimestamp = null;
			   
			   Tick tick = null;
			  
			   double bid = 0.0;
			   
			   while(data.hasNext()) {
				   tick = (Tick) data.next();
				   try {
					   tickTimestamp = format.parse(tick.getDate());
					   currentMs = new Millisecond(tickTimestamp);
				   }catch(Throwable t) {
					   System.out.println("Failed to parse date string");
				   }
				   bid = tick.getBidPrice();
				   
				   bidSeries.addOrUpdate(currentMs, new Double(bid));
			   }			   
			   
			   
			   return new TimeSeriesCollection(bidSeries);
			   	   
		   }
		   
		   private JFreeChart createChart(final XYDataset dataset) {
		      return ChartFactory.createTimeSeriesChart(             
		      "Time Series Brexit Sample", 
		      "Time",              
		      "BID",              
		      dataset,             
		      true,              
		      false,              
		      false);
		   }
		}
}


