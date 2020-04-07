package com.yjy.test02_communication;

import java.util.concurrent.Semaphore;

/**
 * ģ��8������ʹ��3̨����������1̨����ͬһʱ��ֻ��1�˲���
 */
public class SemaphoreDemo {

	static class Worker implements Runnable {
		private Semaphore semaphore = new Semaphore(3);
		
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			try {
				semaphore.acquire();
				System.out.println(name + "��ȡ����������ʼ����...");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println(name + "ʹ����ϣ��ͷŻ���");
				semaphore.release();
			}
		}
	}
	
	public static void main(String[] args) {
		int workers = 8; //��������
		Worker worker = new Worker();
		for (int i = 0; i < workers; i++) {
			new Thread(worker, "����" + i).start();
		}
	}
}
