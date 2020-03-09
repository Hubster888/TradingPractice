package practice;

import com.oanda.v20.account.AccountID;

/**
 * A POJO that holds account information. No setters are needed
 * as all variables are final and are initialised in the constructors.
 * This class is not used for the Oanda api as they supply their own
 * */
public class AccountOther<T> {
	
	private final double totalBalance;
	private final double unrealisedPnl;
	private final double realisedPnl;
	private final double marginUsed;
	private final double marginAvaliable;
	private final double netAssetValue;
	private final long openTrades;
	private final String currency;
	private final AccountID accountID;
	private final String toStr;
	private final double amountAvaliableRatio;
	/**
	 * The amount available to trade as a 
	 * fraction of total amount.
	 * */
	private final double marginRate;
	/**
	 * The leverage offered on this
	 * account. 
	 * */
	private final int hash;
	
	public AccountOther(final double totalBalance, double unrealisedPnl,
			double realisedPnl, double marginUsed, double marginAvaliable,
			long openTrades, String currency, AccountID accountID, double marginRate) {
		this.totalBalance = totalBalance;
		this.unrealisedPnl = unrealisedPnl;
		this.realisedPnl = realisedPnl;
		this.marginUsed = marginUsed;
		this.marginAvaliable = marginAvaliable;
		this.openTrades = openTrades;
		this.currency = currency;
		this.accountID = accountID;
		this.amountAvaliableRatio = this.marginAvaliable / this.totalBalance;
		this.netAssetValue = this.marginUsed + this.marginAvaliable;
		this.marginRate = marginRate;
		this.hash = calcHashCode();
		this.toStr = String.format("ID=%s " + "Currency=%s, NAV=%5.2f,Total Balance=%5.2f, UnrealisedPnl=%5.2f, "
				+ "RealisedPnl=%5.2f", accountID, currency, netAssetValue, totalBalance, unrealisedPnl, realisedPnl,
				marginUsed, marginAvaliable, openTrades, marginAvaliable, openTrades,
				this.amountAvaliableRatio, this.marginRate);
		
	}
	
	public double getAmountAvaliableRatio() {
		return this.amountAvaliableRatio;
	}
	
	public double getMarginRate() {
		return this.marginRate;
	}
	
	@Override
	public String toString() {
		return this.toStr;
	}
	
	public AccountID getAccountID() {
		return this.accountID;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	
	public double getNetAssetValue() {
		return this.netAssetValue;
	}
	
	public double getTotalBalance() {
		return this.totalBalance;
	}
	
	public double getUnrealisedPnl() {
		return this.unrealisedPnl;
	}
	
	public double getRealisedPnl() {
		return this.realisedPnl;
	}
	
	public double getMarginUsed() {
		return this.marginUsed;
	}
	
	@Override
	public int hashCode() {
		return this.hash;
	}
	
	private int calcHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountID == null) ? 0 : accountID.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {return true;}
		if(obj == null) {return false;}
		if(getClass() != obj.getClass()) {return false;}
		
		@SuppressWarnings("unchecked")
		AccountOther<T> other = (AccountOther<T>) obj;
		if(accountID == null) {
			if(other.accountID != null){return false;}
		}else if(!accountID.equals(other.accountID)) {return false;}
		return true;
	}
	
	public double getMarginAvaliable() {
		return marginAvaliable;
	}
	
	public long getOpenTrades() {
		return this.openTrades;
	}
	
}
