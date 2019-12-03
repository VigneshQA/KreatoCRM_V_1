package com.kreatocrm.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.kreatocrm.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();		// Creating an object for ReadConfig.java to read data from config.properties file
	
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUserName(); 
	public String password = readconfig.getPassword();
	public String loginpagetitle = readconfig.getLoginpageTitle();
	public String homepagetitle = readconfig.getHomepageTitle();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		/* Log4j Configuration */
		logger = Logger.getLogger("KretoCRM");
		PropertyConfigurator.configure("log4j.properties");
		
		/* Initializing browser based on input "br" */
		if(br.equals("chrome"))									
		{
			logger.info("Initializing chrome browser");
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();
		}
		
		else if(br.equals("ie"))
		{
			logger.info("Initializing IE browser");
			WebDriverManager.iedriver().setup();
			//System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		else if(br.equals("firefox"))
		{
			logger.info("Initializing firefox browser");
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver", readconfig.getFireFoxPath());
			driver = new FirefoxDriver();
		}
		
		else if(br.equals("HeadlessChrome"))
		{
			logger.info("Initializing Headless Chrome Browser");
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			//options.addArguments("--headless");
			
			driver = new ChromeDriver(options);			
		}
		
		else if(br.equals("HeadlessFirefox"))
		{
			logger.info("Initializing Headless Firefox Browser");
			System.setProperty("webdriver.gecko.driver", readconfig.getFireFoxPath());
			
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			//options.addArguments("--headless");
			
			driver = new FirefoxDriver(options);			
		}
		
		driver.manage().window().maximize();
		
	}
	
	@AfterClass
	public void teardown()
	{
		logger.info("Quit");
		driver.quit();
	}
	
	/*Method to take Screenshot*/											// Call "CaptureScreen(driver , testcaseName)" in failure case
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
	}
	
	/*Method to take Screenshot of Particular Element*/
	public void captureElement(WebElement element, String tname) throws IOException
	{
		File source = element.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + "-elmnt.png");
		FileUtils.copyFile(source, target);
	}
	
	
	/*Method to generate Random Email ID*/
	public String randomEmailID()
	{
		String generatedEmailID = RandomStringUtils.randomAlphabetic(8);
		return(generatedEmailID+"@gmail.com");
	}
	
	/*Method to generate Random Password*/
	public String randomPassword()
	{
		String generatedPassword = RandomStringUtils.randomAlphabetic(8);
		return(generatedPassword);
	}
	
	/*Method to generate Random Password*/
	public String randomContactNO()
	{
		String generatedContactNO = RandomStringUtils.randomNumeric(10);
		return(generatedContactNO);
	}
	
	/*Method to Flash Element*/
	public static void Flash(WebElement element ,WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		//String bgcolor = element.getCssValue("backgroundColor");
		for(int i=0;i<500;i++)
		{
			js.executeScript("argument[0].style.backgroundColor = '#000000'", element);
			js.executeScript("argument[0].style.backgroundColor = '#111111'", element);
		}
	}
	
}
