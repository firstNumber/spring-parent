package com.weilai.common.amin;

import java.util.Random;

public class RandomM {

	public static void main(String[] args) {
//		Random random = new Random(10000);
//		System.out.println(random.nextInt(10));
//		System.out.println(random.nextInt(10));
		
		Random randoma = new Random();
		Random randomb = new Random();
//		System.out.println(randoma.nextInt(10));
//		System.out.println(randomb.nextInt(10));
		
		ThreadLocal<Integer> t = new ThreadLocal<Integer>();
		ThreadLocal<Integer> t2 = new ThreadLocal<Integer>();
		
		t.set(randoma.nextInt(10));
		t2.set(randomb.nextInt(10));
		System.out.println(t.get());
		System.out.println(t2.get());
	}

}
