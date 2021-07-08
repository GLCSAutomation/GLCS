package base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import reusable.ActionoOnElements;

public class BaseLogin extends ActionoOnElements {
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void launch() throws Exception 
	{

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	 	ChromeOptions options = new ChromeOptions();
	 	options.addArguments("incognito");
	 	capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		System.setProperty("webdriver.chrome.driver", getDirectory + "//Driver//Chrome//chromedriver.exe");
		driver = new ChromeDriver(capabilities);
		driver.get("http://testsol.gscmaven.in/#/login//");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void close() throws InterruptedException
	{
		//driver.close();
	}

}
