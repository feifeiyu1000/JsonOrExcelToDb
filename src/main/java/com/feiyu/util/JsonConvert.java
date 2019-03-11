package com.feiyu.util;

import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by zdy on 2018/3/22.
 */
public class JsonConvert {

	/**
	 * 正常json输入
	 *
	 * @return
	 */
	public static String getJsonString() {
		// 读取原始json文件并进行操作和输出
		JSONArray dataJson = null;
		String laststr = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("/Users/xxxx/Desktop/test.json"));// 读取原始json文件
			String tempString;
			while ((tempString = br.readLine()) != null) {
				laststr += tempString;
			}
			dataJson = new JSONArray(laststr);// 创建一个包含原始json串的json对象
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataJson.toString();
	}
}