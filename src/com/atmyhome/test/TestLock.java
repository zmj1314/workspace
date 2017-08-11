package com.atmyhome.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@author 朱梦君
 *@datetime 2017年5月22日 下午1:07:58
 *@version 1.0
 *@see 
 */
public class TestLock {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(ticket,"chu1").start();
		new Thread(ticket,"chu2").start();
		new Thread(ticket,"chu3").start();
	}
}
class Ticket implements Runnable{
	private int tick = 100;
	private Lock lock = new ReentrantLock();
	@Override
	public void run() {
		while(true){
			try {
				lock.lock();
				if(tick > 0){
					Thread.sleep(200);
					System.out.println(Thread.currentThread().getName()+">>>>>"+(--tick));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
		}
	}
	
}