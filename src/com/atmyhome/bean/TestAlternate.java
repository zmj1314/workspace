package com.atmyhome.bean;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@author 朱梦君
 *@datetime 2017年5月23日 下午11:46:06
 *@version 1.0
 *@see 
 */
public class TestAlternate {
	public static void main(String[] args) {
		AlternateDemo altd = new AlternateDemo();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 20; i++) {
					altd.loopA(i);
				}
			}
		},"A").start();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 20; i++) {
					altd.loopB(i);
				}
			}
		},"B").start();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 20; i++) {
					altd.loopC(i);
					System.out.println("*****************************");
				}
			}
		},"C").start();
		
	}
}
class AlternateDemo{
	private int number = 1;
	private Lock lock = new ReentrantLock();
	private Condition codition1= lock.newCondition();
	private Condition codition2= lock.newCondition();
	private Condition codition3= lock.newCondition();
	
	public void loopA(int totalLoop){
		lock.lock();
		try {
			if(number != 1){
				codition1.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()+i+" "+totalLoop);
			}
			number = 2;
			codition2.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
	public void loopB(int totalLoop){
		lock.lock();
		try {
			if(number != 2){
				codition2.await();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()+i+" "+totalLoop);
			}
			number = 3;
			codition3.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
	public void loopC(int totalLoop){
		lock.lock();
		try {
			if(number != 3){
				codition3.await();
			}
			for (int i = 0; i < 15; i++) {
				System.out.println(Thread.currentThread().getName()+i+" "+totalLoop);
			}
			number = 1;
			codition1.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
}