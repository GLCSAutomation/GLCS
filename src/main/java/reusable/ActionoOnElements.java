package reusable;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionoOnElements extends ExcelData{

	Properties prop;
	protected WebDriver driver;
	WebElement ele;
	protected int ele_count;
	String input;
	JavascriptExecutor js;
	protected String propertyFileName;

	protected String readPropertiesFile(String propText) {
		prop = new Properties();
		InputStream input = null;
		try {
			switch(propertyFileName) {
			case("PurchaseOrder"):
				input = new FileInputStream(getDirectory + "\\Properties File\\OMS\\PurchaseOrder.properties");
			break;
			case("SaleOrder"):
				input = new FileInputStream(getDirectory + "\\Properties File\\OMS\\SaleOrder.properties");
			break;
			case("MastersSKU"):
				input = new FileInputStream(getDirectory + "\\Properties File\\OMS\\MastersSKU.properties");
			break;
			case("GeneratePicklist"):
				input = new FileInputStream(getDirectory + "\\Properties File\\WMS\\GeneratePicklist.properties");
			break;
			case("Picking"):
				input = new FileInputStream(getDirectory + "\\Properties File\\WMS\\Picking.properties");
			break;
			case("Packing"):
				input = new FileInputStream(getDirectory + "\\Properties File\\WMS\\Packing.properties");
			break;
			case("GenerateManifest"):
				input = new FileInputStream(getDirectory + "\\Properties File\\WMS\\Manifest.properties");
			break;
			} prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(propText);
	}

	protected void input(String xpath) throws Exception
	{	
		input = readExcelInput(xpath);
		xpath = readPropertiesFile(xpath);
		waitUntilElementFound(xpath);
		driver.findElement(By.xpath(xpath)).sendKeys(input);
	}

	protected void inputAndSelect (String value, String selectValue ) throws Exception
	{	
		input = readExcelInput(value);
		value = readPropertiesFile(value);
		waitUntilElementFound(value);
		driver.findElement(By.xpath(value)).sendKeys(input);
		waitForSecond(1);
		driver.findElement(By.xpath(readPropertiesFile(selectValue))).click();
	}

	protected void click_on_Element(String xpath) throws Exception {
		if (xpath.equalsIgnoreCase("SelectOrderCheckBoxToGenearetePicklist"))
		{
			xpath = readPropertiesFile("SelectOrderBy") + readExcelInput("SystemOrderNo") + readPropertiesFile("CheckBox");
			waitUntilElementFound(xpath);
			driver.findElement(By.xpath(xpath)).click();
		}
		else if (xpath.equalsIgnoreCase("SelectDefaultBin"))
		{
			Thread.sleep(2000);
			xpath = readPropertiesFile(xpath);
			waitUntilElementFound(xpath);
			ele = driver.findElement(By.xpath(xpath));
			driver.findElement(By.xpath(xpath)).click();
		}
		else 
		{
			Thread.sleep(2000);
			xpath = readPropertiesFile(xpath);
			waitUntilElementFound(xpath);
			driver.findElement(By.xpath(xpath)).click();
		}
	}

	protected void select_From_Dropdown(String xpath) throws Exception 
	{
		input = readExcelInput(xpath);
		xpath = readPropertiesFile(xpath);
		waitUntilElementFound(xpath);
		ele = driver.findElement(By.xpath(xpath));
		Select select = new Select(ele);
		select.selectByVisibleText(input);
	}

	protected void scrollTillElementFound(String xpath) throws Exception
	{
		xpath = readPropertiesFile(xpath);
		waitUntilElementFound(xpath);
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath(xpath)));
	}

	protected void waitUntilElementFound(String xpath) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

	}

	protected WebElement getElement(String xpath) throws Exception
	{
		xpath = readPropertiesFile(xpath); 
		waitUntilElementFound(xpath);
		return driver.findElement(By.xpath(xpath));
	}

	/* Function to return list of web elements */
	protected List<WebElement> getElementsList(String xpath) throws Exception
	{
		xpath = readPropertiesFile(xpath);
		waitUntilElementFound(xpath);
		List <WebElement> ele = driver.findElements(By.xpath(xpath));
		return ele;
	}

	/* Function to wait for a particular number of seconds */
	protected void waitForSecond (int sec) throws InterruptedException
	{
		Thread.sleep(sec*1000);
	}

	/* This function is upload a file from a particular window location */
	protected void uploadFile (String xpath) throws Exception
	{
		input = readExcelInput(xpath);
		click_on_Element(xpath);
		waitForSecond(1); 
		Robot robot = new Robot();
		StringSelection filepath = new StringSelection(input);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		waitForSecond(1);
		robot.keyPress(KeyEvent.VK_V);
		waitForSecond(1);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}


	/* This function is select a particular date from  calendar */
	protected void select_Date(String xpath) throws Exception {
		/* To get today's date */
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
		String currentDate[] = formattedDate.split("-");
		String currentDay = currentDate[0];
		currentDay = getMonthName(Integer.parseInt(currentDay));
		String currentMonth = currentDate[1];
		String currentYear = currentDate[2];

		/* removing 0 from 01 to make it as 1 excluding date as 10, 20 or 30 */
		if ((!currentDay.equalsIgnoreCase("10")) || (!currentDay.equalsIgnoreCase("20"))
				|| (!currentDay.equalsIgnoreCase("30"))) 
		{
			currentDay = currentDay.replace("0", "");
		}


		String input = readExcelInput(xpath); 
		String dateToInput[] = input.split("/");
		String day = dateToInput[1];
		String month = dateToInput[0];
		int monthNo = Integer.parseInt(month);
		month = getMonthName(monthNo);
		String year = "20" + dateToInput[2];

		click_on_Element(xpath);
		waitForSecond(2);
		String xpathForDate;

		if ((day.equalsIgnoreCase(currentDay)) && (month.equalsIgnoreCase(currentMonth))
				&& (year.equalsIgnoreCase(currentYear))) {
			xpathForDate = "//tr/td/span[contains(text(),'" + currentMonth + " " + currentYear + 
					"')]/parent::td/parent::tr/parent::tbody/descendant::td/span[text()='" + currentDay + "']";
			waitUntilElementFound(xpathForDate);
			driver.findElement(By.xpath(xpathForDate)).click();	

		} else {
			xpathForDate = "//tr/td/span[contains(text(),'" + currentMonth + " " + currentYear + "')]";
			waitUntilElementFound(xpathForDate);
			driver.findElement(By.xpath(xpathForDate)).click();

			if (monthNo <= 6) {
				xpathForDate = "//tbody/tr/td[contains(text(),'" + year + "')]/parent::tr/parent::tbody/tr[1]/td/span[contains(text(),'" +
						month + "')]";
				waitUntilElementFound(xpathForDate);
				driver.findElement(By.xpath(xpathForDate)).click();

			} else {
				xpathForDate = "//tbody/tr/td[contains(text(),'" + year + "')]/parent::tr/parent::tbody/tr[2]/td/span[contains(text(),'" +
						month + "')]";
				waitUntilElementFound(xpathForDate);
				driver.findElement(By.xpath(xpathForDate)).click();
			}
			xpathForDate = "//tr/td/span[contains(text(),'" + month + " " + year	 + 
					"')]/parent::td/parent::tr/parent::tbody/descendant::td/span[text()='" + day + "']";
			waitUntilElementFound(xpathForDate);
			driver.findElement(By.xpath(xpathForDate)).click();	
		}
	}

	/* Function to return month short name */
	protected static String getMonthName(int monthValue)
	{
		String monthShortName = null;
		DateFormatSymbols dateFormatSymbolsobject = new DateFormatSymbols();
		String[] shortFormatMonthsNames = dateFormatSymbolsobject.getShortMonths();
		monthValue-=1;

		for (int i = 0; i < (shortFormatMonthsNames.length-1); i++) 
		{
			if (monthValue == i)
			{
				monthShortName = shortFormatMonthsNames[i];
				break;
			}
		}
		return monthShortName;
	}

}

