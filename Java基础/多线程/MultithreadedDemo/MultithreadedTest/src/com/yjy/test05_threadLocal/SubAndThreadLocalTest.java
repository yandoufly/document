package com.yjy.test05_threadLocal;

/**
 * ���ԣ��߳��ڴ������̣߳����߳�����õ����̵߳�˽������
 */
public class SubAndThreadLocalTest {
	/**
	 * �����
	 * 	���߳�Thread-0:���߳�˽�б���
	 * 	���߳�Thread-1:null
	 * ���������
	 * 	���̲߳�����������̵߳ľֲ�����
	 */
	public static void main(String[] args) {
		ThreadLocal<String> t1 = new ThreadLocal<String>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t1.set("���߳�˽�б���");
				System.out.println("���߳�" + Thread.currentThread().getName() + ":" + t1.get());
				new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println("���߳�" + Thread.currentThread().getName() + ":" + t1.get());
					}
					
				}).start();
			}
		}).start();

	}
}
