package nilseleniuminc.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListeners implements IRetryAnalyzer{
	int cnt = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(cnt < maxTry) {
			cnt++;
			return true;
		}
		return false;
	}

}
