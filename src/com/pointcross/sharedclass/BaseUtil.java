package com.pointcross.sharedclass;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import atu.testng.reports.ATUReports;
import atu.testng.reports.utils.Utils;

import com.pointcross.config.GetPath;
import com.pointcross.helper.objHelper.Xls_Reader;



public class BaseUtil {
	
	//public  static  Xls_Reader datatable;

	//public static Xls_Reader datatable=new Xls_Reader(GetPath.getBasePath()+"\\TestData\\ProjectTestData.xlsx");
	public static Xls_Reader datatable=new Xls_Reader(GetPath.getBasePath()+"\\TestData\\ProjectTestData.xlsm");
	//public static Xls_Reader datatable=new Xls_Reader(GetPath.getBasePath()+"\\ProjectTestData.xls");
	
	public static  String strControllerPath = GetPath.getBasePath()
			    + "\\Setup\\OR.xlsx";
	
    @SuppressWarnings("rawtypes")
	public static Hashtable<?, ?> GlobalSettings = new Hashtable();
	 protected void setAuthorInfoForReports() {
         ATUReports.setAuthorInfo("TA3S_Tester", Utils.getCurrentTime(), "21.0");
   }
   protected void setWebDriverReport() {
    ATUReports.setWebDriver(driver);
   }
  
   public static WebDriver driver;
   public static WebDriver drvr;
   public static WebDriver dr;

  /* @BeforeMethod
	public void initiate() throws IOException, AWTException{	
				
		String browser=datatable.getCellData("SetUp", 1, 2); 
		if(browser.equalsIgnoreCase("firefox")){
			FirefoxProfile profile= new FirefoxProfile();
			profile.setPreference("javascript.enabled", "true");
			 driver= new FirefoxDriver(profile);
		} else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver",GetPath.getBasePath()+ "\\chromedriver.exe");
			

				ChromeOptions coptions = new ChromeOptions();
				     coptions.addArguments("--disable-extensions");   
				    driver= new ChromeDriver(coptions);
		} else if(browser.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver",GetPath.getBasePath()+ "\\IEDriverServer.exe");
			
		    driver= new InternetExplorerDriver();
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		ctdcurl=datatable.getCellData("SetUp", 0, 4);
    	driver.get(ctdcurl);
		
	}*/
   
 
	
	
	
