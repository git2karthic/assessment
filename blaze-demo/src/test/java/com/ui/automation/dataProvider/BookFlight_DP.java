package com.ui.automation.dataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.ui.automation.utility.ExcelManager;

public class BookFlight_DP {

	@DataProvider(name = "DP_Bookflight")
	public static Object[][] DP_Bookflight() throws IOException {
		List<String> list = GetTestData("TC_001");
		Object[][] obj = new Object[list.size()][17];
		for(int ilist = 0; ilist < list.size(); ilist++) {
			obj[ilist][0] = list.get(ilist).split(";")[0];
			obj[ilist][1] = list.get(ilist).split(";")[1];
			obj[ilist][2] = list.get(ilist).split(";")[2];
			obj[ilist][3] = list.get(ilist).split(";")[3];
			obj[ilist][4] = list.get(ilist).split(";")[4];
			obj[ilist][5] = list.get(ilist).split(";")[5];
			obj[ilist][6] = list.get(ilist).split(";")[6];
			obj[ilist][7] = list.get(ilist).split(";")[7];
			obj[ilist][8] = list.get(ilist).split(";")[8];
			obj[ilist][9] = list.get(ilist).split(";")[9];
			obj[ilist][10] = list.get(ilist).split(";")[10];
			obj[ilist][11] = list.get(ilist).split(";")[11];
			obj[ilist][12] = list.get(ilist).split(";")[12];
			obj[ilist][13] = list.get(ilist).split(";")[13];
			obj[ilist][14] = list.get(ilist).split(";")[14];
			obj[ilist][15] = list.get(ilist).split(";")[15];
			obj[ilist][16] = list.get(ilist).split(";")[16];
		}
		
		return obj;
	}

	public static List<String> GetTestData(String testCaseName) throws IOException {

		List<String> list = new ArrayList<String>();
		String testDataSheet = "Test_Data_Sheet";

		ExcelManager xl = new ExcelManager();
		int rowCount = xl.getRowCount(testDataSheet);

		for (int irow = 0; irow <= rowCount; irow++) {
			if (xl.getCellData(testDataSheet, irow, 4).equalsIgnoreCase("Y")
					&& xl.getCellData(testDataSheet, irow, 1).equalsIgnoreCase(testCaseName)) {
				String testCaseId = xl.getCellData(testDataSheet, irow, 1);
				String testCaseDescription = xl.getCellData(testDataSheet, irow, 2);
				String iteration = xl.getCellData(testDataSheet, irow, 3);
				String departureCity = xl.getCellData(testDataSheet, irow, 5);
				String destinationCity = xl.getCellData(testDataSheet, irow, 6);
				String searchUsing = xl.getCellData(testDataSheet, irow, 7);
				String searchValue = xl.getCellData(testDataSheet, irow, 8);
				String name  = xl.getCellData(testDataSheet, irow, 9); 
				String address = xl.getCellData(testDataSheet, irow, 10);
				String city = xl.getCellData(testDataSheet, irow, 11);
				String state = xl.getCellData(testDataSheet, irow, 12);
				String zipCode = xl.getCellData(testDataSheet, irow, 13);
				String cardType = xl.getCellData(testDataSheet, irow, 14);
				String cardNumber = xl.getCellData(testDataSheet, irow, 15);
				String month = xl.getCellData(testDataSheet, irow, 16);
				String year = xl.getCellData(testDataSheet, irow, 17);
				String nameOnCard = xl.getCellData(testDataSheet, irow, 18);
				list.add(testCaseId + ";" + testCaseDescription + ";" + iteration + ";" + departureCity + ";" + destinationCity + ";" + searchUsing + ";" + searchValue + ";" +
						name + ";" + address + ";" + city + ";" + state + ";" + zipCode + ";" + cardType + ";" + cardNumber + ";" + month + ";" + year + ";" + nameOnCard);
			}
		}

		return list;

	}

}
