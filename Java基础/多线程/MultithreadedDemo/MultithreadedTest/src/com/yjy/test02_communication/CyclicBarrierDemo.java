package com.yjy.test02_communication;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	private CyclicBarrier cyclicBarrier = new CyclicBarrier(3); // ����CyclicBarrier���߳���Ϊ3

	public void startThread() {
		// 1.��ӡ�߳�׼������
		String name = Thread.currentThread().getName();
		System.out.println(name + "����׼��...");
		// 2.����CyclicBarrier��await�����ȴ��߳�ȫ��׼�����
		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		// 3.��ӡ�߳����������Ϣ
		System.out.println(name + "�Ѿ�������ϣ�time��" + System.currentTimeMillis());
	}

	public static void main(String[] args) {
		CyclicBarrierDemo demo = new CyclicBarrierDemo();
		new Thread(new Runnable() {
			public void run() {
				demo.startThread();
			}
		}, "�߳�1").start();
		new Thread(new Runnable() {
			public void run() {
				demo.startThread();
			}
		}, "�߳�2").start();
		new Thread(new Runnable() {
			public void run() {
				demo.startThread();
			}
		}, "�߳�3").start();
	}

}
