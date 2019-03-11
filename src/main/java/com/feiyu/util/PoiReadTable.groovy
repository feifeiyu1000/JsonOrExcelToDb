package com.feiyu.util

import com.feiyu.db.GroovyJDBC
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 * 读取上篇中的xls文件的内容，并打印出来
 * @author Administrator
 *
 */
class PoiReadTable {


	//通过对单元格遍历的形式来获取信息 ，这里要判断单元格的类型才可以取出值
	static void readTable(String pathName,String sql) throws Exception {
		InputStream ips = new FileInputStream(pathName)
		XSSFWorkbook wb = new XSSFWorkbook(ips)
		XSSFSheet sheet = wb.getSheetAt(0)
		def sqlDb = new GroovyJDBC().db()
		int rowLength = sheet._rows.size()
		for (int i = 1; i < rowLength; i++) {
			XSSFRow row = (XSSFRow) sheet.getRow(i)
			if ("示例".equals(row.getCell(0).toString()) || ''.equals(row.getCell(0).toString()) || null == row.getCell(0)) {
				continue
			}
			// 插入db
			sqlDb.execute(sql, row.getCell(1).toString())
		}
	}
}

