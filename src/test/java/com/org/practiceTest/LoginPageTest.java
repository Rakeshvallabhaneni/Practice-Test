package com.org.practiceTest;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.org.practicetestBase.Base;
import com.org.practicetestPages.DashboardPage;
import com.org.practicetestPages.LoginPage;
import com.org.practicetestUtils.ExcelReader;

public class LoginPageTest extends Base{
	LoginPage loginPage;
	DashboardPage dasboardPage;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();	
	}
	
	@Test(dataProvider="getData")
    public void loginTest(Hashtable<String,String> data){
	
	dasboardPage = loginPage.login(data.get("USERNAME"), data.get("PASSWORD"));
	Assert.assertNotNull(dasboardPage);
    }
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

	@DataProvider
	public Object[][] getData() throws Exception{
		String currentDir = System.getProperty("user.dir");
		String testdatapath = currentDir + "\\src\\main\\java\\com\\org\\practicetestTestData\\PracticetestTestData.xls";
		
		ExcelReader xlReaderObj = new ExcelReader(testdatapath);
		Object[][] d = xlReaderObj.getData();
		return d;
	}
}
