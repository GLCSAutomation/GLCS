package outbound;

import org.testng.annotations.Test;

import base.BaseLogin;

public class GeneratePicklist extends BaseLogin {
	
	@Test
	void generatePicklist() throws Exception
	{
		propertyFileName = "GeneratePicklist";
		sheetName = "Outbound";
		
		input("InputUserName"); 
		click_on_Element("GetClients");
		input("InputLoginPassword"); 
		click_on_Element("LoginButton");
		click_on_Element("WMSApp"); 
		select_From_Dropdown("SelectWH");
		click_on_Element("EnterWH");
		click_on_Element("ListOutboundScreens");
		click_on_Element("ManageOutbound");
		input("SystemOrderNo"); 
		click_on_Element("SearchOrderNo");
		click_on_Element("SelectOrderCheckBoxToGenearetePicklist");
		//click_on_Element("GeneratePicklist");
	}
}
