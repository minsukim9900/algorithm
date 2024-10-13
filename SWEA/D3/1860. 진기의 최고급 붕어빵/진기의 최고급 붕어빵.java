import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 손님이 시간 오는 순서대로 정렬하고
		//
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 가게에 오는 손님 수
			int M = Integer.parseInt(st.nextToken()); // M초의 시간이 걸리면
			int K = Integer.parseInt(st.nextToken()); // K개의 붕어빵을 만듬;
			int bread = 0;
			int[] guest = new int[11112];
			
			st = new StringTokenizer(br.readLine());
			int max = -1;
			for(int n = 0; n<N; n++) { // 손님이 오는 시간을 오름차순으로 정렬
				int num = Integer.parseInt(st.nextToken());
				guest[num]++;
				if(max < num) {
					max = num;
				}
			}
			
			int time = 0;
			int btime = 0;
			int result = 0;
			
			while(time<=max) {
				if(btime == M) {
					bread += K;
					btime = 0;
				}
				if(guest[time]>=1) {
					if(bread < guest[time]) {
						System.out.printf("#%d Impossible\n", t+1);
						result = 0;
						break;
					}else {
						bread -= guest[time];
					}
				}
					time++;
					btime++;
					result = 1;
			}
			
			if(result == 1) {
				System.out.printf("#%d Possible\n", t+1);
			}
		}
		
	}
}