import java.io.*;
import java.util.*;

public class Solution {

	private static int N, M, K, A, B;
	private static int[] at, bt;
	private static Queue<int[]> pt;
	private static ArrayList<Integer>[] survey;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			at = new int[N + 1];
			bt = new int[M + 1];
			pt = new ArrayDeque<>();

			st = new StringTokenizer(br.readLine());
			
            // 접수청구 별 걸리는 시간을 저장하는 배열
			for (int i = 1; i <= N; i++) {
				at[i] = Integer.parseInt(st.nextToken());
			}
	
			st = new StringTokenizer(br.readLine());
	
            // 정비청구 별 걸리는 시간을 저장하는 배열
			for (int i = 1; i <= M; i++) {
				bt[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			
            // 도착 순서는 순서대로 주어지기 때문에 손님 인덱스와 도착 시간을 배열 저장해서 큐로 넣음
			for (int i = 1; i <= K; i++) {
				pt.add(new int[] { i, Integer.parseInt(st.nextToken()) });
			}
			
            // 서비스가 모두 종료 되면
			aftertime();
			
			int sum = 0;
			
			for(int i = 1; i<=K; i++) {
				
                // 설문조사에 적은것을 바탕으로 조건에 맞는 것만 더해줌
				if(survey[i].get(0) == A && survey[i].get(1) == B) {
					sum += i;
				}
				
			}
			
			if(sum == 0) sum = -1;
			sb.append("#" + t + " " + sum +"\n");
		}
		System.out.println(sb.toString());
	}
	
    // 접수와 정비 창구의 하루 일과
	private static void aftertime() {
	
        // 설문조사는 사람 수 별로 크기를 할당했음
		survey = new ArrayList[K + 1];

		for (int i = 1; i <= K; i++) {
			survey[i] = new ArrayList<>();
		}
	
        // 접수처 큐
		Queue<int[]>[] aq = new ArrayDeque[N + 1];

		for (int i = 1; i <= N; i++) {
			aq[i] = new ArrayDeque<>();
		}
		
        // 장비 큐
		Queue<int[]>[] bq = new ArrayDeque[M + 1];

		for (int i = 1; i <= M; i++) {
			bq[i] = new ArrayDeque<>();
		}
		
        // 접수 끝나고 장비에 들어가기 전 대기하는 줄
		Queue<Integer> wait = new ArrayDeque<>();
		
        // 현재 시각
		int t = 0;
        
        // 몇 명 완료 했는지 확인하는 변수
		int pick = 0;
		while (true) {
			
			// 접수 창구
			for (int i = 1; i <= N; i++) {
				
                // 만약 해당 접수 창구에 사람이 있고, 접수 창구에 있는 손님이 끝났을 경우
                // 설문조사에 이 접수 창구를 이용했다는 것을 저장하고
                // wait 큐에 대기 시킴
				if (!aq[i].isEmpty() && aq[i].peek()[1] == t) {
					int num = aq[i].poll()[0];
					survey[num].add(i);
					wait.add(num);
				}
				
                // 현재 대기하는 사람이 존재하고, 대기 한 사람이 이미 도착했으며
                // 현재 접수 창구가 비어있다면
                // 그 사람을 비어있는 접수 창구에 넣습니다.
				if (!pt.isEmpty() && pt.peek()[1] <= t && aq[i].isEmpty()) {
					int[] curr = pt.poll();
					aq[i].add(new int[] { curr[0], t + at[i] });
				}

			}
			

			// 정비 창구

			for (int i = 1; i <= M; i++) {
				
                // 정비 창구에 사람이 존재하고, 그 사람의 정비 창구 업무가 끝났다면
                // 설문조사에 저장하고
                // pick을 올려줍니다.
				if (!bq[i].isEmpty() && bq[i].peek()[1] == t) {
					int num = bq[i].poll()[0];
					survey[num].add(i);
					pick++;
				}
				
                // 접수 창구를 끝내고 대기 하는 사람이 존재하고
                // 현재 정비 창구에 손님이 없다면
                // 대기 하는 사람을 정비 창구에 넣습니다.
				if (!wait.isEmpty() && bq[i].isEmpty()) {
					int curr = wait.poll();
					bq[i].add(new int[] { curr, t + bt[i] });
				}

			}
			
            // 만약 모든 사람이 업무를 다 보면 종료 시킵니다.
			if (pick == K)
				break;

			t++;
		}


	}
}
