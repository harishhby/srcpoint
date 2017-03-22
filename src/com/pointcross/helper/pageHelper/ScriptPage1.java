package com.pointcross.helper.pageHelper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.api.ImageTarget;

import com.pointcross.config.GetPath;
import com.pointcross.helper.objHelper.WebPage;
import com.pointcross.helper.objHelper.Xls_Reader;
import com.sun.prism.impl.Disposer.Target;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;


/**
 * @author Harish
 */

public class ScriptPage1 extends WebPage
{
	public  Xls_Reader datatable=new Xls_Reader(GetPath.getBasePath()+"\\ProjectTestData.xlsm");
	
	 public void initiate() throws IOException, AWTException{	
			
		 if (driver != null) {
		        driver.quit();
		    }
		 
			String browser=datatable.getCellData("SetUp", 1, 2); 
			if(browser.equalsIgnoreCase("firefox")){
				FirefoxProfile profile= new FirefoxProfile();
				profile.setPreference("javascript.enabled", "true");
				 driver= new FirefoxDriver(profile);
			} else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver",GetPath.getBasePath()+ "\\Setup\\chromedriver.exe");
						ChromeOptions coptions = new ChromeOptions();
					     coptions.addArguments("--disable-extensions");   
					    driver= new ChromeDriver(coptions);
			} else if(browser.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver",GetPath.getBasePath()+ "\\Setup\\IEDriverServer.exe");			
			    driver= new InternetExplorerDriver();				
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		//	ctdcurl=datatable.getCellData("SetUp", 0, 4);
	    //	driver.get(ctdcurl);			
		}
	 
	
}



