package com.yjy.test03_reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

// ���Է����ȡ��
public class Test1 {
	public static void main(String[] args) {
		Class<?> clazz = Student.class; //��ȡ����
		System.out.println(clazz); 
		System.out.println(clazz.getSuperclass()); //��ȡ�������Ͳ����ĸ��ࣨclass com.yjy.test03_reflect.Student��
		
		Type type = clazz.getGenericSuperclass();
		System.out.println(type);//��ȡ���ࣨclass com.yjy.test03_reflect.Person��
		ParameterizedType p = (ParameterizedType) type;
		Class<?> c = (Class<?>)p.getActualTypeArguments()[0]; //class java.lang.String
		System.out.println(c.getName()); //java.lang.String
	}
}