package com.weilai.common.tool.scaffold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.weilai.common.tool.ScaffoldGenerator;

public class ScaffoldBuilder {
	protected final static String PKG_PREFIX = "com." + ScaffoldGenerator.COMPANY_NAME + ".";
	protected final static String PKG_SUFFIX_MODEL = ".model.";
	protected final static String PKG_SUFFIX_DAO = ".dao.";
	protected final static String PKG_SUFFIX_MANAGER = ".service.";
	protected final static String PKG_IMPL = "impl";

	protected String pkgName;
	protected String clzName;
	protected TableInfo tableInfo;
	private final Map<String, String> mapping;

	public ScaffoldBuilder(String pkgName, String clzName, TableInfo tableInfo) {
		this.pkgName = PKG_PREFIX + pkgName;
		this.clzName = clzName;
		this.tableInfo = tableInfo;

		mapping = new HashMap<String, String>();
		mapping.put("companyName", ScaffoldGenerator.COMPANY_NAME);

		mapping.put("clzName", clzName);
		mapping.put("clzNameLC", StringUtils.uncapitalize(clzName));
		mapping.put("tblName", tableInfo.getName());
		mapping.put("modelPath", getModelPath());
		mapping.put("daoPath", getDaoPath());
		mapping.put("daoImplPath", getDaoImplPath());
		mapping.put("managerPath", getManagerPath());

		mapping.put("fieldsDeclareInfo", tableInfo.getFieldsDeclareInfo());

		mapping.put("resultMap", tableInfo.getResultMap());
		mapping.put("baseField", tableInfo.getColumnNames());
		mapping.put("otherCondition", tableInfo.getOtherCondition());
		mapping.put("likeCondition", tableInfo.getLikeCondition());

		System.out.println(tableInfo.getPrimaryKey());
		mapping.put("primaryKey", tableInfo.getPrimaryKey());
		System.out.println(tableInfo.getInsertStatement());
		mapping.put("insertStatement", tableInfo.getInsertStatement());
		System.out.println(tableInfo.getUpdateStatement());
		mapping.put("updateStatement", tableInfo.getUpdateStatement());
		mapping.put("updateMapModel", tableInfo.getUpdateMapModel());
	}

	public String getModelPath() {
		return pkgName + PKG_SUFFIX_MODEL + clzName;
	}

	public String getSqlMapFile() {
		return pkgName + PKG_SUFFIX_MODEL + clzName + ".xml";
	}

	public String getDaoPath() {
		return pkgName + PKG_SUFFIX_DAO + clzName + "Dao";
	}

	public String getDaoImplPath() {
		return pkgName + PKG_SUFFIX_DAO + PKG_IMPL + ScaffoldUtil.DOT + clzName + "Dao"
				+ StringUtils.capitalize(PKG_IMPL);
	}

	public String getManagerPath() {
		return pkgName + PKG_SUFFIX_MANAGER + clzName + "Service";
	}

	public List<FileGenerator> buildGenerators() {
		List<FileGenerator> list = new ArrayList<FileGenerator>();
		// model
		list.add(new FileGenerator(pkgName + ".model", clzName, "Model.txt", mapping));
		list.add(new FileGenerator(pkgName + ".model.mapping", clzName, "SqlMap.txt", mapping, "xml"));

		// dao
		list.add(new FileGenerator(pkgName + ".dao", clzName + "Dao", "Dao.txt", mapping));
		list.add(new FileGenerator(pkgName + ".dao.impl", clzName + "DaoImpl", "DaoImpl.txt", mapping));
		//
		// // service
		list.add(new FileGenerator(pkgName + ".service", clzName + "Service", "Service.txt", mapping));
		list.add(new FileGenerator(pkgName + ".service.impl", clzName + "ServiceImpl", "ServiceImpl.txt", mapping));
		// list.add(new FileGenerator(pkgName + ".service", clzName +
		// "ServiceTest", "ServiceTest.txt", mapping));
		// // controller
		list.add(new FileGenerator(pkgName + ".controller", clzName + "Controller", "Controller.txt", mapping));
		return list;
	}

}
