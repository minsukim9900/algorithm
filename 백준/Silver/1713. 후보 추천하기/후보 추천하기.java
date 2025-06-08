import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] result;
	private static int[][] numsInfo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		result = new int[N];
		numsInfo = new int[M + 1][2];

		int cnt = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int curr = Integer.parseInt(st.nextToken());

			if (numsInfo[curr][0] > 0) {
				numsInfo[curr][0]++;
				continue;
			}

			if (cnt == N) {
				int idx = deleteSearchCandidate();
				numsInfo[idx][0] = 0;
				numsInfo[idx][1] = 0;
			} else {
				cnt++;
			}
			numsInfo[curr][0]++;
			numsInfo[curr][1] = i;
		}

		for (int i = 1; i <= M; i++) {
			if(numsInfo[i][0] > 0) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb.toString());

	}

	private static int deleteSearchCandidate() {
		int idx = 0;
		int min = 1001;

		for (int i = 1; i <= M; i++) {
			if (numsInfo[i][0] == 0) {
				continue;
			}

			if (min > numsInfo[i][0]) {
				min = numsInfo[i][0];
				idx = i;
			} else if (min == numsInfo[i][0]) {
				if (numsInfo[idx][1] > numsInfo[i][1]) {
					idx = i;
				}
			}
		}

		return idx;
	}
}