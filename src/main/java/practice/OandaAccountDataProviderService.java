package practice;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.account.*;
import com.oanda.v20.transaction.TransactionID;

public class OandaAccountDataProviderService {
	private final String url;
	private final AccountID accountId;
	private final String accessToken;
	private Account myAccount;
	private final Context ctx;
	
	public OandaAccountDataProviderService(String url, String accessToken, AccountID accountId) {
		this.accountId = accountId;
		this.url = url;
		this.accessToken = accessToken;
		ctx = new ContextBuilder(url)
				.setToken(accessToken)
				.setApplication("Practice")
				.build();
		makeAccount();
	}
	
	private void makeAccount() {
		try {
			AccountGetResponse accountStateResponse = ctx.account.get(accountId);
            myAccount = accountStateResponse.getAccount();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Account getAccount() {
		return this.myAccount;
	}
}
