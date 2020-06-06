package com.yjy.test07_producer_consumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ģ���������ߺͶ�������ߣ�ArrayBlockingQueueʵ��
 */
public class ProducerAndConsumer {
	
	public static void main(String[] args) {
		BlockingQueue<Integer> messageQueue = new ArrayBlockingQueue<Integer>(10);
		// ģ��һ�������ߺ�����������
		new Thread(new Producer(messageQueue)).start();
		new Thread(new Consumer(messageQueue)).start();
		new Thread(new Consumer(messageQueue)).start();
	}

	static class Producer implements Runnable {
		private BlockingQueue<Integer> messageQueue;
		public Producer(BlockingQueue<Integer> messageQueue) {
			this.messageQueue = messageQueue;
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(500);
					int n = new Random().nextInt(10)+1;
					System.out.println(Thread.currentThread().getName() + "������Ʒ����" + n);
					messageQueue.put(n);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class Consumer implements Runnable {
		private BlockingQueue<Integer> messageQueue;
		public Consumer(BlockingQueue<Integer> messageQueue) {
			this.messageQueue = messageQueue;
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					if (!messageQueue.isEmpty()) {
						Thread.sleep(500);
						Integer take = messageQueue.take();
						System.out.println(Thread.currentThread().getName() + "���Ѳ�Ʒ����" + take);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
