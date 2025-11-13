import java.io.*;
import java.util.*;

public class Main {
	private static long N, H;
	private static int[][] info;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		H = Long.parseLong(st.nextToken());

		info = new int[(int) N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
			info[i][2] = Integer.parseInt(st.nextToken());
		}
		System.out.println(binarySearch());
	}

	private static long binarySearch() {
		long answer = 0L;
		long s = 0L;
		long e = Long.MAX_VALUE;

		long attack = H;

		while (s <= e) {
			long mid = (s + e) / 2;
			if (check(mid)) {
				answer = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
			H = attack;
		}

		return answer;
	}

	private static boolean check(long maxHp) {
		long currHp = maxHp;
		for (int[] state : info) {
			if (state[0] == 1) {
				currHp = fightMonster(currHp, state);
			} else {
				currHp = getPostion(currHp, maxHp, state);
			}

			if (currHp == 0L) {
				return false;
			}
		}
		return true;
	}

	private static long fightMonster(long hp, int[] state) {
		int monsterAp = state[1];
		int monsterHp = state[2];

		long turn = monsterHp / H;
		if (monsterHp % H > 0) {
			turn++;
		}
		if (hp - (monsterAp * (turn - 1)) > 0) {
			return hp - (monsterAp * (turn - 1));
		} else {
			return 0L;
		}
	}

	private static long getPostion(long currHp, long maxHp, int[] state) {
		H += state[1];
		return Math.min(currHp + state[2], maxHp);
	}
}