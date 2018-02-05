package com.zzx.testapp1.dev;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.zzx.common.util.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateException;

public class ControllerServiceDaoGen implements Config {

	private static final String daoTmp = "TmpDao.ftl";
	private static final String daoImplTmp = "TmpDaoImpl.ftl";
	private static final String serviceTmp = "TmpService.ftl";
	private static final String serviceImplTmp = "TmpServiceImpl.ftl";
	private static final String controllerTmp = "TmpController.ftl";

	static void doGen(Properties model) throws IOException, TemplateException {

		String workspacePath = model.getProperty(WORKSPACE_PATH);
		if(workspacePath == null){
			throw new RuntimeException("Workspace's absolute path is null!");
		}
		
		String tableName = model.getProperty(TABLE_NAME);

		File parentFile = new File(workspacePath.toString());
		if(model.getProperty(PROJ_TARGET_PATH) != null) {
			parentFile = new File(parentFile, model.getProperty(PROJ_TARGET_PATH));
		}
		
		String domainClassName = StringUtil.firstCharUpperCase(StringUtil.toCamelCase(tableName));
		model.setProperty(DOMAIN_CLASS_NAME, domainClassName);
		
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
		cfg.setDirectoryForTemplateLoading(new File(ControllerServiceDaoGen.class.getResource("ftl").getPath()));
		cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
		
		File path = null;
		String fileName = null;
		File outFile = null;
		
		if (model.get(DAO_PACKAGE) != null) {
			String relativePath = model.getProperty(DAO_PACKAGE).replace(".", File.separator);
			path = new File(parentFile, relativePath);
			fileName = domainClassName + model.getProperty(DAO_SUFFIX) + ".java";
			outFile = CodeGenerator.createFile(path, fileName);
			CodeGenerator.output(cfg, model, daoTmp, outFile, (Boolean)model.get(APPEND));
		}
		if(model.get(DAOIMPL_PACKAGE) != null) {
			String relativePath = model.getProperty(DAOIMPL_PACKAGE).replace(".", File.separator);
			path = new File(parentFile, relativePath);
			fileName = domainClassName + model.getProperty(DAO_SUFFIX) + model.getProperty(IMPL_SUFFIX) + ".java";
			outFile = CodeGenerator.createFile(path, fileName);
			CodeGenerator.output(cfg, model, daoImplTmp, outFile, (Boolean)model.get(APPEND));
		}

		if (model.get(SERVICE_PACKAGE) != null) {
			String relativePath = model.getProperty(SERVICE_PACKAGE).replace(".", File.separator);
			path = new File(parentFile, relativePath);
			fileName = domainClassName + model.getProperty(SERVICE_SUFFIX) + ".java";
			outFile = CodeGenerator.createFile(path, fileName);
			CodeGenerator.output(cfg, model, serviceTmp, outFile, (Boolean)model.get(APPEND));
		}
		
		if (model.get(SERVICEIMPL_PACKAGE) != null) {
			String relativePath = model.getProperty(SERVICEIMPL_PACKAGE).replace(".", File.separator);
			path = new File(parentFile, relativePath);
			fileName = domainClassName + model.getProperty(SERVICE_SUFFIX) + model.getProperty(IMPL_SUFFIX) + ".java";
			outFile = CodeGenerator.createFile(path, fileName);
			CodeGenerator.output(cfg, model, serviceImplTmp, outFile, (Boolean)model.get(APPEND));
		}

		if (model.get(CONTROLLER_PACKAGE) != null) {
			String relativePath = model.getProperty(CONTROLLER_PACKAGE).replace(".", File.separator);
			path = new File(parentFile, relativePath);
			fileName = domainClassName + model.getProperty(CONTROLLER_SUFFIX) + ".java";
			outFile = CodeGenerator.createFile(path, fileName);
			CodeGenerator.output(cfg, model, controllerTmp, outFile, (Boolean)model.get(APPEND));
		}
	
	}

//	private static void output(Configuration cfg, Properties model, String srcFile, File destFile)
//			throws IOException, TemplateException {
//		output(cfg, model, srcFile, destFile, false);
//	}
	
	
}