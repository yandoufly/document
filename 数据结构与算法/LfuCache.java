package com.yjy;

import java.util.HashMap;
import java.util.Map;

/**
 * LFU(Least frequently used,�����ʹ���㷨)ʵ�֣�˫������+HashMap
 * ˫�������������ݣ����µķ�ǰ�棬������Ϊ�����ݡ��������������δ��-ֱ�ӽ����ݲ���ͷ��������-�����ݲ���ͷ����ȥ�����һ������
 * HashMap����¼�����ÿ���ڵ㡣����Ԫ��ʱ���ȴ�HashMap�л�ȡ�ڵ㣬��-��ֱ�ӽ����ݲ�������ͷ����Ȼ������β�ڵ�ɾ������-�򽫸ýڵ��Ƶ�ͷ���������������
 */
public class LfuCache {

	ListNode head;
	ListNode last;
	int limit = 4;

	Map<String, ListNode> hashMap = new HashMap<>();

	// ˫������
	public static class ListNode {
		String key;// ����洢key����Ԫ����ʱ��ɾ��β�ڵ�ʱ���Կ��ٴ�HashMapɾ����ֵ��
		Integer value;
		ListNode pre = null;
		ListNode next = null;

		ListNode(String key, Integer value) {
			this.key = key;
			this.value = value;
		}
	}

	public void add(String key, Integer val) {
		ListNode existNode = hashMap.get(key);
		if (existNode != null) {
			// ��������ɾ�����Ԫ��
			ListNode pre = existNode.pre;
			ListNode next = existNode.next;
			if (pre != null) {
				pre.next = next;
			}
			if (next != null) {
				next.pre = pre;
			}
			// ����β�ڵ�
			if (last == existNode) {
				last = existNode.pre;
			}
			// �ƶ�����ǰ��
			head.pre = existNode;
			existNode.next = head;
			head = existNode;
			// ����ֵ
			existNode.value = val;
		} else {
			// �ﵽ���ƣ���ɾ��β�ڵ�
			if (hashMap.size() == limit) {
				ListNode deleteNode = last;
				hashMap.remove(deleteNode.key);
				// ������Ϊ��Ҫɾ�������Բ���Ҫÿ��ListNode����key
				last = deleteNode.pre;
				deleteNode.pre = null;
				last.next = null;
			}
			ListNode node = new ListNode(key, val);
			hashMap.put(key, node);
			if (head == null) {
				head = node;
				last = node;
			} else {
				// ����ͷ���
				node.next = head;
				head.pre = node;
				head = node;
			}
		}

	}

	public ListNode get(String key) {
		return hashMap.get(key);
	}

	public void remove(String key) {
		ListNode deleteNode = hashMap.get(key);
		ListNode preNode = deleteNode.pre;
		ListNode nextNode = deleteNode.next;
		if (preNode != null) {
			preNode.next = nextNode;
		}
		if (nextNode != null) {
			nextNode.pre = preNode;
		}
		if (head == deleteNode) {
			head = nextNode;
		}
		if (last == deleteNode) {
			last = preNode;
		}
		hashMap.remove(key);
	}

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		LfuCache aa = new LfuCache();
		aa.add("1", 1);
		aa.add("2", 2);
		aa.add("3", 3);
		aa.add("4", 4);
		System.out.println(aa.get("1").value);
		aa.add("5", 5);
		System.out.println(aa.get("2").value);
		System.out.println(aa.get("4").value);
	}
}
