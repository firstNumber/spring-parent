package com.weilai.common.tool.scaffold;

import org.apache.commons.lang.StringUtils;

public class ColumnInfo {
	private String name;
	private String type;
	private int size;
	private int digits;
	private boolean nullable;
	private final WordsParser wordsParser;

	public ColumnInfo(String name, String type, int size, int decimalDigits, int nullable) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.digits = decimalDigits;
		if (nullable == 1)
			this.nullable = true;
		if (ScaffoldUtil.isUpperCase(name) || name.contains(ScaffoldUtil.UNDER_LINE)) {
			this.wordsParser = new UnderlineSplitWordsParser();
		} else {
			this.wordsParser = new UncapitalizeWordsParser();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String parseJavaType() {
		String jdbcType = StringUtils.upperCase(getType());
		String result = "Integer";
		if (jdbcType.contains("CHAR")) {
			result = "String";
		}
		if (jdbcType.contains("CLOB")) {
			result = "String";
		}
		if (jdbcType.contains("TEXT")) {
			result = "String";
		}
		if (jdbcType.contains("DATE")) {
			result = "java.util.Date";
		}
		if (jdbcType.contains("DECIMAL")) {
			result = "java.math.BigDecimal";
		}
		if (jdbcType.contains("FLOAT")) {
			result = "Float";
		}
		if (jdbcType.contains("DOUBLE")) {
			result = "Double";
		}
		if (jdbcType.contains("BIGINT")) {
			result = "Long";
		}
		return result;
	}

	public String parseJdbcType() {
		String javaType = parseJavaType();
		String result = "NUMERIC";
		if ("String".equals(javaType)) {
			result = "VARCHAR";
		}
		if (javaType.equalsIgnoreCase("int")) {
			result = "INTEGER";
		}
		if (javaType.endsWith("Date")) {
			result = "DATE";
		}
		return result;
	}

	public String parseFieldName() {
		return wordsParser.parseWords(name);
	}

	@Override
	public String toString() {
		return name + " " + type + " " + size + " " + digits + " " + nullable;
	}
}
