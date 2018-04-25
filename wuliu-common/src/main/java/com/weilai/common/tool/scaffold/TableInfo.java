package com.weilai.common.tool.scaffold;

import java.util.ArrayList;
import java.util.List;

public class TableInfo {
	private static final String ID = "id";
	private final String ENDL = "\n";
	private final String TAB = "    ";
	private final String TAB2 = TAB + TAB;
	private final String TAB3 = TAB2 + TAB;
	private final String TAB4 = TAB2 + TAB2;
	private String name;
	private String primaryKey;
	private List<ColumnInfo> columns;
	private List<FieldInfo> fields;

	public TableInfo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<ColumnInfo> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnInfo> columns) {
		this.columns = columns;
	}

	public void addColumn(ColumnInfo column) {
		if (columns == null)
			columns = new ArrayList<ColumnInfo>();
		if (!columns.contains(column)) {
			columns.add(column);
			String type = column.parseJavaType();
			String name = column.parseFieldName();
			addFiled(new FieldInfo(type, name));
		}
	}

	public void addFiled(FieldInfo field) {
		if (fields == null)
			fields = new ArrayList<FieldInfo>();
		if (field.getName().equalsIgnoreCase(ID))
			return;
		if (!fields.contains(field)) {
			fields.add(field);
		}

	}

	public List<FieldInfo> getFields() {
		return fields;
	}

	public String getFieldsDeclareInfo() {
		StringBuffer sb = new StringBuffer(TAB + "private Integer id;" + ENDL);
		for (FieldInfo field : fields) {
			if (field.getName().equalsIgnoreCase(ID))
				continue;// id property is in the BaseModel
			sb.append(TAB);
			sb.append("private ");
			sb.append(field.getType());
			sb.append(" ");
			sb.append(field.getName());
			sb.append(";");
			sb.append(ENDL);

		}
		return sb.toString();
	}

	public String getInsertStatement() {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into " + name);
		sb.append("( " + this.getColumnNames() + " ) values (");
		ColumnInfo col = null;
		sb.append("#{id,jdbcType=INTEGER},");
		for (int i = 0; i < columns.size(); i++) {
			col = columns.get(i);
			sb.append("#{" + col.parseFieldName());
			if (!col.parseJdbcType().equals("DATE")) {// date不使用jdbcType,用于保证年月日,时分秒可用
				sb.append(",jdbcType=" + col.parseJdbcType());
			}
			sb.append("}");
			if (i + 1 != columns.size()) {
				sb.append(",");
			}
		}
		sb.append(" )");
		return sb.toString();
	}

	public String getUpdateStatement() {
		StringBuffer sb = new StringBuffer();
		sb.append("update " + name + " set ");
		ColumnInfo col = null;
		for (int i = 0; i < columns.size(); i++) {
			col = columns.get(i);
			if ("createTime".equals(col.getName())) {
				continue;
			}
			sb.append(col.getName() + "=#{" + col.parseFieldName());
			if (!col.parseJdbcType().equals("DATE")) {// date不使用jdbcType,用于保证年月日,时分秒可用
				sb.append(",jdbcType=" + col.parseJdbcType());
			}
			sb.append("}");
			if (i + 1 != columns.size()) {
				sb.append(",");
			}
		}
		sb.append(" where " + primaryKey + "=#{id,jdbcType=INTEGER}");
		return sb.toString();
	}

	public String getResultMap() {
		StringBuffer sb = new StringBuffer();
		sb.append(TAB3);
		sb.append("<id property=\"id\"");
		sb.append(" column=\"" + primaryKey + "\"");
		sb.append(" jdbcType=\"INTEGER\" />");
		sb.append(ENDL);
		for (ColumnInfo col : columns) {
			sb.append(TAB3);
			sb.append("<result property=\"" + col.parseFieldName() + "\" column=\"" + col.getName() + "\"");
			if (!col.parseJdbcType().equals("DATE")) {// date不使用jdbcType,用于保证年月日,时分秒可用
				sb.append(" jdbcType=\"" + col.parseJdbcType() + "\"");
			}
			sb.append(" />");
			sb.append(ENDL);
		}
		return sb.toString();
	}

	public String getOtherCondition() {
		StringBuffer sb = new StringBuffer();
		for (ColumnInfo col : columns) {
			sb.append(TAB3);
			sb.append("<if test= \"" + col.parseFieldName() + " != null \">");
			sb.append("	and " + col.getName() + " = #{" + col.parseFieldName());
			if (!col.parseJdbcType().equals("DATE")) {// date不使用jdbcType,用于保证年月日,时分秒可用
				sb.append(",jdbcType=" + col.parseJdbcType());
			}
			sb.append(" }");
			sb.append("</if>");
			sb.append(ENDL);

			if (col.getName().equals("status") && col.parseJdbcType().equals("NUMERIC")) {
				sb.append(TAB3);
				sb.append("<if test= \"" + col.parseFieldName() + "In != null \">");
				sb.append("	and " + col.getName() + " in (${" + col.parseFieldName() + "In})");
				sb.append("</if>");
				sb.append(ENDL);
			}
		}
		return sb.toString();
	}

	public String getLikeCondition() {
		StringBuffer sb = new StringBuffer();
		for (ColumnInfo col : columns) {
			sb.append(TAB3);
			sb.append("<if test= \"" + col.parseFieldName() + " != null \">");
			if (col.parseJavaType().equals("String")) {
				sb.append("	and " + col.getName() + " like \"%\"#{" + col.parseFieldName() + "}\"%\"");
			} else {
				sb.append("	and " + col.getName() + " = #{" + col.parseFieldName());
				if (!col.parseJdbcType().equals("DATE")) {// date不使用jdbcType,用于保证年月日,时分秒可用
					sb.append(",jdbcType=" + col.parseJdbcType());
				}
				sb.append(" }");
			}
			sb.append("</if>");
			sb.append(ENDL);

			if (col.getName().equals("status") && col.parseJdbcType().equals("NUMERIC")) {
				sb.append(TAB3);
				sb.append("<if test= \"" + col.parseFieldName() + "In != null \">");
				sb.append("	and " + col.getName() + " in (${" + col.parseFieldName() + "In})");
				sb.append("</if>");
				sb.append(ENDL);
			}
		}
		return sb.toString();
	}

	public String getColumnNames() {
		StringBuffer sb = new StringBuffer();
		sb.append(primaryKey + ",");
		ColumnInfo column = null;
		for (int i = 0; i < columns.size(); i++) {
			column = columns.get(i);
			sb.append(column.getName());
			if (i + 1 != columns.size()) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public String getUpdateMapModel() {// 动态字段更新
		StringBuffer sb = new StringBuffer();
		sb.append("update " + name + ENDL);
		sb.append(TAB2 + "<set>" + ENDL);
		ColumnInfo col = null;
		for (int i = 0; i < columns.size(); i++) {
			col = columns.get(i);
			if ("createTime".equals(col.getName())) {
				continue;
			}
			sb.append(TAB3 + "<if test=\"" + col.parseFieldName() + " != null \"> " + ENDL);
			sb.append(TAB4 + col.getName() + " = #{" + col.parseFieldName());
			if (!col.parseJdbcType().equals("DATE")) {// date不使用jdbcType,用于保证年月日,时分秒可用
				sb.append(",jdbcType=" + col.parseJdbcType());
			}
			sb.append("}," + ENDL);
			sb.append(TAB3 + "</if>" + ENDL);
		}
		sb.append(TAB2 + "</set>" + ENDL);
		sb.append(TAB2 + " where " + primaryKey + "=#{id,jdbcType=INTEGER}");
		return sb.toString();
	}
}
