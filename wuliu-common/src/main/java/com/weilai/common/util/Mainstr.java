package com.weilai.common.util;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

public class Mainstr {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String jsonString = "{\"name\":\"小明\"}";
		String ecode = Base64.encode(jsonString);
//		System.out.println(ecode);
		byte[] decode = Base64.decode(ecode);
//		System.out.println(decode);
		String b = new String(decode,"utf8");
//		System.out.println(b);
		Object o  = JsonUtil.toBean(decode, Object.class);
		System.out.println(o.toString());
		JSONObject json = JSONObject.fromObject(b);
//		String a = json.getString("name");
//		System.out.println(a);
		
	}

}

