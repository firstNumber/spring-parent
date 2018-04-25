package com.weilai.common.redis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weilai.common.util.SpringContextUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * 
 * redis操作封装
 * 
 */
public class JedisUtil {

	private static Log logger = LogFactory.getLog(JedisUtil.class);

	protected static JedisPool jedisPool = (JedisPool) SpringContextUtil.getBean("jedisPool");;

	/**
	 * 获取redis操作实例（不必加锁）
	 * 
	 * @return jedis
	 */
	protected static Jedis getJedis() throws JedisException {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (JedisException e) {
			logger.warn("failed:jedisPool getResource.", e);
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		}
		return jedis;
	}

	protected static void release(Jedis jedis, boolean isBroken) {
		if (jedis != null) {
			if (isBroken) {
				jedisPool.returnBrokenResource(jedis);
			} else {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public static void setKO(String key, Object obj) {
		setKOT(key, obj, 0);
	}

	public static void setKOT(String key, Object obj, int seconds) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			String value = JedisSerializeJsonUtil.serialize(obj);
			jedis.set(key, value);
			if (seconds > 0) {
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao::setKOT: key: " + key + " obj: " + obj + " seconds: " + seconds + " message: "
					+ e.getMessage(), e);
		} finally {
			release(jedis, isBroken);
		}
	}

	public static void setKList(String key, List<?> list) {
		setKListT(key, list, 0);
	}

	public static void setKListT(String key, List<?> list, int seconds) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.del(key);// 删除原来的

			for (Object obj : list) {
				String value = JedisSerializeJsonUtil.serialize(obj);
				jedis.rpush(key, value);
			}
			if (seconds > 0) {
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			isBroken = true;
			logger.error(
					"JedisDao::setKList: key: " + key + " list: " + list.toString() + " message: " + e.getMessage(), e);
		} finally {
			release(jedis, isBroken);
		}
	}

