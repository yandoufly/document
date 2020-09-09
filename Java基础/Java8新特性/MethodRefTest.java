package com.yjy;

// ����jdk1.8�����ԣ���������
public class MethodRefTest {

	public static void main(String[] args) {
		UserService userService = new UserService();
		System.out.println(userService.getDefaultUsername());
		System.out.println(IUserService.getStaticUsername());
	}
}

interface IUserService {
	default String getDefaultUsername() {
		return "defaultUsername";
	}
	static String getStaticUsername() {
		return "staticUsername";
	}
}

class UserService implements IUserService {
	
}
