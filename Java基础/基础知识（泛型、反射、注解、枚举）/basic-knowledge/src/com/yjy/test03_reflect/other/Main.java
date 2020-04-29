package com.yjy.test03_reflect.other;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * ��ȡ�༰��������˽�����ԣ�<br>
 * ��һЩ�����ֶ�ȫ��д��һ���������ҵ����ֻ��̳ж�Ӧ�Ĺ����༴��������Щ�����ֶΡ�<br>
 * ��User��̳���AudutEntity�࣬�������ˡ������ˡ�����ʱ�䡢����޸��ˡ�����޸�ʱ�䡢�汾�š�id����Щ�����ֶΡ�
 */
public class Main {

	public static void main(String[] args) {
		User user = new User();
		user.setUsername("username!!");
		user.setPassword("password!!");
		user.setBirthday(Date.valueOf("2019-12-02"));

		user.setCreateUser("createUser!!");
		user.setLastUpdateUser("lastUpdateUser!!");
		user.setCreateTime(Timestamp.valueOf("2019-12-03 08:23:26"));
		user.setLastUpdateTime(Timestamp.valueOf("2019-12-03 08:23:26"));

		user.setVersionNumber(2);
		user.setId(UUID.randomUUID().toString());
		getAllField(user);
	}

	private static void getAllField(Object obj) {
		Class<?> clazz = obj.getClass();
		try {
			while (clazz != Object.class) {
				System.out.println("������:" + clazz);

				Field[] declaredFields = clazz.getDeclaredFields();
				for (Field field : declaredFields) {
					int mod = field.getModifiers();
					if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
						clazz = clazz.getSuperclass();
						continue;
					}
					field.setAccessible(true);
					System.out.println("�ֶ���" + field.getName() + "=" + field.get(obj));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
