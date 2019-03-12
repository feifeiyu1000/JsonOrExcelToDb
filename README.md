json及excel数据通过groovy脚本入库mysql
===========================

程序入口：JsonOrExcelToDb
------
 * 1：testJsonToDB json数据导入到db
 * 2：testExcelToDB excel数据导入到db
 
       *XSSF读取Excel2007
       *HSSF读取Excel2003
       *从windows导出的excel，需要再mac中新建excel并copy进入，再导入db【原因可能为：office 平台差异】
       
 