package com.yjy.test03_reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

// ��������
public class Test2 {
	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("com.yjy.test03_reflect.Student"); //��ȡ��
		Student student = (Student) clazz.newInstance(); //ʵ��������
		
		Field stuNoField = clazz.getDeclaredField("stuNo"); //��ȡ�ض��ֶ�
		stuNoField.setAccessible(true);
		stuNoField.set(student, 23);
		
		Field[] fields = clazz.getFields(); //��ȡ�����༰���ࡱ���й����ֶ�
		for (Field field : fields) {
			System.out.println("�������ƣ�" + field.getName());
			System.out.println("���η���" + Modifier.toString(field.getModifiers()));
			System.out.println("�������ͣ�" + field.getType());
		}
		
		Field[] fields2 = clazz.getDeclaredFields(); //��ȡ���������ֶ�
		for (Field field : fields2) {
			System.out.println("�������ƣ�" + field.getName());
			System.out.println("���η���" + Modifier.toString(field.getModifiers()));
			System.out.println("�������ͣ�" + field.getType());
		}
	}
}