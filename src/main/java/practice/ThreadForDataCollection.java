package practice;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.oanda.v20.account.AccountID;
import com.oanda.v20.pricing.ClientPrice;
import com.oanda.v20.primitives.DateTime;
import com.oanda.v20.primitives.InstrumentName;

public class ThreadForDataCollection extends Thread{
	AccountID id = new AccountID("101-004-13661335-002");
	String url = "https://api-fxpractice.oanda.com";
	String accessToken = "edbe715b87f077cbb4567a4a58f90421-90ac55c43f6c136f2194b70051ff103f";
	
	public void run() {
		try {
			// Displaying the thread that is running 
			
			while(true) {
				FileWriter myWriter = new FileWriter("LOG.txt");
				OandaMarketDataStreamingService prices = new OandaMarketDataStreamingService(url,
		        		accessToken, id);
		        List<InstrumentName> listOfNames = new ArrayList<InstrumentName>();
		        listOfNames.add(new InstrumentName("GBP_USD"));
		        listOfNames.add(new InstrumentName("EUR_USD"));
				
		        List<ClientPrice> pricesList = prices.getPriceOfInstrument(listOfNames);
		        
		        for(ClientPrice price : pricesList) {
		        	
		        	myWriter.write(price.toString() + "\n");
		        	//System.out.println(price.toString());
		        	
		        }
				Thread.sleep(1000);
				myWriter.close();
			}
			
		}catch(Exception e) {
			// Throwing an exception 
            System.out.println ("Exception is caught " + e);
		}
	}
}
