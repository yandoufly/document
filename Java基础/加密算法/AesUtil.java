package com.yjy;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/*
 * AES�ԳƼ��ܺͽ���
 */
public class AesUtil {

	private static Encoder encoder = Base64.getEncoder();
	private static Decoder decoder = Base64.getDecoder();

	/*
	 * ����
	 */
	public static String encode(String encodeRules, String content) {
		try {
			// 1.������Կ��������ָ��ΪAES�㷨,�����ִ�Сд
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			// 2.����ecnodeRules�����ʼ����Կ������
			// ����һ��128λ�����Դ,���ݴ�����ֽ�����
			keygen.init(128, new SecureRandom(encodeRules.getBytes()));
			// 3.����ԭʼ�Գ���Կ
			SecretKey original_key = keygen.generateKey();
			// 4.���ԭʼ�Գ���Կ���ֽ�����
			byte[] raw = original_key.getEncoded();
			// 5.�����ֽ���������AES��Կ
			SecretKey key = new SecretKeySpec(raw, "AES");
			// 6.����ָ���㷨AES�Գ�������
			Cipher cipher = Cipher.getInstance("AES");
			// 7.��ʼ������������һ������Ϊ����(Encrypt_mode)���߽��ܽ���(Decrypt_mode)�������ڶ�������Ϊʹ�õ�KEY
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// 8.�����ܺ������ת��Ϊ�ַ���(����Ҫ����Ϊutf-8����Ȼ��������������ĺ�Ӣ�Ļ�����ľͻ����Ϊ����)
			String AES_encode = new String(encoder.encode(cipher.doFinal(content.getBytes("utf-8"))));
			return AES_encode;
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ����д�ͷ���nulll
		return null;
	}

	/*
	 * ����
	 */
	public static String decode(String encodeRules, String content) {
		try {
			// 1.������Կ��������ָ��ΪAES�㷨,�����ִ�Сд
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			// 2.����ecnodeRules�����ʼ����Կ������
			// ����һ��128λ�����Դ,���ݴ�����ֽ�����
			keygen.init(128, new SecureRandom(encodeRules.getBytes()));
			// 3.����ԭʼ�Գ���Կ
			SecretKey original_key = keygen.generateKey();
			// 4.���ԭʼ�Գ���Կ���ֽ�����
			byte[] raw = original_key.getEncoded();
			// 5.�����ֽ���������AES��Կ
			SecretKey key = new SecretKeySpec(raw, "AES");
			// 6.����ָ���㷨AES�Գ�������
			Cipher cipher = Cipher.getInstance("AES");
			// 7.��ʼ������������һ������Ϊ����(Encrypt_mode)���߽���(Decrypt_mode)�������ڶ�������Ϊʹ�õ�KEY
			cipher.init(Cipher.DECRYPT_MODE, key);
			// 8.�����ܲ����������ݽ�����ֽ�����
			String AES_decode = new String(cipher.doFinal(decoder.decode(content)), "utf-8");
			return AES_decode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String encodeRules = "my_rule"; // ���ܹ���
		String content = "123"; // Ҫ���ܵ�����
		String aesEncode = AesUtil.encode(encodeRules, content); // ����
		String aesDncode = AesUtil.decode(encodeRules, aesEncode); // ����

		System.out.println("���ܺ��������:" + aesEncode);
		System.out.println("���ܺ��������:" + aesDncode);
	}

}