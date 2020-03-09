package practice;

import java.util.List;

import com.oanda.v20.instrument.Candlestick;

public class MovingAverageCalculationService {
	private final OandaHistoricMarketDataProvider dataProvider;
	
	public MovingAverageCalculationService(OandaHistoricMarketDataProvider dataProvider){
		this.dataProvider = dataProvider;
	}
	
	/*
	 * Simple average calculation of close price of candle stick
	 */
	public double calculateSMA(List<Candlestick> list) {
		double sumsma = 0;
		for (Candlestick candle: list) {
		sumsma += candle.getMid().getC().doubleValue();
		}
		return sumsma / list.size();
	}
	
	/*
	 * If there are N candle sticks then, Mth candle stick will have
	 * weight
	 * M/(N * (N+1)/2). Therefore the divisor D for each candle is (N *
	 * (N+1)/2) */
	public double calculateWMA(List<Candlestick> list) {
		double divisor = (list.size() * (list.size() + 1)) / 2;
		int count = 0;
		double sumwma = 0;
		for (Candlestick candle : list) {
		count++;
		sumwma += (count * candle.getMid().getC().doubleValue()) / divisor;
		}
		return sumwma;
	}
}
