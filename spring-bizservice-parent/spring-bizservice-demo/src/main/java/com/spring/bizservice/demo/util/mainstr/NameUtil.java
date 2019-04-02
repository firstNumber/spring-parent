package com.spring.bizservice.demo.util.mainstr;

/**
 * @author liyongzhen
 * @create 2019-03-22 16:21
 **/
public class NameUtil {
    private static final int divide = 1000;
    private static final long baseline = 1548864000;


    public static String generateTmsCode(TmsType type, Integer id) {
		if (type.name().equals("ORDER"))
			return type.getCode() + System.currentTimeMillis() + String.valueOf(Math.random() * 100000 + 100000).substring(0, 6);
		// 若id不为空
		if (id != null && id > 0) {
			int count = type.name().equals("CUSTOMER") ? 6 : 8;
			String code = "00000000" + id;
			return type.getCode() + code.substring(code.length() - count);
		}

		// 每秒可以生成999个，若访问量增大需要改进方法 最少为六位
		int mulNum = 1000;
		int random = (int) (Math.random() * mulNum);
		long minute = System.currentTimeMillis() / divide;
		long orderNum = (minute - baseline) * mulNum + random;
		String radixNum = Long.toString(orderNum, Character.MAX_RADIX);

		System.out.println("random=" + random + ",minute=" + minute + ",orderNum=" + orderNum + ",radixNum=" + radixNum);

		// 不足六位以0填充
		int prefixNum = 7 - radixNum.length();
		if (prefixNum > 0) {
			String format = "%0" + prefixNum + "d";
			String r = String.format(format, 0) + radixNum;
			radixNum = r;
		}

		return (type.getCode() + radixNum).toUpperCase();
	}

    public static void main(String[] args) {
        System.out.println(generateTmsCode(TmsType.WAREHOUSE,null));
    }
}
