package outbound;

import org.testng.annotations.Test;

import base.BaseLogin;

public class GenerateManifestAndHandover extends BaseLogin{
	
	@Test()
	void generateManifestAndHandOver() throws Exception
	{
		propertyFileName = "GenerateManifest";
		sheetName = "Outbound";
		
		input("InputUserName"); 
		click_on_Element("GetClients");
		input("InputLoginPassword"); 
		click_on_Element("LoginButton");
		click_on_Element("WMSApp"); 
		select_From_Dropdown("SelectWH");
		click_on_Element("EnterWH");
		click_on_Element("ListOutboundScreens");
		click_on_Element("SippingScreen");
		click_on_Element("NewShippingTab");
		input("OrderToBeShipped");
		click_on_Element("SearchShippingTask");
		//click_on_Element("AssignShippingTaskToMe");
		click_on_Element("InProcessShippingTab");
		//click_on_Element("EditAWB");
		//add_manifestDetails();
		//generate_manifest();
		click_on_Element("Handover");
		//generate_gatepass();
		uploadFile("FileUpload");
		//click_on_Element("HandoverDone");
	}
	
	
	void add_manifestDetails() throws Exception
	{
		click_on_Element("EditAWB");
		click_on_Element("CommonAWBForBoxes");
		input("MasterAWB");
		select_From_Dropdown("Transporter");
		input("Mode");
		input("ChildAWBNo");
		input("BoxNo");
		input("BagNo");
		click_on_Element("SubmitManifestDetails");	
	}
	
	void generate_manifest() throws Exception
	{
		click_on_Element("InputManifestDetails");
		input("BoxId");
		waitForSecond(1);
		click_on_Element("GenerateManifest");
		
	}
	
	void generate_gatepass() throws Exception
	{
	click_on_Element("Gatepass");
	input("VehicleNo");
	select_From_Dropdown("VehicleType");
	select_From_Dropdown("VehicleCondition");
	input("DriverName");
	input("NumberOfBoxes");
	select_From_Dropdown("Dock");
	click_on_Element("GenerateGatePass");
	waitForSecond(2);
	}
}
	
