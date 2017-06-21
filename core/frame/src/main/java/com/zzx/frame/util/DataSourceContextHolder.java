package com.zzx.frame.util;

public class DataSourceContextHolder {

	private static final ThreadLocal<Context> LOCAL_CONTEXT = new ThreadLocal<Context>();
	
	public static void markMaster() {
		LOCAL_CONTEXT.set(new Context(DataSourceType.MASTER, DataSourceType.MASTER.getCode()));
	}
	
	public static void markSlave() {
		LOCAL_CONTEXT.set(new Context(DataSourceType.SLAVE));
	}
	
	
	
	public static boolean isChoiceNone() {
		return null == LOCAL_CONTEXT.get();
	}
	
	public static boolean isChoiceMaster() {
		Context context = LOCAL_CONTEXT.get();
		return DataSourceType.MASTER == (context != null ? context.type : null);
	}
	
	public static boolean isChoiceSlave() {
		Context context = LOCAL_CONTEXT.get();
		return DataSourceType.SLAVE == (context != null ? context.type : null);
	}
	
	public static String getDataSourceKey() {
		Context context = LOCAL_CONTEXT.get();
		return context != null ? context.dsKey : null;
	}
	
	public static void setDataSourceKey(String dataSourceKey) {
		Context context = LOCAL_CONTEXT.get();
		if(context != null){
			context.dsKey = dataSourceKey;
		}
	}
	
	public static String getDataSourceType() {
		Context context = LOCAL_CONTEXT.get();
		return context != null ? context.type.getCode() : null;
	}
	
	public static void reset() {
		LOCAL_CONTEXT.set(null);
    }
	
	static class Context {
		DataSourceType type;
		String dsKey;
		
		Context(DataSourceType type) {
			this(type, null);
		}
		Context(DataSourceType type, String dsKey) {
			this.type = type;
			this.dsKey = dsKey;
		}
	}
	
	static enum DataSourceType {

		MASTER("master", "主库"), SLAVE("slave", "从库");
	    private String code;
	    private String name;

	    DataSourceType(String code, String name) {
	        this.code = code;
	        this.name = name;
	    }

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
