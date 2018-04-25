package com.weilai.common.amin;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ExecutorCompletion {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(100);
		CompletionService<Long> completionService = new ExecutorCompletionService<Long>(executor);
		int groupNum = 1000000/100;
		
		long a =System.currentTimeMillis();
		for(int i = 1; i<=100;i++){
			final int start = (i-1)*groupNum+1;
			final int end = i*groupNum;
			completionService.submit(new Callable<Long>() {
				public Long call(){
					Long sum = 0L;
					for(int j =start;j<=end;j++){
						System.out.println(Thread.currentThread().getName() +" is looping of "+j+" for start of "+start+"f"
								+ "or end of "+end);
						sum +=j;
					}
					return sum;
				}
			});
		}
		
		System.out.println("你走你的,我先走一步");
		Thread.sleep(1000);
		long result = 0L;
		for(int i =0;i<100;i++){
			try {
				result += completionService.take().get();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		long b =System.currentTimeMillis();
		System.out.println("所有任务执行结束......."+result+"\t"+(b-a));
		executor.shutdown();
	}
}
