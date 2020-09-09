package com.yjy;

import java.util.Comparator;
import java.util.function.Function;

public class FunctionTest {

	public static void main(String[] args) {
		// Function�Ǹ������࣬T�������������R�����ؽ����
		// Ӧ�ó���������ͨ�����벻ͬ��Function��ʵ����ͬһ����ʵ�ֲ�ͬ�Ĳ�����
		// ����Ŀ���и������û����ܣ����û���VIP����ͨ�û����������ֲ�ͬ�������߼�����ô��ʱ���ǾͿ�����д���ֲ�ͬ���߼�������֮�⣬���������߼������ݷ��뿪�������ǿ���ʵ���߼��ĸ��á�
		Function<Integer, Integer> A = x -> x + 1;
		Function<Integer, Integer> B = i -> i * i;
		System.out.println(A.apply(3)); // 4
		System.out.println(calculate(B, 5)); // 25
		
		Function<String, Integer> s = Integer::parseInt;
		Integer integer = s.apply("10");
		System.out.println(integer);
		
		Comparator<Integer> comparator = Integer::compare;
		System.out.println(comparator.compare(100,10)); // ����(1)
		System.out.println(comparator.compare(10,10)); // ����(0)
		System.out.println(comparator.compare(10,100)); // С��(-1)
		
		// ���ӵ��ã���������F1,F2����Ҫ�������߼�AB������F1��ҪA->B��F2������ҪB->A
		System.out.println("F1:"+B.apply(A.apply(5))); // 36
		System.out.println("F2:"+A.apply(B.apply(5))); // 26
		// �Ż���ʹ��compose��andThen
		System.out.println("F1:"+B.compose(A).apply(5)); // 36
		System.out.println("F2:"+B.andThen(A).apply(5)); // 26

	}
	
	public static Integer calculate(Function<Integer, Integer> fun, Integer num) {
		return fun.apply(num);
	}
}