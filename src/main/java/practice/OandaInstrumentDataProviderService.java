package practice;


import java.util.List;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.primitives.Instrument;

public class OandaInstrumentDataProviderService implements InstrumentDataProvider<Instrument> {
	private final Context ctx;
	private final AccountID accountId;
	private List<Instrument> listOfInstruments;
	
	public OandaInstrumentDataProviderService(AccountID accountId, String url, String accessToken) throws RequestException, ExecuteException {
		this.accountId = accountId;
		this.ctx = new ContextBuilder(url)
				.setToken(accessToken)
				.setApplication("Practice")
				.build();
		listOfInstruments = ctx.account.instruments(accountId).getInstruments();
	}
	
	public List<Instrument> getListOfInstruments() {
        return this.listOfInstruments;
	}
}
