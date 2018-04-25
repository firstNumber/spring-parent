package com.weilai.common.service.impl;

import java.util.List;
import java.util.Map;

import com.weilai.common.cache.CacheEnum;
import com.weilai.common.dao.BaseDao;
import com.weilai.common.service.BaseService;
import com.weilai.common.util.CommonConst.SortBy;
import com.weilai.common.util.CommonConst.UpdateCacheType;
import com.weilai.common.util.PageBean;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	public BaseServiceImpl() {
	}

	/**
	 * 由业务类实现
	 * 
	 * @return
	 */
	public abstract BaseDao<T> getBaseDao();

	// protected BaseDao<T> baseDao;

	// public abstract void setBaseDao(BaseDao<T> baseDao);

	/**
	 * 根据id,查询单个
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public T get(Integer id) {
		return getBaseDao().get(id);
	}

	/**
	 * 插入单个对象
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public int create(T model) {
		int result = getBaseDao().insert(model);
		removeCache(UpdateCacheType.CREATE, model);
		return result;
	}

	/**
	 * 根据主键更新对象,更新对象所有字段
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public int update(T model) {
		int result = getBaseDao().update(model);
		removeCache(UpdateCacheType.UPDATE, model);
		return result;
	}

	/**
	 * 根据主键更新对象,如果o属性不为空,则更新,为空则不更新 可用于表字段过多,只更新部分字段时候. T t = new T();
	 * t.setId(xx); t.setXX(xx); update4Selective(t);
	 * 
	 * @param model
	 * @return
	 */
	@Override
	public int update4Selective(T model) {
		int result = getBaseDao().update4Selective(model);
		removeCache(UpdateCacheType.UPDATE4SELECTIVE, model);
		return result;
	}

	/**
	 * 根据主键删除
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public int delete(Integer id) {
		int result = getBaseDao().delete(id);
		removeCache(UpdateCacheType.DELETE_ID, id);
		return result;
	}

	/**
	 * 根据对象信息删除 例如,传入 对象 User, id=1,name=a , 则删除 id=1,name=a的记录 如果传入对象
	 * User,name=a, 则删除 name=a的记录
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public int delete(T model) {
		int result = getBaseDao().delete(model);
		removeCache(UpdateCacheType.DELETE, model);
		return result;
	}

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	@Override
	public List<T> findAll() {
		return getBaseDao().findAll();
	}

	/**
	 * 根据属性名和属性值查询对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @return 符合条件的对象列表
	 */
	@Override
	public List<T> findBy(String name, Object value) {
		return getBaseDao().findBy(name, value);
	}

	/**
	 * 根据属性名和属性值查询对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 * @return 符合条件的对象列表
	 */
	@Override
	public List<T> findBy(String name, Object value, boolean isLike) {
		return getBaseDao().findBy(name, value, isLike);
	}

	/**
	 * 根据属性名和属性值查询对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @param sortName
	 *            排序的字段(默认正序)
	 * @param sortBy
	 *            正序 or 倒序
	 * @return 符合条件的对象列表
	 */
	@Override
	public List<T> findBy(String name, Object value, String sortName, SortBy sortBy) {
		return getBaseDao().findBy(name, value, false, sortName, sortBy);
	}

	/**
	 * 根据属性名和属性值查询对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 * @param sortName
	 *            排序的字段(默认正序)
	 * @param sortBy
	 *            正序 or 倒序
	 * @return 符合条件的对象列表
	 */
	@Override
	public List<T> findBy(String name, Object value, boolean isLike, String sortName, SortBy sortBy) {
		return getBaseDao().findBy(name, value, isLike, sortName, sortBy);
	}

	/**
	 * 根据map 查询对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @return 符合条件的对象列表
	 */
	@Override
	public List<T> findByMap(Map<String, Object> map) {
		return getBaseDao().findByMap(map);
	}

	/**
	 * 根据map 查询对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 * @return 符合条件的对象列表
	 */
	@Override
	public List<T> findByMap(Map<String, Object> map, boolean isLike) {
		return getBaseDao().findByMap(map, isLike);
	}

	/**
	 * 根据map 查询对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @param sortName
	 *            排序的字段(默认正序)
	 * @param sortBy
	 *            正序 or 倒序
	 * @return 符合条件的对象列表
	 */
	@Override
	public List<T> findByMap(Map<String, Object> map, String sortName, SortBy sortBy) {
		return getBaseDao().findByMap(map, sortName, sortBy);
	}

	/**
	 * 根据map 查询对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 * @param sortName
	 *            排序的字段(默认正序)
	 * @param sortBy
	 *            正序 or 倒序
	 * @return 符合条件的对象列表
	 */
	@Override
	public List<T> findByMap(Map<String, Object> map, boolean isLike, String sortName, SortBy sortBy) {
		return getBaseDao().findByMap(map, isLike, sortName, sortBy);
	}

	/**
	 * 根据属性名和属性值查询单个对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @return 符合条件的唯一对象,如果有多条,则返回null
	 */
	@Override
	public T findUniqueBy(String name, Object value) {
		return getBaseDao().findUniqueBy(name, value);
	}

	/**
	 * 根据map 查询单个对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @return 符合条件的唯一对象,如果有多条,则返回null
	 */
	@Override
	public T findUniqueByMap(Map<String, Object> map) {
		return getBaseDao().findUniqueByMap(map);
	}

	/**
	 * 根据查询条件和分页信息,查询对象
	 * 
	 * @param pageBean
	 * @param map
	 */
	@Override
	public void find4Page(PageBean<T> pageBean, Map<String, Object> map) {
		getBaseDao().find4Page(pageBean, map);
	}

	/**
	 * 根据查询条件和分页信息,查询对象
	 * 
	 * @param pageBean
	 * @param map
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 */
	@Override
	public void find4Page(PageBean<T> pageBean, Map<String, Object> map, boolean isLike) {
		getBaseDao().find4Page(pageBean, map, isLike);
	}

	/**
	 * 根据查询条件和分页信息,查询对象
	 * 
	 * @param pageBean
	 * @param map
	 * @param sortName
	 *            排序的字段(默认正序)
	 * @param sortBy
	 *            正序 or 倒序
	 */
	@Override
	public void find4Page(PageBean<T> pageBean, Map<String, Object> map, String sortName, SortBy sortBy) {
		getBaseDao().find4Page(pageBean, map, sortName, sortBy);
	}

	/**
	 * 根据查询条件和分页信息,查询对象
	 * 
	 * @param pageBean
	 * @param map
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 * @param sortName
	 *            排序的字段(默认正序)
	 * @param sortBy
	 *            正序 or 倒序
	 */
	@Override
	public void find4Page(PageBean<T> pageBean, Map<String, Object> map, boolean isLike, String sortName,
			SortBy sortBy) {
		getBaseDao().find4Page(pageBean, map, isLike, sortName, sortBy);
	}

	/**
	 * 根据ids,查询对象
	 * 
	 * @param ids
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2015年8月21日
	 */
	@Override
	public List<T> findByIds(String ids) {
		return getBaseDao().findByIds(ids);
	}

	/**
	 * 用于redis查询数据库, 各模块需要需override
	 * 如果insert,update,delete等操作过后需要清空缓存,则各模块需要重写removeCache方法 注意:重写必须catch所有异常
	 * 
	 * @param cacheEnum
	 * @param value
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年5月17日
	 */
	@Override
	public Object get4CacheFactory(CacheEnum cacheEnum, Object value) {
		throw new RuntimeException("must Override get4CacheFactory");
	}

	/**
	 * remove缓存
	 * 
	 * @Author: liyongzhen
	 * @Date: 2016年10月13日
	 */
	protected void removeCache(UpdateCacheType type, Object value) {

	}

	/**
	 * 查询总记录数
	 * 
	 * @param countParam
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2017年3月16日
	 */
	@Override
	public int findCountByMap(Map<String, Object> countParam) {
		return getBaseDao().findCountByMap(countParam);
	}
}
