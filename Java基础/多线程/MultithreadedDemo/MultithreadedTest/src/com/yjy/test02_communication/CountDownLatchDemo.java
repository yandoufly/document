package com.yjy.test02_communication;

import java.util.concurrent.CountDownLatch;

/**
 * ����ѵ���˶�Ա��������Ҫ�ȴ������˶�Ա��λ�ſ�ʼ
 */
public class CountDownLatchDemo {
	
	/**
	 * �˶�Ա���������˶�Ա�̵߳���
	 */
	static class Racer extends Thread {
		private CountDownLatch countDownLatch;
		public Racer(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			System.out.println(name + "����׼��...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + "׼����ϣ�countDownLatch��1...");
			countDownLatch.countDown();
		}
	}
	
	/**
	 * �������������˶�Ա�̵߳���
	 */
	static class Coach extends Thread {
		
		private CountDownLatch countDownLatch;
		public Coach(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}
		
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			System.out.println(name + "�ȴ������˶�Ա׼��...");
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("�����˶�Ա�Ѿ�����" + name + "��ʼѵ����");
		}
	}
	
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(3); //���õȴ����˶�Ա3��
		Racer racer1 = new Racer(countDownLatch);
		racer1.setName("�˶�Ա1");
		racer1.start();
		Racer racer2 = new Racer(countDownLatch);
		racer2.setName("�˶�Ա1");
		racer2.start();
		Racer racer3 = new Racer(countDownLatch);
		racer3.setName("�˶�Ա1");
		racer3.start();

		Coach coach = new Coach(countDownLatch);
		coach.setName("����");
		coach.start();
	}
	
}
