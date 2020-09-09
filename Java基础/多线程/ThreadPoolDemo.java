package com.yjy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
	public static void main(String[] args) {
//		// newCachedThreadPool:����100���߳�ִ��100��������������ִ��ʱ��ϳ��Ǹ����̳߳�ֻ�ܴ����µ��߳�ȥִ�У�����sleepȥ�����ῴ���߳��ܹ������ã�
//		test1();
//
//		// newFixedThreadPool:�����̶�10���߳�ȥִ��100������
//		test2();
		
//		// newSingleThreadExecutor������һ���߳�ȥִ��100������
//		test3();

		

//		// ThreadPoolExecutor���Զ����ȳ˳�������31������ʱ�����쳣��10�������߳�+10���Ǻ����߳���+10������Ŷ��У�
//		test4();
		
//		// ʹ�ö��߳�ִ�������̳߳ص��߳����������ܾ����ԣ��ɵ����̴߳�������� ��
//		test5();

	}

	// ����100���߳�ִ��100��������������ִ��ʱ��ϳ��Ǹ����̳߳�ֻ�ܴ����µ��߳�ȥִ�У�����sleepȥ�����ῴ���߳��ܹ������ã�
	public static void test1() {
		ExecutorService e1 = Executors.newCachedThreadPool();
		for (int i = 1; i <= 100; i++) {
			e1.execute(new MyTask(i));
		}
	}

	// �����̶�10���߳�ȥִ��100������
	public static void test2() {
		ExecutorService e2 = Executors.newFixedThreadPool(10);
		for (int i = 1; i <= 100; i++) {
			e2.execute(new MyTask(i));
		}
	}

	// �����̶�10���߳�ȥִ��100������
	public static void test3() {
		ExecutorService e3 = Executors.newSingleThreadExecutor();
		for (int i = 1; i <= 100; i++) {
			e3.execute(new MyTask(i));
		}
	}
	
	// ����31������ʱ�����쳣��10�������߳�+10������߳���+10������Ŷ��У�
	public static void test4() {
		ThreadPoolExecutor t = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
		for (int i = 1; i <= 100; i++) {
			t.execute(new MyTask(i));
		}
	}
	
	// ʹ�ö��߳�ִ�������̳߳ص��߳����������ܾ����ԣ��ɵ����̴߳�������� ��
	public static void test5() {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10,10,0, TimeUnit.SECONDS,new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
		for (int i = 0; i < 1000; i++) {
			final int ii = i;
			threadPool.execute(() -> {
				System.out.println(Thread.currentThread().getName() + " :::: " + ii);
			});
		}
	}

	static class MyTask implements Runnable {
		int i;

		public MyTask(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "------" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
