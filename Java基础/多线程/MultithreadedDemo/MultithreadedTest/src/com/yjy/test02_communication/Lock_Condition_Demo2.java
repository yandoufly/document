package com.yjy.test02_communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��ӡ����1~10
 * ʹ��Lock��Conditionʵ���̼߳�ͨ�Ż���
 */
public class Lock_Condition_Demo2 {
	
	private int i = 1;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	/**
	 * ������ӡ
	 */
	public void odd() {
		lock.lock();
		try {
			while (i <= 10) {
				if (i % 2 == 1) {
					System.out.println("����" + i);
					i++;
					condition.signal(); // ����ż���̴߳�ӡ
				} else {
					try {
						condition.await(); // �ȴ�ż���̴߳�ӡ
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * ż����ӡ
	 */
	public void even() {
		lock.lock();
		try {
			while (i <= 10) {
				if (i % 2 == 0) {
					System.out.println("����" + i);
					i++;
					condition.signal(); // ����ż���̴߳�ӡ
				} else {
					try {
						condition.await(); // �ȴ�ż���̴߳�ӡ
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		Lock_Condition_Demo2 demo = new Lock_Condition_Demo2();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo.odd();
			}
		}).start(); // ���������̴߳�ӡ
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo.even();
			}
		}).start(); // ����ż���̴߳�ӡ
	}
}
