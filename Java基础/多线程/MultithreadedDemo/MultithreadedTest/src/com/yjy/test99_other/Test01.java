package com.yjy.test99_other;
/*
 * �����
	Thread 2 sent notify.
	Thread 1 wake up.
	������t1ִ��obj.wait();ʱ���ͷ�����������t2���Ի����������t1û��ȡ����˲�������ִ�У���t2ִ�����ͷ�����t1���л�������ִ�С�
 */
public class Test01 {
	public static void main(String[] args) throws Exception {
		final Object obj = new Object();
		Thread t1 = new Thread() {
			public void run() {
				synchronized (obj) {
					try {
						obj.wait();
						System.out.println("Thread 1 wake up.");
					} catch (InterruptedException e) {
					}
				}
			}
		};
		t1.start();
		
		Thread.sleep(1000);// �ȴ�1��
		
		Thread t2 = new Thread() {
			public void run() {
				synchronized (obj) {
					obj.notifyAll();
					System.out.println("Thread 2 sent notify.");
				}
			}
		};
		t2.start();
	}
}

