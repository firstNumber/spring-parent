package com.weilai.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.weilai.common.exception.BusiException;

public class MapUtil {
	public static final Integer SUCCESS_CODE = 1;// 默认成功状态码
	public static final Integer ERROR_CODE = 2; // 默认失败状态码

	/**
	 * 返回错误信息(错误码500)
	 * 
	 * @param message
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	public static Map<String, Object> error500() {
		return toMap(500, "网络异常,请刷新重试!", null);
	}

	/**
	 * 返回错误信息(错误码默认2)
	 * 
	 * @param message
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	public static Map<String, Object> error(String message) {
		return toMap(ERROR_CODE, message, null);
	}

	/**
	 * 返回错误信息
	 * 
	 * @param message
	 *            提示信息
	 * @param errorCode
	 *            错误码
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	public static Map<String, Object> error(String message, Integer errorCode) {
		return toMap(errorCode, message, null);
	}

	/**
	 * 返回正确结果
	 * 
	 * @param result
	 *            结果内容
	 * @param message
	 *            提示语
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	public static Map<String, Object> success(Object result, String message) {
		return toMap(SUCCESS_CODE, message, result);
	}

	/**
	 * 一些错误情况,也需要返回result
	* @param code
	* @param result
	* @param message
	* @return
	* @Author: liyongzhen
	* @Date: 2017年4月10日
	 */
	public static Map<String, Object> error(Integer code, Object result, String message) {
		return toMap(code, message, result);
	}

	/**
	 * 返回正确结果
	 * 
	 * @param result
	 *            结果内容
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	public static Map<String, Object> success(Object result) {
		return toMap(SUCCESS_CODE, "操作成功!", result);
	}

	/**
	 * 操作成功
	 * 
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	public static Map<String, Object> success200() {
		return toMap(SUCCESS_CODE, "操作成功!", null);
	}

	/**
	 * 封装返回结果
	 * 
	 * @param code
	 * @param message
	 * @param result
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	private static Map<String, Object> toMap(Integer code, String message, Object result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", code);
		map.put("message", message);
		if (result != null)
			map.put("result", result);
		return map;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 分页数据的封装
	 * 
	 * @param objList
	 *            明细list
	 * @param totalRows
	 *            总记录数
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年5月17日
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> success4Page(List objList, Integer totalRows, Integer totalPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", SUCCESS_CODE);// 状态固定为成功
		map.put("totalRow", totalRows);// 总记录数
		map.put("totalPage", totalPage);// 总页数
		map.put("message", "操作成功!");// 提示信息
		map.put("result", objList);// 结果明细
		return map;
	}

	/**
	 * 分页封装数据
	 * 
	 * @param pageBean
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年6月25日
	 */
	public static Map<String, Object> success4Page(PageBean<?> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", SUCCESS_CODE);// 状态固定为成功
		map.put("totalRow", pageBean.getTotalRows());// 总记录数
		map.put("totalPage", pageBean.getTotalPages());// 总页数
		map.put("message", "操作成功!");// 提示信息
		map.put("result", pageBean.getObjList());// 结果明细
		return map;
	}

	/**
	 * 将异常状态和信息返回
	 * 
	 * @param e
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年6月2日
	 */
	public static Map<String, Object> error(BusiException e) {
		return toMap(e.getErrorCode(), e.getMessage(), null);
	}

	/**
	 * 将错误状态和信息返回
	 * 
	 * @param resultBean
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年12月8日
	 */
	public static Map<String, Object> error(ResultBean<?> resultBean) {
		return toMap(resultBean.getStatus(), resultBean.getErrorMsg(), null);
	}
	
	/**
	 * 返回警告信息
	 * 
	 * @param message
	 *            提示信息
	 * @param warnCode
	 *            警告码
	 * @return
	 * @Author: xiedonghua
	 * @Date: 2017年8月8日
	 */
	public static Map<String, Object> warn(Integer warnCode, String message) {
		return toMap(warnCode, message, null);
	}

}
