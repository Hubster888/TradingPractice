package practice;

import java.util.Collection;

import com.oanda.v20.account.*;
import com.oanda.v20.primitives.Instrument;

public class Summary {

	public static void main(String[] args) throws Exception {
		AccountID id = new AccountID("101-004-13661335-002");
		OandaAccountDataProviderService accountProvider = new OandaAccountDataProviderService("https://api-fxpractice.oanda.com",
				"edbe715b87f077cbb4567a4a58f90421-90ac55c43f6c136f2194b70051ff103f" , id);
		///////////////////////////////////
		OandaInstrumentDataProviderService instrumentProvider = new OandaInstrumentDataProviderService(id, "https://api-fxpractice.oanda.com",
				"edbe715b87f077cbb4567a4a58f90421-90ac55c43f6c136f2194b70051ff103f");
		InstrumentService service = new InstrumentService(instrumentProvider);
        Collection<Instrument> list = service.getAllPairsWithCurrency("GBP");
        for(Instrument in : list) {
        	System.out.println(in.getName().toString());
        }
	}
}