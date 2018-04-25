package com.weilai.common.amin;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;
public class TrRandomM {
	public static void main(String[] args) {
		ThreadLocalRandom.current();
		System.out.println(System.currentTimeMillis());
		ExecutorService excutor = Executors.newCachedThreadPool();
		
		Thread.holdsLock(TrRandomM.class);
		
		ReentrantLock a = new ReentrantLock();
	}
}
