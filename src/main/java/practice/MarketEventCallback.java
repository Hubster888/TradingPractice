package practice;

import com.oanda.v20.primitives.DateTime;
import com.oanda.v20.primitives.Instrument;

public interface MarketEventCallback<T> {
	
	/**
	 * A method, invoked by the upstream handler of streaming market
	 * data events. This invocation of this method is synchronous,
	 * therefore the method should return asap, to make sure that the
	 * upstream events do not queue up.
	 *
	 * @param instrument
	 * @param bid
	 * @param ask
	 * @param eventDate
	 */
	void onMarketEvent(Instrument instrument, double bid,
	double ask, DateTime eventDate);
}
