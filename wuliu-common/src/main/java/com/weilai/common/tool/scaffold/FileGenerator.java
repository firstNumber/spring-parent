package com.weilai.common.tool.scaffold;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.weilai.common.tool.ScaffoldGenerator;

public class FileGenerator {
	private final static String TEMPLATE_PATH = "com/" + ScaffoldGenerator.COMPANY_NAME
			+ "/common/tool/scaffold/template/";
	private final static String SRC_PATH = "src/main/java/";
	private final static String TEST_PATH = "src/test/java/";
	protected String pkgPath;
	protected String clzName;
	protected String template;
	protected String suffix;
	private final Map<String, String> mapping;

	public FileGenerator(String pkgPath, String clzName, String template, Map<String, String> mapping) {
		this(pkgPath, clzName, template, mapping, "java");
	}

	public FileGenerator(String pkgPath, String clzName, String template, Map<String, String> mapping,
			String fileSuffix) {
		this.pkgPath = pkgPath;
		this.clzName = clzName;
		this.template = template;
		this.mapping = mapping;
		this.suffix = fileSuffix;
	}

	public String getTargetFilePath() {
		String result = pkgPath.replace(ScaffoldUtil.DOT, "/");
		result = result + "/";
		result = result + clzName + ScaffoldUtil.DOT + suffix;
		if (clzName.endsWith("Test")) {// 单元测试类
			result = TEST_PATH + result;
		} else {
			result = SRC_PATH + result;// 普通类
		}
		return result;
	}

	public void execute() {
		execute(true);
	}

	public void execute(boolean debug) {
		String tmplFile = TEMPLATE_PATH + template;
		InputStream templateInputStream = getClass().getClassLoader().getResourceAsStream(tmplFile);

		if (templateInputStream == null) {
			// system.out.println("[WARN] " + tmplFile + " not exists.");
			return;
		}
		if (ScaffoldGenerator.pathMap.get(template) == null) {
			System.out.println(template + " 不需要创建.");
			return;
		}

		File f = new File(ScaffoldGenerator.pathMap.get(template) + getTargetFilePath());
		if (f.exists() && !debug) {
			if (suffix.equals("xml") || template.equals("Model.txt")) {// xml增量替换,model替换
				System.out.println(f.getAbsoluteFile() + " start replace.");
			} else {
				System.out.println(f.getAbsoluteFile() + " aleardy exists.");
				return;
			}
		}
		try {
			System.out.println(f.getAbsoluteFile() + " created !");
			if (debug) {
				writeContentWithTemplate(System.out, templateInputStream, mapping);
			} else {
				if (f.exists() && suffix.equals("xml")) {
					updateXml(f);
				} else {
					createFileWithDirs(f);
					writeContentWithTemplate(new PrintStream(f), templateInputStream, mapping);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			// system.out.println(f.getAbsoluteFile() + " create failed.");
		}
	}

	private void updateXml(File f) {
		try {
			List<Map<String, String>> replaceMappingList = getXMLUpdateMappingList();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			boolean isNeedReplace = false;
			int mappingIndex = 0;
			String line = null;
			List<String> newLineList = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				// 先判断是否以 start 开头
				// 如果不是, 判断是否需要替换
				// 如果不需要,输出原内容
				// 如果需要,判断是否以 end 开头
				// 如果不是 什么都不做
				// 如果是 输出替换内容,输出结尾标签, 下标+1,isNeedReplace=false
				// 如果是, isNeedReplace=true,输出原内容

				// 替换完成,输出原内容
				if (mappingIndex == replaceMappingList.size()) {
					newLineList.add(line);// 原内容输出
					continue;
				}
				Map<String, String> newmap = replaceMappingList.get(mappingIndex);

				if (line.trim().startsWith(newmap.get("start"))) {
					isNeedReplace = true;
					newLineList.add(line);// 原内容输出
				} else {
					if (isNeedReplace) {
						if (line.trim().startsWith(newmap.get("end"))) {
							newLineList.add(newmap.get("newContent"));
							newLineList.add(line);// 原内容输出
							isNeedReplace = false;
							mappingIndex++;
						} else {
							// 什么都不做
						}
					} else {
						newLineList.add(line);// 原内容输出
					}
				}
			}
			// 输出
			PrintStream ps = new PrintStream(f);
			for (String s : newLineList) {
				ps.println(s);
			}
			ps.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Map<String, String>> getXMLUpdateMappingList() {
		List<Map<String, String>> replaceMappingList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", "<resultMap id=\"result\"");
		map.put("end", "</resultMap>");
		map.put("newContent", mapping.get("resultMap"));
		replaceMappingList.add(map);

		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("start", "<sql id=\"base_field\">");
		map1.put("end", "</sql>");
		map1.put("newContent", "        " + mapping.get("baseField"));
		replaceMappingList.add(map1);

		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("start", "<sql id=\"other-condition\">");
		map2.put("end", "</sql>");
		map2.put("newContent", mapping.get("otherCondition"));
		replaceMappingList.add(map2);

		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("start", "<sql id=\"like-condition\">");
		map3.put("end", "</sql>");
		map3.put("newContent", mapping.get("likeCondition"));
		replaceMappingList.add(map3);

		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("start", "<insert id=\"insert\"");
		map4.put("end", "</insert>");
		map4.put("newContent", "        " + mapping.get("insertStatement"));
		replaceMappingList.add(map4);

		Map<String, String> map5 = new HashMap<String, String>();
		map5.put("start", "<update id=\"update\"");
		map5.put("end", "</update>");
		map5.put("newContent", "        " + mapping.get("updateStatement"));
		replaceMappingList.add(map5);

		Map<String, String> map6 = new HashMap<String, String>();
		map6.put("start", "<update id=\"update4Selective\"");
		map6.put("end", "</update>");
		map6.put("newContent", "        " + mapping.get("updateMapModel"));
		replaceMappingList.add(map6);
		return replaceMappingList;
	}

	private void writeContentWithTemplate(PrintStream ps, InputStream templateInputStream, Map<String, String> mapping)
			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(templateInputStream));
		String line = null;
		while ((line = br.readLine()) != null) {
			line = replaceWithMapping(line, mapping);
			ps.println(line);
		}
		br.close();
	}

	private String replaceWithMapping(String srcLine, Map<String, String> mapping) {
		final String TAG_BEGI = "${";
		final String TAG_END = "}";
		String result = srcLine;
		for (String key : mapping.keySet()) {
			String value = mapping.get(key);
			result = StringUtils.replace(result, TAG_BEGI + key + TAG_END, value);
		}
		result = StringUtils.replace(result, TAG_BEGI + "pkgPath" + TAG_END, pkgPath);
		return result;
	}

	public boolean createFileWithDirs(File f) throws IOException {
		File parentDir = f.getParentFile();
		boolean parentCreated = false;
		if (!parentDir.exists()) {
			parentCreated = parentDir.mkdirs();
		}
		if (parentCreated) {
			return f.createNewFile();
		}
		return false;
	}

	public static void main(String[] args) {
		FileGenerator gen = new FileGenerator("com." + ScaffoldGenerator.COMPANY_NAME, "UserDAO", "DAO.txt",
				new HashMap<String, String>());
		gen.execute();
	}
}
