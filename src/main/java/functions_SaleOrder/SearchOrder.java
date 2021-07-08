package functions_SaleOrder;

import org.testng.annotations.Test;

import base.BaseLogin;

public class SearchOrder extends BaseLogin {

	@Test
	void search_SaleOrder() throws Exception
	{
		propertyFileName = "SaleOrder";
		sheetName = "OMSOrder_SO";	
		
		input("UserName");
		click_on_Element("GetPasswordInputField");
		input("Password");
		click_on_Element("Login");
		click_on_Element("OmsApp");
		click_on_Element("OrderType");
		click_on_Element("SaleOrder");
		//input("FilterMavenOrderNo");
		//input("FilterClientOrderNo");
		//input("FilterFMSOrderNo");
		//select_From_Dropdown("FilterWH");
		//inputAndSelect("FilterCustomerName", "SelectFilterCustomerName");
		//inputAndSelect("FilterSKU_Code", "SelectFilterSKU_Code");
		//select_From_Dropdown("FilterCategory");
		//select_From_Dropdown("FilterSalesChannel");
		//select_From_Dropdown("FilterOrderTag");
		select_Date("FilterStartDate");
		select_Date("FilterEndDate");
	}
}
