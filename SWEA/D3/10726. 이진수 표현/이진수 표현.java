import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if ((M & (1 << i)) != 0) {
					cnt++;
				}
			}
			
			if(cnt == N) {
				System.out.println("#"+t+" "+"ON");
			}else {
				System.out.println("#"+t+" "+"OFF");
			}
		}
	}

}
