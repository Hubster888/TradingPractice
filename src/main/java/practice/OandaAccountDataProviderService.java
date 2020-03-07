package practice;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;

public class OandaAccountDataProviderService implements AccountDataProvider<String> {
	private final String url;
	private final String accountID;
	private final String accessToken;
	private final Account<String> account;
	
	public OandaAccountDataProviderService(final String url, final String accountID, final String accessToken) {
		this.url = url;
		this.accountID = accountID;
		this.accessToken = accessToken;
		this.account = getLatestAccountInfo(this.accountID);
	}

	public Account<String> getLatestAccountInfo(String accountId) {
		Context ctx = new ContextBuilder(url)
				.setToken(accessToken)
				.setApplication("Practice")
				.build();
		AccountSummary summary = null;
		Account<String> account = null;
		try {
			summary = ctx.account.summary(
					new AccountID(accountID)).getAccount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(summary != null) {
			account = new Account<String>(summary.getBalance().doubleValue(), summary.getUnrealizedPL().doubleValue(), summary.getPl().doubleValue(),
					summary.getMarginUsed().doubleValue(), summary.getMarginAvailable().doubleValue(), summary.getOpenTradeCount().longValue(), summary.getCurrency().toString(),
					this.accountID, summary.getMarginRate().doubleValue());
		}
		
		return account;
	}
	
	public Account<String> getAccount(){
		return this.account;
	}

}
