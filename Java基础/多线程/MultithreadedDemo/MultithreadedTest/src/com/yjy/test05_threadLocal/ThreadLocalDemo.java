package com.yjy.test05_threadLocal;

import java.util.concurrent.TimeUnit;

/**
 * ͳ��ĳ�������ĺ�ʱʱ��
 */
public class ThreadLocalDemo {

	// static final���ͣ���֤�ñ�������ָ����������
	private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>();

	public static final void start() {
		TIME_THREADLOCAL.set(System.currentTimeMillis());
	}

	public static final long end() {
		return System.currentTimeMillis() - TIME_THREADLOCAL.get();
	}

	public static void main(String[] args) throws Exception {
		// AOP����
		ThreadLocalDemo.start(); // ִ�з���ǰ
		TimeUnit.SECONDS.sleep(2); // ģ��ִ�з���
		System.out.println(ThreadLocalDemo.end()); // ִ�з�����
	}

}
