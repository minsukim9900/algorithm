import java.io.*;
import java.util.*;

public class Main {
	private static PriorityQueue<Integer> maxHeap;
	private static PriorityQueue<Integer> minHeap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
		minHeap = new PriorityQueue<>();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			if (maxHeap.size() == minHeap.size()) {
				maxHeap.add(num);
			} else {
				minHeap.add(num);
			}

			if (isPoss() && maxHeap.peek() > minHeap.peek()) {
				int tempA = maxHeap.poll();
				int tempB = minHeap.poll();

				maxHeap.add(tempB);
				minHeap.add(tempA);
			}

			sb.append(maxHeap.peek()).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static boolean isPoss() {
		return !maxHeap.isEmpty() && !minHeap.isEmpty();
	}
}