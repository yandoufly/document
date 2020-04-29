package com.yjy.test03_reflect;

import java.lang.reflect.Method;

// ��������
public class Test3 {
	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("com.yjy.test03_reflect.Student"); //��ȡ��
		Student student = (Student) clazz.newInstance(); //ʵ��������
		
		Method declaredMethod = clazz.getDeclaredMethod("setStuNo", Integer.class);
		declaredMethod.invoke(student, 123);
		
		Method[] methods = clazz.getMethods(); //��ȡ�����༰���ࡱ���й���method
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		
		System.out.println("---�ָ���---");
		Method[] methods2 = clazz.getDeclaredMethods(); //��ȡ��������з�����ע�⸸�������д��ͬһ�ļ��в�̫һ����
		for (Method method : methods2) {
			System.out.println(method.getName());
		}
	}
}