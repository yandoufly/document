package com.yjy.test06_wait_parent_thread;

/**
 * ʹ��join����
 */
public class JoinThreadDemo {
	
	static class JoinRunnableTest implements Runnable {
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
		}
	}

	public static void main(String[] args) {
		JoinRunnableTest test = new JoinRunnableTest();
		Thread threadA = new Thread(test, "�߳�A");
		Thread threadB = new Thread(test, "�߳�B");
		threadA.start();
		threadB.start();
		
		System.out.println("���߳̿�ʼִ��");
		try {
			threadA.join();
			threadB.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("���߳�ִ����ϡ�����");
	}
}
