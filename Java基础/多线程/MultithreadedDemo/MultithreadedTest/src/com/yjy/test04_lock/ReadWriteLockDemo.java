package com.yjy.test04_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
	private Map<String, String> map = new HashMap<>(); //������map����
	
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock(); //��������
	private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock(); //д������
	
	public String get(String key) {
		readLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "�������Ѽ���...");
			Thread.sleep(3000);
			return map.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + "�������ѽ���...");
			readLock.unlock();
		}
		return null;
	}
	
	public void put(String key, String value) {
		writeLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "д�����Ѽ���...");
			Thread.sleep(3000);
			map.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + "д�����ѽ���...");
			writeLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ReadWriteLockDemo demo = new ReadWriteLockDemo();
		demo.put("key1", "value1");
		new Thread("���߳�1") {
			public void run() {
				System.out.println("key1=" + demo.get("key1"));
			}
		}.start();
		new Thread("���߳�2") {
			public void run() {
				System.out.println("key1=" + demo.get("key1"));
			}
		}.start();
		
		new Thread("д�߳�1") {
			public void run() {
				demo.put("key1", "д�߳�1");
			}
		}.start();
		new Thread("д�߳�2") {
			public void run() {
				demo.put("key1", "д�߳�2");
			}
		}.start();
		
		new Thread("���߳�3") {
			public void run() {
				System.out.println("key1=" + demo.get("key1"));
			}
		}.start();
		new Thread("���߳�4") {
			public void run() {
				System.out.println("key1=" + demo.get("key1"));
			}
		}.start();
	}
}
