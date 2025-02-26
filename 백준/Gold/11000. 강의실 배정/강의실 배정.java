import java.io.*;
import java.util.*;

public class Main {

	private static int N, size;
	private static int[][] lec;
	private static int[] heap;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		lec = new int[N][2];
		heap = new int[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			lec[i][0] = Integer.parseInt(st.nextToken());
			lec[i][1] = Integer.parseInt(st.nextToken());

		}

		Arrays.sort(lec, new Comparator<int[]>() {

			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}

		});

		push(lec[0][1]);

		for (int i = 1; i < N; i++) {

			if (heap[1] <= lec[i][0]) {
				pop();
			}
			push(lec[i][1]);

		}
		
		System.out.println(size);

	}

	private static void swap(int i, int j) {
		int tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
	}

	private static void push(int data) {

		heap[++size] = data;

		int p = size / 2;
		int ch = size;

		while (ch != 1 && heap[p] > heap[ch]) {

			swap(p, ch);

			ch = p;
			p = ch / 2;

		}

	}

	private static int pop() {
		int popNum = heap[1];

		heap[1] = heap[size--];

		int p = 1;
		int ch = p * 2;
		if (ch + 1 <= size && heap[ch] > heap[ch + 1]) {
			ch++;
		}

		while (ch <= size && heap[p] > heap[ch]) {

			swap(p, ch);

			p = ch;
			ch = p * 2;

			if (ch + 1 <= size && heap[ch] > heap[ch + 1]) {
				ch++;
			}

		}

		return popNum;

	}

}