package com.yjy.test05_threadLocal;

public class ThreadLocalDemo2 {

	// 1.�������ж���Ǯ��ȡ����
	static class Bank {
		private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
			protected Integer initialValue() {
				return 0;
			}
		};

		public Integer get() {
			return threadLocal.get();
		}

		public void set(Integer value) {
			threadLocal.set(threadLocal.get() + value);
		}
	}

	// 2.����ת�˶��󣻴�������ȡǮ��ת�ˣ����浽�˻�
	static class Transfer implements Runnable {
		private Bank bank;

		public Transfer(Bank bank) {
			this.bank = bank;
		}

		public void run() {
			for (int i = 0; i < 10; i++) {
				bank.set(10);
				System.out.println(Thread.currentThread().getName() + "�˻���" + bank.get());
			}
		}
	}

	// 3.��main������ʹ����������ģ��ת��
	public static void main(String[] args) {
		Bank bank = new Bank();
		Transfer transfer = new Transfer(bank);
		Thread t1 = new Thread(transfer);
		Thread t2 = new Thread(transfer);
		t1.start();
		t2.start();
	}
}
