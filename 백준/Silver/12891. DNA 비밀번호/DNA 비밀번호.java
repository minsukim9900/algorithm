import java.io.*;
import java.util.*;

public class Main {
	private static int S, P;
	private static int[] info;
	private static Map<Character, Integer> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		info = new int[4];
		map = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		char[] dna = br.readLine().toCharArray();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			info[i] = Integer.parseInt(st.nextToken());
		}

		map.put('A', 0);
		map.put('C', 1);
		map.put('G', 2);
		map.put('T', 3);

		int[] comp = new int[4];
		int answer = 0;
		for (int i = 0; i < P; i++) {
			comp[map.get(dna[i])]++;
		}

		if (isPoss(comp))
			answer++;

		int left = 0;
		for (int i = P; i < S; i++) {
			comp[map.get(dna[left++])]--;
			comp[map.get(dna[i])]++;

			if (isPoss(comp))
				answer++;
		}
		System.out.println(answer);
	}

	private static boolean isPoss(int[] comp) {
		for (int i = 0; i < 4; i++) {
			if (comp[i] < info[i])
				return false;
		}
		return true;
	}
}
