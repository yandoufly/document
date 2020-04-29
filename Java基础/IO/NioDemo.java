package com.yjy.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class NioDemo {
	public static void main(String[] args) throws IOException, InterruptedException {
		 testBuffer();
//		 testChannel();
//		 testFileVisitor();
//		 testWatchService();
	}

	// ʹ��NIO��WatchService����ļ�ϵͳ�仯
	private static void testWatchService() throws IOException, InterruptedException {
		// ��ȡ���ļ�ϵͳ��WatchService����
		WatchService watchService = FileSystems.getDefault().newWatchService();

		Paths.get("D:\\TestTest").register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY,
				StandardWatchEventKinds.OVERFLOW);
		// ͨ��wachService�������ļ�ϵͳ
		while (true) {
			WatchKey key = watchService.take();
			List<WatchEvent<?>> pollEvents = key.pollEvents();
			for (WatchEvent<?> watchEvent : pollEvents) {
				System.out.println(watchEvent.context() + "������" + watchEvent.kind() + "�¼�");
			}

			boolean reset = key.reset();
			if (!reset) {
				break;
			}
		}
	}

	// ����NIO�������ļ��������¼������ķ�ʽ�����ļ�
	private static void testFileVisitor() throws IOException {
		FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) throws IOException {
				System.out.println("��׼������" + path + "�ļ�");
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				System.out.println("���ڷ���" + path + "�ļ�");
				if (path.endsWith("nio2.txt")) {
					System.out.println("��ϲ���ҵ�������Ҫ��Java�ļ�,�����ֹͣ������");
					return FileVisitResult.TERMINATE;
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path path, IOException exc) throws IOException {
				return FileVisitResult.SKIP_SIBLINGS;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path path, IOException exc) throws IOException {
				return FileVisitResult.CONTINUE;
			}
		};
		Files.walkFileTree(Paths.get("D:\\TestTest"), visitor);
	}

	// ����Channelͨ��
	private static void testChannel() throws IOException {
		File srcFile = new File("D:\\nio.txt");
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(new File("D:\\nio2.txt"));

		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(10);

		// ��inChannel�е����ݶ�ȡ��Buffer��
		int len = inChannel.read(buffer);
		System.out.println(len);
		byte[] bys = buffer.array();
		System.out.println(new String(bys, 0, len));
		// // �л��ɶ�ģʽ
		// buffer.flip();
		// len = inChannel.read(buffer);
		// System.out.println(len);
		// System.out.println(new String(buffer.array(), 0, len));
		// buffer.flip();
		//
		// len = inChannel.read(buffer);
		// System.out.println(len);
		// System.out.println(new String(buffer.array(), 0, len));
		// buffer.flip();

		// int len = 0;
		// while ((len = inChannel.read(buffer)) != -1) {
		// buffer.flip();
		// System.out.print(new String(buffer.array(), 0, len));
		// }

		// while ((inChannel.read(buffer)) != -1) {
		// buffer.flip(); // Ϊȡ����������׼��
		// outChannel.write(buffer);
		// buffer.clear();
		// }

		// 3.Channel�ܹ���ָ���Ĳ��ֻ���ȫ���ļ�ӳ�䵽�ڴ���
		// java.nio.channels.NonWritableChannelException
		// java.nio.channels.NonReadableChannelException
		// MappedByteBuffer mapBuffer = inChannel.map(MapMode.READ_WRITE, 0,
		// srcFile.length());
		// byte[] array = mapBuffer.array();
		// System.out.println(new String(array));
	}

	// ����Buffer
	private static void testBuffer() {
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
