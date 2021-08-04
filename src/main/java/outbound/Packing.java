package outbound;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.BaseLogin;

public class Packing extends BaseLogin {

	@Test
	void packing() throws Exception
	{
		propertyFileName = "Packing";
		sheetName = "Outbound";

		input("InputUserName"); 
		click_on_Element("GetClients");
		input("InputLoginPassword"); 
		click_on_Element("LoginButton");
		click_on_Element("WMSApp"); 
		select_From_Dropdown("SelectWH");
		click_on_Element("EnterWH");
		click_on_Element("ListOutboundScreens");
		click_on_Element("PackingScreen");
		input("OrderToBePacked");
		click_on_Element("SearchPackingTask");
		//click_on_Element("AssignPackingtaskToMe");
		click_on_Element("InProcessPackingTab");
		click_on_Element("StartPackingTask");
		click_on_Element("SkipBoxSelectionButton");
		addPackingSKUs();
		addBoxDetails();
	}

	void addPackingSKUs() throws Exception
	{
		click_on_Element("ChicletButtonToSeeAllSkus");
		List <WebElement> listSKU = getElementsList("GetPackingSKUs");
		List <WebElement> listAddButtonForSKU = getElementsList("SKUAddButton");
		List <WebElement> listSKUQty = getElementsList("PackSKURemainingQty");
		int countSKU = listSKU.size();
		int remainingQty; 
		//String[] packingSKU = new String[countSKU];
		if (countSKU == 1)
		{
			waitForSecond(1);
			getElement("SKUAddButton").click();
			remainingQty = Integer.parseInt(getElement("PackSKURemainingQty").getText());
			for (int i = 0 ; i < remainingQty; i++)
			{
				click_on_Element("ChicletButtonToSeeAllSkus");
				waitForSecond(1);
				getElement("SKUAddButton").click();
			}
		}
		else 
		{
			for (int a = 0; a < countSKU; a++)
			{
				waitForSecond(1);
				listAddButtonForSKU.get(a).click();
				remainingQty = Integer.parseInt(listSKUQty.get(a).getText());
				for (int b = 0; b < remainingQty-1; b++ ) 
				{ 
					click_on_Element("ChicletButtonToSeeAllSkus");
					waitForSecond(1);
					listAddButtonForSKU.get(a).click(); 
				}
				if (a < countSKU-1)
				{
					click_on_Element("ChicletButtonToSeeAllSkus");
				}
			}
		}
	}
	
	void addBoxDetails() throws Exception
	{
		if (readExcelInput("BoxType")!= null)
		{
			click_on_Element("MarkBoxAsFilled");
			select_From_Dropdown("BoxType");
			input("BoxWeight");
			click_on_Element("SubmitBoxDetails");	
		}
		click_on_Element("MarkBoxAsFilled");
		input("BoxLength");
		input("BoxWidth");
		input("BoxHeight");
		input("BoxWeight");
		click_on_Element("SubmitBoxDetails");
	}
}
