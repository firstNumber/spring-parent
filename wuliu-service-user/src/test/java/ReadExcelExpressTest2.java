
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

public class ReadExcelExpressTest2 {

	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_XLSX = "xlsx";

	/***
	 * <pre>
	 * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类)
	 *   xls:HSSFWorkbook
	 *   xlsx：XSSFWorkbook
	 * &#64;param filePath
	 * &#64;return
	 * &#64;throws IOException
	 * </pre>
	 */
	private Workbook getWorkbook(String filePath) throws IOException {
		Workbook workbook = null;
		InputStream is = new FileInputStream(filePath);
		if (filePath.endsWith(EXTENSION_XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (filePath.endsWith(EXTENSION_XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		return workbook;
	}

	/**
	 * 文件检查
	 * 
	 * @param filePath
	 * @throws FileNotFoundException
	 * @throws FileFormatException
	 */
	private void preReadCheck(String filePath) throws FileNotFoundException, FileFormatException {
		// 常规检查
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException("传入的文件不存在：" + filePath);
		}

		if (!(filePath.endsWith(EXTENSION_XLS) || filePath.endsWith(EXTENSION_XLSX))) {
			throw new FileFormatException("传入的文件不是excel");
		}
	}

	/**
	 * 读取excel文件内容
	 * 
	 * @param filePath
	 * @throws FileNotFoundException
	 * @throws FileFormatException
	 */
	public List readExcel(String filePath) throws FileNotFoundException, FileFormatException {
		// 检查
		this.preReadCheck(filePath);
		// 获取workbook对象
		Workbook workbook = null;
		List list = new ArrayList();
		try {
			workbook = this.getWorkbook(filePath);
			// 读文件 一个sheet一个sheet地读取
			for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
				Sheet sheet = workbook.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				System.out.println("=======================" + sheet.getSheetName() + "=========================");
				String sheetname = sheet.getSheetName();
				if (sheet.getSheetName().contains("（")) {
					sheetname = sheetname.split("\\（")[0];
				}

				int firstRowIndex = sheet.getFirstRowNum();
				int lastRowIndex = sheet.getLastRowNum();

				// 读取首行 即,表头
				Row firstRow = sheet.getRow(firstRowIndex);
				for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
					Cell cell = firstRow.getCell(i);
					String cellValue = this.getCellValue(cell, true);
					System.out.print(" " + cellValue + "\t");
				}
				System.out.println("");

				// 读取数据行
				for (int rowIndex = firstRowIndex + 2; rowIndex <= lastRowIndex; rowIndex++) {
					Map<String, String> map = new HashMap<>();
					Row currentRow = sheet.getRow(rowIndex);// 当前行
					int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
					int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
					if (firstColumnIndex == -1 || lastColumnIndex == -1) {
						break;
					}
					for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
						Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
						String currentCellValue = this.getCellValue(currentCell, true);// 当前单元格的值
						try {
							BigDecimal bd = new BigDecimal(currentCellValue);
							String value = bd.setScale(3, BigDecimal.ROUND_HALF_UP)
									.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
							System.out.print(value + "\t");
							map.put("" + columnIndex, value);
							if (columnIndex == lastColumnIndex) {
								map.put("" + (columnIndex), "5000");
								System.out.print(5000 + "\t");
								map.put("" + (columnIndex + 1), sheetname);
								System.out.print(sheet.getSheetName() + "\t");
							}
						} catch (Exception e) {
							map.put("" + columnIndex, currentCellValue);
							if (columnIndex == lastColumnIndex) {
								map.put("" + (columnIndex), "5000");
								System.out.print(5000 + "\t");
								map.put("" + (columnIndex + 1), sheetname);
								System.out.print(sheet.getSheetName() + "\t");
							}
							System.out.print(currentCellValue + "\t");
						}
					}
					list.add(map);
					System.out.println("");
				}
				System.out.println("======================================================");

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
					return list;
				}
			}
		}
	}

	/**
	 * 取单元格的值
	 * 
	 * @param cell
	 *            单元格对象
	 * @param treatAsStr
	 *            为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
	 * @return
	 */
	private String getCellValue(Cell cell, boolean treatAsStr) {
		if (cell == null) {
			return "";
		}

		if (treatAsStr) {
			// 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
			// 加上下面这句，临时把它当做文本来读取
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}

		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

	public static void main(String[] args) {
		DBHelper db1 = new DBHelper();
		ReadExcelExpressTest2 t = new ReadExcelExpressTest2();
		String sql = "INSERT into driver_rule(`vehicleType`,`discription`,`length`,`width`,`height`,`maxWeight`,`maxVolume`,`initPrice`,`price`,`initKm`,`province`) VALUES ";
		try {
			List list = t.readExcel("D:\\lyz.xlsx");
			for (int i = 0; i < list.size(); i++) {
				Map m = (Map) list.get(i);
				sql += "('" + (m.get("0") == null ? "" : m.get("0")) + "'," + "'"
						+ (m.get("1") == null ? "" : m.get("1")) + "'," + ""
						+ (m.get("2") == null ? "" : m.get("2") + "*10") + "," + ""
						+ (m.get("3") == null ? "" : m.get("3") + "*10") + "," + ""
						+ (m.get("4") == null ? "" : m.get("4") + "*10") + "," + ""
						+ (m.get("6") == null ? "" : m.get("6") + "/1000") + "," + ""
						+ (m.get("5") == null ? "" : m.get("5")) + "," + "" + (m.get("7") == null ? "" : m.get("7"))
						+ "," + "" + (m.get("8") == null ? "" : m.get("8")) + "," + ""
						+ (m.get("9") == null ? "" : m.get("9")) + "," + "'" + (m.get("10") == null ? "" : m.get("10"))
						+ "'" + ")";
				if (i != list.size() - 1) {
					sql += ",";
				}
			}
			System.out.println(sql);
			db1.getStatement().execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
