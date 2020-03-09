package practice;

import java.util.ArrayList;
import java.util.List;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.order.MarketIfTouchedOrder;
import com.oanda.v20.order.MarketIfTouchedOrderRequest;
import com.oanda.v20.order.MarketOrder;
import com.oanda.v20.order.MarketOrderRequest;
import com.oanda.v20.order.OrderCancel404RequestException;
import com.oanda.v20.order.OrderCancelResponse;
import com.oanda.v20.order.OrderContext;
import com.oanda.v20.order.OrderCreate400RequestException;
import com.oanda.v20.order.OrderCreate404RequestException;
import com.oanda.v20.order.OrderCreateRequest;
import com.oanda.v20.order.OrderCreateResponse;
import com.oanda.v20.order.OrderReplace400RequestException;
import com.oanda.v20.order.OrderReplace404RequestException;
import com.oanda.v20.order.OrderReplaceRequest;
import com.oanda.v20.order.OrderReplaceResponse;
import com.oanda.v20.order.OrderRequest;
import com.oanda.v20.order.OrderSpecifier;
import com.oanda.v20.order.OrderType;
import com.oanda.v20.primitives.InstrumentName;
import com.oanda.v20.transaction.TransactionID;

public class OandaOrderManagementProvider {
	
	private final String url;
	private final String accessToken;
	private final OandaAccountDataProviderService accountProvider;
	private final AccountID accountId;
	private final Context ctx;
	private static List<TransactionID> listOfTransactionIDs = new ArrayList<TransactionID>();
	
	public OandaOrderManagementProvider(String url, String accessToken, OandaAccountDataProviderService accountProvider, AccountID accountId) {
		this.accessToken = accessToken;
		this.url = url;
		this.accountProvider = accountProvider;
		this.accountId = accountId;
		ctx = new ContextBuilder(this.url)
        		.setToken(this.accessToken)
        		.setApplication("Practice")
        		.build();
	}
	
	public void placeMarketOrder(InstrumentName instrument) throws OrderCreate400RequestException, OrderCreate404RequestException, RequestException, ExecuteException {
		OrderRequest requestOrder = new MarketOrderRequest()
				.setInstrument(instrument)
				.setUnits(10);
		OrderCreateRequest request = new OrderCreateRequest(accountId)
				.setOrder(requestOrder);
		OrderCreateResponse response = ctx.order.create(request);
		listOfTransactionIDs.add(response.getLastTransactionID());
	}
	
	public void placeMarketIfTouchedOrder(InstrumentName instrument) throws OrderCreate400RequestException, OrderCreate404RequestException, RequestException, ExecuteException {
		OrderRequest requestOrder = new MarketIfTouchedOrderRequest()
				.setInstrument(instrument)
				.setUnits(10)
				.setPrice(2.5);
		OrderCreateRequest request = new OrderCreateRequest(accountId)
				.setOrder(requestOrder);
		OrderCreateResponse response = ctx.order.create(request);
		listOfTransactionIDs.add(response.getLastTransactionID());
	}
	
	public void changeOrder(OrderSpecifier orderSpecifier, double numOfUnits, InstrumentName instrument) throws OrderReplace400RequestException, OrderReplace404RequestException, RequestException, ExecuteException {
		OrderRequest order = new MarketIfTouchedOrderRequest()
				.setInstrument(instrument)
				.setUnits(20)
				.setType(OrderType.MARKET_IF_TOUCHED)
				.setPrice(3);
		OrderReplaceRequest replaceRequest = new OrderReplaceRequest(accountId, orderSpecifier);
		replaceRequest.setOrder(order);
		OrderReplaceResponse response = ctx.order.replace(replaceRequest);
		System.out.println(response.getOrderCreateTransaction());
	}
	
	public void closeOrder(OrderSpecifier orderSpecifier) throws OrderCancel404RequestException, RequestException, ExecuteException {
		OrderCancelResponse response = ctx.order.cancel(accountId, orderSpecifier);
		response.getOrderCancelTransaction();
	}
	
	public List<TransactionID> getList(){
		return OandaOrderManagementProvider.listOfTransactionIDs;
	}
}
