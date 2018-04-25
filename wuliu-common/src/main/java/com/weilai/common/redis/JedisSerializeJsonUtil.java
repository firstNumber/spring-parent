package com.weilai.common.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.weilai.common.util.JsonUtil;

public class JedisSerializeJsonUtil {
private final static Log logger = LogFactory.getLog(JsonUtil.class);
	
    private static ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES,true);
        objectMapper.configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE,true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    /**
     * obj 转 json
    * @param obj
    * @return
    * @Author: liyongzhen
    * @Date: 2017年2月17日
     */
    private  static String toJson(final Object obj) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
        	logger.error("redis序列化出错,obj:"+obj,e);
        }
        return json;
    }

   /**
    * json 转 obj
   * @param json
   * @param clazz
   * @return
    */
    private static  <T> T toBean(final String json, final Class<T> clazz) {
    	if(json==null || clazz==null) return null;
        T t = null;
        try {
            t = objectMapper.readValue(json, clazz);
        } catch (Exception e) {
        	logger.error("redis反序列化,json:"+json+",clazz:"+clazz,e);
        }
        return t;
    }
    
//    private static  <T> List<T> toList(final String json, TypeReference<List<T>> ref) {
//    	List<T> t = null;
//    	try {
//    		t = objectMapper.readValue(json, ref);
//    	} catch (Exception e) {
//    		logger.error("json 转 list 出错",e);
//    		throw new JsonFormatException(e);
//    	}
//    	return t;
//    }
	
    /**
     * 序列化为json格式
    * @param object
    * @return
    * @Author: liyongzhen
    * @Date: 2017年2月17日
     */
	public static String serialize(Object object) {
		return toJson(object);
	}

	public static <T> T unserialize(String value,Class<T> clazz) {
		return toBean(value, clazz);
	}
}
