package com.yjy.test07_producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ģ�������ߺ������߳�����ArrayBlockingQueueʵ��
 */
public class ProducerAndConsumer2 {

	// �����������д�С
	private static final int maxSize = 5;

	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(maxSize);
		new Thread(new Productor(queue)).start();
		new Thread(new Customer(queue)).start();
	}

	static class Productor implements Runnable {
		private BlockingQueue<Integer> queue;
		private int count = 1;

		Productor(BlockingQueue<Integer> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					queue.put(count);
					System.out.println("����������������" + count + "����Ʒ");
					count++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class Customer implements Runnable {
		private BlockingQueue<Integer> queue;

		Customer(BlockingQueue<Integer> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					int count = (int) queue.take();
					System.out.println("customer�������ѵ�" + count + "����Ʒ===");
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
