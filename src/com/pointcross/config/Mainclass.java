package com.pointcross.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class Mainclass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> file= new ArrayList<String>();
		String path =GetPath.getBasePath()+"//Setup//testNg.xml";
		System.out.println("Path Is:"+path);
		file.add(File.separator+path);
		
		TestNG testNG=new TestNG();
		testNG.setTestSuites(file);
		testNG.run();
	}
}
