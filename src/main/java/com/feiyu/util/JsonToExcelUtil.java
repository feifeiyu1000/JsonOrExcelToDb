package com.feiyu.util;


import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import jxl.write.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * Created by zdy on 11/03/2019
 */
public class JsonToExcelUtil {
//	public static final String mavenInfos = JsonConvert.getJsonString();

	public static void parseJsonToExcel(String saveFileName, String sourceJson) {
		try {
			File filewrite = new File(saveFileName);
			filewrite.createNewFile();
			OutputStream os = new FileOutputStream(filewrite);
			JSONArray jsonArray = JSONArray.fromObject(sourceJson);
//            正常json转换
			createExcelByJsonArray(os, jsonArray);
			System.out.println("解析完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 正常json解析
	 *
	 * @param os
	 * @param jsonArray
	 * @param rootNodeName
	 * @throws WriteException
	 * @throws IOException
	 */
	public static void createExcelByJsonArray(OutputStream os, JSONArray jsonArray) throws WriteException, IOException {
		//创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		//创建新的一页
		WritableSheet sheet = workbook.createSheet("First Sheet", 0);
		JSONObject jsonObjectHeader = jsonArray.getJSONObject(0);
		String[] headers = createTableHeader(jsonObjectHeader, sheet);
		int size = jsonArray.size() + 1;
		for (int i = 1; i < size; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i - 1);
			int j = 0;
			for (String key : headers) {
				try {
					Label cellValue = new Label(j, i, jsonObject.get(key).toString());
					sheet.addCell(cellValue);
					j++;
				} catch (Exception e) {
					j++;
					continue;
				}
			}
		}
		//把创建的内容写入到输出流中，并关闭输出流
		workbook.write();
		workbook.close();
		os.close();
	}

	public static String[] createTableHeader(JSONObject jsonObjectHeader, WritableSheet sheet) throws WriteException {
		//遍历JSONObject中的key
		Iterator iterable = jsonObjectHeader.keys();
		String[] headers = new String[jsonObjectHeader.size()];
		int i = 0;
		while (iterable.hasNext()) {
			//创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
			String headerName = iterable.next().toString();
			Label cell = new Label(i, 0, headerName);
			sheet.addCell(cell);
			headers[i] = headerName;
			i++;
			System.out.println(headerName);
		}
		return headers;
	}


}
