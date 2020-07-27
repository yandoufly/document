package com.yjy.tree;

import java.util.LinkedList;
import java.util.Stack;

public class TreeTest {

	/**
	 * ��������
	 */
	public static class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		public TreeNode(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		TreeNode[] nodes = buildTree();
		preOrder(nodes[0]);

		// ǰ�����
		System.out.println();
		preOrder2(nodes[0]);
		System.out.println();

		// �������
		midOrder(nodes[0]);
		System.out.println();
		midOrder2(nodes[0]);
		System.out.println();

		// �������
		postOrder(nodes[0]);
		System.out.println();
		postOrder2(nodes[0]);
		System.out.println();
		
		// ��α���
		levelOrder(nodes[0]);
	}

	/**
	 * ��������ʽ����һ����ȫ���������ṹ���£� 0 / \ 1 2 / \ /\ 3 4 5 6 /\ / 7 8 9
	 */
	private static TreeNode[] buildTree() {
		TreeNode[] nodes = new TreeNode[10];
		for (int i = 0; i < 10; i++) {
			nodes[i] = new TreeNode(i);
		}
		for (int i = 0; i < 10; i++) {
			if (i * 2 + 1 < 10)
				nodes[i].left = nodes[i * 2 + 1];
			if (i * 2 + 2 < 10)
				nodes[i].right = nodes[i * 2 + 2];
		}
		return nodes;
	}

	// ǰ�����-�ݹ�ʵ��
	private static void preOrder(TreeNode node) {
		if (node != null) {
			System.out.print(node.value + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	// ǰ�����-�ǵݹ�ʵ��
	private static void preOrder2(TreeNode node) {
		Stack<TreeNode> stack = new Stack<>();
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				System.out.print(node.value + " ");
				stack.push(node.right);
				node = node.left;
			}

			if (!stack.isEmpty()) {
				node = stack.pop();
			}
		}
	}

	// �������-�ݹ�ʵ��
	private static void midOrder(TreeNode node) {
		if (node != null) {
			midOrder(node.left);
			System.out.print(node.value + " ");
			midOrder(node.right);
		}
	}

	// �������-�ǵݹ�ʵ��
	private static void midOrder2(TreeNode node) {
		Stack<TreeNode> stack = new Stack<>();
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			if (!stack.isEmpty()) {
				node = stack.pop();
				System.out.print(node.value + " ");
				node = node.right;
			}
		}
	}

	// �������-�ݹ�ʵ��
	private static void postOrder(TreeNode node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.value + " ");
		}
	}

	// �������-�ǵݹ�ʵ��
	private static void postOrder2(TreeNode node) {
		int left = 1;// �ڸ���ջ���ʾ��ڵ�
		int right = 2;// �ڸ���ջ���ʾ�ҽڵ�
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<Integer> stack2 = new Stack<Integer>();// ����ջ�������ж��ӽڵ㷵�ظ��ڵ�ʱ������ڵ㻹���ҽڵ㡣

		while (node != null || !stack.empty()) {
			while (node != null) {// ���ڵ�ѹ��ջ1������ջ2���ڵ���Ϊ��ڵ�
				stack.push(node);
				stack2.push(left);
				node = node.left;
			}
			while (!stack.empty() && stack2.peek() == right) {// ����Ǵ����ӽڵ㷵�ظ��ڵ㣬��������ɣ�������ջ��ջ������
				stack2.pop();
				System.out.print(stack.pop().value + " ");
			}
			if (!stack.empty() && stack2.peek() == left) {// ����Ǵ����ӽڵ㷵�ظ��ڵ㣬�򽫱�Ǹ�Ϊ���ӽڵ�
				stack2.pop();
				stack2.push(right);
				node = stack.peek().right;
			}

		}
	}

	// ��α���
	public static void levelOrder(TreeNode node) {
		if (node == null)
			return;
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		list.add(node);
		TreeNode currentNode;
		while (!list.isEmpty()) {
			currentNode = list.poll();
			System.out.print(currentNode.value + " ");
			if (currentNode.left != null)
				list.add(currentNode.left);
			if (currentNode.right != null)
				list.add(currentNode.right);
		}
	}
}
