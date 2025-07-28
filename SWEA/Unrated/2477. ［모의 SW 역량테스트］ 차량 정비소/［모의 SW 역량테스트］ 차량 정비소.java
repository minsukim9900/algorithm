import java.io.*;
import java.util.*;

public class Solution {
	private static int N, M, K, A, B;
	private static int[] infoA, infoB;
	private static boolean[] visitedA, visitedB;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			infoA = new int[N + 1];
			visitedA = new boolean[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				infoA[i] = Integer.parseInt(st.nextToken());
			}

			infoB = new int[M + 1];
			visitedB = new boolean[M + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				infoB[i] = Integer.parseInt(st.nextToken());
			}

			Queue<int[]> guest = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				int time = Integer.parseInt(st.nextToken());
				guest.add(new int[] { i, time });
			}
			sb.append("#").append(t).append(" ").append(simulate(guest)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int simulate(Queue<int[]> guest) {
		int time = guest.peek()[1];
		int result = 0;

		PriorityQueue<int[]> workA = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) {
					return o1[2] - o2[2];
				}
				return o1[1] - o2[1];
			}
		});

		Queue<int[]> waitB = new ArrayDeque<>();

		PriorityQueue<int[]> workB = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});

		int count = 0;
		while (count < K) {
			// 접수 창구가 끝났다면 차량 정비 대기 줄에 진입
			while (!workA.isEmpty() && workA.peek()[1] <= time) {
				int[] curr = workA.poll();
				
				// 정비 창구 접수 종료했기 때문에 false 처리
				visitedA[curr[2]] = false;
				waitB.add(curr);
			}

			// 손님이 도착 한 시간에 접수 창구가 비어있다면 접수 창구에 넣기
			// 접수 창구가 비어있지 않다면 while 문 종료
			while (!guest.isEmpty() && guest.peek()[1] <= time) {
				boolean isPoss = false;

				for (int i = 1; i <= N; i++) {
					if (visitedA[i])
						continue;

					int[] curr = guest.poll();
					visitedA[i] = true;
					isPoss = true;
					workA.add(new int[] { curr[0], time + infoA[i], i });
					break;
				}
				if (!isPoss) {
					break;
				}
			}

			while (!workB.isEmpty() && workB.peek()[1] <= time) {
				int[] curr = workB.poll();
				visitedB[curr[3]] = false;
				count++;
				if (curr[2] == A && curr[3] == B) {
					result += curr[0];
				}
			}

			// 차량 정비 대기하는 사람들을 순서대로 차량 정비 시작
			while (!waitB.isEmpty()) {
				boolean isPoss = false;

				for (int i = 1; i <= M; i++) {
					if (visitedB[i])
						continue;
					int[] curr = waitB.poll();
					isPoss = true;
					visitedB[i] = true;
					workB.add(new int[] { curr[0], time + infoB[i], curr[2], i });
					break;
				}

				if (!isPoss) {
					break;
				}
			}

			time++;
		}
		return result == 0 ? -1 : result;
	}
}