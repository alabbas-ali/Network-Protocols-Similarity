package his.packet.comparing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperation {

	private File file;
	private XSSFWorkbook myWorkBook;
	private XSSFSheet mySheet;
	private FileInputStream myInput;

	public ExcelOperation(String outputfile) throws IOException {
		file = new File(outputfile);
		if( file.exists() ) {
			myInput = new FileInputStream(file);
			myWorkBook = new XSSFWorkbook(myInput);
		} else {
			System.out.println("The xlsx output file dosen't exests");
			throw new IOException();
		}
		
	}

	public void createSheet(int i) {
		mySheet = myWorkBook.createSheet("outputShhet " + i);
	}

	public void createHeadLine(int rownum) {
		XSSFRow row1 = mySheet.createRow(rownum);
		XSSFCell r1c1 = row1.createCell(0);
		r1c1.setCellValue("Protocole1");
		XSSFCell r1cm = row1.createCell(1);
		r1cm.setCellValue("String's length");
		XSSFCell r1c2 = row1.createCell(2);
		r1c2.setCellValue("Cosine");
		XSSFCell r1c3 = row1.createCell(3);
		r1c3.setCellValue("Jaccard");
		XSSFCell r1c4 = row1.createCell(4);
		r1c4.setCellValue("RBF");
		XSSFCell r1c5 = row1.createCell(5);
		r1c5.setCellValue("NGram");
		XSSFCell r1c6 = row1.createCell(6);
		r1c6.setCellValue("Needleman_Wunch");
		XSSFCell r1c7 = row1.createCell(7);
		r1c7.setCellValue("Smith_Waterman");
	}

	public void addRowInfo(String[] res, int rownum) {
		XSSFRow row = mySheet.createRow(rownum);
		for (int i = 0; i < res.length; i++) {
			XSSFCell r1c1 = row.createCell(i);
			r1c1.setCellValue(res[i]);
		}
	}

	public void save() throws IOException {
		myInput.close();
		FileOutputStream myOutput = new FileOutputStream(file);
		myWorkBook.write(myOutput);
		myOutput.close();
		System.out.println("Done");
	}

}
