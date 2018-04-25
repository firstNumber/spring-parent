package com.weilai.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.weilai.common.util.MapUtil;

/**
 * *********************************
* 异常处理返回
* @author: liyongzhen
* @createdAt: 2016年6月14日下午2:55:39
**********************************
 */
public class MyExceptionHandler implements HandlerExceptionResolver{
	private Logger logger = Logger.getLogger(MyExceptionHandler.class);
	
	/**
	 * 封装异常信息返回
	* @return
	* @Author: liyongzhen
	* @Date: 2016年6月14日
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if(ex instanceof BusiException){
			logger.error(ex);//自定义异常
			return new ModelAndView(new MappingJackson2JsonView(),MapUtil.error((BusiException)ex));
		}
		
//		if(ex.getMessage().indexOf("Duplicate entry")!=-1
//				&& ex.getMessage().indexOf("for key")!=-1){
//			return new ModelAndView(new MappingJackson2JsonView(),MapUtil.error("数据重复!"));
//		}

		logger.error(ex.getMessage(),ex);
		
		if(ex instanceof MySqlException){
			return new ModelAndView(new MappingJackson2JsonView(),MapUtil.error("数据查询失败!"));
		}
		
		if(ex instanceof JsonFormatException){
			return new ModelAndView(new MappingJackson2JsonView(),MapUtil.error("参数格式错误!"));
		}
		
		if(ex instanceof MaxUploadSizeExceededException){
			return new ModelAndView(new MappingJackson2JsonView(),MapUtil.error("文件大小超过限制!"));
		}
		if(ex instanceof RuntimeException){
			if(ex.getMessage()!=null){
				if(ex.getMessage().startsWith("com.zhidian.common.exception.BusiException:")){
					String msg = ex.getMessage();
					int end =  msg.lastIndexOf("com.zhidian.common.exception.BusiException:");
					if(end>43){
						return new ModelAndView(new MappingJackson2JsonView(),MapUtil.error(msg.substring(43,end)));
					}
				}
			}
		}
		return new ModelAndView(new MappingJackson2JsonView(),MapUtil.error500());
	}

}