	public static <T> List<T> getKList(String key, Class<T> clazz) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			List<String> results = jedis.lrange(key, 0, -1);
			if (CollectionUtils.isEmpty(results)) {
				return null;
			}
			List<T> list = new ArrayList<T>();
			for (String str : results) {
				T t = JedisSerializeJsonUtil.unserialize(str, clazz);
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			isBroken = true;
			logger.error(
					"JedisDao::getKList: key: " + key + " clazz: " + clazz.toString() + " message: " + e.getMessage(),
					e);
		} finally {
			release(jedis, isBroken);
		}
		return null;
	}

	/**
	 * redis get 操作
	 * 
	 * @param key
	 * @return
	 */
	public static <T> T getObject(String key, Class<T> clazz) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			String value = jedis.get(key);
			T t = JedisSerializeJsonUtil.unserialize(value, clazz);
			return t;
		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao::getObject: key: " + key + " message: " + e.getMessage(), e);
		} finally {
			release(jedis, isBroken);
		}
		return null;

	}

	/**
	 * redis del 操作
	 * 
	 * @param key
	 */
	public static void remove(String key) {

		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.del(key);
		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao::remove: key: " + key + " message: " + e.getMessage(), e);
		} finally {
			release(jedis, isBroken);
		}

	}

	public static void remove(String[] delKeys) {

		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.del(delKeys);

		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao::remove: key: " + delKeys.toString() + " message: " + e.getMessage(), e);
		} finally {
			release(jedis, isBroken);
		}

	}

	/***
	 * 删除全部缓存，慎用。
	 */
	public static void flushAll() {

		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.flushAll();

		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao::flushAll:" + e.getMessage(), e);
		} finally {
			release(jedis, isBroken);
		}

	}

	public static Set<String> keys(String cacheName) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			Set<String> keys = jedis.keys(cacheName + "*");
			return keys;
		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao::rpush: keys: " + cacheName + " message: " + e.getMessage(), e);
		} finally {
			release(jedis, isBroken);
		}
		return new HashSet<String>();
	}

	public static String view(String key) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			String type = jedis.type(key);
			if (type.equals("string")) {
				String value = jedis.get(key);
				return value;
			} else if (type.equals("list")) {
				List<String> list = jedis.lrange(key, 0, -1);
				return list.toString();
			} else if (type.equals("none")) {
				return "缓存=none";
			} else {
				return "缓存类型为" + type + ",不支持!";
			}
		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao::getObject: key: " + key + " message: " + e.getMessage(), e);
		} finally {
			release(jedis, isBroken);
		}
		return null;
	}

	/**
	 * 如果key不存在,则设置成功
	 * 
	 * @param key
	 * @param value
	 * @param expireMillis
	 *            过期时间,毫秒
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2017年9月27日
	 */
	public static boolean setnx(String key, String value, Long expireMillis) {
		Jedis jedis = null;
		boolean isBroken = false;
		boolean flag = false;
		try {
			jedis = getJedis();
			if (value != null) {
				value = JedisSerializeJsonUtil.serialize(value);
			}
			// nx = not exist, px= 单位是毫秒
			String result = jedis.set(key, value, "NX", "PX", expireMillis);
			if (result != null && result.equalsIgnoreCase("OK")) {
				flag = true;
			}
		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao::setnx: key: " + key + ",value:" + value + " message: " + e.getMessage(), e);
		} finally {
			release(jedis, isBroken);
		}
		return flag;
	}

	/**
	 * 设置过期时间
	 * 
	 * @param fullKey
	 * @param lockTime
	 * @Author: liyongzhen
	 * @Date: 2017年7月4日
	 */
	public static void expire(String fullKey, int lockTime) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.expire(fullKey, lockTime);
		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao:expire: key: " + fullKey + ",lockTime:" + lockTime + " message: " + e.getMessage(),
					e);
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 查看key的有效期
	 * 
	 * @param fullKey
	 * @return 如果key不存在或者已过期，返回 -2 ;如果key没有设置过期时间（永久有效），返回 -1 ,否则返回有效期(单位秒)
	 * @Author: liyongzhen
	 * @Date: 2017年7月4日
	 */
	public static long ttl(String fullKey) {
		Jedis jedis = null;
		boolean isBroken = false;
		Long result = 0L;
		try {
			jedis = getJedis();
			result = jedis.ttl(fullKey);
		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao: ttl : key: " + fullKey + " message: " + e.getMessage(), e);
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 自增
	 * 
	 * @param key
	 * @param num
	 * @return
	 * @Author: liupeng
	 * @Date: 2017年6月9日
	 */
	public static Long incrBy(String key, long num) {
		Jedis jedis = null;
		boolean isBroken = false;
		long result = 0L;
		try {
			jedis = getJedis();
			result = jedis.incrBy(key, num);
		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao::incrby: key: " + key + " message: " + e.getMessage(), e);
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 自减
	 * 
	 * @param key
	 * @param num
	 * @return
	 * @Author: liupeng
	 * @Date: 2017年6月9日
	 */
	public static Long decrBy(String key, long num) {
		Jedis jedis = null;
		boolean isBroken = false;
		long result = 0L;
		try {
			jedis = getJedis();
			result = jedis.decrBy(key, num);
		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao::decrBy: key: " + key + " message: " + e.getMessage(), e);
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	public static boolean unlock(String fullKey, String value) {
		Jedis jedis = null;
		boolean isBroken = false;
		boolean result = false;
		try {
			jedis = getJedis();
			jedis.watch(fullKey);
			String existValue = jedis.get(fullKey);
			existValue = JedisSerializeJsonUtil.unserialize(existValue, String.class);
			if (Objects.equals(value, existValue)) {
				jedis.del(fullKey);
				result = true;
			} else {
				logger.info("unlock failed ; key:" + fullKey + ",value:" + value + ",existValue:" + existValue);
			}
		} catch (Exception e) {
			isBroken = true;
			logger.error("JedisDao::unlock: key: " + fullKey + ",value:" + value + ", message: " + e.getMessage(), e);
		} finally {
			jedis.unwatch();
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 从左侧弹出 对象, 用blpop,阻塞进行,没有消息则一直等待
	 * 
	 * @param key
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年6月6日
	 */
	// protected static Object blpop(String cacheName) {
	// Jedis jedis = null;
	// Object obj = null;
	// boolean isBroken = false;
	// try {
	// jedis = getJedis();
	//// System.out.println("我的心在等待,永远在等待.........");
	// byte[] bKey = cacheName.getBytes();
	// List<byte[]> results = jedis.blpop(0,bKey);
	//// System.out.println("你快回来");
	// if(!results.isEmpty()){
	// //第一个元素是key,第二个是value
	//// System.out.println(new String(results.get(0)));
	// obj = JedisSerializeJsonUtil.unserialize(results.get(1));
	// }
	// } catch (Exception e) {
	// isBroken = true;
	// logger.error(e.getMessage(), e);
	// } finally {
	// release(jedis, isBroken);
	// }
	// return obj;
	// }

	/**
	 * 从右侧 放入 对象
	 * 
	 * @param cacheName
	 * @param obj
	 * @Author: liyongzhen
	 * @Date: 2016年6月6日
	 */
	// protected static void rpush(String cacheName,Object obj) {
	// if(obj==null) return;
	// Jedis jedis = null;
	// boolean isBroken = false;
	// try {
	// jedis = getJedis();
	// byte[] bKey = cacheName.getBytes();
	// byte[] bObj = JedisSerializeJsonUtil.serialize(obj);
	// jedis.rpush(bKey, bObj);
	// } catch (Exception e) {
	// isBroken = true;
	// logger.error("RedisQueue::rpush: obj: " + obj, e);
	// } finally {
	// release(jedis, isBroken);
	// }
	// }

}
