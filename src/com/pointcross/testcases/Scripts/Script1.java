package com.pointcross.testcases.Scripts;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.pointcross.config.GetPath;
import com.pointcross.helper.objHelper.TestUtil;
import com.pointcross.helper.objHelper.WebPage;
import com.pointcross.helper.objHelper.Xls_Reader;
import com.pointcross.helper.pageHelper.ScriptPage1;
import com.pointcross.sharedclass.BaseUtil;


public class Script1  extends WebPage {
	BaseUtil bc=new BaseUtil();
		
	@Test(priority=1)
	public void Test_1() throws IOException, InterruptedException, AWTException
	{
		ScriptPage1 tp=new ScriptPage1();
		Xls_Reader datatable=new Xls_Reader(GetPath.getBasePath()+"\\TestData\\ProjectTestData.xlsm");
		System.out.println("Inside Script 1-Before");
		if(!TestUtil.isExecutable("Test_1", datatable))
			throw new SkipException("Run Mode Set To NO");
		System.out.println("Inside Script 1-After");		
		tp.initiate();
		tp.clickOnLoginLnk();
		
		System.out.println("EOS");
		
	}
}