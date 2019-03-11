package com.feiyu

import com.feiyu.db.GroovyJDBC
import com.feiyu.util.JsonToExcelUtil
import com.feiyu.util.PoiReadTable
import net.sf.json.JSONArray
import net.sf.json.JSONObject
import org.junit.Test

/**
 * Created by zdy on 11/03/2019
 */
class JsonOrExcelToDb {

	@Test
	void testJsonToDB() {
		def sqlDb = new GroovyJDBC().db()
		def strMap = JsonToExcelUtil.mavenInfos
		JSONArray jsonArray = JSONArray.fromObject(strMap)
		int size = jsonArray.size() + 1
		for (int i = 1; i < size; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i - 1)
			def sql = "insert into hr.employee_info(eas_employee_id,eas_employee_no,duty_id) values (?,?,?)"
			sqlDb.execute(sql, jsonObject.get("person.id"), jsonObject.get("person.number"), jsonObject.get("position.number"))
		}
	}

	@Test
	void testExcelToDB() throws Exception {
		def pathName = "/data/sys.xls"
		def sql = "insert into safety.sys_enterprise(qymc) values (?)"
		PoiReadTable.readTable(pathName, sql)

		def pathName2 = "/data/stander.xls"
		def sql2 = "insert into safety.stander_enterprise(qymc) values (?)"
		PoiReadTable.readTable(pathName2, sql2)
	}
}
