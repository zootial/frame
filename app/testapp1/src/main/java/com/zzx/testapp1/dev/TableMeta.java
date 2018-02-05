package com.zzx.testapp1.dev;

import java.util.List;

public class TableMeta {

	private String schema;
	private String tableName;
	private List<ColumnMeta> columns;
	private String domainPackage;
	private String domainClassName;
	private String comment;
	
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getDomainPackage() {
		return domainPackage;
	}
	public void setDomainPackage(String domainPackage) {
		this.domainPackage = domainPackage;
	}
	public String getDomainClassName() {
		return domainClassName;
	}
	public void setDomainClassName(String domainClassName) {
		this.domainClassName = domainClassName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<ColumnMeta> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnMeta> columns) {
		this.columns = columns;
	}
}
