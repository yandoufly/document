package com.yjy.test01_string;

public class Test04 {

	public static void main(String[] args) {
		StringBuffer a = new StringBuffer("A");
		StringBuffer b = new StringBuffer("B");
		operate(a, b);
		System.out.println(a + "." + b); //
	}

	private static void operate(StringBuffer x, StringBuffer y) {
		x.append(y);
		y = x;
	}
}
// ���Ϊ��AB.B��
// ������
// 1���յ���operate����ʱ������a��xָ�����A��������b��yָ�����B��
// 2��ִ�С�x.append(y)��������xָ��Ķ���A��������B������AҲ�ͱ����AB����ʱ������a������xָ�����AB��������b������yָ�����B��
// 3��ִ�С�y=x��������a��x��yָ�����AB��������bû�з����κθı䣬��Ȼָ�����B��
