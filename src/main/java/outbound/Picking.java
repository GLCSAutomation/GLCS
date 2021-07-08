package outbound;

import org.testng.annotations.Test;

import base.BaseLogin;

public class Picking extends BaseLogin {
	
	@Test
	void picking() throws Exception
	{
		propertyFileName = "Picking";
		sheetName = "Outbound";
		
		input("InputUserName"); 
		click_on_Element("GetClients");
		input("InputLoginPassword"); 
		click_on_Element("LoginButton");
		click_on_Element("WMSApp"); 
		select_From_Dropdown("SelectWH");
		click_on_Element("EnterWH");
		click_on_Element("ListOutboundScreens");
		click_on_Element("PickingScreen");
		click_on_Element("NewPickingTab");
		input("OrderToBePicked");
		click_on_Element("SearchPickingTask");
		click_on_Element("AssignPickingTaskToMe");
		click_on_Element("InProcessPickingTab");
		waitForSecond(1);;
		click_on_Element("InProcessPickingTab");
		click_on_Element("StartPickingTask");
		click_on_Element("PickDone");
		click_on_Element("SelectDefaultBin");
		click_on_Element("SubmitPickDone");
	}
}
