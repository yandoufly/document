package com.yjy.test04_annotation.test1;

import java.lang.annotation.Annotation;

// ��ע�����
@MyAnnotation(value="����ע��")
public class Main {

	public static void main(String[] args) {
		Annotation[] annotations = Main.class.getAnnotations(); //��ʽ1����ȡ����ע��
		for (Annotation annotation : annotations) {
			if (annotation instanceof MyAnnotation) {
				MyAnnotation myAnnotation = (MyAnnotation) annotation;
				System.out.println(myAnnotation.value());
			}
		}
		
		MyAnnotation annotation = Main.class.getAnnotation(MyAnnotation.class); //��ʽ2����ȡ����ע��
		System.out.println(annotation.value());
	}
}
