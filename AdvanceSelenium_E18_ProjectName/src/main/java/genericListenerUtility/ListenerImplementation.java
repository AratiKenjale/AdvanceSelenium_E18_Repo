package genericListenerUtility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import genericBaseclassUtility.BaseClass;

public class ListenerImplementation implements ITestListener,ISuiteListener
{
	public static ExtentReports report=null;
	public ExtentSparkReporter spark=null;
	public static ExtentTest test=null;
	
	@Override
	public void onStart(ISuite suite)
	{
		System.out.println("Report configuration");
		Date date= new Date();
		SimpleDateFormat sim= new SimpleDateFormat("HH-mm-ss");
		String time = sim.format(date);
		System.out.println(time);
		spark=new ExtentSparkReporter("./AdvancedReports/report"+time+" .html");
		spark.config().setDocumentTitle("Ninza_CRM_Project_Results");
		spark.config().setReportName("CRM_Report");
		spark.config().setTheme(Theme.DARK);
		
		report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("Browser", "Chrome-135");
		report.setSystemInfo("Env", "Production");
	}

	@Override
	public void onFinish(ISuite suite) 
	{
		System.out.println("Report backup");
		Date date= new Date();
		SimpleDateFormat sim= new SimpleDateFormat("HH-mm-ss");
		String time = sim.format(date);
		System.out.println(time);
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		System.out.println(result.getMethod().getMethodName()+" starts");
		test=report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, result.getMethod().getMethodName()+" Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		System.out.println(result.getMethod().getMethodName()+" ends");
		test.log(Status.PASS, result.getMethod().getMethodName()+" ended");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		String testcaseName = result.getMethod().getMethodName();
		TakesScreenshot ts= (TakesScreenshot)BaseClass.sdriver;
		Date date= new Date();
		SimpleDateFormat sim= new SimpleDateFormat("HH-mm-ss");
		String time = sim.format(date);
		String temp = ts.getScreenshotAs(OutputType.BASE64);
		File temp1 = ts.getScreenshotAs(OutputType.FILE);
		File dest= new File("./screenshots/"+ testcaseName+"_"+time+" .png");
		try 
		{
			FileHandler.copy(temp1, dest);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		test.addScreenCaptureFromBase64String(temp,"Failed"+testcaseName);
		
		test.log(Status.FAIL, result.getMethod().getMethodName()+" Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) 
	{
		
	}

	@Override
	public void onStart(ITestContext context)
	{
		
	}

	@Override
	public void onFinish(ITestContext context)
	{
		report.flush();
	}

}
