package reusable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SKUDetails {

	File src;
	FileInputStream fis;
	XSSFWorkbook wb;
	protected String sheetName;
	DataFormatter formatter = new DataFormatter();
	Cell cell;
	protected XSSFSheet sheet;
	protected String inputValue = null;
	protected int getSKU_At_Row;
	protected String getDirectory = System.getProperty("user.dir");

	String readOrderSKUDetails (String valueForXpath) throws IOException
	{
		src = new File(getDirectory + "//src/test/resources//InputSKUDetailsData.xlsx");
		fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
		getSheetName();

		for (int a = 0; a<=sheet.getRow(0).getPhysicalNumberOfCells(); a++)
		{
			cell = sheet.getRow(0).getCell(a);
			inputValue = formatter.formatCellValue(cell);
			if (valueForXpath.equalsIgnoreCase(inputValue))
			{
				cell = sheet.getRow(getSKU_At_Row).getCell(a);
				inputValue = formatter.formatCellValue(cell);	
				break;
			}
			else
			{
				continue;
			}
		}
		wb.close();	
		return inputValue;
	}

	void getSheetName()
	{
		switch(sheetName){
		case("OMSOrder_SO"): sheet = wb.getSheet("SO"); break;
		case("OMSOrder_PO"): sheet = wb.getSheet("PO"); break;
		case("OMSOrder_ST"): sheet = wb.getSheet("ST"); break;
		case("OMSOrder_SR"): sheet = wb.getSheet("SR"); break;
		case("Outbound"): sheet = wb.getSheet("Outbound"); break;
		}
	}
}
