import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			
			String num = br.readLine();
			
			boolean[] visited = new boolean[10];
			visited = Check(visited, num);

			int time = 1;
			String number = num;
			
			while (true) {

				if (isCheckAll(visited)) {
					
					System.out.println("#"+t+" "+number);
					break;
				}

				int tmp = Integer.parseInt(num) * (++time);
				number = Integer.toString(tmp);
				
				visited = Check(visited, number);
			}
			

		}
	}

	private static boolean isCheckAll(boolean[] visited) {
		for (int i = 0; i < 10; i++) {
			if (!visited[i])
				return false;
		}
		return true;
	}

	private static boolean[] Check(boolean[] visited, String number) {
		for(int i = 0; i<number.length(); i++) {
			int tmp = number.charAt(i) - '0';
			if(visited[tmp]) continue;
			visited[tmp] = true;
		}
		
		return visited;
	}
}
