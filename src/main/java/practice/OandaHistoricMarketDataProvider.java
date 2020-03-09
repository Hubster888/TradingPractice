package practice;


import java.util.List;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.CandlestickGranularity;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.primitives.DateTime;
import com.oanda.v20.primitives.InstrumentName;

public class OandaHistoricMarketDataProvider {
	
	private final String url;
	private final String accessToken;
	private final Context ctx;
	
	public OandaHistoricMarketDataProvider(String url, String accessToken) {
		this.url = url;
		this.accessToken = accessToken;
		this.ctx = new ContextBuilder(url)
				.setToken(accessToken)
				.setApplication("Practice")
				.build();
	}
	
	public List<Candlestick> getCandles(DateTime from, DateTime to, InstrumentName instrument) throws RequestException, ExecuteException {
		InstrumentCandlesRequest request = new InstrumentCandlesRequest(instrument)
				.setFrom(from)
				.setTo(to)
				.setGranularity(CandlestickGranularity.H8);
		
		InstrumentCandlesResponse response = ctx.instrument.candles(request);
		List<Candlestick> list = response.getCandles();
		
		
		return list;
	}
}
