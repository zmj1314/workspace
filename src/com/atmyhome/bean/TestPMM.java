package com.atmyhome.bean;

import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@author 朱梦君
 *@datetime 2017年5月23日 上午12:05:57
 *@version 1.0
 *@see 
 */
public class TestPMM {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		//int days = calendar.DAY_OF_YEAR;
		long timeInMillis = calendar.getTimeInMillis();
		
		System.out.println(timeInMillis/1000/60/60/24);
		/*Clerk clerk = new Clerk();
		Productor pro = new Productor(clerk);
		Consumer cus = new Consumer(clerk);
		new Thread(pro, "scz1: ").start();
		new Thread(pro, "scz2: ").start();
		new Thread(cus,"xfz1: ").start();
		new Thread(cus,"xfz2: ").start();*/
	}

}
class Clerk{
	private int product = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	//进货
	public  void get(){
		lock.lock();
		try {
			while(product >= 10){
				System.out.println("满了");
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				System.out.println(Thread.currentThread().getName()+": "+(++product));
				condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void sale(){
		lock.lock();

		try {
			while(product <=0){
				System.out.println("缺货");
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				System.out.println(Thread.currentThread().getName()+": "+(--product));
				condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}
class Productor implements Runnable{
	private Clerk clerk;
	
	public Productor(Clerk clerk) {
		super();
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			clerk.get();
		}
	}
	
}
class Consumer implements Runnable{
	private Clerk clerk;
	
	public Consumer(Clerk clerk) {
		super();
		this.clerk = clerk;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			clerk.sale();
		}
	}
	
}