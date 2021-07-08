package orders_OMS;

import org.testng.annotations.Test;

import base.BaseLogin;

public class PurchaseOrder extends BaseLogin {

	@Test
	void createPO() throws Exception
	{
		propertyFileName = "PurchaseOrder";
		sheetName = "OMSOrder_PO";	

		input("UserName");
		click_on_Element("GetPasswordInputField");
		input("Password");
		click_on_Element("Login");
		click_on_Element("OmsApp");
		click_on_Element("Purchases");
		click_on_Element("PO");
		click_on_Element("AddNewPO");
		click_on_Element("Don'tWantToScanSku(s)");
		click_on_Element("NewPOPage");
		select_From_Dropdown("Warehouse");
		select_From_Dropdown("Vendor");
		inputSKUDetails();
		select_From_Dropdown("ShippingOwner");
		select_From_Dropdown("VendorAddress");
		scrollTillElementFound("ExpectedDelivery_Date");
		select_Date("ExpectedDelivery_Date");
	}

	void inputSKUDetails() throws Exception
	{
		readInputSKUDetailsData = true;
		int count = sheet.getLastRowNum();
		for (getSKU_At_Row = 1; getSKU_At_Row < count ; getSKU_At_Row++)
		{
			input("SKU_Code");
			input("SKU_Qty");
			input("SKU_Price");	
			input("SKU_MRP");
			click_on_Element("AddSKU");
		}
		readInputSKUDetailsData = false;
	}
}	