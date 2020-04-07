package com.yjy.test01_create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Callable_FutureTaskDemo2 {

	static class Task implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			System.out.println("���߳���ִ������...");
			Thread.sleep(3000);
			return 1000;
		}
	}

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Task task = new Task();
		Future<Integer> future = executorService.submit(task);
		executorService.shutdown();
		System.out.println("���߳���ִ������...");

		Thread.sleep(2000); //ģ����������ʱ��
		System.out.println("task���н����future:" + future.get());
		System.out.println("��������ִ�����");
	}
}
