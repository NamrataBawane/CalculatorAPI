package UnitTestBanking.Banking;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestBankingSystem 
{

	Account Acc;
	double Check;
	int AccNo;
	
	@BeforeGroups("RegressionTesting")
	public void InitRegression()
	{
		System.out.println("I am in before Group");
		Acc = new Account();
	}
	
	@BeforeClass
	public void Initial()
	{
		System.out.println("I am in before class");
		Acc = new Account();
	}
	@BeforeMethod
	public void Init()
	{
		System.out.println("I am in before class");
		
	}
	@Test
	public void TestIntitialAmount() {
		
		Check = Acc.InitialAmount(1500);
	}
	
	@Test(priority=1,groups="RegressionTesting")
	public void TestValidAccoutNo()
	{
		String Check = Acc.AccountNo(105);
		Assert.assertEquals(Check, "105 is Valid","Invalid Account Number");
	}
	@Test
	public void TestValidDeposite()
	{
		Check = Acc.Deposite(500.0);
		Assert.assertEquals(Check, 2000.0,"Amount not yet deposited");
	}
	
	@Test(priority=2)
	public void TestValidWithdrawal() throws ExceptionFile
	{
		String Check = Acc.Withdraw(200.0);
		Assert.assertEquals(Check, "The Balance is : 1800.0","Insufficient Balance");
	}
	/*
	 * @Test public void TestInvalidWithdrawal() throws ExceptionFile { String Check
	 * = Acc.Withdraw(-10000000.0);
	 * Assert.assertEquals(Check,"Insufficient Balance", "Insufficient Balance");
	 * 
	 * }
	 */
	
	@Test(priority = 3, dataProvider = "AllValues")
	public void TestObjects(double Add, double total  ) {
		Check = Acc.Deposite(Add);
		Assert.assertEquals(Check, total, "Not Deposited");	
	}
	
	
	@DataProvider
	public Object[][] AllValues() {

		Object[][] SetOfValues = new Object[2][2];

		SetOfValues[0][0] = 100;
		SetOfValues[0][1] = 1900;
	

		SetOfValues[1][0] = 100;
		SetOfValues[1][1] = 2000;
		

		return SetOfValues;
	}

	
	@AfterClass
	public void End() {
		Acc = null;
	}

	

    @BeforeSuite 
	  @Parameters({"GetID"})
	  public void CreateResultFolder(String GetID) {
	  
	  System.out.println("Before Suite"); 
	  try {
	  Files.createDirectories(Paths.get("./"+GetID));
	  } catch (IOException e) {
	  // TODO Auto-generated catch block
	  System.out.println("Problem in creating a Result Directory"); } }
	  
    @AfterSuite 
    @Parameters({"GetID"})
	  public void CopyResultFile(String GetID) {
	  System.out.println(" After Suite");
	  try {
	  Files.copy(Paths.get(".\\test-output\\emailable-report.html"),Paths.get("./"+GetID+"/Result.html"),StandardCopyOption.REPLACE_EXISTING);
	  } catch (IOException e)
	  { System.out.println("Problem in copying "); 
	  }      
	 }
 }

	

