# FXTrading 

## Introduction
Foreign Exchange, usually abbreviated Forex or simply FX, is the largest and most liquid market in the world with average daily turnover (traded volume) exceeding trillions of dollars of worth in total. Characteristic of the FX market is that it is decentralized, trades are done over the counter between financial institutes such as banks and it is open 24-7, all year around except for weekends. Money, or cash is the most liquid financial asset in the world which means that money can be bought and sold quickly without affecting the assets price. Compare this to antiquities, say a Chinese vase which might be valued $30,000. If you own such a vase and want to sell it for cash it might take some time to find a buyer willing to pay the full price. If you are in a hurry you might want to lower the price to $25,000 and sell it. This example demonstrates why antiquities are not as liquid as money.

Currencies are priced and traded in pairs with different number of days until settlement from the transaction date. The most common FX transaction is the FX Spot, characterized by its relatively short timeframe from trade- until settlement date.  For FX Spots the usual timeframe for settlement is T+2 days, i.e. two banking days from the trade date T. Other FX transactions with longer settlements are forwards and futures, but these will not be considered further. 

Consider **figure 1**, a set of external LPs (Liquidity Providers) such as Bank of America, Morgan Stanley, Citi etc. provides “streams” of spot prices for different currency pairs. For a given currency pair, say GBP/USD, the data typically consists of a bid- and ask price with a corresponding size (for how many) and a timestamp. The bid price is what the LP is willing to buy the currency “to the left” for using the currency to the right if we sell them an amount of bid size at that particular time. The ask price is the opposite, it is the price the LP is willing to sell the currency to the left for in terms of the currency to the right. The difference between the ask price and the bid price is called the spread and usually very small for assets with high liquidity (such as currency). 
Danske Bank subscribes to these streams and feeds them into our backend. For every currency pair that is traded the aggregator picks the best BID/ASK price and sends it as a “tick” (orange arrow) to our price engine (pricer). The price engine adds a small commission (fee) for the given currency pair and sends them out to different trading platforms (yellow arrow) such as Bloomberg or One Trader (Danske Banks own platform) where customers can trade on the spots with Danske Bank.

![alt text](https://github.com/DanskeBank/FXTradingStudentChallenge/blob/master/Progstuga_flowchart.png "Figure 1")

## Data Description
Today we provide you with two classes of data aggregated during the time period 20 – 30 June 2016. The first class consists of aggregated spot prices (ticks) for the currency pairs GBP/USD, EUR/USD, EUR/SEK collected from our “tick database”. An example line of one tick together with headers is given by the listing below. From the line you can read tbe line number (Index), currency pair (Symbol), bid price (BID), ask price (ASK), bid size (BIDSIZE) and ask size (ASKSIZE).

```
Index,Symbol,Time,BID,ASK,BIDSIZE,ASKSIZE
1,APAMA::GBPUSD=A,2016/06/20 00:00:00.638000,1.444830000,1.445610000,1000000,1000000
```
 The second class of data consists of actual trades made on the GBP/USD during the time period for ten different traders, here anonymized as trader one to ten. An example line of trade data together with headers is given by the listing below. 
 ```
AmountBuy;AmountSell;CurrencyCodeSell;CurrencyCodeBuy;SpotBase;CreateTime
1343400.00;1000000.00;GBP;USD;1.3434000;2016-06-29 21:50:07.693
```
 
## Challenge Description
We want this to be a fun, creative and a fairly open challenge which means you are free to do whatever you want with the data. Maybe you are a GUI wizard and want to write a beautiful interface where various statistics of the data is displayed as plots and/or histograms? Or maybe you are more of an algorithmic person who prefers to crunch raw arcane numbers out of the data? Go ahead and do you thing! **Our only requirement is that you at least use one currency pair from the tick data.** You are free to work in pairs/small groups, if you are a lone gunman and prefer to work alone that is also fine. Regarding allowed tools, language etc. you can choose whatever makes sense to you. At Danske Banks GitHub repo you can find all the data and some example code written in Java to start from (if you want).

## Ideas
To give you some inspiration we have compiled a list of ideas of what one could do with the data.
- Data manipulation: write a small program that cross correlates the various currency pairs, i.e. create new currency pairs such as GBP/SEK.
- Statistics: plot the Bollinger bands for a time series of ticks.
- Data analysis: can you from the data read how the dealers performed? Who performed best against the market
- GUI: write a program where the user can pick and choose datasets and plot them for a given time interval. 

Happy Coding!



