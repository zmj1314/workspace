package com.atmyhome.bean;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *@author 朱梦君
 *@datetime 2017年5月21日 下午11:49:02
 *@version 1.0
 *@see 
 */
public class TestCallable {
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		FutureTask<Integer> result = new FutureTask<>(td);
		new Thread(result).start();
		try {
			Integer sum = result.get();
			System.out.println(sum);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class ThreadDemo implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i=0;i<100;i++){
			sum+=i;
		}
		return sum;
	}
	
}