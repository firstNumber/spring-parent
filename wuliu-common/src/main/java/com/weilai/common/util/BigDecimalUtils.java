package com.weilai.common.util;

import java.math.BigDecimal;

public class BigDecimalUtils {
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 2;

	private static final BigDecimal ZERO = new BigDecimal("0");

	// 这个类不能实例化
	private BigDecimalUtils() {
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		if (v2 == 0) {
			throw new IllegalArgumentException("除数不能为0");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商 默认2位小数
	 */
	public static double div(Integer v1, Integer v2) {
		return div(Double.valueOf(v1.toString()), Double.valueOf(v2.toString()), DEF_DIV_SCALE);
	}

	/**
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            保留的小数位数
	 * @return 两个参数的商 默认2位小数
	 */
	public static double div(Integer v1, Integer v2, int scale) {
		return div(Double.valueOf(v1.toString()), Double.valueOf(v2.toString()), scale);
	}

	/**
	 * 比较2个值大小,返回最小的, 如果2个都是null,则返回null,如果1个是null,则返回另一个
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @Author: wangxingfei
	 * @Date: 2017年5月11日
	 */
	public static BigDecimal min(BigDecimal v1, BigDecimal v2) {
		if (v1 == v2) {
			return v1;
		}
		if (v1 == null) {
			return v2;
		}
		if (v2 == null) {
			return v1;
		}
		if (v1.compareTo(v2) <= 0) {
			return v1;
		}
		return v2;
	}

	/**
	 * 比较2个值大小,返回最大的, 如果2个都是null,则返回null,如果1个是null,则返回另一个
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @Author: wangxingfei
	 * @Date: 2017年5月13日
	 */
	public static BigDecimal max(BigDecimal v1, BigDecimal v2) {
		if (v1 == v2) {
			return v1;
		}
		if (v1 == null) {
			return v2;
		}
		if (v2 == null) {
			return v1;
		}
		if (v1.compareTo(v2) > 0) {
			return v1;
		}
		return v2;
	}

	/**
	 * 判断val是否等0, 如果是null,返回false
	 * 
	 * @param val
	 * @return
	 * @Author: wangxingfei
	 * @Date: 2017年5月13日
	 */
	public static boolean isZero(BigDecimal val) {
		if (val != null && ZERO.compareTo(val) == 0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isZero(null));
	}
}
