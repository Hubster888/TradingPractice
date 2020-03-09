package practice;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.pricing.ClientPrice;
import com.oanda.v20.pricing.PricingGetRequest;
import com.oanda.v20.pricing.PricingGetResponse;
import com.oanda.v20.primitives.DateTime;
import com.oanda.v20.primitives.Instrument;
import com.oanda.v20.primitives.InstrumentName;

public class OandaMarketDataStreamingService {
	
	private final Context ctx;
	private final String url;
	private final String accessToken;
	private final AccountID accountId;
	
	private static DateTime since = null;
	
	public OandaMarketDataStreamingService(String url, String accessToken, AccountID accountId) throws FileNotFoundException {
		this.url = url;
		this.accessToken = accessToken;
		this.accountId = accountId;
		ctx = new ContextBuilder(this.url)
        		.setToken(this.accessToken)
        		.setApplication("Practice")
        		.build();
		
		
	}

	
	/**
	 * @param instrumentNames
	 */
	public List<ClientPrice> getPriceOfInstrument(List<InstrumentName> instrumentNames) {
		try {
            PricingGetRequest request = new PricingGetRequest(accountId, instrumentNames);
            
            if (since != null)
            {
                request.setSince(since);
            }
            PricingGetResponse resp = ctx.pricing.get(request);
            
            List<ClientPrice> prices = resp.getPrices();
            
            since = resp.getTime();
            return prices;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}
