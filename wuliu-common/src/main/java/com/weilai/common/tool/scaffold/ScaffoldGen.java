package com.weilai.common.tool.scaffold;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.weilai.common.tool.ScaffoldGenerator;

public class ScaffoldGen {

	private static final String NULLABLE = "NULLABLE";
	private static final String DECIMAL_DIGITS = "DECIMAL_DIGITS";
	private static final String COLUMN_SIZE = "COLUMN_SIZE";
	private static final String TYPE_NAME = "TYPE_NAME";
	private static final String MYSQL = "mysql";
	private static final String COLUMN_NAME = "COLUMN_NAME";
	private Connection conn;
	private String schema;
	private DatabaseMetaData metaData;
	private final String pkgName;
	private final String clzName;
	private final String tblName;

	public ScaffoldGen(String pkgName, String clzName, String tblName) {
		this.pkgName = pkgName;
		this.clzName = StringUtils.capitalize(clzName);
		this.tblName = tblName;
	}

	public void execute() {
		execute(false);
	}

	public void execute(boolean debug) {
		if (!initConnection()) {
			// system.out.println("数据库连接失败,请检查ClassPath路径下的配置文件" +
			// CONFIG_PROPERTIES);
			return;
		}
		TableInfo tableInfo = parseDbTable(this.tblName);
		if (tableInfo == null) {
			return;
		}

		ScaffoldBuilder sf = new ScaffoldBuilder(this.pkgName, this.clzName, tableInfo);
		List<FileGenerator> list = sf.buildGenerators();
		for (FileGenerator gen : list) {
			gen.execute(debug);
		}
	}

	private boolean initConnection() {
		String driver = null;
		String url = StringUtils.EMPTY;
		String user = StringUtils.EMPTY;
		String password = StringUtils.EMPTY;
		String schema = StringUtils.EMPTY;
		try {
			driver = "com.mysql.jdbc.Driver";
			url = ScaffoldGenerator.DB_URL;
			user = ScaffoldGenerator.DB_USERNAME;
			password = ScaffoldGenerator.DB_PASSWORD;
			if (StringUtils.isNotBlank(schema)) {
				this.schema = schema;
			}
			if (driver.toLowerCase().contains(MYSQL)) {
				this.schema = "information_schema";
			}
			if (StringUtils.isBlank(this.schema)) {
				this.schema = user;
			}
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Jdbc driver not found - " + driver);
			return false;
		}

		try {
			conn = DriverManager.getConnection(url, user, password);
			// conn = DriverManager.getConnection(url);
			if (conn == null) {
				System.out.println("Database connection is null");
				return false;
			}
			metaData = conn.getMetaData();
			if (metaData == null) {
				System.out.println("Database MetaData is null");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Database connect failed");
			e.printStackTrace();
		}
		return true;
	}

	private TableInfo parseDbTable(String tableName) {
		TableInfo tableInfo = new TableInfo(tableName);
		ResultSet rs = null;
		try {
			rs = metaData.getPrimaryKeys(null, schema, tableName);
			if (rs.next()) {
				tableInfo.setPrimaryKey(rs.getString(COLUMN_NAME));
			}
			if (rs.next()) {
				// system.out.println("该表为复合主键，不适用于代码脚手架生成工具");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Table " + tableName + " parse error.");
			e.printStackTrace();
			return null;
		}
		System.out.println("PrimaryKey : " + tableInfo.getPrimaryKey());

		try {
			rs = metaData.getColumns(conn.getCatalog(), schema, tableName, null);
			if (!rs.next()) {
				System.out.println("Table " + schema + "." + tableName + " not found.");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				String columnName = rs.getString(COLUMN_NAME);
				String columnType = rs.getString(TYPE_NAME);
				int datasize = rs.getInt(COLUMN_SIZE);
				int digits = rs.getInt(DECIMAL_DIGITS);
				int nullable = rs.getInt(NULLABLE);
				ColumnInfo colInfo = new ColumnInfo(columnName, columnType, datasize, digits, nullable);
				System.out.println("DB column : " + colInfo);
				System.out.println("Java field : " + colInfo.parseFieldName() + " / " + colInfo.parseJavaType());
				tableInfo.addColumn(colInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Table " + tableName + " parse error.");
		}
		return tableInfo;
	}

}
