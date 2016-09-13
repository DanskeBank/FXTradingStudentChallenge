package programmeringsstuga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.general.SeriesException; 
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Second; 
import org.jfree.data.time.TimeSeries; 
import org.jfree.data.time.TimeSeriesCollection; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TradesReader implements Iterable<Trade> {
	private ArrayList<Trade> trades;
	
	public TradesReader(String filePath) {
		trades = readTrades(filePath);	
	}
	
	private ArrayList<Trade> readTrades(String filePath){
		ArrayList<Trade> trades = new ArrayList<Trade>();
		String line = "";
		   try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			   br.readLine(); //Skip first line with headers
			   Trade trade = null;
			   
			   while ((line = br.readLine()) != null) {
				   String [] tokens = line.split(";");

				   trade = new Trade(
						   Double.parseDouble(tokens[0]), //amountBuy
						   Double.parseDouble(tokens[1]),//amountSell
						   tokens[2],//currencyCodeSell
						   tokens[3], //currencyCodeBuy
						   Double.parseDouble(tokens[4]), //spotBase
						   tokens[5] //date
						   );
				   
				   trades.add(trade);
			   } 
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		   return trades;
	}
	
	public TimeSeries createDataset(String t1, String t2){
		TimeSeries dataset = new TimeSeries("asdf", Millisecond.class);
		SimpleDateFormat tradeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS" );
		
		Millisecond currentMs = null;
		Date d1 = null;
		Date d2 = null;
		
		try {
			   d1 = tradeFormat.parse(t1);
			   d2 = tradeFormat.parse(t2);  
	    }catch(Throwable t) {
		    System.out.println("Failed to parse date string");
	    }
		
		Date tradeDate = null;
		
		Iterator<Trade> itr = trades.iterator();
		Trade trade = null;
		
		double buyAmount = 0.0;
		
		while(itr.hasNext()){
			trade = itr.next();
			try{
				tradeDate = tradeFormat.parse(trade.getDate());
				currentMs = new Millisecond(tradeDate);
			}catch(Throwable t){
				System.out.println("Failed to parse date");
			}
			
			buyAmount = trade.getAmountBuy();
			
			//Checks if d1 < tradeDate < d2
			if(tradeDate.compareTo(d1) == 1 && tradeDate.compareTo(d2) == -1){
				dataset.addOrUpdate(currentMs, new Double(buyAmount));
			}
		}
		
		return  dataset;
		
	}
	
	@Override
	public Iterator<Trade> iterator() {
		 Iterator<Trade> itr = new Iterator<Trade>() {

	            private int currentIndex = 0;

	            @Override
	            public boolean hasNext() {
	                return currentIndex < trades.size();
	            }

	            @Override
	            public Trade next() {
	                return trades.get(currentIndex++);
	            }

	            @Override
	            public void remove() {
	                throw new UnsupportedOperationException();
	            }
		 };
	     return itr;
	}
	
	
}
