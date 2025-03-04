import java.io.*;
import java.util.*;

public class Main {

	private static int[] visited = new int[((1 << 25) >> 5) + 1];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		String[] str = br.readLine().split(" ");

		for (int i = 0; i < str.length; i++) {
			
			int num = Integer.parseInt(str[i]);
			
			if((visited[num >> 5] & (1<< (num & 31))) == 0) {
				sb.append(num + " ");
				visited[num >> 5] |= (1<< (num & 31));
			}
			
			
		}
		
		System.out.println(sb);

	}

}
