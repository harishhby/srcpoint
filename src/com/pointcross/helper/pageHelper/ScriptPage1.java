package com.pointcross.helper.pageHelper;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.pointcross.config.GetPath;
import com.pointcross.helper.objHelper.WebPage;
import com.pointcross.helper.objHelper.Xls_Reader;


/**
 * @author Harish
 */

public class ScriptPage1 extends WebPage
{
	public  Xls_Reader datatable=new Xls_Reader(GetPath.getBasePath()+"\\TestData\\ProjectTestData.xlsm");
	
	 public void initiate() throws IOException, AWTException{	
					 
			String browser=datatable.getCellData("SetUp", "Browser", 2); 
			System.out.println(browser);
			if(browser.equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver",GetPath.getBasePath()+ "\\Setup\\geckodriver.exe");				
				 driver= new FirefoxDriver();
			} else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver",GetPath.getBasePath()+ "\\Setup\\chromedriver.exe");
					    driver= new ChromeDriver();
			} else if(browser.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver",GetPath.getBasePath()+ "\\Setup\\IEDriverServer.exe");			
			    driver= new InternetExplorerDriver();				
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.get(datatable.getCellData("SetUp", 1, 2));
			
		}
	 
	 public void clickOnLoginLnk() throws IOException
	 {
			click("loginLink");
	 }
	
}



