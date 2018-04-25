package com.weilai.common.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.weilai.common.exception.JsonFormatException;

public class JsonUtil {
	
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
    *obj 转 json 
   * @param obj
   * @return
    */
    public  static String toJson(final Object obj) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
        	logger.error("object 转 json 出错",e);
        	throw new JsonFormatException(e);
        }
        return json;
    }

   /**
    * json 转 obj
   * @param json
   * @param clazz
   * @return
    */
    public static  <T> T toBean(final String json, final Class<T> clazz) {
    	if(json==null || clazz==null) return null;
        T t = null;
        try {
            t = objectMapper.readValue(json, clazz);
        } catch (Exception e) {
        	logger.error("json 转 obj 出错",e);
        	throw new JsonFormatException(e);
        }
        return t;
    }
    
    /**
     * byte数组转 obj
    * @param byteArr
    * @param clazz
    * @return
     */
    public static  <T> T toBean(final byte[] byteArr, final Class<T> clazz) {
        return toBean(new String(byteArr),clazz);
    }
    
    /**
     * json转 list
    * @param json
    * @param ref	例如 new TypeReference<List<TestJson>>() {}
    * @return	
    * @Author: liyongzhen
    * @Date: 2016年11月17日
     */
    public static  <T> List<T> toList(final String json, TypeReference<List<T>> ref) {
    	List<T> t = null;
    	try {
    		t = objectMapper.readValue(json, ref);
    	} catch (Exception e) {
    		logger.error("json 转 list 出错",e);
    		throw new JsonFormatException(e);
    	}
    	return t;
    }
   /**
    * json转 list
   * @param byteArr
   * @param ref
   * @return
   * @Author: liyongzhen
   * @Date: 2017年1月4日
    */
    public static  <T> List<T> toList(final byte[] byteArr, TypeReference<List<T>> ref) {
    	List<T> t = null;
    	try {
    		t = objectMapper.readValue(new String(byteArr), ref);
    	} catch (Exception e) {
    		logger.error("json 转 list 出错",e);
    		throw new JsonFormatException(e);
    	}
    	return t;
    }
    
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException{
    	TestJson t = new TestJson();
    	t.setCreateTime(new Date());
    	t.setStr("str");
    	t.setDoub(123d);
    	t.setInte(1);
    	t.setBigd(new BigDecimal("34355"));
    	String str = toJson(t);
    	System.out.println(str);
    	TestJson t2 = toBean(str, TestJson.class);
    	System.out.println(t2);
    	List<TestJson> list = new ArrayList<TestJson>();
    	list.add(t);
    	list.add(t2);
    	String listStr = toJson(list);
    	System.out.println(listStr);
    	List<TestJson> listJson = toList(listStr, new TypeReference<List<TestJson>>() {});
    	System.out.println(listJson);
    	List<TestJson> oneList = toList(str, new TypeReference<List<TestJson>>() {});
    	System.out.println(oneList);
    }
    
    
	
}

class TestJson{
	private Date createTime;
	private String str;
	private Integer inte;
	private Double doub;
	private BigDecimal bigd;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Integer getInte() {
		return inte;
	}
	public void setInte(Integer inte) {
		this.inte = inte;
	}
	public Double getDoub() {
		return doub;
	}
	public void setDoub(Double doub) {
		this.doub = doub;
	}
	
	public BigDecimal getBigd() {
		return bigd;
	}
	public void setBigd(BigDecimal bigd) {
		this.bigd = bigd;
	}
	@Override
	public String toString() {
		return "TestJson [createime=" + createTime + ", str=" + str + ", inte=" + inte + ", doub=" + doub + ", bigd="
				+ bigd + "]";
	}
	
}