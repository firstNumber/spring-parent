package com.weilai.common.tool.scaffold;

import org.apache.commons.lang.StringUtils;

public class UncapitalizeWordsParser implements WordsParser {

	public String parseWords(String orginalString) {
		return StringUtils.uncapitalize(orginalString);
	}

}
