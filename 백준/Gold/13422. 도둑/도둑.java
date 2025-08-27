import java.io.*;
import java.text.DateFormatSymbols;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] infos = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				infos[i] = Integer.parseInt(st.nextToken());
			}

			int money = 0;
			for (int i = 0; i < M; i++) {
				money += infos[i];
			}

			int left = 0;
			int right = M - 1;
			int answer = money < K ? 1 : 0;

			if (N != M) {
				for (int i = 1; i < N; i++) {
					money -= infos[left++];
					right = (right + 1) % N;
					money += infos[right];

					if (money < K) {
						answer++;
					}

				}
			}

			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}