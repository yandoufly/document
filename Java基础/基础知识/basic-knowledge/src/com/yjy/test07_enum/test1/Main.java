package com.yjy.test07_enum.test1;

import java.util.EnumSet;

public class Main {

	public static void main(String[] args) {
		System.out.println("value=" + WeekEnum.FRI.getValue()); // �����value=0

		// ���� EnumSet��ʹ��
		EnumSet<WeekEnum> weekSet = EnumSet.allOf(WeekEnum.class);
		for (WeekEnum dayTest : weekSet) {
			System.out.println(dayTest.name() + "��value=" + dayTest.getValue());
		}
	}
}
