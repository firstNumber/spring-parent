package com.weilai.common.tool.scaffold;

import org.springframework.util.StringUtils;

public class UnderlineSplitWordsParser implements WordsParser {

	public String parseWords(String orginalString) {
		String[] items = orginalString.split(ScaffoldUtil.UNDER_LINE);
		String result = "";
		for (int i = 0; i < items.length; i++) {
			items[i] = items[i].toLowerCase();
			if (i > 0) {
				result = result + StringUtils.capitalize(items[i]);
			} else {
				result = result + items[i];
			}
		}
		return result;
	}

}
