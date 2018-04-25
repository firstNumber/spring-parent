package com.weilai.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.weilai.common.util.CommonConst.SortBy;
import com.weilai.common.util.PageBean;


public interface BaseDao<T> {
	/**
	 * 根据id,查询单个
	 * @param id
	 * @return
	 */
    public T get(Serializable id);
    
    /**
     * 插入单个对象
     * @param model
     * @return
     */
    public int insert(T model);
    
    /**
     * 根据主键更新对象
     * @param model
     * @return
     */
    public int update(T model);
    
    /**
     * 根据主键更新对象,如果o属性不为空,则更新,为空则不更新
     * @param model
     * @return
     */
    public int update4Selective(T model);
    
    /**
     * 根据主键删除
     * @param o
     * @return
     */
    public int delete(Serializable id);
    
    /**
     * 根据对象信息删除
     * 例如,传入 对象 User, id=1,name=a , 则删除 id=1,name=a的记录
     * 如果传入对象 User,name=a, 则删除 name=a的记录
     * @param model
     * @return
     */
    public int delete(T model);
    
	/**
	 * 查询所有记录
	* @return
	 */
	public List<T> findAll();
    
    /**
     * 根据属性名和属性值查询对象
     * @param name	属性名
     * @param value	属性值
     * @return	符合条件的对象列表
     */
	public List<T> findBy(String name, Object value);
	
    /**
     * 根据属性名和属性值查询对象
     * @param name	属性名
     * @param value	属性值
     * @param isLike 是否模糊匹配(只有属性类型为字符串,才生效)
     * @return	符合条件的对象列表
     */
	public List<T> findBy(String name, Object value,boolean isLike);
	
    /**
     * 根据属性名和属性值查询对象
     * @param name	属性名
     * @param value	属性值
     * @param sortName	排序的字段(默认正序)
     * @param sortBy	正序 or 倒序 
     * @return	符合条件的对象列表
     */
	public List<T> findBy(String name, Object value,String sortName,SortBy sortBy);
	
    /**
     * 根据属性名和属性值查询对象
     * @param name	属性名
     * @param value	属性值
     * @param isLike	是否模糊匹配(只有属性类型为字符串,才生效)
     * @param sortName	排序的字段(默认正序)
     * @param sortBy	正序 or 倒序
     * @return	符合条件的对象列表
     */
	public List<T> findBy(String name, Object value,boolean isLike,String sortName,SortBy type);
	
	/**
	 * 根据map 查询对象
	 * @param map	key=属性名,value=属性值
	 * @return	符合条件的对象列表
	 */
	public List<T> findByMap(Map<String, Object> map);
	
	/**
	 * 根据map 查询对象
	 * @param map	key=属性名,value=属性值
	 * @param isLike	是否模糊匹配(只有属性类型为字符串,才生效)
	 * @return	符合条件的对象列表
	 */
	public List<T> findByMap(Map<String, Object> map,boolean isLike);
	
	/**
	 * 根据map 查询对象
	 * @param map	key=属性名,value=属性值
	 * @param sortName	排序的字段(默认正序)
     * @param sortBy	正序 or 倒序
	 * @return	符合条件的对象列表
	 */
	public List<T> findByMap(Map<String, Object> map,String sortName,SortBy sortBy);
	
	/**
	 * 根据map 查询对象
	 * @param map	key=属性名,value=属性值
     * @param isLike	是否模糊匹配(只有属性类型为字符串,才生效)
     * @param sortName	排序的字段(默认正序)
     * @param sortBy	正序 or 倒序 
	 * @return	符合条件的对象列表
	 */
	public List<T> findByMap(Map<String, Object> map,boolean isLike,String sortName,SortBy sortBy);
	
	/**
	 * 根据属性名和属性值查询单个对象
	 * @param name	属性名
	 * @param value	属性值
	 * @return	符合条件的唯一对象,如果有多条,则返回null
	 */
	public T findUniqueBy(String name, Object value);
	
	/**
	 * 根据map 查询单个对象
	 * @param name	属性名
	 * @param value	属性值
	 * @return	符合条件的唯一对象,如果有多条,则返回null
	 */
	public T findUniqueByMap(Map<String, Object> map);
	
	/**
	 * 根据查询条件和分页信息,查询对象
	 * @param pageBean
	 * @param map	key=属性名,value=属性值
	 */
	public void find4Page(PageBean<T> pageBean,Map<String,Object> map);
	
	/**
	 * 根据查询条件和分页信息,查询对象
	 * @param pageBean
	 * @param map
     * @param isLike	是否模糊匹配(只有属性类型为字符串,才生效)
	 */
	public void find4Page(PageBean<T> pageBean,Map<String,Object> map,boolean isLike);
	
	/**
	 * 根据查询条件和分页信息,查询对象
	 * @param pageBean
	 * @param map
     * @param sortName	排序的字段(默认正序)
     * @param sortBy	正序 or 倒序 
	 */
	public void find4Page(PageBean<T> pageBean,Map<String,Object> map,String sortName,SortBy sortBy );
	
	/**
	 * 根据查询条件和分页信息,查询对象
	 * @param pageBean
	 * @param map
     * @param isLike	是否模糊匹配(只有属性类型为字符串,才生效)
     * @param sortName	排序的字段(默认正序)
     * @param sortBy	正序 or 倒序 
	 */
	public void find4Page(PageBean<T> pageBean,Map<String,Object> map,boolean isLike,String sortName,SortBy sortBy );
	
	/**
	 * 根据ids,查询对象
	* @param ids
	* @return
	* @Author: liyongzhen
	* @Date: 2015年8月21日
	 */
	public List<T> findByIds(String ids);
	
	/**
	 * 批量插入
	* @param list
	* @Author: liyongzhen
	* @Date: 2015年8月26日
	 */
	public void insertBatch(List<T> list);
	
	/**
	 * 查询总记录数
	* @param countParam
	* @return
	* @Author: liyongzhen
	* @Date: 2017年3月16日
	 */
	int findCountByMap(Map<String, Object> countParam);
    
}
