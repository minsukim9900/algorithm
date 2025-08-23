import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Map<String, String> info = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			info.put(String.valueOf(i + 1), str);
			info.put(str, String.valueOf(i + 1));
		}

		for (int i = 0; i < M; i++) {
			System.out.println(info.get(br.readLine()));
		}
	}
}