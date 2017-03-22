package com.pointcross.testcases.Scripts;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pointcross.config.GetPath;
import com.pointcross.helper.objHelper.TestUtil;
import com.pointcross.helper.objHelper.WebPage;
import com.pointcross.helper.pageHelper.ScriptPage1;
import com.pointcross.sharedclass.BaseUtil;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
	MethodListener.class })

public class Script1  extends WebPage {
	BaseUtil bc=new BaseUtil();
	{
		System.setProperty("atu.reporter.config",GetPath.getBasePath()+"\\Setup\\atu.properties");
	}
	
	
	@Test(priority=1)
	public void Manage_Study_Life_Cycle_Test_1() throws IOException, InterruptedException, AWTException
	{
		ScriptPage1 tp=new ScriptPage1();
		//Xls_Reader datatable=new Xls_Reader(GetPath.getBasePath()+"\\ProjectTestData.xlsm");
		System.out.println("Inside Script 1-Before");
		if(!TestUtil.isExecutable("Script_1", datatable))
			throw new SkipException("Run Mode Set To NO");
		System.out.println("Inside Script 1-After");

		setAuthorInfoForReports();
		setWebDriverReport();
		
		tp.initiate();
		
	}
}