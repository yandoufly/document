package com.yjy.test06_wait_parent_thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	
	static class SubThread implements Runnable {
		private CountDownLatch countDownLatch;
		public SubThread(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}
		
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			System.out.println(name + "��ʼִ��");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + "ִ�����...");
			countDownLatch.countDown();
		}
	}
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		SubThread subThread = new SubThread(countDownLatch);
		Thread thread1 = new Thread(subThread, "�߳�A");
		Thread thread2 = new Thread(subThread, "�߳�B");
		
		System.out.println("���߳̿�ʼִ��");
		thread1.start();
		thread2.start();
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("���߳�ִ����ϡ�����");
	}
}
