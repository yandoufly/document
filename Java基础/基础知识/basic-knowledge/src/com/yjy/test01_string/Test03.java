package com.yjy.test01_string;

public class Test03 {
	static {
		int x = 5;
	}

	static int x, y;

	public static void main(String[] args) {
		x--;
		myMethod();
		System.out.println(x + y + ++x);
	}

	public static void myMethod() {
		y = x++ + ++x;
	}
}
// ������Ϊ3
// ������
// 1����̬�����xΪ�ֲ���������Ӱ�쾲̬����x��ֵ
// 2��x��yΪ��̬������Ĭ�ϳ�ʼֵΪ0�����ڵ�ǰ�࣬��ֵ�ı��Ӱ�������������
// main�����У�
// 1��ִ�� x-- ��x = -1
// 2��ִ�� myMethod() ��x = 1�� y = (-1 + 1) = 0
// 3��ִ�� x + y + ++x ����ִ�� x+y���Ϊ1����ִ��++x���Ϊ2�����ս��Ϊ3