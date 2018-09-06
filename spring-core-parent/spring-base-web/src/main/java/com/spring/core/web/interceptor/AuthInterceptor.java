package com.spring.core.web.interceptor;

import com.spring.core.web.annotation.RoleAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author liyongzhen
 * @create 2018-05-07 9:15
 **/
public class AuthInterceptor  {
//    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
//    public static final String ERROR_RESPONSE_AUTH_FORBIDDEN = "无权访问";
//	public static final String ERROR_RESPONSE_AUTH_TOKEN_ROLE = "token角色错误，请重新登录";
//	public static final String ERROR_RESPONSE_AUTH_TOKEN_FAKE = "token签名错误，请重新登录";
//	public static final String ERROR_RESPONSE_AUTH_TOKEN_TIMEOUT = "token过期，请重新登录";
//	public static final String ERROR_RESPONSE_AUTH_JWTPARSE = "无效的token";
//
//	public static final String HEADER_AUTH = "Authorization";
//	public static final String X_USERID = "X-USERID";
//	public static final String JWT_SECRET = "secret-tango";
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
//        if(StringUtils.isEmpty(httpServletRequest.getHeader("from"))){
//            return  true;
//        }
//        String auth = httpServletRequest.getHeader(HEADER_AUTH);
//		String userId = authorization(handler, auth);
//		if (!StringUtils.isEmpty(userId)) {
//			httpServletRequest.setAttribute(X_USERID, userId);
//		}
//		return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//
//
//    private String authorization(Object handler,String auth){
//        if(handler ==null)
//            return null;
//        Method method = null;
//        RoleAuth roleAuth = null;
//        if(handler instanceof HandlerMethod){
//            method =((HandlerMethod) handler).getMethod();
//            if(!method.isAnnotationPresent(RoleAuth.class)){
//                return null;
//            }
//            roleAuth = method.getAnnotation(RoleAuth.class);
//            if(roleAuth == null){
//                return null;
//            }
//        }
//
//        if(StringUtils.isEmpty(auth)){
//            throw new ForbiddenTargetException(AuthInterceptor.ERROR_RESPONSE_AUTH_TOKEN_FAKE);
//        }
//        Jws<Claims> jwt = null;
//		try {
//			jwt = Jwts.parser().setSigningKey(JWT_SECRET.getBytes("UTF-8")).parseClaimsJws(auth);
//		} catch (ExpiredJwtException e1) {
//			throw new ForbiddenTargetException(AuthInterceptor.ERROR_RESPONSE_AUTH_TOKEN_TIMEOUT);
//		} catch (Exception e1) {
//			throw new ForbiddenTargetException(AuthInterceptor.ERROR_RESPONSE_AUTH_JWTPARSE);
//		}
//		Claims claims = jwt.getBody();
//		String userId = String.valueOf(claims.get("id"));
//		if (StringUtils.isEmpty(userId)) {
//			logger.debug("error:auth.userId.empty");
//			throw new ForbiddenTargetException(AuthInterceptor.ERROR_RESPONSE_AUTH_TOKEN_FAKE);
//		}
//		String userRole = String.valueOf(claims.get("role"));
//		if (StringUtils.isEmpty(userRole)) {
//			logger.debug("error:auth.role.empty");
//			throw new ForbiddenTargetException(AuthInterceptor.ERROR_RESPONSE_AUTH_TOKEN_ROLE);
//		}
//        return "";
//    }
}
