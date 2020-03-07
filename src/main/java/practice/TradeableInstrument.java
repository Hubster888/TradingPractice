package practice;

public class TradeableInstrument<T> {
	private final String instrument;
	private final String description;
	private final T instrumentId;
	private final double pip;
	private final int hash;
	private InstrumentPairInterestRate instrumentPairIntrestRate;
	
	public TradeableInstrument(String instrument) {
		this(instrument, null);
	}

	public TradeableInstrument(String instrument, String description) {
		this(instrument, null, description);
	}
	
	public TradeableInstrument(String instrument, T instrumentId, String description) {
		this(instrument, instrumentId, 0, null);
	}
	
	public TradeableInstrument(final String instrument, final double pip,
			InstrumentPairInterestRate instrumentPairIntrestRate, String description) {
		this(instrument, null, pip, instrumentPairIntrestRate, description);
	}
	
	public TradeableInstrument(final String instrument, T instrumentId, final double pip,
			InstrumentPairInterestRate instrumentPairIntrestRate) {
		this(instrument, instrumentId, pip, instrumentPairIntrestRate, null);
	}
	
	public TradeableInstrument(final String instrument, T instrumentId, final double pip,
			InstrumentPairInterestRate instrumentPairIntrestRate, String description) {
		this.instrument = instrument;
		this.pip = pip;
		this.instrumentPairIntrestRate = instrumentPairIntrestRate;
		this.instrumentId = instrumentId;
		this.description = description;
		this.hash = calcHashCode();
	}
	
	private int calcHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result +((instrument == null) ? 0 : instrument.hashCode());
		result = prime * result + ((instrument == null) ? 0 : instrumentId.hashCode());
		return result;
	}
	
	public T getInstrumentId() {
		return this.instrumentId;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public int hashCode() {
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {return true;}
		if(obj == null) {return false;}
		if(getClass() != obj.getClass()) {return false;}
		@SuppressWarnings("unchecked")
		TradeableInstrument<T> other = (TradeableInstrument<T>) obj;
		if(instrument == null) {
			if(other.instrument != null) {
				return false;
			}
		}else if(!instrument.equals(other.instrument)) {
			return false;
		}
		if(instrumentId == null) {
			if(other.instrumentId != null) {
				return false;
			}
		}else if(!instrumentId.equals(other.instrumentId)) {
			return false;
		}
		return true;
	}
	
	public InstrumentPairInterestRate getInstrumentPairIntrestRate() {
		return this.instrumentPairIntrestRate;
	}
	
	public void setInstrumentPairIntrestRate(InstrumentPairInterestRate instrumentPairIntrestRate) {
		this.instrumentPairIntrestRate = instrumentPairIntrestRate;
	}
	
	@Override
	public String toString() {
		return "TradeavleInstrument [instrument=" + instrument + ", description=" + description+
				", instrumentId=" + instrumentId + ", pip=" + pip + ", instrumentPairIntrestRate=" +
				instrumentPairIntrestRate + "]";
	}
	
	public String getInstrument() {
		return instrument;
	}
	
	public double getPip() {
		return pip;
	}
}
