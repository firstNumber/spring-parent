package com.spring.bizservice.demo.ztool;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang.StringUtils;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author psc
 * 字符串工具类
 */
public class StringUtil {

	/**
	 * 去掉最后多余的0
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }

	/**
	 * 验证账号是否合法
	 * @param account
	 * @return
	 */
	public static boolean validateAccount(String account){
		String REGEX = "[a-zA-Z0-9_]{4,16}$";
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(account);
		return matcher.matches();
	}

	/**
	 * 字符encode
	 * @param content
	 * @return
	 */
	public static String encode(String content){
		try {
			content = URLEncoder.encode(content, "utf-8");
			return content;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 位数不够补0
	 * @param num
	 * @param strLength
	 * @return
	 */
	public static String addZeroForNum(Long num, int strLength) {
		return String.format("%0" + strLength + "d", num);
	}


	/**
	 * 大写转下划线加小写
	 * aaaA --> aaa_a
	 * @param param
	 * @return
	 */
	private static Pattern p = Pattern.compile("[A-Z]");

	public static String camel4underline(String param){

		if(param==null ||param.equals("")){
			return "";
		}
		StringBuilder builder=new StringBuilder(param);
		Matcher mc = p.matcher(param);
		int i=0;
		while(mc.find()){
			builder.replace(mc.start()+i, mc.end()+i, "_"+mc.group().toLowerCase());
			i++;
		}

		if('_' == builder.charAt(0)){
			builder.deleteCharAt(0);
		}
		return builder.toString();
	}

	/**
	 * 过滤特殊字符
	 * @param str
	 * @return
	 */
	public static String StringFilter(String str) {

		String REGEX = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 匿名
	 * @param name
	 * @param sex
	 * @return
	 */
	public static String anonymous(String name,String sex) {

		if (StringUtils.isBlank(name) || StringUtils.isBlank(sex)) {
			return null;
		}

		String nameSuffix = null;

		if ("男".equals(sex)) {
			nameSuffix = "先生";
		} else {
			nameSuffix = "女士";
		}

		if (name.length() > 3) {
			name = name.substring(0,2);
		} else {
			name = name.substring(0,1);
		}

		return name + nameSuffix;
	}

	public static String freeMarkFullData(String templateName, String content, Map<String,Object> data) throws Exception {

		if (StringUtils.isBlank(templateName)) {
			return null;
		}

		if (StringUtils.isBlank(content)) {
			return null;
		}

		if (data == null) {
			return null;
		}

		freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);

		StringTemplateLoader stringLoader = new StringTemplateLoader();

		stringLoader.putTemplate(templateName, content);

		cfg.setTemplateLoader(stringLoader);

		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

		StringWriter writer = new StringWriter();
		cfg.getTemplate(templateName).process(data, writer);

		return writer.toString();
	}

	/**
	 * 检测是否有emoji字符
	 *
	 * @param source
	 * @return 一旦含有就抛出
	 */
	public static boolean containsEmoji(String source) {
		if (StringUtils.isBlank(source)) {
			return false;
		}
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isEmojiCharacter(codePoint)) {
				//do nothing，判断到了这里表明，确认有表情字符
				return true;
			}
		}
		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) ||
				(codePoint == 0x9) ||
				(codePoint == 0xA) ||
				(codePoint == 0xD) ||
				((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
				((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
				((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 *
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		source = source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
		if (!containsEmoji(source)) {
			return source;//如果不包含，直接返回
		}

		//到这里铁定包含
		StringBuilder buf = null;

		for (int i = 0; i < source.length(); i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}

				buf.append(codePoint);
			} else {
				buf.append("");
			}
		}

		//如果没有找到 emoji表情，则返回源字符串
		if (buf == null) {
			return source;
		} else {
			//这里的意义在于尽可能少的toString，因为会重新生成字符串
			if (buf.length() == source.length()) {
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}
	}

	/**
	 * 获取URL参数
	 * @param param
	 * @return
	 */
	public static Map<String, Object> getUrlParams(String param) {

		if (StringUtils.isBlank(param)) {
			return null;
		}

		String[] paramArray = param.split("[&]");

		Map<String, Object> params = new HashMap<>(paramArray.length);

		for (String str : paramArray) {
			String[] item = str.split("[=]");
			params.put(item[0], item[1]);
		}

		return params;
	}

	/**
	 * map转为url参数
	 * @param map
	 * @return
	 */
	public static String map2UrlParams(Map<String,Object> map) {

		if (map == null || map.isEmpty()) {
			return null;
		}

		StringBuffer params = new StringBuffer();

		for (Map.Entry<String, Object> param : map.entrySet()) {

			if (params.length() > 0) {
				params.append("&");
			}

			params.append(param.getKey()).append("=").append(param.getValue());
		}

		return params.toString();
	}
}
