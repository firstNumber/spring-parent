package com.weilai.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * get,post 请求util类 *********************************
 * 
 * @Description: TODO
 * @author: liyongzhen
 * @createdAt: 2016年5月24日下午12:01:36
 ********************************** 
 */
public class HttpUtil {
	private final static Log logger = LogFactory.getLog(HttpUtil.class);
	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000)
			.setConnectTimeout(30000).setConnectionRequestTimeout(30000).build();

	/**
	 * post请求
	 * 
	 * @param url
	 *            请求路径
	 * @param paramMap
	 *            参数key=key,value=value
	 * @return 响应文本
	 * @Author: liyongzhen
	 * @Date: 2016年5月24日
	 */
	public static String post(String url, Map<String, String> paramMap) {
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			if (paramMap != null) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				for (Entry<String, String> entry : paramMap.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			}
			response = httpclient.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			return result;
		} catch (Exception e) {
			logger.error("url:" + url + "," + paramMap != null ? paramMap.toString() : "null" + "," + e.getMessage(), e);
		} finally {
			try {
				if (response != null)
					response.close();
				if (httpclient != null)
					httpclient.close();
			} catch (IOException e) {
				logger.error("url:" + url + "," + paramMap.toString() + "," + e.getMessage(), e);
			}
		}
		return result;
	}

	public static String postWithHead(String url, Map<String, String> paramMap, Map<String, String> headMap) {
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			if (headMap != null) {
				for (String key : headMap.keySet()) {
					httpPost.addHeader(key, headMap.get(key));
				}
			} else {
				httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
			}
			if (paramMap != null) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				for (Entry<String, String> entry : paramMap.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			}
	
			response = httpclient.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			return result;
		} catch (Exception e) {
			logger.error("url:" + url + ",headMap=" + headMap + ",paramMap:" + paramMap, e);
		} finally {
			try {
				if (response != null)
					response.close();
				if (httpclient != null)
					httpclient.close();
			} catch (IOException e) {
				logger.error("url:" + url + ",headMap=" + headMap + ",paramMap:" + paramMap, e);
			}
		}
		return result;
	}

	public static String postWithHead(String url, Map<String, String> headMap, String params) {
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			if (headMap != null) {
				for (String key : headMap.keySet()) {
					httpPost.addHeader(key, headMap.get(key));
				}
			} else {
				httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
			}
			StringEntity se = new StringEntity(params, "utf-8");
			httpPost.setEntity(se);
			response = httpclient.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			return result;
		} catch (Exception e) {
			logger.error("url:" + url + ",headMap=" + headMap + ",params:" + params, e);
		} finally {
			try {
				if (response != null)
					response.close();
				if (httpclient != null)
					httpclient.close();
			} catch (IOException e) {
				logger.error("url:" + url + ",headMap=" + headMap + ",params:" + params, e);
			}
		}
		return result;
	}

	/**
	 * posn请求
	 * 
	 * @param url
	 * @param jsonParam
	 *            json格式参数
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年11月16日
	 */
	public static String postJSON(String url, JSONObject jsonParam) {
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);

			httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
			StringEntity se = new StringEntity(jsonParam.toString(), "utf-8");
			se.setContentEncoding("text/json;charset=utf-8");
			se.setContentType("text/json;charset=utf-8");
			httpPost.setEntity(se);

			response = httpclient.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			return result;
		} catch (Exception e) {
			logger.error("url:" + url + ",jsonParam:" + jsonParam, e);
		} finally {
			try {
				if (response != null)
					response.close();
				if (httpclient != null)
					httpclient.close();
			} catch (IOException e) {
				logger.error("url:" + url + ",jsonParam:" + jsonParam, e);
			}
		}
		return result;
	}

	/**
	 * post json请求，可设置请求头
	 * 
	 * @param url
	 *            请求地址
	 * @param headMap
	 *            请求头
	 * @param jsonParam
	 *            请求内容
	 * @return
	 * @Author: liupeng
	 * @Date: 2017年6月20日
	 */
	public static String postJSONWithHead(String url, Map<String, String> headMap, JSONObject jsonParam) {
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			if (headMap != null) {
				for (String key : headMap.keySet()) {
					httpPost.addHeader(key, headMap.get(key));
				}
			} else {
				httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
			}
			StringEntity se = new StringEntity(jsonParam.toString(), "utf-8");
			se.setContentEncoding("text/json;charset=utf-8");
			se.setContentType("text/json;charset=utf-8");
			httpPost.setEntity(se);
			response = httpclient.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			return result;
		} catch (Exception e) {
			logger.error("url:" + url + ",headMap=" + headMap + ",jsonParam:" + jsonParam, e);
		} finally {
			try {
				if (response != null)
					response.close();
				if (httpclient != null)
					httpclient.close();
			} catch (IOException e) {
				logger.error("url:" + url + ",headMap=" + headMap + ",jsonParam:" + jsonParam, e);
			}
		}
		return result;
	}

	/**
	 * get请求
	 * 
	 * @param url
	 *            请求路径+参数
	 * @return 响应文本
	 * @Author: liyongzhen
	 * @Date: 2016年5月24日
	 */
	public static String get(String url) {
		return get(url, null);
	}

	/**
	 * get请求
	 * 
	 * @param url
	 *            请求路径+参数
	 * @param headerParamMap
	 *            header参数,例如token等
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年11月16日
	 */
	public static String get(String url, Map<String, String> headerParamMap) {
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			if (headerParamMap != null) {
				for (Entry<String, String> entry : headerParamMap.entrySet()) {
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
			}
			response = httpclient.execute(httpGet);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			return result;
		} catch (Exception e) {
			logger.error("url:" + url + ",headerParamMap:" + headerParamMap, e);
		} finally {
			try {
				if (response != null)
					response.close();
				if (httpclient != null)
					httpclient.close();
			} catch (IOException e) {
				logger.error("url:" + url + ",headerParamMap:" + headerParamMap, e);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// testAddOrder();
		testAddOrderAndBeihuo();

	}

	private static void testAddOrderAndBeihuo() {
		final long begin = System.currentTimeMillis();
	}

	private static void testAddOrder() {
		

	}


	public static String postEncode(String url, String encode) {
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);

			httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
			StringEntity se = new StringEntity(encode, "utf-8");
			se.setContentEncoding("text/json;charset=utf-8");
			se.setContentType("text/json;charset=utf-8");
			httpPost.setEntity(se);

			response = httpclient.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			return result;
		} catch (Exception e) {
			logger.error("url:" + url + ",jsonParam:" + encode, e);
		} finally {
			try {
				if (response != null)
					response.close();
				if (httpclient != null)
					httpclient.close();
			} catch (IOException e) {
				logger.error("url:" + url + ",jsonParam:" + encode, e);
			}
		}
		return result;
	}

}
