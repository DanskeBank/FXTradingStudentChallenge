package programmeringsstuga;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

// Rename to TricksReader?
public class TicksReader implements Iterable<Tick> {
	private ArrayList<Tick> ticks;
	
	public TicksReader(String filePath) {
		ticks = readTicks(filePath);
	}
	
	private ArrayList<Tick> readTicks(String filePath) {
		String line = "";
		ArrayList<Tick> ticks = new ArrayList<Tick>();
		   try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			   br.readLine(); //Skip first line with headers
			   Tick tick = null;
			   
			   while ((line = br.readLine()) != null) {
				   String [] tokens = line.split(",");

				   tick = new Tick(
						   Integer.parseInt(tokens[0]), //index
						   tokens[1],//symbol
						   tokens[2],//time
						   Double.parseDouble(tokens[3]), //bid
						   Double.parseDouble(tokens[4]), //ask
						   Integer.parseInt(tokens[5]), //bidSize
						   Integer.parseInt(tokens[6]) //askSize
						   );
				   
				   ticks.add(tick);
			   } 
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		   
		   return ticks;
	}
	
	@Override
	public Iterator<Tick> iterator() {
		 Iterator<Tick> itr = new Iterator<Tick>() {

	            private int currentIndex = 0;

	            @Override
	            public boolean hasNext() {
	                return currentIndex < ticks.size();
	            }

	            @Override
	            public Tick next() {
	                return ticks.get(currentIndex++);
	            }

	            @Override
	            public void remove() {
	                throw new UnsupportedOperationException();
	            }
		 };
	     return itr;
	}
	
	public ArrayList<Tick> getList() {
		return ticks;
	}
	
	public int getSize() {
		return ticks.size();
	}
	
}
