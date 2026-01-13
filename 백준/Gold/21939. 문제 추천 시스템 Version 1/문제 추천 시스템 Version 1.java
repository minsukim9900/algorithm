
import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static TreeSet<Info>[] problems;
	private static Map<Integer, Info> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		map = new HashMap<>();
		problems = new TreeSet[2];
		problems[0] = new TreeSet<>((a, b) -> a.l == b.l ? Integer.compare(a.p, b.p) : Integer.compare(a.l, b.l));
		problems[1] = new TreeSet<>((a, b) -> a.l == b.l ? Integer.compare(b.p, a.p) : Integer.compare(b.l, a.l));

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			Info info = new Info(P, L, true);
			for (int j = 0; j < 2; j++) {
				problems[j].add(info);
			}
			map.put(P, info);
		}

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			String[] state = br.readLine().split(" ");

			switch (state[0]) {
			case "add":
				int p = Integer.parseInt(state[1]);
				int l = Integer.parseInt(state[2]);
				Info info = new Info(p, l, true);
				for(int j = 0; j < 2; j++) {
					problems[j].add(info);
				}
				map.put(p, info);
				break;
			case "recommend":
				int index = state[1].equals("1") ? 1 : 0;
				sb.append(recommend(index)).append("\n");
				break;
			case "solved":
				int problemNumber = Integer.parseInt(state[1]);
				info = map.get(problemNumber);
				info.state = false;
				break;
			}
		}
		System.out.println(sb.toString());
	}
	
	private static class Info {
		int p;
		int l;
		boolean state;
		
		public Info(int p, int l, boolean state) {
			this.p = p;
			this.l = l;
			this.state = state;
		}
	}
	private static int recommend(int index) {
		
		int pick = 0;
		Info curr = null;
		while(pick == 0) {
			curr = problems[index].pollFirst();
			
			if(!curr.state) continue;
			pick++;
		}
		problems[index].add(curr);
		return curr.p;
	}
}
