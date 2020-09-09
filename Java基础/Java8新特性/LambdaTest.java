package com.yjy.lambda;

public class LambdaTest {
	
	public static void main(String[] args) {
		// ���޲�������
		MathOperation addition = (int a, int b) -> a + b;
		MathOperation subtraction = (a, b) -> a - b;
		
		// �л����ţ���return�ؼ���
		MathOperation multiplication = (a, b) -> {return a * b;};
		// �޻����ţ���return�ؼ���
		MathOperation division = (a, b) -> a / b;
		
		System.out.println(operate(10, 5, addition));
		System.out.println(operate(10, 5, subtraction));
		System.out.println(operate(10, 5, multiplication));
		System.out.println(operate(10, 5, division));
		
		// �����ţ������������
		Greet greet = message -> System.out.println("saying::" + message);
		greet.sayMessage("hello");
		greet.sayMessage("hello2");
	}

	private static int operate(int i, int j, MathOperation mathOperation) {
		return mathOperation.operation(i, j);
	}

	interface MathOperation {
		int operation(int a, int b);
	}
	interface Greet {
		void sayMessage(String message);
	}
}
