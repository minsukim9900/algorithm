import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		List<Info>[] day = new ArrayList[100];
		for (int i = 0; i < 100; i++) {
			day[i] = new ArrayList<>();
		}

		int N = Integer.parseInt(br.readLine());
		Map<String, Info> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			String name = st.nextToken();
			int w = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int money = Integer.parseInt(st.nextToken());

			Info info = new Info(money, true);

			day[(w - 1) * 7 + d].add(info);
			map.put(name, info);
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			String name = st.nextToken();
			int money = Integer.parseInt(st.nextToken());

			Info info = map.get(name);
			if (info.money > money) {
				info.isPoss = false;
			}
		}

		int count = 0;
		int ans = 0;
		for (int i = 0; i < 100; i++) {
			boolean isContinue = false;
			for (Info info : day[i]) {
				if (info.isPoss) {
					count++;
					isContinue = true;
					break;
				}
			}

			if (!isContinue) {
				ans = Math.max(ans, count);
				count = 0;
			}
		}
		ans = Math.max(ans, count);
		System.out.println(ans);
	}

	private static class Info {
		int money;
		boolean isPoss;

		public Info(int money, boolean isPoss) {
			this.money = money;
			this.isPoss = isPoss;
		}
	}
}
