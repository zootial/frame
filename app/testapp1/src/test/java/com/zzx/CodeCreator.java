package com.zzx;

import java.io.File;
import java.net.URL;

import com.zzx.testapp1.dev.CodeGenerator;

public class CodeCreator {

	public static void main(String[] args) throws Exception {
		new CodeGenerator(){

			@Override
			protected File getConfigFile() {
				URL url = getClass().getClassLoader().getResource("code_config.properties");
				return new File(url.getFile());
			}
			
		}.generate();
	}
}
