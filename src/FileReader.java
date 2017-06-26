import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class FileReader {

	public static void firstSheet(String sourceFile) throws IOException {

		String fileName = sourceFile;

		ArrayList<String> licenseNames = new ArrayList<String>();
		ArrayList<String> expiryDate = new ArrayList<String>();

		File file = new File(fileName);

		try {
			POIFSFileSystem fs = new POIFSFileSystem(file);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(2);
			HSSFRow row;
			HSSFCell licenseName;
			HSSFCell cell1;
			HSSFCell cell2;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			int cols = 0; // No of columns
			int tmp = 0;

			// This trick ensures that we get the data properly even if it doesn't start from first few rows
			for(int i = 0; i < rows; i++) {
				row = sheet.getRow(i);
				if(row != null) {
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if(tmp > cols) cols = tmp;
				}
			}
			for(int r = 5; r < rows; r++) {
				row = sheet.getRow(r);
				if(row != null) {
					licenseName = row.getCell((short)0);
					cell1 = row.getCell((short)2);
					cell2 = row.getCell((short)4);

					if(cell1.toString().compareTo(cell2.toString()) > 0){
						licenseNames.add(licenseName.toString());
						expiryDate.add(cell1.toString());
						//System.out.println(licenseName.toString() +" "+cell1.toString());
					}
					else if(cell2.toString().compareTo(cell1.toString()) > 0){
						licenseNames.add(licenseName.toString());
						expiryDate.add(cell2.toString());
						//System.out.println(licenseName.toString() +" "+cell2.toString());
					}

				}
			}
			/**
			 * Having some issue with the CMW2 Lic sheet, getting null pointer exception
			 */
			//secondSheet(licenseNames, expiryDate, file);
		} catch(Exception ioe) {
			ioe.printStackTrace();
		}
	}
	
	
	/**
	 * This function is to check the licenses of CMW 2 Lic sheet; this is created because I did not have enough
	 * time to design and implement a generic implementation that works for all sheets of the same format.
	 * @param names
	 * @param dates
	 * @param file
	 */

	public static void secondSheet(ArrayList<String> names, ArrayList<String> dates, File file){

		ArrayList<String> licenseNames = names;
		ArrayList<String> expiryDate = dates;

		try {
			POIFSFileSystem fs = new POIFSFileSystem(file);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(3);
			HSSFRow row;
			HSSFCell licenseName;
			HSSFCell cell1;
			HSSFCell cell2;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			int cols = 0; // No of columns
			int tmp = 0;

			// This trick ensures that we get the data properly even if it doesn't start from first few rows
			for(int i = 0; i < rows; i++) {
				row = sheet.getRow(i);
				if(row != null) {
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if(tmp > cols) cols = tmp;
				}
			}
			for(int r = 4; r < rows; r++) {
				row = sheet.getRow(r);
				if(row != null) {
					licenseName = row.getCell((short)0);
					cell1 = row.getCell((short)2);
					cell2 = row.getCell((short)4);

					if(cell1.toString().compareTo(cell2.toString()) > 0){
						licenseNames.add(licenseName.toString());
						expiryDate.add(cell1.toString());
						System.out.println(licenseName.toString() +" "+cell1.toString());
					}
					else if(cell2.toString().compareTo(cell1.toString()) > 0){
						licenseNames.add(licenseName.toString());
						expiryDate.add(cell2.toString());
						System.out.println(licenseName.toString() +" "+cell2.toString());
					}
				}

			}
		} catch(Exception ioe) {
			ioe.printStackTrace();
		}
	}

	
	/**
	 * This is a function to check the contents of the array lists
	 * @param names
	 * @param dates
	 */

	public static void printList(ArrayList<String> names, ArrayList<String> dates){
		for(int i = 0; i < names.size(); i++){
			System.out.println(names.get(i)+" "+dates.get(i));
		}
	}
}
