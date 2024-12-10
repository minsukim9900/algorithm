import java.io.*;
import java.util.*;

public class Solution {
	private static Queue<Integer> password;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 1; i <= 10; i++) {
			
			int t = Integer.parseInt(br.readLine());
			
			password = new ArrayDeque<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int n = 0; n<8; n++) {
				password.offer(Integer.parseInt(st.nextToken()));
			}
			process();
			
			sb.append("#").append(t).append(" ");
			
			for(int p : password) {
				sb.append(p).append(" ");
			}
			sb.append("\n");
			
		}
		System.out.println(sb.toString());
	}
	
	public static void process() {
		
		int minus = 1;
		while(true) {
			
			int tmp = password.poll();
			tmp -= minus;
			if(tmp <= 0) {
				tmp = 0;
				password.offer(tmp);
				break;
			}else {
				password.offer(tmp);
			}
			minus++;
			if(minus == 6) {
				minus = 1;
			}
			
		}
	}
	

}
