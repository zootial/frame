package com.zzx.testapp1.dev;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.zzx.common.util.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class ModelGen implements Config {
	private static final String domainTmp = "TmpDomain.ftl";

	static void doGen(Properties props, Connection conn) throws Exception {
		if (props.getProperty(DOMAIN_PACKAGE) == null) {
			return;
		}
		String workspacePath = props.getProperty(WORKSPACE_PATH);
		if (workspacePath == null) {
			throw new RuntimeException("Workspace's absolute path is null!");
		}
		String tableName = props.getProperty(TABLE_NAME);
		String domainClassName = StringUtil.firstCharUpperCase(StringUtil.toCamelCase(tableName));

		TableMeta tm = new TableMeta();
		tm.setSchema(props.getProperty(SCHEMA));
		tm.setTableName(tableName);
		tm.setDomainPackage(props.getProperty(DOMAIN_PACKAGE));
		tm.setDomainClassName(domainClassName);
		tm.setColumns(new ArrayList<ColumnMeta>());

		DatabaseMetaData meta = conn.getMetaData();
		ResultSet indexSet = meta.getIndexInfo(null, tm.getSchema(), tableName, true, true);
		Set<String> columnsUnique = new HashSet<String>();
		while (indexSet.next()) {
			if (indexSet.getBoolean("NON_UNIQUE")) {
				columnsUnique.add(indexSet.getString("COLUMN_NAME"));
			}
		}
		indexSet.close();

		ResultSet rs = meta.getColumns(null, tm.getSchema(), tableName, null);
		while (rs.next()) {
			ColumnMeta cm = new ColumnMeta();
			cm.setColumnName(rs.getString("COLUMN_NAME"));
			cm.setOrdinalPosition(rs.getString("ORDINAL_POSITION"));
			cm.setIsNullable(rs.getString("IS_NULLABLE"));
			cm.setColumnDefault(rs.getString("COLUMN_DEF"));
			cm.setColumnType(rs.getInt("DATA_TYPE"));
			cm.setColumnComment(rs.getString("REMARKS"));
			if (columnsUnique.contains(cm.getColumnName())) {
				cm.setUnique(true);
			}
			tm.getColumns().add(cm);
		}
		rs.close();

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
		cfg.setDirectoryForTemplateLoading(new File(ControllerServiceDaoGen.class.getResource("ftl").getPath()));
		cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));

		File parentFile = new File(workspacePath.toString());
		if (props.getProperty(PROJ_TARGET_PATH) != null) {
			parentFile = new File(parentFile, props.getProperty(PROJ_TARGET_PATH));
		}

		String relativePath = tm.getDomainPackage().replace(".", File.separator);
		File outFile = CodeGenerator.createFile(new File(parentFile, relativePath), tm.getDomainClassName() + ".java");
		CodeGenerator.output(cfg, tm, domainTmp, outFile, (Boolean)props.get(APPEND));
		System.out.println(String.format("Model for table '%s' generating finish.", tableName));
	}

	static Connection initConnection(Properties properties) throws Exception {
		Class<?> c = Class.forName(properties.getProperty(DRIVER_NAME));
		Driver driver = (Driver) c.newInstance();
		DriverManager.registerDriver(driver);
		Properties props = new Properties();
		props.put("remarksReporting", "true");
		props.put("user", properties.getProperty(USER_NAME));
		props.put("password", properties.getProperty(PASSWORD));
		return DriverManager.getConnection(properties.getProperty(DB_URL), props);
	}

}
