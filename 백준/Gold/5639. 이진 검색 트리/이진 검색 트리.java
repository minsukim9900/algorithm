import java.io.*;
import java.util.*;

public class Main {
	private static class Node {
		int value;
		Node head, left, right;

		public Node(int value, Main.Node head, Main.Node left, Main.Node right) {
			this.value = value;
			this.head = head;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		Node node = null;
		while (true) {
			String tmp = br.readLine();
			if (tmp == null) {
				break;
			}
			int value = Integer.parseInt(tmp);
			Node curr = new Node(value, null, null, null);

			if (node == null) {
				node = curr;
				continue;
			}

			insertValue(node, curr);
		}
		postOrder(node);
		System.out.println(sb.toString());

	}

	private static void insertValue(Node node, Node curr) {
		while (true) {
			if (node.value > curr.value) { // 현재 노드보다 curr value가 작을 때
				if (node.left == null) {
					node.left = curr;
					curr.head = node;
					break;
				}
				node = node.left;
			} else { // 현재 노드보다 curr.value가 클 때
				if (node.right == null) {
					node.right = curr;
					curr.head = node;
					break;
				}
				node = node.right;
			}
		}
	}

	private static StringBuilder sb = new StringBuilder();

	private static void postOrder(Node node) {
		if (node.left != null) {
			postOrder(node.left);
		}

		if (node.right != null) {
			postOrder(node.right);
		}

		sb.append(node.value).append("\n");
	}
}