package com.yjy.test07_enum.test1;

public enum WeekEnum {
	MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(0);

	private int value;

	// ��������δ���ʵ���ϵ�����7��Enum(String name, int ordinal)
	private WeekEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
