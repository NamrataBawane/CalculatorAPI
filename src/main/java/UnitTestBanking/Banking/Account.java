package UnitTestBanking.Banking;


public class Account 
{

	private int AccountNum;
	protected double Balance=0.0;
	static final double InterestRate=7.00;
	
	public String AccountNo(int AccNo)
	{
		if(AccNo >= 101 && AccNo <= 150)
			return AccNo+" is Valid";
		else
			return AccNo+" is Invalid Account Number";
	}
	
	public double InitialAmount(int Amount)
	{
		return Balance=Amount;
	}
	public  double Deposite(double Amount)
	{
		return Balance = Balance + Amount;
	}
	
	public String Withdraw(double Amount) throws ExceptionFile
	{
		if (Amount < 0 || this.Balance < Amount) {
			throw new ExceptionFile();
		}
		else 
		{
			Balance = Balance -  Amount;
			return "The Balance is : "+Balance;
		}
		
	}
	
	public double GetBalance()
	{
		return Balance;
	}
	
	public void GetIntrest()
	{
	
	}
	
}
