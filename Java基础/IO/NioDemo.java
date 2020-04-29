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

	// 使用NIO的WatchService监控文件系统变化
	private static void testWatchService() throws IOException, InterruptedException {
		// 获取到文件系统的WatchService对象
		WatchService watchService = FileSystems.getDefault().newWatchService();

		Paths.get("D:\\TestTest").register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY,
				StandardWatchEventKinds.OVERFLOW);
		// 通过wachService来监听文件系统
		while (true) {
			WatchKey key = watchService.take();
			List<WatchEvent<?>> pollEvents = key.pollEvents();
			for (WatchEvent<?> watchEvent : pollEvents) {
				System.out.println(watchEvent.context() + "发生了" + watchEvent.kind() + "事件");
			}

			boolean reset = key.reset();
			if (!reset) {
				break;
			}
		}
	}

	// 利用NIO来遍历文件，基于事件驱动的方式遍历文件
	private static void testFileVisitor() throws IOException {
		FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) throws IOException {
				System.out.println("正准备访问" + path + "文件");
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				System.out.println("正在访问" + path + "文件");
				if (path.endsWith("nio2.txt")) {
					System.out.println("恭喜你找到了我想要的Java文件,你可以停止查找了");
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

	// 测试Channel通道
	private static void testChannel() throws IOException {
		File srcFile = new File("D:\\nio.txt");
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(new File("D:\\nio2.txt"));

		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(10);

		// 将inChannel中的数据读取到Buffer中
		int len = inChannel.read(buffer);
		System.out.println(len);
		byte[] bys = buffer.array();
		System.out.println(new String(bys, 0, len));
		// // 切换成读模式
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
		// buffer.flip(); // 为取出数据做好准备
		// outChannel.write(buffer);
		// buffer.clear();
		// }

		// 3.Channel能够将指定的部分或者全部文件映射到内存中
		// java.nio.channels.NonWritableChannelException
		// java.nio.channels.NonReadableChannelException
		// MappedByteBuffer mapBuffer = inChannel.map(MapMode.READ_WRITE, 0,
		// srcFile.length());
		// byte[] array = mapBuffer.array();
		// System.out.println(new String(array));
	}

	// 测试Buffer
	private static void testBuffer() {
		// 1.分配一个指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		System.out.println("-------------------allocate()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());

		// 2.写数据：将数据存入缓冲区中
		buf.put("abcd".getBytes());
		System.out.println("-------------------put()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());

		// 3.切换为读数据
		buf.flip();
		System.out.println("-------------------flip()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());

		// 4.读数据：读取缓冲区的数据
		byte[] dst = new byte[buf.limit()];
		buf.get(dst);
		System.out.println(new String(dst, 0, dst.length));
		System.out.println("-------------------get()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());

		// 5.rewind():可重复读
		buf.rewind();
		System.out.println("-------------------rewind()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());

		// 6.clear()：清空缓冲区，但缓冲区中的数据仍然存在
		buf.clear();
		System.out.println("-------------------clear()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
	}
}
