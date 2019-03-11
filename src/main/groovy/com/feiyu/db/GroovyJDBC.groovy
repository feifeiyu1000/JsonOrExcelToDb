package com.feiyu.db

/**
 * Created by zdy on 2018/4/3.
 */
class GroovyJDBC {
	def db() {
		def db_url = "jdbc:mysql://localhost:3310/safety?useUnicode=true&useSSL=false&characterEncoding=utf-8"
		def username = "root"
		def password = "root"
		def driverClass = "com.mysql.jdbc.Driver"
		def sql = groovy.sql.Sql.newInstance(db_url, username, password, driverClass)
		sql
	}

}
