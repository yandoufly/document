package com.yjy.io;

import java.nio.ByteBuffer;

public class BufferTest {
	public static void main(String[] args) {
		// 1.����һ��ָ����С�Ļ�����
		ByteBuffer buf = ByteBuffer.allocate(1024);
		System.out.println("-------------------allocate()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
		
		// 2.д���ݣ������ݴ��뻺������
		buf.put("abcd".getBytes());
		System.out.println("-------------------put()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
		
		// 3.�л�Ϊ������
		buf.flip();
		System.out.println("-------------------flip()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
		
		// 4.�����ݣ���ȡ������������
		byte[] dst = new byte[buf.limit()];
		buf.get(dst);
		System.out.println(new String(dst, 0, dst.length));
		System.out.println("-------------------get()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
		
		// 5.rewind():���ظ���
		buf.rewind();
		System.out.println("-------------------rewind()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
		
		// 6.clear()����ջ����������������е�������Ȼ����
		buf.clear();
		System.out.println("-------------------clear()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
	}
}
