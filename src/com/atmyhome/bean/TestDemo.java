package com.atmyhome.bean;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *@author 朱梦君
 *@datetime 2017年5月20日 下午12:57:12
 *@version 1.0
 *@see 
 */
public class TestDemo {
	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(10);
		Demo ad = new Demo(latch);
		long start = System.currentTimeMillis();
		try {
			for (int i = 0; i < 10; i++) {
				new Thread(ad).start();
			}
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
}
class Demo implements Runnable{
	private CountDownLatch latch;
	
	public Demo(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		synchronized(this){
			
			try {
				for (int i = 0; i < 5000; i++) {
					if(i%2 == 0){
						System.out.println(i);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				latch.countDown();
			}
		}
	}
}