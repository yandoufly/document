package com.yjy.test08_dead_lock;

public class DeadLockDemo {
	private static Object lockA = new Object(); // ����A
	private static Object lockB = new Object(); // ����B

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() { // �߳�1
			public void run() {
				System.out.println(Thread.currentThread().getName() + "��ʼִ��");
				synchronized (lockA) { // ������A����
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (lockB) { // �ٸ�����B����
						System.out.println(Thread.currentThread().getName() + "ִ�����...");
					}
				}
			}
		}, "�߳�1");

		Thread t2 = new Thread(new Runnable() { // �߳�2
			public void run() {
				System.out.println(Thread.currentThread().getName() + "��ʼִ��");
				synchronized (lockB) { // ������B����
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (lockA) { // �ٸ�����A����
						System.out.println(Thread.currentThread().getName() + "ִ�����...");
					}
				}
			}
		}, "�߳�2");
		t1.start();
		t2.start();
	}

}
