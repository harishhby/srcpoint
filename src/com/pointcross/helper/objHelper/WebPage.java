package com.pointcross.helper.objHelper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pointcross.sharedclass.BaseUtil;

public class WebPage extends BaseUtil {

	public void type(String key, String value) throws IOException {
		try {
			if (readObject(key).isEnabled()) {
				readObject(key).clear();
				readObject(key).sendKeys(value);
			}
		} catch (Exception e) {
			takeScreenShot("Error_"+ key+".jpeg");
			System.out.println("Typing failed on the key " + key);
		}
	}
	
	public void Type(WebElement key, String value) throws IOException {
		  try {
		   if (key.isEnabled()) {
		    (key).clear();
		    (key).sendKeys(value);
		   }
		  } catch (Exception e) {
		   takeScreenShot("Error_"+ key+".jpeg");
		   System.out.println("Typing failed on the key " + key);
		  }
		 }
	public void Clear(String key) throws IOException {
		try {			
			readObject(key).clear();
		} catch (Exception e) {
			takeScreenShot("Error_"+ key+".jpeg");
			System.out.println("Typing failed on the key " + key);
		}
	}
	public void CompleteClear(String key) throws IOException {
		try {			
			readObject(key).sendKeys(Keys.CONTROL+"a");
			readObject(key).sendKeys(Keys.DELETE);
		} catch (Exception e) {
			takeScreenShot("Error_"+ key+".jpeg");
			System.out.println("Typing failed on the key " + key);
		}
	}
	
	public void typeonly(String key, String value) throws IOException {
		try {
			if (readObject(key).isEnabled()){	
				readObject(key).sendKeys(value);
			}
			}catch (Exception e) {
			takeScreenShot("Error_"+ key+".jpeg");
			System.out.println("Typing failed on the key " + key);
		}
	}
	
	public void keyRight() throws AWTException, InterruptedException{
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_RIGHT);
		r.keyRelease(KeyEvent.VK_RIGHT);
	}
	
	
	public void click(String key) throws IOException {
		try {
			if (readObject(key).isEnabled()) {
				readObject(key).click();
			}
		} catch (Exception e) {
			takeScreenShot("Error_"+ key+".jpeg");
			System.out.println("Click failed on the key " + key);
		}
	}
	
	public void selectByVisibleText(String key, String value) throws IOException{
		Select select= new Select(readObject(key));
		select.selectByVisibleText(value);
	}
	
	public void selectByValue(String key, String value) throws IOException{
		Select select= new Select(readObject(key));
		select.selectByValue(value);
	}
	
	
	public String getText(String key){
		try{
			return readObject(key).getText();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Get text failed on the key "+ key);
		}
		return null;
	}
	

	
	public String getAttribute(String key){
		try{
			return readObject(key).getAttribute("value");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Get Attribute failed on the key "+ key);
		}
		return null;
	}
	
	public void keyTab() throws AWTException, InterruptedException{
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
	}
	
	public void keyDown() throws AWTException, InterruptedException{
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
	
	}
	
	public void HandleAlert(){
		Alert alt=null;
		try{
		alt=driver.switchTo().alert();
		}catch(Exception e){
			alt.accept();
		}
		
	}
	public void keyEnter() throws AWTException, InterruptedException{
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	 
	 public void rightClick(String key) throws IOException, InterruptedException{
			WebElement ele=readObject(key);
			Actions action = new Actions(driver).contextClick(ele);
			action.build().perform();

		}
	}
