package com.zzx.testapp1.dev;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.Properties;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class CodeGenerator implements Config {

	public static void output(Configuration cfg, Object model, String templateFile, File destFile, boolean append)
			throws IOException, TemplateException {
		Writer out = null;
		try {
			Template template = cfg.getTemplate(templateFile, "UTF-8");
			out = new OutputStreamWriter(new FileOutputStream(destFile, append), "UTF-8");
			// out = new OutputStreamWriter(System.out);
			template.process(model, out);
			out.flush();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public static File createFile(File path, String fileName) {
		if (!path.exists()) {
			path.mkdirs();
		}
		return new File(path, fileName);
	}

	public void generate() throws Exception {
		// File confFile = new
		// File(CodeGenerator.class.getResource("conf.properties").getFile());
		File confFile = getConfigFile();
		Properties prop = getProp(confFile);
		String tableNames = prop.getProperty(TABLE_NAMES);
		if (tableNames == null || tableNames.trim().length() < 1) {
			throw new RuntimeException("Table names is necessary!");
		}
		Connection con = ModelGen.initConnection(prop);
		int count = 0;
		for (String tn : tableNames.split(",")) {
			tn = tn.trim();
			if (tn.length() > 0) {
				prop.setProperty(TABLE_NAME, tn);
				ModelGen.doGen(prop, con);
				ControllerServiceDaoGen.doGen(prop);
				count++;
			}
		}
		System.out.println(String.format("Successfully generated codes for %s table%s!", count, count > 0 ? "s" : ""));
		con.close();
	}

	protected abstract File getConfigFile();

	private static Properties getProp(File conf) throws IOException {

		Properties prop = new Properties();
		prop.load(new FileInputStream(conf));

		prop.put(APPEND, Boolean.valueOf(prop.getProperty(APPEND, "true")));
		prop.put(JSON, Boolean.valueOf(prop.getProperty(JSON, "true")));
		prop.put(HTML, Boolean.valueOf(prop.getProperty(HTML, "true")));
		prop.put(DAO_SUFFIX, prop.getProperty(DAO_SUFFIX, "Dao"));
		prop.put(SERVICE_SUFFIX, prop.getProperty(SERVICE_SUFFIX, "Service"));
		prop.put(CONTROLLER_SUFFIX, prop.getProperty(CONTROLLER_SUFFIX, "Controller"));
		prop.put(IMPL_SUFFIX, prop.getProperty(IMPL_SUFFIX, ""));

		return prop;
	}

}
