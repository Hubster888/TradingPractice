package practice;

public class InstrumentPairInterestRate {
	
	private final Double baseCurrencyBidIntrestRate;
	private final Double baseCurrencyAskIntrestRate;
	private final Double quoteCurrencyBidIntrestRate;
	private final Double quoteCurrencyAskIntrestRate;
	
	public InstrumentPairInterestRate() {
		this(null, null, null, null);
	}
	
	public InstrumentPairInterestRate(Double baseCurrencyBidIntrestRate,
			Double baseCurrencyAskIntrestRate, Double quoteCurrencyBidIntrestRate,
			Double quoteCurrencyAskIntrestRate) {
		this.baseCurrencyBidIntrestRate = baseCurrencyBidIntrestRate;
		this.baseCurrencyAskIntrestRate = baseCurrencyAskIntrestRate;
		this.quoteCurrencyBidIntrestRate = quoteCurrencyBidIntrestRate;
		this.quoteCurrencyAskIntrestRate = quoteCurrencyAskIntrestRate; 
	}
	
	public Double getBaseCurrencyBidIntrestRate() {
		return this.baseCurrencyBidIntrestRate;
	}
	
	public Double getBaseCurrencyAskIntrestRate() {
		return this.baseCurrencyAskIntrestRate;
	}
	
	public Double getQuoteCurrencyBidIntrestRate() {
		return this.quoteCurrencyBidIntrestRate;
	}
	
	public Double getQuoteCurrencyAskIntrestRate() {
		return this.quoteCurrencyAskIntrestRate;
	}
	
	@Override
	public String toString() {
		return "InstrumentPairIntrestRate [baseCurrencyBidIntrestRate=" + baseCurrencyBidIntrestRate +
				", baseCurrencyAskIntrestRate=" + baseCurrencyAskIntrestRate +
				", quoteCurrencyBidIntrestRate=" + quoteCurrencyBidIntrestRate +
				", quoteCurrencyAskIntrestRate=" + quoteCurrencyAskIntrestRate + "]";
	}
}
