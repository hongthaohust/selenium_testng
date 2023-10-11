package testng;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Topic_08_Listener implements ITestListener {
	
	@Override
	public void onTestFailure(ITestResult arg0) {
		System.out.println("Testcase này false, đã chụp hình và add vào report");
	}
	
	@Override
	public void onTestSkipped(ITestResult arg0) {
		System.out.println("Testcase này skip");
	}
	
	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.out.println("Testcase này pass");
	}

}
