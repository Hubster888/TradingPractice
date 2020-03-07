package practice;

public class Summary {

	public static void main(String[] args) {
		OandaAccountDataProviderService accounts = new OandaAccountDataProviderService("https://api-fxpractice.oanda.com",
				"101-004-13661335-002","edbe715b87f077cbb4567a4a58f90421-90ac55c43f6c136f2194b70051ff103f");
		Account<String> account = accounts.getAccount();
		System.out.println(account.toString());
		
	}
}