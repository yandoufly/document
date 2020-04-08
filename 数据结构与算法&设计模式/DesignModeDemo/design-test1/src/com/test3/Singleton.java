package com.test3;

/**
 * ��̬�ڲ���-����ģʽ
 */
public class Singleton {
	private static class SingletonHolder {
		private static final Singleton singleton = new Singleton();
	}
	private Singleton() {}
	public static final Singleton getInstance() {
		return SingletonHolder.singleton;
	}
	
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);
	}
}