	public WebElement readObject(String key) throws IOException{
		
		WebDriverWait wait = new WebDriverWait(driver, 120);
	    
		String strControllerPath = GetPath.getBasePath()
				+ "\\Setup\\OR.xlsx";
		datatable = new Xls_Reader(strControllerPath);
		Object[][] data= Xls_Reader.getData("Elements");
		for(int row=0; row<data.length; row++){
			if(data[row][0].equals(key)){
				String identifier= (String) data[row][1];
				String locator= (String) data[row][2];
				if(identifier.equalsIgnoreCase("xpath")){
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					return driver.findElement(By.xpath(locator));
				} else if(identifier.equalsIgnoreCase("id")){
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
					return driver.findElement(By.id(locator));
				} else if(identifier.equalsIgnoreCase("name")){
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
					return driver.findElement(By.name(locator));
				}
			}
		}
		return null;
		
		
	}
	public void initiate1(String browsr) throws IOException, AWTException{	
		
		String browser= browsr;
		if(browser.equalsIgnoreCase("firefox")){
			FirefoxProfile profile= new FirefoxProfile();
			profile.setPreference("javascript.enabled", "true");
			driver= new FirefoxDriver(profile);
		} else if(browser.equalsIgnoreCase("chrome")){
			//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",GetPath.getBasePath()+ "\\Setup\\chromedriver.exe");		
		//	driver= new ChromeDriver();
			ChromeOptions coptions = new ChromeOptions();
		    coptions.addArguments("--disable-extensions");   
			driver= new ChromeDriver(coptions);
		} else if(browser.equalsIgnoreCase("ie")){
			//System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+ "\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver",GetPath.getBasePath()+ "\\Setup\\IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			driver= new InternetExplorerDriver(capabilities);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get(datatable.getCellData("SetUp", 0, 4));
	
		
		
	}

	
	public List<WebElement> readObjectsN(String key) throws IOException{
		  String strControllerPath = System.getProperty("user.dir")
		    + "\\OR.xlsx";
		  datatable = new Xls_Reader(strControllerPath);
		  Object[][] data= datatable.getData("Elements");
		  for(int row=0; row<data.length; row++){
		   if(data[row][0].equals(key)){
		    String identifier= (String) data[row][1];
		    String locator= (String) data[row][2];
		    if(identifier.equalsIgnoreCase("xpath")){
		     return driver.findElements(By.xpath(locator));
		    } else if(identifier.equalsIgnoreCase("id")){
		     return driver.findElements(By.id(locator));
		    } else if(identifier.equalsIgnoreCase("name")){
		     return driver.findElements(By.name(locator));
		    }
		   }
		  }
		  return null;
	}
	
	
	public List<WebElement> readObjects(String key) throws IOException{
		String value[]= readORFile(key).split("");
		String identifier= value[0];
		String locator= value[1];
		if(identifier.equalsIgnoreCase("xpath")){
			return driver.findElements(By.xpath(locator));
		} else if(identifier.equalsIgnoreCase("id")){
			return driver.findElements(By.id(locator));
		} else if(identifier.equalsIgnoreCase("name")){
			return driver.findElements(By.name(locator));
		}
		return null;
	}
	
	
	/*public WebElement fluentWait(final By locator){
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	        .withTimeout(30, TimeUnit.SECONDS)
	        .pollingEvery(5, TimeUnit.SECONDS)
	        .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	            public WebElement apply(WebDriver driver) {
	                return driver.findElement(locator);
	            }
	        }
	    );
	    return foo;
	};*/
	
	public String readConfigFile(String key) throws IOException{
		Properties p= new Properties();
		FileInputStream fs= new FileInputStream(GetPath.getBasePath()+"\\Setup\\config.properties");
		p.load(fs);
		return (String)p.get(key);
	}
	
	public String readORFile(String key) throws IOException{
		Properties p= new Properties();
		FileInputStream fs= new FileInputStream(GetPath.getBasePath()+"\\Setup\\OR.properties");
		p.load(fs);
		return (String)p.get(key);
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	public static void takeScreenShot(String filePath) throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(filePath));
	}
	
	public static boolean loadController() {

		try {
			String strControllerPath = GetPath.getBasePath()
					+ "\\Setup\\OR.xlsx";
			if (isFilePresent(strControllerPath)) {
				datatable = new Xls_Reader(strControllerPath);
				Reporter.log("The Controller sheet is successfully loaded");
				return true;
			} else {
				Reporter.log("The Controller sheet loading is failed");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Some Exception occured in loading the Configuration file");
			Assert.fail("Some Exception occured in loading the Configuration file");
			return false;
		}
	}
	
	public static boolean isFilePresent(String strFilePath) {
		try {
			if ((strFilePath).trim() == "") {
				Reporter.log("The passed file path paramenter is blank");
				return false;
			} else {
				File file = new File(strFilePath);
				boolean exists = file.exists();
				if (exists) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	public void WriteConfigFile(String key, String value)
    {
    try {
           Properties p = new Properties();
           p.setProperty(key, value);
           File file = new File(GetPath.getBasePath()+"//Setup//config.properties");
           FileOutputStream fileOut = new FileOutputStream(file,true);
           p.store(fileOut, " ");
           fileOut.close();
    }
    catch (FileNotFoundException e) {
           e.printStackTrace();
           } catch (IOException e) {
           e.printStackTrace();
           }
    }
	/*@AfterMethod
	public void teardown(){
		driver.close();				 
	}*/
}
