package com.yjy.test02_communication;

/**
 * ʹ��Object��wait��notifyʵ���̼߳�ͨ�Ż���
 */
public class Synchronized_Object_Demo2 {
	private int i = 1;
	private Object obj = new Object();

	/**
	 * ������ӡ
	 */
	public void odd() {
		synchronized (obj) {
			while (i <= 10) {
				if (i % 2 == 1) {
					System.out.println("����" + i);
					i++;
					obj.notify(); // ����ż���̴߳�ӡ
				} else {
					try {
						obj.wait(); // �ȴ�ż���̴߳�ӡ
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * ż����ӡ
	 */
	public void even() {
		synchronized (obj) {
			while (i <= 10) {
				if (i % 2 == 0) {
					System.out.println("ż��" + i);
					i++;
					obj.notify(); // ���������̴߳�ӡ
				} else {
					try {
						obj.wait(); // �ȴ������̴߳�ӡ
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Synchronized_Object_Demo2 demo = new Synchronized_Object_Demo2();
		// ���������̴߳�ӡ
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo.odd();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo.even();
			}
		}).start();
	}
}
