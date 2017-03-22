package com.pointcross.helper.objHelper;

import com.pointcross.sharedclass.BaseUtil;



public class TestUtil extends BaseUtil
{
	//public static String datatable1 = (GetPath.getBasePath()+"\\ProjectTestData.xlsm");
	
	public static boolean isExecutable(String testName, Xls_Reader datatable)
	{
		for(int rNum=2;rNum<=datatable.getRowCount("RunModeTestCase");rNum++)
		{
			if(testName.equals(datatable.getCellData("RunModeTestCase", "TC_Name", rNum)))
			{
				if(datatable.getCellData("RunModeTestCase", "RunMode", rNum).equalsIgnoreCase("Y"))
					return true;
					else
					return false;
			}
		}
		return false;
		
	}

}