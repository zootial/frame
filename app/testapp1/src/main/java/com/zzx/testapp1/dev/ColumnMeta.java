package com.zzx.testapp1.dev;

import static java.sql.Types.*;

import com.zzx.common.util.StringUtil;

public class ColumnMeta {
	private String columnName;
	private String ordinalPosition;
	private String isNullable;
	private String columnDefault;
	private Integer columnType;
	private Boolean unique;
	private String columnComment;
	private int columnSize;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(String ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public Integer getColumnType() {
		return columnType;
	}

	public void setColumnType(Integer columnType) {
		this.columnType = columnType;
	}

	public Boolean getUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	/**
	 * 获取属性名称
	 * 
	 * @return
	 */
	public String getPropertyName() {
		return StringUtil.toCamelCase(columnName);
	}

	/**
	 * 获取属性类型
	 * 
	 * @return
	 */
	public String getPropertyType() {
		return mapColumnType(columnType);
	}

	private static String mapColumnType(int type) {
		String dataType = null;
		switch (type) {
		case DATE:
		case TIME:
		case TIMESTAMP:
			dataType = "java.util.Date";
			break;
		case CHAR:
		case VARCHAR:
		case LONGVARCHAR:
			dataType = "java.lang.String";
			break;
		case BIT:
			dataType = "java.lang.Byte";
			break;
		case TINYINT:
		case SMALLINT:
		case INTEGER:
			dataType = "java.lang.Integer";
			break;
		case BIGINT:
			dataType = "java.lang.Long";
			break;
		case DECIMAL:
			dataType = "java.math.BigDecimal";
			break;
		case DOUBLE:
			dataType = "java.lang.Double";
			break;
		case FLOAT:
		case REAL:
			dataType = "java.lang.Float";
		}

		return dataType;
	}
}
