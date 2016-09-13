package programmeringsstuga;

public class Trade {
	//AmountBuy;AmountSell;CurrencyCodeSell;CurrencyCodeBuy;SpotBase;CreateTime
	private double amountBuy;
	private double amountSell;
	private String currencyCodeSell;
	private String currencyCodeBuy;
	private double spotBase;
	private String date;
	
	public Trade(double amountBuy,
				 double amountSell,
				 String currencyCodeSell,
				 String currencyCodeBuy,
				 double spotBase,
				 String date){
			
		this.amountBuy = amountBuy;
		this.amountSell = amountSell;
		this.currencyCodeSell = currencyCodeSell;
		this.currencyCodeBuy = currencyCodeBuy;
		this.spotBase = spotBase;
		this.date = date;
	}
	
	public double getAmountBuy(){
		return amountBuy;
	}
	
	public double getAmountSell(){
		return amountSell;
	}
	
	public String getCurrencyCodeSell(){
		return currencyCodeSell;
	}
	
	public String getCurrencyCodeBuy(){
		return currencyCodeBuy;
	}
	
	public double getSpotBase(){
		return spotBase;
	}
	
	public String getDate(){
		return date;
	}
	
	@Override
	public String toString(){
		return amountBuy + "," 
			 + amountSell +","
			 + currencyCodeSell +","
			 +currencyCodeBuy + ","
			 +spotBase + ","
			 +date;
	}
	
	
	
}
