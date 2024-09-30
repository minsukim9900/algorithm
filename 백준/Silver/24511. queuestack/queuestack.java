import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		Deque<Integer> room = new ArrayDeque<>();

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int[] queueStack = new int[N];
		for (int i = 0; i < N; i++) {
			queueStack[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(queueStack[i]==0) {
			room.addLast(num);
			}
		}

		int M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			int number = Integer.parseInt(st.nextToken());
			room.addFirst(number);
			sb.append(room.pollLast()).append(" ");
		}
		System.out.println(sb.toString());
	}
}