package com.yjy.test02_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

/**
 * 一、使用NIO完成网路通信的三个核心：
 * 1.通道（Channel）：负责连接
 * 		java.nio.channels.Channel接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 * 
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 * 
 * 2.缓冲区（Buffer）：负责数据的存取
 * 
 * 3.选择器（Selector）：是SelectableChannel的多路复用器。用于监控SelectableChannel的IO状况
 *
 */
// 测试SocketChannel：先开启一个server，再开启两个client
public class Test04SocketChannel {
	
	
	/** 客户端 */
	@Test
	public void client() throws IOException {
		// 1.获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
		
		// 2.切换为非阻塞模式
		sChannel.configureBlocking(false);
	
		// 3.分配指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		// 4.发送数据到服务端
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			String str = scan.next();
			buf.put((LocalDateTime.now() + " >> " + str).getBytes());
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}
		
		// 5.关闭通道
		sChannel.close();
		scan.close();
	}
	
	/** 服务端 */
	@Test
	public void server() throws IOException {
		// 1.获取通道
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		
		// 2.切换为非阻塞模式
		ssChannel.configureBlocking(false);
		
		// 3.绑定连接
		ssChannel.bind(new InetSocketAddress(8080));
		
		// 4.获取选择器
		Selector selector = Selector.open();
		
		// 5.将通道注册到选择器上，并且指定“监听接收事件”
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		// 6.轮询获取选择器上已经“准备就绪”的事件
		while (selector.select() > 0) {
			// 7.获取当前选择器中所有注册的“选择键（已就绪的监听事件）”
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			
			while (iterator.hasNext()) {
				// 8.获取准备“就绪”的事件
				SelectionKey sk = iterator.next();
				
				// 9.判断具体是什么事件准备就绪
				if (sk.isAcceptable()) {
					// 10.若为“接收就绪”，获取客户端连接
					SocketChannel sChannel = ssChannel.accept();
					
					// 11.切换为非阻塞模式
					sChannel.configureBlocking(false);
					
					// 12.将该通道注册到选择器上，并指定为“监听读取事件”
					sChannel.register(selector, SelectionKey.OP_READ);
				} else if (sk.isReadable()) {
					// 13.获取当前选择器上“读就绪”状态的通道
					SocketChannel sChannle = (SocketChannel) sk.channel();
					
					// 14.读取数据
					ByteBuffer buf = ByteBuffer.allocate(1024);
					int len = 0;
					while ((len = sChannle.read(buf)) > 0) {
						buf.flip();
						System.out.println("服务端接收数据：" + new String(buf.array(), 0, len));
						buf.clear();
					}
				}
				
				// 15.取消选择键SelectionKey
				iterator.remove();
			}
		}
	}
}
