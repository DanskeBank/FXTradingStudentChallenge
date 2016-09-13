package programmeringsstuga;

public class Tick {
//Index,Symbol,Time,BID,ASK,BIDSIZE,ASKSIZE
	private int index;
	private String symbol;
	private String date;
	private Double bid;
	private Double ask;
	private int bidSize;
	private int askSize;
	
	public Tick(int index, String symbol, String date, Double bid, Double ask, int bidSize, int askSize){
		this.index = index;
		this.symbol = symbol;
		this.date = date;
		this.bid = bid;
		this.ask = ask;
		this.bidSize = bidSize;
		this.askSize = askSize;
	}
	
	public int getIndex(){
		return index;
	}
	
	public String getSymbol(){
		return symbol;
	}
	
	public String getDate(){
		return date.substring(0,23);
	}
	
	public double getBidPrice(){
		return bid;
	}
	
	public double getAskPrice(){
		return ask;
	}
	
	public int getBidSize(){
		return bidSize;
	}
	
	public int getAskSize(){
		return askSize;
	}
	
	@Override
	public String toString(){
		return index + "," + symbol +"," + date +"," +bid + "," + ask + "," +bidSize + "," + askSize ;
	}
}
