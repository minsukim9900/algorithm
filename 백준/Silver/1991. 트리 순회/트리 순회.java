import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static char[][] Node;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		Node = new char[N][3];
		Node[0][0] = 'A';

		for (int i = 0; i < N; i++) {
			String str = br.readLine();

			if (str.charAt(2) != '.') {
				Node[str.charAt(0) - 'A'][1] = str.charAt(2);
				Node[str.charAt(2) - 'A'][0] = str.charAt(0);
			} else if (str.charAt(2) == '.') {
				Node[str.charAt(0) - 'A'][1] = '1';
			}

			if (str.charAt(4) != '.') {
				Node[str.charAt(0) - 'A'][2] = str.charAt(4);
				Node[str.charAt(4) - 'A'][0] = str.charAt(4);
			} else if (str.charAt(4) == '.') {
				Node[str.charAt(0) - 'A'][2] = '1';
			}

		}

		preOrder(Node[0][0]);
		System.out.println();
		inOrder(Node[0][0]);
		System.out.println();
		postOrder(Node[0][0]);

	}

	private static void preOrder(char start) {
		if (start == '1') {
			return;
		}

		System.out.print(start);
		preOrder(Node[start - 'A'][1]);
		preOrder(Node[start - 'A'][2]);
	}

	private static void inOrder(char start) {
		if (start == '1') {
			return;
		}
		inOrder(Node[start - 'A'][1]);
		System.out.print(start);
		inOrder(Node[start - 'A'][2]);
	}

	private static void postOrder(char start) {
		if (start == '1') {
			return;
		}
		postOrder(Node[start - 'A'][1]);
		postOrder(Node[start - 'A'][2]);
		System.out.print(start);
	}

}