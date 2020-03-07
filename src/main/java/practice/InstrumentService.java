package practice;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;

import com.oanda.v20.primitives.Instrument;

public class InstrumentService {
	private final Map<String, Instrument> instrumentMap;
	
	public InstrumentService(InstrumentDataProvider<Instrument> instrumentDataProvider) {
		if(instrumentDataProvider == null) {throw new NullPointerException();}
		List<Instrument> instruments = instrumentDataProvider.getListOfInstruments();
		Map<String, Instrument> treadableInstrumentMap = new HashMap<String, Instrument>();
		for(Instrument instrument : instruments) {
			treadableInstrumentMap.put(instrument.getName().toString(), instrument);
		}
		this.instrumentMap = Collections.unmodifiableMap(treadableInstrumentMap);
	}
	
	public Double getPipForInstrument(Instrument instrument) {
		if(instrument == null) {throw new NullPointerException();}
		Instrument treadableInstrument = this.instrumentMap.get(instrument.getName().toString());
		if(treadableInstrument != null) {
			return treadableInstrument.getPipLocation().doubleValue();
		}else {
			return 1.0;
		}
	}
	
	public Collection<Instrument> getAllPairsWithCurrency(String currency){
		Collection<Instrument> allPairs = new HashSet<Instrument>();
		if(currency.equals("")) {return allPairs;}
		for(Map.Entry<String, Instrument> entry: instrumentMap.entrySet()) {
			if(entry.getKey().contains(currency)) {
				allPairs.add(entry.getValue());
			}
		}
		return allPairs;
	}
}
