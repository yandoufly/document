package com.yjy.test01_string;

public class Test02 {
	public static void main(String[] args) {
		final String str1 = "hello";
		final String str2 = "java";
		String str3 = "hellojava";
		String str4 = str1 + str2;
		String str5 = str1 + "java";
		System.out.println(str3 == str4); // true ������str1��str2���Ǳ�final���ε��ַ�����������ôstr4�ڱ����ھͿ��Ա�ȷ��
		System.out.println(str3 == str5); // true
	}
}
