package com.kreatocrm.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.kreatocrm.pageObjects.LoginPage;
import com.kreatocrm.utilities.XLUtils;

public class TS_Login_001 extends BaseClass{

	@Test
	public void TC_01_Login_001() throws IOException, InterruptedException, Exception
	{
		logger.info("Getting Base URL");
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered UserName");
		lp.setPassword(password);
		logger.info("Entered Password");
		lp.clickLogin();
		logger.info("Clicked Login button");
		Thread.sleep(2000);
		
		try {
				lp.btnMLWOk.isDisplayed();
				lp.clickMultiLoginOk();
				logger.info("Clicked Ok button in Multi Login Warining Popup");
				Thread.sleep(2000);			
			}
		catch (Exception e) {}
		
		if(driver.getPageSource().contains("Invalid user id") || driver.getPageSource().contains("Invalid password"))
		{
			logger.info("Invalid UserName / Password");
			logger.warn("Login Failed");
			Assert.assertTrue(false);
			captureScreen(driver, "TC_01_Login_001");
			logger.info("Screen shot taken");
		}
				
		lp.clickUserInfo();
		logger.info("Clicked on UserInfo");
		lp.clickLogout();
		logger.info("Clicked on Logout");
		Thread.sleep(2000);
		
		if(driver.getTitle().contains("Login"))
		{
			logger.info("Successfully Logged out");
			Assert.assertTrue(true);
		}
		
		else
		{
			logger.info("Logout failed");
			Assert.assertTrue(false);
			captureScreen(driver, "TC_01_Login_001");
			logger.info("Screen shot taken");
		}
		
	}
	
	//@Test(dataProvider="LoginCredentials")
	public void loginDDT(String userName,String Password) throws InterruptedException, IOException
	{
		logger.info("Getting Base URL");
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		logger.info("Entered UserName");
		lp.setPassword(Password);
		logger.info("Entered Password");
		lp.clickLogin();
		logger.info("Clicked Login button");
		Thread.sleep(3000);
		
		try {
				lp.btnMLWOk.isDisplayed();
				lp.clickMultiLoginOk();
				logger.info("Clicked Ok button in Multi Login Warining Popup");
				Thread.sleep(2000);			
			}
		catch (Exception e) {}
		
		if(driver.getPageSource().contains("Invalid user id") || driver.getPageSource().contains("Invalid password"))
		{
			if(username==userName && password == Password) 
			{
				logger.info("Valid UserName and Password");
				logger.warn("Login Failed");
				Assert.assertTrue(false);
				captureScreen(driver, "TC_02_Login_001");
				logger.info("Screen shot taken");
				logger.info(" ");
			}
			else 
			{
				logger.info("Invalid UserName / Password");
				logger.info(" ");
			}
			
		}
		lp.clickUserInfo();
		logger.info("Clicked on UserInfo");
		lp.clickLogout();
		logger.info("Clicked on Logout");
		Thread.sleep(2000);
		
		if(driver.getTitle().contains("Login"))
		{
			logger.info("Successfully Logged out");
			Assert.assertTrue(true);
		}
		
		else
		{
			logger.info("Logout failed");
			Assert.assertTrue(false);
			captureScreen(driver, "TC_01_Login_001");
			logger.info("Screen shot taken");
		}
	logger.info(" ");
		
	}
	
	//@Test
	public void dynamicEmailIDPasswordContactNo() throws InterruptedException
	{
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(randomEmailID());
		Thread.sleep(5000);
		lp.setUserName(randomPassword());
		Thread.sleep(5000);
		lp.setUserName(randomContactNO());
		Thread.sleep(5000);
	}
	
	@DataProvider(name="LoginCredentials")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/kreatocrm/testData/LoginCredentials.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
		String logincredentials[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logincredentials[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);
			}
				
		}
	return logincredentials;
	}
	
		
}
