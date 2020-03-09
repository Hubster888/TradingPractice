package practice;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.account.*;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.order.Order;
import com.oanda.v20.order.OrderSpecifier;
import com.oanda.v20.pricing.ClientPrice;
import com.oanda.v20.primitives.DateTime;
import com.oanda.v20.primitives.Instrument;
import com.oanda.v20.primitives.InstrumentName;
import com.oanda.v20.transaction.TransactionID;

public class Summary {

	public static void main(String[] args) throws Exception {
		
		
		AccountID id = new AccountID("101-004-13661335-002");
		String url = "https://api-fxpractice.oanda.com";
		String accessToken = "edbe715b87f077cbb4567a4a58f90421-90ac55c43f6c136f2194b70051ff103f";
		Context ctx = new ContextBuilder(url)
        		.setToken(accessToken)
        		.setApplication("Practice")
        		.build();
		
		OandaAccountDataProviderService accountProvider = new OandaAccountDataProviderService(url,
				accessToken , id);
		///////////////////////////////////
		OandaInstrumentDataProviderService instrumentProvider = new OandaInstrumentDataProviderService(id, url,
				accessToken);
		InstrumentService service = new InstrumentService(instrumentProvider);
        Collection<Instrument> list = service.getAllPairsWithCurrency("GBP");
        
        
        ThreadForDataCollection dataCollection = new ThreadForDataCollection();
        dataCollection.start();
        
        OandaHistoricMarketDataProvider historicProvider = new OandaHistoricMarketDataProvider(url, accessToken);
        
        
        MovingAverageCalculationService averageCalc = new MovingAverageCalculationService(historicProvider);
        List<Candlestick> list1  = historicProvider.getCandles(new DateTime("2020-01-07T16:58:44.082039298Z"),
        		new DateTime("2020-03-07T16:58:44.082039298Z"), new InstrumentName("GBP_USD"));
        System.out.println(averageCalc.calculateSMA(list1));
        System.out.println(averageCalc.calculateWMA(list1));
        
        OandaOrderManagementProvider orderManager = new OandaOrderManagementProvider(url, accessToken, accountProvider, id);
        orderManager.placeMarketOrder(new InstrumentName("GBP_USD"));
        orderManager.placeMarketIfTouchedOrder(new InstrumentName("GBP_USD"));
        TransactionID transID = orderManager.getList().get(0);
        List<TransactionID> listO = orderManager.getList();
        if(listO.isEmpty()) {System.out.println("Fuck");}
        
        AccountChangesRequest changesRequest = new AccountChangesRequest(id).setSinceTransactionID(transID);
        AccountChangesResponse changeResponse = ctx.account.changes(changesRequest);
        List<Order> listOfChanges = changeResponse.getChanges().getOrdersCreated();
        if(listOfChanges.isEmpty()) {System.out.print("WTF??");}
        Thread.sleep(5000);
        for(Order order : listOfChanges) {
        	//orderManager.changeOrder(new OrderSpecifier(order.getId()), 20.0, new InstrumentName("GBP_USD"));
        	System.out.println(1);
        	orderManager.closeOrder(new OrderSpecifier(order.getId()));
        }
	}
}