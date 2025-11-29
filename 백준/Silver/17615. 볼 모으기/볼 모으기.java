import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		char[] state = br.readLine().toCharArray();
		boolean flag = true;

		List<Integer>[] info = new ArrayList[2];
		for (int i = 0; i < 2; i++) {
			info[i] = new ArrayList<>();
		}

		char comp = state[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			if (flag && comp == state[i])
				continue;

			flag = false;
			int index = state[i] == 'R' ? 0 : 1;

			info[index].add(i);
		}
		
		System.out.println(Math.min(info[0].size(), info[1].size()));
	}
}