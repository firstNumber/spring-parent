package com.weilai.common.tool;

import java.util.HashMap;
import java.util.Map;

import com.weilai.common.tool.scaffold.ScaffoldGen;

public class ScaffoldGenerator {
	// public static String DB_URL =
	// "jdbc:mysql://localhost:3306/wuliu_order?useUnicode=true&characterEncoding=utf8";
	// public static String DB_URL =
	// "jdbc:mysql://localhost:3306/wuliu_user?useUnicode=true&characterEncoding=utf8";
	// public static String DB_URL =
	// "jdbc:mysql://localhost:3306/wuliu_trace?useUnicode=true&characterEncoding=utf8";
	// public static String DB_URL =
	// "jdbc:mysql://localhost:3306/wuliu_lingdan?useUnicode=true&characterEncoding=utf8";
	public static String DB_URL = "jdbc:mysql://localhost:3306/lyztest?useUnicode=true&characterEncoding=utf8";
	public static String DB_USERNAME = "root";
	public static String DB_PASSWORD = "123456";

	public static String COMPANY_NAME = "weilai";
	public static String ROOT_PATH = "D:/officeware/eclipse/workespace2/weilai-wuliu/";
	// public static String ROOT_PATH = "E:/src/zd/zhidian-wuliu/";
	// public static String ROOT_PATH = "D:/gitWorkSpaceV2/zhidian-wuliu/";

	// public static String moduleName = "order";
	// public static String moduleName = "zhongbao";
	public static String moduleName = "appuser";
	// public static String moduleName = "user";
	public static Map<String, String> pathMap = new HashMap<String, String>() {
		{
			// put("Model.txt", ROOT_PATH + "wuliu-api-" + moduleName + "/");
			put("SqlMap.txt", ROOT_PATH + "wuliu-service-" + moduleName + "/");
			// put("Dao.txt", ROOT_PATH + "wuliu-service-" + moduleName + "/");
			// put("DaoImpl.txt", ROOT_PATH + "wuliu-service-" + moduleName +
			// "/");
			// put("Service.txt", ROOT_PATH + "wuliu-api-" + moduleName + "/");
			// put("ServiceImpl.txt", ROOT_PATH + "wuliu-service-" + moduleName
			// + "/");
			// put("Controller.txt", ROOT_PATH + "wuliu-web-" + "moduleName" +
			// "/");
		}
	};

	public static void main(String[] args) {
		// arg1 子系统名
		// arg2 业务对象名,即Model,双词以上要求单词首字大写
		// arg3 表名
		// ScaffoldGen generator = new ScaffoldGen("zhongbao.driver",
		// "OrderDriver","order_driver");
		ScaffoldGen generator = new ScaffoldGen("appuser.core", "orderShort", "order_short");

		// ScaffoldGen generator = new ScaffoldGen("zhongbao.driver",
		// "OrderDriverPackage","order_driver_package");
		// ScaffoldGen generator = new ScaffoldGen("user.account",
		// "IdentityVerifyLog","wuliu_user_idverify_log");
		// ScaffoldGen generator = new ScaffoldGen("zhongbao.driver",
		// "Driver","driver");
		// ScaffoldGen generator = new ScaffoldGen("zhongbao.order",
		// "OrderZhongBao", "order_zhongbao");
		// ScaffoldGen generator = new ScaffoldGen("zhongbao.qishi", "Qishi",
		// "qishi");
		// ScaffoldGen generator = new ScaffoldGen("zhongbao.driver",
		// "OrderDriverPackage","order_driver_package");
		// ScaffoldGen generator = new ScaffoldGen("user.account",
		// "IdentityVerifyLog","wuliu_user_idverify_log");
		// ScaffoldGen generator = new ScaffoldGen("zhongbao.driver",
		// "OrderDriver","order_driver");
		// ScaffoldGen generator = new ScaffoldGen("user.core",
		// "WuliuUserMerchant", "wuliu_user_merchant");
		// ScaffoldGen generator = new ScaffoldGen("zhongbao.driver",
		// "Driver","driver");
		// ScaffoldGen generator = new ScaffoldGen("zhongbao.order",
		// "OrderZhongBao", "order_zhongbao");
		// ScaffoldGen generator = new ScaffoldGen("zhongbao.qishi", "Qishi",
		// "qishi");

		// true 控制台 false 文件
		generator.execute(false);
	}

}
