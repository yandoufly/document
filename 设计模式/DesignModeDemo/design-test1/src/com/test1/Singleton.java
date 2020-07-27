package com.test1;

/**
 * ����ʽ-����ģʽ
 */
public class Singleton {
	private static Singleton singleton = new Singleton();
	private Singleton() {}
	
	public static Singleton getInstance() {
		return singleton;
	}
	
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);
	}
}
