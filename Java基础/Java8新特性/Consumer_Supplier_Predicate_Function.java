package com.yjy;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Java8�����ԣ�Consumer�ӿڡ�Supplier�ӿڡ�Predicate�ӿڡ�Function�ӿ�
public class Consumer_Supplier_Predicate_Function {

	public static void main(String[] args) {

		// ����Consumer�ӿ�
		// Consumer�ӿھ���һ�������͵Ľӿڣ�ͨ�����������Ȼ�����ֵ��������ô��
		testConsumer1();

		// ����Supplier�ӿ�
		// Supplier�ӿ���һ�������͵Ľӿڣ��൱��һ����������������Դ洢���ݣ�Ȼ����Թ���������ʹ�õ���ôһ���ӿ�
		testSupplier1();
		testSupplier2();

		// ����Predicate�ӿ�
		// Predicate�ӿ���ʵ����һ���жϵ����ã�ͨ��test�������ж�
		testPredicate1();
		testPredicate2();

		// ����Function�ӿ�
		// Function�ӿ���һ�������ͽӿڣ�ʵ��apply����������ת��
		// Function�ӿ���һ�����ܽӿڣ�����һ�����þ���ת��������������ת������һ����ʽ�����ݸ�ʽ��
		testFunction1();
	}

	private static void testConsumer1() {
		System.out.println("***********************");
		Consumer<String> consumer = new Consumer<String>() {

			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		};
		Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
		stream.forEach(consumer);
		
		// �򻯴��룺ʹ��lambda���ʽ
		Stream<String> stream1 = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
		Consumer<String> consumer1 = (s) -> System.out.println(s);
		stream1.forEach(consumer1);
		
		// �򻯴��룺ʹ�÷�������
		Stream<String> stream2 = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
		Consumer<String> consumer2 = System.out::println;
		stream2.forEach(consumer2);
	}

	private static void testSupplier1() {
		System.out.println("***********************");
		Supplier<Integer> supplier = new Supplier<Integer>() {
			@Override
			public Integer get() {
				return new Random().nextInt(100);
			}
		};
		System.out.println(supplier.get());

		// �򻯴��룺ʹ��lambda���ʽ
		Supplier<Integer> supplier2 = () -> new Random().nextInt(100);
		System.out.println(supplier2.get());

		// �򻯴��룺ʹ�÷�������
		Supplier<Double> supplier3 = Math::random;
		System.out.println(supplier3.get());
	}

	private static void testSupplier2() {
		System.out.println("***********************");
		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
		Optional<Integer> first = stream.filter(i -> i > 6).findFirst();
		// orElse�����first�д��������ͷ������������������ڣ��ͷŻش������
		System.out.println(first.orElse(5));

		Supplier<Integer> supplier = new Supplier<Integer>() {
			@Override
			public Integer get() {
				// ����һ�����ֵ
				return new Random().nextInt();
			}
		};
		// orElseGet�����first�д��������ͷ������������������ڣ��ͷ���supplier���ص�ֵ
		System.out.println(first.orElseGet(supplier));
	}

	private static void testPredicate1() {
		System.out.println("***********************");
		// ʹ��Predicate�ӿ�ʵ�ַ���,ֻ��һ��test����������һ������������һ��booleanֵ
		Predicate<Integer> predicate = new Predicate<Integer>() {

			@Override
			public boolean test(Integer t) {
				return t > 5 ? true : false;
			}
		};
		System.out.println(predicate.test(4));
		System.out.println(predicate.test(6));

		// �򻯴���
		Predicate<Integer> predicate2 = (t) -> t > 5 ? true : false;
		System.out.println(predicate2.test(4));
		System.out.println(predicate2.test(6));
	}

	private static void testPredicate2() {
		Predicate<Integer> predicate = (t) -> t > 5 ? true : false;
		Stream<Integer> stream = Stream.of(1, 23, 3, 4, 5, 56, 6, 6);
		List<Integer> list = stream.filter(predicate).collect(Collectors.toList());
		list.forEach(System.out::println);
		System.out.println();
	}

	private static void testFunction1() {
		System.out.println("***********************");

		// ʹ��map���������͵ĵ�һ��������ת��ǰ�����ͣ��ڶ�����ת���������
		// Function�ӿڶ���ʵ����apply�������÷�����һ�����������һ���������
		Function<String, Integer> function = new Function<String, Integer>() {

			@Override
			public Integer apply(String s) {
				return s.length(); // ����ÿ���ַ����ĳ���
			}
		};
		Stream<String> strStream = Stream.of("aaa", "bb", "cccc");
		Stream<Integer> intStream = strStream.map(function);
		intStream.forEach(System.out::println);

		// �򻯴���
		Function<String, Integer> function2 = (s) -> s.length();
		Stream<String> strStream2 = Stream.of("aaa", "bb", "cccc");
		Stream<Integer> intStream2 = strStream2.map(function2);
		intStream2.forEach(System.out::println);
	}

}