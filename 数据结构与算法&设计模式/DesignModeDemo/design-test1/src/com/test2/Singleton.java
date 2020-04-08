package com.test2;

/**
 * ˫�ؼ��-����ģʽ
 */
public class Singleton {
	private volatile static Singleton singleton; //ע��volatile
	private Singleton() {}
	
	public static final Singleton getInstance() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
	
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);
	}
}

