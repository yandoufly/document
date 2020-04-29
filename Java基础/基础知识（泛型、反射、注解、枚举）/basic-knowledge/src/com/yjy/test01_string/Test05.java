package com.yjy.test01_string;

public class Test05 {

	class Super {
		int flag = 1;

		Super() {
			test();
		}

		void test() {
			System.out.println("Super.test() flag=" + flag);
		}
	}

	class Sub extends Super {
		Sub(int i) {
			flag = i;
			System.out.println("Sub.Sub()flag=" + flag);
		}

		void test() {
			System.out.println("Sub.test()flag=" + flag);
		}
	}

	public static void main(String[] args) {
		new Test05().new Sub(5);
	}
}
// �����
// Sub.test()flag=1
// Sub.Sub()flag=5
// ������
// 1�����ȵ��ø��๹�췽������super()
// 2������test()������
// 3������������sub()����д��test()���������Ե�������test()
// 4�����Sub.test() flag=1
// 5������sub���вι��췽��
// 6�����Sub.Sub() flag=5
// �ص㣺Ҫʱ�̼ǵ�������д���෽��������ʱ�����������д֮��ķ���