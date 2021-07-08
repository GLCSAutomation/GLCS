package reusable;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData extends SKUDetails{

	protected boolean readInputSKUDetailsData = false; 
	protected String readExcelInput(String valueForXpath) throws Exception {

		if (readInputSKUDetailsData == true)
		{
			readOrderSKUDetails(valueForXpath);
		}
		else
		{
			src = new File(getDirectory + "//src/test/resources//ExcelData.xlsx");
			fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			getSheetName();

			for (int a = 0; a<=sheet.getLastRowNum(); a++)
			{
				cell = sheet.getRow(a).getCell(0);
				inputValue = (formatter.formatCellValue(cell)).trim();
				if (valueForXpath.equalsIgnoreCase(inputValue))
				{
					cell = sheet.getRow(a).getCell(1);
					inputValue = formatter.formatCellValue(cell);
					break;
				}
				else
				{
					continue;
				}
			}
		}
		wb.close();
		return inputValue;
	}
}


